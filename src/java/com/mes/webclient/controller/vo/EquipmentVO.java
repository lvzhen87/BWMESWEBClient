/*
 * @(#) EquipmentVO.java 2016-7-3 下午10:53:19
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.controller.vo;

import java.util.List;

import com.mes.compatibility.client.Equipment;

public class EquipmentVO extends BaseVO
{
	//设备编号
	private String equipmentNumber;
	//设备名称
	private String equipmentName;
	//设备分类
	private String equipmentCategory;
	
	private String[] childEquipmentKeys;
	
	private List<Equipment> childEquipments;
	
	private List<EquipmentVO> childEquipmentVOs;
	
	
	public String getEquipmentNumber()
	{
		return equipmentNumber;
	}
	public String getEquipmentName()
	{
		return equipmentName;
	}
	public void setEquipmentNumber(String equipmentNumber)
	{
		this.equipmentNumber = equipmentNumber;
	}
	public void setEquipmentName(String equipmentName)
	{
		this.equipmentName = equipmentName;
	}
	public String getEquipmentCategory()
	{
		return equipmentCategory;
	}
	public void setEquipmentCategory(String equipmentCategory)
	{
		this.equipmentCategory = equipmentCategory;
	}
	public String[] getChildEquipmentKeys()
	{
		return childEquipmentKeys;
	}
	public void setChildEquipmentKeys(String[] childEquipmentKeys)
	{
		this.childEquipmentKeys = childEquipmentKeys;
	}
	public List<Equipment> getChildEquipments()
	{
		return childEquipments;
	}
	public void setChildEquipments(List<Equipment> childEquipments)
	{
		this.childEquipments = childEquipments;
	}
	
	public List<EquipmentVO> getChildEquipmentVOs() {
		return childEquipmentVOs;
	}
	
	public void setChildEquipmentVOs(List<EquipmentVO> childEquipmentVOs) {
		this.childEquipmentVOs = childEquipmentVOs;
	}
	
	
}
