/*
 * @(#) ExportVO.java 2016年10月17日 下午3:58:58
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class ExportVO
{
	private boolean site;
	private boolean area;
	private boolean productionLine;
	private boolean workCenter;
	private boolean equipment;
	private boolean carrier;
	private boolean operation;
	private boolean route;
	public boolean isSite()
	{
		return site;
	}
	public void setSite(boolean site)
	{
		this.site = site;
	}
	public boolean isArea()
	{
		return area;
	}
	public void setArea(boolean area)
	{
		this.area = area;
	}
	public boolean isProductionLine()
	{
		return productionLine;
	}
	public void setProductionLine(boolean productionLine)
	{
		this.productionLine = productionLine;
	}
	public boolean isWorkCenter()
	{
		return workCenter;
	}
	public void setWorkCenter(boolean workCenter)
	{
		this.workCenter = workCenter;
	}
	public boolean isEquipment()
	{
		return equipment;
	}
	public void setEquipment(boolean equipment)
	{
		this.equipment = equipment;
	}
	public boolean isCarrier()
	{
		return carrier;
	}
	public void setCarrier(boolean carrier)
	{
		this.carrier = carrier;
	}
	public boolean isOperation()
	{
		return operation;
	}
	public void setOperation(boolean operation)
	{
		this.operation = operation;
	}
	public boolean isRoute()
	{
		return route;
	}
	public void setRoute(boolean route)
	{
		this.route = route;
	}
	
}
