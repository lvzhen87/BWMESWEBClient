/*
 * @(#) UserController.java 2016年7月4日 下午3:01:20
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
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

import com.mes.compatibility.client.DeAnzaForm;
import com.mes.compatibility.client.Filter;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UDADefinition;
import com.mes.compatibility.client.UDADefinitionFilter;
import com.mes.compatibility.client.UDADefinitionItem;
import com.mes.compatibility.client.User;
import com.mes.compatibility.client.UserFilter;
import com.mes.compatibility.client.UserGroup;
import com.mes.compatibility.client.UserGroupFilter;
import com.mes.compatibility.manager.FormManager;
import com.mes.shopflow.common.constants.IObjectTypes;
import com.mes.shopflow.common.constants.IUserStatusConstants;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.shopflow.common.constants.filtering.IUserFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IUserGroupFilterAttributes;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.controller.vo.UserVO;
import com.mes.webclient.controller.vo.WebFormVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.DateTimeUtils;
import com.mes.webclient.util.StringUtil;

@Controller("UserCustomerController")
@RequestMapping("userCustomer.sp")
public class UserCustomerController extends BaseController
{
	@Autowired
	IIMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(String userName, Model model)
	{

		if (StringUtil.isNotNull(userName))
		{
			model.addAttribute(
				"userName", userName);
			return "/im/userCustomer/list";
		}
		return "/im/userCustomer/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(UserVO userVO, Model model)
	{
		long key = userVO.getKey();
		List<UDADefinitionItem> items = null;
		UDADefinition udaDefinition = null;
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		List<DeAnzaForm> forms = null;
		try
		{

			UDADefinitionFilter udaDefinitionFilter;
			udaDefinitionFilter = imService.createUDADefinitionFilter();
			Vector<UDADefinition> udaDefinitions = udaDefinitionFilter.exec();
			for (int i = 0; i < udaDefinitions.size(); i++)
			{
				if (udaDefinitions.get(
					i).getKey() == IObjectTypes.TYPE_USER)
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
				User user = imService.getUserByKey(key);
				userVO.setName(user.getName());
				userVO.setDescription(user.getDescription());
				userVO.setFirstName(user.getFirstName());
				userVO.setLastName(user.getLastName());
				userVO.setNote(user.getNote());
				userVO.setPassword(null);
				userVO.setPasswordExpiration(DateTimeUtils.formatDate(
					user.getPasswordExpiration(), IDateFormat.TIME_LONG2));
				userVO.setPasswordModifiable(user.isPasswordModifiable());
				userVO.setStatus(user.getStatus());
				userVO.setUserExpiration(DateTimeUtils.formatDate(
					user.getUserExpiration(), IDateFormat.TIME_LONG2));
				userVO.setEmail(user.getEmail());
				userVO.setKey(key);
				userVO.setUserGroups(user.getUserGroups());
				userVO.setUda1(user.getUDA(1));
				if(StringUtil.isNotNull(user.getUDA(2))){
					userVO.setUda2(user.getUDA(2).split(",")[0]);
				}
				userVO.setUda3(user.getUDA(3));
				userVO.setUda4(user.getUDA(4));
				ArrayList<Object> values = new ArrayList<>();
				for (int i = 0; i < items.size(); i++)
				{
					String itemName = items.get(
						i).getName();
					Object value = user.getUDA(itemName);
					values.add(value);
				}
				userVO.setNamedUDAValue(values);
				FormManager formManager = imService.getFormManager();
				Filter filter = null;
				forms = formManager.getObjects(filter);

				userVO.setFormKey(user.getFormKey());
			}
			catch (MESException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute(
			VIEW_OBJECT, userVO);
		model.addAttribute(
			"formlist", forms);
		model.addAttribute(
			"namedItems", items);
		return "im/userCustomer/addOrEdit";
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
				User user = imService.getUserByKey(key);
				Response response = user.delete(
					null, null);
				if (response.isError())
				{
					throw new Exception("删除用户失败，原因为：" + response.getFirstErrorMessage());
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
	String toList(UserVO userVO)
	{
		JSONArray rt = null;
		try
		{
			String userName = userVO.getName();
			String userDecription = userVO.getDescription();

			UserFilter userFilter = imService.createUserFilter();
			UDADefinitionFilter udaDefinitionFilter;
			udaDefinitionFilter = imService.createUDADefinitionFilter();
			if (StringUtil.isNotNull(userName))
			{
				userFilter.forUserNameContaining(userName);
			}
			if (StringUtil.isNotNull(userDecription))
			{
				userFilter.addSearchBy(
					IUserFilterAttributes.DESCRIPTION, IFilterComparisonOperators.CONTAINING,
					userDecription);
			} 
 			userFilter.forUda0EqualTo("customer");
			
			userFilter.setMaxRows(userVO.getQueryNum());
			Vector<User> users = userFilter.exec();
			Vector<Object> vos = new Vector<Object>();
			for (User user : users)
			{
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
				if(StringUtil.isNotNull(user.getUDA(2))){
					userVO.setUda2(user.getUDA(2).split(",")[0]);
				}
				 
				vos.add(userVO);
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
	String save(UserVO userVO)
	{
		try
		{
			long key = userVO.getKey();
			User user = null;
			if (key < 0)
			{
				user = imService.createUser();

			}
			else
			{
				user = imService.getUserByKey(key);
			}

			DeAnzaForm form = (DeAnzaForm) imService.getFormManager().getObject(
				userVO.getFormKey());

			user.setName(userVO.getName());
			user.setDescription(userVO.getDescription());
			user.setFirstName(userVO.getFirstName());
			user.setLastName(userVO.getLastName());
			user.setNote(userVO.getNote());
			user.setPasswordExpiration(DateTimeUtils.parseDateOfPnut(
					"9999-12-12 12:12", IDateFormat.TIME_LONG2));
			user.setPasswordModifiable(true);
		 
				user.setStatus(IUserStatusConstants.STATUS_DISABLED);
			 
			user.setUserExpiration(DateTimeUtils.parseDateOfPnut(
					"9999-12-12 12:12", IDateFormat.TIME_LONG2));
			user.setEmail(userVO.getEmail());

			// Bug003
			if (StringUtil.isNotNull(userVO.getPassword()))
			{
				user.setPassword(userVO.getPassword());
			}

			UDADefinition udaDefinition = null;

			try
			{

				UDADefinitionFilter udaDefinitionFilter;
				udaDefinitionFilter = imService.createUDADefinitionFilter();
				Vector<UDADefinition> udaDefinitions = udaDefinitionFilter.exec();
				for (int i = 0; i < udaDefinitions.size(); i++)
				{
					if (udaDefinitions.get(
						i).getKey() == IObjectTypes.TYPE_USER)
					{

						udaDefinition = udaDefinitions.get(i);
						break;
					}
				}
				List<UDADefinitionItem> items = udaDefinition.getNamedItems();
				for (int i = 0; i < items.size(); i++)
				{
					String itemName = items.get(
						i).getName();
					Object value = userVO.getNamedUDAValue().get(
						i);
					int type = items.get(
						i).getType();
					if (type == 6)
					{
						user.setUDA(
							DateTimeUtils.parseDate(
								value.toString(), IDateFormat.TIME_LONG2), itemName);
					}
					else
					{
						user.setUDA(
							value, itemName);
					}
				}
			}
			catch (MESException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			user.setUDA(
				"customer", 0);
			user.setUDA(
				userVO.getUda1(), 1);
			user.setUDA(
				userVO.getUda2(), 2);
			user.setUDA(
				userVO.getUda3(), 3);
			user.setUDA(
				userVO.getUda4(), 4);

			user.setForm(form);

			String[] userGroups = userVO.getUserGroupKeys();
			/*
			UserGroupFilter userGroupFilter = imService.createUserGroupFilter();
			userGroupFilter.addSearchBy(
				IUserGroupFilterAttributes.KEY, IFilterComparisonOperators.IN, userGroups);
			Vector<UserGroup> currentUserGroups = userGroupFilter.exec();
			Vector<UserGroup> originalUserGroups = (Vector<UserGroup>) user.getUserGroups();
			Vector<UserGroup> toRemoveUserGroups = (Vector<UserGroup>) getMissingObject(
				currentUserGroups, originalUserGroups);
			Vector<UserGroup> toAddUserGroups = (Vector<UserGroup>) getMissingObject(
				originalUserGroups, currentUserGroups);

			for (int i = 0; i < toRemoveUserGroups.size(); i++)
			{
				if (user.getKey() > 0)
				{
					user.removeUserGroup(toRemoveUserGroups.get(i));
				}
			}

			for (int i = 0; i < toAddUserGroups.size(); i++)
			{
				user.addUserGroup(toAddUserGroups.get(i));
			}
			if (user.getUserGroups().size() < 1)
			{
				throw new Exception("用户必须属于一个用户组");
			}*/
			Response response = user.save();
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
