/*
 * @(#) SiteController.java 2016年7月5日 下午4:45:06
 *
 * Copyright 2016 Catarc, Inc. All rights reserved.
 * Catarc PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ac.catarc.qj.function.PassStationService;

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
import com.mes.compatibility.functions.utility.formatLongTime;
import com.mes.compatibility.ui.DateTimePickerFormat;
import com.mes.compatibility.ui.Time;
import com.mes.compatibility.ui.plantscheduler.Calendar;
import com.mes.shopflow.common.services.objectretrieval.GetWorkOrderByName;
import com.mes.webclient.app.demo.api.PassStationServiceImpl;
import com.mes.webclient.constants.FunctionConstants;
import com.mes.webclient.constants.Message;
import com.mes.webclient.constants.PassStation;
import com.mes.webclient.proxy.WebServerProxy;
import com.mes.webclient.service.impl.IMService;
import com.mes.webclient.util.PrintChineseByBmp;
import com.mes.webclient.util.StringUtil;

@Controller("passStationController")
@RequestMapping("/passStation.sp")
public class PassStationController extends BaseController implements FunctionConstants
{
	@Autowired
	IMService imService;
	
	private static final String packageName = "cn.ac.catarc.qj.function";
	
	/**
	 * 打开扫描页面
	 */
	@RequestMapping(params = "toScanPage")
	public String toScanPage(){
		return "im/passStation/scan";
	}

	
	/**
	 * 打开扫描页面
	 */
	@RequestMapping(params = "toUploadScanPage")
	public String toUploadScanPage(){
		return "im/passStation/wbon_list_scan";
	}
	/**
	 * 打开电池上线扫描页面
	 */
	@RequestMapping(params = "toEleUploadScanPage")
	public String toEleUploadScanPage(){
		return "im/passStation/ele_upload_scan";
	}
	
	
	/**
	 * 打开电池上线扫描页面
	 */
	@RequestMapping(params = "toEleScanPage")
	public String toEleScanPage(){
		return "im/passStation/ele_scan";
	}
	
	/**
	 * 获得电池数据
	 */
	@RequestMapping(params = "getEleInformation")
	public @ResponseBody String getEleInformation(HttpServletRequest req, HttpServletResponse res){
		String vin = req.getParameter("vin");
		System.out.println(vin);
		HashMap<String, Object> map = null;
		Order order = null;
		try {
			String  sql = " select t.ele_id,t.ele_part_num , t.ele_part_desc , t.ele_order_num , t.start_time , t.entry_time  from ele_order t     where ele_id = '"+vin+"'  ";
		    Vector<String[]> vectorData = imService.getFunctions().getArrayDataFromActive(sql, 500);
		    if(vectorData.size() <= 0  ){
		    	map = generateResponseMap("电池包号【"+vin+"】在MES不存在");
		    	return JSONArray.fromObject(map).toString();
		    }
		    String[] array = vectorData.get(0);
		    map = generateResponseMap();
			map.put("ele_part_num", PassStation.convert2String( array[1] ));
			map.put("ele_order_num", PassStation.convert2String( array[3] ));
		} catch (MESException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return JSONArray.fromObject(map).toString();
	}

	/**
	 * 获得车辆数据
	 */
	@RequestMapping(params = "getCarInformation")
	public @ResponseBody String getCarInformation(HttpServletRequest req, HttpServletResponse res){
		String vin = req.getParameter("vin");
		System.out.println(vin);
		HashMap<String, Object> map = null;
		Order order = null;
		try {
			order = imService.getOrderByName(vin);
		} catch (MESException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//UTRow order = getOrderByVin(vin);
		if(null==order){
			map = generateResponseMap("VIN【"+vin+"】订单不存在");
		}
		else{
			map = generateResponseMap();
			try {
				map.put("order_number", PassStation.convert2String(order.getUDA("plan_id")));
			
				map.put("car_type", PassStation.convert2String(order.getUDA("car_type")));
				map.put("color", PassStation.convert2String(order.getUDA("color")));
				map.put("vehicle_configuration", PassStation.convert2String(order.getUDA("vehicle_configuration")));
				map.put("engine_type", PassStation.convert2String(order.getUDA("engine_type")));
				map.put("special_order", PassStation.convert2String(order.getUDA("special_order")));
			} catch (MESException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return JSONArray.fromObject(map).toString();
	}

	
	/**
	 * 电池包上线
	 */
	@RequestMapping(params = "eleUpload")
	public @ResponseBody String eleUpload(HttpServletRequest req, HttpServletResponse res){
		String vin = req.getParameter("vin");
		
		User user =WebServerProxy.getCurrentUser();
		String station ="";
		HashMap<String, Object> map = null;
		try {
			Object objStation = user.getUDA("station") ;
			if( objStation == null ){
				map = generateResponseMap("当前用户站点没有配置");
				return JSONArray.fromObject(map).toString();
			}
			station =objStation.toString();
			Time entryTime = imService.getFunctions().getDBTime();
			map = generateResponseMap(startAtOperation(vin, station, entryTime));
		}
		catch (MESException e) {
			map = generateResponseMap("过站异常:"+e.getMessage());
		}
		return JSONArray.fromObject(map).toString();
	}

	
	/**
	 * 过站
	 */
	@RequestMapping(params = "passStationByVinAndStation")
	public @ResponseBody String passStationByVinAndStation(HttpServletRequest req, HttpServletResponse res){
		String vin = req.getParameter("vin");
		
		User user =WebServerProxy.getCurrentUser();
		String station ="";
		HashMap<String, Object> map = null;
		try {
			station =user.getUDA("station").toString();
			Time entryTime = imService.getFunctions().getDBTime();
			
			map = generateResponseMap(startAtOperation(vin, station, entryTime));
		}
		catch (MESException e) {
			map = generateResponseMap("过站异常:"+e.getMessage());
		}
		return JSONArray.fromObject(map).toString();
	}

	
	
	/*
	 * 获取电池上线数据
	 * */
	@RequestMapping(params = "getElePassList")
	public @ResponseBody String getElePassList(HttpServletRequest req, HttpServletResponse res){
		HashMap<String, Object> map = null;
		try {
			User user =WebServerProxy.getCurrentUser();
			Object objStation = user.getUDA("station") ;
			if( objStation == null ){
				map = generateResponseMap("当前用户站点没有配置");
				return JSONArray.fromObject(map).toString();
			}
			String station = objStation.toString();
			String sql = " select t.ele_id,t.ele_part_num , t.ele_part_desc , t.ele_order_num , t.start_time , t.entry_time  from ele_order t  where station = '"+station+"' and entry_time >= to_char(sysdate , 'YYYY-MM-DD') order by entry_time desc    ";
			Vector<String[]> vectorData = imService.getFunctions().getArrayDataFromActive(sql, 500);
			map = generateResponseMap();
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> dataMap = null;
			for(String[] data : vectorData){
				dataMap = new HashMap<String, String>();
				dataMap.put("ele_id", PassStation.convert2String(data[0]));
				dataMap.put("ele_part_num", PassStation.convert2String(data[1]));
				dataMap.put("ele_part_desc", PassStation.convert2String(data[2]));
				dataMap.put("ele_order_num", PassStation.convert2String(data[3]));
				dataMap.put("start_time", PassStation.convert2String(data[4]));
				dataMap.put("entry_time", PassStation.convert2String(data[5]));
				list.add(dataMap);
			}
			map.put("list", list);
		}
		catch (MESException e) {
			map = generateResponseMap("取得车辆上线数据异常:"+e.getMessage());
		}
		return JSONArray.fromObject(map).toString();
	}
	
	
	/*
	 * 获取电池上线数据
	 * */
	@RequestMapping(params = "getEleUploadList")
	public @ResponseBody String getEleUploadList(HttpServletRequest req, HttpServletResponse res){
		HashMap<String, Object> map = null;
		try {
			String sql = " select t.ele_id,t.ele_part_num , t.ele_part_desc , t.ele_order_num , t.start_time , t.csn  from ele_order t  where station is null order by csn   ";
			Vector<String[]> vectorData = imService.getFunctions().getArrayDataFromActive(sql, 500);
			map = generateResponseMap();
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> dataMap = null;
			for(String[] data : vectorData){
				dataMap = new HashMap<String, String>();
				dataMap.put("ele_id", PassStation.convert2String(data[0]));
				dataMap.put("ele_part_num", PassStation.convert2String(data[1]));
				dataMap.put("ele_part_desc", PassStation.convert2String(data[2]));
				dataMap.put("ele_order_num", PassStation.convert2String(data[3]));
				dataMap.put("start_time", PassStation.convert2String(data[4]));
				dataMap.put("csn", PassStation.convert2String(data[5]));
				list.add(dataMap);
			}
			map.put("list", list);
		}
		catch (MESException e) {
			map = generateResponseMap("取得车辆上线数据异常:"+e.getMessage());
		}
		return JSONArray.fromObject(map).toString();
	}
	
	/*
	 * 读取焊接上线车辆列表
	 * */
	@RequestMapping(params = "getWbonUploadCarList")
	public @ResponseBody String getWbonUploadCarList(HttpServletRequest req, HttpServletResponse res){
		HashMap<String, Object> map = null;
		try {
			String sql = " select vin_S , car_project_S , car_type_S ,car_type_desc_S , color_S  , plan_id_S , plan_start_time_S ,vehicle_configuration_S ,special_order_S ,csn_S  from UDA_Order where  station_S  is null  order by csn_S  ";
			Vector<String[]> vectorData = imService.getFunctions().getArrayDataFromActive(sql, 500);
			map = generateResponseMap();
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> dataMap = null;
			for(String[] data : vectorData){
				dataMap = new HashMap<String, String>();
				dataMap.put("vin", PassStation.convert2String(data[0]));
				dataMap.put("car_project", PassStation.convert2String(data[1]));
				dataMap.put("car_type", PassStation.convert2String(data[2]));
				dataMap.put("car_type_desc", PassStation.convert2String(data[3]));
				dataMap.put("color", PassStation.convert2String(data[4]));
				dataMap.put("plan_id", PassStation.convert2String(data[5]));
				dataMap.put("plan_start_time", PassStation.convert2String(data[6]));
				dataMap.put("vehicle_configuration", PassStation.convert2String(data[7]));
				dataMap.put("special_order", PassStation.convert2String(data[8]));
				dataMap.put("csn", PassStation.convert2String(data[9]));
				list.add(dataMap);
			}
			map.put("list", list);
		}
		catch (MESException e) {
			map = generateResponseMap("取得车辆上线数据异常:"+e.getMessage());
		}
		return JSONArray.fromObject(map).toString();
	}
	
	/**
	 * 取得过站历史记录
	 */
	@RequestMapping(params = "getPassStationList")
	public @ResponseBody String getPassStationList(HttpServletRequest req, HttpServletResponse res){
		User user =WebServerProxy.getCurrentUser();
		String station ="";
		HashMap<String, Object> map = null;
		try {
			station =user.getUDA("station").toString();
			
	
			String sql = " select  m.vin_S,  m.csn_S,  m.entry_time_S, o.plan_id_S, o.car_type_S, o.color_S, o.vehicle_configuration_S, o.engine_type_S, o.special_order_S  "
	                   + "    from UD_MES_PM_CarMove m inner join UDA_Order o on m.vin_S = o.vin_S where 1=1 ";
			if(station!= null && !station.equals(""))
			{
				sql += " and m.station_S = N'" + station +  "'";
			
			}
			sql +=  " order by m.entry_time_S desc ";
		
			Vector<String[]> vectorData = imService.getFunctions().getArrayDataFromActive(sql, 500);
			map = generateResponseMap();
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> dataMap = null;
			for(String[] data : vectorData){
				dataMap = new HashMap<String, String>();
				dataMap.put("vin", PassStation.convert2String(data[0]));
				dataMap.put("csn", PassStation.convert2String(data[1]));
				dataMap.put("entry_time", PassStation.convert2String(data[2]));
				dataMap.put("order_number", PassStation.convert2String(data[3]));
				dataMap.put("car_type", PassStation.convert2String(data[4]));
				dataMap.put("color", PassStation.convert2String(data[5]));
				dataMap.put("vehicle_configuration", PassStation.convert2String(data[6]));
				dataMap.put("engine_type", PassStation.convert2String(data[7]));
				dataMap.put("special_order", PassStation.convert2String(data[8]));
				list.add(dataMap);
			}
			map.put("list", list);
		}
		catch (MESException e) {
			map = generateResponseMap("取得过站历史记录异常:"+e.getMessage());
		}
		return JSONArray.fromObject(map).toString();
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

	/**
	 * test
	 */
	@RequestMapping(params = "test")
	public void test()
	{
		try {
			String vin = "LXXX6XXXXXX000001";
			String station = "焊接上线";
			UTRowFilter filter = imService.getFunctions().createATRowFilter("Car");
			Vector<ATRow> vectorData = filter.exec();
			filter.forColumnNameEqualTo("vin", vin);
			if(vectorData.size()<=0){
				UTHandler atHandler = imService.getFunctions().createATHandler("Car");
				UTRow order = atHandler.createATRow();
				order.setValue("vin", vin);
				order.setValue("unit_key", Long.parseLong("-1"));
				order.save(imService.getFunctions().getDBTime(), "", null);
			}
			Time entryTime = imService.getFunctions().getDBTime();
			System.out.println(startAtOperation(vin, station, entryTime));
		} catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param vin
	 * @param station
	 * @param entryTime
	 * @return
	 */
	private String startAtOperation(String vin, String station, Time entryTime){
		try {
			Station stationTemp = imService.getStationByName(station);
			
			String listName = stationTemp.getUDA(0).toString();
			HashMap<String, Object> parameterMap = generateParameterMap(vin, station, entryTime);
			Message m = new Message();
			List<String> functionList = getFunctionListByStation(station, vin,listName);
			String functionName = "ExecPassStationFunction";
			for(String function : functionList){
				//if(!isFunctionOkByVinAndStation(vin, station, function)){
					Class clazz = Class.forName(function);
					Object obj = clazz.newInstance();
					Method method = clazz.getMethod(functionName, java.util.HashMap.class);
					method.invoke(obj, parameterMap);
					//method = clazz.getDeclaredMethod("run", HashMap.class );
					//m = (Message) method.invoke(obj);
					//saveCarPassStationLog(vin, station, function, m);
//					PassStationServiceImpl ps =  new PassStationServiceImpl();
//					ps.ExecPassStationFunction(parameterMap);
					if(m.isError()){
						break;
					}
				//}
			}
			return m.toString();
		}
        catch (Exception e) {
			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】在工位【"+station+"】过站异常:"+e.getMessage()).toString();
		}
	}
	
	/**
	 * @param station
	 * @param vin
	 * @return
	 * @throws Exception
	 */
	private List<String> getFunctionListByStation(String station, String vin,String listName) throws Exception{
		try {
			DsList passStationFunctionDsList = imService.getFunctions().getList(listName);
			List<Object> passStationFunctionList = passStationFunctionDsList.getItems();
			List<String> functionList = new ArrayList<String>();
			for(Object passStationFunction : passStationFunctionList){
				String[] function = PassStation.convert2String(passStationFunction).split(":");
				//if(function.length>1){
					//String functionName = function[0];
					//TODO
					//if(true){
				if(!PassStation.convert2String(passStationFunction).equals(""))
				{	
					functionList.add(PassStation.convert2String(passStationFunction));
				}
					//}
				//}
			}
			return functionList;
		}
		catch (MESException e) {
			throw new Exception("VIN【"+vin+"】在工位【"+station+"】取得功能列表异常:"+e.getMessage());
		}
	}
	
	/**
	 * @param vin
	 * @param station
	 * @param function
	 * @return
	 */
	private boolean isFunctionOkByVinAndStation(String vin, String station, String function){
		try {
			UTRowFilter filter = imService.getFunctions().createATRowFilter("MES_PM_CarPassLog");
			filter.forColumnNameEqualTo("vin", vin);
			filter.forColumnNameEqualTo("station", station);
			filter.forColumnNameEqualTo("function", function);
			Vector<ATRow> vectorData = filter.exec();
			if(vectorData.size()>0){
				return FunctionConstants.FUNCTION_RUN_RESULT_OK.equals(vectorData.get(0).getValue("is_ok"));
			}
			else{
				return false;
			}
		} catch (MESException e) {
			return false;
		}
	}
	
	/**
	 * @param vin
	 * @param station
	 * @param entryTime
	 * @return
	 */
	private HashMap<String, Object> generateParameterMap(String vin, String station, Time entryTime) throws Exception{
		//UTRow order = getOrderByVin(vin);
		Order order = null;
		if( vin != null && vin.length() == 17){
			order = imService.getOrderByName(vin);
			if(null==order){
				throw new Exception(new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】订单不存在").toString());
			}
		}
		User user =WebServerProxy.getCurrentUser();
		
		String time=formatTimeByDateFormat(entryTime,"yyyy-MM-dd HH:mm:ss");
	
		String stage = getStageByStation(station);
		if(StringUtil.isNull(stage)){
			throw new Exception(new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】在工位【"+station+"】stage不存在").toString());
		}
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put(FunctionConstants.PARAMETER_TYPE_VIN, vin);
		parameterMap.put(FunctionConstants.PARAMETER_TYPE_ORDER, order);
		parameterMap.put(FunctionConstants.PARAMETER_TYPE_STATION, station);
		parameterMap.put(FunctionConstants.PARAMETER_TYPE_ENTRYTIME, time);
		parameterMap.put(FunctionConstants.PARAMETER_TYPE_STAGE, stage);
		parameterMap.put(FunctionConstants.USER_NAME, user.getName());
		return parameterMap;
	}
	
	/**
	 * @param vin
	 * @param station
	 * @param function
	 * @param message
	 * @throws MESException
	 */
	private void saveCarPassStationLog(String vin, String station, String function, Message message) throws MESException{
		UTRowFilter filter = imService.getFunctions().createATRowFilter("MES_PM_CarPassLog");
		filter.forColumnNameEqualTo("vin", vin);
		filter.forColumnNameEqualTo("station", station);
		filter.forColumnNameEqualTo("function", function);
		Vector<UTRow> vectorData = filter.exec();
		UTRow atRow = null;
		if(vectorData.size()>0){
			atRow = vectorData.get(0);
		}
		else{
			UTHandler atHandler = imService.getFunctions().createATHandler("MES_PM_CarPassLog");
			atRow = atHandler.createATRow();
		}
		atRow.setValue("vin", vin);
		atRow.setValue("station", station);
		atRow.setValue("function", function);
		atRow.setValue("stage", getStageByStation(station));
		if(message.isOk()){
			atRow.setValue("is_ok", FunctionConstants.FUNCTION_RUN_RESULT_OK);
		}
		else{
			atRow.setValue("is_ok", FunctionConstants.FUNCTION_RUN_RESULT_NG);
		}
		atRow.save(imService.getFunctions().getDBTime(), "", null);
	}
	
	//TODO
	private static String getStageByStation(String station){
		return "1010";
	}
	
	/**
	 * @param vin
	 * @return
	 */
	private UTRow getOrderByVin(String vin){
		try {
			UTRowFilter filter = imService.getFunctions().createATRowFilter("Car");
			filter.forColumnNameEqualTo("vin", vin);
			Vector<UTRow> vectorData = filter.exec();
			if(vectorData.size()>0){
				return vectorData.get(0);
			}
			else{
				return null;
			}
		} catch (MESException e) {
			return null;
		}
	}
	
	/**
	 * Description: use java.text.SimpleDateFormat to format time
	 * example: yyyy-MM-dd HH:mm:ss = 2010-10-20 15:23:44, yyyy-MM-dd = 2010-10-20
	 * @param Time； time 
	 * @return String: string of format Time
	 **/
	private String formatTimeByDateFormat(Time time,String mask){
	    String stringOfDate = "";
	    if(time != null){
	      java.util.Calendar  c = java.util.Calendar.getInstance();
	        c.set(time.getYear(), time.getMonth() -1, time.getDay(), time.getHour(), time.getMinute(),time.getSecond());
	       Date date = c.getTime();
	        SimpleDateFormat sformatter = new java.text.SimpleDateFormat(mask);
	        stringOfDate = sformatter.format(date);
	    }
	    
	    return stringOfDate;
	}
	
	@RequestMapping(params = "printBarcodeTxt")
	public @ResponseBody String printBarcodeTxt(HttpServletRequest req, HttpServletResponse res)   {
		HashMap<String, Object> map = null;
		try{
			IMService imservice = new IMService();
			String vin = req.getParameter("vin");
			Order order = imservice.getOrderByName(vin);
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
		//	obj.runPrint(command ,printerName);    //打印条码 
			System.out.println(command);
			map = generateResponseMap(command);
		}catch ( Exception ex){
			map = generateResponseMap(ex.getMessage());
		}
		return JSONArray.fromObject(map).toString();
	}
}