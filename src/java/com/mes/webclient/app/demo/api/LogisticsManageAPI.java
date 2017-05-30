package com.mes.webclient.app.demo.api;

import java.util.HashMap;
import java.util.Vector;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTDefinition;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.ui.Time;
import com.mes.shopflow.common.constants.filtering.IATRowFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.webclient.controller.vo.DeliverInfoVO;
import com.mes.webclient.controller.vo.LogisticsMaindataVO;
import com.mes.webclient.controller.vo.ReceiveInfoVO;
import com.mes.webclient.service.impl.BaseService;
import com.mes.webclient.service.impl.IMService;



public class LogisticsManageAPI extends BaseService {
	
	private int timeout = 30000;
	IMService imService;

	//物料主数据查询
	 public Vector<String[]> getConfigures(String part_type, String part_name) throws MESException
	 {
		 String sql = "select atr_key, part_name_S, pack_code_S, part_no_S,pack_size_S,part_type_S from UD_maindata_part where 1=1";
		 if(part_type!=null && !"".equals(part_type))
		 {
			 sql = sql + " and part_type_S like '%"+part_type+"%'";
		 }
		 if(part_name!=null && !"".equals(part_name))
		 {
			 sql = sql + " and part_name_S like '%"+part_name+"%'";
		 }
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public Vector<String[]> getRDCDeliverConfigures(String bill_no, String part_no) throws MESException
	 {
		 String sql = "select atr_key, bill_no_S, part_no_S,part_name_S,part_type_S,batch_date_S ,seq_no_S ,supplier_S ,batch_no_I ,operator_S , deliver_time_S from UD_DeliverInfo where 1=1";
		 if(bill_no!=null && !"".equals(bill_no))
		 {
			 sql = sql + " and bill_no_S like '%"+bill_no+"%'";
		 }
		 if(part_no!=null && !"".equals(part_no))
		 {
			 sql = sql + " and part_no_S like '%"+part_no+"%'";
		 }
		 
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 //rdcReceicve sql
	 public Vector<String[]> getRDCReceiveConfigures(String bill_no, String part_no) throws MESException
	 {
		 String sql = "select atr_key, bill_no_S, part_no_S,part_name_S,part_type_S,batch_date_S ,seq_no_S ,supplier_S ,batch_no_I ,operator_S , receive_time_S from UD_ReceiveInfo where 1=1";
		 if(bill_no!=null && !"".equals(bill_no))
		 {
			 sql = sql + " and bill_no_S like '%"+bill_no+"%'";
		 }
		 if(part_no!=null && !"".equals(part_no))
		 {
			 sql = sql + " and part_no_S like '%"+part_no+"%'";
		 }
		 
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	
	 public Vector<String[]> getInstoreData(String part_name, String part_type, String part_no) throws MESException
	 {
		 String sql = "select t1.atr_key, t2.part_no_S, t2.part_name_S, t2.part_type_S, t1.num_I from UD_InstoreStatics t1 left join UD_maindata_part t2 on t1.part_key_I = t2.atr_key";
		 if(part_type!=null && !"".equals(part_type))
		 {
			 sql = sql + " and t2.part_type_S like '%"+part_type+"%'";
		 }
		 if(part_name!=null && !"".equals(part_name))
		 {
			 sql = sql + " and t2.part_name_S like '%"+part_name+"%'";
		 }
		 if(part_no!=null && !"".equals(part_no))
		 {
			 sql = sql + " and t2.part_no_S like '%"+part_no+"%'";
		 }
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	//保存API
	public Response saveMainData(LogisticsMaindataVO maindataVO) throws MESException
	{
		long key = maindataVO.getKey();
		UTRow utRow;
		if(key > 0)
		{
			utRow = getRowByKey(key, "maindata_part");
		}
		else
		{
			 UTDefinition  atDefinition = getFunctions().getATDefinition("maindata_part");
			 utRow = atDefinition.createUTRow_();
		}
		
		
		utRow.setValue("part_name", maindataVO.getPart_name());
		utRow.setValue("part_no", maindataVO.getPart_no());
		utRow.setValue("pack_size", maindataVO.getPack_size());
		utRow.setValue("part_type", maindataVO.getPart_type());
		utRow.setValue("pack_code", maindataVO.getPack_code());
		Response r = utRow.save( getFunctions().getDBTime(), " ", null);
//		 UTRowFilter filter =  getFunctions().createATRowFilter(name)
//		 getFunctions().getFilteredATRows(atRowFilter)
		return r;
	}

	//添加或编辑
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
	 
	 public Time getDBTime() throws MESException
	 {
		 return  getFunctions().getDBTime();
	 }
	 public UTRow createUTRow(String name) throws MESException
	 {
		 UTDefinition utDefinition =   getFunctions().getATDefinition(name);
		 return utDefinition.createUTRow_();
	 }

		//保存发货信息
	 public Response saveDeliverInfoData(DeliverInfoVO dInfoVO) throws MESException
	 {
		long key = dInfoVO.getKey();
		UTRow utRow;
		if(key > 0)
		{
			utRow = getRowByKey(key, "DeliverInfo");
		}
		else
		{
			UTDefinition  atDefinition = getFunctions().getATDefinition("DeliverInfo");
			utRow = atDefinition.createUTRow_();
		}
			
		utRow.setValue("bill_no", dInfoVO.getBill_no());
		utRow.setValue("part_no", dInfoVO.getPart_no());
		utRow.setValue("part_name", dInfoVO.getPart_name());
		utRow.setValue("part_type", dInfoVO.getPart_type());
		utRow.setValue("batch_date", dInfoVO.getBatch_date());
		utRow.setValue("seq_no", dInfoVO.getSeq_no());
		utRow.setValue("supplier", dInfoVO.getSupplier());
		utRow.setValue("batch_no", dInfoVO.getBatch_no());
		utRow.setValue("operator", dInfoVO.getOperator());
		utRow.setValue("deliver_time", dInfoVO.getDeliver_time());
		utRow.setValue("color", dInfoVO.getColor());
	
		Response r = utRow.save( getFunctions().getDBTime(), " ", null);
		return r;
	 }
	 /*
	  * 保存收货信息
	  */
	 public Response saveReceiveInfoData(ReceiveInfoVO rInfoVO) throws MESException
	 {
		long key = rInfoVO.getKey();
		UTRow utRow;
		if(key > 0)
		{
			utRow = getRowByKey(key, "ReceiveInfo");
		}
		else
		{
			UTDefinition  atDefinition = getFunctions().getATDefinition("ReceiveInfo");
			utRow = atDefinition.createUTRow_();
		}
			
		utRow.setValue("bill_no", rInfoVO.getBill_no());
		utRow.setValue("part_no", rInfoVO.getPart_no());
		utRow.setValue("part_name", rInfoVO.getPart_name());
		utRow.setValue("part_type", rInfoVO.getPart_type());
		utRow.setValue("batch_date", rInfoVO.getBatch_date());
		utRow.setValue("seq_no", rInfoVO.getSeq_no());
		utRow.setValue("supplier", rInfoVO.getSupplier());
		utRow.setValue("batch_no", rInfoVO.getBatch_no());
		utRow.setValue("operator", rInfoVO.getOperator());
		utRow.setValue("receive_time", rInfoVO.getReceive_time());
		utRow.setValue("color", rInfoVO.getColor());
	
		Response r = utRow.save( getFunctions().getDBTime(), " ", null);
		return r;
	 }
	 
	 /*
//	  * 保存收货信息
//	  */
//	 public Vector<String[]> getInstoreData(ReceiveInfoVO rInfoVO) throws MESException
//	 {
//		long key = rInfoVO.getKey();
//		UTRow utRow;
//		if(key > 0)
//		{
//			utRow = getRowByKey(key, "ReceiveInfo");
//		}
//		else
//		{
//			UTDefinition  atDefinition = getFunctions().getATDefinition("ReceiveInfo");
//			utRow = atDefinition.createUTRow_();
//		}
//			
//		utRow.setValue("bill_no", rInfoVO.getBill_no());
//		utRow.setValue("part_no", rInfoVO.getPart_no());
//		utRow.setValue("part_name", rInfoVO.getPart_name());
//		utRow.setValue("part_type", rInfoVO.getPart_type());
//		utRow.setValue("batch_date", rInfoVO.getBatch_date());
//		utRow.setValue("seq_no", rInfoVO.getSeq_no());
//		utRow.setValue("supplier", rInfoVO.getSupplier());
//		utRow.setValue("batch_no", rInfoVO.getBatch_no());
//		utRow.setValue("operator", rInfoVO.getOperator());
//		utRow.setValue("receive_time", rInfoVO.getReceive_time());
//	
//		Response r = utRow.save( getFunctions().getDBTime(), " ", null);
//		return r;
//	 }
	 

	 public String addInstore(String partNO, int num) throws MESException
	 {
		 UTRow utRow;
		 Response r = null ;
		 long partKey = getPartKeyByNo(partNO);
		 if(partKey > 0)
		 {
			long currentNum = 0;
			long atrKey = getInstoreDatabyPartkey(partKey);
			if (atrKey > 0)
			{
				utRow = getRowByKey(
					atrKey, "InstoreStatics");
				currentNum = (long)utRow.getValue("num");
			}
			else
			{
				UTDefinition atDefinition = getFunctions().getATDefinition(
					"InstoreStatics");
				utRow = atDefinition.createUTRow_();
			}

			utRow.setValue("part_key", partKey);
			utRow.setValue("num", num + currentNum);
			

			r = utRow.save(getFunctions().getDBTime(), " ", null);
			if(r.isOk())
			{
				return "Success";
			}
			else
			{
				return "数据库库存保存失败";
			}			

		 }
		 else
		 {
			 return "零件编号 "+partNO+" 没有维护基础数据";
		 }
	 }
	 
	 public String reduceInstore(String partNO, int num) throws MESException
	 {		 
		 UTRow utRow;
		 Response r = null ;
		 long partKey = getPartKeyByNo(partNO);
		 if(partKey > 0)
		 {
			long currentNum = 0;
			long atrKey = getInstoreDatabyPartkey(partKey);
			if (atrKey > 0)
			{
				utRow = getRowByKey(
					atrKey, "InstoreStatics");
				currentNum = (long)utRow.getValue("num");
				if(currentNum > num)
				{
					utRow.setValue("part_key", partKey);
					utRow.setValue("num", currentNum - num);
					r = utRow.save(getFunctions().getDBTime(), " ", null);
					if(r.isOk())
					{
						return "Success";
					}
					else
					{
						return "数据库库存保存失败";
					}
					
				}
				else
				{
					return "零件编号 "+partNO+" 库存不足";
				}
				
			}
			else
			{
				return "零件编号 "+partNO+" 没有库存";
			}

		 }
		 else
		 {
			 return "零件编号 "+partNO+" 没有维护基础数据";
		 }

	 }
	 
	 public long getPartKeyByNo(String partNO) throws MESException
	 {
		 long partKey = -1; 
		 String sql = "select atr_key from UD_maindata_part where part_no_S = '"+partNO+"'";
		 Vector<String[]> vec =  getFunctions().getArrayDataFromActive(sql, timeout);
		 if(vec!=null && vec.size()>0)
		 {
			 partKey = Long.parseLong(vec.get(0)[0]);
		 }
		 return partKey;
	 }
	 
	 public long getInstoreDatabyPartkey(long key) throws MESException
	 {
		 long atr_key = -1;
		 String sql = "select atr_key from UD_InstoreStatics where part_key_I = " + key;
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 if(vec!=null && vec.size()>0)
		 {
			 atr_key  = Long.parseLong(vec.get(0)[0]);
		 }
		 return atr_key;
	 }
	 
	 public Vector<String[]> getPartInfo(String partNO) throws MESException
	 {
		 String sql = "select part_name_S, part_type_S, pack_size_S, pack_code_S from UD_maindata_part where part_no_S = '"+partNO+"'";
		 Vector<String[]> vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		 return vec;
	 }
	 
	 public HashMap parseBillNO(String bill_no) throws MESException
	 {
		 HashMap dataMap = new HashMap();
		 if(bill_no!=null && bill_no!="")
		 {
			 
			 String part_no = bill_no.substring(0, 8);
			 String supplier = bill_no.substring(11, 16);
			 String batch_date = bill_no.substring(16, 20);
			 String seq_no = bill_no.substring(20, bill_no.length());
			 String color = bill_no.substring(8, 11);
			 dataMap.put("part_no", part_no);
			 dataMap.put("supplier", supplier);
			 dataMap.put("batch_date", batch_date);
			 dataMap.put("seq_no", seq_no);
			 dataMap.put("color", color);
			 //10005566 000 12345 7C25 0000
			 //10005566000123457C250000
		//C10005566107601407230003
				
			 Vector<String[]> partInfo = getPartInfo(part_no);
			 if(partInfo!=null && partInfo.size()>0)
			 {
				 String[] partInfoArr = partInfo.get(0);
				 String part_name = partInfoArr[0];
				 String part_type = partInfoArr[1];
				 String pack_size = partInfoArr[2];
				 String pack_code = partInfoArr[3];
				 
				 dataMap.put("part_name", part_name);
				 dataMap.put("part_type", part_type);
				 dataMap.put("pack_size", pack_size);
				 dataMap.put("pack_code", pack_code);
			 }
//			 System.out.println("part_no " + part_no);
//			 System.out.println("supplier " + supplier);
//			 System.out.println("batch_date " + batch_date);
//			 System.out.println("seq_no " + seq_no);
		 }
		 
		return dataMap;
		 
	 }
	 
//	 public static void main(String[] args)
//	 {
//		 LogisticsManageAPI api = new LogisticsManageAPI();
//		 String bill_no = "C10005566107601407230003";
//		 api.parseBillNO(bill_no);
//			
//	 }
	
}


