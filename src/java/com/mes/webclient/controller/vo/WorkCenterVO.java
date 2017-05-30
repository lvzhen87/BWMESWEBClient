/*
 * @(#) PartCondition.java 2014-1-22 下午12:18:43
 *
 * Copyright 2014 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.controller.vo;

import java.util.List;

import com.mes.compatibility.client.Equipment;

public class WorkCenterVO extends BaseVO
{
	/**
	 * 工作中心名称
	 */
	private String workCenterName;
	
	/**
	 * 工作中心编号
	 */
	private String workCenterNumber;
	
	private String workCenterCategory;
	
	private String[] equipmentKeys;
	
	private List<Equipment> equipments;
	
	private List<EquipmentVO> equipmentVOs;
	
	public String getWorkCenterName()
	{
		return workCenterName;
	}

	public void setWorkCenterName(String workCenterName)
	{
		this.workCenterName = workCenterName;
	}

	public String getWorkCenterNumber()
	{
		return workCenterNumber;
	}

	public void setWorkCenterNumber(String workCenterNumber)
	{
		this.workCenterNumber = workCenterNumber;
	}

	@Override
	public String toString()
	{
		return "WorkCenterVO [workCenterName=" + workCenterName + ", workCenterNumber="
			+ workCenterNumber + "]";
	}

	/**
	 * @return Returns the equipmentKeys.
	 */
	public String[] getEquipmentKeys()
	{
		return equipmentKeys;
	}

	/**
	 * @param equipmentKeys The equipmentKeys to set.
	 */
	public void setEquipmentKeys(String[] equipmentKeys)
	{
		this.equipmentKeys = equipmentKeys;
	}

	/**
	 * @return Returns the equipments.
	 */
	public List<Equipment> getEquipments()
	{
		return equipments;
	}

	/**
	 * @param equipments The equipments to set.
	 */
	public void setEquipments(List<Equipment> equipments)
	{
		this.equipments = equipments;
	}

	public List<EquipmentVO> getEquipmentVOs() {
		return equipmentVOs;
	}

	public void setEquipmentVOs(List<EquipmentVO> equipmentVOs) {
		this.equipmentVOs = equipmentVOs;
	}

	public String getWorkCenterCategory()
	{
		return workCenterCategory;
	}

	public void setWorkCenterCategory(String workCenterCategory)
	{
		this.workCenterCategory = workCenterCategory;
	}

	
}
