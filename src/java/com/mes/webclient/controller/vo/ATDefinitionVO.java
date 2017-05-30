/*
 * @(#) AtdefinitionVO.java 2016年7月12日 上午9:19:39
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.util.Vector;

import com.mes.compatibility.client.ATColumnDefinition;
import com.mes.compatibility.client.ATIndexDefinition;


public class ATDefinitionVO extends BaseVO
{
	private String name;
	private short growType;
	private String growTypeString;
	private short dataManagementType;
	private String dataManageMentTypeString;
	private String category;
	private boolean autoNameCreation;
	private String[] atColumnDefinitionkeys;
	private String[] atIndexDefinitonKeys;
	private Vector<ATColumnDefinition> atColumnDefinitions;
	private Vector<ATIndexDefinition> atIndexDefinitions;
	private int contentNumber;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public short getGrowType()
	{
		return growType;
	}
	public void setGrowType(short growType)
	{
		this.growType = growType;
	}
	

	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public boolean isAutoNameCreation()
	{
		return autoNameCreation;
	}
	public void setAutoNameCreation(boolean autoNameCreation)
	{
		this.autoNameCreation = autoNameCreation;
	}
	public Vector<ATColumnDefinition> getAtColumnDefinitions()
	{
		return atColumnDefinitions;
	}
	public void setAtColumnDefinitions(Vector<ATColumnDefinition> atColumnDefinitions)
	{
		this.atColumnDefinitions = atColumnDefinitions;
	}
	public String[] getAtColumnDefinitionkeys()
	{
		return atColumnDefinitionkeys;
	}
	public void setAtColumnDefinitionkeys(String[] atColumnDefinitionkeys)
	{
		this.atColumnDefinitionkeys = atColumnDefinitionkeys;
	}
	public String[] getAtIndexDefinitonKeys()
	{
		return atIndexDefinitonKeys;
	}
	public void setAtIndexDefinitonKeys(String[] atIndexDefinitonKeys)
	{
		this.atIndexDefinitonKeys = atIndexDefinitonKeys;
	}
	public Vector<ATIndexDefinition> getAtIndexDefinitions()
	{
		return atIndexDefinitions;
	}
	public void setAtIndexDefinitions(Vector<ATIndexDefinition> atIndexDefinitions)
	{
		this.atIndexDefinitions = atIndexDefinitions;
	}
	public short getDataManagementType()
	{
		return dataManagementType;
	}
	public void setDataManagementType(short dataManagementType)
	{
		this.dataManagementType = dataManagementType;
	}
	public String getDataManageMentTypeString()
	{
		return dataManageMentTypeString;
	}
	public void setDataManageMentTypeString(String dataManageMentTypeString)
	{
		this.dataManageMentTypeString = dataManageMentTypeString;
	}
	public String getGrowTypeString()
	{
		return growTypeString;
	}
	public void setGrowTypeString(String growTypeString)
	{
		this.growTypeString = growTypeString;
	}
	public int getContentNumber()
	{
		return contentNumber;
	}
	public void setContentNumber(int contentNumber)
	{
		this.contentNumber = contentNumber;
	}
	

}
