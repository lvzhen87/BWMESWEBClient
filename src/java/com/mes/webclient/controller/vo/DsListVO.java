package com.mes.webclient.controller.vo;


public class DsListVO extends BaseVO
{
	private String siteNumber;
	private String siteName;
	private String listItemStr;
	
	public String getListItemStr() {
		return listItemStr;
	}

	public void setListItemStr(String listItemStr) {
		this.listItemStr = listItemStr;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteNumber() {
		return siteNumber;
	}

	public void setSiteNumber(String siteNumber) {
		this.siteNumber = siteNumber;
	}

	private String listName;
	
	//工厂编号
	private String listDesc;
	
	//工厂分类
	private String listCategory;

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getListDesc() {
		return listDesc;
	}

	public void setListDesc(String listDesc) {
		this.listDesc = listDesc;
	}

	public String getListCategory() {
		return listCategory;
	}

	public void setListCategory(String listCategory) {
		this.listCategory = listCategory;
	}
	
	
	 
	
}
