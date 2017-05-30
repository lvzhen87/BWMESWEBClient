/*
 * @(#) ATColumnDefinionVO.java 2016年7月18日 上午9:08:12
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class ATColumnDefinitionVO extends BaseVO
{
	private String originalName;
	private String name;
	private String columnType;
	private boolean nullable;
	private String textLength="80";
	private String defaultValue;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getColumnType()
	{
		return columnType;
	}
	public void setColumnType(String columnType)
	{
		this.columnType = columnType;
	}
	public boolean isNullable()
	{
		return nullable;
	}
	public void setNullable(boolean nullable)
	{
		this.nullable = nullable;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}
	public String getOriginalName()
	{
		return originalName;
	}
	public void setOriginalName(String originalName)
	{
		this.originalName = originalName;
	}
	public String getTextLength()
	{
		return textLength;
	}
	public void setTextLength(String textLength)
	{
		this.textLength = textLength;
	}
	
}
