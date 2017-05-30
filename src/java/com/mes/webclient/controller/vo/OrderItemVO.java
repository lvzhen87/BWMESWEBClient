/*
 * @(#) OrderItemVO.java 2016年8月5日 下午1:21:57
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import com.mes.compatibility.ui.Time;

public class OrderItemVO extends BaseVO
{
	private String orderItem;
	private long partKey;
	private String partNumber;
	private String partRevision;
	private long plannedRoutekey;
	private String plannedRoute;
	private long plannedProductionLineKey;
	private String plannedProductionLine;
	private String plannedStartTime;
	private String plannedFinishTime;
	private long bomKey;
	private String bomName;
	private String bomRevision;
	private int quantityOrdered;
	private String note;
	private String trxState;
	private String currentStatus;
	public String getOrderItem()
	{
		return orderItem;
	}
	public void setOrderItem(String orderItem)
	{
		this.orderItem = orderItem;
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
	public String getPlannedRoute()
	{
		return plannedRoute;
	}
	public void setPlannedRoute(String plannedRoute)
	{
		this.plannedRoute = plannedRoute;
	}
	public String getPlannedProductionLine()
	{
		return plannedProductionLine;
	}
	public void setPlannedProductionLine(String plannedProductionLine)
	{
		this.plannedProductionLine = plannedProductionLine;
	}
	public String getPlannedStartTime()
	{
		return plannedStartTime;
	}
	public void setPlannedStartTime(String plannedStartTime)
	{
		this.plannedStartTime = plannedStartTime;
	}
	public String getPlannedFinishTime()
	{
		return plannedFinishTime;
	}
	public void setPlannedFinishTime(String plannedFinishTime)
	{
		this.plannedFinishTime = plannedFinishTime;
	}
	public String getBomName()
	{
		return bomName;
	}
	public void setBomName(String bomName)
	{
		this.bomName = bomName;
	}
	public String getBomRevision()
	{
		return bomRevision;
	}
	public void setBomRevision(String bomRevision)
	{
		this.bomRevision = bomRevision;
	}
	public int getQuantityOrdered()
	{
		return quantityOrdered;
	}
	public void setQuantityOrdered(int quantityOrdered)
	{
		this.quantityOrdered = quantityOrdered;
	}
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
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
	public long getPartKey()
	{
		return partKey;
	}
	public void setPartKey(long partKey)
	{
		this.partKey = partKey;
	}
	public long getPlannedRoutekey()
	{
		return plannedRoutekey;
	}
	public void setPlannedRoutekey(long plannedRoutekey)
	{
		this.plannedRoutekey = plannedRoutekey;
	}
	public long getPlannedProductionLineKey()
	{
		return plannedProductionLineKey;
	}
	public void setPlannedProductionLineKey(long plannedProductionLineKey)
	{
		this.plannedProductionLineKey = plannedProductionLineKey;
	}
	public long getBomKey()
	{
		return bomKey;
	}
	public void setBomKey(long bomKey)
	{
		this.bomKey = bomKey;
	}
	
	
}
