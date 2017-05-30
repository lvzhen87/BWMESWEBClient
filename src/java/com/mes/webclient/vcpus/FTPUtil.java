package com.mes.webclient.vcpus;


import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;

public class FTPUtil {
	
	private static final String FTP_HOST = "pdfs.vecc-mep.org.cn";
	
	private static int FTP_PORT = 21;
	
	private static final String FTP_USERNAME = "91110228MA0037BTXB";
	
	private static final String FTP_PASSWORD = "CLxY32t8kmr8K5u8";
	
	private static FTPClient ftpClient;
	
	private static void connect() throws Exception{
		ftpClient = new FTPClient();
		ftpClient.connect(FTP_HOST, FTP_PORT);
		ftpClient.login(FTP_USERNAME, FTP_PASSWORD);
		ftpClient.setFileStructure(FTPClient.BINARY_FILE_TYPE);
	}
	
	private static void close() throws Exception{
		if(ftpClient!=null){
			ftpClient.disconnect();
			ftpClient = null;
		}
	}
	
	public static boolean upload(String vin) throws Exception{
		boolean result = false;
		FileInputStream fis = null;
		try{
			String fileName = vin+".pdf";
			connect();
			File file = new File("E:\\环保\\"+fileName);
			fis = new FileInputStream(file);
			ftpClient.enterLocalPassiveMode();
			result = ftpClient.storeFile(fileName, fis);
			fis.close();
			close();
			return result;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(fis!=null){
				fis.close();
				fis = null;
			}
			if(ftpClient!=null){
				ftpClient.disconnect();
				ftpClient = null;
			}
		}
	}
}