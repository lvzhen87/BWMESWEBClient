/*
 * @(#) SiteController.java 2016年7月5日 下午4:45:06
 *
 * Copyright 2016 Catarc, Inc. All rights reserved.
 * Catarc PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.ATHandler;
import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.ATRowFilter;
import com.mes.compatibility.client.DsList;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Order;
import com.mes.compatibility.client.Station;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.User;
import com.mes.compatibility.functions.orderstep.getOrderStepByName;
import com.mes.compatibility.ui.Time;
import com.mes.shopflow.common.services.objectretrieval.GetWorkOrderByName;
import com.mes.webclient.constants.FunctionConstants;
import com.mes.webclient.constants.Message;
import com.mes.webclient.constants.PassStation;
import com.mes.webclient.controller.vo.PassStationHistoryVO;
import com.mes.webclient.proxy.WebServerProxy;
import com.mes.webclient.service.impl.IMService;
import com.mes.webclient.util.StringUtil;

@Controller("passStationHistoryController")
@RequestMapping("/passStationHistory.sp")
public class PassStationHistoryController extends BaseController implements FunctionConstants
{
	@Autowired
	IMService imService;
	
	private static final String packageName = "cn.ac.catarc.qj.function";
	
	private static final String utName = "MES_PM_CarMove";
	/**
	 * 打开扫描页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toScanPage( Model model){
		List<String> stationList = new ArrayList<String>();
		String sql = "select station from STATION";
		try {
			Vector<String[]> vectorData = imService.getFunctions().getArrayDataFromActive(sql, 500);
			for(String[] data : vectorData){
				if(data!= null && data.length>0)
				{
					stationList.add(data[0]);
				
				}
			}
			model.addAttribute(
					"stationlist", stationList);
		} catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "im/passStation/listHistory";
	}

	/**
	 * 查询
	 */
	@ResponseBody
	@RequestMapping(params = "toList")
	public String query(PassStationHistoryVO passStationHistoryVO)
	{
		HashMap<String, Object> map = null;
		Vector<PassStationHistoryVO> vector = new Vector<>();
		try {
			String username = passStationHistoryVO.getUsername();
			String station_S = passStationHistoryVO.getStation();
			String stationend_S = passStationHistoryVO.getStation_end();
			Station station = imService.getStationByName(station_S);
			Station stationend = imService.getStationByName(stationend_S);
			String timeStart = passStationHistoryVO.getEntry_time();
			String timeEnd = passStationHistoryVO.getEntry_time_end();
			String vin = passStationHistoryVO.getVin();
			String carType = passStationHistoryVO.getCar_type();
			String color =passStationHistoryVO.getColor();
			String orderNum = passStationHistoryVO.getOrder_number();
			String sql = "     select a.atr_key,a.car_type_S,a.vin_S,a.color_S,a.csn_S,a.entry_time_S,a.special_order_S,a.username_S,a.vehicle_configuration_S,a.station_S,a.order_number_S "
							+ " from UD_MES_PM_CarMove a, STATION b where  a.station_S = b.station ";
			PassStationHistoryVO passTemp ;
			if((station_S!= null && !station_S.equals("")) || (stationend_S!= null && !stationend_S.equals("")))
			{
				sql += " and  b.uda_1 is not null" ;
			}
			
			if(station_S!= null && !station_S.equals(""))
			{
				sql += " and   to_number(b.uda_1) >= " + station.getUDA(1);
			}
			if(stationend_S!= null && !stationend_S.equals(""))
			{
				sql += " and   to_number(b.uda_1) <= " + stationend.getUDA(1);
			}
			if(timeStart!= null && !timeStart.equals(""))
			{
				sql += " and   a.entry_time_S > '" + timeStart + "'";
			}
			if(timeEnd!= null && !timeEnd.equals(""))
			{
				sql += " and   a.entry_time_S < '" + timeEnd + "'";
			}
			if(username!= null && !username.equals(""))
			{
				sql += " and   a.username_S like '%" + username + "%'";
			}
			if(vin!= null && !vin.equals(""))
			{
				sql += " and   a.vin_S like '%" + vin + "%'";
			}
			if(carType!= null && !carType.equals(""))
			{
				sql += " and   a.car_type_S like '%" + carType + "%'";
			}
			if(color!= null && !color.equals(""))
			{
				sql += " and   a.color_S like '%" + color + "%'";
			}
			if(orderNum!= null && !orderNum.equals(""))
			{
				sql += " and   a.order_number_S like '%" + orderNum + "%'";
			}
			sql +=  " order by a.csn_S desc ";
			
			Vector<String[]> vectorData = imService.getFunctions().getArrayDataFromActive(sql, 500);
			
			map = generateResponseMap();
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> dataMap = null;
			for(String[] data : vectorData){
				
				passTemp = new PassStationHistoryVO();
				passTemp.setVin(data[2]);
				passTemp.setKey(Integer.valueOf(data[0]));
				passTemp.setColor(data[3]);
				passTemp.setCar_type(data[1]);
				passTemp.setCsn(data[4]);
				passTemp.setEntry_time(data[5]);
				passTemp.setSpecial_order(data[6]);
				passTemp.setUsername(data[7]);
				passTemp.setVehicle_configuration(data[8]);
				passTemp.setStation(data[9]);
				passTemp.setOrder_number(data[10]);
				
				vector.add(passTemp);
			}
			map.put("list", vector);
		}
		catch (MESException e) {
			map = generateResponseMap("取得过站历史记录异常:"+e.getMessage());
		}
		return JSONArray.fromObject(vector).toString();
	}
	
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