
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
import com.mes.webclient.controller.vo.FactoryBrandVO;
import com.mes.webclient.service.impl.IMService;

@Controller("FactoryBrandController")
@RequestMapping("/FactoryBrandController.sp")
public class FactoryBrandController extends BaseController
{
	@Autowired
	IMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{
		return "im/factoryBrand/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(FactoryBrandVO factoryBrandVO, Model model)
	{
		long key = factoryBrandVO.getKey();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			try
			{
				UTRow atrow = null ;
				UTHandler athandler  = imService.getFunctions().createATHandler("factory_brand") ;
				UTRowFilter filter =  imService.getFunctions().createATRowFilter("factory_brand");
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> currentList = athandler.getATRowsByFilter(filter, false);
				if( currentList.size() == 0){
					return null;
				}
				atrow = currentList.get(0);
		 
				if( atrow == null){
					return null ;
				}
				FactoryBrandVO vo = new FactoryBrandVO();
				long key1 = atrow.getKey();
				String brandName = atrow.getValue("brand_name")!=null?atrow.getValue("brand_name").toString():null ;
				String factoryName = atrow.getValue("factory_name")!=null?atrow.getValue("factory_name").toString():null ;
				vo.setKey(key1);
				vo.setFactoryName(factoryName);
				vo.setBrandName(brandName);
				model.addAttribute(
						VIEW_OBJECT, vo);
				 
			}
			catch (MESException e)
			{

				e.printStackTrace();
			}
		}else{
		model.addAttribute(
			VIEW_OBJECT, factoryBrandVO);
		}
		return "im/factoryBrand/addOrEdit";
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
				UTHandler athandler  = imService.getFunctions().createATHandler("factory_brand") ;
				UTRowFilter filter =  imService.getFunctions().createATRowFilter("factory_brand");
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
	public String queryList(FactoryBrandVO factoryBrandVO)
	{
		JSONArray rt = null;
		try
		{	
			UTRow atrow = null ;
			String factoryNameP = factoryBrandVO.getFactoryName();
			String brandNameP = factoryBrandVO.getBrandName();
			
		 
			UTHandler athandler  = imService.getFunctions().createATHandler("factory_brand") ;
			UTRowFilter filter =  imService.getFunctions().createATRowFilter("factory_brand");
			if( factoryNameP != null && !"".equals(factoryNameP)){
				filter.forColumnNameContaining("factory_name", factoryNameP);
			}
			if( brandNameP != null && !"".equals(brandNameP)){
				filter.forColumnNameContaining("brand_name", brandNameP);
			}
			
			
			Vector<UTRow> currentList = athandler.getATRowsByFilter(filter, false);
			Vector<FactoryBrandVO> factoryBrandVOs = new Vector<FactoryBrandVO>();
			for(UTRow row : currentList)
			{
				FactoryBrandVO vo = new FactoryBrandVO();
				long key1 = row.getKey();
				String brandName = row.getValue("brand_name")!=null?row.getValue("brand_name").toString():null ;
				String factoryName = row.getValue("factory_name")!=null?row.getValue("factory_name").toString():null ;
				vo.setKey(key1);
				vo.setFactoryName(factoryName);
				vo.setBrandName(brandName);
				factoryBrandVOs.add(vo);
			}
			rt = JSONArray.fromObject(factoryBrandVOs);
			return rt.toString();
		}
		catch (MESException e)
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
	String save(FactoryBrandVO factoryBrandVO)
	{
		try
		{
			long key = factoryBrandVO.getKey();
			String factoryName = factoryBrandVO.getFactoryName();
			String brandName = factoryBrandVO.getBrandName();
			
			UTRow atrow = null;
			UTHandler athandler  = imService.getFunctions().createATHandler("factory_brand") ;
			if (key < 0){
				atrow = athandler.createATRow(); 
			}
			else
			{
				UTRowFilter filter =  imService.getFunctions().createATRowFilter("factory_brand");
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
			atrow.setValue("factory_name" ,factoryName );
			atrow.setValue("brand_name", brandName );
			atrow.setValue("input_user", imService.getFunctions().getCurrentUser().getName());
			atrow.setValue("input_time", imService.getFunctions().getDBTime());
			
			UTRowFilter filter1 =  imService.getFunctions().createATRowFilter("factory_brand");
			filter1.forColumnNameEqualTo("factory_name" ,factoryName );
			filter1.forColumnNameEqualTo("brand_name", brandName);
			
			
			Vector<UTRow> dbList = athandler.getATRowsByFilter(filter1, false);
			if( dbList.size() > 0){
				return ajaxDoneError("保存工厂失败，原因为:当前记录已经存在！" );
			}
			
			
			Response response = atrow.save( imService.getFunctions().getDBTime(), null,null);
			if (response.isError())
			{
				return ajaxDoneError("保存工厂失败，原因为:" + response.getFirstErrorMessage());
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
	 * 删除
	 */
	@RequestMapping(params = "getAreaByKey")
	public @ResponseBody
	String getAreaByKey(@RequestParam("key") long key)
	{
		try
		{
			if (key > 0)
			{

				UTRow atrow = null;
				UTHandler athandler  = imService.getFunctions().createATHandler("factory_brand") ;
				UTRowFilter filter =  imService.getFunctions().createATRowFilter("factory_brand");
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> currentList = athandler.getATRowsByFilter(filter, false);
				if( currentList.size() == 0){
					return null;
				}
				atrow = currentList.get(0);
				 
				if( atrow == null){
					return null ;
				}
				Response res =  atrow.delete( imService.getFunctions().getDBTime(), null,null);
				 if(res.isError()){
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
}