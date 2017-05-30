/*
 * @(#) SiteVO.java 2016年7月5日 下午2:55:32
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.util.List;

import com.mes.compatibility.client.Area;

public class SiteVO extends BaseVO
{
	//工厂名称
	private String siteName;
	
	//工厂编号
	private String siteNumber;
	
	//工厂分类
	private String siteCategory;
	
	private String[] areaKeys;
	
	private List<Area> areas;
	
	private List<AreaVO> areaVOs;

	public String getSiteName()
	{
		return siteName;
	}

	public void setSiteName(String siteName)
	{
		this.siteName = siteName;
	}

	public String getSiteNumber()
	{
		return siteNumber;
	}

	public void setSiteNumber(String siteNumber)
	{
		this.siteNumber = siteNumber;
	}

	public String getSiteCategory()
	{
		return siteCategory;
	}

	public void setSiteCategory(String siteCategory)
	{
		this.siteCategory = siteCategory;
	}

	public String[] getAreaKeys()
	{
		return areaKeys;
	}

	public void setAreaKeys(String[] areaKeys)
	{
		this.areaKeys = areaKeys;
	}

	public List<Area> getAreas()
	{
		return areas;
	}

	public void setAreas(List<Area> areas)
	{
		this.areas = areas;
	}

	public List<AreaVO> getAreaVOs() {
		return areaVOs;
	}

	public void setAreaVOs(List<AreaVO> areaVOs) {
		this.areaVOs = areaVOs;
	}
	
}
