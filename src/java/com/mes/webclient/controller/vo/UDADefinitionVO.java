/*
 * @(#) UDADefinitionVO.java 2016年11月8日 下午2:34:56
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class UDADefinitionVO extends BaseVO
{
	private short objectType;
	private String name;
	private String itemName;
	private short itemType;
	private String  defaultValue;
	private String itemTyepString;
	private String orignalName;
	private String orignalItemName;
	private String objectTypeString;
	private boolean itemUsed;
	public String getItemName()
	{
		return itemName;
	}
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	public short getItemType()
	{
		return itemType;
	}
	public void setItemType(short itemType)
	{
		this.itemType = itemType;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public short getObjectType()
	{
		return objectType;
	}
	public void setObjectType(short objectType)
	{
		this.objectType = objectType;
	}
	public String getDefaultValue()
	{
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}
	public String getItemTyepString()
	{
		return itemTyepString;
	}
	public void setItemTyepString(String itemTyepString)
	{
		this.itemTyepString = itemTyepString;
	}
	public String getOrignalName()
	{
		return orignalName;
	}
	public void setOrignalName(String orignalName)
	{
		this.orignalName = orignalName;
	}
	public String getOrignalItemName()
	{
		return orignalItemName;
	}
	public void setOrignalItemName(String orignalItemName)
	{
		this.orignalItemName = orignalItemName;
	}
	public String getObjectTypeString()
	{
		return objectTypeString;
	}
	public void setObjectTypeString(String objectTypeString)
	{
		this.objectTypeString = objectTypeString;
	}
	public boolean isItemUsed()
	{
		return itemUsed;
	}
	public void setItemUsed(boolean itemUsed)
	{
		this.itemUsed = itemUsed;
	}



	
	
}
