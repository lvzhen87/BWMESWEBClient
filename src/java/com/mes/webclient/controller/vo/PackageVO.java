/*
 * @(#) PackageVO.java 2016年8月8日 上午11:56:20
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class PackageVO extends BaseVO
{
	private String name;
	private int quantity;
	private String currentStatus;
	private String creationTime;
	private String serialNumber;
	private String partNumber;
	private String partRevision;
	private String finishedTime;
	
	
	public String getSerialNumber()
	{
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber)
	{
		this.serialNumber = serialNumber;
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
	public String getFinishedTime()
	{
		return finishedTime;
	}
	public void setFinishedTime(String finishedTime)
	{
		this.finishedTime = finishedTime;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	public String getCreationTime()
	{
		return creationTime;
	}
	public void setCreationTime(String creationTime)
	{
		this.creationTime = creationTime;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
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
