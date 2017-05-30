/*
 * @(#) ATEmployeeVo.java 2016年10月25日 上午9:25:35
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class ATEmployeeVO extends BaseVO
{
	private String name;
	private int age;
	private String department;
	private String atDefinitionName;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getDepartment()
	{
		return department;
	}
	public void setDepartment(String department)
	{
		this.department = department;
	}
	public String getAtDefinitionName()
	{
		return atDefinitionName;
	}
	public void setAtDefinitionName(String atDefinitionName)
	{
		this.atDefinitionName = atDefinitionName;
	}
}
