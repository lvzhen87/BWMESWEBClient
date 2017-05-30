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

public class PassStationServiceImpl implements PassStationService {

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
			
			UTHandler athandler = imservice.getAtHandler("MES_PM_CarMove");
			UTRow utRow = null ;
			Vector<UTRow> vector = new Vector<UTRow>();
			Response response = null;
	//		UserTransaction userTransaction = imService.getUserTransaction();
	//		userTransaction.begin();
			 utRow = athandler.createATRow();
			 utRow.setValue("vin", order.getUDA("vin"));
			 System.out.println( "planID = "+order.getUDA("plan_id") );
			 utRow.setValue("order_number", order.getUDA("plan_id"));
			 utRow.setValue("entry_time",hsmap.get("entryTime").toString());
			 utRow.setValue("car_type", order.getUDA("car_type"));
			 utRow.setValue("color", order.getUDA("color"));
			 utRow.setValue("vehicle_configuration", order.getUDA("vehicle_configuration"));
			 utRow.setValue("engine_type", order.getUDA("engine_type"));
			 utRow.setValue("special_order", order.getUDA("special_order"));
			 if(order.getUDA("csn") == null|| order.getUDA("csn").equals("") )
			 {
				 utRow.setValue("csn", "001");
			 }
			 else
			 {
				 utRow.setValue("csn", order.getUDA("csn"));
			 }
			 utRow.setValue("station", hsmap.get(FunctionConstants.PARAMETER_TYPE_STATION).toString());
			 utRow.setValue("username", hsmap.get(FunctionConstants.USER_NAME).toString());
			 response = athandler.save();
			if (response != null &&response.isError())
			{

				return response.getFirstErrorMessage();
				//throw new Exception(response.getFirstErrorMessage());
			}
			order.setUDA(hsmap.get("entryTime").toString(),"entry_time");
			order.setUDA(hsmap.get(FunctionConstants.PARAMETER_TYPE_STATION).toString(),"station");
			order.setUDA(hsmap.get(FunctionConstants.USER_NAME).toString(),"username");
			
			order.save();
			
		}
		catch (Exception ex)
		{
			
			ex.printStackTrace();
		}
	
			
		
		return null;
	}

}
