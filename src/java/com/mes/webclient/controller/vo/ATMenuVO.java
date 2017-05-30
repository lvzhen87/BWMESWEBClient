package com.mes.webclient.controller.vo;

import com.mes.compatibility.client.ATRow;

public class ATMenuVO extends BaseVO {
 
 	private String pid;
	private String menuName;
	private String menuIcon; //新添加字段，数据库也添加了//by me 
	private String menuSrc;
	private String parent;
	private String v_parentS;
	private String v_pName;
	private String v_check;
	
	public ATMenuVO(){};
	public ATMenuVO(ATRow atRow){
		this.menuIcon = String.valueOf(atRow.getValue("menuIcon"));//by me 
		this.menuName = String.valueOf(atRow.getValue("menuName"));
		this.menuSrc = String.valueOf(atRow.getValue("menuSrc"));
	}
	 
	 
	public String getV_parentS() {
		return v_parentS;
	}
	public void setV_parentS(String v_parentS) {
		this.v_parentS = v_parentS;
	}
	public String getV_pName() {
		return v_pName;
	}
	public void setV_pName(String v_pName) {
		this.v_pName = v_pName;
	}
	public String getV_check() {
		return v_check;
	}
	public void setV_check(String v_check) {
		this.v_check = v_check;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuSrc() {
		return menuSrc;
	}
	public void setMenuSrc(String menuSrc) {
		this.menuSrc = menuSrc;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	} 
	
	
}
