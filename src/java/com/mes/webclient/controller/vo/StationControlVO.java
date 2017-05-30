/*
 * @(#) StationControlVO.java 2016年8月4日 下午4:31:34
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class StationControlVO extends BaseVO
{
	private String unitSerialNumber;
	private String routeName;
	private String routeStepName;
	private String productionLineName;
	private String queueName;
	private String workCenterName;
	private String stepName;
	private String startedTime;
	private String startedUserName;
	private String completedTime;
	private String completedUserName;

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

	public String getProductionLineName()
	{
		return productionLineName;
	}

	public void setProductionLineName(String productionLineName)
	{
		this.productionLineName = productionLineName;
	}

	public String getStartedTime()
	{
		return startedTime;
	}

	public void setStartedTime(String startedTime)
	{
		this.startedTime = startedTime;
	}

	public String getStartedUserName()
	{
		return startedUserName;
	}

	public void setStartedUserName(String startedUserName)
	{
		this.startedUserName = startedUserName;
	}

	public String getCompletedTime()
	{
		return completedTime;
	}

	public void setCompletedTime(String completedTime)
	{
		this.completedTime = completedTime;
	}

	public String getCompletedUserName()
	{
		return completedUserName;
	}

	public void setCompletedUserName(String completedUserName)
	{
		this.completedUserName = completedUserName;
	}

	public String getUnitSerialNumber()
	{
		return unitSerialNumber;
	}

	public void setUnitSerialNumber(String unitSerialNumber)
	{
		this.unitSerialNumber = unitSerialNumber;
	}

	public String getQueueName()
	{
		return queueName;
	}

	public void setQueueName(String queueName)
	{
		this.queueName = queueName;
	}

	public String getWorkCenterName()
	{
		return workCenterName;
	}

	public void setWorkCenterName(String workCenterName)
	{
		this.workCenterName = workCenterName;
	}

	public String getStepName()
	{
		return stepName;
	}

	public void setStepName(String stepName)
	{
		this.stepName = stepName;
	}
	
}
