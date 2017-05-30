/*
 * @(#) BaseService.java 2016-6-30 下午8:33:11
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.service.impl;

import com.mes.compatibility.client.MESException;
import com.mes.webclient.proxy.WebServerProxy;

public class BaseService
{
	/**
	 * 获得客户端连接服务器代理
	 */
	public WebServerProxy getFunctions() throws MESException
	{
			WebServerProxy CMESProxy = WebServerProxy.getInstance();
			return CMESProxy;
	}
}
