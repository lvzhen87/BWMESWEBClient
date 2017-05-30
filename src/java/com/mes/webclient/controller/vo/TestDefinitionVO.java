/*
 * @(#) TestDefinitionVO.java 2016年7月8日 下午3:06:38
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;



public class TestDefinitionVO extends BaseVO
{
	private String name;
	private String description;
	private String category;
	private int level;
	private String levelString;
	private String defectCodeListName;
	private String repairCodeListName;
	private String testResultCodeListName;
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
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
	}
	public int getLevel()
	{
		return level;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
	public String getDefectCodeListName()
	{
		return defectCodeListName;
	}
	public void setDefectCodeListName(String defectCodeListName)
	{
		this.defectCodeListName = defectCodeListName;
	}
	public String getRepairCodeListName()
	{
		return repairCodeListName;
	}
	public void setRepairCodeListName(String repairCodeListName)
	{
		this.repairCodeListName = repairCodeListName;
	}
	public String getTestResultCodeListName()
	{
		return testResultCodeListName;
	}
	public void setTestResultCodeListName(String testResultCodeListName)
	{
		this.testResultCodeListName = testResultCodeListName;
	}
	public String getLevelString()
	{
		return levelString;
	}
	public void setLevelString(String levelString)
	{
		this.levelString = levelString;
	}

	
	
	
}
