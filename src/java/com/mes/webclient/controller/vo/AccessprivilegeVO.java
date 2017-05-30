/*
 * @(#) AccessprivilegeVO.java 2016年7月5日 上午10:21:27
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.util.List;

import com.mes.compatibility.client.UserGroup;

public class AccessprivilegeVO extends BaseVO
{
	private String name;
	private String description;
	private String category;
	private String effectivityStart;
	private String effectivityEnd;
	private String[] userGroupKeys;
	private List<UserGroup> userGroups;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public String getEffectivityStart()
	{
		return effectivityStart;
	}
	public void setEffectivityStart(String effectivityStart)
	{
		this.effectivityStart = effectivityStart;
	}
	public String getEffectivityEnd()
	{
		return effectivityEnd;
	}
	public void setEffectivityEnd(String effectivityEnd)
	{
		this.effectivityEnd = effectivityEnd;
	}
	public String[] getUserGroupKeys()
	{
		return userGroupKeys;
	}
	public void setUserGroupKeys(String[] userGroupKeys)
	{
		this.userGroupKeys = userGroupKeys;
	}
	public List<UserGroup> getUserGroups()
	{
		return userGroups;
	}
	public void setUserGroups(List<UserGroup> userGroups)
	{
		this.userGroups = userGroups;
	}

	
	
	
}
