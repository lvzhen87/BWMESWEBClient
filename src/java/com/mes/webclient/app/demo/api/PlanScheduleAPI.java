package com.mes.webclient.app.demo.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTDefinition;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.ui.Time;
import com.mes.shopflow.common.constants.filtering.IATRowFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.transactiongrouping.UserTransaction;
import com.mes.webclient.controller.vo.PlanScheduleVO;
import com.mes.webclient.service.impl.BaseService;

public class PlanScheduleAPI extends BaseService
{
	private int timeout = 30000;
	

//	 public UTDefinition getAttributes() throws MESException
//	 {
//		 UTDefinition utDefinition =   getFunctions().getATDefinition("PlanScheduleAtt");
//		 
//		 return utDefinition;
//	 }
	 public Vector<String[]> getAttributes(String factory, String workshop, String attruName) throws MESException
	 {
		 String sql = "select atr_key, factory_S, workshop_S, name_S, attribute_S, batch_I, pripority_I from UD_PlanScheduleAtt where 1=1 ";
		 if(factory!=null && !"".equals(factory))
		 {
			 sql = sql + " and factory_S like '%"+factory+"%'";
		 }
		 if(workshop!=null && !"".equals(workshop))
		 {
			 sql = sql + " and workshop_S like '%"+workshop+"%'";
		 }
		 if(attruName!=null && !"".equals(attruName))
		 {
			 sql = sql + " and name_S like '%"+attruName+"%'";
		 }
		 sql = sql + " order by creation_time desc";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getConditions() throws MESException
	 {
		 String sql = "select atr_key, factory_S, workshop_S, type_S, subattru_a_S, subattru_b_S, interval_I from UD_PlanScheduleCon order by creation_time desc";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getFactories() throws MESException
	 {
		 String sql = "select distinct factory_S from UD_PlanScheduleAtt";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getAllFactories() throws MESException
	 {
		 String sql = "select distinct site_name from FACTORY";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getWorkshopByFactory(String name) throws MESException
	 {
		 String sql = "select distinct workshop_S from UD_PlanScheduleAtt where factory_S = '"+ name +"'";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getSubAttru(String factoryName, String workShopName) throws MESException
	 {
		 String sql = "select distinct b.subname_S from UD_PlanScheduleAtt a, UD_PlanScheduleAttSub b where a.atr_key = b.parentKey_I and a.factory_S = '"+factoryName+"' and a.workshop_S = '"+workShopName+"'";
		 Vector<String[]> vec =  getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getSubAttru(String factoryName, String workShopName, String attruName) throws MESException
	 {
		 String sql = "select distinct b.subname_S from UD_PlanScheduleAtt a, UD_PlanScheduleAttSub b where a.atr_key = b.parentKey_I and a.factory_S = '"+factoryName+"' and a.workshop_S = '"+workShopName+"' and a.name_S = '"+attruName+"'";
		 Vector<String[]> vec =  getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getConfigures(String factory, String workshop, String type) throws MESException
	 {
		 String sql = "select atr_key, vehicle_type_S, factory_S, workshop_S,attru_name_S, subattru_name_S from UD_CarTypeAttruConfig where 1=1";
		 if(factory!=null && !"".equals(factory))
		 {
			 sql = sql + " and factory_S like '%"+factory+"%'";
		 }
		 if(workshop!=null && !"".equals(workshop))
		 {
			 sql = sql + " and workshop_S like '%"+workshop+"%'";
		 }
		 if(type!=null && !"".equals(type))
		 {
			 sql = sql + " and vehicle_type_S like '%"+type+"%'";
		 }
		 sql = sql + " order by vehicle_type_S";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getVehicleTypes() throws MESException
	 {
		 String sql = "select distinct type_S from UD_VehicleTypeBasicData";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getAttru(String factoryName, String workShopName) throws MESException
	 {
		 String sql = "select distinct a.name_S from UD_PlanScheduleAtt a where a.factory_S = '"+factoryName+"' and a.workshop_S = '"+workShopName+"'";
		 Vector<String[]> vec =  getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public UTRow getRowByKey(long key, String name) throws MESException
	 {
		 UTDefinition utDefinition =   getFunctions().getATDefinition(name);
		 UTRowFilter utRowfilter = getFunctions().createATRowFilter(name);
		 utRowfilter.addSearchBy(IATRowFilterAttributes.KEY, IFilterComparisonOperators.EQUAL_TO, key);
		 Vector<UTRow> vec = utDefinition.getUTRowsByFilter(utRowfilter, false);
		 if(vec!=null&& vec.size()>0)
		 {
			 return vec.get(0);
		 }
		 return null;
	 }
	 
	 public UTRow createUTRow(String name) throws MESException
	 {
		 UTDefinition utDefinition =   getFunctions().getATDefinition(name);
		 return utDefinition.createUTRow_();
	 }
	 
	 public Time getDBTime() throws MESException
	 {
		 return  getFunctions().getDBTime();
	 }
	 
	 public UserTransaction getUserTrx() throws Throwable
	 {
		 UserTransaction userTrx = getFunctions().getUserTransaction();
		 
		 return userTrx;
	 }
 
	 public Vector<String[]> getSubUTRowByParentkey(long key) throws MESException
	 {
		 String sql = "select atr_key, subname_S, subattribute_S, subpripority_I, subBatch_I from UD_PlanScheduleAttSub  where parentKey_I = "+ key +" order by creation_time desc";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getOrderData(String completeTime) throws MESException
	 {
		 String sql = "select a.order_key, b.factory_S, b.vin_S, b.vehicletype_S, b.color_S, b.isSpecial_S, b.csn_S, b.completetime_S, b.abOnline_S, b.abOffline_S, b.pbOnline_S,b.pbOffline_S, b.wbOnline_S, b.wbOffline_S, b.seq_I from WORK_ORDER a, UDA_Order b where b.object_key = a.order_key and status_I = 3";
		 if(completeTime!=null || completeTime!="")
		 {
			 sql = sql + " and b.completetime_S = '"+completeTime+"'";
		 }
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getAttruConfigures(String factory, String workshop) throws MESException
	 {
		 String sql = "select name_S, pripority_I from UD_PlanScheduleAtt where factory_S = '"+factory+"' and workshop_S = '"+workshop+"' order by pripority_I";
		
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public String getSubAttruValue(String factory, String workshop, String type, String attruName) throws MESException
	 {
		 String subAttru = "";
		 String sql = "select subattru_name_S from UD_CarTypeAttruConfig where factory_S = '"+factory+"' and workshop_S ='"+workshop+"' and vehicle_type_S='"+type+"' and attru_name_S = '"+attruName+"'";
		
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 if(vec!=null && vec.size()>0)
		 {
			 subAttru = vec.get(0)[0];
		 }
		 return subAttru;
	 }
	 
	 public Vector<String[]> getSubAttruProp(String factory, String workshop, String attruName, String subAttru) throws MESException
	 {
//		 String proirety = "";
		 String sql = "select distinct b.subname_S, b.subpripority_I, b.subBatch_I from UD_PlanScheduleAtt a, UD_PlanScheduleAttSub b where a.atr_key = b.parentKey_I and a.factory_S = '"+factory+"' and a.workshop_S = '"+workshop+"' and a.name_S = '"+attruName+"' and b.subname_S = '"+subAttru+"'";
		
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
//		 if(vec!=null && vec.size()>0)
//		 {
//			 proirety = vec.get(0)[0];
//		 }
		 return vec;
	 }
	 
	 public Vector<PlanScheduleVO> exec(Vector<PlanScheduleVO> tempList, Vector<String> typeVec, String factory, String workshop) throws MESException
	{
		
		HashMap<String, Vector<PlanScheduleVO>> typeVOMap = new HashMap<String, Vector<PlanScheduleVO>>();
		HashMap<String, Integer> typeSizeMap = new HashMap<String, Integer>();
		TreeMap<Integer, String> attruMap = new TreeMap<Integer, String>();
		HashMap typeMap = null;
		TreeMap categoryMap = null;
		HashMap<String, String[]> typeAttruMap = new HashMap<String, String[]>();
		
//		Vector<PlanScheduleVO> newTempList = new Vector<PlanScheduleVO>();
//		typeVOMap.clear();
//		typeSizeMap.clear();
//		pointMap.clear();


//		attruMap.clear();
//		Vector<String[]> pbAttruConfig = getAttruConfigures(
//			factory, "PB");
		Vector<String[]> attruConfig = getAttruConfigures(
			factory, workshop);
		for (int i = 0; attruConfig != null && i < attruConfig.size(); i++)
		{
			int attruPrio = Integer.parseInt(attruConfig.get(i)[1]);
			String attruName = attruConfig.get(i)[0];
			attruMap.put(
				attruPrio, attruName);

		}
		if (attruMap != null)
		{
			Set setAttru = attruMap.keySet();
			if (setAttru != null && setAttru.size() > 0)
			{
				Iterator itAttru = setAttru.iterator();
				while (itAttru.hasNext())
				{
					int attruprio = (int) itAttru.next();
					String attruName = (String) attruMap.get(attruprio);
					categoryMap = new TreeMap();
					typeMap = new HashMap();
					Vector<String> subAttruVec = new Vector<String>();
					for (int j = 0; typeVec != null && j < typeVec.size(); j++)
					{

						String type = typeVec.get(j);
						String subAttru = getSubAttruValue(
							factory, workshop, type, attruName);
						Vector<String[]> subVec = getSubAttruProp(
							factory, workshop, attruName, subAttru);
						if (subVec != null && subVec.size() > 0)
						{
							String subAttruPriority = subVec.get(0)[1];
							String subAttruBatch = subVec.get(0)[2];
							if (subAttru != null && subAttruPriority != null
								&& subAttruBatch != null)
							{
								String[] attruArray = new String[3];
								attruArray[0] = subAttru;
								attruArray[1] = subAttruPriority;
								attruArray[2] = subAttruBatch;
								typeAttruMap.put(
									subAttru, attruArray);
								typeMap.put(
									type, subAttru);
								categoryMap.put(
									Integer.parseInt(subAttruPriority), subAttru);
								subAttruVec.add(subAttru);
							}
						}
						
					}
					if (tempList != null && tempList.size() > 0)
					{
						for (int i = 0; subAttruVec != null && i < subAttruVec.size(); i++)
						{
							String subAttru = subAttruVec.get(i);
							Vector<PlanScheduleVO> typel = new Vector<PlanScheduleVO>();
							for (int j = 0; j < tempList.size(); j++)
							{
								PlanScheduleVO planScheduleVO = tempList.get(j);
								String vehicleType = planScheduleVO.getVehicletype();
								String sub = (String) typeMap.get(vehicleType);
								if (sub != null && sub.equals(subAttru))
								{
									typel.add(planScheduleVO);
								}

							}
							typeVOMap.put(
								subAttru, typel);
							typeSizeMap.put(
								subAttru, typel.size());
						}
					}
					tempList = runSeq(categoryMap, tempList, typeVOMap, typeAttruMap, typeSizeMap, typeMap, workshop);

				}
			}
		}
		return tempList;
	}
	 
	public Vector<PlanScheduleVO> runSeq(TreeMap categoryMap, Vector<PlanScheduleVO> tempList,
		HashMap<String, Vector<PlanScheduleVO>> typeVOMap, HashMap<String, String[]> typeAttruMap,
		HashMap<String, Integer> typeSizeMap, HashMap typeMap, String workshop)
	 {
			TreeMap<Float, PlanScheduleVO> pointMap = new TreeMap<Float, PlanScheduleVO>();
			Vector<PlanScheduleVO> newTempList = new Vector<PlanScheduleVO>();
			if (categoryMap != null)
			{
				Set setType = categoryMap.keySet();
				if (setType != null && setType.size() > 0)
				{
					Iterator itType = setType.iterator();
					tempList = new Vector<PlanScheduleVO>();
					while (itType.hasNext())
					{
						int keyPrio = (int) itType.next();
						String type = (String) categoryMap.get(keyPrio);
						Vector<PlanScheduleVO> vecVO = typeVOMap.get(type);
						String[] attruArray = (String[]) typeAttruMap.get(type);
						for (int i = 0; i < vecVO.size(); i++)
						{
							PlanScheduleVO vo = (PlanScheduleVO) vecVO.get(i);
							int batch = Integer.parseInt(attruArray[2]);
							int total = typeSizeMap.get(type);
							int no = total / batch + 1;
							vo.setBatchNo(i / batch + 1);
							vo.setTotalBatchNo(no);
							tempList.add(vo);
							System.out.println("type: " + type +
								 "  Value:  " + vo.getCsn());
						}
						 
						// System.out.println("Key: " + key +
						// "  Value:" + map.get(key));
					}

					for (int i = 0; tempList != null && i < tempList.size(); i++)
					{
						PlanScheduleVO planScheduleVO = tempList.get(i);
//						System.out.println("i: " + i +
//							 "  Value:  " + planScheduleVO.getCsn());
						String type = planScheduleVO.getVehicletype();
						String subAttru = (String) typeMap.get(type);
						String[] attruArray = (String[]) typeAttruMap.get(subAttru);
						int batch = Integer.parseInt(attruArray[2]);
						int seq = i + 1;
						int currntBatchNo = (int) planScheduleVO.getBatchNo();
						int totalNo = (int) planScheduleVO.getTotalBatchNo();
						float point = ((999999 / (totalNo + 1)) * currntBatchNo) + seq;
						// System.out.println("type: " + type +
						// " pointKey: " + point
						// + " NUM:  " + seq +
						// " batch: "+batch+" currntBatchNo:  " +
						// currntBatchNo);
						pointMap.put(
							point, planScheduleVO);
					}

					if (pointMap != null)
					{
						Set set = pointMap.keySet();
						if (set != null)
						{
							Iterator it = set.iterator();
							int num = 1;
//							tempList = new Vector<PlanScheduleVO>();
							while (it.hasNext())
							{
								float key = (float) it.next();
								PlanScheduleVO vo = (PlanScheduleVO) pointMap.get(key);
								vo.setSeq(num);
								String completeTime = vo.getCompletetime();
								Time starttime = new Time(completeTime);
								starttime = starttime.addHours(8);
								if("AB".equals(workshop))
								{
									starttime = starttime.addSeconds(50*num);
									vo.setAbOffline(starttime.toString());
								}
								else if("PB".equals(workshop))
								{
									starttime = starttime.addSeconds(70 * num);
									vo.setPbOffline(starttime.toString());
								}
								else if("WB".equals(workshop))
								{
									starttime = starttime.addSeconds(70 * num);
									vo.setWbOffline(starttime.toString());
								}
								// tempList = new
								// Vector<PlanScheduleVO>();
								newTempList.add(vo);
								// vos.add(vo);
								
								num++;
								System.out.println("WorkShop: "+workshop+ "  Key: "+key+  " NO: "+ num + "  CSN:"
									+ vo.getCsn());
								// System.out.println("Key: " + key
								// + "  Value:" + map.get(key));
							}
						}

					}
				}

			}
			if(newTempList!=null && newTempList.size()>0)
			{
				return newTempList;
			}
			else
			{
				return tempList;
			}			
	 }
//	 public Response saveDefect(QMDefectVO defectInfo) throws MESException
//	 {
//		 UTDefinition  atDefinition = getFunctions().getATDefinition("defect_car");
//		 UTRow atRow = atDefinition.createUTRow_();
//		 atRow.setValue("VIN_NUM", defectInfo.getVechileNum());
//		 atRow.setValue("vin", defectInfo.getVin());
//		 atRow.setValue("input_user", defectInfo.getInputUser());
//		 atRow.setValue("shift", defectInfo.getShift());
//		 atRow.setValue("orderNum", defectInfo.getOrderNum());
//		 atRow.setValue("is_access", defectInfo.getIsAccess());
//		 atRow.setValue("checkDate", defectInfo.getCheckDate());
//		 atRow.setValue("memo", defectInfo.getMemo());
//		 
//		 atRow.setValue("part_level1", defectInfo.getLevel1());
//		 atRow.setValue("part_level2", defectInfo.getLevel2());
//		 atRow.setValue("part_level3", defectInfo.getLevel3());
//		 atRow.setValue("part_level4", defectInfo.getLevel4());
//		 atRow.setValue("part_level5", defectInfo.getLevel5());
//		 
//		 atRow.setValue("level1", defectInfo.getCode1());
//		 atRow.setValue("level2", defectInfo.getCode2());
//		 
//		 atRow.setValue("defect_level", defectInfo.getDefectLevel());
//		 atRow.setValue("responsible_party", defectInfo.getResponsible());
//		 
//		 Response r = atRow.save( getFunctions().getDBTime(), " ", null);
//		 
//		 return r;
//	 }
//	 
//	 public String getCurrentCSN(String currentVIN) throws MESException
//	 {
//		 String sql = "select csn_S from AT_Vechile_Queue where vin_S = '"+currentVIN+"'";
//		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
//		 if(vec!=null && vec.size()>0)
//		 {
//			 String currentCSN = vec.get(0)[0];
//			 return currentCSN;
//		 }
//		 return "";
//	 }
//	 
//	 public String getPrevVIN(String currentVIN) throws MESException
//	 {
//
//		String currentCSN = getCurrentCSN(currentVIN);
//	    String sqlStatem = "select Top 1 csn_S, vin_S from AT_Vechile_Queue where cast(csn_S as int) < cast('"+currentCSN+"' as int) order by cast(csn_S as int) desc";
//		Vector<String[]> vecRes =   getFunctions().getArrayDataFromActive(sqlStatem, timeout);
//		if(vecRes!=null && vecRes.size()>0)
//		{
//			return vecRes.get(0)[1];
//		}
//
//		return "";
//
//	 }
//	 
//	 public String getNextVIN(String currentVIN) throws MESException
//	 {
//
//		String currentCSN = getCurrentCSN(currentVIN);
//	    String sqlStatem = "select Top 1 csn_S, vin_S from AT_Vechile_Queue where cast(csn_S as int) > cast('"+currentCSN+"' as int) order by cast(csn_S as int)";
//		Vector<String[]> vecRes =   getFunctions().getArrayDataFromActive(sqlStatem, timeout);
//		if(vecRes!=null && vecRes.size()>0)
//		{
//			return vecRes.get(0)[1];
//		}
//		return "";
//
//	 }
	 
	 public static void main(String[] args)
	 {
//		 TreeMap map = new TreeMap();
//		 map.put("1", "yi"); 
//		 map.put("8", "ba"); 
//		 map.put("9", "jiu"); 
//		 map.put("7", "qi"); 
//		 map.put("5", "wu"); 
//		 map.put("6", "liu"); 
//		 map.put("4", "si"); 
//		 map.put("3", "san"); 
//		 map.put("2", "er"); 
//		 Set set = map.keySet();
//		 Iterator it= set.iterator();
//		 while(it.hasNext())
//		 {
//			 String key = (String) it.next();
//			 
//			 System.out.println("Key: "+key+"  Value:"+map.get(key));
//		 }
		 String cTime = "2016/12/28 09:12:33";
		 Time ctimeT = new Time(cTime);
		 ctimeT = ctimeT.addSeconds(50);
		 System.out.println("ctimeT: "+ ctimeT.toString());
	 }
	 
}
