/*
 * @(#) BomItemVO.java 2016年8月1日 下午3:45:46
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.math.BigDecimal;

public class BomItemVO extends BaseVO
{

	private long partKey;
	private String bomItemName;
	private String partNumber;
	private String partRevision;
	private float quantity;
	private String uom;
	public long getPartKey()
	{
		return partKey;
	}
	public void setPartKey(long partKey)
	{
		this.partKey = partKey;
	}
	public String getBomItemName()
	{
		return bomItemName;
	}
	public void setBomItemName(String bomItemName)
	{
		this.bomItemName = bomItemName;
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
	public float getQuantity()
	{
		return quantity;
	}
	public void setQuantity(float quantity)
	{
		this.quantity = quantity;
	}
	public String getUom()
	{
		return uom;
	}
	public void setUom(String uom)
	{
		this.uom = uom;
	}

	
	
	
	
	
	
}
