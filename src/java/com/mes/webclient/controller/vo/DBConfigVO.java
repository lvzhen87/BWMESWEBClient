package com.mes.webclient.controller.vo;

public class DBConfigVO extends BaseVO {
	public String email;

	public String hostName;
	public int timeOut;
	public String jmsUrl;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public String getJmsUrl() {
		return jmsUrl;
	}
	public void setJmsUrl(String jmsUrl) {
//		String url="failover://(tcp://"+jmsUrl+":61616)";
		this.jmsUrl = jmsUrl;
	}
	
	
	
}
