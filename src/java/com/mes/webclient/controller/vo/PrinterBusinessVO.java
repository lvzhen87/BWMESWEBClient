package com.mes.webclient.controller.vo;


public class PrinterBusinessVO extends BaseVO
{
	private String siteNumber;
	private String siteName;
	
	private Long printerKey;
	private String v_printerName;
	private String printerBusiness;
	private String flag; //启用标志 0 未启用；1 已启用
	
	
	public String getV_printerName() {
		return v_printerName;
	}
	public void setV_printerName(String v_printerName) {
		this.v_printerName = v_printerName;
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
	
	public Long getPrinterKey() {
		return printerKey;
	}
	public void setPrinterKey(Long printerKey) {
		this.printerKey = printerKey;
	}
	public String getPrinterBusiness() {
		return printerBusiness;
	}
	public void setPrinterBusiness(String printerBusiness) {
		this.printerBusiness = printerBusiness;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	

	 
	
}
