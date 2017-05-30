package com.mes.webclient.controller;

import java.util.Vector;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.BillOfMaterials;
import com.mes.compatibility.client.Order;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.webclient.controller.vo.ATCertificateVO;
import com.mes.webclient.controller.vo.BOMVO;
import com.mes.webclient.controller.vo.CertConfigFuelVO;
import com.mes.webclient.service.impl.IMService;
import com.mes.webclient.util.ATUtil;
import com.mes.webclient.util.StringUtil;


@Controller("CertConfigFuelController")
@RequestMapping("/CertConfigFuelController.sp")
public class CertConfigCOClController extends BaseController
{
	@Autowired
	IMService imService;

	String tableName = "certconfig_cert";
	
	@RequestMapping(params = "toMainPage")
	public String toMainPage(CertConfigFuelVO fuelVO)
	{
		return "im/certconfigfuel/list";
	}
	
	@ResponseBody
	@RequestMapping(params = "toList")
	public String query(CertConfigFuelVO fuelVO)
	{
		CertConfigFuelVO atCertificatevo = new CertConfigFuelVO();
		String carmodelcode=fuelVO.getCarmodelcode();
		Vector<CertConfigFuelVO> vector = new Vector<>();
		//System.out.println("certificate");
		UTRow atRow ;
		try{			
			String atDefinitionName = "MES_PR_CertConfigFuel";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
			if (StringUtil.isNotNull(carmodelcode))
			{
				filter.forColumnNameEqualTo("carmodelcode", carmodelcode);
	 		}
			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
			if(rows!=null&&rows.size()>0){
				for(int i = 0 ;i<rows.size();i++)
				{
					atRow= rows.get(i);
					atCertificatevo = new CertConfigFuelVO();
					atCertificatevo.setProducecompany(atRow.getValue("producecompany").toString());
					atCertificatevo.setCartype(atRow.getValue("cartype").toString());
					atCertificatevo.setDriveengine(atRow.getValue("driveengine").toString());
					atCertificatevo.setFueltype(atRow.getValue("fueltype").toString());
					atCertificatevo.setDisplacement(atRow.getValue("displacement").toString());
					atCertificatevo.setPowerrate(atRow.getValue("powerrate").toString());
					atCertificatevo.setTranstype(atRow.getValue("transtype").toString());
					atCertificatevo.setDrivetype(atRow.getValue("drivetype").toString());
					atCertificatevo.setCompletecarmass(atRow.getValue("completecarmass").toString());
					atCertificatevo.setMaxdesignmass(atRow.getValue("maxdesignmass").toString());
					atCertificatevo.setCityoil(atRow.getValue("cityoil").toString());
					atCertificatevo.setAlloil(atRow.getValue("alloil").toString());
					atCertificatevo.setSuburboil(atRow.getValue("suburboil").toString());
					atCertificatevo.setCountrystandard(atRow.getValue("countrystandard").toString());
					atCertificatevo.setStage1(atRow.getValue("stage1").toString());
					atCertificatevo.setStagestarttimeyear1(atRow.getValue("stagestarttimeyear1").toString());
					atCertificatevo.setLimiteoil1(atRow.getValue("limiteoil1").toString());
					atCertificatevo.setStage(atRow.getValue("stage").toString());
					atCertificatevo.setStagestarttimeyear(atRow.getValue("stagestarttimeyear").toString());
					atCertificatevo.setLimiteoil(atRow.getValue("limiteoil").toString());
//					atCertificatevo.setRecordnum(atRow.getValue("recordnum").toString());
//					atCertificatevo.setAllstarttimeyear(atRow.getValue("allstarttimeyear").toString());
//					atCertificatevo.setColor(atRow.getValue("color").toString());
					atCertificatevo.setCarmodelcode(atRow.getValue("carmodelcode").toString());
					atCertificatevo.setKey(atRow.getKey());
					
					vector.add(atCertificatevo);
				}
			//	atDepartmentVO.setD_id(String.valueOf(atRow.getValue("d_id")));				 			
			}	
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONArray.fromObject(vector).toString();
	}

	
	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(CertConfigFuelVO atCertificatevo, Model model)
	{
		long key = atCertificatevo.getKey();
		System.out.println("key====="+key);
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		try
		{

			String atDefinitionName = "MES_PR_CertConfigFuel";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
			filter.forUTRowKeyEqualTo(key);
 			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
			if(rows!=null&&rows.size()>0){
				UTRow atRow = rows.get(0);
				atCertificatevo = (CertConfigFuelVO)ATUtil.getATObject(CertConfigFuelVO.class, atRow); 		
				atCertificatevo.setKey(key);			
			//	atDepartmentVO.setD_id(String.valueOf(atRow.getValue("d_id")));				 			
			}			 
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		model.addAttribute(
			VIEW_OBJECT, atCertificatevo);		 
		return "im/certconfigfuel/addOrEdit";
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(params = "del")
	public @ResponseBody
	String del(@RequestParam("key") long key) {
		try {
			if (key > 0) {
				String atDefinitionName = "MES_PR_CertConfigFuel";
				UTHandler atHandler = imService.getAtHandler(atDefinitionName);
				UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
				filter.forUTRowKeyEqualTo(key);
	 			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
	 			if(rows!=null&&rows.size()>0){
					UTRow atRow = rows.get(0);
				 atRow.delete();			
				//	atDepartmentVO.setD_id(String.valueOf(atRow.getValue("d_id")));				 			
				}		
				return ajaxDoneSuccess();
			} else {
				throw new Exception("无法找到该对象的key");
			}
		} catch (Exception e) {
			logger.error(e);
			return ajaxDoneError(e.getLocalizedMessage());
		}
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(params = "save")
	public @ResponseBody
	String save(CertConfigFuelVO fuelVO) {
		try {
			long key = fuelVO.getKey();
			BillOfMaterials billOfMaterials = null;
			UTRow atRow = null ;
			if (key < 0) {
				String atDefinitionName = "MES_PR_CertConfigFuel";
				UTHandler atHandler = imService.getAtHandler(atDefinitionName);
				atRow = atHandler.createATRow();
			} else {
				String atDefinitionName = "MES_PR_CertConfigFuel";
				UTHandler atHandler = imService.getAtHandler(atDefinitionName);
				UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
				filter.forUTRowKeyEqualTo(key);
	 			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
	 			if(rows!=null&&rows.size()>0){
	 			 atRow = rows.get(0);	
				//	atDepartmentVO.setD_id(String.valueOf(atRow.getValue("d_id")));				 			
				}		
			}
			atRow.setValue("producecompany", fuelVO.getProducecompany());
			atRow.setValue("cartype", fuelVO.getCartype());
			atRow.setValue("driveengine", fuelVO.getDriveengine());
			atRow.setValue("fueltype", fuelVO.getFueltype());
			atRow.setValue("displacement", fuelVO.getDisplacement());
			atRow.setValue("powerrate", fuelVO.getPowerrate());
			atRow.setValue("transtype", fuelVO.getTranstype());
			atRow.setValue("drivetype", fuelVO.getDrivetype());
			atRow.setValue("completecarmass", fuelVO.getCompletecarmass());
			atRow.setValue("maxdesignmass", fuelVO.getMaxdesignmass());
			atRow.setValue("cityoil", fuelVO.getCityoil());
			atRow.setValue("alloil", fuelVO.getAlloil());
			atRow.setValue("suburboil", fuelVO.getSuburboil());
			atRow.setValue("countrystandard", fuelVO.getCountrystandard());
			atRow.setValue("stage1", fuelVO.getStage1());
			atRow.setValue("stagestarttimeyear1", fuelVO.getStagestarttimeyear1());
			atRow.setValue("limiteoil1", fuelVO.getLimiteoil1());
			atRow.setValue("stage", fuelVO.getStage());
			atRow.setValue("stagestarttimeyear", fuelVO.getStagestarttimeyear());
			atRow.setValue("limiteoil", fuelVO.getLimiteoil());
			atRow.setValue("carmodelcode", fuelVO.getCarmodelcode());
			
			Response response = atRow.save(null, null, null);
			if (response.isError()) {
				throw new Exception(response.getFirstErrorMessage());
			}
			return ajaxDoneSuccess();
		} catch (Exception e) {
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}

	}
}