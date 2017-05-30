package com.mes.webclient.app.demo.api;

import java.util.HashMap;

import com.mes.compatibility.client.Order;
import com.mes.webclient.constants.FunctionConstants;
import com.mes.webclient.service.impl.IMService;
import com.mes.webclient.util.PrintChineseByBmp;

public class CopyOfPrinterBarCodeServiceImpl implements PrinterBarCodeService {

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
			String carTypeDesc = order.getUDA("car_type_desc") == null? "" : order.getUDA("car_type_desc").toString();
			String color  = order.getUDA("color") == null? "":  order.getUDA("color")  .toString() ;
			String carProject = order.getUDA("car_project") == null ? "" : order.getUDA("car_project").toString();
		    PrintChineseByBmp obj = new PrintChineseByBmp();
		        
		        obj.printCN( carTypeDesc ,30,30,230);
		        obj.printCN(color ,30,300,300);
		        obj.printCN(carProject ,30,30,300);
		        String  vinBarCode= "^FO30,30^BCN,120,N,N,N^BY2^FD>:"+vin+"^FS^FO60,165^ADN,36,20^FD"+vin+"^FS" ;
		        String command = obj.getCommand(vinBarCode);
		        String printerName = "ZDesigner 105SL 203DPI";
		        obj.runPrint(command ,printerName);    //打印条码 
		        System.out.println(command);
		 
			
		}
		catch (Exception ex)
		{
			
			ex.printStackTrace();
		}
	
			
		
		return null;
	}

}
