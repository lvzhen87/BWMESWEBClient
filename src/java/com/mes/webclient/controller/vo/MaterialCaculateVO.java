package com.mes.webclient.controller.vo;

public class MaterialCaculateVO extends BaseVO {
	private String planId;
	private String planStartTime;
	private String planEndTime;
	private String partNum;
	private String partDesc;
	private String partReqCount;
	
	
	public String getPartNum() {
		return partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getPartReqCount() {
		return partReqCount;
	}
	public void setPartReqCount(String partReqCount) {
		this.partReqCount = partReqCount;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
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
