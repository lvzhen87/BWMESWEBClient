/*
 * @(#) OperationVO.java 2016年7月5日 下午2:58:11
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class OperationVO extends BaseVO
{
	//工序名称
	private String operationName;
	
	//工序编号
	private String operationNumber;
	
	//工序分类
	private String operationCategory;
	
	//返工次数
	private int operationCycleDuration;
	
	private long imageKey;
	private String imageName;
	private String imagePath;

	public long getImageKey()
	{
		return imageKey;
	}

	public void setImageKey(long imageKey)
	{
		this.imageKey = imageKey;
	}

	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public String getOperationName()
	{
		return operationName;
	}

	public void setOperationName(String operationName)
	{
		this.operationName = operationName;
	}

	public String getOperationNumber()
	{
		return operationNumber;
	}

	public void setOperationNumber(String operationNumber)
	{
		this.operationNumber = operationNumber;
	}

	public String getOperationCategory()
	{
		return operationCategory;
	}

	public void setOperationCategory(String operationCategory)
	{
		this.operationCategory = operationCategory;
	}

	public int getOperationCycleDuration()
	{
		return operationCycleDuration;
	}

	public void setOperationCycleDuration(int operationCycleDuration)
	{
		this.operationCycleDuration = operationCycleDuration;
	}
	
	
	
}
