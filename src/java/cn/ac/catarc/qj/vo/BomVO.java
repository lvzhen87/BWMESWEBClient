/*
 * @(#) OrderVO.java 2016年8月5日 上午10:46:16
 *
 * Copyright 2016 Catarc, Inc. All rights reserved.
 * Catarc PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.ac.catarc.qj.vo;


public class BomVO extends BaseVO
{
	
	private String effectDate;
	private String factoryNum;
	private String lineNum;
	private String orderNum;
	private String partDesc;
	private String partNum;
	private String quantity;
	private String stationNum;
	public String getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}
	public String getFactoryNum() {
		return factoryNum;
	}
	public void setFactoryNum(String factoryNum) {
		this.factoryNum = factoryNum;
	}
	public String getLineNum() {
		return lineNum;
	}
	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getPartNum() {
		return partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getStationNum() {
		return stationNum;
	}
	public void setStationNum(String stationNum) {
		this.stationNum = stationNum;
	}
	
	
}
