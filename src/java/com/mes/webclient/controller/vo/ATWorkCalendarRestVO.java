package com.mes.webclient.controller.vo;

public class ATWorkCalendarRestVO extends BaseVO {
 
	private Long workCalendarBasicKey;	 
	private String startDate;
	private String endDate;
	 
	
	public Long getWorkCalendarBasicKey() {
		return workCalendarBasicKey;
	}
	public void setWorkCalendarBasicKey(Long workCalendarBasicKey) {
		this.workCalendarBasicKey = workCalendarBasicKey;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	  	
}
