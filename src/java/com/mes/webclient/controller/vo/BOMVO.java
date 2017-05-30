/*
 * @(#) BOMVO.java 2016-7-7 下午3:11:19
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.controller.vo;

public class BOMVO extends BaseVO
{
	private String bomNumber;
	private String bomRevision;
	private String bomName;
	private String uom;
	public String getBomNumber()
	{
		return bomNumber;
	}
	public void setBomNumber(String bomNumber)
	{
		this.bomNumber = bomNumber;
	}
	public String getBomRevision()
	{
		return bomRevision;
	}
	public void setBomRevision(String bomRevision)
	{
		this.bomRevision = bomRevision;
	}
	public String getBomName()
	{
		return bomName;
	}
	public void setBomName(String bomName)
	{
		this.bomName = bomName;
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
