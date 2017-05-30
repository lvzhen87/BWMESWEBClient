package com.mes.webclient.controller.vo;


public class OperationDefectCodeVO extends BaseVO
{
	private String siteNumber;
	private String siteName;
	 

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteNumber() {
		return siteNumber;
	}

	public void setSiteNumber(String siteNumber) {
		this.siteNumber = siteNumber;
	}

	private String vin;
	
	//工厂编号
 
	private String brandName;
	private String qualityGate_1;
	private String defectName;
	//工厂分类
	private String qualityGate_2;
	private String responsibleParty;
	private String businessUnit;
	private String defectLevel;
	private String defectDesc;
	private String repairOpinion;
	private String supplierName;
	private String tableData;
	private String suplierNum ;
	private String suplierPart;
	private String handleUser;
	private String orderNum;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTableData() {
		return tableData;
	}

	public void setTableData(String tableData) {
		this.tableData = tableData;
	}

	public String getSuplierNum() {
		return suplierNum;
	}

	public void setSuplierNum(String suplierNum) {
		this.suplierNum = suplierNum;
	}

	public String getSuplierPart() {
		return suplierPart;
	}

	public void setSuplierPart(String suplierPart) {
		this.suplierPart = suplierPart;
	}


	
//select brand_name_S ,site_num_S ,'业务单元', VIN_NUM_S,'order_num' , quality_gate_1_S,quality_gate_2_S ,defect_level_S ,responsible_party_S ,defect_name_S , level1_S  , level2_S,level3_S,level4_S,level5_S,input_user_S ,input_time_T from AT_defect_car WHERE STATUS_S = '1'

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getDefectLevel() {
		return defectLevel;
	}

	public void setDefectLevel(String defectLevel) {
		this.defectLevel = defectLevel;
	}

	public String getDefectDesc() {
		return defectDesc;
	}

	public void setDefectDesc(String defectDesc) {
		this.defectDesc = defectDesc;
	}

	public String getRepairOpinion() {
		return repairOpinion;
	}

	public void setRepairOpinion(String repairOpinion) {
		this.repairOpinion = repairOpinion;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getHandleUser() {
		return handleUser;
	}

	public void setHandleUser(String handleUser) {
		this.handleUser = handleUser;
	}

	public String getResponsibleParty() {
		return responsibleParty;
	}

	public void setResponsibleParty(String responsibleParty) {
		this.responsibleParty = responsibleParty;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	 
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getQualityGate_1() {
		return qualityGate_1;
	}

	public void setQualityGate_1(String qualityGate_1) {
		this.qualityGate_1 = qualityGate_1;
	}

	public String getDefectName() {
		return defectName;
	}

	public void setDefectName(String defectName) {
		this.defectName = defectName;
	}

	public String getQualityGate_2() {
		return qualityGate_2;
	}

	public void setQualityGate_2(String qualityGate_2) {
		this.qualityGate_2 = qualityGate_2;
	}
 
	
}
