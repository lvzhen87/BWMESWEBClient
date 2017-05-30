/*
 * @(#) OrderController.java 2016年8月5日 上午10:50:31
 *
 * Copyright 2016 Catarc, Inc. All rights reserved.
 * Catarc PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.ac.catarc.qj.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ac.catarc.qj.util.FileUtil;
import cn.ac.catarc.qj.util.StringUtil;
import cn.ac.catarc.qj.vo.BomVO;

import com.mes.compatibility.client.AlternateBomItem;
import com.mes.compatibility.client.BillOfMaterials;
import com.mes.compatibility.client.BomFilter;
import com.mes.compatibility.client.BomItem;
import com.mes.compatibility.client.ConsumptionPlan;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Part;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.Route;
import com.mes.compatibility.client.Step;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.webclient.controller.BaseController;
import com.mes.webclient.controller.vo.AlternateBomItemVO;
import com.mes.webclient.controller.vo.BOMVO;
import com.mes.webclient.controller.vo.BomItemVO;
import com.mes.webclient.controller.vo.ConsumptionPlanVO;
import com.mes.webclient.controller.vo.EleBOMVO;
import com.mes.webclient.service.IIMService;

@Controller("qjBomController")
@RequestMapping("/qjbom.sp")
public class BomController extends BaseController {
	@Autowired
	IIMService imService;

	/**
	 * 进入页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage() {
		return "qj/bom/list";
	}

	
	@RequestMapping(params = "toEleBomMainList")
	public String toEleBomList() {
		return "qj/bom/electric_bom_list";
	}
	
	
	
	/**
	 * 查询
	 * 
	 * @param orderVO
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "eleBomList")
	public String eleBomList(EleBOMVO bomVO){
		String parentNum = bomVO.getParentNumber();
		String sunNum = bomVO.getSunNumber();
		JSONArray  rt=null;
		try
		{
		UTRowFilter utRowFilter =imService.createAtRowFilter("electric_bom");
		if(!"".equals(StringUtil.toString(parentNum))){
			utRowFilter.forColumnNameContaining("parent_part_number", parentNum);
		}
		if(!"".equals(StringUtil.toString(sunNum))){
			utRowFilter.forColumnNameContaining("sun_part_number", sunNum);
		}
	    Vector<UTRow> boms = utRowFilter.exec();
	    ArrayList<EleBOMVO> vos = new ArrayList<>();
	    for (int i = 0; i < boms.size(); i++)
		{
	    	EleBOMVO vo = new EleBOMVO();
	    	UTRow row = boms.get(i);
	    	vo.setParentNumber(StringUtil.toString(row.getValue("parent_part_number")) );
	    	vo.setParentDesc(StringUtil.toString(row.getValue("parent_part_desc")) );
	    	vo.setSunNumber(StringUtil.toString(row.getValue("sun_part_number")) );
	    	vo.setSunDesc(StringUtil.toString(row.getValue("sun_part_desc")) );
	    	vo.setStation(StringUtil.toString(row.getValue("station")) );
	    	vo.setPartCount(StringUtil.toString(row.getValue("part_count")) );
			vos.add(vo);
		}
	    rt=JSONArray.fromObject(vos);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return rt.toString();
	}
	
	
	
	/**
	 * 接收计划
	 * 
	 * @return
	 */
	@RequestMapping(params = "downloadBom")
	public String downloadBom(){
		try {
			processBom();
			processBomItem();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "qj/bom/list";
	}
	private void processBom() throws Exception{
		String bomPath="D:\\qj_txt\\bom";
		String bomBackupPath="D:\\qj_txt\\bom_backup";
		String bomFileName="";
		File dir = new File(bomPath);
		File list[] = dir.listFiles();
		String encoding = "GBK";
		for (int i = 0; i < list.length; i++) {
			bomFileName = list[i].getName();
			String suffix = bomFileName.substring(bomFileName.lastIndexOf(".") + 1);
			if ("txt".equals(suffix)) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(list[i]), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String bomInfo[] = lineTxt.split(" ");
					BillOfMaterials bom = imService.getBillOfMaterials(bomInfo[0],bomInfo[1]);
					if(bom!=null){
						Response response = bom.delete(null, null);
						if (response.isError())
						{
							throw new Exception(response.getFirstErrorMessage());
						}
					}
					bom = imService.createBOM();
					
					bom.setBomName(bomInfo[0]);
					bom.setBomRevision(bomInfo[1]);
					bom.setDescription(bomInfo[2]);
					bom.setCategory(bomInfo[3]);
					bom.setUnitOfMeasure(bomInfo[4]);
					
					Response response = bom.save(null, null, null);
					if (response.isError())
					{
						throw new Exception(response.getFirstErrorMessage());
					}
				}
				
				read.close();
				FileUtil.copyFile(bomPath+"\\"+bomFileName, bomBackupPath+"\\"+bomFileName);
				FileUtil.deleteFile(bomPath+"\\"+bomFileName);
			}
		}
	}

	private void processBomItem() throws Exception{
		String itemPath="D:\\qj_txt\\bom_item";
		String itemBackupPath="D:\\qj_txt\\bom_item_backup";
		String itemFileName="";
		File itemDIR = new File(itemPath);
		File itemList[] = itemDIR.listFiles();
		String encoding = "GBK";
		for (int i = 0; i < itemList.length; i++) {
			itemFileName = itemList[i].getName();
			String suffix = itemFileName.substring(itemFileName.lastIndexOf(".") + 1);
			if ("txt".equals(suffix)) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(itemList[i]), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String itemInfo[] = lineTxt.split(" ");
					BillOfMaterials bom = imService.getBillOfMaterials(itemInfo[0],itemInfo[1]);
					
					if(bom!=null){
						String partNumber = itemInfo[2];
						String partRevision = itemInfo[3];
						if (StringUtil.isNull(partNumber) || StringUtil.isNull(partRevision))
						{
							throw new Exception("未选择物料！");
						}
						Part part = imService.getPart(
								partNumber, partRevision);
						if (part==null)
						{
							throw new Exception("物料:"+partNumber+"."+partRevision+"不存在");
						}
						
						BomItem bomItem = bom.addBomItem(part);
						bomItem.setBomItemName(itemInfo[4]);
						bomItem.setPartNumber(itemInfo[2]);
						bomItem.setPartRevision(itemInfo[3]);
						bomItem.setQuantity(new BigDecimal(itemInfo[5]));
						bomItem.setUnitOfMeasure(itemInfo[6]);

						Response response = bom.save(null, null, null);
						if (response.isError())
						{
							throw new Exception(response.getFirstErrorMessage());
						}
						
					}
				}
				
				read.close();
				FileUtil.copyFile(itemPath+"\\"+itemFileName, itemBackupPath+"\\"+itemFileName);
				FileUtil.deleteFile(itemPath+"\\"+itemFileName);
			}
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping(params = "del")
	public @ResponseBody
	String del(@RequestParam("key") long key)
	{
		try
		{
			if (key > 0)
			{
				BillOfMaterials bom = imService.getBillOfMaterialsByKey(key);
				Response response = bom.delete(
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
	public String query(BOMVO bomVO)
	{
		JSONArray rt = null;
		try
		{
			String bomNumber = bomVO.getBomNumber();
			String bomName = bomVO.getBomName();

			BomFilter bomFilter = imService.createBOMFilter();
			if (StringUtil.isNotNull(bomNumber))
			{
				bomFilter.forBomNameContaining(bomNumber);
			}
			if (StringUtil.isNotNull(bomName))
			{
				bomFilter.forDescriptionContaining(bomName);
			}
			bomFilter.setMaxRows(bomVO.getQueryNum());
			Vector<BillOfMaterials> boms = bomFilter.exec();
			Vector<Object> vos = new Vector<Object>();
			for(BillOfMaterials billOfMaterials : boms)
			{
				BOMVO vo = new BOMVO();
				vo.setKey(billOfMaterials.getKey());
				vo.setBomNumber(billOfMaterials.getBomName());;
				vo.setBomName(billOfMaterials.getDescription());
				vo.setCategory(billOfMaterials.getCategory());
				vo.setBomRevision(billOfMaterials.getBomRevision());
				vo.setUom(billOfMaterials.getUnitOfMeasure());
				vos.add(vo);
			}
			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(BOMVO bomVO, Model model)
	{
		long key = bomVO.getKey();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			try
			{
				BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(key);
				bomVO.setBomName(billOfMaterials.getDescription());
				bomVO.setBomNumber(billOfMaterials.getBomName());
				bomVO.setBomRevision(billOfMaterials.getBomRevision());
				bomVO.setCategory(billOfMaterials.getCategory());
				bomVO.setUom(billOfMaterials.getUnitOfMeasure());				
				bomVO.setKey(key);
				
				bomVO.setUda0(billOfMaterials.getUDA(0));
				bomVO.setUda1(billOfMaterials.getUDA(1));
				bomVO.setUda2(billOfMaterials.getUDA(2));
				bomVO.setUda3(billOfMaterials.getUDA(3));
				bomVO.setUda4(billOfMaterials.getUDA(4));
			}
			catch (MESException e)
			{
				e.printStackTrace();
			}
		}
		model.addAttribute(
			VIEW_OBJECT, bomVO);
		return "im/bom/addOrEdit";
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params = "save")
	public @ResponseBody String save(BOMVO bomVO)
	{
		try
		{
			long key = bomVO.getKey();
			BillOfMaterials billOfMaterials = null;
			if (key < 0)
			{
				billOfMaterials = imService.createBOM();
			}
			else
			{
				billOfMaterials = imService.getBillOfMaterialsByKey(key);
			}
			billOfMaterials.setBomName(bomVO.getBomNumber());
			billOfMaterials.setDescription(bomVO.getBomName());
			billOfMaterials.setBomRevision(bomVO.getBomRevision());
			billOfMaterials.setCategory(bomVO.getCategory());
			billOfMaterials.setUnitOfMeasure(bomVO.getUom());
			
			billOfMaterials.setUDA(bomVO.getUda0(), 0);
			billOfMaterials.setUDA(bomVO.getUda1(), 1);
			billOfMaterials.setUDA(bomVO.getUda2(), 2);
			billOfMaterials.setUDA(bomVO.getUda3(), 3);
			billOfMaterials.setUDA(bomVO.getUda4(), 4);
			
			Response response = billOfMaterials.save(
				null, null, null);
			if (response.isError())
			{
				throw new Exception(response.getFirstErrorMessage());
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
	 * 显示BOMItem
	 */
	@ResponseBody
	@RequestMapping(params = "loadBomItem")
	public String loadBomItem(@RequestParam("bomKey") long bomKey, BomItemVO bomItemVO)
	{
		JSONArray rt = null;
		try
		{
			BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
			if(billOfMaterials == null)
			{
				return null;
			}
			billOfMaterials.refresh();
			Vector<BomItem> bomItems = billOfMaterials.getBomItems();
			Vector<Object> vos = new Vector<Object>();
			for(BomItem bomItem : bomItems)
			{
				BomItemVO vo = new BomItemVO();
				vo.setKey(bomItem.getKey());
				vo.setBomItemName(bomItem.getBomItemName());
				vo.setPartNumber(bomItem.getPartNumber());
				vo.setPartRevision(bomItem.getPartRevision());
				BigDecimal quantity = new BigDecimal(Float.toString(bomItem.getQuantity()));
				quantity.setScale(5, BigDecimal.ROUND_HALF_UP);
				vo.setUom(bomItem.getUnitOfMeasure());
				vos.add(vo);
			}
			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 添加或编辑BOMItem
	 */
	@RequestMapping(params = "toAddOrEditBomItem")
	public String toAddOrEditBomItem(@RequestParam("bomKey") long bomKey, BomItemVO bomItemVO, Model model)
	{
		long key = bomItemVO.getKey();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		try
		{
			if (key > 0)
			{
				BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
				BomItem bomItem = billOfMaterials.getBomItem(key); 
				
				bomItemVO.setPartNumber(bomItem.getPartNumber());
				bomItemVO.setPartRevision(bomItem.getPartRevision());
				BigDecimal quantity = new BigDecimal(Float.toString(bomItem.getQuantity()));
				quantity.setScale(5, BigDecimal.ROUND_HALF_UP);
				bomItemVO.setUom(bomItem.getUnitOfMeasure());
				bomItemVO.setKey(key);
				
				bomItemVO.setUda0(bomItem.getUDA(0));
				bomItemVO.setUda1(bomItem.getUDA(1));
				bomItemVO.setUda2(bomItem.getUDA(2));
				bomItemVO.setUda3(bomItem.getUDA(3));
				bomItemVO.setUda4(bomItem.getUDA(4));
			}
		}
		catch (MESException e)
		{
			e.printStackTrace();
		}
		model.addAttribute(
			VIEW_OBJECT, bomItemVO);
		model.addAttribute("bomKey", bomKey);
		return "im/bom/addOrEditBomItem";
	}
	
	
	/**
	 * 保存BOMItem
	 */
	@RequestMapping(params = "saveBomItem")
	public @ResponseBody String saveBomItem(@RequestParam("bomKey") long bomKey, BomItemVO bomItemVO)
	{
		try
		{
			long key = bomItemVO.getKey();
			BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
			String partNumber =  bomItemVO.getPartNumber();
			String partRevision =  bomItemVO.getPartRevision();
			if(StringUtil.isNull(partNumber) || StringUtil.isNull(partRevision))
			{
				throw new Exception("未选择物料！");
			}
			BomItem bomItem = null;
			if(key<0)
			{				
				Part part = imService.getPart(partNumber, partRevision);
				bomItem = billOfMaterials.addBomItem(part);
			}
			else
			{
				bomItem = billOfMaterials.getBomItem(key);
			}
			bomItem.setBomItemName(partNumber);
			bomItem.setPartNumber(bomItemVO.getPartNumber());
			bomItem.setPartRevision(partRevision);
			bomItem.setQuantity(bomItemVO.getQuantity());
			bomItem.setUnitOfMeasure(bomItemVO.getUom());
			
			bomItem.setUDA(bomItemVO.getUda0(), 0);
			bomItem.setUDA(bomItemVO.getUda1(), 1);
			bomItem.setUDA(bomItemVO.getUda2(), 2);
			bomItem.setUDA(bomItemVO.getUda3(), 3);
			bomItem.setUDA(bomItemVO.getUda4(), 4);
			
			Response response = billOfMaterials.save(null, null, null);
			if(response.isError())
			{
				throw new Exception(response.getFirstErrorMessage());
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess();
	}
	
	/**
	 * 删除BOMItem
	 */
	@RequestMapping(params = "delBomItem")
	public @ResponseBody
	String delBomItem(@RequestParam("bomKey") long bomKey, @RequestParam("key") long key)
	{
		try
		{
			if (bomKey > 0 && key > 0)
			{
				BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
				BomItem bomItem = billOfMaterials.getBomItem(key);
				billOfMaterials.removeBomItem(bomItem);
				billOfMaterials.save();
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
	 * 显示ConsumptionPlan
	 */
	@ResponseBody
	@RequestMapping(params = "loadConsumptionPlan")
	public String loadConsumptionPlan(@RequestParam("bomKey") long bomKey, @RequestParam("bomItemKey") long bomItemKey, Model model, ConsumptionPlanVO consumptionPlanVO)
	{
		JSONArray rt = null;
		try
		{
			BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
			if(billOfMaterials == null)
			{
				return null;
			}
			billOfMaterials.refresh();
			BomItem bomItem = billOfMaterials.getBomItem(bomItemKey);
			if(bomItem == null)
			{
				return  null;
			}
			model.addAttribute("bomKey",bomKey);
			model.addAttribute("bomItemKey",bomItemKey);
			ConsumptionPlan[] consumptionPlans = bomItem.getConsumptionPlans();
			Vector<Object> vos = new Vector<Object>();
			for(ConsumptionPlan consumptionPlan : consumptionPlans)
			{
				ConsumptionPlanVO vo = new ConsumptionPlanVO();
				vo.setKey(consumptionPlan.getKey());
				vo.setConsumptionPlanName(consumptionPlan.getName());
				vo.setLowerAmount(consumptionPlan.getLowerAmount());
				vo.setTargetAmount(consumptionPlan.getTargetAmount());
				vo.setUpperAmount(consumptionPlan.getUpperAmount());
				vo.setUom(consumptionPlan.getUnitOfMeasure());
				vos.add(vo);
			}
			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 添加或编辑ConsumptionPlan
	 */
	@RequestMapping(params = "toAddOrEditConsumptionPlan")
	public String toAddOrEditConsumptionPlan(@RequestParam("bomKey") long bomKey, @RequestParam("bomItemKey") long bomItemKey, ConsumptionPlanVO consumptionPlanVO, Model model)
	{
		long key = consumptionPlanVO.getKey();
		Vector<Route> routes = new Vector();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		try
		{
			if (key > 0)
			{
				BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
				BomItem bomItem = billOfMaterials.getBomItem(bomItemKey); 
				ConsumptionPlan consumptionPlan = bomItem.getConsumptionPlan(key);
				Step step = consumptionPlan.getStep();
				
//				consumptionPlanVO.setRouteName();
//				consumptionPlanVO.setStepName(step.getName());
				consumptionPlanVO.setKey(key);
				consumptionPlanVO.setConsumptionPlanName(consumptionPlan.getName());
				consumptionPlanVO.setLowerAmount(consumptionPlan.getLowerAmount());
				consumptionPlanVO.setTargetAmount(consumptionPlan.getTargetAmount());
				consumptionPlanVO.setUpperAmount(consumptionPlan.getUpperAmount());
				consumptionPlanVO.setUom(consumptionPlan.getUnitOfMeasure());
			}
			routes = imService.createRouteFilter().exec();
		}
		catch (MESException e)
		{
			e.printStackTrace();
		}
		model.addAttribute(
			VIEW_OBJECT, consumptionPlanVO);
		model.addAttribute("bomKey", bomKey);
		model.addAttribute("bomItemKey", bomItemKey);
		model.addAttribute("routes", routes);
		return "im/bom/addOrEditConsumptionPlan";
	}
	

	/**
	 * 保存ConsumptionPlan
	 */
	@RequestMapping(params = "saveConsumptionPlan")
	public @ResponseBody String saveConsumptionPlan(@RequestParam("bomKey") long bomKey, @RequestParam("bomItemKey") long bomItemKey, ConsumptionPlanVO consumptionPlanVO)
	{
		try
		{
			long key = consumptionPlanVO.getKey();
			BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
			BomItem bomItem = billOfMaterials.getBomItem(bomItemKey);
			ConsumptionPlan consumptionPlan = null;
			if(key<0)
			{				
				long routeKey = consumptionPlanVO.getRouteKey();
				if(routeKey < 0)
				{
					throw new Exception("未选择工艺路径！");
				}
				Route route = imService.getRouteByKey(routeKey);
				long stepKey =  consumptionPlanVO.getStepKey();
				if(stepKey < 0)
				{
					throw new Exception("未选择工序！");
				}
				Step step = route.getStep(stepKey);
				consumptionPlan = bomItem.addConsumptionPlan(step);
			}
			else
			{
				consumptionPlan = bomItem.getConsumptionPlan(key);
			}
			consumptionPlan.setName(consumptionPlanVO.getConsumptionPlanName());
			consumptionPlan.setLowerAmount(consumptionPlanVO.getLowerAmount());
			consumptionPlan.setTargetAmount(consumptionPlanVO.getTargetAmount());
			consumptionPlan.setUpperAmount(consumptionPlanVO.getUpperAmount());
			consumptionPlan.setUnitOfMeasure(consumptionPlanVO.getUom());
			
			Response response = billOfMaterials.save(null, null, null);
			if(response.isError())
			{
				throw new Exception(response.getFirstErrorMessage());
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess();
	}
	
	/**
	 * 删除ConsumptionPlan
	 */
	@RequestMapping(params = "delConsumptionPlan")
	public @ResponseBody
	String delConsumptionPlan(@RequestParam("bomKey") long bomKey,@RequestParam("bomItemKey") long bomItemKey, @RequestParam("key") long key)
	{
		try
		{
			if (bomKey>0 && bomItemKey>0 && key > 0)
			{
				BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
				BomItem bomItem = billOfMaterials.getBomItem(bomItemKey);
				ConsumptionPlan consumptionPlan = bomItem.getConsumptionPlan(key);
				bomItem.removeConsumptionPlan(consumptionPlan);
				billOfMaterials.save();
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
	 * 显示AlternateBomItem
	 */
	@ResponseBody
	@RequestMapping(params = "loadAlternateBomItem")
	public String loadAlternateBomItem(@RequestParam("bomKey") long bomKey, @RequestParam("bomItemKey") long bomItemKey, AlternateBomItemVO alternateBomItemVO, Model model)
	{
		JSONArray rt = null;
		try
		{
			BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
			if(billOfMaterials == null)
			{
				return null;
			}
			billOfMaterials.refresh();
			BomItem bomItem = billOfMaterials.getBomItem(bomItemKey);
			if(bomItem == null)
			{
				return null;
			}
			model.addAttribute("bomKey",bomKey);
			model.addAttribute("bomItemKey",bomItemKey);
			AlternateBomItem[] alertnateBomItems = bomItem.getAlternateBomItems();
			Vector<Object> vos = new Vector<Object>();
			for(AlternateBomItem alternateBomItem : alertnateBomItems)
			{
				AlternateBomItemVO vo = new AlternateBomItemVO();
				vo.setKey(alternateBomItem.getKey());
				vo.setAlternateBomItemName(alternateBomItem.getName());
				vo.setPartNumber(alternateBomItem.getPartNumber());
				vo.setPartRevision(alternateBomItem.getPartRevision());
				vo.setMaxReplacementPercent(alternateBomItem.getMaxReplacementPercent());
				vos.add(vo);
			}
			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 添加或编辑AlternateBomItem
	 */
	@RequestMapping(params = "toAddOrEditAlternateBomItem")
	public String toAddOrEditAlternateBomItem(@RequestParam("bomKey") long bomKey, @RequestParam("bomItemKey") long bomItemKey, AlternateBomItemVO alternateBomItemVO, Model model)
	{
		long key = alternateBomItemVO.getKey();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		try
		{
			if (key > 0)
			{
				BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
				BomItem bomItem = billOfMaterials.getBomItem(bomItemKey); 
				AlternateBomItem alternateBomItem = bomItem.getAlternateBomItem(key);
				alternateBomItemVO.setKey(key);
				alternateBomItemVO.setAlternateBomItemName(alternateBomItem.getName());
				alternateBomItemVO.setPartNumber(alternateBomItem.getPartNumber());
				alternateBomItemVO.setPartRevision(alternateBomItem.getPartRevision());
				alternateBomItemVO.setMaxReplacementPercent(alternateBomItem.getMaxReplacementPercent());
				
			}
		}
		catch (MESException e)
		{
			e.printStackTrace();
		}
		model.addAttribute(
			VIEW_OBJECT, alternateBomItemVO);
		model.addAttribute("bomKey", bomKey);
		model.addAttribute("bomItemKey", bomItemKey);
		return "im/bom/addOrEditAlternateBomItem";
	}
	
	/**
	 * 保存AlternateBomItem
	 */
	@RequestMapping(params = "saveAlternateBomItem")
	public @ResponseBody String saveAlternateBomItem(@RequestParam("bomKey") long bomKey, @RequestParam("bomItemKey") long bomItemKey, AlternateBomItemVO alternateBomItemVO)
	{
		try
		{
			long key = alternateBomItemVO.getKey();
			BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
			BomItem bomItem = billOfMaterials.getBomItem(bomItemKey);
			String partNumber = alternateBomItemVO.getPartNumber();
			String partRevision = alternateBomItemVO.getPartRevision();
			if(StringUtil.isNull(partNumber) || StringUtil.isNull(partRevision))
			{
				throw new Exception("未选择物料！");
			}
			AlternateBomItem alternateBomItem = null;
			if(key<0)
			{				
				
				alternateBomItem = bomItem.addAlternateBomItem(partNumber, partRevision);
			}
			else
			{
				alternateBomItem = bomItem.getAlternateBomItem(key);
			}
			alternateBomItem.setName(alternateBomItemVO.getAlternateBomItemName());
			alternateBomItem.setPartNumber(alternateBomItemVO.getPartNumber());
			alternateBomItem.setPartRevision(alternateBomItemVO.getPartRevision());
			alternateBomItem.setMaxReplacementPercent(alternateBomItemVO.getMaxReplacementPercent());
			
			Response response = billOfMaterials.save(null, null, null);
			if(response.isError())
			{
				throw new Exception(response.getFirstErrorMessage());
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
		return ajaxDoneSuccess();
	}
	
	/**
	 * 删除AlternateBomItem
	 */
	@RequestMapping(params = "delAlternateBomItem")
	public @ResponseBody
	String delAlternateBomItem(@RequestParam("bomKey") long bomKey, @RequestParam("bomItemKey") long bomItemKey, @RequestParam("key") long key)
	{
		try
		{
			if (bomKey>0 && bomItemKey>0 && key>0)
			{
				BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(bomKey);
				BomItem bomItem = billOfMaterials.getBomItem(bomItemKey); 
				AlternateBomItem alternateBomItem = bomItem.getAlternateBomItem(key);
				bomItem.removeAlternateBomItem(alternateBomItem);
				billOfMaterials.save();
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
	 * 根据Key获得BOM
	 * @param key
	 * @return
	 */
	@RequestMapping(params = "getBomByKey")
	public @ResponseBody String getBomByKey(@RequestParam("key") long key)
	{
		try
		{
			if (key > 0)
			{
				BillOfMaterials billOfMaterials = imService.getBillOfMaterialsByKey(key);
				if (billOfMaterials != null)
				{
					BOMVO bomVO = new BOMVO();
					bomVO.setKey(key);
					bomVO.setBomNumber(billOfMaterials.getBomName());
					bomVO.setBomRevision(billOfMaterials.getBomRevision());
					JSONObject obj = JSONObject.fromObject(bomVO);
					logger.info(obj.toString());
					return obj.toString();
				}
				return null;
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
