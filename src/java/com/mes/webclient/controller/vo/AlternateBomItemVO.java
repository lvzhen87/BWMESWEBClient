/*
 * @(#) AlternateBomItemVO.java 2016年8月3日 下午2:41:02
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.math.BigDecimal;

public class AlternateBomItemVO extends BaseVO
{
	private String alternateBomItemName;
	private long partKey;
	private String partNumber;
	private String partRevision;
	private BigDecimal maxReplacementPercent;
	public String getAlternateBomItemName()
	{
		return alternateBomItemName;
	}
	public void setAlternateBomItemName(String alternateBomItemName)
	{
		this.alternateBomItemName = alternateBomItemName;
	}
	public long getPartKey()
	{
		return partKey;
	}
	public void setPartKey(long partKey)
	{
		this.partKey = partKey;
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
	public BigDecimal getMaxReplacementPercent()
	{
		return maxReplacementPercent;
	}
	public void setMaxReplacementPercent(BigDecimal maxReplacementPercent)
	{
		this.maxReplacementPercent = maxReplacementPercent;
	}
	
	
}
