package com.mes.webclient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.DeAnzaForm;
import com.mes.compatibility.client.Order;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.User;
import com.mes.compatibility.manager.FormManager;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.controller.vo.ATCertificateVO;
import com.mes.webclient.controller.vo.UserVO;
import com.mes.webclient.controller.vo.WebFormVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.ATUtil;
import com.mes.webclient.util.DateTimeUtils;
import com.mes.webclient.util.StringUtil;
 
@Controller("ATCertificateController")
@RequestMapping("certificate.sp")
public class ATCertificateController extends BaseController {

	@Autowired
	IIMService imService;

	protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;  
      
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();  
    }  
	/**
	 * 进入管理页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(String dName, Model model)
	{
		System.out.println("certificate");
		if (StringUtil.isNotNull(dName))
		{
			model.addAttribute(
				"dName", dName);			
			return "/im/certificate/list";
		}
		return "/im/certificate/list";
	}

	/**
	 * 进入单车查询页面
	 */
	@RequestMapping(params = "toMainPageInfo")
	public String toMainPageInfo(String vin, Model model)
	{
		ATCertificateVO atCertificatevo = new ATCertificateVO();
		System.out.println("certificate");
		try{			
			if (StringUtil.isNotNull(vin))
			{
				Order order = 	 imService.getOrderByName(vin);
				String carType = (String)order.getUDA("car_type");
				
				String atDefinitionName = "ATCertificate";
				UTHandler atHandler = imService.getAtHandler(atDefinitionName);
				UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
				filter.forColumnNameEqualTo("vehicleModel", carType);
	 			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
				if(rows!=null&&rows.size()>0){
					UTRow atRow = rows.get(0);
					atCertificatevo = (ATCertificateVO)ATUtil.getATObject(ATCertificateVO.class, atRow); 		
					atCertificatevo.setKey(atRow.getKey());			
				//	atDepartmentVO.setD_id(String.valueOf(atRow.getValue("d_id")));				 			
				}	
	 		}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		model.addAttribute(
			VIEW_OBJECT, atCertificatevo);		 
 		return "/im/certificate/addOrEditInfo";
	}
	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(ATCertificateVO atCertificatevo, Model model)
	{
		long key = atCertificatevo.getKey();
		System.out.println("key====="+key);
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		try
		{

			String atDefinitionName = "ATCertificate";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
			filter.forUTRowKeyEqualTo(key);
 			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
			if(rows!=null&&rows.size()>0){
				UTRow atRow = rows.get(0);
				atCertificatevo = (ATCertificateVO)ATUtil.getATObject(ATCertificateVO.class, atRow); 		
				atCertificatevo.setKey(key);			
			//	atDepartmentVO.setD_id(String.valueOf(atRow.getValue("d_id")));				 			
			}			 
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		model.addAttribute(
			VIEW_OBJECT, atCertificatevo);		 
		return "im/certificate/addOrEdit";
	}
	
	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toCopy")
	public String toCopy(ATCertificateVO atCertificatevo, Model model)
	{
		long key = atCertificatevo.getKey();
		System.out.println("toCopy");
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		try
		{

			String atDefinitionName = "ATCertificate";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
			filter.forUTRowKeyEqualTo(key);
 			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
			if(rows!=null&&rows.size()>0){
				UTRow atRow = rows.get(0);
				atCertificatevo = (ATCertificateVO)ATUtil.getATObject(ATCertificateVO.class, atRow); 		
			//	atDepartmentVO.setD_id(String.valueOf(atRow.getValue("d_id")));				 			
			}			 
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		model.addAttribute(
			VIEW_OBJECT, atCertificatevo);		 
		return "im/certificate/addOrEdit";
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
				String atDefinitionName = "ATCertificate";
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
	String toList(ATCertificateVO atCertificateVO)
	{
		JSONArray rt = null;
		 
		try
		{
		//	String dName = atDepartmentVO.getD_name();
			String carModel    = atCertificateVO.getCarModel();
			String vehicleType = atCertificateVO.getVehicleType();
			String atDefinitionName = "ATCertificate";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			 
			if(carModel!=null&&!"".equals(carModel)){
				filter.forColumnNameEqualTo("carModel",carModel);
			}		

			if(vehicleType!=null&&!"".equals(vehicleType)){
				filter.forColumnNameEqualTo("vehicleType",vehicleType);
			}		
			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
 		    ArrayList<Object> vos = new ArrayList<>();		    
 		     
			for (UTRow atRow : rows)
			{
				ATCertificateVO vo = new ATCertificateVO();
				vo = (ATCertificateVO)ATUtil.getATObject(ATCertificateVO.class, atRow); 	
				vo.setKey(atRow.getKey());
				vos.add(vo);
 			}
			rt = JSONArray.fromObject(vos);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return rt.toString();
	}

	/**
	 * 保存
	 */
	@RequestMapping(params = "save")
	public @ResponseBody
	String save(ATCertificateVO atCertificateVO)
	{
		
		try
		{
			Map map = request.getParameterMap();
						
			String atDefinitionName = "ATCertificate";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			
			long key = atCertificateVO.getKey();
			UTRow atRow;
 			if (key > 0)
			{
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
				atRow = rows.get(0);
			}else{
				atRow = atHandler.createATRow();
			}
 			for(Object obj : map.keySet()){
 				if(obj.equals("save")||obj.equals("key")){
 					continue;
 				}
 				Object value = map.get(obj);
  				atRow.setValue(String.valueOf(obj), ((Object[])value)[0].toString());
 			} 
			Response response = atRow.save(null, null, null);		 
			if (response.isError())
			{
				throw new Exception("保存用户失败，原因为:" + response.getFirstErrorMessage());
			}
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
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
