/*
 * @(#) OrderVO.java 2016年8月5日 上午10:46:16
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import com.mes.compatibility.ui.Time;

public class OrderVO extends BaseVO
{
	private String orderNumber;
	private String enteredTime;
	private String promisedTime;
	private String note;
	private String orderState;
	private String trxState;
	private String currentStatus;
	public String getOrderNumber()
	{
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}
	public String getEnteredTime()
	{
		return enteredTime;
	}
	public void setEnteredTime(String enteredTime)
	{
		this.enteredTime = enteredTime;
	}
	public String getPromisedTime()
	{
		return promisedTime;
	}
	public void setPromisedTime(String promisedTime)
	{
		this.promisedTime = promisedTime;
	}
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
	}
	public String getOrderState()
	{
		return orderState;
	}
	public void setOrderState(String orderState)
	{
		this.orderState = orderState;
	}
	public String getTrxState()
	{
		return trxState;
	}
	public void setTrxState(String trxState)
	{
		this.trxState = trxState;
	}
	public String getCurrentStatus()
	{
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus)
	{
		this.currentStatus = currentStatus;
	}
	
	
}
