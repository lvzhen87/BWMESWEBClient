
package com.mes.webclient.controller;

import java.util.Vector;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.webclient.controller.vo.OperationDefectCodeVO;
import com.mes.webclient.service.impl.IMService;

@Controller("DefectQueryController")
@RequestMapping("/DefectQueryController.sp")
public class DefectQueryController extends BaseController
{
	@Autowired
	IMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(OperationDefectCodeVO odcVO , Model model)
	{

		model.addAttribute(VIEW_OBJECT, odcVO);
		return "im/defectQuery/list";
	}


	/**
	 * 查询
	 */
	@ResponseBody
	@RequestMapping(params = "toList")
	public String queryList( OperationDefectCodeVO odcVO )
	{
		JSONArray rt = null;
		try
		{
			String vin = odcVO.getVin();
			String qualityGate_1 = odcVO.getQualityGate_1();
			String qualityGate_2 = odcVO.getQualityGate_2();
			String resParty = odcVO.getResponsibleParty();
			//工厂分类
			String sql = " select  d.atr_key, f.brand_name_S ,f.factory_name_S ,'business_unit', d.vin_S ,'body_num','order_num' , quality_gate_1_S,quality_gate_2_S ,  isnull( part_level1_S ,'') + '/' + isnull( part_level2_S ,'') +'/' +isnull( part_level3_S ,'') +'/'+isnull( part_level4_S ,'') +'/'+isnull( part_level5_S ,'')  +'/'+ isnull( level1_S ,'')     +'/'+ isnull( level2_S ,'')  ,defect_level_S ,responsible_party_S    ,d.input_user_S ,  CONVERT (varchar,d.input_time_T ,120 )     from UD_defect_car d ,UD_factory_brand f  WHERE  d.factory_brand_key_I = f.atr_key and  status_S= '1'  ";
		//	String sql = " select  d.atr_key, f.brand_name_S ,f.factory_name_S ,'', d.VIN_S ,(select csn_s from UDA_order u where u.vin_S = d.vin_S) ,(select plan_id_S from UDA_Order u where u.vin_S = d.vin_S) , quality_gate_1_S,quality_gate_2_S ,  isnull( part_level1_S ,'') || '/' || isnull( part_level2_S ,'') ||'/' ||isnull( part_level3_S ,'') ||'/'||isnull( part_level4_S ,'') ||'/'||isnull( part_level5_S ,'')  ||  '/'|| isnull( level1_S ,'')     ||'/'|| isnull( level2_S ,'')  ,defect_level_S ,responsible_party_S    ,d.input_user_S ,   d.input_time_T      from UD_defect_car d ,UD_factory_brand f  WHERE  d.factory_brand_key_I = f.atr_key and  status_S= '1' ";
			if( vin != null && !"".equals( vin ) ){
				sql = sql + "  and vin_S like '%"+vin+"%' ";
			}
			
			if( qualityGate_1 != null && !"".equals(qualityGate_1) ){
				sql = sql + "  and quality_gate_1_S like '%"+qualityGate_1+"%' ";
			}
			
			if( qualityGate_2 != null && !"".equals(qualityGate_2 ) ){
				sql = sql + "  and quality_gate_2_S like '%"+qualityGate_2+"%' ";
			}
			
			if( resParty != null && !"".equals( resParty ) ){
				sql = sql + "  and responsible_party_S like '%"+resParty+"%' ";
			}
			
           
			
			Vector<String[]> vectorDual = imService.getFunctions().getArrayDataFromActive(sql, 100);
			rt = JSONArray.fromObject(vectorDual);
			System.out.println(rt.toString());
			return rt.toString();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;

	}

	 
}