
package com.mes.webclient.controller;

import java.util.Vector;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.webclient.controller.vo.QualityGateVO;
import com.mes.webclient.service.impl.IMService;

@Controller("QualityGateController")
@RequestMapping("/QualityGateController.sp")
public class QualityGateController extends BaseController
{
	@Autowired
	IMService imService;
	
	String tableName = "quality_gate";
	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{
		return "im/qualitygate/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(QualityGateVO qualityGateVO, Model model)
	{
		long key = qualityGateVO.getKey();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			try
			{
				String sql = "select    q.atr_key, factory_name_S,brand_name_S , quality_gate_1_S,quality_gate_2_S   , f.atr_key  from UD_quality_gate q , UD_factory_brand f WHERE  f.atr_key = q.factory_brand_key_I and q.atr_key = " + key;
 
				
				Vector<String[]> vectorDual = imService.getFunctions().getArrayDataFromActive(sql, 100);
				if( vectorDual.size() == 0){
					return ajaxDoneError("当前记录【"+key+"】不存在！");
				}
				String[] array = vectorDual.get(0);
				QualityGateVO vo = new QualityGateVO();
				long key1 = Long.parseLong( array[0] );
				String factoryName = array[1];
				String brandName   = array[2];
				String qualityGate_1 = array[3] ;
				String qualityGate_2 = array[4];
				String fbKey  		 = array[5];
				vo.setKey(key1);
				vo.setQualityGate_1(qualityGate_1);
				vo.setQualityGate_2(qualityGate_2);
				vo.setFactoryName(factoryName);
				vo.setBrandName(brandName);
				vo.setFbKey(fbKey);
				model.addAttribute(
						VIEW_OBJECT, vo);
				 
			}
			catch (MESException e)
			{

				e.printStackTrace();
			}
		}else{
		model.addAttribute(
			VIEW_OBJECT, qualityGateVO);
		}
		return "im/qualitygate/addOrEdit";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	public @ResponseBody
	String del(@RequestParam("key") long key)
	{
		try
		{
			if (key > 0)
			{
				UTRow atrow = null ;
				UTHandler athandler  = imService.getFunctions().createATHandler("quality_gate") ;
				UTRowFilter filter =  imService.getFunctions().createATRowFilter("quality_gate");
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> currentList = athandler.getATRowsByFilter(filter, false);
				if( currentList.size() == 0){
					return null;
				}
				atrow = currentList.get(0);
		 
				if( atrow == null){
					return ajaxDoneError("当前数据【"+key+"】不存在");
				}
				Response res =  atrow.delete( imService.getFunctions().getDBTime(), null,null);
				if( res.isError() ){
					return ajaxDoneError(res.getFirstErrorMessage());
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
	public String queryList(QualityGateVO qualityGateVO)
	{
		JSONArray rt = null;
		try
		{
			String qualityGate_1P = qualityGateVO.getQualityGate_1();
			String qualityGate_2P = qualityGateVO.getQualityGate_2();
			String factoryName = qualityGateVO.getFactoryName();
			String brandName = qualityGateVO.getBrandName();
			
			int queryNum = qualityGateVO.getQueryNum();
			//工厂分类
			String sql = "select  top "+queryNum+"  q.atr_key,f.factory_name_S, f.brand_name_S , quality_gate_1_S,quality_gate_2_S   ,q.input_user_S ,  CONVERT (varchar,q.input_time_T ,120 )   from UD_quality_gate q , UD_factory_brand f WHERE  f.atr_key = q.factory_brand_key_I  ";
			if( qualityGate_1P != null && !"".equals( qualityGate_1P ) ){
				sql = sql + "  and quality_gate_1_S like '%"+qualityGate_1P+"%' ";
			}
			
			if( qualityGate_2P != null && !"".equals(qualityGate_2P) ){
				sql = sql + "  and quality_gate_2_S like '%"+qualityGate_2P+"%' ";
			}
			
			if( brandName != null && !"".equals(brandName) ){
				sql = sql + "  and f.brand_name_S like '%"+brandName+"%' ";
			}
			
			if( factoryName != null && !"".equals(factoryName) ){
				sql = sql + "  and f.factory_name_S like '%"+factoryName+"%' ";
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

	/**
	 * 保存
	 */
	@RequestMapping(params = "save")
	public @ResponseBody
	String save(QualityGateVO qualityGateVO)
	{
		try
		{
			long key = qualityGateVO.getKey();
			String qualityGate_1 = qualityGateVO.getQualityGate_1();
			String qualityGate_2 = qualityGateVO.getQualityGate_2();
			String fdKey = qualityGateVO.getFbKey();
			long fdKeyL = 0;
			if(fdKey !=null){
				fdKeyL = Long.parseLong(fdKey);
			}
			UTRow atrow = null;
			UTHandler athandler  = imService.getFunctions().createATHandler(tableName) ;
			if (key < 0){
				atrow = athandler.createATRow(); 
			}
			else
			{
				UTRowFilter filter =  imService.getFunctions().createATRowFilter(tableName);
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> currentList = athandler.getATRowsByFilter(filter, false);
				if( currentList.size() == 0){
					return null;
				}
				atrow = currentList.get(0);
			}
			if( atrow == null){
				return null ;
			}
			atrow.setValue("quality_gate_1" ,qualityGate_1 );
			atrow.setValue("quality_gate_2", qualityGate_2 );
			atrow.setValue("factory_brand_key", fdKeyL );
	 
			atrow.setValue("input_user", imService.getFunctions().getCurrentUser().getName());
			atrow.setValue("input_time", imService.getFunctions().getDBTime());
			
			UTRowFilter filter1 =  imService.getFunctions().createATRowFilter(tableName);
			filter1.forColumnNameEqualTo("quality_gate_1" ,qualityGate_1 );
			filter1.forColumnNameEqualTo("quality_gate_2" ,qualityGate_2 );
			filter1.forColumnNameEqualTo("factory_brand_key", fdKeyL );
		 
			
			Vector<UTRow> dbList = athandler.getATRowsByFilter(filter1, false);
			if( dbList.size() > 0){
				return ajaxDoneError("保存失败，原因为:当前记录已经存在！" );
			}
			
			
			Response response = atrow.save( imService.getFunctions().getDBTime(), null,null);
			if (response.isError())
			{
				return ajaxDoneError("保存失败，原因为:" + response.getFirstErrorMessage());
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