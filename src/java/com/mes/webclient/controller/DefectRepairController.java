
package com.mes.webclient.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.DsList;
import com.mes.compatibility.client.ListFilter;
import com.mes.compatibility.client.ReportDataDefinitionVariable;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.shopflow.common.constants.filtering.IListFilterAttributes;
import com.mes.webclient.controller.vo.DsListVO;
import com.mes.webclient.controller.vo.OperationDefectCodeVO;
import com.mes.webclient.controller.vo.ReportDataDefinitionVO;
import com.mes.webclient.service.impl.IMService;

@Controller("DefectRepairController")
@RequestMapping("/DefectRepairController.sp")
public class DefectRepairController extends BaseController
{
	@Autowired
	IMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{
		return "im/defectrepair/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(OperationDefectCodeVO odcVO, Model model)
	{
		long key = odcVO.getKey();
		model.addAttribute(INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			try
			{
				String sql = " select  atr_key, 'business_unit', VIN_NUM_S,'order_num' , quality_gate_1_S,quality_gate_2_S ,defect_level_S ,responsible_party_S ,  isnull( part_level1_S ,'') + '/' + isnull( part_level2_S ,'') +'/' +isnull( part_level3_S ,'') +'/'+isnull( part_level4_S ,'') +'/'+isnull( part_level5_S ,'')    +'/'+ isnull( level1_S ,'')     +'/'+ isnull( level2_S ,'')  + '/'+ isnull( defect_name_S ,'')   ,  input_user_S   , input_time_T from UD_defect_car WHERE status_S = '1'     and atr_key =  " + key ;
				Vector<String[]> vectorDual = imService.getFunctions().getArrayDataFromActive(sql, 100);
			    if(vectorDual .size() !=1  ){
			    	return null;
			    }
			    String [] defectCarInfo = vectorDual.get(0);
			    String defectkey = defectCarInfo[0];
			    String businessUnit = defectCarInfo[1];
			    String vin = defectCarInfo[2];
			    String orderNum = defectCarInfo[3];
			    String qualityGate1 = defectCarInfo[4];
			    String qualityGate2 = defectCarInfo[5];
			    String defectLevel = defectCarInfo[6];
			    String responsibleParty = defectCarInfo[7];
			    String defectDesc = defectCarInfo[8];
			    
			    OperationDefectCodeVO odcDBVO = new OperationDefectCodeVO();
			    odcDBVO.setKey( Long.parseLong( defectkey ) );
			    odcDBVO.setBusinessUnit(businessUnit);
			    odcDBVO.setVin(vin);
			    odcDBVO.setOrderNum(orderNum);
			    odcDBVO.setQualityGate_1(qualityGate1);
			    odcDBVO.setQualityGate_2(qualityGate2);
			    odcDBVO.setDefectLevel(defectLevel);
			    odcDBVO.setResponsibleParty(responsibleParty);
			    odcDBVO.setDefectDesc(defectDesc);
				model.addAttribute(VIEW_OBJECT, odcDBVO);
				
				UTHandler athandler  = imService.getFunctions().createATHandler("defect_car_repair") ;
				UTRowFilter filter =  imService.getFunctions().createATRowFilter("defect_car_repair");
				filter.forColumnNameEqualTo("repair_key", key) ;
				Vector<UTRow> repairATRows = athandler.getATRowsByFilter(filter, false);
				model.addAttribute("repairATRows", repairATRows);
				
				
				
			}
			catch (Exception e)
			{

				e.printStackTrace();
			}
		}else{
			model.addAttribute(VIEW_OBJECT, odcVO);
		}
		return "im/defectrepair/addOrEdit";
	}

	/**
	 * 删除
	 * @return 
	 */
	@RequestMapping(params = "del")
	public @ResponseBody String del(@RequestParam("key") long key)
	{
			try
			{
				if (key > 0)
				{
					ListFilter listFilter = imService.createListFilter();
					listFilter.addSearchBy(IListFilterAttributes.KEY, IFilterComparisonOperators.EQUAL_TO, key);
					Vector<DsList> currentList = listFilter.exec();
					if(currentList.size() ==0){
						System.out.println("系统数据错误");
					}
					DsList dsListObj  = currentList.get(0);
					Response response = dsListObj.delete(
						null, null);
					if (response.isError())
					{
						throw new Exception(response.getFirstErrorMessage());
					}
					return ajaxDoneSuccess();
				}
				else
				{
					throw new Exception("无法找到该对象的key");
				}
			}
			catch (Exception e)
			{
				logger.error(e);
				return ajaxDoneError(e.getLocalizedMessage());
			}
	}

	/**
	 * 查询
	 */
	@ResponseBody
	@RequestMapping(params = "toList")
	public String queryList( OperationDefectCodeVO odcVO )
	{
		JSONArray rt = null;
		try
		{
			String vin = odcVO.getVin();
			String qualityGate_1 = odcVO.getQualityGate_1();
			String qualityGate_2 = odcVO.getQualityGate_2();
			String resParty = odcVO.getResponsibleParty();
			int queryNum = odcVO.getQueryNum();
			//工厂分类
			String sql = " select    d.atr_key, brand_name_S ,factory_name_S ,'buesiness_unit', VIN_NUM_S,'order_num' , quality_gate_1_S,quality_gate_2_S ,defect_level_S ,responsible_party_S,  isnull( part_level1_S ,'') + '/' + isnull( part_level2_S ,'') +'/' +isnull( part_level3_S ,'') +'/'+isnull( part_level4_S ,'') +'/'+isnull( part_level5_S ,'')   +'/'+ isnull( level1_S ,'')     +'/'+ isnull( level2_S ,'')  + '/'+ isnull( defect_name_S ,'')   ,d.input_user_S ,  CONVERT (varchar,d.input_time_T ,120 )   from UD_defect_car d , UD_factory_brand f  WHERE d.factory_brand_key_I = f.atr_key and   status_S = '1'  ";
			if( vin != null && !"".equals( vin ) ){
				sql = sql + "  and VIN_NUM_S like '%"+vin+"%' ";
			}
			
			if( qualityGate_1 != null && !"".equals(qualityGate_1) ){
				sql = sql + "  and quality_gate_1_S like '%"+qualityGate_1+"%' ";
			}
			
			if( qualityGate_2 != null && !"".equals(qualityGate_2 ) ){
				sql = sql + "  and quality_gate_2_S like '%"+qualityGate_2+"%' ";
			}
			
			if( resParty != null && !"".equals( resParty ) ){
				sql = sql + "  and responsible_party_S like '%"+resParty+"%' ";
			}
			
           
			
			Vector<String[]> vectorDual = imService.getFunctions().getArrayDataFromActive(sql, 100);
			rt = JSONArray.fromObject(vectorDual);
			System.out.println(rt.toString());
			return rt.toString();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;

	}

	
	@RequestMapping(params = "saveSubmit", method=RequestMethod.POST)  
	public @ResponseBody
	String saveSubmit(OperationDefectCodeVO odcVO  , HttpServletRequest request,
			HttpServletResponse response )
	{
		try
		{
			long key = odcVO.getKey();
			UTRow atrow = null;
			if( key > 0 ){
				UTHandler athandler  = imService.getFunctions().createATHandler("defect_car") ;
				UTRowFilter filter =  imService.getFunctions().createATRowFilter("defect_car");
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> currentList = athandler.getATRowsByFilter(filter, false);
				if(currentList.size() == 0){
					return ajaxDoneError( "数据【"+key+"】不存在");
				}
				atrow  = currentList.get(0);
			}
				
			if(atrow == null  ){
			}
		 	atrow.setValue("status", "2");
			Response r = atrow.save( imService.getFunctions().getDBTime() , null, null);
			if(r.isError()){
				return ajaxDoneError( "提交数据【"+key+"】保存失败"+r.getFirstErrorMessage());
			}
			
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
		
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params = "save", method=RequestMethod.POST)  
	public @ResponseBody
	String save(OperationDefectCodeVO odcVO  , HttpServletRequest request,
			HttpServletResponse response )
	{
		try
		{
			long key = odcVO.getKey();
			String vin = odcVO.getVin();
			String brandName = odcVO.getBrandName();
			
			/*String businessUnit = odcVO.getBusinessUnit();
			
			String orderNum = odcVO.getOrderNum();
			String qualityGate_1 = odcVO.getQualityGate_1();
			String qualityGate_2 = odcVO.getQualityGate_2();
			String responsibleParty = odcVO.getResponsibleParty();
			String qualityGate_2 = odcVO.getQualityGate_2();*/
			String opinion = odcVO.getRepairOpinion();
			String tableDataStr = odcVO.getTableData();
			UTRow atrow = null;
			if( key > 0 ){
				UTHandler athandler  = imService.getFunctions().createATHandler("defect_car") ;
				UTRowFilter filter =  imService.getFunctions().createATRowFilter("defect_car");
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> currentList = athandler.getATRowsByFilter(filter, false);
				if(currentList.size() == 0){
					return ajaxDoneError( "数据【"+key+"】不存在");
				}
				atrow  = currentList.get(0);
			}
				
			if(atrow == null  ){
			}
		//	atrow.setValue("status", "2");
			atrow.setValue( "repair_opinion", opinion );
			 
			
			
			
			UTHandler athandler  = imService.getFunctions().createATHandler("defect_car_repair") ;
			UTRowFilter filter =  imService.getFunctions().createATRowFilter("defect_car_repair");
			filter.forColumnNameEqualTo("repair_key", key) ;
			Vector<UTRow> repairList = athandler.getATRowsByFilter(filter, false);
			for (UTRow repairRow : repairList) {
				repairRow.delete( imService.getFunctions().getDBTime(), null, null);
			}
			
			if(tableDataStr != null && !tableDataStr.equals("") ){
				tableDataStr = URLDecoder.decode(tableDataStr);
 				String [] rows  =tableDataStr.split(";;");
				for(String row : rows){
					String[] cels = row.split(",");
					// myName+","+myDefaultValue +","+ myDesc +","+isGlob+","+selectValue+";";
					
					String partNum = cels[0];
					String partDesc = cels[1];
					String supNum = cels[2];
					String supDesc = cels[3];
					String partCount = cels[4];
					String partTime = cels[5];
				    
				    UTRow repairATRow = 	athandler.createATRow();
				    repairATRow.setValue("repair_key", key);
				    repairATRow.setValue("factory_name","fn" );//atrow.getValue("factory_name")
				    repairATRow.setValue("brand_name", "bn" );  //atrow.getValue("brand_name")
				    repairATRow.setValue("VIN_NUM", vin);
				    repairATRow.setValue("part_name", partDesc);
				    repairATRow.setValue("part_num", partNum);
				    repairATRow.setValue("supplier_name", supDesc);
				    repairATRow.setValue("supplier_num", supNum);
				    repairATRow.setValue("part_count", partCount);
				    repairATRow.setValue("repair_time", partTime);
				    repairATRow.setValue("handle_user", "xue");
				    
				}
			
			}
			
			Response r = athandler.save( imService.getFunctions().getDBTime() , null, null);
			if(r.isError()){
				return ajaxDoneError( "数据列表【"+key+"】保存失败"+r.getFirstErrorMessage());
			}
			
			/*Response r =  atrow.save( imService.getFunctions().getDBTime() , null, null);
			if(r.isError()){
				return ajaxDoneError( "数据列表【"+key+"】保存失败"+r.getFirstErrorMessage());
			}*/
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
	}
}