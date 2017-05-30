/*
 * @(#) ArcVO.java 2016年7月19日 下午2:04:39
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class ArcVO extends BaseVO
{
	private long routeKey;
	private String arcName;
	private boolean mainPath;
	private long fromKey;
	private long toKey;
	private String type;
	private String reason;
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getFromKey() {
		return fromKey;
	}
	public void setFromKey(long fromKey) {
		this.fromKey = fromKey;
	}
	public long getToKey() {
		return toKey;
	}
	public void setToKey(long toKey) {
		this.toKey = toKey;
	}
	public long getRouteKey()
	{
		return routeKey;
	}
	public void setRouteKey(long routeKey)
	{
		this.routeKey = routeKey;
	}
	public String getArcName()
	{
		return arcName;
	}
	public void setArcName(String arcName)
	{
		this.arcName = arcName;
	}
	public boolean isMainPath()
	{
		return mainPath;
	}
	public void setMainPath(boolean mainPath)
	{
		this.mainPath = mainPath;
	}
	
	
}
