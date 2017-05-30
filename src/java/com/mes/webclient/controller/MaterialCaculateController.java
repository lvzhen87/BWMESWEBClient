package com.mes.webclient.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ac.catarc.qj.util.StringUtil;
import cn.ac.catarc.qj.vo.PlanVO;

import com.mes.admin.dataobjects.filter.IFilterSortOrders;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.ui.Time;
import com.mes.shopflow.common.constants.filtering.IATRowFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.shopflow.common.constants.filtering.IListFilterAttributes;
import com.mes.webclient.controller.vo.MaterialCaculateVO;
import com.mes.webclient.controller.vo.MaterialTrackConfigVO;
import com.mes.webclient.controller.vo.MaterialTrackScanVO;
import com.mes.webclient.proxy.WebServerProxy;
import com.mes.webclient.service.impl.IMService;

@Controller("MaterialCaculateController")
@RequestMapping("/materialCaculate.sp")
public class MaterialCaculateController extends BaseController
{
	@Autowired
	IMService imService;
	
	/**
	 * 打开扫描页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(){
		return "im/materailCaculate/materialCaculatelistAll";
	}

	/**
	 * 查询
	 * 
	 * @param orderVO
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "planList")
	public String planList(PlanVO planVO){
		String planId = planVO.getPlanId();
		String planStartTime = planVO.getPlanStartTime() ;
		String planEndTime   = planVO.getPlanEndTime()   ;
		JSONArray  rt=null;
		try
		{
		UTRowFilter utRowFilter =imService.createAtRowFilter("Interface_ERP_Order");
		if(!"".equals(planId)){
			utRowFilter.forColumnNameContaining("plan_id", planId);
		}
		if(!"".equals( planStartTime )){
			utRowFilter.forColumnNameGreaterThan("plan_start_time", planStartTime) ;
		}
		if( !"".equals(  planEndTime )){
			utRowFilter.forColumnNameLessThan("plan_start_time", planEndTime) ;
		}
		
		utRowFilter.addOrderBy(IATRowFilterAttributes.CREATIONTIME, IFilterSortOrders.DESCENDING);
	    Vector<UTRow> plans = utRowFilter.exec();
	    ArrayList<PlanVO> vos = new ArrayList<>();
	    for (int i = 0; i < plans.size(); i++)
		{
	    	PlanVO vo = new PlanVO();
	    	UTRow row = plans.get(i);
			vo.setKey(plans.get(i).getKey());
			vo.setPlanId(StringUtil.toString(row.getValue("plan_id")));
			vo.setPlanStartTime(StringUtil.toString(row.getValue("plan_start_time")));
			vo.setPlanEndTime(StringUtil.toString(row.getValue("plan_end_time")));
			vo.setVsn(StringUtil.toString(row.getValue("vsn")));
			vo.setQuantity(StringUtil.toString(row.getValue("quantity")));
			vo.setComment(StringUtil.toString(row.getValue("comment")));
			vo.setStatus(StringUtil.transferPlanStatus(StringUtil.toString(row.getValue("status"))));
			vo.setVsnDesc( StringUtil.toString(row.getValue("vsn_desc") ) );
			vo.setLineId(  StringUtil.toString(row.getValue("line_id") )    );
			vo.setCarProject( StringUtil.toString(row.getValue("car_project") )     );
			vo.setVin8( StringUtil.toString(row.getValue("vin8") )     );
			vo.setColor(StringUtil.toString(row.getValue("color") ) );
			vo.setEngineType(StringUtil.toString(row.getValue("engine_type") ) );
			vo.setSpecialOrder(StringUtil.toString(row.getValue("special_order") ));

			 

			
			vos.add(vo);
		}
	    rt=JSONArray.fromObject(vos);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return rt.toString();
	}

	/**
	 * 查询扫描信息
	 */
	@ResponseBody
	@RequestMapping(params = "toQuery")
	public String toQuery(MaterialCaculateVO materialCaculateVO, Model model){
		try{
			String orderNumber = materialCaculateVO.getPlanId();
			String startDate = materialCaculateVO.getPlanStartTime();
			String endDate = materialCaculateVO.getPlanEndTime();
			String sql = "  select  b.part_num_s   , (select part_desc_s from ud_order_bom  ub where ub.part_num_s = b.part_num_s and rownum =  1  )  , sum(b.quantity_i) "+
						 " from ud_interface_erp_order o , ud_order_bom b "+ 
						 " where  plan_id_s = order_num_s  " ; 
			if( orderNumber != null && ! "".equals(orderNumber)  ){
				sql = sql + " and  plan_id_s = '"+orderNumber+"' ";
			}
			if( startDate != null && !startDate.equals("")){
				sql= sql + " and plan_start_time_s >= '"+startDate+"' " ; 
			}
		    if( endDate != null && !endDate.equals("")){
				sql= sql + " and plan_start_time_s <= '"+endDate+"' " ; 
			}
			sql = sql + "    group by  b.part_num_s  " ;	
			Vector<String[]> bomRequest = imService.getArrayDataFromActive(sql);
			Vector<MaterialCaculateVO> vos = new Vector<MaterialCaculateVO>();
			for (String[] array : bomRequest) {
				MaterialCaculateVO bomReqVO = new MaterialCaculateVO(); 
				String partNum = array[0];
				String partDesc = array[1];
				String partReqCount = array[2];
				bomReqVO.setPartNum(partNum);
				bomReqVO.setPartDesc(partDesc);
				bomReqVO.setPartReqCount(partReqCount);
				vos.add(bomReqVO);
			}
			JSONArray rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e){
			logger.error(e);
		}
		return null;
	}

	
}