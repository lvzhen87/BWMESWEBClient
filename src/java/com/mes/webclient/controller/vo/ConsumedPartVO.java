/*
 * @(#) ConsumedPartVO.java 2016年8月2日 下午3:21:27
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.math.BigDecimal;

public class ConsumedPartVO extends BaseVO
{
	private String partNumber;
	private String partRevision;
	private String partBatch;
	private String partSerial;
	private String partQuantity;
	
	private String productionLineName;
	private String workCenterName;
	private String operationName;
	private String routeName;
	private BigDecimal precisePartQuantity;
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
	public String getPartBatch()
	{
		return partBatch;
	}
	public void setPartBatch(String partBatch)
	{
		this.partBatch = partBatch;
	}
	public String getPartSerial()
	{
		return partSerial;
	}
	public void setPartSerial(String partSerial)
	{
		this.partSerial = partSerial;
	}
	public String getPartQuantity()
	{
		return partQuantity;
	}
	public void setPartQuantity(String partQuantity)
	{
		this.partQuantity = partQuantity;
	}
	public String getProductionLineName()
	{
		return productionLineName;
	}
	public void setProductionLineName(String productionLineName)
	{
		this.productionLineName = productionLineName;
	}
	public String getWorkCenterName()
	{
		return workCenterName;
	}
	public void setWorkCenterName(String workCenterName)
	{
		this.workCenterName = workCenterName;
	}
	public String getOperationName()
	{
		return operationName;
	}
	public void setOperationName(String operationName)
	{
		this.operationName = operationName;
	}
	public String getRouteName()
	{
		return routeName;
	}
	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}
	public BigDecimal getPrecisePartQuantity()
	{
		return precisePartQuantity;
	}
	public void setPrecisePartQuantity(BigDecimal precisePartQuantity)
	{
		this.precisePartQuantity = precisePartQuantity;
	}

	
	
}
