package com.mes.webclient.uploadImpl;

import java.util.ArrayList;

import info.catarc.tsoap.ArrayOfRllxParamEntity;
import info.catarc.tsoap.ArrayOfVehicleBasicInfo;
import info.catarc.tsoap.FuelDataSysSTDTest;
import info.catarc.tsoap.FuelDataSysSTDTestSoap;
import info.catarc.tsoap.RllxParam;
import info.catarc.tsoap.RllxParamEntity;
import info.catarc.tsoap.VehicleBasicInfo;

import com.mes.webclient.controller.vo.CertPrintFuelVO;
import com.mes.webclient.upload.FuelUpload;

public class FuelUploadImpl implements FuelUpload{
	public String uploadTest(String userName,String password,ArrayList<CertPrintFuelVO> volist,String oKey)
	{
		ArrayOfVehicleBasicInfo vehicleInfoList = null ;
		VehicleBasicInfo vbi = new VehicleBasicInfo();
		RllxParam rl = new RllxParam();
		ArrayOfRllxParamEntity arrayrpe =null;
		RllxParamEntity rpe = new RllxParamEntity();
		rpe.setVin(null);
		rpe.setParamCode(null);
		rpe.setParamValue(null);
		arrayrpe.getRllxParamEntity().add(rpe);
		for (CertPrintFuelVO vo : volist) {
			vbi.setClxh(vo.getCartype());
			vbi.setAppVin(vo.getRecordnum());
			vbi.setClzl(null);
			vbi.setClzzrq(null);
			vbi.setCocNo(null);
			vbi.setEdzk(null);
			vbi.setHgNo(null);
			vbi.setHgspbm(null);

		}

		vbi.setEntityList(arrayrpe);
		FuelDataSysSTDTest ft = new FuelDataSysSTDTest();
		FuelDataSysSTDTestSoap fs = ft.getFuelDataSysSTDTestSoap();
		vehicleInfoList.getVehicleBasicInfo().add(vbi);
		fs.uploadFuelData(userName, password, vehicleInfoList, oKey);
		return null;
	}
}
