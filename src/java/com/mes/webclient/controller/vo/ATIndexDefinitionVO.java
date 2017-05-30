/*
 * @(#) ATIndexDefinition.java 2016年7月18日 上午9:12:46
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;



public class ATIndexDefinitionVO extends BaseVO
{
	private String name;
	private String description;
	private boolean unique;
	private String columnName;
	private String originalName;

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public boolean isUnique()
	{
		return unique;
	}
	public void setUnique(boolean unique)
	{
		this.unique = unique;
	}
	public String getColumnName()
	{
		return columnName;
	}
	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}
	public String getOriginalName()
	{
		return originalName;
	}
	public void setOriginalName(String originalName)
	{
		this.originalName = originalName;
	}
	
	
	
}
