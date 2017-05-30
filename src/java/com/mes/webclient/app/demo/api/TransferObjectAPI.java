package com.mes.webclient.app.demo.api;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.mes.compatibility.client.UTRow;
import com.mes.operationalreporting.ReportDataSet;

public class TransferObjectAPI {
	public static <T> ReportDataSet ObjectToReportData(T reportObject,HashMap<String, String> nameMap)
	{
		try {
			ReportDataSet reportDataSet = new ReportDataSet();
	        Class c = reportObject.getClass();
	        Field[] fields = c.getFields();
	        HashMap<String, String> hashmap = new HashMap<String, String>();
	        for (Field field : fields) {
				if(nameMap.containsKey(field))
				{
					hashmap.put(nameMap.get(field), field.get(reportObject).toString());
				}
			}
	        reportDataSet.appendRow(hashmap); 
			return reportDataSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static <T> T UTrowToObject (UTRow utRow,Class<T> clazz)
	{
		T object;
		try {
			object = clazz.newInstance();
			
			Class c = clazz.getClass();
			
			Field[] fields = c.getFields();
			
			for (Field field : fields) {
				if(field.getName().equals("Recordnum"))
				{
//					utRow.setValue("vin", field.get(object));
					field.set(object, utRow.getValue("vin").toString());
				}
				else
				{
					field.set(object, utRow.getValue(field.getName()).toString());
//					utRow.setValue(field.getName(), field.get(object));
				}
				
			}
			return object;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	public static <T> UTRow ObjectToUTRow (T object,UTRow utRow)
	{
		try {			
			Class c = object.getClass();
			
			Field[] fields = c.getFields();
			
			for (Field field : fields) {
//				field.set(object, utRow.getValue(field.getName()).toString());
				if(field.getName().equals("Recordnum"))
				{
					utRow.setValue("vin", field.get(object));
				}
				else
				{
					utRow.setValue(field.getName(), field.get(object));
				}
			}
			return utRow;
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
}
