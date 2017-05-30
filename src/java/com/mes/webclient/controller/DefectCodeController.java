
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
import com.mes.webclient.controller.vo.QualitypartVO;
import com.mes.webclient.service.impl.IMService;

@Controller("DefectCodeController")
@RequestMapping("/DefectCodeController.sp")
public class DefectCodeController extends BaseController
{
	@Autowired
	IMService imService;

	String tableName = "base_defect_code";
	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{
		return "im/defectCode/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(QualitypartVO qualitypartVO, Model model)
	{
		long key = qualitypartVO.getKey();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			try
			{
				UTRow atrow = null ;
				UTHandler athandler  = imService.getFunctions().createATHandler(tableName) ;
				UTRowFilter filter =  imService.getFunctions().createATRowFilter(tableName);
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> currentList = athandler.getATRowsByFilter(filter, false);
				if( currentList.size() == 0){
					return null;
				}
				atrow = currentList.get(0);
		 
				if( atrow == null){
					return null ;
				}
				QualitypartVO vo = new QualitypartVO();
				long key1 = atrow.getKey();
				String level1 = atrow.getValue("level1")!=null? atrow.getValue("level1").toString():null ;
				String level2 = atrow.getValue("level2")!=null? atrow.getValue("level2").toString():null ;
				String level3 = atrow.getValue("level3")!=null? atrow.getValue("level3").toString():null ;
				String level4 = atrow.getValue("level4")!=null? atrow.getValue("level4").toString():null ;
				String level5 = atrow.getValue("level5")!=null? atrow.getValue("level5").toString():null ;
				
				vo.setKey(key1);
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
			VIEW_OBJECT, qualitypartVO);
		}
		return "im/defectCode/addOrEdit";
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
				UTHandler athandler  = imService.getFunctions().createATHandler(tableName) ;
				UTRowFilter filter =  imService.getFunctions().createATRowFilter(tableName);
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
	public String queryList(QualitypartVO qualitypartVO)
	{
		JSONArray rt = null;
		try
		{	
			UTRow atrow = null ;
			String level1P = qualitypartVO.getLevel1();
			String level2P = qualitypartVO.getLevel2();
			String level3P = qualitypartVO.getLevel3();
			String level4P = qualitypartVO.getLevel4();
			String level5P = qualitypartVO.getLevel5();
			UTHandler athandler  = imService.getFunctions().createATHandler(tableName) ;
			UTRowFilter filter =  imService.getFunctions().createATRowFilter(tableName);
			if( level1P != null && !"".equals(level1P)){
				filter.forColumnNameContaining("level1", level1P);
			}
			if( level2P != null && !"".equals(level2P)){
				filter.forColumnNameContaining("level2", level2P);
			}
			if( level3P != null && !"".equals(level3P)){
				filter.forColumnNameContaining("level3", level3P);
			}
			if( level4P != null && !"".equals(level4P)){
				filter.forColumnNameContaining("level4", level4P);
			}
			if( level5P != null && !"".equals(level5P)){
				filter.forColumnNameContaining("level5", level5P);
			}
			
			Vector<UTRow> currentList  = imService.getFunctions().getATDefinition(tableName).getUTRowsByFilter(filter, false);
			Vector<QualitypartVO> qualitypartVOS = new Vector<QualitypartVO>();
			for(UTRow row : currentList)
			{
				QualitypartVO vo = new QualitypartVO();
				long key1 = row.getKey();
				String level1 = row.getValue("level1")!=null? row.getValue("level1").toString():null ;
				String level2 = row.getValue("level2")!=null? row.getValue("level2").toString():null ;
				String level3 = row.getValue("level3")!=null? row.getValue("level3").toString():null ;
				String level4 = row.getValue("level4")!=null? row.getValue("level4").toString():null ;
				String level5 = row.getValue("level5")!=null? row.getValue("level5").toString():null ;
				vo.setKey(key1);
				vo.setLevel1(level1);
				vo.setLevel2(level2);
				vo.setLevel3(level3);
				vo.setLevel4(level4);
				vo.setLevel5(level5);
			 
				qualitypartVOS.add(vo);
			}
			rt = JSONArray.fromObject(qualitypartVOS);
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
	String save(QualitypartVO qualitypartVO)
	{
		try
		{
			long key = qualitypartVO.getKey();
			String level1 = qualitypartVO.getLevel1();
			String level2 = qualitypartVO.getLevel2();
			String level3 = qualitypartVO.getLevel3();
			String level4 = qualitypartVO.getLevel4();
			String level5 = qualitypartVO.getLevel5();
			
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
			atrow.setValue("level1",level1);
			atrow.setValue("level2",level2);
			atrow.setValue("level3",level3);
			atrow.setValue("level4",level4);
			atrow.setValue("level5",level5);
		 
			atrow.setValue("input_user", imService.getFunctions().getCurrentUser().getName());
			atrow.setValue("input_time", imService.getFunctions().getDBTime());
			
			UTRowFilter filter1 =  imService.getFunctions().createATRowFilter(tableName);
			filter1.forColumnNameEqualTo("level1" ,level1 );
			filter1.forColumnNameEqualTo("level2", level2);
			filter1.forColumnNameEqualTo("level3", level3);
			filter1.forColumnNameEqualTo("level4", level4);
			filter1.forColumnNameEqualTo("level5", level5);
		 
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