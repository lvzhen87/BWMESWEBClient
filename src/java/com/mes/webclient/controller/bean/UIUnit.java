package com.mes.webclient.controller.bean;

import com.mes.compatibility.client.BillOfMaterials;
import com.mes.compatibility.client.Keyed;
import com.mes.compatibility.client.Unit;
import com.mes.compatibility.ui.Time;
import com.mes.webclient.util.UIObject;

public class UIUnit extends UIObject
{

	Unit unit;

	public UIUnit(Unit unit)
	{
		this.unit = unit;
	}
	
	public long getKey()
	{
		return unit.getKey();
	}

	@Override
	protected Keyed getNativeObject()
	{
		return unit;
	}

	public String getRouteStepName()
	{
		return unit.getRouteStepName();
	}

	public String getSerialNumber()
	{
		return unit.getSerialNumber();
	}

	public String getBomName()
	{
		BillOfMaterials bom = unit.getBillOfMaterials();
		if(bom != null)
		{
			return bom.getBomName();
		}
		return null;
	}
	
	public String getLotName()
	{
		return unit.getLotName();
	}

	public String getPartNumber()
	{
		return unit.getPartNumber();
	}

	public String getPartRevision()
	{
		return unit.getPartRevision();
	}

	public String getRouteName()
	{
		return unit.getRouteName();
	}

	public String getProductionLineName()
	{
		return unit.getProductionLineName();
	}

	public String getWorkCenterName()
	{
		return unit.getWorkCenterName();
	}

	public String getTrxState()
	{
		return unit.getTrxState();
	}

	public String getCurrentStatus()
	{
		return unit.getCurrentStatus();
	}
	
	public String getPartDescription()
	{
		return unit.getPart().getDescription();
	}
	
	public Time getShippedTime()
	{
		return unit.getShippedTime();
	}

	public String getOrderNumber()
	{
		return unit.getOrderNumber();
	}
	public String getOrderItem()
	{
			return unit.getOrderItem();
	}

	public Time getCreationTime()
	{
		return unit.getCreationTime();
	}
	public Time getFinishedTime()
	{
		return unit.getFinishedTime();
	}

}
