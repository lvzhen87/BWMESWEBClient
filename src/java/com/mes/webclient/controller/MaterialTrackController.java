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

import com.mes.admin.dataobjects.filter.IFilterSortOrders;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.ui.Time;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.shopflow.common.constants.filtering.IListFilterAttributes;
import com.mes.webclient.controller.vo.MaterialTrackConfigVO;
import com.mes.webclient.controller.vo.MaterialTrackScanVO;
import com.mes.webclient.proxy.WebServerProxy;
import com.mes.webclient.service.impl.IMService;

@Controller("MaterialTrackController")
@RequestMapping("/materialTrack.sp")
public class MaterialTrackController extends BaseController
{
	@Autowired
	IMService imService;
	
	/**
	 * 打开扫描页面
	 */
	@RequestMapping(params = "toScanPage")
	public String toScanPage(){
		return "im/materialTrack/scan";
	}
	/**
	 * 生成扫描列表
	 */
	private String generateElectricKeyList(String parentBarCode, String station){
		try {
			UTHandler atHandler = imService.getFunctions().createATHandler("QM_MT_Scan");
			String partPartNumber = parentBarCode.substring(0, 11) ;
			String sql = "select SUN_PART_NUMBER_S ,SUN_PART_DESC_S ,station_s from  ud_electric_bom   WHERE  PARENT_PART_NUMBER_S = '"+partPartNumber+"' and station_s = '"+station+"' ";
			Vector<String[]> vectorData = imService.getArrayDataFromActive(sql);
			String partNumber = "";
			for(String[] data: vectorData){
				partNumber  = data[0];
				String desc =  data[1];
				UTRow atRow = atHandler.createATRow();
				atRow.setValue("vin", parentBarCode);
				atRow.setValue("station", station);
				atRow.setValue("part_number", partNumber);
				atRow.setValue("barcode", "");
				atRow.setValue("description", desc);
				Response response = atHandler.save(imService.getFunctions().getDBTime(), "", "");
				if(response.isError()){
					return "VIN【"+parentBarCode+"】在工位【"+station+"】生成扫描列表失败:"+response.getFirstErrorMessage();
				}
			}
		}
        catch (MESException e) {
			return "VIN【"+parentBarCode+"】在工位【"+station+"】生成扫描列表失败:"+e.getMessage();
		}
		return "";
	}
	
	@RequestMapping(params = "getElectricKeyList")
	public @ResponseBody String getElectricKeyList(HttpServletRequest req, HttpServletResponse res){
		String parentBarCode = req.getParameter("vin");
		String station = WebServerProxy.getCurrentUser().getUDA(2);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(!hasList(parentBarCode, station)){
			String message = generateElectricKeyList(parentBarCode, station);
			if(isNotNullAndEmpty(message)){
				map = generateResponseMap(message);
				return JSONArray.fromObject(map).toString();
			}
		}
		Vector<UTRow> vectorData = getListByVinAndStation(parentBarCode, station);
		if(vectorData.size()<=0){
			map = generateResponseMap("父物料【"+parentBarCode+"】在工位【"+station+"】没有关键件需要扫描");
		}
		else{
			map = generateResponseMap();
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> m = new HashMap<String, String>();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(UTRow atRow: vectorData){
				m = new HashMap<String, String>();
				m.put("key", object2String(atRow.getKey()));
				m.put("description", object2String(atRow.getValue("description")));
				m.put("part_number", object2String(atRow.getValue("part_number")));
				m.put("barcode", object2String(atRow.getValue("barcode")));
				m.put("scan_user", object2String(atRow.getValue("scan_user")));
				Object scanTime = atRow.getValue("scan_time");
				if(scanTime==null){
					m.put("scan_time", "");
				}
				else{
					m.put("scan_time", df.format(((Time)scanTime).getCalendar().getTime()));
				}
				list.add(m);
			}
			map.put("LIST", list);
		}
        return JSONArray.fromObject(map).toString();
	}
	
	/**
	 * 获得数据列表
	 */
	@RequestMapping(params = "getScanList")
	public @ResponseBody String getScanList(HttpServletRequest req, HttpServletResponse res){
		String vin = req.getParameter("vin");
		String station = WebServerProxy.getCurrentUser().getUDA(2);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(!hasList(vin, station)){
			String message = generateList(vin, station);
			if(isNotNullAndEmpty(message)){
				map = generateResponseMap(message);
				return JSONArray.fromObject(map).toString();
			}
		}
		Vector<UTRow> vectorData = getListByVinAndStation(vin, station);
		if(vectorData.size()<=0){
			map = generateResponseMap("VIN【"+vin+"】在工位【"+station+"】没有关键件需要扫描");
		}
		else{
			map = generateResponseMap();
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> m = new HashMap<String, String>();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(UTRow atRow: vectorData){
				m = new HashMap<String, String>();
				m.put("key", object2String(atRow.getKey()));
				m.put("description", object2String(atRow.getValue("description")));
				m.put("part_number", object2String(atRow.getValue("part_number")));
				m.put("barcode", object2String(atRow.getValue("barcode")));
				m.put("scan_user", object2String(atRow.getValue("scan_user")));
				Object scanTime = atRow.getValue("scan_time");
				if(scanTime==null){
					m.put("scan_time", "");
				}
				else{
					m.put("scan_time", df.format(((Time)scanTime).getCalendar().getTime()));
				}
				list.add(m);
			}
			map.put("LIST", list);
		}
        return JSONArray.fromObject(map).toString();
	}

	/**
	 * 判断是否有扫描列表
	 */
	private boolean hasList(String vin, String station){
		try {
			UTRowFilter filter = imService.getFunctions().createATRowFilter("QM_MT_Scan");
			filter.forColumnNameEqualTo("vin", vin);
			filter.forColumnNameEqualTo("station", station);
			Vector<UTRow> vectorData = filter.exec();
			if(vectorData.size()>0){
				return true;
			}
		}
        catch (MESException e) {
			return false;
		}
		return false;
	}
	
	
	/**
	 * 生成扫描列表
	 */
	private String generateList(String vin, String station){
		try {
			UTHandler atHandler = imService.getFunctions().createATHandler("QM_MT_Scan");
			String sql = "select mc.part_number_S,mc.station_S,ob.order_num_S,ob.part_desc_S from (select * from  UD_QM_MT_Config where station_S = N'"
			+station+"') mc "+
					",(select * from UD_order_bom where 1=1 and order_num_S =      ( select plan_id_S  from UDA_Order where vin_S =   '"+vin+"' )    ) ob where  ob.part_num_S  = mc.part_number_S";
//			UTRowFilter configFilter = imService.getFunctions().createATRowFilter("QM_MT_Config");
//			configFilter.forColumnNameEqualTo("station", station);
			Vector<String[]> vectorData = imService.getArrayDataFromActive(sql);
			UTRowFilter bomFilter = null;
			String partNumber = "";
			for(String[] data: vectorData){
				partNumber = data[0];

				UTRow atRow = atHandler.createATRow();
				atRow.setValue("vin", vin);
				atRow.setValue("station", station);
				atRow.setValue("part_number", partNumber);
				atRow.setValue("barcode", "");
				atRow.setValue("description", data[3]);
				Response response = atHandler.save(imService.getFunctions().getDBTime(), "", "");
				if(response.isError()){
					return "VIN【"+vin+"】在工位【"+station+"】生成扫描列表失败:"+response.getFirstErrorMessage();
				}
			}
		}
        catch (MESException e) {
			return "VIN【"+vin+"】在工位【"+station+"】生成扫描列表失败:"+e.getMessage();
		}
		return "";
	}
	
	/*private String generateList(String vin, String station){
		try {
			UTHandler atHandler = imService.getFunctions().createATHandler("QM_MT_Scan");

			UTRowFilter configFilter = imService.getFunctions().createATRowFilter("QM_MT_Config");
			configFilter.forColumnNameEqualTo("station", station);
			Vector<UTRow> vectorData = configFilter.exec();
			UTRowFilter bomFilter = null;
			String partNumber = "";
			for(UTRow data: vectorData){
				partNumber = object2String(data.getValue("part_number"));
				bomFilter = imService.getFunctions().createATRowFilter("QM_MT_BOM");
				bomFilter.forColumnNameEqualTo("vin", vin);//根据订单号查询
				bomFilter.forColumnNameEqualTo("part_number", partNumber);
				if(bomFilter.exec().size()>0){
					UTRow atRow = atHandler.createATRow();
					atRow.setValue("vin", vin);
					atRow.setValue("station", station);
					atRow.setValue("part_number", partNumber);
					atRow.setValue("barcode", "");
					atRow.setValue("description", object2String(data.getValue("description")));
					Response response = atHandler.save(imService.getFunctions().getDBTime(), "", "");
					if(response.isError()){
						return "VIN【"+vin+"】在工位【"+station+"】生成扫描列表失败:"+response.getFirstErrorMessage();
					}
				}
			}
		}
        catch (MESException e) {
			return "VIN【"+vin+"】在工位【"+station+"】生成扫描列表失败:"+e.getMessage();
		}
		return "";
	}*/

	/**
	 * 取得扫描列表
	 */
	private Vector<UTRow> getListByVinAndStation(String vin, String station){
		try {
			UTRowFilter filter = imService.getFunctions().createATRowFilter("QM_MT_Scan");
			filter.forColumnNameEqualTo("vin", vin);
			filter.forColumnNameEqualTo("station", station);
			Vector<UTRow> vectorData = filter.exec();
			return vectorData;
		}
        catch (MESException e) {
			return new Vector<UTRow>();
		}
	}

	/**
	 * 扫描关键件
	 */
	@RequestMapping(params = "scan")
	public @ResponseBody String scan(HttpServletRequest req, HttpServletResponse res){
		String vin = req.getParameter("vin");
		String station = WebServerProxy.getCurrentUser().getUDA(2);
		String barcode = req.getParameter("barcode");
		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			String otherVin = validateUnique(barcode);
			if(isNullOrEmpty(otherVin)){
				Vector<UTRow> vectorData = getListByVinAndStation(vin, station);
				String partNumber = "";
				map = generateResponseMap("VIN【"+vin+"】在工位【"+station+"】没有匹配【"+barcode+"】的关键件");
				for(UTRow atRow: vectorData){
					if(isNotNullAndEmpty(object2String(atRow.getValue("barcode")))){
						continue;
					}
					partNumber = object2String(atRow.getValue("part_number"));
					if(isNotNullAndEmpty(partNumber) && barcode.startsWith(partNumber)){
						map = generateResponseMap();
						atRow.setValue("barcode", barcode);
						atRow.setValue("scan_user", imService.getFunctions().getCurrentUser().getName());
						atRow.setValue("scan_time", imService.getFunctions().getDBTime());
						atRow.save(imService.getFunctions().getDBTime(), "", null);
						break;
					}
				}
				
//				vectorData = getListByVinAndStation(vein, station);
//				if(vectorData.size()<=0){
//					map = generateResponseMap("VIN【"+vin+"】在工位【"+station+"】没有关键件需要扫描");
//				}
//				else{
//					map = generateResponseMap();
//					map.put("DATA", vectorData);
//				}
			}
			else{
				map = generateResponseMap("VIN【"+vin+"】在工位【"+station+"】扫描【"+barcode+"】已经被VIN【"+otherVin+"】扫描");
			}
		}
        catch (MESException e) {
        	map = generateResponseMap("VIN【"+vin+"】在工位【"+station+"】扫描【"+barcode+"】保存失败:"+e.getMessage());
		}

		return JSONArray.fromObject(map).toString();
	}

	/**
	 * 重扫关键件
	 */
	@RequestMapping(params = "deleteBarcode")
	public @ResponseBody String deleteBarcode(HttpServletRequest req, HttpServletResponse res){
		String key = req.getParameter("key");
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			UTRowFilter filter = imService.getFunctions().createATRowFilter("QM_MT_Scan");
			filter.forUTRowKeyEqualTo(Long.parseLong(key));
			Vector<UTRow> vectorData = filter.exec();
			UTRow atRow = vectorData.get(0);
			atRow.setValue("barcode", "");
			atRow.setValue("scan_user", "");
			atRow.setValue("scan_time", null);
			Response response = atRow.save(imService.getFunctions().getDBTime(), "", null);
			if(response.isError()){
				map = generateResponseMap("重扫关键件【"+key+"】失败:"+response.getFirstErrorMessage());
			}
			else{
				map = generateResponseMap();
			}
		}
        catch (MESException e) {
        	map = generateResponseMap("重扫关键件【"+key+"】失败:"+e.getMessage());
		}
		return JSONArray.fromObject(map).toString();
	}
	
	/**
	 * 打开配置页面
	 */
	@RequestMapping(params = "toConfigPage")
	public String toConfigPage(){
		return "im/materialTrack/config";
	}

	/**
	 * 查询配置信息
	 */
	@ResponseBody
	@RequestMapping(params = "toConfig")
	public String toConfig(MaterialTrackConfigVO materialTrackConfigVO, Model model){
		try{
			String station = materialTrackConfigVO.getStation();
			String partNumber = materialTrackConfigVO.getPartNumber();
			String description = materialTrackConfigVO.getDescription();
			UTRowFilter filter = imService.getFunctions().createATRowFilter("QM_MT_Config");
			if(isNotNullAndEmpty(station)){
				filter.forColumnNameContaining("station", station);
			}
			if(isNotNullAndEmpty(partNumber)){
				filter.forColumnNameContaining("part_number", partNumber);
			}
			if(isNotNullAndEmpty(description)){
				filter.forColumnNameContaining("description", description);
			}
			filter.addOrderATColumnBy("station", IFilterSortOrders.ASCENDING);
			filter.addOrderATColumnBy("part_number", IFilterSortOrders.ASCENDING);
			filter.setMaxRows(materialTrackConfigVO.getQueryNum());
			Vector<UTRow> vectorData = filter.exec();
			Vector<MaterialTrackConfigVO> vos = new Vector<MaterialTrackConfigVO>();
			for (UTRow atRow : vectorData) {
				MaterialTrackConfigVO vo = new MaterialTrackConfigVO();
				vo.setKey(atRow.getKey());
				vo.setStation(object2String(atRow.getValue("station")));
				vo.setPartNumber(object2String(atRow.getValue("part_number")));
				vo.setDescription(object2String(atRow.getValue("description")));
				vos.add(vo);
			}
			JSONArray rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e){
			logger.error(e);
		}
		return null;
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(MaterialTrackConfigVO materialTrackConfigVO, Model model, 
			HttpServletRequest req,	HttpServletResponse res){
		long key = materialTrackConfigVO.getKey();
		model.addAttribute(INVOKE_TYPE, key<=0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if(key>0){
			try{
				UTRowFilter filter = imService.getFunctions().createATRowFilter("QM_MT_Config");
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> vectorData = filter.exec();
				if(vectorData.size()<=0){
					System.out.println("系统数据错误");
				}
				UTRow atRow = vectorData.get(0);
				MaterialTrackConfigVO vo = new MaterialTrackConfigVO();
				String station = object2String(atRow.getValue("station"));
				String partNumber = object2String(atRow.getValue("part_number"));
				String description = object2String(atRow.getValue("description"));
				vo.setKey(key);
				vo.setStation(station);
				vo.setPartNumber(partNumber);
				vo.setDescription(description);
				model.addAttribute(VIEW_OBJECT, vo);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		else{
			model.addAttribute(VIEW_OBJECT, materialTrackConfigVO);
		}
		return "im/materialTrack/addOrEdit";
	}

	/**
	 * 删除
	 * @return 
	 */
	@RequestMapping(params = "del")
	public @ResponseBody String del(@RequestParam("key") long key){
        try{
            if(key>0){
				UTRowFilter filter = imService.getFunctions().createATRowFilter("QM_MT_Config");
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> vectorData = filter.exec();
				if(vectorData.size()<=0){
					return ajaxDoneError("关键件删除错误:【"+key+"】数据不存在");
				}
				UTRow atRow = vectorData.get(0);
				Response response = atRow.delete(imService.getFunctions().getDBTime(), "", null);
				if(response.isError()){
					return ajaxDoneError("关键件删除错误:【"+key+"】删除异常");
				}
				return ajaxDoneSuccess();
            }
            else{
            	return ajaxDoneError("关键件删除错误:【"+key+"】错误");
			}
        }
		catch(Exception e){
			logger.error(e);
			return ajaxDoneError("关键件删除错误:"+e.getMessage());
		}
	}
 	
	@RequestMapping(params = "saveConfig", method=RequestMethod.POST)  
	public @ResponseBody String saveConfig(MaterialTrackConfigVO materialTrackConfigVO, HttpServletRequest req, HttpServletResponse res)
	{  
		try{
			long key = materialTrackConfigVO.getKey();
			String station = materialTrackConfigVO.getStation();
			String partNumber = materialTrackConfigVO.getPartNumber();
			String description = materialTrackConfigVO.getDescription();
			if(isNullOrEmpty(station)){
				return ajaxDoneError("工位不能为空");
			}
			if(isNullOrEmpty(partNumber)){
				return ajaxDoneError("零件不能为空");
			}
			UTRow atRow = null;
			UTRowFilter filter = imService.getFunctions().createATRowFilter("QM_MT_Config");
			Vector<UTRow> vectorData = null;
			if(key>0){
				filter.forUTRowKeyEqualTo(key);
				vectorData = filter.exec();
				if(vectorData.size()<=0){
					return ajaxDoneError("关键件保存失败:【"+key+"】数据不存在");
				}
				filter = imService.getFunctions().createATRowFilter("QM_MT_Config");
				filter.addSearchBy(IListFilterAttributes.KEY, IFilterComparisonOperators.NOT_EQUAL_TO, key);
				filter.forColumnNameEqualTo("station", station);
				filter.forColumnNameEqualTo("part_number", partNumber);
				if(filter.exec().size()>0){
					return ajaxDoneError("关键件配置工位【"+station+"】零件【"+partNumber+"】已存在");
				}
				atRow = vectorData.get(0);
			}
			else{
				filter.forColumnNameEqualTo("station", station);
				filter.forColumnNameEqualTo("part_number", partNumber);
				vectorData = filter.exec();
				if(vectorData.size()>0){
					return ajaxDoneError("关键件配置工位【"+station+"】零件【"+partNumber+"】已存在");
				}
			    UTHandler atHandler = imService.getFunctions().createATHandler("QM_MT_Config");
			    atRow = atHandler.createATRow();
			}
			if(null==atRow){
				return ajaxDoneError("关键件配置工位【"+station+"】零件【"+partNumber+"】创建失败");
			}
			atRow.setValue("station", station);
			atRow.setValue("part_number", partNumber);
			atRow.setValue("description", description);
			Response response = atRow.save(imService.getFunctions().getDBTime(), "", null);
            if(response.isOk()){
            	return ajaxDoneSuccess();
            }
            else{
            	return ajaxDoneError(response.getFirstErrorMessage());
            }
		}
		catch (Exception e){
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
	}
	
	/**
	 * 打开查询页面
	 */
	@RequestMapping(params = "toQueryPage")
	public String toQueryPage(){
		return "im/materialTrack/query";
	}

	/**
	 * 查询扫描信息
	 */
	@ResponseBody
	@RequestMapping(params = "toQuery")
	public String toQuery(MaterialTrackScanVO materialTrackScanVO, Model model){
		try{
			String vin = materialTrackScanVO.getVin();
			String station = materialTrackScanVO.getStation();
			String partNumber = materialTrackScanVO.getPartNumber();
			String description = materialTrackScanVO.getDescription();
			String barcode = materialTrackScanVO.getBarcode();
			String scanUser = materialTrackScanVO.getScanUser();
			String scanTimeStart = materialTrackScanVO.getScanTimeStart();
			String scanTimeEnd = materialTrackScanVO.getScanTimeEnd();
			UTRowFilter filter = imService.getFunctions().createATRowFilter("QM_MT_Scan");
			if(isNotNullAndEmpty(vin)){
				filter.forColumnNameContaining("vin", vin);
			}
			if(isNotNullAndEmpty(station)){
				filter.forColumnNameContaining("station", station);
			}
			if(isNotNullAndEmpty(partNumber)){
				filter.forColumnNameContaining("part_number", partNumber);
			}
			if(isNotNullAndEmpty(description)){
				filter.forColumnNameContaining("description", description);
			}
			if(isNotNullAndEmpty(barcode)){
				filter.forColumnNameContaining("barcode", barcode);
			}
			if(isNotNullAndEmpty(scanUser)){
				filter.forColumnNameContaining("scan_user", scanUser);
			}
			DateFormat dfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			DateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(isNotNullAndEmpty(scanTimeStart)){
				Calendar calendarStart = new GregorianCalendar();
				Date dateStart = dfInput.parse(scanTimeStart);
				calendarStart.setTime(dateStart);
				filter.forColumnNameGreaterThanOrEqualTo("scan_time", new Time(calendarStart));
			}
			if(isNotNullAndEmpty(scanTimeEnd)){
				Calendar calendarEnd = new GregorianCalendar();
				Date dateEnd = dfInput.parse(scanTimeEnd);
				calendarEnd.setTime(dateEnd);
				filter.forColumnNameLessThanOrEqualTo("scan_time", new Time(calendarEnd));
			}
			filter.addOrderATColumnBy("vin", IFilterSortOrders.ASCENDING);
			filter.addOrderATColumnBy("station", IFilterSortOrders.ASCENDING);
			filter.setMaxRows(materialTrackScanVO.getQueryNum());
			Vector<UTRow> vectorData = filter.exec();
			Vector<MaterialTrackScanVO> vos = new Vector<MaterialTrackScanVO>();
			for (UTRow atRow : vectorData) {
				MaterialTrackScanVO vo = new MaterialTrackScanVO();
				vo.setKey(atRow.getKey());
				vo.setVin(object2String(atRow.getValue("vin")));
				vo.setStation(object2String(atRow.getValue("station")));
				vo.setPartNumber(object2String(atRow.getValue("part_number")));
				vo.setDescription(object2String(atRow.getValue("description")));
				vo.setBarcode(object2String(atRow.getValue("barcode")));
				vo.setScanUser(object2String(atRow.getValue("scan_user")));
				Object scanTime = atRow.getValue("scan_time");
				if(scanTime==null){
					vo.setScanTimeStart("");
				}
				else{
					vo.setScanTimeStart(dfOutput.format(((Time)scanTime).getCalendar().getTime()));
				}
				vos.add(vo);
			}
			JSONArray rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e){
			logger.error(e);
		}
		return null;
	}

	/**
	 * 唯一件校验
	 */
	private String validateUnique(String barcode){
		try {
			UTRowFilter filter = imService.getFunctions().createATRowFilter("QM_MT_Scan");
			filter.forColumnNameEqualTo("barcode", barcode);
			Vector<UTRow> vectorData = filter.exec();
			if(vectorData.size()>0){
				return object2String(vectorData.get(0).getValue("vin"));
			}
			else{
				return "";
			}
		}
        catch (MESException e) {
			return "条码【"+barcode+"】唯一件校验失败:"+e.getMessage();
		}
	}

	/**
	 * 校验是否为空
	 */
	private boolean isNullOrEmpty(String str){
		if(null==str || "".equals(str)){
			return true;
		}
		return false;
	}

	/**
	 * 校验是否不为空
	 */
	private boolean isNotNullAndEmpty(String str){
		return !isNullOrEmpty(str);
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
		if(isNullOrEmpty(message)){
			map.put("RESULT", "SUCCESS");
		}
		else{
			map.put("RESULT", "FAIL");
		}
		map.put("MESSAGE", message);
		return map;
	}

	/**
	 * insert
	 */
	@RequestMapping(params = "insert")
	public @ResponseBody String insert(HttpServletRequest req, HttpServletResponse res){
		try {
			UTHandler atHandler = imService.getFunctions().createATHandler("QM_MT_BOM");
			UTRow atRow = atHandler.createATRow();
			atRow.setValue("vin", "LXXX6XXXXXX000001");
			atRow.setValue("part_number", "01010001");
			atRow.setValue("description", "发动机控制单元");
			atRow = atHandler.createATRow();
			atRow.setValue("vin", "LXXX6XXXXXX000001");
			atRow.setValue("part_number", "01020001");
			atRow.setValue("description", "ESC控制单元");
			atRow = atHandler.createATRow();
			atRow.setValue("vin", "LXXX6XXXXXX000001");
			atRow.setValue("part_number", "01030001");
			atRow.setValue("description", "第三排左侧安全带总成");
			atRow = atHandler.createATRow();
			atRow.setValue("vin", "LXXX6XXXXXX000002");
			atRow.setValue("part_number", "01010002");
			atRow.setValue("description", "发动机控制单元");
			atRow = atHandler.createATRow();
			atRow.setValue("vin", "LXXX6XXXXXX000002");
			atRow.setValue("part_number", "01020001");
			atRow.setValue("description", "ESC控制单元");
			atHandler.save(imService.getFunctions().getDBTime(), "", "");
			
			
			atHandler = imService.getFunctions().createATHandler("QM_MT_Config");
			atRow = atHandler.createATRow();
			atRow.setValue("station", "TL04L");
			atRow.setValue("part_number", "01010001");
			atRow.setValue("description", "发动机控制单元");
			atRow = atHandler.createATRow();
			atRow.setValue("station", "TL04L");
			atRow.setValue("part_number", "01010002");
			atRow.setValue("description", "发动机控制单元");
			atRow = atHandler.createATRow();
			atRow.setValue("station", "TL04L");
			atRow.setValue("part_number", "01020001");
			atRow.setValue("description", "ESC控制单元");
			atRow = atHandler.createATRow();
			atRow.setValue("station", "TL04L");
			atRow.setValue("part_number", "01030001");
			atRow.setValue("description", "第三排左侧安全带总成");
			atHandler.save(imService.getFunctions().getDBTime(), "", "");
		}
        catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * delete
	 */
	@RequestMapping(params = "delete")
	public @ResponseBody String delete(HttpServletRequest req, HttpServletResponse res){
		try {
			UTRowFilter filter = imService.getFunctions().createATRowFilter("QM_MT_BOM");
			Vector<UTRow> vectorData = filter.exec();
			for(UTRow atRow: vectorData){
				atRow.delete(imService.getFunctions().getDBTime(), "", null);
			}
			filter = imService.getFunctions().createATRowFilter("QM_MT_Config");
			vectorData = filter.exec();
			for(UTRow atRow: vectorData){
				atRow.delete(imService.getFunctions().getDBTime(), "", null);
			}
			filter = imService.getFunctions().createATRowFilter("QM_MT_Scan");
			vectorData = filter.exec();
			for(UTRow atRow: vectorData){
				atRow.delete(imService.getFunctions().getDBTime(), "", null);
			}
			
		}
        catch (MESException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	private String object2String(Object obj){
		if(obj==null){
			return "";
		}
		return String.valueOf(obj);
	}
}