/*
 * @(#) EventControlVO.java 2016年9月1日 上午11:13:35
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class EventControlVO
{
	private String className;
	private String controlType;
	public String getClassName()
	{
		return className;
	}
	public void setClassName(String className)
	{
		this.className = className;
	}
	public String getControlType()
	{
		return controlType;
	}
	public void setControlType(String controlType)
	{
		this.controlType = controlType;
	}
	
	
}
