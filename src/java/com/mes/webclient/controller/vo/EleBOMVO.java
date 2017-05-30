/*
 * @(#) BOMVO.java 2016-7-7 下午3:11:19
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.controller.vo;

public class EleBOMVO extends BaseVO
{
	private String parentNumber;
	private String parentDesc;
	private String sunNumber;
	private String sunDesc;
	private String station;
	private String partCount;
	
	public String getParentNumber() {
		return parentNumber;
	}
	public String getPartCount() {
		return partCount;
	}
	public void setPartCount(String partCount) {
		this.partCount = partCount;
	}
	public void setParentNumber(String parentNumber) {
		this.parentNumber = parentNumber;
	}
	public String getParentDesc() {
		return parentDesc;
	}
	public void setParentDesc(String parentDesc) {
		this.parentDesc = parentDesc;
	}
	public String getSunNumber() {
		return sunNumber;
	}
	public void setSunNumber(String sunNumber) {
		this.sunNumber = sunNumber;
	}
	public String getSunDesc() {
		return sunDesc;
	}
	public void setSunDesc(String sunDesc) {
		this.sunDesc = sunDesc;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	 
	
}
