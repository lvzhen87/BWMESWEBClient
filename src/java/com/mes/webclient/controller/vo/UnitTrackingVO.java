/*
 * @(#) UnitTrackingVO.java 2016年8月17日 上午10:15:46
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class UnitTrackingVO extends BaseVO
{
	private String serialNumber;
	private String orderNumber;
	private String orderItem;
	private String lotNumber;
	private String partNumber;
	private String partName;
	private String currentStatus;
	private String productionLineNumber;
	private String creationTimeStarted;
	private String creationTimeEnded;
	private String finishedTimeStarted;
	private String finishedTimeEnded;
	private String shippedTimeStarted;
	private String shippedTimeEnded;
	
	private String trxState;
	private String productionLineName;
	private String routeName;
	private String creationTime;
	private String finishedTime;
	private String shippedTime;
	public String getSerialNumber()
	{
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber)
	{
		this.serialNumber = serialNumber;
	}
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
	public String getLotNumber()
	{
		return lotNumber;
	}
	public void setLotNumber(String lotNumber)
	{
		this.lotNumber = lotNumber;
	}
	public String getPartNumber()
	{
		return partNumber;
	}
	public void setPartNumber(String partNumber)
	{
		this.partNumber = partNumber;
	}
	public String getPartName()
	{
		return partName;
	}
	public void setPartName(String partName)
	{
		this.partName = partName;
	}
	public String getCurrentStatus()
	{
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus)
	{
		this.currentStatus = currentStatus;
	}
	public String getProductionLineNumber()
	{
		return productionLineNumber;
	}
	public void setProductionLineNumber(String productionLineNumber)
	{
		this.productionLineNumber = productionLineNumber;
	}
	public String getCreationTimeStarted()
	{
		return creationTimeStarted;
	}
	public void setCreationTimeStarted(String creationTimeStarted)
	{
		this.creationTimeStarted = creationTimeStarted;
	}
	public String getCreationTimeEnded()
	{
		return creationTimeEnded;
	}
	public void setCreationTimeEnded(String creationTimeEnded)
	{
		this.creationTimeEnded = creationTimeEnded;
	}
	public String getFinishedTimeStarted()
	{
		return finishedTimeStarted;
	}
	public void setFinishedTimeStarted(String finishedTimeStarted)
	{
		this.finishedTimeStarted = finishedTimeStarted;
	}
	public String getFinishedTimeEnded()
	{
		return finishedTimeEnded;
	}
	public void setFinishedTimeEnded(String finishedTimeEnded)
	{
		this.finishedTimeEnded = finishedTimeEnded;
	}
	public String getShippedTimeStarted()
	{
		return shippedTimeStarted;
	}
	public void setShippedTimeStarted(String shippedTimeStarted)
	{
		this.shippedTimeStarted = shippedTimeStarted;
	}
	public String getShippedTimeEnded()
	{
		return shippedTimeEnded;
	}
	public void setShippedTimeEnded(String shippedTimeEnded)
	{
		this.shippedTimeEnded = shippedTimeEnded;
	}
	public String getTrxState()
	{
		return trxState;
	}
	public void setTrxState(String trxState)
	{
		this.trxState = trxState;
	}
	public String getProductionLineName()
	{
		return productionLineName;
	}
	public void setProductionLineName(String productionLineName)
	{
		this.productionLineName = productionLineName;
	}
	public String getRouteName()
	{
		return routeName;
	}
	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}
	public String getCreationTime()
	{
		return creationTime;
	}
	public void setCreationTime(String creationTime)
	{
		this.creationTime = creationTime;
	}
	public String getFinishedTime()
	{
		return finishedTime;
	}
	public void setFinishedTime(String finishedTime)
	{
		this.finishedTime = finishedTime;
	}
	public String getShippedTime()
	{
		return shippedTime;
	}
	public void setShippedTime(String shippedTime)
	{
		this.shippedTime = shippedTime;
	}
	
}
