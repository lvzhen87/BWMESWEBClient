/*
 * @(#) RouteVO.java 2016年7月12日 上午9:50:29
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller.vo;


public class RouteStepVO  extends BaseVO {
	private static final long serialVersionUID = 1L;
	private Integer routeStepKey;
	private Integer siteNum;
	private Integer routeKey;
	private Integer opKey;
	private String routeStepName;
	private String routeStepType;
	private String description;
	private String category;
	private Integer pixelX;
	private Integer pixelY;
	private Integer instListKey;
	private Integer dcsListKey;
	private Integer formKey;
	private Integer labelWidth;
	private Integer borderFlag;
	private Integer checklistKey;
	private String dependencies;
	private Integer enforcement;
	private String reasons;
	private Integer cycleDuration;
	private Integer cycleDurationType;
	private Integer failure;
	private Integer valueAdded;
	private Integer workCenterAssigned;

	public void setRouteStepKey(Integer routeStepKey){
		this.routeStepKey=routeStepKey;
	}
	public Integer getRouteStepKey(){
		return routeStepKey;
	}
	public void setSiteNum(Integer siteNum){
		this.siteNum=siteNum;
	}
	public Integer getSiteNum(){
		return siteNum;
	}
	public void setRouteKey(Integer routeKey){
		this.routeKey=routeKey;
	}
	public Integer getRouteKey(){
		return routeKey;
	}
	public void setOpKey(Integer opKey){
		this.opKey=opKey;
	}
	public Integer getOpKey(){
		return opKey;
	}
	public void setRouteStepName(String routeStepName){
		this.routeStepName=routeStepName;
	}
	public String getRouteStepName(){
		return routeStepName;
	}
	public void setRouteStepType(String routeStepType){
		this.routeStepType=routeStepType;
	}
	public String getRouteStepType(){
		return routeStepType;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public void setCategory(String category){
		this.category=category;
	}
	public String getCategory(){
		return category;
	}
	public void setPixelX(Integer pixelX){
		this.pixelX=pixelX;
	}
	public Integer getPixelX(){
		return pixelX;
	}
	public void setPixelY(Integer pixelY){
		this.pixelY=pixelY;
	}
	public Integer getPixelY(){
		return pixelY;
	}
	public void setInstListKey(Integer instListKey){
		this.instListKey=instListKey;
	}
	public Integer getInstListKey(){
		return instListKey;
	}
	public void setDcsListKey(Integer dcsListKey){
		this.dcsListKey=dcsListKey;
	}
	public Integer getDcsListKey(){
		return dcsListKey;
	}
	public void setFormKey(Integer formKey){
		this.formKey=formKey;
	}
	public Integer getFormKey(){
		return formKey;
	}
	public void setLabelWidth(Integer labelWidth){
		this.labelWidth=labelWidth;
	}
	public Integer getLabelWidth(){
		return labelWidth;
	}
	public void setBorderFlag(Integer borderFlag){
		this.borderFlag=borderFlag;
	}
	public Integer getBorderFlag(){
		return borderFlag;
	}
	public void setChecklistKey(Integer checklistKey){
		this.checklistKey=checklistKey;
	}
	public Integer getChecklistKey(){
		return checklistKey;
	}
	public void setDependencies(String dependencies){
		this.dependencies=dependencies;
	}
	public String getDependencies(){
		return dependencies;
	}
	public void setEnforcement(Integer enforcement){
		this.enforcement=enforcement;
	}
	public Integer getEnforcement(){
		return enforcement;
	}
	public void setReasons(String reasons){
		this.reasons=reasons;
	}
	public String getReasons(){
		return reasons;
	}
	public void setCycleDuration(Integer cycleDuration){
		this.cycleDuration=cycleDuration;
	}
	public Integer getCycleDuration(){
		return cycleDuration;
	}
	public void setCycleDurationType(Integer cycleDurationType){
		this.cycleDurationType=cycleDurationType;
	}
	public Integer getCycleDurationType(){
		return cycleDurationType;
	}
	public void setFailure(Integer failure){
		this.failure=failure;
	}
	public Integer getFailure(){
		return failure;
	}
	public void setValueAdded(Integer valueAdded){
		this.valueAdded=valueAdded;
	}
	public Integer getValueAdded(){
		return valueAdded;
	}
	public void setWorkCenterAssigned(Integer workCenterAssigned){
		this.workCenterAssigned=workCenterAssigned;
	}
	public Integer getWorkCenterAssigned(){
		return workCenterAssigned;
	}
}
