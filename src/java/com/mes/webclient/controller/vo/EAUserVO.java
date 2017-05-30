package com.mes.webclient.controller.vo;

public class EAUserVO extends BaseVO{
	String userName;
	String password;
	public String getUserName()
	{
		return userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}
