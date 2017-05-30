package com.mes.webclient.vcpus;

import java.io.StringReader;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.mes.webclient.util.StringUtil;
import com.mes.webclient.vcpus.epi.WSXxgkVin;
import com.mes.webclient.vcpus.epi.WSXxgkVinSoap;

public class SOAPUtil {
	
	private static final String SOAP_MANUFID = "91110228MA0037BTXB";
	
	private static final String SOAP_PASSWORD = "CLxY32t8kmr8K5u8";
	
	private class SOAPResult{
		private boolean succeed;
		private String data;
		private boolean isSucceed() {
			return succeed;
		}
		private void setSucceed(boolean succeed) {
			this.succeed = succeed;
		}
		private String getData() {
			return data;
		}
		private void setData(String data) {
			this.data = data;
		}
		private SOAPResult(boolean b, String s){
			this.setSucceed(b);
			this.setData(s);
		}
	}
	
	public static final String login() throws Exception{
		WSXxgkVin ws = new WSXxgkVin();
		WSXxgkVinSoap soap = ws.getWSXxgkVinSoap();
		String str = soap.login(SOAP_MANUFID, SOAP_PASSWORD);
		SOAPResult result = setSOAPResult(str);
		if(result.isSucceed()){
			System.out.println(result.getData());
			return result.getData();
		}
		else{
			System.out.println(result.getData());
			throw new Exception(result.getData());
		}
	}
	
	public static final String run(String soapMethodName, Object... parameter) throws Exception{
		String key = login();
		WSXxgkVin ws = new WSXxgkVin();
		WSXxgkVinSoap soap = ws.getWSXxgkVinSoap();
		Class clazz = soap.getClass();
		int parameterLength = parameter.length;
		Method method = null;
		String str = "";
		if(0==parameterLength){
			method = clazz.getMethod(soapMethodName, String.class);
			str = (String)method.invoke(soap, key);
		}
		else if(1==parameterLength){
			method = clazz.getMethod(soapMethodName, String.class, parameter[0].getClass());
			str = (String)method.invoke(soap, key, parameter[0]);
		}
		else if(2==parameterLength){
			method = clazz.getMethod(soapMethodName, String.class, parameter[0].getClass(), parameter[1].getClass());
			str = (String)method.invoke(soap, key, parameter[0], parameter[1]);
		}
		else{
			throw new Exception("接口方法【"+soapMethodName+"】参数不符合要求，请联系管理员");
		}
		SOAPResult result = setSOAPResult(str);
		soap.logout(key);
		if(result.isSucceed()){
			System.out.println(result.getData());
			return result.getData();
		}
		else{
			System.out.println(result.getData());
			throw new Exception(result.getData());
		}
	}
	
	public static final SOAPResult setSOAPResult(String str){
		if(StringUtil.isNull(str)){
			return new SOAPUtil().new SOAPResult(false, "没有得到规范的返回结果");
		}
		StringReader sr = new StringReader(str);
		InputSource is = new InputSource(sr);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			String succeed = "";
			if(doc.getElementsByTagName("succeed").getLength()>0){
				succeed = doc.getElementsByTagName("succeed").item(0).getTextContent();
			}
			String data = "";
			if(doc.getElementsByTagName("data").getLength()>0){
				data = doc.getElementsByTagName("data").item(0).getTextContent();
			}
			if(StringUtil.isNull(succeed)){
				return new SOAPUtil().new SOAPResult(false, "没有得到规范的返回结果");
			}
			else{
				if("true".equals(succeed)){
					return new SOAPUtil().new SOAPResult(true, data); 
				}
				else{
					return new SOAPUtil().new SOAPResult(false, data); 
				}
			}
		}
		catch(Exception e){
			return new SOAPUtil().new SOAPResult(false, e.toString());
		}
	}
	
	
}