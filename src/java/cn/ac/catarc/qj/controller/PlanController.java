package cn.ac.catarc.qj.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

/*import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;*/
import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.ac.catarc.qj.function.CreateCsn;
import cn.ac.catarc.qj.function.FunctionConstants;
import cn.ac.catarc.qj.util.FileUtil;
import cn.ac.catarc.qj.util.StringUtil;
import cn.ac.catarc.qj.util.VINHelper;
import cn.ac.catarc.qj.vo.BomVO;
import cn.ac.catarc.qj.vo.OrderVO;
import cn.ac.catarc.qj.vo.PlanVO;

import com.mes.admin.dataobjects.filter.IFilterSortOrders;
import com.mes.compatibility.client.Order;
import com.mes.compatibility.client.OrderFilter;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.User;
import com.mes.compatibility.ui.Time;
import com.mes.shopflow.common.constants.filtering.IATRowFilterAttributes;
import com.mes.webclient.controller.BaseController;
import com.mes.webclient.controller.vo.EleBOMVO;
import com.mes.webclient.controller.vo.EleOrderVO;
import com.mes.webclient.proxy.WebServerProxy;
import com.mes.webclient.service.impl.IMService;

@Controller("planController")
@RequestMapping("/plan.sp")
public class PlanController extends BaseController {
	@Autowired
	IMService imService;

	/**
	 * 进入页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage() {
		return "qj/plan/list";
	}
	
	/**
	 * 进入BOM页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "toBomList")
	public String toBomPage() {
		return "qj/plan/bom_list";
	}

	
	@RequestMapping(params = "toEleOrderPage")
	public String toEleOrderPage() {
		return "qj/plan/electric_order_list";
	}
	
	/**
	 * 接收计划
	 * 
	 * @return
	 */
	@RequestMapping(params = "downloadPlan")
	public String downloadPlan(){
		try {
			runDownloadPlan();
			deletePlan();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "qj/plan/list";
	}
	
	
	 
	@ResponseBody
	@RequestMapping(params = "eleOrderList")
	public String eleOrderList(EleOrderVO orderVO){
		String orderNum = orderVO.getOrderNum();
		String eleID = orderVO.getEleID();
		JSONArray  rt=null;
		try
		{
		String sql = "select ELE_ID,ELE_PART_NUM,ELE_PART_DESC ,ELE_ORDER_NUM,START_TIME,CSN , station , entry_time , scan_user from   ele_order  where 1=1 " ;	
	    if( orderNum != null && !"".equals(orderNum) ){
	    	sql = sql + " and ele_order_num like '%"+orderNum+"%' " ;
	    }
	    if( eleID != null && !"".equals(eleID) ){
	    	sql = sql + " and ele_id like '%"+eleID+"%' " ;
	    }
	    Vector<String[]> eleOrderData = imService.getArrayDataFromActive(sql);
		ArrayList<EleOrderVO> vos = new ArrayList<EleOrderVO>();
	    for (String[] array : eleOrderData)	{
	    	EleOrderVO vo = new EleOrderVO();
	    	vo.setEleID(StringUtil.toString( array[0]) );
	    	vo.setPartNum(StringUtil.toString( array[1]) );
	    	vo.setPartDesc(StringUtil.toString( array[2]) );
	    	vo.setOrderNum(StringUtil.toString( array[3]) );
	    	vo.setStartTime(StringUtil.toString( array[4]) );
	    	vo.setCsn(StringUtil.toString( array[5]) );
	    	vo.setStation(StringUtil.toString( array[6]) );
	    	vo.setEntryTime(StringUtil.toString( array[7]) );
	    	vo.setScanUser(StringUtil.toString( array[8]) );
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
	
	
	
	public Vector<String[]> getExcelDatas(MultipartFile tExcel) throws Exception{
		Workbook rwb = null;
		Vector<String[]> excelDatas  = new Vector<String[]>() ;;
		String fileName = tExcel.getOriginalFilename();
		 if(fileName.endsWith("xlsx")){
			 rwb = new XSSFWorkbook(tExcel.getInputStream());
		 }else{
			 rwb = new HSSFWorkbook(tExcel.getInputStream());
		 }
		Sheet rs = rwb.getSheetAt(0);
		int rows = rs.getLastRowNum();// 得到所有的行
		Row rowTitle = 	rs.getRow(0); //第0行为列标题
		int closTitle = rowTitle.getLastCellNum();// 得到所有的列
		for (int rowIndex = 1; rowIndex <= rows; rowIndex++)
		{
			Row row = 	rs.getRow(rowIndex);
			String[] rowDatas = new String[closTitle];
			for (int colIndex = 0; colIndex < closTitle; colIndex++)
			{
				Cell cell = row.getCell(colIndex);
				if( cell != null ){
					
					 
					DecimalFormat df = new DecimalFormat("0");// 格式化 number String 字符
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd");// 格式化日期字符串
					DecimalFormat nf = new DecimalFormat("0");// 格式化数字

					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						 
						rowDatas[colIndex] = cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						 
						if ("@".equals(cell.getCellStyle().getDataFormatString())) {
							rowDatas[colIndex] = df.format(cell.getNumericCellValue());
						} else if ("General".equals(cell.getCellStyle()
								.getDataFormatString())) {
							rowDatas[colIndex] = nf.format(cell.getNumericCellValue());
						} else {
							rowDatas[colIndex] = sdf.format(HSSFDateUtil.getJavaDate(cell
									.getNumericCellValue()));
						}
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
//							System.out.println(i + "行" + j + " 列 is Boolean type");
						rowDatas[colIndex]  = ""+cell.getBooleanCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
//							System.out.println(i + "行" + j + " 列 is Blank type");
						rowDatas[colIndex]  = "";
						break;
					default:
//							System.out.println(i + "行" + j + " 列 is default type");
						rowDatas[colIndex]  = cell.toString();
					}

					 
					
				}
			}
			excelDatas.add(rowDatas);
		}
	
		return excelDatas;
	}
	
	public String saveMaterialKeyVin(Vector<String[]> erpKeyDatas){
		try{
			User user =WebServerProxy.getCurrentUser();
			String scanUser = user.getName();
			for(String[] array : erpKeyDatas ){
				if(array.length != 7 ){
					return "车辆关键件模版不列数不对，应该为【序号，VIN号，关键件号，关键件零件描述，关键件条码，装配工序，扫描时间】";
				}
				String vin = array[1];
				String partNumber = array[2];
				String description = array[3];
				String barCode = array[4];
				
				String station =  "abok" ; 
				if( !"".equals(array[5]) ){
					station  = array[5];
				}
				String scanTime = array[6];
				
				UTRow atRow = null;
			    UTHandler atHandler = imService.getFunctions().createATHandler("QM_MT_Scan");
			    atRow = atHandler.createATRow();
			    atRow.setValue("vin",vin);
				atRow.setValue("station",station);
				atRow.setValue("part_number", partNumber);
				atRow.setValue("description", description);
				atRow.setValue("barcode", barCode);
				atRow.setValue("scan_user", scanUser);
				DateFormat dfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(!"".equals(scanTime) ){
					Calendar calendarStart = new GregorianCalendar();
					Date inputTime = dfInput.parse(scanTime);
					calendarStart.setTime(inputTime);
					atRow.setValue("scan_time", new Time(calendarStart) );
				}
				Response response = atRow.save(imService.getFunctions().getDBTime(), "", null);
				if( response.isError() ){
					return response.getFirstErrorMessage();
				}
			}
		}catch( Exception e ){
			e.printStackTrace();
		}
		return "";
	}
	 
		@RequestMapping(params = "importUDTable")
		@ResponseBody
		public String saveImportUDTable(){
			try{
				String importSrcTableName = "ud_ATCertificate_1";
				String udTableName = "ATCertificate";
				String sql = "select   substr(atc_name , 0, length(atc_name) - 2 ) ,atc_name  from udt_column where ud_key = (select ud_key from udt where ud_name = '"+udTableName+"' ) order by atc_name";
				Vector<String[]> vectorData = imService.getArrayDataFromActive(sql);
				String[] dbUDAName = new String[ vectorData.size() ];
				int udaNameI = 0;
			
				String importSrcSql = " select ";
				for (String[] dbObjConfig : vectorData) {
					dbUDAName[ udaNameI ++ ] = dbObjConfig[0];
					importSrcSql = importSrcSql  +dbObjConfig[1]+"," ;
				}
				importSrcSql = importSrcSql.substring(0, importSrcSql.length() -1 ) ;
				importSrcSql = importSrcSql  + " from "+importSrcTableName ;
			
				Vector<String[]> vectorQueryTableData = imService.getArrayDataFromActive(importSrcSql);
				for(int i = 0 ; i<vectorQueryTableData.size(); i++ ){
					String[] array = vectorQueryTableData.get(i);
					UTHandler atHandler = imService.getFunctions().createATHandler(udTableName);
				    UTRow atRow = atHandler.createATRow();
					for( int colIndex = 0 ; colIndex < array.length; colIndex++){
						String colValue = array[colIndex];
						String udaColName = dbUDAName[colIndex] ;
						System.out.println("uda "+udaColName + " = "+ colValue );
						atRow.setValue(udaColName, colValue) ;
					}
				    Response	response = atRow.save(null, null, null);
					if (response.isError())
					{
						throw new Exception(response.getFirstErrorMessage());
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return "";
		}
		//分解完成后设置计划状态为0（分解）
		 
	 
	@RequestMapping(params = "importObject")
	@ResponseBody
	public String saveImportOrder(){
		try{

			String sql = "select name , db_name from uda where object_type = 'Order' order by name";
			Vector<String[]> vectorData = imService.getArrayDataFromActive(sql);
			String[] dbUDAName = new String[ vectorData.size() ];
			int udaNameI = 0;
			String importSrcTableName = "uda_order_1";
			String importSrcSql = " select ";
			for (String[] dbObjConfig : vectorData) {
				dbUDAName[ udaNameI ++ ] = dbObjConfig[0];
				importSrcSql = importSrcSql  +dbObjConfig[1]+"," ;
			}
			importSrcSql = importSrcSql.substring(0, importSrcSql.length() -1 ) ;
			importSrcSql = importSrcSql  + " from "+importSrcTableName ;
		
			Vector<String[]> vectorQueryTableData = imService.getArrayDataFromActive(importSrcSql);
			for(int i = 0 ; i<vectorQueryTableData.size(); i++ ){
				String[] array = vectorQueryTableData.get(i);
				Order order = imService.createOrder();
				for( int colIndex = 0 ; colIndex < array.length; colIndex++){
					String colValue = array[colIndex];
					String udaColName = dbUDAName[colIndex] ;
				
					if( udaColName.equals("vin")) {
						order.setOrderNumber(colValue);
						order.setDescription( colValue );
					}
					System.out.println("uda "+udaColName + " = "+ colValue );
					order.setUDA(colValue , udaColName  );
				}
		 
			    Response	response = order.save(null, null, null);
				if (response.isError())
				{
					throw new Exception(response.getFirstErrorMessage());
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	//分解完成后设置计划状态为0（分解）
	 
	
	
	/*
	 * 导入车辆关键件
	 * */
	@RequestMapping(params = "importExcelKeyVin")
	public @ResponseBody
	String importExcelKeyVin(@RequestParam(value = "myfile") MultipartFile tExcel)
	{
		
		try {
			Vector<String[]> ExcelDatas = getExcelDatas(tExcel);
			String res = saveMaterialKeyVin( ExcelDatas );
			if(!  "".equals(res)){
				return ajaxDoneError(  res );
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ajaxDoneError("计划导入异常："+ e.getMessage() );
		}
		
		return ajaxDoneSuccess();
	}
	
	
	
	public String saveMaterialKey(Vector<String[]> erpKeyDatas){
		try{
			for(String[] array : erpKeyDatas ){
				
				String partNumber = array[1];
				String description = array[2];
				String station =  "abok" ; // array[3];
				UTRow atRow = null;
			    UTHandler atHandler = imService.getFunctions().createATHandler("QM_MT_Config");
			    atRow = atHandler.createATRow();
				atRow.setValue("station",station);
				atRow.setValue("part_number", partNumber);
				atRow.setValue("description", description);
				Response response = atRow.save(imService.getFunctions().getDBTime(), "", null);
			}
		}catch( Exception e ){
			e.printStackTrace();
		}
		return "";
	}
	
	/*
	 * 导入关键件
	 * */
	@RequestMapping(params = "importExcelKeyConfig")
	public @ResponseBody
	String importExcelKeyConfig(@RequestParam(value = "myfile") MultipartFile tExcel)
	{
		
		try {
			Vector<String[]> ExcelDatas = getExcelDatas(tExcel);
			String res = saveMaterialKey( ExcelDatas );
			if(!  "".equals(res)){
				return ajaxDoneError(  res );
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ajaxDoneError("计划导入异常："+ e.getMessage() );
		}
		
		return ajaxDoneSuccess();
	}
	
	
	
	
	
	
	@RequestMapping(params = "importExcel")
	public @ResponseBody
	String importExcel(@RequestParam(value = "myfile") MultipartFile tExcel)
	{
		
		try {
			Vector<String[]> ExcelDatas = getExcelDatas(tExcel);
			String res = saveErpPlan( ExcelDatas );
			if(!  "".equals(res)){
				return ajaxDoneError(  res );
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ajaxDoneError("计划导入异常："+ e.getMessage() );
		}
		
		return ajaxDoneSuccess();
	}

	
	
	@RequestMapping(params = "importExcelEleOrder")
	public @ResponseBody
	String importExcelEleOrder(@RequestParam(value = "myfile") MultipartFile tExcel)
	{
		
		try {
			Vector<String[]> ExcelDatas = getExcelDatas(tExcel);
			String res = saveExcelEleOrder( ExcelDatas );
			if(!  "".equals(res)){
				return ajaxDoneError(  res );
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ajaxDoneError("计划导入异常："+ e.getMessage() );
		}
		
		return ajaxDoneSuccess();
	}
	
	public String saveExcelEleOrder(Vector<String[]> OrderDatas ) throws Exception {
		if( OrderDatas.size() == 0 ){
			return "电池订单导入的数据为空，不能导入到MES系统";
		}
		String[] sqlStatements = new String[OrderDatas.size()];
		int i = 0;
		for(String[] array : OrderDatas ){
			if( array.length == 7 ){
				String eleId = array[1];
				String partNum = array[2];
				String partDesc = array[3];
				String orderNum = array[4];
				String startTime = array[5];
				String csn = array[6];
				String insertSql = " insert into  ele_order (ELE_ID,ELE_PART_NUM,ELE_PART_DESC ,ELE_ORDER_NUM,START_TIME,CSN )  "
						+ "values ( '"+eleId+"','"+partNum+"','"+partDesc+"','"+orderNum+"','"+startTime+"','"+csn+"' )  " ;
				insertSql = insertSql.replaceAll("'null'","null") ;
				insertSql = insertSql.replaceAll("''","null") ;
				sqlStatements[i++] = insertSql ;
			}else{
				logger.error( "电池订单 EXCEL文件格错误" );
				return  "保存失败，原因:电池 ORDER EXCEL文件格错误" ;
			}
		}
		if( i > 0 ){
			int[] res  = imService.getFunctions().executeStatements(sqlStatements);
			if(res[0] > 0 ){ 
				return "";
			}else{
				return  "保存失败，原因:数据库异常" ;
			}
				
		}else{
			return "读取数据模版异常";
		}
	}
	
	
	public String saveExcelEleBom(Vector<String[]> bomDatas ) throws Exception {
		if( bomDatas.size() == 0 ){
			return "电池BOM导入的数据为空，不能导入到MES系统";
		}
		String atDefinitionName = "electric_bom";
		for(String[] array : bomDatas ){
			String parentNum = array[1];
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRow utRow = null ;
			utRow = atHandler.createATRow();
			if( array.length == 7 ){
				utRow.setValue("parent_part_number", parentNum);
				utRow.setValue("parent_part_desc", array[2]);
				utRow.setValue("sun_part_number", array[3]);
				utRow.setValue("sun_part_desc", array[4]);
				utRow.setValue("station", array[5]);
				utRow.setValue("part_count", array[6]);
				Response response = utRow.save(null, null, null);
				if (response.isError()) {
					logger.error(response.getFirstErrorMessage());
					return parentNum + "电池BOM保存失败，原因:"+response.getFirstErrorMessage() ;
				}
			}else{
				logger.error( parentNum +"电池BOM EXCEL文件格错误" );
				return parentNum + "保存失败，原因:电池 BOM EXCEL文件格错误" ;
			}
		}
	 
		return "";
	}






	@RequestMapping(params = "importExcelEleBom")
	public @ResponseBody
	String importExcelEleBom(@RequestParam(value = "myfile") MultipartFile tExcel)
	{
		
		try {
			Vector<String[]> ExcelDatas = getExcelDatas(tExcel);
			String res = saveExcelEleBom( ExcelDatas );
			if(!  "".equals(res)){
				return ajaxDoneError(  res );
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ajaxDoneError("计划导入异常："+ e.getMessage() );
		}
		
		return ajaxDoneSuccess();
	}
	
	
	
	@RequestMapping(params = "importExcelBom")
	public @ResponseBody
	String importExcelBom(@RequestParam(value = "myfile") MultipartFile tExcel)
	{
		
		try {
			Vector<String[]> ExcelDatas = getExcelDatas(tExcel);
			String res = saveExcelBom( ExcelDatas );
			if(!  "".equals(res)){
				return ajaxDoneError(  res );
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ajaxDoneError("计划导入异常："+ e.getMessage() );
		}
		
		return ajaxDoneSuccess();
	}
	
	
	public String saveErpPlan(Vector<String[]> erpPlanDatas ) throws Exception {
		
			if( erpPlanDatas.size() == 0 ){
				return "导入的数据为空，不能导入到MES系统";
			}
			String atDefinitionName = "Interface_ERP_Order";
			for(String[] array : erpPlanDatas ){
				String orderNum = array[0];
				UTHandler atHandler = imService.getAtHandler(atDefinitionName);
				UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
				filter.forColumnNameEqualTo("plan_id",orderNum);
				Vector<UTRow> v = atHandler.getATRowsByFilter(filter, false);
				UTRow utRow = null ;
				if(v.size()>0){
					
						throw new Exception("订单号："+ orderNum +"不能重复接收");
					
				}else{
					utRow = atHandler.createATRow();
				}
				if( array.length == 15 ){
					utRow.setValue("plan_id", orderNum);
					utRow.setValue("plan_start_time", array[1]);
					utRow.setValue("plan_end_time", array[2]);
					utRow.setValue("vsn", array[3]);
					utRow.setValue("vsn_desc", array[4]);
					utRow.setValue("quantity", array[5]);
					utRow.setValue("color", array[6]); 
					utRow.setValue("vin8", array[7]);
					utRow.setValue("engine_type", array[8]); 
					utRow.setValue("special_order", array[9]); 
					utRow.setValue("line_id", array[10]);
					utRow.setValue("car_project", array[11]);
					utRow.setValue("factory_name", array[12]); 
					utRow.setValue("brand_name", array[13]); 
					utRow.setValue("comment", array[14]); 
					Response response = utRow.save(null, null, null);
					if (response.isError()) {
						logger.error(response.getFirstErrorMessage());
						return orderNum + "保存失败，原因:"+response.getFirstErrorMessage() ;
					}
				}else{
					logger.error( orderNum +"EXCEL文件格错误,与模版要求列数不符" );
					return orderNum + "保存失败，原因:EXCEL文件格错误,与模版要求列数不符" ;
				}
			}
			
		 
		return "";
	}
	
	public String saveExcelBom(Vector<String[]> bomDatas ) throws Exception {
		
		if( bomDatas.size() == 0 ){
			return "导入的数据为空，不能导入到MES系统";
		}
	
		String atDefinitionName = "order_bom";
		for(String[] array : bomDatas ){
			String partNum = array[4];
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			//UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			//filter.forColumnNameEqualTo("part_num",partNum);
			//Vector<UTRow> v = atHandler.getATRowsByFilter(filter, false);
			UTRow utRow = null ;
			//if(v.size()>0){
				//	throw new Exception("零件号："+ partNum +"不能重复接收");
				
	//		}else{
				utRow = atHandler.createATRow();
		//	}
			if( array.length == 8 ){
				utRow.setValue("part_num", partNum);
				utRow.setValue("effect_date", array[0]);
				utRow.setValue("factory_num", array[1]);
				utRow.setValue("line_num", array[2]);
				utRow.setValue("order_num", array[3]);
				//utRow.setValue("order_num", "DA1600"+i);
				utRow.setValue("part_desc", array[5]);
				utRow.setValue("quantity", array[6]); 
				utRow.setValue("station_num", array[7]);
				Response response = utRow.save(null, null, null);
				if (response.isError()) {
					logger.error(response.getFirstErrorMessage());
					return partNum + "保存失败，原因:"+response.getFirstErrorMessage() ;
				}
			}else{
				logger.error( partNum +"EXCEL文件格错误" );
				return partNum + "保存失败，原因:EXCEL文件格错误" ;
			}
			
		
		
		}
	 
	return "";
}
	
	/**
	 * 查询
	 * 
	 * @param orderVO
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "bomList")
	public String bomList(BomVO bomVO){
		String partNum = bomVO.getPartNum();
		String orderNum = bomVO.getOrderNum();
		JSONArray  rt=null;
		try
		{
		UTRowFilter utRowFilter =imService.createAtRowFilter("order_bom");
		if(!"".equals(StringUtil.toString(partNum))){
			utRowFilter.forColumnNameContaining("part_num", partNum);
		}
		if(!"".equals(StringUtil.toString(orderNum))){
			utRowFilter.forColumnNameContaining("order_num", orderNum);
		}
	    Vector<UTRow> boms = utRowFilter.exec();
	    ArrayList<BomVO> vos = new ArrayList<>();
	    for (int i = 0; i < boms.size(); i++)
		{
	    	BomVO vo = new BomVO();
	    	UTRow row = boms.get(i);
			vo.setEffectDate(StringUtil.toString(row.getValue("effect_date")));
			vo.setFactoryNum(StringUtil.toString(row.getValue("factory_num")));
			vo.setLineNum(StringUtil.toString(row.getValue("line_num")));
			vo.setOrderNum(StringUtil.toString(row.getValue("order_num")));
			vo.setPartDesc(StringUtil.toString(row.getValue("part_desc")));
			vo.setPartNum(StringUtil.toString(row.getValue("part_num")));
			vo.setQuantity(StringUtil.toString(row.getValue("quantity")));
			vo.setStationNum(StringUtil.toString(row.getValue("station_num")));
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
	
	private void runDownloadPlan() throws Exception{
		String planPath="D:\\qj_txt\\plan";
		String planBackupPath="D:\\qj_txt\\plan_backup";
		String planFileName="";
		File dir = new File(planPath);
		File list[] = dir.listFiles();
		String encoding = "GBK";
		for (int i = 0; i < list.length; i++) {
			planFileName = list[i].getName();
			String suffix = planFileName.substring(planFileName.lastIndexOf(".") + 1);
			if ("txt".equals(suffix)) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(list[i]), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				String atDefinitionName = "Interface_ERP_Order";
				UTHandler atHandler = imService.getAtHandler(atDefinitionName);
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String orderInfo[] = lineTxt.split(" ");
					UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
					filter.forColumnNameEqualTo("plan_id",orderInfo[0]);
					Vector<UTRow> v = atHandler.getATRowsByFilter(filter, false);
					//plan_id已存在但是没有分解则更新数据，已存在且已分解，报错；不存在的时候直接创建
					UTRow utRow;
					if(v.size()>0){
						utRow = v.get(0);
						String status = StringUtil.toString(utRow.getValue("status"));
						if(!"-1".equals(status)){
							throw new Exception("订单号："+orderInfo[0]+"已分解，不能重复接收");
						}
					}else{
						utRow = atHandler.createATRow();
					}
					utRow.setValue("plan_id", orderInfo[0]);
					utRow.setValue("plan_start_time", orderInfo[1]);
					utRow.setValue("plan_end_time", orderInfo[2]);
					utRow.setValue("vsn", orderInfo[3]);
					utRow.setValue("quantity", orderInfo[4]);
					utRow.setValue("comment", orderInfo[5]);
					utRow.setValue("status", "-1");
					Response response = utRow.save(null, null, null);
					if (response.isError()) {
						logger.error(response.getFirstErrorMessage());
					}
				}
				read.close();
				FileUtil.copyFile(planPath+"\\"+planFileName, planBackupPath+"\\"+planFileName);
				FileUtil.deleteFile(planPath+"\\"+planFileName);
			}
		}
	}
	private void deletePlan() throws Exception{
		String planPath="D:\\qj_txt\\plan_delete";
		String planBackupPath="D:\\qj_txt\\plan_delete_backup";
		String planFileName="";
		File dir = new File(planPath);
		File list[] = dir.listFiles();
		String encoding = "GBK";
		for (int i = 0; i < list.length; i++) {
			planFileName = list[i].getName();
			String suffix = planFileName.substring(planFileName.lastIndexOf(".") + 1);
			if ("txt".equals(suffix)) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(list[i]), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				String atDefinitionName = "Interface_ERP_Order";
				UTHandler atHandler = imService.getAtHandler(atDefinitionName);
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String orderInfo[] = lineTxt.split(" ");
					UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
					filter.forColumnNameEqualTo("plan_id",orderInfo[0]);
					Vector<UTRow> v = atHandler.getATRowsByFilter(filter, false);
					//plan_id已存在但是没有分解则更新数据，已存在且已分解，报错；不存在的时候直接创建
					UTRow utRow;
					if(v.size()>0){
						utRow = v.get(0);
						utRow.delete();
					}else{
						throw new Exception("订单号："+orderInfo[0]+"不存在，无法删除");
					}
					Response response = utRow.save(null, null, null);
					if (response.isError()) {
						logger.error(response.getFirstErrorMessage());
					}
				}
				
				read.close();
				FileUtil.copyFile(planPath+"\\"+planFileName, planBackupPath+"\\"+planFileName);
				FileUtil.deleteFile(planPath+"\\"+planFileName);
			}
		}
	}
	/**
	 * 分解计划
	 * 
	 * @return
	 */
	@RequestMapping(params = "decompose")
	@ResponseBody
	public String decompose(PlanVO planVO){
		long key = planVO.getKey();
		String atDefinitionName = "Interface_ERP_Order";
		UTHandler atHandler;
		try {
			atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			filter.forUTRowKeyEqualTo(key);
			Vector<UTRow> v = atHandler.getATRowsByFilter(filter, false);
			UTRow utRow;
			if(v.size()>0){
				utRow = v.get(0);
				if("0".equals(String.valueOf(utRow.getValue("status")))){
					throw new Exception("计划已分解，不能重复分解");
				}
			}else{
				throw new Exception("计划："+key+"不存在，无法分解");
			}
			//部分字段来源不确定，暂时硬编码
			VINHelper helper = new VINHelper();
			String vin8 = (String) utRow.getValue("vin8");
			String lineId = (String) utRow.getValue("line_id");
			lineId=lineId==null?"A":lineId;
			Integer qtyD = Integer.parseInt((String) utRow.getValue("quantity"));
			String planNo=(String) utRow.getValue("plan_id");
			String vinProject =(String)  utRow.getValue("car_project") ;
			String[] vins = helper.generateCode(
				vin8,
				lineId,
				qtyD.intValue(),
				planNo, 
				vinProject);
			Response response;
			for (int i = 0; i < vins.length; i++) {
				String vin = vins[i];
				Order order = imService.createOrder();
				order.setOrderNumber(vin);
//				order.setEnteredTime(DateTimeUtils.parseDateOfPnut((String) utRow.getValue("plan_start_time"), IDateFormat.TIME_DAY));
//				order.setPromisedTime(DateTimeUtils.parseDateOfPnut((String) utRow.getValue("plan_end_time"), IDateFormat.TIME_DAY));
				order.setDescription((String) utRow.getValue("comment"));
				order.setUDA("0", "status");
				order.setUDA((String) utRow.getValue("plan_id") , "plan_id");
				order.setUDA((String) utRow.getValue("vsn"), "car_type");
				order.setUDA((String) utRow.getValue("vsn_desc"), "car_type_desc");
				order.setUDA((String) utRow.getValue("color"), "color");
				order.setUDA((String) utRow.getValue("engine_type"), "engine_type");
				order.setUDA((String) utRow.getValue("special_order"), "special_order");
				order.setUDA((String) utRow.getValue("car_project"), "car_project");
				order.setUDA((String) utRow.getValue("plan_start_time"), "plan_start_time");
				order.setUDA((String) utRow.getValue("plan_end_time"), "plan_end_time");
				order.setUDA("标配", "vehicle_configuration");
				 
				order.setUDA(vin, "vin");
			 
				 
				
				
				
				
				CreateCsn cc = new CreateCsn();
				String stage = FunctionConstants.STAGE_ORDER_ADJUST;
				String csnName = CreateCsn.getCsnNameByStage("0010");
				int csnNumber = cc.getCsnNumber(csnName);
				String csn = CreateCsn.generateCsn(stage, imService.getFunctions().getDBTime().getYear(), csnNumber);
				order.setUDA(csn, "csn");
				if(StringUtil.isNull(csn)){
					throw new Exception("生成CSN号为空");
				}
				response = order.save(null, null, null);
				if (response.isError())
				{
					throw new Exception(response.getFirstErrorMessage());
				}
			}
			//分解完成后设置计划状态为0（分解）
			utRow.setValue("status", "0");
			response=utRow.save(null, null, null);
			if (response.isError())
			{
				throw new Exception(response.getFirstErrorMessage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ajaxDoneError(e.getLocalizedMessage());
		}
		
		return ajaxDoneSuccess();
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
		JSONArray  rt=null;
		try
		{
		UTRowFilter utRowFilter =imService.createAtRowFilter("Interface_ERP_Order");
		if(!"".equals(planId)){
			utRowFilter.forColumnNameContaining("plan_id", planId);
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
	 * 进入订单页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "toOrderList")
	public String toOrderPage() {
		return "qj/plan/order_list";
	}

	/**
	 * 查询workorder
	 * @param orderVO
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "orderList")
	public String orderList(OrderVO orderVO)
	{
		JSONArray rt = null;
		try
		{
			String sql = "select object_key ,vin_S, plan_id_S,car_type_S,car_type_desc_S, color_S,engine_type_S,special_order_S,car_project_S,plan_start_time_S,plan_end_time_S,station_S, username_S ,status_S,csn_S,entry_time_S "
					+" from  UDA_Order uo where 1=1 ";
			String orderNumber = orderVO.getOrderNumber();
			String vin = orderVO.getVin();
			String status = orderVO.getStatus();  // 0 分解  1  锁定  2 发布
			String planOfflineDateStart = orderVO.getPlanStartTime();
			String planOfflineDateEnd = orderVO.getPlanEndTime();
			if(StringUtil.isNotNull(orderNumber))
			{
				sql += " and plan_id_S like '%"+orderNumber+"%' ";
			}
			if(StringUtil.isNotNull(vin))
			{
				sql += " and vin_s like '%"+vin+"%' ";
			}
			if(StringUtil.isNotNull(status)&&!"-100".equals(status))
			{
				sql += " and status_S ="+status;
			}
			if(StringUtil.isNotNull(planOfflineDateStart))
			{
				sql += " and plan_start_time_S >= '"+planOfflineDateStart+"'";
			}
			if(StringUtil.isNotNull(planOfflineDateEnd))
			{
				sql += " and plan_start_time_S <= '"+planOfflineDateEnd+"'";
			}
			sql += " order by csn_S asc ";
			Vector<String[]> vectorData = imService.getArrayDataFromActive(sql);;
			Vector<OrderVO> vos = new Vector<OrderVO>();
			for(String[] data : vectorData){
				OrderVO vo = new OrderVO();
				vo.setKey(Long.parseLong(data[0]));
			
				vo.setVin(data[1]);
				vo.setOrderNumber(data[2]);
				vo.setCarType(data[3]);
				vo.setCarTypeDesc(data[4]);
				vo.setColor( data[5]);
				vo.setEngineType(data[6]);
				vo.setSpecialOrder(data[7]);
				vo.setCarProject(data[8]);
				vo.setPlanStartTime(data[9]);
				vo.setPlanEndTime(data[10]);
				vo.setStation(data[11]);
				vo.setUserName(data[12]);
				vo.setStatus(data[13]);
				vo.setCsn(data[14]);
				vo.setEntryTime(data[15]);
				vos.add(vo);
			}
			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	/**
	 * 计划上移
	 * 
	 * @return
	 */
	@RequestMapping(params = "up")
	@ResponseBody
	public String up(OrderVO orderVO){
		long key = orderVO.getKey();
		if (key > 0) {
			try {
				Order order = imService.getOrderByKey(key);
				String csn = StringUtil.toString(order.getUDA("csn"));
				int seq = Integer.parseInt(csn.substring(2))-1;
				String newCsn = csn.substring(0, 2)+seq;
				order.setUDA(newCsn,"csn");
				OrderFilter orderFilter = imService.createOrderFilter();
				orderFilter.forUdaEqualTo("csn", newCsn);
				Vector<Order> orders = orderFilter.exec();
				for(Order order1 : orders){
					order1.setUDA(csn,"csn");
					Response response1 = order1.save();
					if (response1.isError())
					{
						throw new Exception(response1.getFirstErrorMessage());
					}
				}
				Response response = order.save();
				if (response.isError())
				{
					throw new Exception(response.getFirstErrorMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ajaxDoneError(e.getLocalizedMessage());
			}
		}
		return ajaxDoneSuccess();
	}
	/**
	 * 计划下移
	 * 
	 * @return
	 */
	@RequestMapping(params = "down")
	@ResponseBody
	public String down(OrderVO orderVO){
		long key = orderVO.getKey();
		if (key > 0) {
			try {
				Order order = imService.getOrderByKey(key);
				String csn = StringUtil.toString(order.getUDA("csn"));
				int seq = Integer.parseInt(csn.substring(2))+1;
				String newCsn = csn.substring(0, 2)+seq;
				order.setUDA(newCsn,"csn");
				OrderFilter orderFilter = imService.createOrderFilter();
				orderFilter.forUdaEqualTo("csn", newCsn);
				Vector<Order> orders = orderFilter.exec();
				for(Order order1 : orders){
					order1.setUDA(csn,"csn");
					Response response1 = order1.save();
					if (response1.isError())
					{
						throw new Exception(response1.getFirstErrorMessage());
					}
				}
				Response response = order.save();
				if (response.isError())
				{
					throw new Exception(response.getFirstErrorMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ajaxDoneError(e.getLocalizedMessage());
			}
		}
		return ajaxDoneSuccess();
	}
	/**
	 * 计划锁定
	 * 
	 * @return
	 */
	@RequestMapping(params = "hold")
	@ResponseBody
	public String hold(String keys){
		String[] keyArray = keys.split(",");
		for (int i = 0; i < keyArray.length; i++) {
			long key =Long.parseLong(keyArray[i]);
			if (key > 0) {
				try {
					Order order = imService.getOrderByKey(key);
					order.setUDA("1","status");
					Response response = order.save();
					if (response.isError())
					{
						throw new Exception(response.getFirstErrorMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ajaxDoneError(e.getLocalizedMessage());
				}
			}
		}

		return ajaxDoneSuccess();
	}
	/**
	 * 计划解锁
	 * 
	 * @return
	 */
	@RequestMapping(params = "release")
	@ResponseBody
	public String release(String keys){
		String[] keyArray = keys.split(",");
		for (int i = 0; i < keyArray.length; i++) {
			long key =Long.parseLong(keyArray[i]);
			if (key > 0) {
				try {
					Order order = imService.getOrderByKey(key);
					order.setUDA("0","status");
					Response response = order.save();
					if (response.isError())
					{
						throw new Exception(response.getFirstErrorMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ajaxDoneError(e.getLocalizedMessage());
				}
			}
		}
		return ajaxDoneSuccess();
	}
	/**
	 * 计划发布
	 * 
	 * @return
	 */
	@RequestMapping(params = "publish")
	@ResponseBody
	public String publish(String keys){
		String[] keyArray = keys.split(",");
		for (int i = 0; i < keyArray.length; i++) {
			long key =Long.parseLong(keyArray[i]);
			if (key > 0) {
				try {
					Order order = imService.getOrderByKey(key);
					order.setUDA("2","status");
					Response response = order.save();
					if (response.isError())
					{
						throw new Exception(response.getFirstErrorMessage());
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ajaxDoneError(e.getLocalizedMessage());
				}
			}
		}
		return ajaxDoneSuccess();
	}
	
	/**
	 * 进入订单页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "toOrderFinish")
	public String toOrderFinish() {
		return "qj/plan/order_finish";
	}
	/**
	 * 查询报工记录
	 * @param orderVO
	 * @param model
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(params = "orderFinish")
	public String orderFinish(OrderVO orderVO)
	{
		JSONArray rt = null;
		try
		{
			String sql = "select wo.order_key,uo.csn_s,wo.order_number,to_char(wo.entered_time,'yyyy-mm-dd'),to_char(wo.promised_time,'yyyy-mm-dd')," 
					+ "	wo.description,uo.status_i "
						+" from work_order wo left join uda_order uo on wo.order_key=uo.object_key where 1=1 ";
			String orderNumber = orderVO.getOrderNumber();
			String csn = orderVO.getCsn();
			String status = orderVO.getStatus();
			String planOfflineDateStart = orderVO.getPlanOfflineDateStart();
			String planOfflineDateEnd = orderVO.getPlanOfflineDateEnd();
			if(StringUtil.isNotNull(orderNumber))
			{
				sql += " and order_number like '%"+orderNumber+"%' ";
			}
			if(StringUtil.isNotNull(csn))
			{
				sql += " and uo.csn_s like '%"+csn+"%' ";
			}
			if(StringUtil.isNotNull(status)&&!"-100".equals(status))
			{
				sql += " and uo.status_i ="+status;
			}
			if(StringUtil.isNotNull(planOfflineDateStart))
			{
				sql += " and to_char(wo.promised_time,'yyyy-mm-dd') >= "+planOfflineDateStart;
			}
			if(StringUtil.isNotNull(planOfflineDateEnd))
			{
				sql += " and to_char(wo.promised_time ,'yyyy-mm-dd') <= "+planOfflineDateEnd;
			}
			sql += " order by csn_s asc ";
			Vector<String[]> vectorData = imService.getArrayDataFromActive(sql);;
			Vector<OrderVO> vos = new Vector<OrderVO>();
			for(String[] data : vectorData){
				OrderVO vo = new OrderVO();
				vo.setKey(Long.parseLong(data[0]));
				vo.setCsn(data[1]);
				vo.setOrderNumber(data[2]);
				vo.setEnteredTime(data[3]);
				vo.setPromisedTime(data[4]);
				vo.setNote(data[5]);
				vo.setStatus(StringUtil.transferOrderStatus(StringUtil.toString(data[6])));
				vos.add(vo);
			}
			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}*/
}
