package com.mes.webclient.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;



public class PrinterUtil  {
	public static String[] getLocalPrinters() {
		
		DocFlavor dof = javax.print.DocFlavor.INPUT_STREAM.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		PrintService allService[] = PrintServiceLookup.lookupPrintServices(dof,
				pras);
		;
		String printers[] = new String[allService.length];
		if (printers != null && printers.length > 0) {
			for (int i = 0; i < allService.length; i++) {
				printers[i] = allService[i].getName();
			}

		}
		return printers;
	}

	public String getLocalDefaultPrinter() {
		String printer = "";
		PrintService defaultService = PrintServiceLookup
				.lookupDefaultPrintService();

		if (defaultService != null) {

			printer = defaultService.getName();
		}
		return printer;
	}

	public String getLocalDefaultPrinterTest() {
	
		String printer = "";
		PrintService defaultService = PrintServiceLookup
				.lookupDefaultPrintService();

		if (defaultService != null) {
			PrintServiceAttributeSet set = defaultService.getAttributes();

			Attribute taskCount = set
					.get(javax.print.attribute.standard.QueuedJobCount.class);
			System.out.println(taskCount.toString());

			Attribute isAccept = set.get(PrinterIsAcceptingJobs.class);
			System.out.println(isAccept.toString());

			

		}
		return printer;
	}

	public void printBarcode4Torque(String barcode, String ip){
	    //println("条码打印："+barcode)
	    String fileName = "test1";
	    String dataInput = barcode;
	    String content = "^XA\r\n" + "^RS8\r\n" + "^BY2,3,120^FT63,190^BCN,,Y,N^FD" + dataInput + "^FS\r\n" + "^XZ";
	    				
	    generateTorqueBarcodeFile("C:/test", fileName, content);
	    printBySocket("C:/test" + "\\" + fileName, ip);
	}

	public void  generateTorqueBarcodeFile(String filePath, String fileName, String content){
	    String filePathText = filePath;
	    String fileText = fileName;
	    try{
	    	File filePathTemp = new File(filePathText);
		    if(!filePathTemp.exists()){
				filePathTemp.mkdir();
			}
			File file = new File(filePathText + "\\" + fileText);
			
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.writeBytes(content);
	    }catch(Exception e){
	    	
	    }

//	 			String filePathText = filePath;
//	 			String fileText = fileName;
	// 
//	 			File filePathTemp = new File(filePathText);
//	 			if (filePathTemp.exists() == false)
//	 			{
//	 				filePathTemp.mkdir();
//	 			}
	// 
//	 			File file = new File(filePathText + "\\" + fileText);
//	 			RandomAccessFile raf = null;
//	 			try
//	 			{
//	 				raf = new RandomAccessFile(file, "rw");
//	 				raf.writeBytes(content);
//	 			}
//	 			catch (IOException e)
//	 			{
//	 				
//	 			}
//	 			finally
//	 			{
//	 				if (raf != null)
//	 				{
//	 					try
//	 					{
//	 						raf.close();
//	 					}
//	 					catch (IOException e)
//	 					{
//	 						
//	 					}
//	 				}
//	 			}
	}


	public boolean printBySocket(String filePath, String ip){
	    boolean printResult = false;
	    int port = 9100;
	    try{
	    	
	    
	    File file = new File(filePath);
	    byte[] command = new byte[512];
	    String label = null;
	    if(file.exists()){
	       // is = null
	    	ByteArrayOutputStream out = new ByteArrayOutputStream();
	    	InputStream is = new FileInputStream(file);
	        int len = 0;
			while((len=is.read(command))>0){
				out.write(command, 0, len);
			}
			label = new String(out.toByteArray(), "utf-8");
			
			out.close();
			is.close();
	    }

	 //   s = null
	  //  writer = null
	    Socket s = new Socket();
	    s.connect(new InetSocketAddress(ip, port));
	    s.setSoTimeout(200);
	    OutputStream writer = s.getOutputStream();
	    byte[] bytes = label.getBytes("utf-8");
	    writer.write(bytes, 0, bytes.length);
	    writer.flush();
	    printResult = true;
	    }catch(Exception e){
	    	
	    }
	    return printResult;
	}
	
	public static void main(String[] args) {
		PrinterUtil p = new PrinterUtil();
		p.getLocalDefaultPrinterTest();
		String[] stts = p.getLocalPrinters() ;
		for (String string : stts) {
			System.out.println(string);
		}
		

	}
}
