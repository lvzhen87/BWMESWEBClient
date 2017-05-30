package com.mes.webclient.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JTextField;


//////
import org.apache.commons.lang3.StringUtils;
 
/**
 *
 * 生成位图，并转16进制字符
 *
 */
public class PrintChineseByBmp {
     
     String imgCodes = "" , begin = "^XA" ,content = "",end = "^XZ";
     Integer textNo = 0;
     
     
    public String getCommand( String vinCode) {
        return imgCodes+
                begin + "\n"+ 
                content+vinCode+"\n"+                
                end;
    }
     
    public void printCN(String text,int size,int x,int y){
        BufferedImage img = createImage(text,size);
        String codeData = convertImageToCode(img);
        String t = ((img.getWidth() / 8 + ((img.getWidth() % 8 == 0) ? 0 : 1)) * img.getHeight()) + "";
        String w = (img.getWidth() / 8 + ((img.getWidth() % 8 == 0) ? 0 : 1)) + "";
//      String zpl = "~DGOUTSTR01," + t + "," + w + "," + codeData;
        ++textNo;
        imgCodes+= "~DGtext"+ textNo +"," + t + "," + w + "," + codeData+"\n";
         
        content+="^FO"+ x +","+ y +"\n"+
                 "^XGtext"+ textNo +",1,1^FS\n";
    }
 
    public final BufferedImage createImage(String text,int size) {
        size = size == 0 ? 28 : size;
        Font font = new Font("宋体",Font.BOLD, size);
        JTextField txt = new JTextField();
        txt.setText(text);
        txt.setFont(font);
         
        int width = txt.getPreferredSize().width;
        int height = txt.getPreferredSize().height;
 
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
 
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, width, height);
 
        g2.setFont(font);
        g2.setPaint(Color.BLACK);
 
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;
 
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
 
        g2.drawString(text, (int) x, (int) baseY);
 
        return bi;
    }
 
    public final String convertImageToCode(BufferedImage img) {
        StringBuffer sb = new StringBuffer();
        long clr = 0, n = 0;
        int b = 0;
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                b = b * 2;
                clr = img.getRGB(j, i);
                String s = String.format("%X", clr);
 
                if (s.substring(s.length() - 6, s.length() - 6 + 6).compareTo(
                        "BBBBBB") < 0) {
                    b++;
                }
                n++;
                if (j == (img.getWidth() - 1)) {
                    if (n < 8) {
                        b = b * (2 ^ (8 - (int) n));
                        sb.append(StringUtils.leftPad(String.format("%X", b),
                                2, "0"));
                        // sb.append(String.format("%X", b).PadLeft(2, '0'));
                        b = 0;
                        n = 0;
                    }
                }
                if (n >= 8) {
                    sb.append(StringUtils.leftPad(String.format("%X", b), 2,
                            "0"));
                    // sb.append(String.format("%X", b).PadLeft(2, '0'));
                    b = 0;
                    n = 0;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
 
    }
     
    public void runPrint(String str , String printerName) throws PrintException {
    	PrintService[] pss = PrinterJob.lookupPrintServices();   
		PrintService printer = null;  
		if( printerName != null && !printerName.equals("")){
			for (int i = 0; i < pss.length; i++) {   
			    String sps = pss[i].getName();  
			    System.out.println(sps);
			    //如果打印机名称相同   
			    if(sps.equals(printerName)){   
			    	printer = pss[i];   
			    }    
			}   
		}else{
	        printer = PrintServiceLookup.lookupDefaultPrintService();
	        if (printer == null) {
	            System.out.println("没有发现条码打印机.");
	            return;
	        }
		}
        DocPrintJob job = printer.createPrintJob();
        byte[] by = str.getBytes();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(by, flavor, null);
        job.print(doc, null);
    }
     
    public static void main(String[] args) throws PrintException {
        PrintChineseByBmp obj = new PrintChineseByBmp();
        String vin = "LA91E61E3G1HZG168";
        obj.printCN("G20宝石蓝舒适版标配（仿皮座椅双侧风道）x1rcb435",25,30,300);
        obj.printCN("宝3石q蓝",30,300,300);
        obj.printCN("JAS01",30,30,300);
        String  vinBarCode= "^FO30,30^BCN,120,N,N,N^BY2^FD>:"+vin+"^FS^FO60,165^ADN,36,20^FD"+vin+"^FS" ;
        String command = obj.getCommand(vinBarCode);
        String printerName = "ZDesigner 105SL 203DPI";
        obj.runPrint(command ,printerName);    //打印条码 
        System.out.println(command);
    }
}

