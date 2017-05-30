package com.mes.webclient.vcpus;

import java.io.File;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.mes.compatibility.client.ATDefinition;
import com.mes.compatibility.client.ATHandler;
import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.ATRowFilter;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTDefinition;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.UserSequence;
import com.mes.compatibility.client.UserSequenceValue;
import com.mes.compatibility.ui.Time;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.service.impl.BaseService;
import com.mes.webclient.util.DateTimeUtils;
import com.mes.webclient.util.StringUtil;
import com.mes.webclient.vcpus.epi.WSXxgkVin;
import com.mes.webclient.vcpus.epi.WSXxgkVinSoap;



public class EnvironmentProtectionInformation extends BaseService{
	
	private static final String QRCODE_URL = "http://xxgk.vecc-mep.org.cn/vin/";
	
	private static final String SOAP_MANUFID = "91110228MA0037BTXB";
	
	private static final String SOAP_PASSWORD = "CLxY32t8kmr8K5u8";
	
	public static final String getSOAPKey() throws Exception{
		WSXxgkVin ws = new WSXxgkVin();
		WSXxgkVinSoap soap = ws.getWSXxgkVinSoap();
		String str = soap.login(SOAP_MANUFID, SOAP_PASSWORD);
		HashMap<String, String> result = getSOAPResult(str);
		if(isSOAPSucceed(result)){
			System.out.println(getSOAPData(result));
			return getSOAPData(result);
		}
		else{
			System.out.println(getSOAPData(result));
			throw new Exception(getSOAPData(result));
		}
	}
	
	public String upload(String vin) throws Exception{
		boolean isFTPSucceed = FTPUtil.upload(vin);
		if(!isFTPSucceed){
			throw new Exception("上传PDF失败"); 
		}
		
		String key = getSOAPKey();
		WSXxgkVin ws = new WSXxgkVin();
		WSXxgkVinSoap soap = ws.getWSXxgkVinSoap();
		StringBuffer s = new StringBuffer("<vindatas><vindata><vin>"+vin+"</vin><xxgkh>");

		HashMap<String, Object> result = getEPI(vin);
		if(Boolean.valueOf(convert2String(result.get("isOk")))){
			ATRow atRow = (ATRow)result.get("object");
			String publicationNumber = convert2String(atRow.getValue("publication_number")).replaceAll("信息公开编号：", "");
			s.append(publicationNumber);
			s.append("</xxgkh><sb>");
			String trademark = convert2String(atRow.getValue("trademark"));
			s.append(trademark);
			s.append("</sb><sccdz>");
			String factoryAddress = convert2String(atRow.getValue("factory_address"));
			s.append(factoryAddress);
			s.append("</sccdz><fdjh>");
			String engineCodeAndCertificatePrintDate = getEngineCodeAndCertificatePrintDate(vin);
			String[] ec = engineCodeAndCertificatePrintDate.split(":");
			String engineCode = ec[0];
			s.append(engineCode);
			s.append("</fdjh><scdate>");
			String productionDate = convert2String(atRow.getValue("production_date"));
			productionDate = productionDate.replaceAll("车辆生产日期：", "").replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "");
			s.append(productionDate);
			s.append("</scdate><ccdate>");
			String certificatePrintDate = ec[1];
			s.append(certificatePrintDate);
			s.append("</ccdate><ccsy>");
			String factoryInspection = convert2String(atRow.getValue("factory_inspection"));
			String[] fi = factoryInspection.split("，");
			String inspectionItem = fi[0];
			s.append(inspectionItem);
			s.append("</ccsy><ccjl>");
			String inspectionConclusion = fi[1];
			s.append(inspectionConclusion);
			s.append("</ccjl><gkwww>");
			String officialSite = convert2String(atRow.getValue("official_site"));
//			officialSite = "http://www.xxx.com.cn";
			officialSite = "http://www.borgward.com.cn";
			s.append(officialSite);
			s.append("</gkwww><fdjsb></fdjsb><fdjsccdz></fdjsccdz></vindata></vindatas>");
			System.out.println(s);
		}
		else{
			throw new Exception("环保信息不存在");
		}
		
		String str = soap.sendVinData(key, s.toString());
		HashMap<String, String> map = getSOAPResult(str);
		soap.logout(key);
		if(isSOAPSucceed(map)){
			System.out.println(getSOAPData(map));
			return getSOAPData(map);
		}
		else{
			System.out.println(getSOAPData(map));
			throw new Exception(getSOAPData(map));
		}
	}
	
//	public static String upload(String vin) throws Exception{
//		String key = getSOAPKey();
//		WSXxgkVin ws = new WSXxgkVin();
//		WSXxgkVinSoap soap = ws.getWSXxgkVinSoap();
//		StringBuffer s = new StringBuffer();
//		s.append("<vindatas><vindata><vin>LXXXXXXXXXXXXXXXX</vin><xxgkh>CN QQ G5 Z2 0000000000 000001</xxgkh><sb>XXX</sb>");
//		s.append("<sccdz>XX省XX市XX路XX号</sccdz><fdjh>XX35684</fdjh><scdate>2011-07-03</scdate><ccdate>2011-07-03</ccdate>");
//		s.append("<ccsy>双怠速试验</ccsy><ccjl>合格</ccjl><gkwww>http://www.xxx.com.cn</gkwww><fdjsb>YYY</fdjsb>");
//		s.append("<fdjsccdz>YY省YY市ZZ路ZZ号</fdjsccdz></vindata></vindatas>");
//		String str = soap.sendVinData(key, s.toString());
//		HashMap<String, String> result = getSOAPResult(str);
//		if(isSOAPSucceed(result)){
//			System.out.println(getSOAPData(result));
//			return getSOAPData(result);
//		}
//		else{
//			System.out.println("上传异常:"+getSOAPData(result));
//			throw new Exception("上传异常:"+getSOAPData(result));
//		}
//	}
	
	public static String delData(String vin) throws Exception{
		String key = getSOAPKey();
		WSXxgkVin ws = new WSXxgkVin();
		WSXxgkVinSoap soap = ws.getWSXxgkVinSoap();
		String str = soap.delData(key, vin);
		HashMap<String, String> result = getSOAPResult(str);
		if(isSOAPSucceed(result)){
			System.out.println(getSOAPData(result));
			return getSOAPData(result);
		}
		else{
			System.out.println(getSOAPData(result));
			throw new Exception(getSOAPData(result));
		}
	}
	
	/**
	 * 取得环保数据
     *
	 * @param vin
	 * @return
	 */
	public HashMap<String, Object> getEPI(String vin){
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			UTRowFilter filter = getFunctions().createATRowFilter("MES_PR_EPI");
			filter.forColumnNameEqualTo("vin", vin);
			Vector<ATRow> atRows = filter.exec();
			if(null==atRows || atRows.size()<=0){
				generateEnvironmentProtectionInformation(vin, false);
			}
			atRows = filter.exec();
			if(null==atRows || atRows.size()<=0){
				result.put("isOk", false);
				result.put("errorMessage", "VIN【"+vin+"】环保信息不存在");
			}
			else{
				result.put("isOk", true);
				result.put("object", atRows.elementAt(0));
			}
			return result;
		}
		catch (Exception e){
			result.put("isOk", false);
			result.put("errorMessage", e.toString());
			return result;
		}
	}
	
	/**
	 * 取得环保数据
     *
	 * @param vin
	 * @return
	 */
	public ATRow getEnvironmentProtectionInformation(String vin){
		try {
			UTRowFilter filter = getFunctions().createATRowFilter("MES_PR_EPI");
			filter.forColumnNameEqualTo("vin", vin);
			Vector<ATRow> atRows = filter.exec();
			if(null==atRows || atRows.size()<=0){
				generateEnvironmentProtectionInformation(vin, false);
			}
			atRows = filter.exec();
			if(null==atRows || atRows.size()<=0){
				return null;
			}
			return atRows.elementAt(0);
		}
		catch (Exception e){
			return null;
		}
	}
	
	public HashMap<String, String> createReportDataRow(ATRow atRow){
		if(atRow==null){
			return null;
		}
		try{
			HashMap<String, String> m = new HashMap<String, String>();
			UTDefinition atDefinition = getFunctions().getATDefinition("MES_PR_EPI");
			List<String> atColumnDefinitionNames = atDefinition.getUTColumnDefinitionNames();
			for(String name : atColumnDefinitionNames){
				m.put(name, convert2String(atRow.getValue(name)));
			}
			return m;
		}
		catch (Exception e){
			return null;
		}
	}
	
	/**
	 * 生成环保数据
     *
	 * @param vin
	 * @param regenerate
	 */
	private void generateEnvironmentProtectionInformation(String vin, boolean regenerate) throws Exception{
		try {
			if(regenerate){
				UTRowFilter filter = getFunctions().createATRowFilter("MES_PR_EPI");
				filter.forColumnNameEqualTo("vin", vin);
				Vector<ATRow> vectorData = filter.exec();
				if(null!=vectorData && vectorData.size()>0){
					//TODO:transaction
					for(ATRow atRow : vectorData){
						atRow.delete(getFunctions().getDBTime(), "", null);
					}
				}
			}
			UTHandler atHandler = getFunctions().createATHandler("MES_PR_EPI");
			UTRow atRow = atHandler.createATRow();
			setOrderInformation(atRow, vin);
			String vehicleCategory = getVehicleCategoryByVin(vin);
			if(vehicleCategory.startsWith("ERROR:")){
				throw new Exception(vehicleCategory.replaceAll("ERROR:", ""));
			}
			setVehicleCategoryInformation(atRow, vehicleCategory, vin);
			atRow.save(getFunctions().getDBTime(), "", null);
		}
		catch (Exception e){
			throw e;
		}
	}
	
	/**
	 * 车辆信息赋值
     *
	 * @param atRow
	 * @param vin
	 * @throws Exception
	 */
	private void setOrderInformation(UTRow atRow, String vin) throws Exception{
		atRow.setValue("vin", vin);  //VIN号，条码来源
		String qrCode = getQRCodeString(vin);
		atRow.setValue("qr_code", qrCode);  //二维码来源
//		String productionDate = getProductionDate(vin, "yyyy年MM月dd日");
		String productionDate = getProductionDateByVin(vin);
		if(StringUtil.isNull(productionDate)){
			throw new Exception("取得【"+vin+"】车辆生产日期失败");
		}
		atRow.setValue("production_date", "车辆生产日期："+productionDate);  //车辆生产日期
	}
	
	public static String getQRCodeString(String vin){
		String url = getQRCodeURL();
		return url+vin;
	}
	
	private static String getQRCodeURL(){
		return QRCODE_URL;
	}
	
	/**
	 * 取得车辆生产日期
     *
	 * @param vin
	 * @param dateFormat
	 * @return
	 */
//	private String getProductionDate(String vin, String dateFormat){
//		try {
//			//TODO:productionDate
//			Time productionDate = getFunctions().getDBTime();
//			DateFormat df = new SimpleDateFormat(dateFormat);
//			return df.format(productionDate.getCalendar().getTime());
//		}
//		catch (MESException e) {
//			return "";
//		}
//	}
	
	/**
	 * 取得车辆生产日期
     *
	 * @param vin
	 * @return
	 */
	private String getProductionDateByVin(String vin){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@(description=(address_list=(load_balance=on)(failover=on)(address=(protocol=tcp)(host=172.24.180.93)(port=1521))(address=(protocol=tcp)(host=172.24.180.94)(port=1521)))(connect_data=(service_name=oramesp)(SERVER = DEDICATED)(failover_mode=(type=select)(method=basic))))";
			String user = "MESPROD";
			String password = "MESPROD";
			con = DriverManager.getConnection(url, user, password);
			String sql = " select to_char(entry_time_t, 'yyyy') || '年' || to_char(entry_time_t, 'mm') || '月' || to_char(entry_time_t, 'dd') || '日' from at_mes_pp_car_move where vin_s = ? and stage_s = '5020' ";
			ps = con.prepareStatement(sql);
			ps.setString(1, vin);
			rs = ps.executeQuery();
			if(!rs.next()){
				return "";
			}
			String productionDate = rs.getString(1);
			return productionDate;
		}
		catch(Exception e){
			return "";
		}
		finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(con!=null){
					con.close();
				}
			}
			catch(Exception e){
				return "";
			}
		}
	}
	
	/**
	 * 取得车辆发动机号和合格证打印日期
     *
	 * @param vin
	 * @return
	 */
	private String getEngineCodeAndCertificatePrintDate(String vin){
//		return "XX35684:2017-01-07";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@(description=(address_list=(load_balance=on)(failover=on)(address=(protocol=tcp)(host=172.24.180.93)(port=1521))(address=(protocol=tcp)(host=172.24.180.94)(port=1521)))(connect_data=(service_name=oramesp)(SERVER = DEDICATED)(failover_mode=(type=select)(method=basic))))";
			String user = "MESPROD";
			String password = "MESPROD";
			con = DriverManager.getConnection(url, user, password);
			String sql = " select enginecode_s, to_char(startcertidate_t, 'yyyy-mm-dd') from at_mes_certificate_pr where vin_s = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, vin);
			rs = ps.executeQuery();
			if(!rs.next()){
				return ":";
			}
			String engineCode = rs.getString(1);
			String startCertiDate = rs.getString(2);
			if(StringUtil.isNotNull(engineCode) && StringUtil.isNotNull(startCertiDate)){
				return engineCode+":"+startCertiDate;
			}
			return ":";
		}
		catch(Exception e){
			return ":";
		}
		finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(con!=null){
					con.close();
				}
			}
			catch(Exception e){
				return ":";
			}
		}
	}
	
	/**
	 * 取得车辆环保信息车型
     *
	 * @param vin
	 * @return
	 */
	private String getVehicleCategoryByVin(String vin){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@(description=(address_list=(load_balance=on)(failover=on)(address=(protocol=tcp)(host=172.24.180.93)(port=1521))(address=(protocol=tcp)(host=172.24.180.94)(port=1521)))(connect_data=(service_name=oramesp)(SERVER = DEDICATED)(failover_mode=(type=select)(method=basic))))";
			String user = "MESPROD";
			String password = "MESPROD";
			con = DriverManager.getConnection(url, user, password);
			String sql = " select vehicle_categ_s, publicmodel_s, ref_atrkey_i from uda_order where vin_s = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, vin);
			rs = ps.executeQuery();
			if(!rs.next()){
				return "ERROR:VIN【"+vin+"】查询配置车型失败:订单不存在";
			}
			String vehicleCateg = rs.getString(1);
			String publicmodel = rs.getString(2);
			Integer refAtrkey = rs.getInt(3);
			if(StringUtil.isNull(vehicleCateg)){
				return "ERROR:VIN【"+vin+"】查询配置车型失败:车型不存在";
			}
			if(StringUtil.isNull(publicmodel)){
				return "ERROR:VIN【"+vin+"】查询配置车型失败:公告车型不存在";
			}
			if(refAtrkey==null || refAtrkey<=0){
				return "ERROR:VIN【"+vin+"】查询配置车型失败:公告车型不存在";
			}
			sql = " select feature_code_s from at_mes_interface_erp_orderfc where parent_key = ? and feature_code_s like 'A5__' ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, refAtrkey);
			rs = ps.executeQuery();
			if(!rs.next()){
				return "ERROR:VIN【"+vin+"】查询配置车型失败:发动机特征数据不存在";
			}
			String featureCode = rs.getString(1);
			String engineType = "A";
			if("A5G6".equals(featureCode)){
				engineType = "B";
			}
			if(StringUtil.isNull(engineType)){
				return "ERROR:VIN【"+vin+"】查询配置车型失败:发动机特征不存在";
			}
			return publicmodel+"("+engineType+")";
		}
		catch(Exception e){
			return "ERROR:VIN【"+vin+"】查询配置车型异常:"+e.toString();
		}
		finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(con!=null){
					con.close();
				}
			}
			catch(Exception e){
				return "ERROR:关闭数据库连接异常:"+e.toString();
			}
		}
	}
	
	/**
	 * 车型信息赋值
     *
	 * @param atRow
	 * @param vehicleCategory
	 * @param vin
	 * @throws Exception
	 */
	private void setVehicleCategoryInformation(UTRow atRow, String vehicleCategory, String vin) throws Exception{
		if(StringUtil.isNull(vehicleCategory)){
			throw new Exception("取得【"+vin+"】车型信息失败");
		}
		UTRowFilter filter = getFunctions().createATRowFilter("MES_PR_EPI_Config");
		filter.forColumnNameEqualTo("vehicle_category", vehicleCategory);
		Vector<ATRow> vectorData = filter.exec();
		if(null==vectorData || vectorData.size()<=0){
			throw new Exception("取得【"+vin+"】车型【"+vehicleCategory+"】信息失败");
		}
//		String publicationNumber = getPublicationNumber(vehicleCategory);
//		atRow.setValue("publication_number", "信息公开编号："+publicationNumber);  //信息公开编号
		ATRow config = vectorData.elementAt(0);
		String publicationNumberConfig = convert2String(config.getValue("publication_number"));
//		String publicationNumber = getPublicationNumber(publicationNumberConfig);
		String publicationNumber = publicationNumberConfig;
		atRow.setValue("publication_number", "信息公开编号："+publicationNumber);  //信息公开编号
		String companyName = "    "+convert2String(config.getValue("company_name"));
//		companyName += "声明：本清单为本企业依据《中华人民共和国大气污染防治法》和环境保护部相关规定公开的机动车环保信息，本企业对本清单所有内容的真实性、准确性、及时性和完整性负责。"
//		            +"本公司承诺：我公司VIN码（见本页条形码）的轻型汽油车符合《轻型汽车污染物排放限值及测量方法（中国第五阶段）》（GB 18352.5-2013）、《点燃式发动机汽车排气污染物排放限值及测量方法（双怠速法及简易工况法）》（GB 18285-2005）"
//				    +"和《汽车加速行驶车外噪声限值及测量方法》（GB 1495-2002）第Ⅱ阶段的要求，同时符合乘用车内空气质量标准和相关标准规定的环境保护耐久性要求。";
		atRow.setValue("company_name", companyName);  //公司名称
		atRow.setValue("car_type", convert2String(config.getValue("car_type")));  //车辆型号
		atRow.setValue("trademark", convert2String(config.getValue("trademark")));  //商标
		atRow.setValue("vehicle_classification", convert2String(config.getValue("vehicle_classification")));  //汽车分类
		atRow.setValue("emission_phase", convert2String(config.getValue("emission_phase")));  //排放阶段
		String recognitionMethodAndLocation = "";
		String recognitionMethod = convert2String(config.getValue("recognition_method"));
		String recognitionLocation = convert2String(config.getValue("recognition_location"));
		if(StringUtil.isNotNull(recognitionMethod)){
			recognitionMethodAndLocation = "识别方法："+recognitionMethod+" ";
		}
		if(StringUtil.isNotNull(recognitionLocation)){
			recognitionMethodAndLocation += "位置："+recognitionLocation+" ";
		}
		atRow.setValue("recognition", recognitionMethodAndLocation);  //车型的识别方法和位置
		atRow.setValue("manufacturer_name", convert2String(config.getValue("manufacturer_name")));  //车辆制造商名称
		atRow.setValue("factory_address", convert2String(config.getValue("factory_address")));  //生产厂地址
		//GB 18352.5-2013
		atRow.setValue("inspection_institution_1", convert2String(config.getValue("inspection_institution_1")));  //检测机构
		atRow.setValue("inspection_conclusion_1", convert2String(config.getValue("inspection_conclusion_1")));  //检测结论
		//GB 18285-2005
		atRow.setValue("inspection_institution_2", convert2String(config.getValue("inspection_institution_2")));  //检测机构
		atRow.setValue("inspection_conclusion_2", convert2String(config.getValue("inspection_conclusion_2")));  //检测结论
		//GB 1495-2002
		atRow.setValue("inspection_institution_3", convert2String(config.getValue("inspection_institution_3")));  //检测机构
		atRow.setValue("inspection_conclusion_3", convert2String(config.getValue("inspection_conclusion_3")));  //检测结论
		//GB/T 27630-2011
		atRow.setValue("inspection_institution_4", convert2String(config.getValue("inspection_institution_4")));  //检测机构
		atRow.setValue("inspection_conclusion_4", convert2String(config.getValue("inspection_conclusion_4")));  //检测结论
		atRow.setValue("factory_inspection", convert2String(config.getValue("factory_inspection")));  //出厂检验项目及结论
		//TODO:污染控制技术信息暂定为按车型配置
		//发动机型号/生产厂
		String engineType = convert2String(config.getValue("engine_type"));
		String engineManufacturer = convert2String(config.getValue("engine_manufacturer"));
		if(StringUtil.isNull(engineType) && StringUtil.isNull(engineManufacturer)){
			atRow.setValue("engine", "无");
		}
		else{
			atRow.setValue("engine", engineType+"/"+engineManufacturer);
		}
		//催化转化器型号/生产厂
		String catalyticConverterFrontModel = convert2String(config.getValue("cc_front_model"));
		String catalyticConverterBackModel = convert2String(config.getValue("cc_back_model"));
		String catalyticConverterFrontManufacturer = convert2String(config.getValue("cc_front_manufacturer"));
		String catalyticConverterBackManufacturer = convert2String(config.getValue("cc_back_manufacturer"));
		String catalyticConverter = connectModelAndManufacturer(catalyticConverterFrontModel, catalyticConverterBackModel, catalyticConverterFrontManufacturer, catalyticConverterBackManufacturer);
		atRow.setValue("catalytic_converter", catalyticConverter);
//		if(StringUtil.isNull(catalyticConverterFrontModel) && StringUtil.isNull(catalyticConverterBackModel)
//				&& StringUtil.isNull(catalyticConverterFrontManufacturer) && StringUtil.isNull(catalyticConverterBackManufacturer)){
//			atRow.setValue("catalytic_converter", "无");
//		}
//		else{
//			atRow.setValue("catalytic_converter", "前："+catalyticConverterFrontModel+"；后："+catalyticConverterBackModel
//					+"/前："+catalyticConverterFrontManufacturer+"；后："+catalyticConverterBackManufacturer);
//		}
		//涂层/载体/封装生产厂
		String coatingManufacturer = convert2String(config.getValue("coating_manufacturer"));
		String carrierManufacturer = convert2String(config.getValue("carrier_manufacturer"));
		String encapsulationManufacturer = convert2String(config.getValue("encapsulation_m"));
		if(StringUtil.isNull(coatingManufacturer) && StringUtil.isNull(carrierManufacturer) && StringUtil.isNull(encapsulationManufacturer)){
			atRow.setValue("cce", "无");
		}
		else{
			atRow.setValue("cce", coatingManufacturer+"/"+carrierManufacturer+"/"+encapsulationManufacturer);
		}
		//燃油蒸发控制装置EVAP型号/生产厂
		String evapModel = convert2String(config.getValue("evap_model"));
		String evapManufacturer = convert2String(config.getValue("evap_manufacturer"));
		String evap = connectModelAndManufacturer(evapModel, "", evapManufacturer, "", "", "");
		atRow.setValue("evap", evap);
//		if(StringUtil.isNull(evapModel) && StringUtil.isNull(evapManufacturer)){
//			atRow.setValue("evap", "无");
//		}
//		else{
//			atRow.setValue("evap", evapModel+"/"+evapManufacturer);
//		}
		//氧传感器EGOS(Exhaust Gas Oxygen Sensor) 型号/生产厂
		String egosFrontModel = convert2String(config.getValue("egos_front_model"));
		String egosBackModel = convert2String(config.getValue("egos_back_model"));
		String egosFrontManufacturer = convert2String(config.getValue("egos_front_manufacturer"));
		String egosBackManufacturer = convert2String(config.getValue("egos_back_manufacturer"));
		String egos = connectModelAndManufacturer(egosFrontModel, egosBackModel, egosFrontManufacturer, egosBackManufacturer);
		atRow.setValue("egos", egos);
//		if(StringUtil.isNull(egosFrontModel) && StringUtil.isNull(egosBackModel)
//				&& StringUtil.isNull(egosFrontManufacturer) && StringUtil.isNull(egosBackManufacturer)){
//			atRow.setValue("egos", "无");
//		}
//		else{
//			atRow.setValue("egos", "前氧："+egosFrontModel+"，后氧："+egosBackModel+"/前氧："+egosFrontManufacturer+"，后氧："+egosBackManufacturer);
//		}
		//曲轴箱排放控制装置型号/生产厂
		String crankcaseEmissionControlModel = convert2String(config.getValue("ce_control_model"));
		String crankcaseEmissionControlManufacturer = convert2String(config.getValue("ce_control_manufacturer"));
		String ceControl = connectModelAndManufacturer(crankcaseEmissionControlModel, "", crankcaseEmissionControlManufacturer, "", "", "");
		atRow.setValue("ce_control", ceControl);
//		if(StringUtil.isNull(crankcaseEmissionControlModel) && StringUtil.isNull(crankcaseEmissionControlManufacturer)){
//			atRow.setValue("ce_control", "无");
//		}
//		else{
//			atRow.setValue("ce_control", crankcaseEmissionControlModel+"/"+crankcaseEmissionControlManufacturer);
//		}
		//EGR型号/生产厂
		String egrModel = convert2String(config.getValue("egr_model"));
		String egrManufacturer = convert2String(config.getValue("egr_manufacturer"));
		String egr = connectModelAndManufacturer(egrModel, "", egrManufacturer, "", "", "");
		atRow.setValue("egr", egr);
//		if(StringUtil.isNull(egrModel) && StringUtil.isNull(egrManufacturer)){
//			atRow.setValue("egr", "无");
//		}
//		else{
//			atRow.setValue("egr", egrModel+"/"+egrManufacturer);
//		}
		//OBD型号/生产厂
		String obdModel = convert2String(config.getValue("obd_model"));
		String obdManufacturer = convert2String(config.getValue("obd_manufacturer"));
		String obd = connectModelAndManufacturer(obdModel, "", obdManufacturer, "", "", "");
		atRow.setValue("obd", obd);
//		if(StringUtil.isNull(obdModel) && StringUtil.isNull(obdManufacturer)){
//			atRow.setValue("obd", "无");
//		}
//		else{
//			atRow.setValue("obd", obdModel+"/"+obdManufacturer);
//		}
		//IUPR监测功能
		atRow.setValue("iupr", config.getValue("iupr"));
		//ECU型号/版本号/生产厂
		String ecuModel = convert2String(config.getValue("ecu_model"));
		String ecuVersion = convert2String(config.getValue("ecu_version"));
		String ecuManufacturer = convert2String(config.getValue("ecu_manufacturer"));
		if(StringUtil.isNull(ecuModel) && StringUtil.isNull(ecuVersion) && StringUtil.isNull(ecuManufacturer)){
			atRow.setValue("ecu", "无");
		}
		else{
			atRow.setValue("ecu", ecuModel+"/"+ecuVersion+"/"+ecuManufacturer);
		}
		//变速器型式/档位数
		String transmissionType = convert2String(config.getValue("transmission_type"));
		String gear = convert2String(config.getValue("gear"));
		if(StringUtil.isNull(transmissionType) && StringUtil.isNull(gear)){
			atRow.setValue("transmission", "无");
		}
		else{
			atRow.setValue("transmission", transmissionType+"/"+gear);
		}
		//消声器型号/生产厂
		String mufflerFrontModel = convert2String(config.getValue("muffler_front_model"));
		String mufflerBackModel = convert2String(config.getValue("muffler_back_model"));
		String mufflerFrontManufacturer = convert2String(config.getValue("muffler_front_m"));
		String mufflerBackManufacturer = convert2String(config.getValue("muffler_back_m"));
		String muffler = connectModelAndManufacturer(mufflerFrontModel, mufflerBackModel, mufflerFrontManufacturer, mufflerBackManufacturer);
		atRow.setValue("muffler", muffler);
//		if(StringUtil.isNull(mufflerFrontModel) && StringUtil.isNull(mufflerBackModel)
//				&& StringUtil.isNull(mufflerFrontManufacturer) && StringUtil.isNull(mufflerBackManufacturer)){
//			atRow.setValue("muffler", "无");
//		}
//		else{
//			atRow.setValue("muffler", "前："+mufflerFrontModel+"；后："+mufflerBackModel+"/前："+mufflerFrontManufacturer+"；后："+mufflerBackManufacturer);
//		}
		//增压器型号/生产厂
		String superchargerModel = convert2String(config.getValue("supercharger_model"));
		String superchargerManufacturer = convert2String(config.getValue("supercharger_m"));
		String supercharger = connectModelAndManufacturer(superchargerModel, "", superchargerManufacturer, "", "", "");
		atRow.setValue("supercharger", supercharger);
//		if(StringUtil.isNull(superchargerModel) && StringUtil.isNull(superchargerManufacturer)){
//			atRow.setValue("supercharger", "无");
//		}
//		else{
//			atRow.setValue("supercharger", superchargerModel+"/"+superchargerManufacturer);
//		}
		//中冷器型式
		String intercooler = convert2String(config.getValue("intercooler"));
		if(StringUtil.isNull(intercooler)){
			atRow.setValue("intercooler", "无");
		}
		else{
			atRow.setValue("intercooler", intercooler);
		}
		atRow.setValue("legal_representative", convert2String(config.getValue("legal_representative")));  //法人代表
		atRow.setValue("address", convert2String(config.getValue("address")));  //地址
		atRow.setValue("telephone_number", convert2String(config.getValue("telephone_number")));  //联系电话
		atRow.setValue("official_site", "本清单内容及相关信息可查询本公司官方网站（"+convert2String(config.getValue("official_site"))+"）");  //官方网站
	}
	
	/**
	 * 连接型号与生产厂
     *
	 * @param frontModel
	 * @param backModel
	 * @param frontManufacturer
	 * @param backManufacturer
	 * @return
	 */
	private String connectModelAndManufacturer(String frontModel, String backModel, String frontManufacturer, String backManufacturer){
		return connectModelAndManufacturer(frontModel, backModel, frontManufacturer, backManufacturer, "前：", "后：");
	}
	
	/**
	 * 连接型号与生产厂
     *
	 * @param frontModel
	 * @param backModel
	 * @param frontManufacturer
	 * @param backManufacturer
	 * @param frontPrefix
	 * @param backPrefix
	 * @return
	 */
	private String connectModelAndManufacturer(String frontModel, String backModel, String frontManufacturer, String backManufacturer
			, String frontPrefix, String backPrefix){
		return connectModelAndManufacturer(frontModel, backModel, frontManufacturer, backManufacturer, frontPrefix, backPrefix, "");
	}
	
	/**
	 * 连接型号与生产厂
     *
	 * @param frontModel
	 * @param backModel
	 * @param frontManufacturer
	 * @param backManufacturer
	 * @param frontPrefix
	 * @param backPrefix
	 * @param totalPrefix
	 * @return
	 */
	private String connectModelAndManufacturer(String frontModel, String backModel, String frontManufacturer, String backManufacturer
			, String frontPrefix, String backPrefix, String totalPrefix){
		String modal = "";
		if(StringUtil.isNotNull(frontModel) && StringUtil.isNotNull(backModel)){
			modal = frontPrefix+frontModel+"；"+backPrefix+backModel;
		}
		else{
			modal = frontModel+backModel;
		}
		String manufacturer = "";
		if(StringUtil.isNotNull(frontManufacturer) && StringUtil.isNotNull(backManufacturer)){
			manufacturer = frontPrefix+frontManufacturer+"；"+backPrefix+backManufacturer;
		}
		else{
			manufacturer = frontManufacturer+backManufacturer;
		}
		if(StringUtil.isNotNull(modal) && StringUtil.isNotNull(totalPrefix)){
			modal = totalPrefix+modal;
		}
		if(StringUtil.isNotNull(manufacturer) && StringUtil.isNotNull(totalPrefix)){
			manufacturer = totalPrefix+manufacturer;
		}
		String result = "";
		if(StringUtil.isNotNull(modal) && StringUtil.isNotNull(manufacturer)){
			result = modal+"/"+manufacturer;
		}
		else{
			result = modal+manufacturer;
		}
		if(StringUtil.isNull(result)){
			result = "无";
		}
		return result;
	}
	
	/**
	 * 生成信息公开编号
     *
	 * @param vehicleCategory
	 * @return
	 */
	private String getPublicationNumber(String publicationNumberConfig){
//		return "CN QQ G5 Z2 9F47000024 000001";
//		return "CN QQ G5 Z2 0H780001-01 000001";
		String sequenceNumber = getSequenceNumber(publicationNumberConfig, 999999);
		if(StringUtil.isNull(sequenceNumber)){
			return "";
		}
		sequenceNumber = "000001";
		return publicationNumberConfig+" "+sequenceNumber;
	}
	
	private String getSequenceNumber(String sequenceName, int maxValue){
		return getSequenceNumber(sequenceName, maxValue, 0, 1);
	}
	
	private String getSequenceNumber(String sequenceName, int maxValue, int initialValue, int incrementValue){
		try{
			UserSequence userSequence = getFunctions().getUserSequenceByName(sequenceName);
			if(null==userSequence){
				userSequence = getFunctions().createUserSequence(sequenceName);
				userSequence.setInitialValue(initialValue);
				userSequence.setIncrementValue(incrementValue);
				userSequence.setMaxValue(maxValue);
				Response response = userSequence.save();
				if(response.isError()){
					return "";
				}
			}
			Response response = userSequence.getNextValue();
			if(response.isError()){
				return "";
			}
			Object result = response.getResult();
			UserSequenceValue userSequenceValue = (UserSequenceValue)result;
			int value = userSequenceValue.getValue();
			return String.format("%06d", value);    
		}
		catch(Exception e){
			return "";
		}
	}
	
	/**
	 * 转换成字符串
     *
	 * @param obj
	 * @return
	 */
	public static final String convert2String(Object obj){
		if(null==obj){
			return "";
		}
		return String.valueOf(obj);
	}
	
	public static final HashMap<String, String> getSOAPResult(String str){
		StringReader sr = new StringReader(str);
		InputSource is = new InputSource(sr);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		HashMap<String, String> result = new HashMap<String, String>();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			result.put("succeed", doc.getElementsByTagName("succeed").item(0).getTextContent());
			result.put("data", doc.getElementsByTagName("data").item(0).getTextContent());
		}
		catch(Exception e){
			result.put("succeed", "false");
			result.put("data", e.toString());
		}
		return result;
	}
	
	public static final boolean isSOAPSucceed(HashMap<String, String> result){
		String succeed = convert2String(result.get("succeed"));
		return (StringUtil.isNotNull(succeed) && "true".equals(succeed));
	}
	
	public static final String getSOAPData(HashMap<String, String> result){
		return convert2String(result.get("data"));
	}
}