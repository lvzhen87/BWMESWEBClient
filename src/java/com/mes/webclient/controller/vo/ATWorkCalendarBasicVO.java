package com.mes.webclient.controller.vo;

import java.util.List;

import com.mes.compatibility.client.ATHandler;
import com.mes.compatibility.client.ATRowFilter;

public class ATWorkCalendarBasicVO extends BaseVO {
 
	private String producedDate;
	private Long areaKey;
	private Long productionlineKey;
	private String classes;
	private String isEffect;
	private String startDate;
	private String endDate;
	private String v_areaName;
	private String v_productionlineName;
	private String v_effects;
	private String v_reststartDates;
	private String v_restendDates;
		
	private Long areaKey1;
	private Long productionlineKey1;
	
	 
	public Long getAreaKey1() {
		return areaKey1;
	}
	public void setAreaKey1(Long areaKey1) {
		this.areaKey1 = areaKey1;
	}
	public Long getProductionlineKey1() {
		return productionlineKey1;
	}
	public void setProductionlineKey1(Long productionlineKey1) {
		this.productionlineKey1 = productionlineKey1;
	}
	public String getV_reststartDates() {
		return v_reststartDates;
	}
	public void setV_reststartDates(String v_reststartDates) {
		this.v_reststartDates = v_reststartDates;
	}
	public String getV_restendDates() {
		return v_restendDates;
	}
	public void setV_restendDates(String v_restendDates) {
		this.v_restendDates = v_restendDates;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	} 
	
	public String getV_effects() {
		return v_effects;
	}
	public void setV_effects(String v_effects) {
		this.v_effects = v_effects;
	}
	public String getV_areaName() {
		return v_areaName;
	}
	public void setV_areaName(String v_areaName) {
		this.v_areaName = v_areaName;
	}
	public String getV_productionlineName() {
		return v_productionlineName;
	}
	public void setV_productionlineName(String v_productionlineName) {
		this.v_productionlineName = v_productionlineName;
	} 
	public String getProducedDate() {
		return producedDate;
	}
	public void setProducedDate(String producedDate) {
		this.producedDate = producedDate;
	} 
	
	public Long getAreaKey() {
		return areaKey;
	}
	public void setAreaKey(Long areaKey) {
		this.areaKey = areaKey;
	}
	public Long getProductionlineKey() {
		return productionlineKey;
	}
	public void setProductionlineKey(Long productionlineKey) {
		this.productionlineKey = productionlineKey;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getIsEffect() {
		return isEffect;
	}
	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}
	 
}
