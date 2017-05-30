/*
 * @(#) AreaVO.java 2016年7月5日 下午2:19:10
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.util.List;

import com.mes.compatibility.client.ProductionLine;

public class AreaVO extends BaseVO
{
	//车间名称
	private String areaName;
	
	//车间编号
	private String areaNumber;
	
	//车间分类
	private String areaCategory;
	
	private String[] productionLineKeys;
	
	private List<ProductionLine> productionLines;
	
	private List<ProductionLineVO> productionLineVOs;

	public String getAreaName()
	{
		return areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	public String getAreaNumber()
	{
		return areaNumber;
	}

	public void setAreaNumber(String areaNumber)
	{
		this.areaNumber = areaNumber;
	}

	public String getAreaCategory()
	{
		return areaCategory;
	}

	public void setAreaCategory(String areaCategory)
	{
		this.areaCategory = areaCategory;
	}

	public String[] getProductionLineKeys()
	{
		return productionLineKeys;
	}

	public void setProductionLineKeys(String[] productionLineKeys)
	{
		this.productionLineKeys = productionLineKeys;
	}

	public List<ProductionLine> getProductionLines()
	{
		return productionLines;
	}

	public void setProductionLines(List<ProductionLine> productionLines)
	{
		this.productionLines = productionLines;
	}

	public List<ProductionLineVO> getProductionLineVOs() {
		return productionLineVOs;
	}

	public void setProductionLineVOs(List<ProductionLineVO> productionLineVOs) {
		this.productionLineVOs = productionLineVOs;
	}
	
	
	
	
}
