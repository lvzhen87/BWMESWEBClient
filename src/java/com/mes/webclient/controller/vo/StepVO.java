/*
 * @(#) StepVO.java 2016年7月18日 下午10:22:55
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;

import java.util.Vector;

import com.mes.compatibility.client.WorkCenter;

public class StepVO extends BaseVO
{
	private long routeKey;
	private long operationKey;
	private String operationName;
	private String operationDesc;
	private String stepName;
	private String originalStepName;
	private String stepDescription;
	private String xy;
	private String reason;
	private String reasons;
	private long imageKey;
	private String imageName;
	private String imagePath = "";
	
	public long getImageKey() {
		return imageKey;
	}
	public void setImageKey(long imageKey) {
		this.imageKey = imageKey;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOperationDesc() {
		return operationDesc;
	}
	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	private String[] workCenterKeys;
	private Vector<WorkCenter> workCenters;
	public long getRouteKey()
	{
		return routeKey;
	}
	public void setRouteKey(long routeKey)
	{
		this.routeKey = routeKey;
	}
	public long getOperationKey()
	{
		return operationKey;
	}
	public void setOperationKey(long operationKey)
	{
		this.operationKey = operationKey;
	}
	public String getOperationName()
	{
		return operationName;
	}
	public void setOperationName(String operationName)
	{
		this.operationName = operationName;
	}
	public String getStepName()
	{
		return stepName;
	}
	public void setStepName(String stepName)
	{
		this.stepName = stepName;
	}
	public String getStepDescription()
	{
		return stepDescription;
	}
	public void setStepDescription(String stepDescription)
	{
		this.stepDescription = stepDescription;
	}
	public String[] getWorkCenterKeys()
	{
		return workCenterKeys;
	}
	public void setWorkCenterKeys(String[] workCenterKeys)
	{
		this.workCenterKeys = workCenterKeys;
	}
	public Vector<WorkCenter> getWorkCenters()
	{
		return workCenters;
	}
	public void setWorkCenters(Vector<WorkCenter> workCenters)
	{
		this.workCenters = workCenters;
	}
	public String getOriginalStepName()
	{
		return originalStepName;
	}
	public void setOriginalStepName(String originalStepName)
	{
		this.originalStepName = originalStepName;
	}
	
	
	
	
}