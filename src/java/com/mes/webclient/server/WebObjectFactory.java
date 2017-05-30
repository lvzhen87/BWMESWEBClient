package com.mes.webclient.server;

import java.io.Serializable;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.ServerInfo;
import com.mes.compatibility.manager.ServerImpl;
import com.mes.compatibility.manager.SiteCache;
import com.mes.shopflow.common.exceptions.MESProxyException;
import com.mes.shopflow.proxies.ProxyFactory;


public class WebObjectFactory extends ServerImpl implements Serializable
{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 4426500114634817335L;

	public WebObjectFactory(ServerInfo serverInfo) throws MESProxyException, MESException
	{
		super(serverInfo);
		getProxyFactory().setClientType(
			ProxyFactory.CLIENT_TYPE_THIN);
		if (this.getDBInfo() != null)
		{
			int siteNumber = this.getDBInfo().getSiteNumber();
			setSiteCache(new SiteCache(siteNumber));
		}
		setDefaultServer(this);
	}
}
