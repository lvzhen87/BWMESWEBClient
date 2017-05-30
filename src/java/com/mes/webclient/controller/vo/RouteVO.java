/*
 * @(#) RouteVO.java 2016年7月12日 上午9:50:29
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.util.List;

import java.util.Vector;

import com.mes.compatibility.client.ProductionLine;

public class RouteVO extends BaseVO
{
	private String routeName;
	private String revision;
	private String routeNumber;
	private String effectStart;
	private String effectEnd; 
	private String routeState;
	private String category;
	private String enforcement;
	private String[] productionLineKeys;
	private List<ProductionLine> productionLines;
	
	public String[] getReasonStrings()
	{
		return reasonStrings;
	}
	public void setReasonStrings(String[] reasonStrings)
	{
		this.reasonStrings = reasonStrings;
	}
	private Vector<String> reasons;
	private String[] reasonStrings;
	
	public String getRouteName()
	{
		return routeName;
	}
	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}
	public String getRouteNumber()
	{
		return routeNumber;
	}
	public String getRevision()
	{
		return revision;
	}
	public void setRevision(String revision)
	{
		this.revision = revision;
	}
	public void setRouteNumber(String routeNumber)
	{
		this.routeNumber = routeNumber;
	}
	public String getEffectStart()
	{
		return effectStart;
	}
	public void setEffectStart(String effectStart)
	{
		this.effectStart = effectStart;
	}
	public String getEffectEnd()
	{
		return effectEnd;
	}
	public void setEffectEnd(String effectEnd)
	{
		this.effectEnd = effectEnd;
	}
	public String getRouteState()
	{
		return routeState;
	}
	public void setRouteState(String routeState)
	{
		this.routeState = routeState;
	}
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category = category;
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
	public String getEnforcement()
	{
		return enforcement;
	}
	public void setEnforcement(String enforcement)
	{
		this.enforcement = enforcement;
	}
	public Vector<String> getReasons()
	{
		return reasons;
	}
	public void setReasons(Vector<String> reasons)
	{
		this.reasons = reasons;
	}
	
	
}
