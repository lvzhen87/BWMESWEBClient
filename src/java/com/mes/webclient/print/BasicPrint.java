package com.mes.webclient.print;

import com.mes.compatibility.client.Report;
import com.mes.webclient.service.impl.BaseService;

public class BasicPrint extends BaseService {
	
	public void print(Report report, String printerName){
		report.print(printerName, 1, false);
	}
}
