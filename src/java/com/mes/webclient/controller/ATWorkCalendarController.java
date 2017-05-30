package com.mes.webclient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.ATHandler;
import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.ATRowFilter;
import com.mes.compatibility.client.Area;
import com.mes.compatibility.client.AreaFilter;
import com.mes.compatibility.client.DeAnzaForm;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.ProductionLine;
import com.mes.compatibility.client.ProductionLineFilter;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UDADefinition;
import com.mes.compatibility.client.UDADefinitionFilter;
import com.mes.compatibility.client.UDADefinitionItem;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.User;
import com.mes.compatibility.manager.FormManager;
import com.mes.shopflow.common.constants.IObjectTypes;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.controller.vo.ATWorkCalendarBasicVO;
import com.mes.webclient.controller.vo.ATWorkCalendarRestVO;
import com.mes.webclient.controller.vo.AreaVO;
import com.mes.webclient.controller.vo.ProductionLineVO;
import com.mes.webclient.controller.vo.UserVO;
import com.mes.webclient.controller.vo.WebFormVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.ATUtil;
import com.mes.webclient.util.DateTimeUtils;
import com.mes.webclient.util.StringUtil;
 
@Controller("ATWorkCalendarController")
@RequestMapping("workCalendar.sp")
public class ATWorkCalendarController extends BaseController {

	@Autowired
	IIMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(String dName, Model model)
	{
		System.out.println("a1");
		Vector<AreaVO> areaVOs = null;
		Vector<ProductionLineVO> productionLinevos  = new Vector<>();
		areaVOs = this.getAreaVOs();
		productionLinevos = this.getProductionLineVO();
		if (StringUtil.isNotNull(dName))
		{
			model.addAttribute(
				"dName", dName);
			return "/im/workCalendar/list";
		}
		model.addAttribute(
				"ProductionLinevos", productionLinevos);
		model.addAttribute(
				"AreaVOs", areaVOs);
		return "/im/workCalendar/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(ATWorkCalendarBasicVO atWorkCalendarBasicvo, Model model)
	{
		long key = atWorkCalendarBasicvo.getKey();
		Vector<AreaVO> areaVOs = null;
		ArrayList<ProductionLineVO> productionLinevos  = new ArrayList<>();
		ArrayList<ATWorkCalendarRestVO> atWorkCalendarRestvos = new ArrayList<>();   
		System.out.println("key====="+key);
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		try
		{
			String atDefinitionName = "ATWorkCalendarBasic";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			
			//查询工作日历详细信息表
			String atDefinitionName1 = "ATWorkCalendarRest";
			UTHandler atHandler1 = imService.getAtHandler(atDefinitionName1);
			UTRowFilter filter1 = imService.createAtRowFilter(atDefinitionName1);
			
			filter.forUTRowKeyEqualTo(key);
 			
 			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
			if(rows!=null&&rows.size()>0){
				UTRow atRow = rows.get(0);				
				atWorkCalendarBasicvo = (ATWorkCalendarBasicVO)ATUtil.getATObject(ATWorkCalendarBasicVO.class, atRow); 
				atWorkCalendarBasicvo.setKey(key);
				//基本信息key
				filter1.forColumnNameEqualTo("workCalendarBasicKey", String.valueOf(atRow.getKey()));
				//得到基本信息对应的详细信息
				Vector<UTRow> datarows = atHandler1.getATRowsByFilter(filter1, false);
				for (UTRow tmpatRow : datarows)
				{
					ATWorkCalendarRestVO vo = new ATWorkCalendarRestVO();
					vo = (ATWorkCalendarRestVO)ATUtil.getATObject(ATWorkCalendarRestVO.class, tmpatRow);
					atWorkCalendarRestvos.add(vo);
				}
				productionLinevos = this.getProductionLineByAreas(String.valueOf(atRow.getValue("areaKey")));
			}
 			areaVOs = this.getAreaVOs();
 		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		model.addAttribute(
			VIEW_OBJECT, atWorkCalendarBasicvo);
		model.addAttribute(
				"ProductionLinevos", productionLinevos);
		model.addAttribute(
				"AreaVOs", areaVOs);
		model.addAttribute(
				"Restvos", atWorkCalendarRestvos);
		return "im/workCalendar/addOrEdit";
	}

	public ATWorkCalendarBasicVO getATWorkCalendarBasicByKey(String workCalendarBasicKey){
		String atDefinitionName = "ATWorkCalendarBasic";
		UTHandler atHandler; 
		ATWorkCalendarBasicVO vo = new ATWorkCalendarBasicVO();
		try {
			atHandler = imService.getAtHandler(atDefinitionName);		
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
			filter.forUTRowKeyEqualTo(Long.valueOf(workCalendarBasicKey));
				Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
			if(rows!=null&&rows.size()>0){
				UTRow atRow = rows.get(0);				
				vo = (ATWorkCalendarBasicVO)ATUtil.getATObject(ATWorkCalendarBasicVO.class, atRow);
				vo.setKey(Long.valueOf(workCalendarBasicKey));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	
	public Vector<AreaVO> getAreaVOs(){
		AreaFilter areaFilter;
		Vector<AreaVO> vos = new Vector<AreaVO>();
		try {
			areaFilter = imService.createAreaFilter();				  
			Vector<Area> areas = areaFilter.exec();
			for(Area area : areas)
			{
				AreaVO areaVO = new AreaVO();
				areaVO.setAreaName(area.getDescription());
				areaVO.setAreaNumber(area.getName());
				areaVO.setAreaCategory(area.getCategory());
				areaVO.setKey(area.getKey());
				areaVO.setProductionLines(area.getProductionLines());
				vos.add(areaVO);
			}
		} catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vos;
	}
	public Vector<ProductionLineVO> getProductionLineVO(){
		ProductionLineFilter productionLineFilter;
		Vector<ProductionLineVO> vos = new Vector<ProductionLineVO>();
		try {
			productionLineFilter = imService.createProductionLineFilter();		
			Vector<ProductionLine> productionLines = productionLineFilter.exec();			
			for (ProductionLine productionLine : productionLines)
			{
				ProductionLineVO vo = new ProductionLineVO();
				vo.setKey(productionLine.getKey());
				vo.setProductionLineNumber(productionLine.getName());				
				vo.setProductionLineName(productionLine.getDescription());				 
				vo.setCategory(productionLine.getCategory());
				vos.add(vo);
			}
		} catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vos;
	}
	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "del")
	public @ResponseBody
	String del(@RequestParam("key") long key)
	{
		
		try
		{
			if (key > 0)
			{
				String atDefinitionName = "ATWorkCalendarBasic";
				UTHandler atHandler = imService.getAtHandler(atDefinitionName);
				UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
				if(rows!=null&&rows.size()>0){
 					UTRow atRow = rows.get(0);
					atRow.delete();
					Response response = atRow.save(null, null, null);
					if (response.isError())
					{
						throw new Exception("删除用户失败，原因为：" + response.getFirstErrorMessage());
					}
					this.delRest(String.valueOf(key));
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
			e.printStackTrace();
			return ajaxDoneError(e.getLocalizedMessage());
		}
	}

	@RequestMapping(params = "getUserByName")
	public @ResponseBody
	String getUserByName(String name)
	{
		JSONArray rt = null;
		try
		{
			UserVO userVO = new UserVO();
			User user = imService.GetUserByName(name);
			Vector<Object> vos = new Vector<Object>();
			userVO = new UserVO();
			userVO.setKey(user.getKey());
			userVO.setName(user.getName());
			userVO.setDescription(user.getDescription());
			userVO.setFirstName(user.getFirstName());
			userVO.setLastName(user.getLastName());
			userVO.setNote(user.getNote());
			userVO.setPasswordModifiable(user.isPasswordModifiable());
			userVO.setPasswordExpiration(DateTimeUtils.formatDate(
				user.getPasswordExpiration(), IDateFormat.TIME_LONG));
			userVO.setStatus(user.getStatus());
			userVO.setUserExpiration(DateTimeUtils.formatDate(
				user.getUserExpiration(), IDateFormat.TIME_LONG));
			userVO.setEmail(user.getEmail());
			vos.add(userVO);
			rt = JSONArray.fromObject(vos);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rt.toString();
	}

	/**
	 * 查询
	 */
	@RequestMapping(params = "toList")
	public @ResponseBody
	String toList(ATWorkCalendarBasicVO atWorkCalendarBasicVO)
	{
		JSONArray rt = null;
		System.out.println("list================="+atWorkCalendarBasicVO.getProducedDate());
		try
		{
			String startDate = atWorkCalendarBasicVO.getStartDate();
			String endDate = atWorkCalendarBasicVO.getEndDate();
			Long areaKey = atWorkCalendarBasicVO.getAreaKey1();
			Long productionlineKey = atWorkCalendarBasicVO.getProductionlineKey1();
			
			//查询工作日历基本信息表
			String atDefinitionName = "ATWorkCalendarBasic";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			if(areaKey!=0){
				filter.forColumnNameEqualTo("areaKey", areaKey);
			}
			if(productionlineKey!=0){
				filter.forColumnNameEqualTo("productionlineKey", productionlineKey);
			}
			
			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
 		    ArrayList<Object> atWorkCalendarBasicvos = new ArrayList<>(); 		    
			for (UTRow atRow : rows)
			{
				ATWorkCalendarBasicVO tmp = new ATWorkCalendarBasicVO();
				//车间key
				String areaKeyt = String.valueOf(atRow.getValue("areaKey"));
				AreaVO areaVO = new AreaVO();
				areaVO.setKey(Long.valueOf(areaKeyt));
				areaVO = this.getAreaVO(areaVO).get(0);
				String areaName = areaVO.getAreaName();
				tmp.setV_areaName(areaName);
				
				//生产线key
				String productionlineKeyt =  String.valueOf(atRow.getValue("productionlineKey"));
				ProductionLineVO productionLineVO = new ProductionLineVO();
				productionLineVO.setKey(Long.valueOf(productionlineKeyt));
				productionLineVO = this.getProductionLineVO(productionLineVO);				
				String productionlineName = productionLineVO.getProductionLineName();
				tmp.setV_productionlineName(productionlineName);
				
				String isEffect = String.valueOf(atRow.getValue("isEffect"));				
				tmp.setIsEffect(isEffect);
				
				tmp.setClasses(String.valueOf(atRow.getValue("classes")));
				tmp.setProducedDate(String.valueOf(atRow.getValue("producedDate")));
				tmp.setStartDate(String.valueOf(atRow.getValue("startDate")));
				tmp.setEndDate(String.valueOf(atRow.getValue("endDate")));
				tmp.setKey(atRow.getKey());
				atWorkCalendarBasicvos.add(tmp);				
			}
			rt = JSONArray.fromObject(atWorkCalendarBasicvos);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return rt.toString();
	}
 	@RequestMapping(params = "getProductionLineByArea")
	public @ResponseBody
	String getProductionLineByArea(String v_areaKey){
		JSONArray rt = null;
		ArrayList<ProductionLineVO> vos = getProductionLineByAreas(v_areaKey);		 
		rt = JSONArray.fromObject(vos);
		return rt.toString();
	}
	
 	public ArrayList<ProductionLineVO> getProductionLineByAreas(String v_areaKey){
		 
		System.out.println("v_areaKey=="+v_areaKey);
		AreaVO areaVO = new AreaVO();
		areaVO.setKey(Long.valueOf(v_areaKey));
		areaVO = this.getAreaVO(areaVO).get(0);
		List<ProductionLine> productionLines = areaVO.getProductionLines();
		ArrayList<ProductionLineVO> vos = new ArrayList<>();
		for(ProductionLine tmp:productionLines){
			ProductionLineVO vo = new ProductionLineVO();			
			vo.setProductionLineName(tmp.getDescription());
			vo.setKey(tmp.getKey());
			vos.add(vo);
		}
	 
		return vos;
	}
	public Vector<AreaVO> getAreaVO(AreaVO areaVO)
	{
		long key = areaVO.getKey();
		
		List<UDADefinitionItem> items = null;
		UDADefinition udaDefinition = null;
		Vector<AreaVO> vos = new Vector<AreaVO>();
		try
		{

			UDADefinitionFilter udaDefinitionFilter;
			udaDefinitionFilter = imService.createUDADefinitionFilter();
			Vector<UDADefinition> udaDefinitions = udaDefinitionFilter.exec();
			for (int i = 0; i < udaDefinitions.size(); i++)
			{
				if (udaDefinitions.get(
					i).getKey() == IObjectTypes.TYPE_AREA)
				{

					udaDefinition = udaDefinitions.get(i);
					break;
				}
			}
			items = udaDefinition.getNamedItems();
		}
		catch (MESException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (key > 0)
		{
			try
			{
				Area area = imService.getAreaByKey(key);
				areaVO.setAreaName(area.getDescription());
				areaVO.setAreaNumber(area.getName());
				areaVO.setAreaCategory(area.getCategory());
				areaVO.setKey(key);
				areaVO.setProductionLines(area.getProductionLines());
				
			

				ArrayList<Object> values = new ArrayList<>();
				for (int i = 0; i < items.size(); i++)
				{
					String itemName = items.get(
						i).getName();
					Object value = area.getUDA(itemName);
					values.add(value);
				}
				areaVO.setNamedUDAValue(values);
				vos.add(areaVO);
				
			}
			catch (MESException e)
			{

				e.printStackTrace();
			}
		}else{
			vos = this.getAreaVOs();
		}
	 
		return vos;
	}
	
	public ProductionLineVO getProductionLineVO(ProductionLineVO productionLineVO)
	{
		long key = productionLineVO.getKey();
		List<UDADefinitionItem> items = null;
		UDADefinition udaDefinition = null;
		 
		try
		{

			UDADefinitionFilter udaDefinitionFilter;
			udaDefinitionFilter = imService.createUDADefinitionFilter();
			Vector<UDADefinition> udaDefinitions = udaDefinitionFilter.exec();
			for (int i = 0; i < udaDefinitions.size(); i++)
			{
				if (udaDefinitions.get(
					i).getKey() == IObjectTypes.TYPE_PRODUCTIONLINE)
				{

					udaDefinition = udaDefinitions.get(i);
					break;
				}
			}
			items = udaDefinition.getNamedItems();
		}
		catch (MESException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key > 0)
		{
			try
			{
				ProductionLine productionLine = imService.getProductionLineByKey(key);
				productionLineVO.setProductionLineName(productionLine.getDescription());
				productionLineVO.setProductionLineNumber(productionLine.getName());
				productionLineVO.setProductionLineCategory(productionLine.getCategory());
				productionLineVO.setKey(key);
				productionLineVO.setWorkCenters(productionLine.getWorkCenters());

				productionLineVO.setUda0(productionLine.getUDA(0));
				productionLineVO.setUda1(productionLine.getUDA(1));
				productionLineVO.setUda2(productionLine.getUDA(2));
				productionLineVO.setUda3(productionLine.getUDA(3));
				productionLineVO.setUda4(productionLine.getUDA(4));

				ArrayList<Object> values = new ArrayList<>();
				for (int i = 0; i < items.size(); i++)
				{
					String itemName = items.get(
						i).getName();
					Object value = productionLine.getUDA(itemName);
					values.add(value);
				}
				productionLineVO.setNamedUDAValue(values);

			}
			catch (MESException e)
			{

				e.printStackTrace();
			}
		}		 
		return productionLineVO;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params = "save")
	public @ResponseBody
	String save(ATWorkCalendarBasicVO atWorkCalendarBasicVO)
	{
		try
		{
			String atDefinitionName = "ATWorkCalendarBasic";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			
			
			long key = atWorkCalendarBasicVO.getKey();
			UTRow atRow;
 			if (key > 0)
			{
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
				atRow = rows.get(0);

				if(!delRest(String.valueOf(atRow.getKey()))){
					return ajaxDoneError("失败");
				}				 
			}else{
				atRow = atHandler.createATRow();
			} 
			atRow.setValue("producedDate", atWorkCalendarBasicVO.getProducedDate());
			atRow.setValue("areaKey", atWorkCalendarBasicVO.getAreaKey());
			atRow.setValue("productionlineKey", atWorkCalendarBasicVO.getProductionlineKey());
			atRow.setValue("classes", atWorkCalendarBasicVO.getClasses());
			atRow.setValue("isEffect", atWorkCalendarBasicVO.getIsEffect());
			atRow.setValue("startDate", atWorkCalendarBasicVO.getStartDate());
			atRow.setValue("endDate", atWorkCalendarBasicVO.getEndDate());
			Response response = atRow.save(null, null, null);		 			
			if (response.isError())
			{
				throw new Exception("保存用户失败，原因为:" + response.getFirstErrorMessage());
			}
			
			//-------保存休息时间记录-----------------

			String atDefinitionName1 = "ATWorkCalendarRest";
			UTHandler atHandler1 = imService.getAtHandler(atDefinitionName1);
			Long workCalendarBasicKey = atRow.getKey();
			String restendDate = atWorkCalendarBasicVO.getV_restendDates();
			String reststartDate = atWorkCalendarBasicVO.getV_reststartDates();
			if(StringUtil.isNull(restendDate)||StringUtil.isNull(reststartDate)){
				return ajaxDoneSuccess();
			}
			String[] restendDates = restendDate.split(",");
			String[] reststartDates = reststartDate.split(",");
			UTRow atRowrest;
			for(int i=0;i<reststartDates.length;i++){
				atRowrest = atHandler1.createATRow();
				atRowrest.setValue("workCalendarBasicKey", workCalendarBasicKey);
				atRowrest.setValue("startDate", reststartDates[i]);
				if(StringUtil.isNull(restendDates[i])){
					continue;
				}
				atRowrest.setValue("endDate", restendDates[i]);
				response = atRowrest.save(null, null, null);
				if (response.isError())
				{
					throw new Exception("保存用户失败，原因为:" + response.getFirstErrorMessage());
				}
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
	 * 删除休息时间
	 * @param workCalendarBasicKey
	 */
	public boolean delRest(String workCalendarBasicKey){
		String atDefinitionName = "ATWorkCalendarRest";
		try {
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);		
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			filter.forColumnNameEqualTo("workCalendarBasicKey", Integer.valueOf(workCalendarBasicKey));
			//得到基本信息对应的详细信息,删除
			Vector<UTRow> datarows = atHandler.getATRowsByFilter(filter, false);
			for (UTRow tmpatRow : datarows)
			{
				tmpatRow.delete();
				Response response = tmpatRow.save(null, null, null);
				if (response.isError())
				{
					return false;
				}
			}				
			return true;
		} catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	@RequestMapping(params = "getUserByKey")
	public @ResponseBody
	String getUserByKey(@RequestParam("key") long key)
	{
		try
		{
			if (key > 0)
			{
				User user = imService.getUserByKey(key);
				if (user != null)
				{
					UserVO userVO = new UserVO();
					userVO.setKey(key);
					userVO.setName(user.getName());
					userVO.setDescription(user.getDescription());
					JSONObject obj = JSONObject.fromObject(userVO);
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

	@RequestMapping(params = "getFormByUserName")
	public void getFormByUserName(String name, HttpServletRequest req, HttpServletResponse res)
	{
		JSONObject jsonObj = new JSONObject();

		try
		{
			User user = imService.GetUserByName(name);
			FormManager formManager = imService.getFormManager();
			long key = user.getFormKey();
			DeAnzaForm form = (DeAnzaForm) formManager.getObject(key);
			if (form != null)
			{
				WebFormVO webFormVO = new WebFormVO();
				webFormVO.setText(form.getName());
				webFormVO.setContent(new String(form.getForm()));

				ArrayList<WebFormVO> formlist = new ArrayList<WebFormVO>();
				formlist.add(webFormVO);

				jsonObj.put(
					"code", 1);
				jsonObj.put(
					"formlist", formlist);
			}
			else
			{
				jsonObj.put(
					"code", 0);
			}

			res.setContentType("text/html; charset=UTF-8");
		}
		catch (Exception e)
		{

			jsonObj.put(
				"code", 0);
			e.printStackTrace();
		}

		try
		{
			res.getWriter().write(
				jsonObj.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
