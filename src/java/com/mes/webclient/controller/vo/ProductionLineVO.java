package com.mes.webclient.controller.vo;

import java.util.ArrayList;
import java.util.List;

import com.mes.compatibility.client.WorkCenter;

public class ProductionLineVO extends BaseVO
{
	/**
	 * 生产线名称
	 */
	private String productionLineName;
	
	/**
	 * 生产线编号
	 */
	private String productionLineNumber;
	
	/**
	 * 生产线分类
	 */
	private String productionLineCategory;
	
	private String[] workCenterKeys;
	
	private List<WorkCenter> workCenters = new ArrayList<WorkCenter>();
	
	private List<WorkCenterVO> workCenterVOs = new ArrayList<WorkCenterVO>();
	
	public String getProductionLineName()
	{
		return productionLineName;
	}

	public void setProductionLineName(String productionLineName)
	{
		this.productionLineName = productionLineName;
	}

	public String getProductionLineNumber()
	{
		return productionLineNumber;
	}

	public void setProductionLineNumber(String productionLineNumber)
	{
		this.productionLineNumber = productionLineNumber;
	}

	public String getProductionLineCategory()
	{
		return productionLineCategory;
	}

	public void setProductionLineCategory(String productionLineCategory)
	{
		this.productionLineCategory = productionLineCategory;
	}

	@Override
	public String toString()
	{
		return "ProductionLineVO [productionLineName=" + productionLineName
			+ ", productionLineNumber=" + productionLineNumber + ", productionLineCategory="
			+ productionLineCategory + "]";
	}

	public String[] getWorkCenterKeys()
	{
		return workCenterKeys;
	}

	public void setWorkCenterKeys(String[] workCenterKeys)
	{
		this.workCenterKeys = workCenterKeys;
	}

	public List<WorkCenter> getWorkCenters()
	{
		return workCenters;
	}

	public void setWorkCenters(List<WorkCenter> workCenters)
	{
		this.workCenters = workCenters;
	}

	public List<WorkCenterVO> getWorkCenterVOs() {
		return workCenterVOs;
	}

	public void setWorkCenterVOs(List<WorkCenterVO> workCenterVOs) {
		this.workCenterVOs = workCenterVOs;
	}
	
	
	
}
