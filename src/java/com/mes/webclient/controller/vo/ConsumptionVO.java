/*
 * @(#) ConsumptionVO.java 2016年8月4日 上午9:17:27
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.math.BigDecimal;

public class ConsumptionVO extends BaseVO
{
	
	private String partNumber;
	private String partRevision;
	private String consumptionType;
	private String PartUOM;
	private String partSerial;
	private String partBatch;
	private String unitSerialNumber;
	private BigDecimal preciseQuantity;
	private String creationTime;
	private String routeStepName;
	public String getPartNumber()
	{
		return partNumber;
	}
	public void setPartNumber(String partNumber)
	{
		this.partNumber = partNumber;
	}
	public String getPartSerial()
	{
		return partSerial;
	}
	public void setPartSerial(String partSerial)
	{
		this.partSerial = partSerial;
	}
	public String getPartBatch()
	{
		return partBatch;
	}
	public void setPartBatch(String partBatch)
	{
		this.partBatch = partBatch;
	}

	public String getPartUOM()
	{
		return PartUOM;
	}
	public void setPartUOM(String partUOM)
	{
		PartUOM = partUOM;
	}
	public String getUnitSerialNumber()
	{
		return unitSerialNumber;
	}
	public void setUnitSerialNumber(String unitSerialNumber)
	{
		this.unitSerialNumber = unitSerialNumber;
	}
	public String getPartRevision()
	{
		return partRevision;
	}
	public void setPartRevision(String partRevision)
	{
		this.partRevision = partRevision;
	}
	public String getConsumptionType()
	{
		return consumptionType;
	}
	public void setConsumptionType(String consumptionType)
	{
		this.consumptionType = consumptionType;
	}
	public BigDecimal getPreciseQuantity()
	{
		return preciseQuantity;
	}
	public void setPreciseQuantity(BigDecimal preciseQuantity)
	{
		this.preciseQuantity = preciseQuantity;
	}
	public String getCreationTime()
	{
		return creationTime;
	}
	public void setCreationTime(String creationTime)
	{
		this.creationTime = creationTime;
	}
	public String getRouteStepName()
	{
		return routeStepName;
	}
	public void setRouteStepName(String routeStepName)
	{
		this.routeStepName = routeStepName;
	}

	
}
