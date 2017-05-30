package com.mes.webclient.controller.vo;

import java.util.Vector;

import com.mes.compatibility.client.ReportDataDefinitionVariable;

public class ReportDataDefinitionVO extends   BaseVO {
	private String name;
	private String description;
	private String category;
	private String reportVersion;
	private String reportSubDataStr;
	
	
	public String getReportSubDataStr() {
		return reportSubDataStr;
	}

	public void setReportSubDataStr(String reportSubDataStr) {
		this.reportSubDataStr = reportSubDataStr;
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

	public String getReportVersion() {
		return reportVersion;
	}

	public void setReportVersion(String reportVersion) {
		this.reportVersion = reportVersion;
	}

	private Vector<ReportDataDefinitionVariable> reportDataDefinitionVariable =new Vector<ReportDataDefinitionVariable>();

	public Vector<ReportDataDefinitionVariable> getReportDataDefinitionVariable() {
		return reportDataDefinitionVariable;
	}

	public void setReportDataDefinitionVariable(
			Vector<ReportDataDefinitionVariable> reportDataDefinitionVariable) {
		this.reportDataDefinitionVariable = reportDataDefinitionVariable;
	}
	
}
