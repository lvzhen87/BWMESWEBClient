
package com.mes.webclient.controller.vo;

import java.util.List;

import com.mes.compatibility.client.User;


public class UserGroupVO extends BaseVO
{
	private String name;
	private String description;
	private String category;
	private String[] userKeys;
	private List<User> users; 
	private boolean defaultUserGroup;
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
	public String[] getUserKeys()
	{
		return userKeys;
	}
	public void setUserKeys(String[] userKeys)
	{
		this.userKeys = userKeys;
	}
	public List<User> getUsers()
	{
		return users;
	}
	public void setUsers(List<User> users)
	{
		this.users = users;
	}
	public boolean isDefaultUserGroup()
	{
		return defaultUserGroup;
	}
	public void setDefaultUserGroup(boolean defaultUserGroup)
	{
		this.defaultUserGroup = defaultUserGroup;
	} 
	
	
}
