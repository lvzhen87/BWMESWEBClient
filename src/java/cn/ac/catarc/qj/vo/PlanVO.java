package cn.ac.catarc.qj.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlanVO extends BaseVO{
	private String batchNo;
	private String comment;
	private String deleteFlag;
	private String erpUploadFlag;
	private String lineId;
	private String planEndTime;
	private String planId;
	private String planStartTime;
	private String plantId;
	private String productionPlanBdLine;
	private String quantity;
	@Id
	@GeneratedValue
	private String seqId;
	private String source;
	private String splitQty;
	private String status;
	private String vin8;
	private String vsn;
	private String vsnDesc;
	private String carProject;
	private String color;
	 
	 
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public String getSpecialOrder() {
		return specialOrder;
	}
	public void setSpecialOrder(String specialOrder) {
		this.specialOrder = specialOrder;
	}
	private String engineType;
	private String specialOrder;
	
	
	public String getCarProject() {
		return carProject;
	}
	public void setCarProject(String carProject) {
		this.carProject = carProject;
	}
	public String getVsnDesc() {
		return vsnDesc;
	}
	public void setVsnDesc(String vsnDesc) {
		this.vsnDesc = vsnDesc;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getErpUploadFlag() {
		return erpUploadFlag;
	}
	public void setErpUploadFlag(String erpUploadFlag) {
		this.erpUploadFlag = erpUploadFlag;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getProductionPlanBdLine() {
		return productionPlanBdLine;
	}
	public void setProductionPlanBdLine(String productionPlanBdLine) {
		this.productionPlanBdLine = productionPlanBdLine;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSplitQty() {
		return splitQty;
	}
	public void setSplitQty(String splitQty) {
		this.splitQty = splitQty;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVin8() {
		return vin8;
	}
	public void setVin8(String vin8) {
		this.vin8 = vin8;
	}
	public String getVsn() {
		return vsn;
	}
	public void setVsn(String vsn) {
		this.vsn = vsn;
	}
}
