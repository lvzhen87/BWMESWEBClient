/*
 * @(#) TestInstanceVO.java 2016年8月5日 下午5:28:42
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class TestInstanceVO extends BaseVO
{
	private String unitSerialNumber;
	private String testDefinitionName;
	private String testDefinitionDescription;
	private String testStartTime;
	private String testEndTime;
	private boolean testValid;
	private boolean testPassed;
	private String routeName;
	private String routeStepName;
	private String userName;
	
	
	public String getRouteName()
	{
		return routeName;
	}
	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}
	public String getRouteStepName()
	{
		return routeStepName;
	}
	public void setRouteStepName(String routeStepName)
	{
		this.routeStepName = routeStepName;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getTestStartTime()
	{
		return testStartTime;
	}
	public void setTestStartTime(String testStartTime)
	{
		this.testStartTime = testStartTime;
	}
	public String getTestEndTime()
	{
		return testEndTime;
	}
	public void setTestEndTime(String testEndTime)
	{
		this.testEndTime = testEndTime;
	}


	public String getTestDefinitionName()
	{
		return testDefinitionName;
	}
	public void setTestDefinitionName(String testDefinitionName)
	{
		this.testDefinitionName = testDefinitionName;
	}
	public String getUnitSerialNumber()
	{
		return unitSerialNumber;
	}
	public void setUnitSerialNumber(String unitSerialNumber)
	{
		this.unitSerialNumber = unitSerialNumber;
	}
	public boolean isTestValid()
	{
		return testValid;
	}
	public void setTestValid(boolean testValid)
	{
		this.testValid = testValid;
	}
	public boolean isTestPassed()
	{
		return testPassed;
	}
	public void setTestPassed(boolean testPassed)
	{
		this.testPassed = testPassed;
	}
	public String getTestDefinitionDescription()
	{
		return testDefinitionDescription;
	}
	public void setTestDefinitionDescription(String testDefinitionDescription)
	{
		this.testDefinitionDescription = testDefinitionDescription;
	}
	
	
}
