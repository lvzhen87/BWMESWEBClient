package com.mes.webclient.controller.vo;

public class OPCMonitorVO extends BaseUDTVO{

	private String oPCName ;
	private String oPCItem;
	private String groupID;
	private String deviceName;
	private String oPCFunction;
	private boolean monitor;
	private String itemGroup;
	private Integer itemSeq;
	
	
	public Integer getItemSeq() {
		return itemSeq;
	}
	public void setItemSeq(Integer itemSeq) {
		this.itemSeq = itemSeq;
	}
	public String getoPCName() {
		return oPCName;
	}
	public void setoPCName(String oPCName) {
		this.oPCName = oPCName;
	}
	public String getoPCItem() {
		return oPCItem;
	}
	public void setoPCItem(String oPCItem) {
		this.oPCItem = oPCItem;
	}

	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getoPCFunction() {
		return oPCFunction;
	}
	public void setoPCFunction(String oPCFunction) {
		this.oPCFunction = oPCFunction;
	}


	public boolean isMonitor() {
		return monitor;
	}
	public void setMonitor(boolean monitor) {
		this.monitor = monitor;
	}
	public String getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}
	
	
	
}
