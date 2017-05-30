/*
 * @(#) UnitVO.java 2016年8月10日 下午10:11:06
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class UnitVO extends BaseVO
{
	private String serialNumber;
	private String lotName;
	private String partNumber;
	private String partRevision;
	private String routeName;
	private String productionLine;
	private String routeStepName;
	private String workCenter;
	private String trxState;
	private String currentStatus;
	private String orderNumber;
	private String orderItem;
	private String bomName;
	
	
	
	public String getOrderNumber()
	{
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}
	public String getOrderItem()
	{
		return orderItem;
	}
	public void setOrderItem(String orderItem)
	{
		this.orderItem = orderItem;
	}
	public String getSerialNumber()
	{
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber)
	{
		this.serialNumber = serialNumber;
	}
	public String getLotName()
	{
		return lotName;
	}
	public void setLotName(String lotName)
	{
		this.lotName = lotName;
	}
	public String getPartNumber()
	{
		return partNumber;
	}
	public void setPartNumber(String partNumber)
	{
		this.partNumber = partNumber;
	}
	public String getPartRevision()
	{
		return partRevision;
	}
	public void setPartRevision(String partRevision)
	{
		this.partRevision = partRevision;
	}
	public String getRouteName()
	{
		return routeName;
	}
	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}
	public String getProductionLine()
	{
		return productionLine;
	}
	public void setProductionLine(String productionLine)
	{
		this.productionLine = productionLine;
	}
	public String getRouteStepName()
	{
		return routeStepName;
	}
	public void setRouteStepName(String routeStepName)
	{
		this.routeStepName = routeStepName;
	}
	public String getWorkCenter()
	{
		return workCenter;
	}
	public void setWorkCenter(String workCenter)
	{
		this.workCenter = workCenter;
	}
	public String getTrxState()
	{
		return trxState;
	}
	public void setTrxState(String trxState)
	{
		this.trxState = trxState;
	}
	public String getCurrentStatus()
	{
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus)
	{
		this.currentStatus = currentStatus;
	}
	public String getBomName()
	{
		return bomName;
	}
	public void setBomName(String bomName)
	{
		this.bomName = bomName;
	}

	
	
}
