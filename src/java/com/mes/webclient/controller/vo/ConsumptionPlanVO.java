/*
 * @(#) ConsumptionPlan.java 2016年8月3日 上午10:37:10
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.math.BigDecimal;

public class ConsumptionPlanVO extends BaseVO
{
	private long routeKey;
	private String routeName;
	private long stepKey;
	private String stepName;
	private String consumptionPlanName;
	private BigDecimal lowerAmount;
	private BigDecimal targetAmount;
	private BigDecimal upperAmount;
	private String uom;
	public long getRouteKey()
	{
		return routeKey;
	}
	public void setRouteKey(long routeKey)
	{
		this.routeKey = routeKey;
	}
	public String getRouteName()
	{
		return routeName;
	}
	public void setRouteName(String routeName)
	{
		this.routeName = routeName;
	}
	public long getStepKey()
	{
		return stepKey;
	}
	public void setStepKey(long stepKey)
	{
		this.stepKey = stepKey;
	}
	public String getStepName()
	{
		return stepName;
	}
	public void setStepName(String stepName)
	{
		this.stepName = stepName;
	}
	public String getConsumptionPlanName()
	{
		return consumptionPlanName;
	}
	public void setConsumptionPlanName(String consumptionPlanName)
	{
		this.consumptionPlanName = consumptionPlanName;
	}
	public BigDecimal getLowerAmount()
	{
		return lowerAmount;
	}
	public void setLowerAmount(BigDecimal lowerAmount)
	{
		this.lowerAmount = lowerAmount;
	}
	public BigDecimal getTargetAmount()
	{
		return targetAmount;
	}
	public void setTargetAmount(BigDecimal targetAmount)
	{
		this.targetAmount = targetAmount;
	}
	public BigDecimal getUpperAmount()
	{
		return upperAmount;
	}
	public void setUpperAmount(BigDecimal upperAmount)
	{
		this.upperAmount = upperAmount;
	}
	public String getUom()
	{
		return uom;
	}
	public void setUom(String uom)
	{
		this.uom = uom;
	}
	
}
