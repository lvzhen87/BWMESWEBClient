/*
 * @(#) StationVO.java 2016年11月7日 上午10:26:42
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.util.ArrayList;
import java.util.List;

import com.mes.compatibility.client.WorkCenter;

public class StationVO extends BaseVO
{

	private String stationNumber;
	private String stationName;
	private String stationCategory;
	private String workCenterNumber;
	private String formName;
	public String getStationNumber()
	{
		return stationNumber;
	}
	public void setStationNumber(String stationNumber)
	{
		this.stationNumber = stationNumber;
	}
	public String getStationName()
	{
		return stationName;
	}
	public void setStationName(String stationName)
	{
		this.stationName = stationName;
	}
	public String getStationCategory()
	{
		return stationCategory;
	}
	public void setStationCategory(String stationCategory)
	{
		this.stationCategory = stationCategory;
	}
	public String getWorkCenterNumber()
	{
		return workCenterNumber;
	}
	public void setWorkCenterNumber(String workCenterNumber)
	{
		this.workCenterNumber = workCenterNumber;
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
