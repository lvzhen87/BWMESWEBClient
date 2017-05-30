/*
 * @(#) CarrierControllerVO.java 2016年7月10日 下午8:55:43
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class CarrierVO extends BaseVO
{
	private String carrierName;
	private String carrierNumber;
	private String carrierCategory;
	private String carrierType;
	public String getCarrierName()
	{
		return carrierName;
	}
	public void setCarrierName(String carrierName)
	{
		this.carrierName = carrierName;
	}
	public String getCarrierNumber()
	{
		return carrierNumber;
	}
	public void setCarrierNumber(String carrierNumber)
	{
		this.carrierNumber = carrierNumber;
	}
	public String getCarrierCategory()
	{
		return carrierCategory;
	}
	public void setCarrierCategory(String carrierCategory)
	{
		this.carrierCategory = carrierCategory;
	}
	public String getCarrierType()
	{
		return carrierType;
	}
	public void setCarrierType(String carrierType)
	{
		this.carrierType = carrierType;
	}
	
	
}
