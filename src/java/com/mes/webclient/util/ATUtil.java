package com.mes.webclient.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.DsList;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.webclient.controller.vo.BaseVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.service.impl.IMService;

public class ATUtil {

	 
	public static Object getATObject(Class clazz,UTRow atRow)throws Exception{
		Field[] fs = clazz.getDeclaredFields();
		Object o = new Object();
		o = clazz.newInstance();
		for(int i=0;i<fs.length;i++){
			String atColName =  fs[i].getName() ;
			if( atColName != "vin" && atColName.lastIndexOf("v_")==-1){
				fs[i].setAccessible(true);
				
				if(atRow.getValue(fs[i].getName())==null||("null").equals(atRow.getValue(fs[i].getName()))){
					fs[i].set(o, "");
				}else{
					fs[i].set(o, atRow.getValue(fs[i].getName()));
				}
			}
		}		 
		return o;
	}
	
	public static Object getATrow(Class clazz,UTRow atRow,BaseVO vo)throws Exception{
		
		 
		Field[] fs = clazz.getDeclaredFields();
		Object o = new Object();
		//o = clazz.newInstance();
		for(int i=0;i<fs.length;i++){
			if(fs[i].getName().lastIndexOf("v_")==-1){
				HttpServletRequest h;

				fs[i].setAccessible(true);
				if(atRow.getValue(fs[i].getName())==null||("null").equals(atRow.getValue(fs[i].getName()))){
					fs[i].set(o, "");
				}else{
					fs[i].set(o, atRow.getValue(fs[i].getName()));
				}
			}
		}		 
		return o;
	}
	
	public static HashMap<String, String> getListHashMap(String listName)
	{
		HashMap<String, String> hsList = new HashMap<String, String>();
		IMService imService = new IMService();
		String listItem = "";
		String[] itemArray ;
		try {
			DsList list =imService.getDsListByName(listName);
			Vector vector = list.getValues();
			for(int i =0;i<vector.size();i++)
			{
				listItem = vector.get(i).toString();
				if(StringUtil.isNull(listItem))
				{
					continue;
				}
				itemArray = listItem.split(" ");
				if(itemArray.length==2)
				{
					hsList.put(itemArray[0], itemArray[1]);
				}
			}

			return hsList;
		} catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
