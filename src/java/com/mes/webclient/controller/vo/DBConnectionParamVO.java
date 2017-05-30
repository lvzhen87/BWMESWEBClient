package com.mes.webclient.controller.vo;

public class DBConnectionParamVO extends BaseVO {
	String name;
	String description;
	String connectionAddr;
	String dbName;
	String userName;
	String password;
	String port;
	
	String dbType;
	String dbSoftwareType;
	String dbDriver;
	
	String sitenum;
	String siteid;
	
	public String getSitenum() {
		return sitenum;
	}
	public void setSitenum(String sitenum) {
		this.sitenum = sitenum;
	}
	public String getSiteid() {
		return siteid;
	}
	public void setSiteid(String siteid) {
		this.siteid = siteid;
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
	public String getConnectionAddr() {
		return connectionAddr;
	}
	public void setConnectionAddr(String connectionAddr) {
		this.connectionAddr = connectionAddr;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getDbSoftwareType() {
		return dbSoftwareType;
	}
	public void setDbSoftwareType(String dbSoftwareType) {
		this.dbSoftwareType = dbSoftwareType;
	}
	public String getDbDriver() {
		return dbDriver;
	}
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}
	
	
	
}
