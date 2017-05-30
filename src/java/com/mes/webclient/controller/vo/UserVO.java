
package com.mes.webclient.controller.vo;





import java.util.List;

import com.mes.compatibility.client.UserGroup;


public class UserVO extends BaseVO
{
	private String name;
	private String description;
	private String firstName;
	private String lastName;
	private String note;
	private String password;
	private String passwordExpiration;
	private boolean passwordModifiable;
	private String status;
	private String userExpiration;
	private String email;
	private String[] userGroupKeys;
	private List<UserGroup> userGroups;
	private long formKey=-1;
	private String formName;
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
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPasswordExpiration()
	{
		return passwordExpiration;
	}
	public void setPasswordExpiration(String passwordExpiration)
	{
		this.passwordExpiration = passwordExpiration;
	}
	public boolean isPasswordModifiable()
	{
		return passwordModifiable;
	}
	public void setPasswordModifiable(boolean passwordModifiable)
	{
		this.passwordModifiable = passwordModifiable;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getUserExpiration()
	{
		return userExpiration;
	}
	public void setUserExpiration(String userExpiration)
	{
		this.userExpiration = userExpiration;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
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
	public long getFormKey()
	{
		return formKey;
	}
	public void setFormKey(long formKey)
	{
		this.formKey = formKey;
	}
	public String getFormName()
	{
		return formName;
	}
	public void setFormName(String formName)
	{
		this.formName = formName;
	}
	
	
}
