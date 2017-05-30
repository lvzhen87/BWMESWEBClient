package com.mes.webclient.printImpl;

import java.util.HashMap;

import net.sf.json.JSONArray;

import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.Report;
import com.mes.compatibility.client.Response;
import com.mes.operationalreporting.ReportDataSet;
import com.mes.webclient.app.demo.api.TransferObjectAPI;
import com.mes.webclient.controller.vo.CertPrintFuelVO;
import com.mes.webclient.print.BasicPrint;
import com.mes.webclient.print.FuelPrint;
import com.mes.webclient.service.impl.BaseService;
import com.mes.webclient.util.StringUtil;

public class FuelPrintImpl  extends BasicPrint implements FuelPrint {
	public String PrintFuel(CertPrintFuelVO fuelVO,String printer,String reportName,String reportVerson)
	{
		if(StringUtil.isNull(printer)){
			return "请选择打印机";
		}
		ReportDataSet reportDataSet = TransferObjectAPI.ObjectToReportData(fuelVO, null);
		//reportDataSet.appendRow(reportData);
		try{
			Report report = getFunctions().createReport(reportName, reportVerson);
			report.setReportDataSet(reportDataSet);
			Response r = report.generate();
			if(r.isError()){
				return r.getFirstErrorMessage();
			}
			
			print(report, printer);

//			report = imService.getFunctions().createReport("轻型汽油车环保信息随车清单", "2");
//			report.setReportDataSet(reportDataSet);
//			r = report.generate();
//			if(r.isError()){
//				return r.getFirstErrorMessage();
//			}
//			export(report, vin);
//			return JSONArray.fromObject(generateResponseMap()).toString();
			return "OK";
		}
		catch(Exception e){
			return "打印VIN【"+fuelVO.getRecordnum()+"】燃油证书失败:"+e.toString();
		}
	}
}
