package com.mes.webclient.controller.vo;


public class PrinterVO extends BaseVO
{
	private String siteNumber;
	private String siteName;
	
	private String printerName;
	private String printerIP;
	private String printerBak;
	
	public String getPrinterBak() {
		return printerBak;
	}
	public void setPrinterBak(String printerBak) {
		this.printerBak = printerBak;
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
	public String getPrinterName() {
		return printerName;
	}
	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	public String getPrinterIP() {
		return printerIP;
	}
	public void setPrinterIP(String printerIP) {
		this.printerIP = printerIP;
	}
	

	 
	
}
