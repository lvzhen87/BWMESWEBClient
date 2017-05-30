/*
 * @(#) OrderVO.java 2016年8月5日 上午10:46:16
 *
 * Copyright 2016 Catarc, Inc. All rights reserved.
 * Catarc PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.ac.catarc.qj.vo;


public class OrderVO extends BaseVO
{
	
	private String status;
	private String csn;
	private String carType;
	private String color;
	private String engineType;
	private String entryTime;
	private String orderNumber;
	private String specialOrder;
	private String station;
	private String userName;
	private String vin;
	private String planId;
	private String carTypeDesc;
	private String carProject;
	private String planStartTime;
	private String planEndTime;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCsn() {
		return csn;
	}
	public void setCsn(String csn) {
		this.csn = csn;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public String getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getSpecialOrder() {
		return specialOrder;
	}
	public void setSpecialOrder(String specialOrder) {
		this.specialOrder = specialOrder;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getCarTypeDesc() {
		return carTypeDesc;
	}
	public void setCarTypeDesc(String carTypeDesc) {
		this.carTypeDesc = carTypeDesc;
	}
	public String getCarProject() {
		return carProject;
	}
	public void setCarProject(String carProject) {
		this.carProject = carProject;
	}
	public String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	public String getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}
	
}
