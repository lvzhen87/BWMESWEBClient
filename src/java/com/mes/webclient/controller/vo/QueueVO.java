/*
 * @(#) QueueVO.java 2016年7月19日 下午1:44:54
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

public class QueueVO extends BaseVO {
	private long routeKey;
	private String queueName;
	private int queueCapacity;
	private int queueDuration;
	private boolean autoStart;
	private String xy;

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public long getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(long routeKey) {
		this.routeKey = routeKey;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public int getQueueCapacity() {
		return queueCapacity;
	}

	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

	public int getQueueDuration() {
		return queueDuration;
	}

	public void setQueueDuration(int queueDuration) {
		this.queueDuration = queueDuration;
	}

	public boolean isAutoStart() {
		return autoStart;
	}

	public void setAutoStart(boolean autoStart) {
		this.autoStart = autoStart;
	}

}
