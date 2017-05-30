
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
import com.mes.webclient.controller.vo.OperationQualityPartVO;
import com.mes.webclient.controller.vo.QualityGateVO;
import com.mes.webclient.service.impl.IMService;

@Controller("OperationQualityPartController")
@RequestMapping("/OperationQualityPartController.sp")
public class OperationQualityPartController extends BaseController
{
	@Autowired
	IMService imService;
	
	 
	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{
		return "im/operationQualityPart/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(OperationQualityPartVO operationQualityPartVO, Model model)
	{
		long key = operationQualityPartVO.getKey();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			try
			{
				String sql = "select oqp.atr_key , f.atr_key , f.factory_name_S ,f.brand_name_S , q.atr_key, q.quality_gate_1_S , q.quality_gate_2_S ,qp.atr_key , qp.level1_S ,qp.level2_S ,qp.level3_S , qp.level4_S , qp.level5_S    from UD_quality_part qp , UD_quality_gate q , UD_factory_brand f , UD_operation_quality_part oqp "+ 
						     " WHERE  f.atr_key =  oqp.factory_brand_key_I and oqp.quality_gate_key_I = q.atr_key and oqp.quality_part_key_I =qp.atr_key  and oqp.atr_key =  "+ key ;	
 
				
				Vector<String[]> vectorDual = imService.getFunctions().getArrayDataFromActive(sql, 100);
				if( vectorDual.size() == 0){
					return ajaxDoneError("当前记录【"+key+"】不存在！");
				}
				String[] array = vectorDual.get(0);
				OperationQualityPartVO vo = new OperationQualityPartVO();
				long keyOQP = Long.parseLong( array[0] );
				String factoryBrandKey = array[1];
				String factoryName = array[2];
				String brandName   = array[3];
				
				String qualityGateKey = array[4] ;
				String qualityGate_1 = array[5] ;
				String qualityGate_2 = array[6];
				 
				String qualityPartKey = array[7];
				String level1 = array[8];
				String level2 = array[9];
				String level3 = array[10];
				String level4 = array[11];
				String level5 = array[12];
				
				
				vo.setKey(keyOQP);
				
				vo.setFbKey(factoryBrandKey);
				vo.setFactoryName(factoryName);
				vo.setBrandName(brandName);
				
				vo.setQgKey(qualityGateKey);
				vo.setQualityGate_1(qualityGate_1);
				vo.setQualityGate_2(qualityGate_2);
				
				vo.setQpKey(qualityPartKey);
				vo.setLevel1(level1);
				vo.setLevel2(level2);
				vo.setLevel3(level3);
				vo.setLevel4(level4);
				vo.setLevel5(level5);
				
				model.addAttribute(
						VIEW_OBJECT, vo);
				 
			}
			catch (MESException e)
			{

				e.printStackTrace();
			}
		}else{
		model.addAttribute(
			VIEW_OBJECT, operationQualityPartVO);
		}
		return "im/operationQualityPart/addOrEdit";
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
				UTHandler athandler  = imService.getFunctions().createATHandler("operation_quality_part") ;
				UTRowFilter filter =  imService.getFunctions().createATRowFilter("operation_quality_part");
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
			String sql = "select  top "+queryNum+"  oqp.atr_key  , f.factory_name_S ,f.brand_name_S , q.quality_gate_1_S , q.quality_gate_2_S  , qp.level1_S ,qp.level2_S ,qp.level3_S , qp.level4_S , qp.level5_S    from UD_quality_part qp , UD_quality_gate q , UD_factory_brand f , UD_operation_quality_part oqp "+ 
            " WHERE  f.atr_key =  oqp.factory_brand_key_I and oqp.quality_gate_key_I = q.atr_key and oqp.quality_part_key_I =qp.atr_key "  ;

			if( qualityGate_1P != null && !"".equals( qualityGate_1P ) ){
				sql = sql + "  and q.quality_gate_1_S like '%"+qualityGate_1P+"%' ";
			}
			
			if( qualityGate_2P != null && !"".equals(qualityGate_2P) ){
				sql = sql + "  and q.quality_gate_2_S like '%"+qualityGate_2P+"%' ";
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
	String save(OperationQualityPartVO operationQualityPartVO)
	{
		try
		{
			long key = operationQualityPartVO.getKey();
			String factoryBrandKey = operationQualityPartVO.getFbKey();
			String qualityGateKey  = operationQualityPartVO.getQgKey();
			String qualityPartKey  = operationQualityPartVO.getQpKey();
			
			
			long factoryBrandKeyL = 0;
			if(factoryBrandKey !=null){
				factoryBrandKeyL = Long.parseLong(factoryBrandKey);
			}
			
			long qualityGateKeyL = 0;
			if(qualityGateKey !=null){
				qualityGateKeyL = Long.parseLong(qualityGateKey);
			}
			
			long qualityPartKeyL = 0;
			if(qualityPartKey !=null){
				qualityPartKeyL = Long.parseLong(qualityPartKey);
			}
			
			UTRow atrow = null;
			UTHandler athandler  = imService.getFunctions().createATHandler("operation_quality_part") ;
			if (key < 0){
				atrow = athandler.createATRow(); 
			}
			else
			{
				UTRowFilter filter =  imService.getFunctions().createATRowFilter("operation_quality_part");
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
			atrow.setValue("factory_brand_key" ,factoryBrandKeyL );
			atrow.setValue("quality_part_key", qualityPartKeyL );
			atrow.setValue("quality_gate_key", qualityGateKeyL );
	 
			atrow.setValue("input_user", imService.getFunctions().getCurrentUser().getName());
			atrow.setValue("input_time", imService.getFunctions().getDBTime());
			
			UTRowFilter filter1 =  imService.getFunctions().createATRowFilter("operation_quality_part");
			filter1.forColumnNameEqualTo("factory_brand_key" ,factoryBrandKeyL );
			filter1.forColumnNameEqualTo("quality_part_key", qualityPartKeyL );
			filter1.forColumnNameEqualTo("quality_gate_key", qualityGateKeyL );
		 
			
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