
package com.mes.webclient.controller;

import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.webclient.controller.vo.DefectSenderVO;
import com.mes.webclient.controller.vo.OperationDefectCodeVO;
import com.mes.webclient.service.impl.IMService;

@Controller("DefectSenderController")
@RequestMapping("/DefectSenderController.sp")
public class DefectSenderController extends BaseController
{
	@Autowired
	IMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(OperationDefectCodeVO odcVO , Model model)
	{
		try{
		String sql = " select DISTINCT  t.quality_gate_1_S , t.quality_gate_2_S  from UD_quality_gate t  ";
		 
		 
		Vector<String[]> vectorData = imService.getFunctions().getArrayDataFromActive(sql, 100);
		
		model.addAttribute("list1",vectorData);
		}catch(Exception ex){
			
		}
		return "im/qmDefectSender/list";
	}


	/**
	 * 查询
	 */
	@ResponseBody
	@RequestMapping(params = "toList")
	public String queryList( DefectSenderVO dsVO , Model model)
	{
		JSONArray rt = null; 
		try
		{
			String desc =  dsVO.getDefectDesc();
			//工厂分类
			if( desc != null ){
				
				desc = URLDecoder.decode(desc);
			}
			String sql = " select * from ( " +
		     " select top 5  atr_key,  isnull( part_level1_S ,'') + '/' + isnull( part_level2_S ,'') +'/' +isnull( part_level3_S ,'') +'/'+isnull( part_level4_S ,'') +'/'+isnull( part_level5_S ,'')  +'/'+ isnull( level1_S ,'')     +'/'+ isnull( level2_S ,'')  as \"desc1\",defect_level_S  , CONVERT ( varchar ,  GETDATE() ,120 ) TIME1  from UD_defect_car  order by checkDate_S desc " + 
		     " )    T  where  1=1 " ; 
			//String sql = " select top 5  atr_key,  isnull( part_level1_S ,'') + '/' + isnull( part_level2_S ,'') +'/' +isnull( part_level3_S ,'') +'/'+isnull( part_level4_S ,'') +'/'+isnull( part_level5_S ,'')  +'/'+ isnull( level1_S ,'')     +'/'+ isnull( level2_S ,'')  ,defect_level_S  , CONVERT ( varchar ,  GETDATE() ,120 )  from AT_defect_car  order by checkDate_S desc  ";
			if( desc != null && !"".equals(desc)){
				sql = sql + " and desc1    LIKE '%"+desc+"%' ESCAPE '/' ";
			}
			Vector<String[]> vectorData = imService.getFunctions().getArrayDataFromActive(sql, 100);
		 
			 
			rt = JSONArray.fromObject(vectorData);
		    System.out.println(rt.toString());
			return rt.toString();
		 
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;

	}

	/**
	 * 保存
	 */
	@RequestMapping(params = "save", method=RequestMethod.POST)  
	public @ResponseBody
	String save(DefectSenderVO vo  , HttpServletRequest request,
			HttpServletResponse response )
	{
		try
		{
			String descs = vo.getDefectDesc();
			String gates = vo.getReceiveQuanlityGate();
			 
			UTHandler athandler  = imService.getFunctions().createATHandler("defect_sender") ;
			if(descs == null || descs.equals("") || gates== null || gates.equals("")  ){
				//ajaxDoneError(e.getMessage());
				return "描述与质量工位必须有值";
			}
			descs = URLDecoder.decode(descs);
			gates = URLDecoder.decode(gates);
					
					
			String[] descArray = descs.split(";;");
			String[] gateArray = gates.split(";;");
			for(String desc : descArray){
				for( String gate : gateArray  ){
					if( desc !=null && gate != null){
						UTRow  atrow = athandler.createATRow();
						atrow.setValue( "defect_desc", desc);
						atrow.setValue("quality_gate_1", gate);
						atrow.setValue("quality_gate_2", gate);
						atrow.setValue("status", "0");
					}
				}
			} 
			
			
			
			 
			
			Response r = athandler.save( imService.getFunctions().getDBTime() , null, null);
			if(r.isError()){
				return ajaxDoneError( "数据保存失败"+r.getFirstErrorMessage());
			}
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
	}
}