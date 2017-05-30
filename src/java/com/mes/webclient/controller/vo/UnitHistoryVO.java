/*
 * @(#) UnitHistoryVO.java 2016年8月31日 上午9:37:41
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class UnitHistoryVO extends BaseVO
{
	private String operationName;
	private String routeStepName;
	private String workCenterName;
	private String startedTime;
	private String startedUserName;
	private String completedTime;
	private String completedUserName;
	public String getOperationName()
	{
		return operationName;
	}
	public void setOperationName(String operationName)
	{
		this.operationName = operationName;
	}
	public String getRouteStepName()
	{
		return routeStepName;
	}
	public void setRouteStepName(String routeStepName)
	{
		this.routeStepName = routeStepName;
	}
	public String getWorkCenterName()
	{
		return workCenterName;
	}
	public void setWorkCenterName(String workCenterName)
	{
		this.workCenterName = workCenterName;
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

	
}
