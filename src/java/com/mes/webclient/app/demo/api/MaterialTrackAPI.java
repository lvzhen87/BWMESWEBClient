package com.mes.webclient.app.demo.api;

import java.util.Date;
import java.util.Vector;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTDefinition;
import com.mes.compatibility.client.UTRow;
import com.mes.webclient.controller.vo.QMDefectVO;
import com.mes.webclient.service.impl.BaseService;

public class MaterialTrackAPI extends BaseService
{
	private int timeout = 30000;

	 public Vector<String[]> getPartData(String partNum, String partName) throws MESException
	 {
		 String sql = "select atr_key, part_num_S, part_name_S from UD_ERP_Part_BasicData where 1=1";
		 if(partNum!=null && !"".equals(partNum))
		 {
			 sql = sql + " and part_num_S like '%"+partNum+"%'";
		 }
		 if(partName!=null && !"".equals(partName))
		 {
			 sql = sql + " and part_name_S like '%"+partName+"%'";
		 }
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 
	 
	 public Response saveDefect(QMDefectVO defectInfo) throws MESException
	 {
		 String vin = defectInfo.getVin();
		 UTDefinition  atDefinition = getFunctions().getATDefinition("defect_car");
		 UTRow atRow = atDefinition.createUTRow_();
		 atRow.setValue("VIN_NUM", defectInfo.getVechileNum());
		 atRow.setValue("vin", vin);
		 atRow.setValue("input_user", defectInfo.getInputUser());
		 atRow.setValue("shift", defectInfo.getShift());
		 atRow.setValue("orderNum", defectInfo.getOrderNum());
		 atRow.setValue("is_access", defectInfo.getIsAccess());
		 atRow.setValue("checkDate", defectInfo.getCheckDate());
		 atRow.setValue("memo", defectInfo.getMemo());
		 
		 atRow.setValue("part_level1", defectInfo.getLevel1());
		 atRow.setValue("part_level2", defectInfo.getLevel2());
		 atRow.setValue("part_level3", defectInfo.getLevel3());
		 atRow.setValue("part_level4", defectInfo.getLevel4());
		 atRow.setValue("part_level5", defectInfo.getLevel5());
		 
		 atRow.setValue("level1", defectInfo.getCode1());
		 atRow.setValue("level2", defectInfo.getCode2());
		 
		 atRow.setValue("defect_level", defectInfo.getDefectLevel());
		 atRow.setValue("responsible_party", defectInfo.getResponsible());
//		 if (vin != null)
//		 {
//			 atRow.setValue("factory_brand_key", getFactoryKey(vin));
//		 }
		 Response r = atRow.save( getFunctions().getDBTime(), " ", null);
		 
		 
		 return r;
	 }
	 
	 
	 
}
