package com.mes.webclient.app.demo.api;

import java.util.HashMap;
import java.util.Vector;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import com.mes.compatibility.client.Order;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.webclient.constants.FunctionConstants;
import com.mes.webclient.service.impl.IMService;

public class EleUploadServiceImpl implements PassStationService {

	@Override
	public String ExecPassStationFunction(HashMap<String, Object> hsmap) {
		// TODO Auto-generated method stub
		try
		{
			IMService imservice = new IMService();
			String vin = hsmap.get(FunctionConstants.PARAMETER_TYPE_VIN).toString();
			Order order = imservice.getOrderByName(vin);
			String station = hsmap.get(FunctionConstants.PARAMETER_TYPE_STATION).toString();
			String entryTime = hsmap.get(FunctionConstants.PARAMETER_TYPE_ENTRYTIME).toString();
			String stage = hsmap.get(FunctionConstants.PARAMETER_TYPE_STAGE).toString();
			String[] sqls =  new String[1] ;
			String userName = hsmap.get(FunctionConstants.USER_NAME).toString() ;
			sqls [0] = "update ele_order set  STATION = '"+station+"' , ENTRY_TIME = '"+entryTime+"' , SCAN_USER ='"+userName+"' where ele_id = '"+vin+"' ";
			imservice.getFunctions().executeStatements(sqls);
			 
			
		}
		catch (Exception ex)
		{
			
			ex.printStackTrace();
		}
	
			
		
		return null;
	}

}
