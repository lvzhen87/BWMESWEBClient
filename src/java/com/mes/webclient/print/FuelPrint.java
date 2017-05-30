package com.mes.webclient.print;

import java.util.HashMap;

import com.mes.webclient.controller.vo.CertPrintFuelVO;

public interface FuelPrint {
	public String PrintFuel(CertPrintFuelVO fuelVO,String printer,String reportName,String reportVerson);
	
	
}
