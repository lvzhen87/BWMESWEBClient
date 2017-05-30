package com.mes.webclient.controller.vo;

public class MaterialTrackConfigVO extends BaseVO {
	private String station;
	private String partNumber;
	private String description;

	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
