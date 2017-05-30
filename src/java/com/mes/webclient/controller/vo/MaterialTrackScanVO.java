package com.mes.webclient.controller.vo;

public class MaterialTrackScanVO extends BaseVO {
	private String vin;
	private String station;
	private String partNumber;
	private String description;
	private String barcode;
	private String scanUser;
	private String scanTimeStart;
	private String scanTimeEnd;

	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
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
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getScanUser() {
		return scanUser;
	}
	public void setScanUser(String scanUser) {
		this.scanUser = scanUser;
	}
	public String getScanTimeStart() {
		return scanTimeStart;
	}
	public void setScanTimeStart(String scanTimeStart) {
		this.scanTimeStart = scanTimeStart;
	}
	public String getScanTimeEnd() {
		return scanTimeEnd;
	}
	public void setScanTimeEnd(String scanTimeEnd) {
		this.scanTimeEnd = scanTimeEnd;
	}
}
