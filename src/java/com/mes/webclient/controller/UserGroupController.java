package com.mes.webclient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UDADefinition;
import com.mes.compatibility.client.UDADefinitionFilter;
import com.mes.compatibility.client.UDADefinitionItem;
import com.mes.compatibility.client.User;
import com.mes.compatibility.client.UserFilter;
import com.mes.compatibility.client.UserGroup;
import com.mes.compatibility.client.UserGroupFilter;
import com.mes.shopflow.common.constants.IObjectTypes;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.shopflow.common.constants.filtering.IUserGroupFilterAttributes;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.controller.vo.UserGroupVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.DateTimeUtils;
import com.mes.webclient.util.StringUtil;

@Controller("userGroupController")
@RequestMapping("/usergroup.sp")
public class UserGroupController extends BaseController
{
	@Autowired
	IIMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{

		return "/im/usergroup/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(UserGroupVO userGroupVO, Model model)
	{
		long key = userGroupVO.getKey();
		List<UDADefinitionItem> items = null;
		UDADefinition udaDefinition = null;
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		try
		{

			UDADefinitionFilter udaDefinitionFilter;
			udaDefinition = imService.getUdaDefinitionByType(IObjectTypes.TYPE_USERGROUP);
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
				UserGroup userGroup = imService.getUserGroupByKey(key);
				userGroupVO.setDefaultUserGroup(userGroup.isNotDefaultUserGroup());
				userGroupVO.setName(userGroup.getName());
				userGroupVO.setDescription(userGroup.getDescription());
				userGroupVO.setCategory(userGroup.getCategory());
				userGroupVO.setUsers(userGroup.getUsers());
				userGroupVO.setUda1(userGroup.getUDA(1));
				userGroupVO.setUda2(userGroup.getUDA(2));
				userGroupVO.setUda3(userGroup.getUDA(3));
				userGroupVO.setUda4(userGroup.getUDA(4));
				ArrayList<Object> values = new ArrayList<>();
				for (int i = 0; i < items.size(); i++)
				{
					String itemName = items.get(
						i).getName();
					Object value = userGroup.getUDA(itemName);
					Object defaultValue = items.get(
						i).getDefaultValue();
					values.add(value == null ? defaultValue : value);
				}
				userGroupVO.setNamedUDAValue(values);

			}
			catch (MESException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute(
			VIEW_OBJECT, userGroupVO);
		model.addAttribute(
			"namedItems", items);
		return "im/usergroup/addOrEdit";
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
				UserGroup userGroup = imService.getUserGroupByKey(key);
				Response response = userGroup.delete(
					null, null);
				if (response.isError())
				{
					throw new Exception("删除用户组失败，原因为：" + response.getFirstErrorMessage());
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

	/**
	 * 查询
	 */
	@RequestMapping(params = "toList")
	public @ResponseBody
	String toMainPage(UserGroupVO userGroupVO)
	{
		JSONArray rt = null;
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
			Vector<Object> vos = new Vector<Object>();
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
		return rt.toString();
	}

	@RequestMapping(params = "save")
	public @ResponseBody
	String save(UserGroupVO userGroupVO)
	{
		try
		{
			System.out.println(userGroupVO.getKey());
			long key = userGroupVO.getKey();
			UserGroup userGroup = null;
			if (key < 0)
			{
				userGroup = imService.createUserGroup();
			}
			else
			{
				userGroup = imService.getUserGroupByKey(key);
			}

			userGroup.setName(userGroupVO.getName());
			userGroup.setDescription(userGroupVO.getDescription());
			userGroup.setCategory(userGroupVO.getCategory());
			userGroup.setUDA(
				userGroupVO.getUda1(), 1);
			userGroup.setUDA(
				userGroupVO.getUda2(), 2);
			userGroup.setUDA(
				userGroupVO.getUda3(), 3);
			userGroup.setUDA(
				userGroupVO.getUda4(), 4);

			UDADefinition udaDefinition = null;

			try
			{

				udaDefinition = imService.getUdaDefinitionByType(IObjectTypes.TYPE_USERGROUP);
				List<UDADefinitionItem> items = udaDefinition.getNamedItems();
				for (int i = 0; i < items.size(); i++)
				{
					String itemName = items.get(
						i).getName();
					Object value = userGroupVO.getNamedUDAValue().get(
						i);
					int type = items.get(
						i).getType();
					if (type == 6)
					{
						userGroup.setUDA(
							DateTimeUtils.parseDate(
								value.toString(), IDateFormat.TIME_LONG2), itemName);
					}
					else
					{
						userGroup.setUDA(
							value, itemName);
					}
				}
			}
			catch (MESException e)
			{
				logger.error(e);
				return ajaxDoneError(e.getMessage());
			}

			String[] users = userGroupVO.getUserKeys();
			UserFilter userFilter = imService.createUserFilter();
			userFilter.addSearchBy(
				IUserGroupFilterAttributes.KEY, IFilterComparisonOperators.IN, users);
			Vector<User> currentUsers = userFilter.exec();
			Vector<User> originalUsers = (Vector<User>) userGroup.getUsers();
			Vector<User> toRemoveUsers = (Vector<User>) getMissingObject(
				currentUsers, originalUsers);
			Vector<User> toAddUsers = (Vector<User>) getMissingObject(
				originalUsers, currentUsers);

			for (int i = 0; i < toRemoveUsers.size(); i++)
			{
				if (userGroup.getKey() > 0)
				{
					userGroup.removeUser(toRemoveUsers.get(i));
				}
			}

			for (int i = 0; i < toAddUsers.size(); i++)
			{
				userGroup.addUser(toAddUsers.get(i));
			}
			Response response = userGroup.save(
				null, null);
			if (response.isError())
			{
				throw new Exception("保存用户组失败，原因为:" + response.getFirstErrorMessage());
			}
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
	}

	/* 删除 */
	@RequestMapping(params = "getUserGroupByKey")
	public @ResponseBody
	String getUserGroupByKey(@RequestParam("key") long key)
	{
		try
		{
			if (key > 0)
			{
				UserGroup userGroup = imService.getUserGroupByKey(key);
				if (userGroup != null)
				{
					UserGroupVO userGroupVO = new UserGroupVO();
					userGroupVO.setKey(key);
					userGroupVO.setName(userGroup.getName());
					userGroupVO.setDescription(userGroup.getDescription());
					userGroupVO.setCategory(userGroup.getCategory());
					JSONObject obj = JSONObject.fromObject(userGroupVO);
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
