package com.mes.webclient.app.demo.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;



public class ClientSocket {
	private Socket client;
private String staionName = null;
	public ClientSocket(String ip, int port) throws UnknownHostException,
			IOException {
		client = new Socket(InetAddress.getByName(ip), port);
		
	}

	public ClientSocket() throws UnknownHostException,
	IOException {
		String ip = "192.168.1.103";//172.18.1.222
		int port = 9500;
		client = new Socket(InetAddress.getByName(ip), port);
	}

	 
		
	
	public void sendData(String data) throws IOException {
		String receiveStr = data; 
//		String[] arrStr = receiveStr.split("::");
//		Map<String,String>    map = new HashMap<String,String>();
//	    for(int i=0; arrStr!=null && i<arrStr.length; i++)
//	    {
//	        String subStr = arrStr[i];
//	        String[] subArr = subStr.split("=");
//	        String subKey = subArr[0];
//	        String subValue = subArr[1];
//	        map.put(subKey, subValue);
//	    }
//		staionName = map.get("station_name");
//		
		
		OutputStream ops = client.getOutputStream();
		OutputStreamWriter opsw = new OutputStreamWriter(ops);
		BufferedWriter bw = new BufferedWriter(opsw);

		bw.write(data);
		bw.flush();
	}

	/**
	 * �������
	 * 
	 * @return
	 * @throws IOException 
	 * @throws IOException 
	 * @throws IOException 
	 */
	public String inceptData() throws IOException{
	
		client.setSoTimeout(300*1000);
		InputStream ips = client.getInputStream();
		 
	    InputStreamReader ipsr = new InputStreamReader(ips,"ISO-8859-1");
	
	/*
		char[] cbuf = new char[1024];
		int i = ipsr.read(cbuf, 0, 1024);
		System.out.println(i);
		String str = new String(cbuf);
		System.out.println(str);
		System.out.println(new String(str.getBytes("ISO-8859-1"),"GBK"));*/
		
 
	    BufferedReader br = new BufferedReader(ipsr);
		String s = "";
//		if ((s = br.readLine()) != null) {
//			s = new String(s.getBytes("ISO-8859-1"), "GBK");
//		}
		while ((s = br.readLine()) != null) {
			s = new String(s.getBytes("ISO-8859-1"), "GBK");
			if( staionName != null && ! staionName.equals("")){
				if(s!=null &&  s.contains(staionName)){
					return s;
				}else{
					System.out.println(s);
				}
			}else{
				return s;
			}
		}
		
		//ipsr.close();
		//ipsr = null;
		return s;
 
	}

	public void close() throws IOException {
		
		client.close();
		client = null;
		
	}
	
	
	public static void main(String[] args) {
		
			try {//192.168.1.13
				ClientSocket c = new ClientSocket("10.23.36.147",4000);
				String str =  "station_name11=xue::vin=vin1::business=hotwax";	
				c.sendData(str);
				int i = 0;
				String rec = "";
				
		    	rec = c.inceptData();
		    	System.out.println( rec );
		
				
				
				c.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
