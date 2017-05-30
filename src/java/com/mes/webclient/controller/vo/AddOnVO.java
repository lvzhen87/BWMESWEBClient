package com.mes.webclient.controller.vo;

public class AddOnVO extends BaseVO {
	private String name;
	private String description;
	private String category;
	private String userVersion;
	private String filePath;
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
	public String getUserVersion() {
		return userVersion;
	}
	public void setUserVersion(String userVersion) {
		this.userVersion = userVersion;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
