/*
 * @(#) EventSheetVO.java 2016-8-12 上午10:37:28
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.controller.vo;

public class EventSheetVO extends BaseVO
{
	String eventSheetNumber;
	String eventSheetName;
	String className;
	String eventType;
	
	//原始类名
	String originalClassName;
	
	//JMS
	String contextFactory;
	String connectionFactoryJNDI;
	String desctinationJNDI;
	String providerUrl;
	
	
	//SOCKET
	String hostName;
	boolean isServer;
	String portNumber;
	String socketStyle;
	
	//TIMER
	int inteval;
	
	//SERIAL_PORT
	String serialPort;
	String stopBatis;
	String baudRate;
	
	//PLC
	String serverName;
	String opcName;
	String userName;
	String password;
	
	String opcUserName;
	String opcPassword;
	
	public String getContextFactory()
	{
		return contextFactory;
	}
	public String getConnectionFactoryJNDI()
	{
		return connectionFactoryJNDI;
	}
	public String getDesctinationJNDI()
	{
		return desctinationJNDI;
	}
	public String getProviderUrl()
	{
		return providerUrl;
	}
	public String getHostName()
	{
		return hostName;
	}
	public boolean getIsServer()
	{
		return isServer;
	}
	public String getPortNumber()
	{
		return portNumber;
	}
	public String getSocketStyle()
	{
		return socketStyle;
	}
	public int getInteval()
	{
		return inteval;
	}
	public String getSerialPort()
	{
		return serialPort;
	}
	public String getStopBatis()
	{
		return stopBatis;
	}
	public String getBaudRate()
	{
		return baudRate;
	}
	public void setContextFactory(String contextFactory)
	{
		this.contextFactory = contextFactory;
	}
	public void setConnectionFactoryJNDI(String connectionFactoryJNDI)
	{
		this.connectionFactoryJNDI = connectionFactoryJNDI;
	}
	public void setDesctinationJNDI(String desctinationJNDI)
	{
		this.desctinationJNDI = desctinationJNDI;
	}
	public void setProviderUrl(String providerUrl)
	{
		this.providerUrl = providerUrl;
	}
	public void setHostName(String hostName)
	{
		this.hostName = hostName;
	}
	public void setIsServer(boolean isServer)
	{
		this.isServer = isServer;
	}
	public void setPortNumber(String portNumber)
	{
		this.portNumber = portNumber;
	}
	public void setSocketStyle(String socketStyle)
	{
		this.socketStyle = socketStyle;
	}
	public void setInteval(int inteval)
	{
		this.inteval = inteval;
	}
	public void setSerialPort(String serialPort)
	{
		this.serialPort = serialPort;
	}
	public void setStopBatis(String stopBatis)
	{
		this.stopBatis = stopBatis;
	}
	public void setBaudRate(String baudRate)
	{
		this.baudRate = baudRate;
	}
	public String getEventSheetNumber()
	{
		return eventSheetNumber;
	}
	public String getEventSheetName()
	{
		return eventSheetName;
	}
	public void setEventSheetNumber(String eventSheetNumber)
	{
		this.eventSheetNumber = eventSheetNumber;
	}
	public void setEventSheetName(String eventSheetName)
	{
		this.eventSheetName = eventSheetName;
	}
	public String getClassName()
	{
		return className;
	}
	public void setClassName(String className)
	{
		this.className = className;
	}
	/**
	 * @return Returns the eventType.
	 */
	public String getEventType()
	{
		return eventType;
	}
	/**
	 * @param eventType The eventType to set.
	 */
	public void setEventType(String eventType)
	{
		this.eventType = eventType;
	}
	public String getServerName()
	{
		return serverName;
	}
	public String getOpcName()
	{
		return opcName;
	}
	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}
	public void setOpcName(String opcName)
	{
		this.opcName = opcName;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getOpcUserName()
	{
		return opcUserName;
	}
	public String getOpcPassword()
	{
		return opcPassword;
	}
	public void setOpcUserName(String opcUserName)
	{
		this.opcUserName = opcUserName;
	}
	public void setOpcPassword(String opcPassword)
	{
		this.opcPassword = opcPassword;
	}
	public String getOriginalClassName()
	{
		return originalClassName;
	}
	public void setOriginalClassName(String originalClassName)
	{
		this.originalClassName = originalClassName;
	}
	
	
}
