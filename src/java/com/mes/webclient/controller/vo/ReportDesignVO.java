package com.mes.webclient.controller.vo;


public class ReportDesignVO extends   BaseVO {
	private String name;
	private String description;
	private String category;
	private String reportDataName;
	private String reportDesignVersion;
	private String reportDataKey;
	public String getReportDataKey() {
		return reportDataKey;
	}
	public void setReportDataKey(String reportDataKey) {
		this.reportDataKey = reportDataKey;
	}
	public String getReportDesignVersion() {
		return reportDesignVersion;
	}
	public void setReportDesignVersion(String reportDesignVersion) {
		this.reportDesignVersion = reportDesignVersion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getReportDataName() {
		return reportDataName;
	}
	public void setReportDataName(String reportDataName) {
		this.reportDataName = reportDataName;
	}

	
	 
	
}
