package com.mes.webclient.app.demo.api;

import java.util.Date;
import java.util.Vector;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTDefinition;
import com.mes.compatibility.client.UTRow;
import com.mes.webclient.controller.vo.QMDefectVO;
import com.mes.webclient.service.impl.BaseService;

public class QMDefectAPI extends BaseService
{
	private int timeout = 30000;

	 public Vector<String[]> getTopNData() throws MESException
	 {
		 String sql = "select atr_key, no_S, memo_S from UD_Defect_TOPN order by cast(no_S as int)";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getLevel1Code() throws MESException
	 {
		 String sql = "select  distinct level1_S, count(*)  from UD_base_defect_code group by level1_S";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getLevel2CodeByLevel1(String level1) throws MESException
	 {
		 String sql = "select distinct level2_S from UD_base_defect_code where level1_S = N'"+level1+"'";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getLevel1() throws MESException
	 {
		 String sql = "select distinct level1_S from UD_quality_part";  //quality_part
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getLevel2ByLevel1(String level1) throws MESException
	 {
		 String sql = "select distinct level2_S from UD_quality_part where level1_S = N'"+level1+"'";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getLevel3ByLevel12(String level1, String level2) throws MESException
	 {
		 String sql = "select distinct level3_S from UD_quality_part where level1_S = N'"+level1+"' and level2_S = N'"+level2+"'";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getLevel4ByLevel123(String level1, String level2, String level3) throws MESException
	 {
		 String sql = "select distinct level4_S from UD_quality_part where level1_S = N'"+level1+"' and level2_S = N'"+level2+"' and level3_S = N'"+level3+"'";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getLevel5ByLevel1234(String level1, String level2, String level3, String level4) throws MESException
	 {
		 String sql = "select distinct level5_S from UD_quality_part where level1_S = N'"+level1+"' and level2_S = N'"+level2+"' and level3_S = N'"+level3+"' and level4_S = N'"+level4+"'";
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
		 if (vin != null)
		 {
			 atRow.setValue("factory_brand_key", getFactoryKey(vin));
		 }
		 Response r = atRow.save( getFunctions().getDBTime(), " ", null);
		 
		 
		 return r;
	 }
	 
	 public String getCurrentCSN(String currentVIN) throws MESException
	 {
		 String sql = "select csn_S from UD_Vechile_Queue where vin_S = '"+currentVIN+"'";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 if(vec!=null && vec.size()>0)
		 {
			 String currentCSN = vec.get(0)[0];
			 return currentCSN;
		 }
		 return "";
	 }
	 
	 public String getPrevVIN(String currentVIN) throws MESException
	 {

		String currentCSN = getCurrentCSN(currentVIN);
	    String sqlStatem = "select Top 1 csn_S, vin_S from UD_Vechile_Queue where cast(csn_S as int) < cast('"+currentCSN+"' as int) order by cast(csn_S as int) desc";
		Vector<String[]> vecRes =   getFunctions().getArrayDataFromActive(sqlStatem, timeout);
		if(vecRes!=null && vecRes.size()>0)
		{
			return vecRes.get(0)[1];
		}

		return "";

	 }
	 
	 public String getNextVIN(String currentVIN) throws MESException
	 {

		String currentCSN = getCurrentCSN(currentVIN);
	    String sqlStatem = "select Top 1 csn_S, vin_S from UD_Vechile_Queue where cast(csn_S as int) > cast('"+currentCSN+"' as int) order by cast(csn_S as int)";
		Vector<String[]> vecRes =   getFunctions().getArrayDataFromActive(sqlStatem, timeout);
		if(vecRes!=null && vecRes.size()>0)
		{
			return vecRes.get(0)[1];
		}
		return "";

	 }
	 
	 public long getFactoryKey(String vin) throws MESException
	 {
		// String sql = "select b.atr_key from UD_Interface_ERP_Order a, uda_order o  , UD_factory_brand b where 	  a.plan_id_S = o.plan_id_S and   a.factory_name_S = b.factory_name_S and a.brand_name_S = b.brand_name_S and o.vin_S = '"+vin+"'";
		 String sql = "select atr_key from  UD_factory_brand  order by creation_time ";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 if(vec!=null && vec.size()>0)
		 {
			 long key  = Long.parseLong(vec.get(0)[0]);
			 return key;
		 }
		 return 0;
	 }
	 
}
