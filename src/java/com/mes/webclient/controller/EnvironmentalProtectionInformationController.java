/*
 * @(#) EnvironmentalProtectionInformationController.java 2016年12月16日 下午16:46:41
 *
 * Copyright 2016 CATARC, Inc. All rights reserved.
 * CATARC. Use is subject to license terms.
 */
package com.mes.webclient.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Vector;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.ATHandler;
import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.ATRowFilter;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Report;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.operationalreporting.ReportDataSet;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.service.impl.IMService;
import com.mes.webclient.util.DateTimeUtils;
import com.mes.webclient.util.StringUtil;
import com.mes.webclient.vcpus.EnvironmentProtectionInformation;

@Controller("EnvironmentalProtectionInformationController")
@RequestMapping("/EPI.sp")
public class EnvironmentalProtectionInformationController extends BaseController
{
	@Autowired
	IMService imService;
	
	/**
	 * 打开配置页面
	 */
	@RequestMapping(params = "toConfigPage")
	public String toConfigPage(){
		return "im/environmentalProtectionInformation/config";
	}
	
	/**
	 * 打开扫描页面
	 */
	@RequestMapping(params = "toPrintPage")
	public String toPrintPage(){
		return "im/environmentalProtectionInformation/print";
	}
	
	/**
	 * 保存配置
	 */
	@RequestMapping(params = "saveConfig")
	public @ResponseBody String saveConfig(HttpServletRequest request, HttpServletResponse response){
		String vehicleCategory = request.getParameter("vehicle_category_para");
		if(StringUtil.isNull(vehicleCategory)){
			return JSONArray.fromObject(generateResponseMap("配置车型不能为空")).toString();
		}
		String publicationNumber = request.getParameter("publication_number");
		if(StringUtil.isNull(publicationNumber)){
			return JSONArray.fromObject(generateResponseMap("型式核准号不能为空")).toString();
		}
		String companyName = request.getParameter("company_name");
		if(StringUtil.isNull(companyName)){
			return JSONArray.fromObject(generateResponseMap("公司名称不能为空")).toString();
		}
		String carType = request.getParameter("car_type");
		if(StringUtil.isNull(carType)){
			return JSONArray.fromObject(generateResponseMap("车辆型号不能为空")).toString();
		}
		String trademark = request.getParameter("trademark");
		if(StringUtil.isNull(trademark)){
			return JSONArray.fromObject(generateResponseMap("商标不能为空")).toString();
		}
		String vehicleClassification = request.getParameter("vehicle_classification");
		if(StringUtil.isNull(vehicleClassification)){
			return JSONArray.fromObject(generateResponseMap("汽车分类不能为空")).toString();
		}
		String emissionPhase = request.getParameter("emission_phase");
		if(StringUtil.isNull(emissionPhase)){
			return JSONArray.fromObject(generateResponseMap("排放阶段不能为空")).toString();
		}
		String recognitionMethod = request.getParameter("recognition_method");
		if(StringUtil.isNull(recognitionMethod)){
			return JSONArray.fromObject(generateResponseMap("车型的识别方法不能为空")).toString();
		}
		String recognitionLocation = request.getParameter("recognition_location");
		if(StringUtil.isNull(recognitionLocation)){
			return JSONArray.fromObject(generateResponseMap("车型的识别位置不能为空")).toString();
		}
		String manufacturerName = request.getParameter("manufacturer_name");
		if(StringUtil.isNull(manufacturerName)){
			return JSONArray.fromObject(generateResponseMap("车辆制造商名称不能为空")).toString();
		}
		String factoryAddress = request.getParameter("factory_address");
		if(StringUtil.isNull(factoryAddress)){
			return JSONArray.fromObject(generateResponseMap("生产厂地址不能为空")).toString();
		}
		String inspectionInstitution1 = request.getParameter("inspection_institution_1");
		if(StringUtil.isNull(inspectionInstitution1)){
			return JSONArray.fromObject(generateResponseMap("检测机构不能为空")).toString();
		}
		String inspectionConclusion1 = request.getParameter("inspection_conclusion_1");
		if(StringUtil.isNull(inspectionConclusion1)){
			return JSONArray.fromObject(generateResponseMap("检测结论不能为空")).toString();
		}
		String inspectionInstitution2 = request.getParameter("inspection_institution_2");
		if(StringUtil.isNull(inspectionInstitution2)){
			return JSONArray.fromObject(generateResponseMap("检测机构不能为空")).toString();
		}
		String inspectionConclusion2 = request.getParameter("inspection_conclusion_2");
		if(StringUtil.isNull(inspectionConclusion2)){
			return JSONArray.fromObject(generateResponseMap("检测结论不能为空")).toString();
		}
		String inspectionInstitution3 = request.getParameter("inspection_institution_3");
		if(StringUtil.isNull(inspectionInstitution3)){
			return JSONArray.fromObject(generateResponseMap("检测机构不能为空")).toString();
		}
		String inspectionConclusion3 = request.getParameter("inspection_conclusion_3");
		if(StringUtil.isNull(inspectionConclusion3)){
			return JSONArray.fromObject(generateResponseMap("检测结论不能为空")).toString();
		}
		String inspectionInstitution4 = request.getParameter("inspection_institution_4");
		String inspectionConclusion4 = request.getParameter("inspection_conclusion_4");
		String factoryInspection = request.getParameter("factory_inspection");
		if(StringUtil.isNull(factoryInspection)){
			return JSONArray.fromObject(generateResponseMap("出厂检验项目及结论不能为空")).toString();
		}
		String engineType = request.getParameter("engine_type");
		if(StringUtil.isNull(engineType)){
			return JSONArray.fromObject(generateResponseMap("发动机型号不能为空")).toString();
		}
		String engineManufacturer = request.getParameter("engine_manufacturer");
		if(StringUtil.isNull(engineManufacturer)){
			return JSONArray.fromObject(generateResponseMap("发动机生产厂不能为空")).toString();
		}
		String catalyticConverterFrontModel = request.getParameter("cc_front_model");
		String catalyticConverterBackModel = request.getParameter("cc_back_model");
		String catalyticConverterFrontManufacturer = request.getParameter("cc_front_manufacturer");
		String catalyticConverterBackManufacturer = request.getParameter("cc_back_manufacturer");
		String coatingManufacturer = request.getParameter("coating_manufacturer");
		String carrierManufacturer = request.getParameter("carrier_manufacturer");
		String encapsulationManufacturer = request.getParameter("encapsulation_m");
		String evapModel = request.getParameter("evap_model");
		String evapManufacturer = request.getParameter("evap_manufacturer");
		String egosFrontModel = request.getParameter("egos_front_model");
		String egosBackModel = request.getParameter("egos_back_model");
		String egosFrontManufacturer = request.getParameter("egos_front_manufacturer");
		String egosBackManufacturer = request.getParameter("egos_back_manufacturer");
		String crankcaseEmissionControlModel = request.getParameter("ce_control_model");
		String crankcaseEmissionControlManufacturer = request.getParameter("ce_control_manufacturer");
		String egrModel = request.getParameter("egr_model");
		String egrManufacturer = request.getParameter("egr_manufacturer");
		String obdModel = request.getParameter("obd_model");
		String obdManufacturer = request.getParameter("obd_manufacturer");
		String iupr = request.getParameter("iupr");
		if(StringUtil.isNull(engineManufacturer)){
			return JSONArray.fromObject(generateResponseMap("IUPR监测功能不能为空")).toString();
		}
		String ecuModel = request.getParameter("ecu_model");
		String ecuVersion = request.getParameter("ecu_version");
		String ecuManufacturer = request.getParameter("ecu_manufacturer");
		String transmissionType = request.getParameter("transmission_type");
		String gear = request.getParameter("gear");
		String mufflerFrontModel = request.getParameter("muffler_front_model");
		String mufflerBackModel = request.getParameter("muffler_back_model");
		String mufflerFrontManufacturer = request.getParameter("muffler_front_m");
		String mufflerBackManufacturer = request.getParameter("muffler_back_m");
		String superchargerModel = request.getParameter("supercharger_model");
		String superchargerManufacturer = request.getParameter("supercharger_m");
		String intercooler = request.getParameter("intercooler");
		String legalRepresentative = request.getParameter("legal_representative");
		if(StringUtil.isNull(legalRepresentative)){
			return JSONArray.fromObject(generateResponseMap("法人代表不能为空")).toString();
		}
		String address = request.getParameter("address");
		if(StringUtil.isNull(address)){
			return JSONArray.fromObject(generateResponseMap("地址不能为空")).toString();
		}
		String telephoneNumber = request.getParameter("telephone_number");
		if(StringUtil.isNull(telephoneNumber)){
			return JSONArray.fromObject(generateResponseMap("联系电话不能为空")).toString();
		}
		String officialSite = request.getParameter("official_site");
		if(StringUtil.isNull(officialSite)){
			return JSONArray.fromObject(generateResponseMap("官方网站不能为空")).toString();
		}
		try{
			UTRowFilter filter = imService.getFunctions().createATRowFilter("MES_PR_EPI_Config");
			filter.forColumnNameEqualTo("vehicle_category", vehicleCategory);
			Vector<UTRow> vectorData = filter.exec();
			UTRow atRow = null;
			if(vectorData!=null && vectorData.size()>0){
				atRow = vectorData.elementAt(0);
			}
			else{
				UTHandler atHandler = imService.getFunctions().createATHandler("MES_PR_EPI_Config");
				atRow = atHandler.createATRow();
			}
			atRow.setValue("vehicle_category", vehicleCategory);
			atRow.setValue("publication_number", publicationNumber);
			atRow.setValue("company_name", companyName);
			atRow.setValue("car_type", carType);
			atRow.setValue("trademark", trademark);
			atRow.setValue("vehicle_classification", vehicleClassification);
			atRow.setValue("emission_phase", emissionPhase);
			atRow.setValue("recognition_method", recognitionMethod);
			atRow.setValue("recognition_location", recognitionLocation);
			atRow.setValue("manufacturer_name", manufacturerName);
			atRow.setValue("factory_address", factoryAddress);
			atRow.setValue("inspection_institution_1", inspectionInstitution1);
			atRow.setValue("inspection_conclusion_1", inspectionConclusion1);
			atRow.setValue("inspection_institution_2", inspectionInstitution2);
			atRow.setValue("inspection_conclusion_2", inspectionConclusion2);
			atRow.setValue("inspection_institution_3", inspectionInstitution3);
			atRow.setValue("inspection_conclusion_3", inspectionConclusion3);
			atRow.setValue("inspection_institution_4", inspectionInstitution4);
			atRow.setValue("inspection_conclusion_4", inspectionConclusion4);
			atRow.setValue("factory_inspection", factoryInspection);
			atRow.setValue("engine_type", engineType);
			atRow.setValue("engine_manufacturer", engineManufacturer);
			atRow.setValue("cc_front_model", catalyticConverterFrontModel);
			atRow.setValue("cc_back_model", catalyticConverterBackModel);
			atRow.setValue("cc_front_manufacturer", catalyticConverterFrontManufacturer);
			atRow.setValue("cc_back_manufacturer", catalyticConverterBackManufacturer);
			atRow.setValue("coating_manufacturer", coatingManufacturer);
			atRow.setValue("carrier_manufacturer", carrierManufacturer);
			atRow.setValue("encapsulation_m", encapsulationManufacturer);
			atRow.setValue("evap_model", evapModel);
			atRow.setValue("evap_manufacturer", evapManufacturer);
			atRow.setValue("egos_front_model", egosFrontModel);
			atRow.setValue("egos_back_model", egosBackModel);
			atRow.setValue("egos_front_manufacturer", egosFrontManufacturer);
			atRow.setValue("egos_back_manufacturer", egosBackManufacturer);
			atRow.setValue("ce_control_model", crankcaseEmissionControlModel);
			atRow.setValue("ce_control_manufacturer", crankcaseEmissionControlManufacturer);
			atRow.setValue("egr_model", egrModel);
			atRow.setValue("egr_manufacturer", egrManufacturer);
			atRow.setValue("obd_model", obdModel);
			atRow.setValue("obd_manufacturer", obdManufacturer);
			atRow.setValue("iupr", iupr);
			atRow.setValue("ecu_model", ecuModel);
			atRow.setValue("ecu_version", ecuVersion);
			atRow.setValue("ecu_manufacturer", ecuManufacturer);
			atRow.setValue("transmission_type", transmissionType);
			atRow.setValue("gear", gear);
			atRow.setValue("muffler_front_model", mufflerFrontModel);
			atRow.setValue("muffler_back_model", mufflerBackModel);
			atRow.setValue("muffler_front_m", mufflerFrontManufacturer);
			atRow.setValue("muffler_back_m", mufflerBackManufacturer);
			atRow.setValue("supercharger_model", superchargerModel);
			atRow.setValue("supercharger_m", superchargerManufacturer);
			atRow.setValue("intercooler", intercooler);
			atRow.setValue("legal_representative", legalRepresentative);
			atRow.setValue("address", address);
			atRow.setValue("telephone_number", telephoneNumber);
			atRow.setValue("official_site", officialSite);
			Response result = atRow.save(imService.getFunctions().getDBTime(), "", null);
			if(result.isError()){
				return JSONArray.fromObject(generateResponseMap("保存车型【"+vehicleCategory+"】配置信息失败:"+result.getFirstErrorMessage())).toString();
			}
			return JSONArray.fromObject(generateResponseMap()).toString();
		}
		catch(MESException e){
			return JSONArray.fromObject(generateResponseMap("保存车型【"+vehicleCategory+"】配置信息失败:"+e.toString())).toString();
		}
	}
	
	/**
	 * 获取配置
	 */
	@RequestMapping(params = "getEPIConfig")
	public @ResponseBody String getEPIConfig(HttpServletRequest request, HttpServletResponse response){
		String vehicleCategory = request.getParameter("vehicle_category");
		if(StringUtil.isNull(vehicleCategory)){
			return JSONArray.fromObject(generateResponseMap("配置车型不能为空")).toString();
		}
		try{
			UTRowFilter filter = imService.getFunctions().createATRowFilter("MES_PR_EPI_Config");
			filter.forColumnNameEqualTo("vehicle_category", vehicleCategory);
			Vector<ATRow> vectorData = filter.exec();
			if(null==vectorData || vectorData.size()<=0){
				return JSONArray.fromObject(generateResponseMap("取得车型【"+vehicleCategory+"】配置信息失败")).toString();
			}
			ATRow atRow = vectorData.elementAt(0);
			HashMap<String, Object> map = generateResponseMap();
			map.put("publication_number", EnvironmentProtectionInformation.convert2String(atRow.getValue("publication_number")));
			map.put("company_name", EnvironmentProtectionInformation.convert2String(atRow.getValue("company_name")));
			map.put("car_type", EnvironmentProtectionInformation.convert2String(atRow.getValue("car_type")));
			map.put("trademark", EnvironmentProtectionInformation.convert2String(atRow.getValue("trademark")));
			map.put("vehicle_classification", EnvironmentProtectionInformation.convert2String(atRow.getValue("vehicle_classification")));
			map.put("emission_phase", EnvironmentProtectionInformation.convert2String(atRow.getValue("emission_phase")));
			map.put("recognition_method", EnvironmentProtectionInformation.convert2String(atRow.getValue("recognition_method")));
			map.put("recognition_location", EnvironmentProtectionInformation.convert2String(atRow.getValue("recognition_location")));
			map.put("manufacturer_name", EnvironmentProtectionInformation.convert2String(atRow.getValue("manufacturer_name")));
			map.put("factory_address", EnvironmentProtectionInformation.convert2String(atRow.getValue("factory_address")));
			map.put("inspection_institution_1", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_institution_1")));
			map.put("inspection_conclusion_1", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_conclusion_1")));
			map.put("inspection_institution_2", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_institution_2")));
			map.put("inspection_conclusion_2", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_conclusion_2")));
			map.put("inspection_institution_3", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_institution_3")));
			map.put("inspection_conclusion_3", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_conclusion_3")));
			map.put("inspection_institution_4", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_institution_4")));
			map.put("inspection_conclusion_4", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_conclusion_4")));
			map.put("factory_inspection", EnvironmentProtectionInformation.convert2String(atRow.getValue("factory_inspection")));
			map.put("engine_type", EnvironmentProtectionInformation.convert2String(atRow.getValue("engine_type")));
			map.put("engine_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("engine_manufacturer")));
			map.put("cc_front_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("cc_front_model")));
			map.put("cc_back_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("cc_back_model")));
			map.put("cc_front_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("cc_front_manufacturer")));
			map.put("cc_back_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("cc_back_manufacturer")));
			map.put("coating_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("coating_manufacturer")));
			map.put("carrier_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("carrier_manufacturer")));
			map.put("encapsulation_m", EnvironmentProtectionInformation.convert2String(atRow.getValue("encapsulation_m")));
			map.put("evap_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("evap_model")));
			map.put("evap_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("evap_manufacturer")));
			map.put("egos_front_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("egos_front_model")));
			map.put("egos_back_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("egos_back_model")));
			map.put("egos_front_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("egos_front_manufacturer")));
			map.put("egos_back_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("egos_back_manufacturer")));
			map.put("ce_control_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("ce_control_model")));
			map.put("ce_control_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("ce_control_manufacturer")));
			map.put("egr_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("egr_model")));
			map.put("egr_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("egr_manufacturer")));
			map.put("obd_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("obd_model")));
			map.put("obd_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("obd_manufacturer")));
			map.put("iupr", EnvironmentProtectionInformation.convert2String(atRow.getValue("iupr")));
			map.put("ecu_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("ecu_model")));
			map.put("ecu_version", EnvironmentProtectionInformation.convert2String(atRow.getValue("ecu_version")));
			map.put("ecu_manufacturer", EnvironmentProtectionInformation.convert2String(atRow.getValue("ecu_manufacturer")));
			map.put("transmission_type", EnvironmentProtectionInformation.convert2String(atRow.getValue("transmission_type")));
			map.put("gear", EnvironmentProtectionInformation.convert2String(atRow.getValue("gear")));
			map.put("muffler_front_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("muffler_front_model")));
			map.put("muffler_back_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("muffler_back_model")));
			map.put("muffler_front_m", EnvironmentProtectionInformation.convert2String(atRow.getValue("muffler_front_m")));
			map.put("muffler_back_m", EnvironmentProtectionInformation.convert2String(atRow.getValue("muffler_back_m")));
			map.put("supercharger_model", EnvironmentProtectionInformation.convert2String(atRow.getValue("supercharger_model")));
			map.put("supercharger_m", EnvironmentProtectionInformation.convert2String(atRow.getValue("supercharger_m")));
			map.put("intercooler", EnvironmentProtectionInformation.convert2String(atRow.getValue("intercooler")));
			map.put("legal_representative", EnvironmentProtectionInformation.convert2String(atRow.getValue("legal_representative")));
			map.put("address", EnvironmentProtectionInformation.convert2String(atRow.getValue("address")));
			map.put("telephone_number", EnvironmentProtectionInformation.convert2String(atRow.getValue("telephone_number")));
			map.put("official_site", EnvironmentProtectionInformation.convert2String(atRow.getValue("official_site")));
			return JSONArray.fromObject(map).toString();
		}
		catch(MESException e){
			return JSONArray.fromObject(generateResponseMap("取得车型【"+vehicleCategory+"】配置信息异常:"+e.toString())).toString();
		}
	}
	
	/**
	 * 删除配置
	 */
	@RequestMapping(params = "resetConfig")
	public @ResponseBody String resetConfig(HttpServletRequest request, HttpServletResponse response){
		String vehicleCategory = request.getParameter("vehicle_category");
		if(StringUtil.isNull(vehicleCategory)){
			return JSONArray.fromObject(generateResponseMap("配置车型不能为空")).toString();
		}
		try{
			UTRowFilter filter = imService.getFunctions().createATRowFilter("MES_PR_EPI_Config");
			filter.forColumnNameEqualTo("vehicle_category", vehicleCategory);
			Vector<ATRow> vectorData = filter.exec();
			if(vectorData==null || vectorData.size()<=0){
				return JSONArray.fromObject(generateResponseMap()).toString();
			}
			for(ATRow atRow : vectorData){
				Response result = atRow.delete(imService.getFunctions().getDBTime(), "", null);
				if(result.isError()){
					return JSONArray.fromObject(generateResponseMap("删除车型【"+vehicleCategory+"】配置信息失败:"+result.getFirstErrorMessage())).toString();
				}
			}
			return JSONArray.fromObject(generateResponseMap()).toString();
		}
		catch(MESException e){
			return JSONArray.fromObject(generateResponseMap("删除车型【"+vehicleCategory+"】配置信息失败:"+e.toString())).toString();
		}
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params = "save")
	public @ResponseBody String save(HttpServletRequest request, HttpServletResponse response){
		String vin = request.getParameter("vin_para");
		if(StringUtil.isNull(vin)){
			return JSONArray.fromObject(generateResponseMap("VIN号不能为空")).toString();
		}
		try{
			UTRowFilter filter = imService.getFunctions().createATRowFilter("MES_PR_EPI");
			filter.forColumnNameEqualTo("vin", vin);
			Vector<ATRow> vectorData = filter.exec();
			if(vectorData==null || vectorData.size()<=0){
				return JSONArray.fromObject(generateResponseMap("VIN【"+vin+"】环保信息不存在")).toString();
			}
			ATRow atRow = vectorData.elementAt(0);
			String qrCode = EnvironmentProtectionInformation.getQRCodeString(vin);
			if(StringUtil.isNull(qrCode)){
				return JSONArray.fromObject(generateResponseMap("二维码不能为空")).toString();
			}
			//TODO:信息公开编号无法编辑
			String companyName = request.getParameter("company_name");
			if(StringUtil.isNull(companyName)){
				return JSONArray.fromObject(generateResponseMap("公司名称不能为空")).toString();
			}
			String carType = request.getParameter("car_type");
			if(StringUtil.isNull(carType)){
				return JSONArray.fromObject(generateResponseMap("车辆型号不能为空")).toString();
			}
			String trademark = request.getParameter("trademark");
			if(StringUtil.isNull(trademark)){
				return JSONArray.fromObject(generateResponseMap("商标不能为空")).toString();
			}
			String vehicleClassification = request.getParameter("vehicle_classification");
			if(StringUtil.isNull(vehicleClassification)){
				return JSONArray.fromObject(generateResponseMap("汽车分类不能为空")).toString();
			}
			String emissionPhase = request.getParameter("emission_phase");
			if(StringUtil.isNull(emissionPhase)){
				return JSONArray.fromObject(generateResponseMap("排放阶段不能为空")).toString();
			}
			String recognition = request.getParameter("recognition");
			if(StringUtil.isNull(recognition)){
				return JSONArray.fromObject(generateResponseMap("车型的识别方法和位置不能为空")).toString();
			}
			String manufacturerName = request.getParameter("manufacturer_name");
			if(StringUtil.isNull(manufacturerName)){
				return JSONArray.fromObject(generateResponseMap("车辆制造商名称不能为空")).toString();
			}
			String factoryAddress = request.getParameter("factory_address");
			if(StringUtil.isNull(factoryAddress)){
				return JSONArray.fromObject(generateResponseMap("生产厂地址不能为空")).toString();
			}
			String inspectionInstitution1 = request.getParameter("inspection_institution_1");
			if(StringUtil.isNull(inspectionInstitution1)){
				return JSONArray.fromObject(generateResponseMap("检测机构不能为空")).toString();
			}
			String inspectionConclusion1 = request.getParameter("inspection_conclusion_1");
			if(StringUtil.isNull(inspectionConclusion1)){
				return JSONArray.fromObject(generateResponseMap("检测结论不能为空")).toString();
			}
			String inspectionInstitution2 = request.getParameter("inspection_institution_2");
			if(StringUtil.isNull(inspectionInstitution2)){
				return JSONArray.fromObject(generateResponseMap("检测机构不能为空")).toString();
			}
			String inspectionConclusion2 = request.getParameter("inspection_conclusion_2");
			if(StringUtil.isNull(inspectionConclusion2)){
				return JSONArray.fromObject(generateResponseMap("检测结论不能为空")).toString();
			}
			String inspectionInstitution3 = request.getParameter("inspection_institution_3");
			if(StringUtil.isNull(inspectionInstitution3)){
				return JSONArray.fromObject(generateResponseMap("检测机构不能为空")).toString();
			}
			String inspectionConclusion3 = request.getParameter("inspection_conclusion_3");
			if(StringUtil.isNull(inspectionConclusion3)){
				return JSONArray.fromObject(generateResponseMap("检测结论不能为空")).toString();
			}
			String inspectionInstitution4 = request.getParameter("inspection_institution_4");
//			if(StringUtil.isNull(inspectionInstitution4)){
//				return JSONArray.fromObject(generateResponseMap("检测机构不能为空")).toString();
//			}
			String inspectionConclusion4 = request.getParameter("inspection_conclusion_4");
//			if(StringUtil.isNull(inspectionConclusion4)){
//				return JSONArray.fromObject(generateResponseMap("检测结论不能为空")).toString();
//			}
			String factoryInspection = request.getParameter("factory_inspection");
			if(StringUtil.isNull(factoryInspection)){
				return JSONArray.fromObject(generateResponseMap("出厂检验项目及结论不能为空")).toString();
			}
			String engine = request.getParameter("engine");
			if(StringUtil.isNull(engine)){
				return JSONArray.fromObject(generateResponseMap("发动机型号/生产厂不能为空")).toString();
			}
			String catalyticConverter = request.getParameter("catalytic_converter");
			if(StringUtil.isNull(catalyticConverter)){
				return JSONArray.fromObject(generateResponseMap("催化转化器型号/生产厂不能为空")).toString();
			}
			String coatingCarrierEncapsulation = request.getParameter("cce");
			if(StringUtil.isNull(coatingCarrierEncapsulation)){
				return JSONArray.fromObject(generateResponseMap("涂层/载体/封装生产厂不能为空")).toString();
			}
			String evap = request.getParameter("evap");
			if(StringUtil.isNull(evap)){
				return JSONArray.fromObject(generateResponseMap("燃油蒸发控制装置型号/生产厂不能为空")).toString();
			}
			String egos = request.getParameter("egos");
			if(StringUtil.isNull(egos)){
				return JSONArray.fromObject(generateResponseMap("氧传感器型号/生产厂不能为空")).toString();
			}
			String crankcaseEmissionControl = request.getParameter("ce_control");
			if(StringUtil.isNull(crankcaseEmissionControl)){
				return JSONArray.fromObject(generateResponseMap("曲轴箱排放控制装置型号/生产厂不能为空")).toString();
			}
			String egr = request.getParameter("egr");
			if(StringUtil.isNull(egr)){
				return JSONArray.fromObject(generateResponseMap("EGR型号/生产厂不能为空")).toString();
			}
			String obd = request.getParameter("obd");
			if(StringUtil.isNull(obd)){
				return JSONArray.fromObject(generateResponseMap("OBD型号/生产厂不能为空")).toString();
			}
			String iupr = request.getParameter("iupr");
			if(StringUtil.isNull(iupr)){
				return JSONArray.fromObject(generateResponseMap("IUPR监测功能不能为空")).toString();
			}
			String ecu = request.getParameter("ecu");
			if(StringUtil.isNull(ecu)){
				return JSONArray.fromObject(generateResponseMap("ECU型号/版本号/生产厂不能为空")).toString();
			}
			String transmission = request.getParameter("transmission");
			if(StringUtil.isNull(transmission)){
				return JSONArray.fromObject(generateResponseMap("变速器型式/档位数不能为空")).toString();
			}
			String muffler = request.getParameter("muffler");
			if(StringUtil.isNull(muffler)){
				return JSONArray.fromObject(generateResponseMap("消声器型号/生产厂不能为空")).toString();
			}
			String supercharger = request.getParameter("supercharger");
			if(StringUtil.isNull(supercharger)){
				return JSONArray.fromObject(generateResponseMap("增压器型号/生产厂不能为空")).toString();
			}
			String intercooler = request.getParameter("intercooler");
			if(StringUtil.isNull(intercooler)){
				return JSONArray.fromObject(generateResponseMap("中冷器型式不能为空")).toString();
			}
			String legalRepresentative = request.getParameter("legal_representative");
			if(StringUtil.isNull(legalRepresentative)){
				return JSONArray.fromObject(generateResponseMap("法人代表不能为空")).toString();
			}
			String address = request.getParameter("address");
			if(StringUtil.isNull(address)){
				return JSONArray.fromObject(generateResponseMap("地址不能为空")).toString();
			}
			String telephoneNumber = request.getParameter("telephone_number");
			if(StringUtil.isNull(telephoneNumber)){
				return JSONArray.fromObject(generateResponseMap("联系电话不能为空")).toString();
			}
			String officialSite = request.getParameter("official_site");
			if(StringUtil.isNull(officialSite)){
				return JSONArray.fromObject(generateResponseMap("官方网站不能为空")).toString();
			}
			String productionDate = request.getParameter("production_date");
			if(StringUtil.isNull(productionDate)){
				return JSONArray.fromObject(generateResponseMap("车辆生产日期不能为空")).toString();
			}
			atRow.setValue("qr_code", qrCode);
			atRow.setValue("company_name", companyName);
			atRow.setValue("car_type", carType);
			atRow.setValue("trademark", trademark);
			atRow.setValue("vehicle_classification", vehicleClassification);
			atRow.setValue("emission_phase", emissionPhase);
			atRow.setValue("recognition", recognition);
			atRow.setValue("manufacturer_name", manufacturerName);
			atRow.setValue("factory_address", factoryAddress);
			atRow.setValue("inspection_institution_1", inspectionInstitution1);
			atRow.setValue("inspection_conclusion_1", inspectionConclusion1);
			atRow.setValue("inspection_institution_2", inspectionInstitution2);
			atRow.setValue("inspection_conclusion_2", inspectionConclusion2);
			atRow.setValue("inspection_institution_3", inspectionInstitution3);
			atRow.setValue("inspection_conclusion_3", inspectionConclusion3);
			atRow.setValue("inspection_institution_4", inspectionInstitution4);
			atRow.setValue("inspection_conclusion_4", inspectionConclusion4);
			atRow.setValue("factory_inspection", factoryInspection);
			atRow.setValue("engine", engine);
			atRow.setValue("catalytic_converter", catalyticConverter);
			atRow.setValue("cce", coatingCarrierEncapsulation);
			atRow.setValue("evap", evap);
			atRow.setValue("egos", egos);
			atRow.setValue("ce_control", crankcaseEmissionControl);
			atRow.setValue("egr", egr);
			atRow.setValue("obd", obd);
			atRow.setValue("iupr", iupr);
			atRow.setValue("ecu", ecu);
			atRow.setValue("transmission", transmission);
			atRow.setValue("muffler", muffler);
			atRow.setValue("supercharger", supercharger);
			atRow.setValue("intercooler", intercooler);
			atRow.setValue("legal_representative", legalRepresentative);
			atRow.setValue("address", address);
			atRow.setValue("telephone_number", telephoneNumber);
			atRow.setValue("official_site", officialSite);
			atRow.setValue("production_date", productionDate);
			Response result = atRow.save(imService.getFunctions().getDBTime(), "", null);
			if(result.isError()){
				return JSONArray.fromObject(generateResponseMap("保存VIN【"+vin+"】环保信息失败:"+result.getFirstErrorMessage())).toString();
			}
			return JSONArray.fromObject(generateResponseMap()).toString();
		}
		catch(MESException e){
			return JSONArray.fromObject(generateResponseMap("保存VIN【"+vin+"】环保信息失败:"+e.toString())).toString();
		}
	}
	
	/**
	 * 获取本地打印机列表
	 */
	@RequestMapping(params = "getLocalPrinters")
	public @ResponseBody String getLocalPrinters(){
		DocFlavor df = javax.print.DocFlavor.INPUT_STREAM.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		PrintService allService[] = PrintServiceLookup.lookupPrintServices(df, pras);
		String[] printers = new String[allService.length];
		if(printers!=null && printers.length>0){
			for(int i=0;i<allService.length;i++){
				printers[i] = allService[i].getName();
			}
		}
		HashMap<String, Object> map = generateResponseMap();
		map.put("printers", printers);
		return JSONArray.fromObject(map).toString();
	}
	
	/**
	 * 打印
	 */
	@RequestMapping(params = "print")
	public @ResponseBody String print(HttpServletRequest request, HttpServletResponse response){
		String vin = request.getParameter("vin");
		if(StringUtil.isNull(vin)){
			return JSONArray.fromObject(generateResponseMap("VIN号不能为空")).toString();
		}
		String printer = request.getParameter("printer");
		if(StringUtil.isNull(printer)){
			return JSONArray.fromObject(generateResponseMap("请选择打印机")).toString();
		}
		EnvironmentProtectionInformation epi = new EnvironmentProtectionInformation();
//		ATRow atRow = epi.getEnvironmentProtectionInformation(vin);
		HashMap<String, Object> result = epi.getEPI(vin);
		if(Boolean.valueOf(EnvironmentProtectionInformation.convert2String(result.get("isOk")))){
			ATRow atRow = (ATRow)result.get("object");
			HashMap<String, String> reportData = epi.createReportDataRow(atRow);
			ReportDataSet reportDataSet = new ReportDataSet();
			reportDataSet.appendRow(reportData);
			try{
				Report report = imService.getFunctions().createReport("轻型汽油车环保信息随车清单", "1");
				report.setReportDataSet(reportDataSet);
				Response r = report.generate();
				if(r.isError()){
					return JSONArray.fromObject(generateResponseMap(r.getFirstErrorMessage())).toString();
				}
				print(report, printer);

				report = imService.getFunctions().createReport("轻型汽油车环保信息随车清单", "2");
				report.setReportDataSet(reportDataSet);
				r = report.generate();
				if(r.isError()){
					return JSONArray.fromObject(generateResponseMap(r.getFirstErrorMessage())).toString();
				}
				export(report, vin);
				return JSONArray.fromObject(generateResponseMap()).toString();
			}
			catch(Exception e){
				return JSONArray.fromObject(generateResponseMap("打印VIN【"+vin+"】环保信息失败:"+e.toString())).toString();
			}
		}
		else{
			return JSONArray.fromObject(generateResponseMap(EnvironmentProtectionInformation.convert2String(result.get("errorMessage")))).toString();
		}
	}
	
	private void print(Report report, String printerName){
		report.print(printerName, 1, false);
	}
	
	private void export(Report report, String vin) throws Exception  {
		String fileName = vin+".pdf";
		OutputStream os = null;
		try {
			String filePath = "E:\\环保\\";
			File dir = new File(filePath);
		    if(!dir.exists()){
		    	boolean b = dir.mkdirs();
		    	System.out.println(filePath+" : "+b);
		    }
		    filePath += fileName;
		    File file = new File(filePath);
		    os = new FileOutputStream(file);
		    report.exportToPDF(os);
		    os.close();
		    os.flush();
		    os.close();
		    os = null;
		} 
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally{
			try {
				if(os!=null){
					os.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}
	
	/**
	 * 获取
	 */
	@RequestMapping(params = "getEPI")
	public @ResponseBody String getEPI(HttpServletRequest request, HttpServletResponse response){
		String vin = request.getParameter("vin");
		if(StringUtil.isNull(vin)){
			return JSONArray.fromObject(generateResponseMap("请扫描VIN号")).toString();
		}
		EnvironmentProtectionInformation epi = new EnvironmentProtectionInformation();
//		ATRow atRow = epi.getEnvironmentProtectionInformation(vin);
//		if(atRow==null){
//			return JSONArray.fromObject(generateResponseMap("取得VIN【"+vin+"】环保信息失败")).toString();
//		}
		HashMap<String, Object> result = epi.getEPI(vin);
		if(!Boolean.valueOf(EnvironmentProtectionInformation.convert2String(result.get("isOk")))){
			return JSONArray.fromObject(generateResponseMap(EnvironmentProtectionInformation.convert2String(result.get("errorMessage")))).toString();
		}
		ATRow atRow = (ATRow)result.get("object");
        HashMap<String, Object> map = generateResponseMap();
        map.put("publication_number", EnvironmentProtectionInformation.convert2String(atRow.getValue("publication_number")));
        map.put("company_name", EnvironmentProtectionInformation.convert2String(atRow.getValue("company_name")));
        map.put("car_type", EnvironmentProtectionInformation.convert2String(atRow.getValue("car_type")));
        map.put("trademark", EnvironmentProtectionInformation.convert2String(atRow.getValue("trademark")));
        map.put("vehicle_classification", EnvironmentProtectionInformation.convert2String(atRow.getValue("vehicle_classification")));
        map.put("emission_phase", EnvironmentProtectionInformation.convert2String(atRow.getValue("emission_phase")));
        map.put("recognition", EnvironmentProtectionInformation.convert2String(atRow.getValue("recognition")));
        map.put("manufacturer_name", EnvironmentProtectionInformation.convert2String(atRow.getValue("manufacturer_name")));
        map.put("factory_address", EnvironmentProtectionInformation.convert2String(atRow.getValue("factory_address")));
        map.put("inspection_institution_1", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_institution_1")));
        map.put("inspection_conclusion_1", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_conclusion_1")));
        map.put("inspection_institution_2", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_institution_2")));
        map.put("inspection_conclusion_2", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_conclusion_2")));
        map.put("inspection_institution_3", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_institution_3")));
        map.put("inspection_conclusion_3", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_conclusion_3")));
        map.put("inspection_institution_4", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_institution_4")));
        map.put("inspection_conclusion_4", EnvironmentProtectionInformation.convert2String(atRow.getValue("inspection_conclusion_4")));
        map.put("factory_inspection", EnvironmentProtectionInformation.convert2String(atRow.getValue("factory_inspection")));
        map.put("engine", EnvironmentProtectionInformation.convert2String(atRow.getValue("engine")));
        map.put("catalytic_converter", EnvironmentProtectionInformation.convert2String(atRow.getValue("catalytic_converter")));
        map.put("cce", EnvironmentProtectionInformation.convert2String(atRow.getValue("cce")));
        map.put("evap", EnvironmentProtectionInformation.convert2String(atRow.getValue("evap")));
        map.put("egos", EnvironmentProtectionInformation.convert2String(atRow.getValue("egos")));
        map.put("ce_control", EnvironmentProtectionInformation.convert2String(atRow.getValue("ce_control")));
        map.put("egr", EnvironmentProtectionInformation.convert2String(atRow.getValue("egr")));
        map.put("obd", EnvironmentProtectionInformation.convert2String(atRow.getValue("obd")));
        map.put("iupr", EnvironmentProtectionInformation.convert2String(atRow.getValue("iupr")));
        map.put("ecu", EnvironmentProtectionInformation.convert2String(atRow.getValue("ecu")));
        map.put("transmission", EnvironmentProtectionInformation.convert2String(atRow.getValue("transmission")));
        map.put("muffler", EnvironmentProtectionInformation.convert2String(atRow.getValue("muffler")));
        map.put("supercharger", EnvironmentProtectionInformation.convert2String(atRow.getValue("supercharger")));
        map.put("intercooler", EnvironmentProtectionInformation.convert2String(atRow.getValue("intercooler")));
        map.put("legal_representative", EnvironmentProtectionInformation.convert2String(atRow.getValue("legal_representative")));
        map.put("address", EnvironmentProtectionInformation.convert2String(atRow.getValue("address")));
        map.put("telephone_number", EnvironmentProtectionInformation.convert2String(atRow.getValue("telephone_number")));
        map.put("official_site", EnvironmentProtectionInformation.convert2String(atRow.getValue("official_site")));
        map.put("production_date", EnvironmentProtectionInformation.convert2String(atRow.getValue("production_date")));
        return JSONArray.fromObject(map).toString();
	}
	
	/**
	 * 上传
	 */
	@RequestMapping(params = "upload")
	public @ResponseBody String upload(HttpServletRequest request, HttpServletResponse response){
		String vin = request.getParameter("vin");
		if(StringUtil.isNull(vin)){
			return JSONArray.fromObject(generateResponseMap("请扫描VIN号")).toString();
		}
		try {
			EnvironmentProtectionInformation epi = new EnvironmentProtectionInformation();
			epi.upload(vin);
//			EnvironmentProtectionInformation.delData(vin);
		} catch (Exception e) {
			return JSONArray.fromObject(generateResponseMap("上传VIN【"+vin+"】环保信息失败:"+e.toString())).toString();
		}

		return JSONArray.fromObject(generateResponseMap()).toString();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(params = "reset")
	public @ResponseBody String reset(HttpServletRequest request, HttpServletResponse response){
		String vin = request.getParameter("vin");
		if(StringUtil.isNull(vin)){
			return JSONArray.fromObject(generateResponseMap("请扫描VIN号")).toString();
		}
		try{
			UTRowFilter filter = imService.getFunctions().createATRowFilter("MES_PR_EPI");
			filter.forColumnNameEqualTo("vin", vin);
			Vector<ATRow> vectorData = filter.exec();
			if(vectorData==null || vectorData.size()<=0){
				return JSONArray.fromObject(generateResponseMap()).toString();
			}
			for(ATRow atRow : vectorData){
				Response result = atRow.delete(imService.getFunctions().getDBTime(), "", null);
				if(result.isError()){
					return JSONArray.fromObject(generateResponseMap("删除VIN【"+vin+"】环保信息失败:"+result.getFirstErrorMessage())).toString();
				}
			}
			return JSONArray.fromObject(generateResponseMap()).toString();
		}
		catch(MESException e){
			return JSONArray.fromObject(generateResponseMap("删除VIN【"+vin+"】环保信息失败:"+e.toString())).toString();
		}
	}
	
//	/**
//	 * 打印测试
//	 */
//	@RequestMapping(params = "test")
//	public void test(HttpServletRequest request, HttpServletResponse response){
//		String vin = "LHBBCCDH46F000201";
//		try {
//			ATRowFilter filter = imService.getFunctions().createATRowFilter("MES_PR_EPI_Config");
//			filter.forColumnNameEqualTo("vehicle_category", "FM6042Q001");
//			Vector<ATRow> vectorData = filter.exec();
//			for(ATRow atRow : vectorData){
//				atRow.delete(imService.getFunctions().getDBTime(), "", null);
//			}
//			if(filter.exec().size()<=0){
//				initTestConfig();
//			}
////			print(vin);
//			String name = "打印";
//			String version = "1";
//
//		    String fileName = name+"."+version+".pdf" ;
//		    String  sysPath = request.getSession().getServletContext().getRealPath("");
//		    System.out.println(sysPath);
//		    String fileDIR = sysPath = sysPath +"\\updownload\\" ;
//		    File dir = new File(fileDIR);
//		    if( ! dir.exists() ){
//		    	dir.mkdir();
//		    }
//		    sysPath = fileDIR + fileName;  
//            System.out.println(sysPath);
//            File f = new File(sysPath);
//
//          
//         
//            String agent = request.getHeader("USER-AGENT");  
//            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent  
//                    && -1 != agent.indexOf("Trident")) {// ie  
//   
//            	fileName = java.net.URLEncoder.encode(fileName, "UTF8");  
//   
//              
//            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等  
//   
//   
//            	fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");  
//            }  
//            
//            
//            response.addHeader("Content-Disposition", "attachment;filename="
//					+  fileName );
//			response.addHeader("Content-Length", "" + f.length());
//			response.setContentType("application/x-msdownload;charset=UTF-8");
//			   
//			
//	            
//	            
//			OutputStream hblicense = new FileOutputStream(f);//response.getOutputStream();// 获取输出流
//			export(vin, hblicense);
//			hblicense.close();// 关闭输
//			hblicense.flush();
//			hblicense.close();
//			hblicense = null;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	private void initTestConfig(){
//		try{
//			ATHandler atHandler = imService.getFunctions().createATHandler("MES_PR_EPI_Config");
//			ATRow atRow = atHandler.createATRow();
//			atRow.setValue("vehicle_category", "FM6042Q001");
//			atRow.setValue("publication_number", "CN QQ G5 Z2 0H780001-01 000001");
//			atRow.setValue("company_name", "北京宝沃汽车有限公司");
//			atRow.setValue("car_type", "BW6470B1X5A");
//			atRow.setValue("trademark", "宝沃牌(BORGWARD)");
//			atRow.setValue("vehicle_classification", "M1");
//			atRow.setValue("emission_phase", "国五");
//			atRow.setValue("recognition_method", "铭牌");
//			atRow.setValue("recognition_location", "驾驶室右立柱");
//			atRow.setValue("manufacturer_name", "北京宝沃汽车有限公司密云工厂");
//			atRow.setValue("factory_address", "北京市密云区西统路188号​");
//			atRow.setValue("inspection_institution_1", "国家汽车质量监督检验中心​");
//			atRow.setValue("inspection_conclusion_1", "符合​");
//			atRow.setValue("inspection_institution_2", "国家汽车质量监督检验中心​");
//			atRow.setValue("inspection_conclusion_2", "符合​");
//			atRow.setValue("inspection_institution_3", "国家汽车质量监督检验中心");
//			atRow.setValue("inspection_conclusion_3", "符合​");
//			atRow.setValue("inspection_institution_4", "国家汽车质量监督检验中心​");
//			atRow.setValue("inspection_conclusion_4", "符合​");
//			atRow.setValue("factory_inspection", "双怠速试验，合格");
//			atRow.setValue("engine_type", "BWE420B");
//			atRow.setValue("engine_manufacturer", "北京宝沃汽车有限公司");
//			atRow.setValue("cc_front_model", "G1E511A");
//			atRow.setValue("cc_back_model", "G1E511B");
//			atRow.setValue("cc_front_manufacturer", "昆明贵研催化剂有限责任公司");
//			atRow.setValue("cc_back_manufacturer", "");
//			atRow.setValue("coating_manufacturer", "左前：单元 1：昆明贵研催化剂有限责任公司；单元 2：昆明贵研催化剂有限责任公司");
//			atRow.setValue("carrier_manufacturer", "左前：单元 1：康宁(上海)有限公司；单元 2：康宁(上海)有限公司");
//			atRow.setValue("encapsulation_m", "左前：杰锋汽车动力股份有限公司");
//			atRow.setValue("evap_model", "V700001019");
//			atRow.setValue("evap_manufacturer", "廊坊华安汽车装备有限公司");
//			atRow.setValue("egos_front_model", "0 258 017 269");
//			atRow.setValue("egos_back_model", "LSF4");
//			atRow.setValue("egos_front_manufacturer", "联合汽车电子有限公司");
//			atRow.setValue("egos_back_manufacturer", "");
//			atRow.setValue("ce_control_model", "P700000104");
//			atRow.setValue("ce_control_manufacturer", "无锡德仕乐汽车部件有限公司");
//			atRow.setValue("egr_model", "");
//			atRow.setValue("egr_manufacturer", "");
//			atRow.setValue("obd_model", "MED1782");
//			atRow.setValue("obd_manufacturer", "联合汽车电子有限公司");
//			atRow.setValue("iupr", "符合");
//			atRow.setValue("ecu_model", "MED17821");
//			atRow.setValue("ecu_version", "");
//			atRow.setValue("ecu_manufacturer", "联合汽车电子有限公司");
//			atRow.setValue("transmission_type", "手自一体");
//			atRow.setValue("gear", "6");
//			atRow.setValue("muffler_front_model", "PZK06-N001");
//			atRow.setValue("muffler_back_model", "");
//			atRow.setValue("muffler_front_m", "杰锋汽车动力系统股份有限公司");
//			atRow.setValue("muffler_back_m", "");
//			atRow.setValue("supercharger_model", "F50");
//			atRow.setValue("supercharger_m", "长春富奥石川岛增压器有限公司");
//			atRow.setValue("intercooler", "空空");
//			atRow.setValue("legal_representative", "Ulrich Walker");
//			atRow.setValue("address", "北京市昌平区西统路188号");
//			atRow.setValue("telephone_number", "010-00000000");
//			atRow.setValue("official_site", "正在建设中");
//			atRow.save(imService.getFunctions().getDBTime(), "", null);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
//	private void print(String vin){
//		EnvironmentProtectionInformation epi = new EnvironmentProtectionInformation();
//		ATRow atRow = epi.getEnvironmentProtectionInformation(vin);
//		HashMap<String, String> m = epi.createReportDataRow(atRow);
////		print(m, "轻型汽油车环保信息随车清单", "1", "Canon iR2520 UFRII LT");
//		print(m, "轻型汽油车环保信息随车清单", "1", "Microsoft XPS Document Writer");
////		print(m, "轻型汽油车环保信息随车清单", "1", "CCR");
//	}
	
//	private void export(String vin, OutputStream os){
//		EnvironmentProtectionInformation epi = new EnvironmentProtectionInformation();
//		ATRow atRow = epi.getEnvironmentProtectionInformation(vin);
//		HashMap<String, String> m = epi.createReportDataRow(atRow);
//        export(m, "轻型汽油车环保信息随车清单", "1", os);
//	}
	
//	private void print(HashMap<String, String> reportData, String reportDesignName, String reportDesignRevision, String printerName) throws MESException{
//		ReportDataSet reportDataSet = new ReportDataSet();
//		reportDataSet.appendRow(reportData);
//		try {
//			Report report = imService.getFunctions().createReport(reportDesignName, reportDesignRevision);
//			report.setReportDataSet(reportDataSet);
//			Response r = report.generate();
//			if(r.isOk()){
//				report.print(printerName, 1, false);
//			}
//		} catch (MESException e) {
//			throw e;
//		}
//	}
	
//	private void export(HashMap<String, String> reportData, String reportDesignName, String reportDesignRevision, OutputStream os){
//		ReportDataSet reportDataSet = new ReportDataSet();
//		reportDataSet.appendRow(reportData);
//		try {
//			Report report = imService.getFunctions().createReport(reportDesignName, reportDesignRevision);
//			report.setReportDataSet(reportDataSet);
//			Response r = report.generate();
//			if(r.isOk()){
//				report.exportToPDF(os);
//			}
//		} catch (MESException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * 返回消息生成
	 */
	private HashMap<String, Object> generateResponseMap(){
		return generateResponseMap("");
	}

	/**
	 * 返回消息生成
	 */
	private HashMap<String, Object> generateResponseMap(String message){
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNull(message)){
			map.put("RESULT", "SUCCESS");
		}
		else{
			map.put("RESULT", "FAIL");
		}
		map.put("MESSAGE", message);
		return map;
	}
}