package com.mes.webclient.controller.vo;


public class DefectSenderVO extends BaseVO
{
	private String siteNumber;
	private String siteName;
	private String defectDesc;
	private String checkUser;
	private String senderQuanlityGate;
	private String receiveQuanlityGate;
	private String defectLevel;
	private String dateTime;
	
	
	
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDefectLevel() {
		return defectLevel;
	}

	public void setDefectLevel(String defectLevel) {
		this.defectLevel = defectLevel;
	}

	public String getSiteNumber() {
		return siteNumber;
	}

	public void setSiteNumber(String siteNumber) {
		this.siteNumber = siteNumber;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getDefectDesc() {
		return defectDesc;
	}

	public void setDefectDesc(String defectDesc) {
		this.defectDesc = defectDesc;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getSenderQuanlityGate() {
		return senderQuanlityGate;
	}

	public void setSenderQuanlityGate(String senderQuanlityGate) {
		this.senderQuanlityGate = senderQuanlityGate;
	}

	public String getReceiveQuanlityGate() {
		return receiveQuanlityGate;
	}

	public void setReceiveQuanlityGate(String receiveQuanlityGate) {
		this.receiveQuanlityGate = receiveQuanlityGate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String status;
	 
	
}
