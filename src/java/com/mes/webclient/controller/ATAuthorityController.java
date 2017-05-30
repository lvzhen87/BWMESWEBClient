package com.mes.webclient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.mes.compatibility.client.DeAnzaForm;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.User;
import com.mes.compatibility.client.UserGroup;
import com.mes.compatibility.client.UserGroupFilter;
import com.mes.compatibility.manager.FormManager;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.shopflow.common.constants.filtering.IUserGroupFilterAttributes;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.controller.vo.ATAuthorityVO;
import com.mes.webclient.controller.vo.ATDepartmentVO;
import com.mes.webclient.controller.vo.ATMenuVO;
import com.mes.webclient.controller.vo.UserGroupVO;
import com.mes.webclient.controller.vo.UserVO;
import com.mes.webclient.controller.vo.WebFormVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.ATUtil;
import com.mes.webclient.util.DateTimeUtils;
import com.mes.webclient.util.StringUtil;
 
@Controller("ATAuthorityController")
@RequestMapping("authority.sp")
public class ATAuthorityController extends BaseController {

	@Autowired
	IIMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(String dName, Model model)
	{
 		return "/im/authority/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(ATDepartmentVO atDepartmentVO, Model model)
	{
		long key = atDepartmentVO.getKey();
		System.out.println("key====="+key);
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		try
		{

			String atDefinitionName = "ATDepartment";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
			filter.forUTRowKeyEqualTo(key);
			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
			if(rows!=null&&rows.size()>0){
				UTRow atRow = rows.get(0);
				atDepartmentVO.setKey(key);
			
				atDepartmentVO.setD_id(String.valueOf(atRow.getValue("d_id")));
				atDepartmentVO.setD_name(String.valueOf(atRow.getValue("d_name")));
				atDepartmentVO.setDescribe(String.valueOf(atRow.getValue("describe")));				 
			}			 
		}
		catch (MESException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		model.addAttribute(
			VIEW_OBJECT, atDepartmentVO);		 
		return "im/department/addOrEdit";
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
				String atDefinitionName = "ATDepartment";
				UTHandler atHandler = imService.getAtHandler(atDefinitionName);
				UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
				filter.forUTRowKeyEqualTo(key);
				Vector<ATRow> rows = atHandler.getATRowsByFilter(filter, false);
				if(rows!=null&&rows.size()>0){
 					ATRow atRow = rows.get(0);
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
	String toList(@RequestParam("userGroupkey")  String userGroupkey)
	{
		System.out.println("userGroupkey==="+userGroupkey);
		JSONArray rt = null;
		try
		{	 
			Vector<UTRow> menus = this.getAllATMenuVO();
  
			//得到用户组对应的菜单
			UserGroupVO userGroupVO = new UserGroupVO();
			userGroupVO.setKey(Long.valueOf(userGroupkey));
			ATAuthorityVO atAuthorityVO = this.getATAuthority(userGroupVO);
			String menuKey = atAuthorityVO.getMenukey();
			String[]  menu;
			List menulist = new ArrayList();
			if(menuKey!=null){
				menu = menuKey.split(",");
				menulist = new ArrayList(Arrays.asList(menu));
			}
			
			ArrayList<Object> vos = new ArrayList<>();
			for(UTRow atRow:menus){
				ATMenuVO vo = new ATMenuVO();
				vo = (ATMenuVO)ATUtil.getATObject(ATMenuVO.class, atRow);
				vo.setKey(atRow.getKey());
				if(menulist.lastIndexOf(String.valueOf(atRow.getKey()))!=-1){
					vo.setV_check("1");
				}
				vos.add(vo);
			} 
			rt = JSONArray.fromObject(vos);
			/*
			String dName = atDepartmentVO.getD_name();
			String describe = atDepartmentVO.getDescribe();

			String atDefinitionName = "ATDepartment";
			ATHandler atHandler = imService.getAtHandler(atDefinitionName);
			ATRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			
			if(dName!=null&&!"".equals(dName)){
				filter.forColumnNameEqualTo("d_name",dName);
			}
			if(describe!=null&&!"".equals(describe)){
				filter.forColumnNameEqualTo("describe",describe);
			}
			Vector<ATRow> rows = atHandler.getATRowsByFilter(filter, false);
 		    ArrayList<Object> vos = new ArrayList<>();		    
		    filter.setMaxRows(10);
		     
			for (ATRow atRow : rows)
			{
				ATDepartmentVO vo = new ATDepartmentVO();
				vo.setKey(atRow.getKey());
				vo.setD_name(String.valueOf(atRow.getValue("d_name")));
				vo.setDescribe(String.valueOf(atRow.getValue("describe")));				 
				vos.add(vo);
			}*/
			
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return rt.toString();
	}
 

	public Vector<UTRow> getAllATMenuVO(){
		String atDefinitionName = "ATMenu";
		UTHandler atHandler;
		Vector<UTRow> rows = null;
		try {
			atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);			
			 
			rows = atHandler.getATRowsByFilter(filter, false);
			 
		} catch (MESException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return rows;
	}
	public String getAllUserGroup(UserGroupVO userGroupVO)
	{
		JSONArray rt = null;
		Vector<Object> vos = new Vector<Object>();
		if(userGroupVO==null){
			userGroupVO = new UserGroupVO();
		}
		try
		{
			String userGroupName = userGroupVO.getName();
			String userGroupDescription = userGroupVO.getDescription();

			UserGroupFilter userGroupFilter = imService.createUserGroupFilter();
			if (StringUtil.isNotNull(userGroupName))
			{
				userGroupFilter.forNameContaining(userGroupName);
			}
			if (StringUtil.isNotNull(userGroupDescription))
			{
				userGroupFilter.addSearchBy(
					IUserGroupFilterAttributes.DESCRIPTION, IFilterComparisonOperators.CONTAINING,
					userGroupDescription);
			}
			userGroupFilter.setMaxRows(userGroupVO.getQueryNum());
			Vector<UserGroup> userGroups = userGroupFilter.exec();			
			for (UserGroup userGroup : userGroups)
			{
				userGroupVO = new UserGroupVO();
				userGroupVO.setKey(userGroup.getKey());
				userGroupVO.setName(userGroup.getName());
				userGroupVO.setDescription(userGroup.getDescription());
				userGroupVO.setCategory(userGroup.getCategory());
				vos.add(userGroupVO);
			}
			rt = JSONArray.fromObject(vos);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 获得用户组对应的权限信息
	 * @return
	 */
	public ATAuthorityVO getATAuthority(UserGroupVO userGroupVO){
		String atDefinitionName = "ATAuthority";
		UTHandler atHandler;
		ATAuthorityVO vo = new ATAuthorityVO();
		try {
			atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			filter.forColumnNameEqualTo("userGroupkey",userGroupVO.getKey());
			
			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
			if(rows!=null&&rows.size()>0){
				UTRow atRow = rows.get(0);
				vo = (ATAuthorityVO)ATUtil.getATObject(ATAuthorityVO.class, atRow);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return vo;
	}
	/**
	 * 保存
	 */
	@RequestMapping(params = "save")
	public @ResponseBody
	String save(String userGroupkey,String menu)
	{
		try
		{
			String atDefinitionName = "ATAuthority";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			filter.forColumnNameEqualTo("userGroupkey",userGroupkey);
			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
			UTRow atRow;
			if(rows!=null&&rows.size()>0){
				atRow = rows.get(0);
			}else{
				atRow = atHandler.createATRow();
			}			
			long key = atRow.getKey();
 			if (key > 0)
			{
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> atrows = atHandler.getATRowsByFilter(filter, false);
				atRow = atrows.get(0);
			}else{
				atRow = atHandler.createATRow();
			} 
			atRow.setValue("userGroupkey", userGroupkey);
			atRow.setValue("menukey", menu);
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
