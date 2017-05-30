/*
 * @(#) LotVO.java 2016年8月9日 上午9:22:17
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.math.BigDecimal;

public class LotVO extends BaseVO
{
	private String name;
	private String orderNumber;
	private String orderItem;
	private String lotType;
	private String routeName;
	private String productionLineName;
	private String partNumber;
	private String partRevision;
	private BigDecimal preciseQuantity;
	private BigDecimal preciseQuantitySerialized;
	private BigDecimal preciseQuantityFinished;
	private BigDecimal preciseQuantityScrapped;
	private BigDecimal preciseQuantityShipped;
	private BigDecimal preciseQuantityClosed;
	private String trxState;
	private String currentStatus;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
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
	public String getLotType()
	{
		return lotType;
	}
	public void setLotType(String lotType)
	{
		this.lotType = lotType;
	}
	public String getRouteName()
	{
		return routeName;
	}
	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}
	public String getProductionLineName()
	{
		return productionLineName;
	}
	public void setProductionLineName(String productionLineName)
	{
		this.productionLineName = productionLineName;
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
	public BigDecimal getPreciseQuantity()
	{
		return preciseQuantity;
	}
	public void setPreciseQuantity(BigDecimal preciseQuantity)
	{
		this.preciseQuantity = preciseQuantity;
	}
	public BigDecimal getPreciseQuantitySerialized()
	{
		return preciseQuantitySerialized;
	}
	public void setPreciseQuantitySerialized(BigDecimal preciseQuantitySerialized)
	{
		this.preciseQuantitySerialized = preciseQuantitySerialized;
	}
	public BigDecimal getPreciseQuantityFinished()
	{
		return preciseQuantityFinished;
	}
	public void setPreciseQuantityFinished(BigDecimal preciseQuantityFinished)
	{
		this.preciseQuantityFinished = preciseQuantityFinished;
	}
	public BigDecimal getPreciseQuantityScrapped()
	{
		return preciseQuantityScrapped;
	}
	public void setPreciseQuantityScrapped(BigDecimal preciseQuantityScrapped)
	{
		this.preciseQuantityScrapped = preciseQuantityScrapped;
	}
	public BigDecimal getPreciseQuantityShipped()
	{
		return preciseQuantityShipped;
	}
	public void setPreciseQuantityShipped(BigDecimal preciseQuantityShipped)
	{
		this.preciseQuantityShipped = preciseQuantityShipped;
	}
	public BigDecimal getPreciseQuantityClosed()
	{
		return preciseQuantityClosed;
	}
	public void setPreciseQuantityClosed(BigDecimal preciseQuantityClosed)
	{
		this.preciseQuantityClosed = preciseQuantityClosed;
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
	
	
	
}
