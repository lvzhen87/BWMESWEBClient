package com.mes.webclient.constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import Aladdin.Hasp;
import Aladdin.HaspStatus;

import com.mes.compatibility.client.User;
import com.mes.webclient.proxy.WebServerProxy;
import com.mes.webclient.util.StringUtil;

public class LoginProxy {
	
//	  public static final String vendorCode = new String(
//		      "AzIceaqfA1hX5wS+M8cGnYh5ceevUnOZIzJBbXFD6dgf3tBkb9cvUF/Tkd/iKu2fsg9wAysYKw7RMA" +
//		      "sVvIp4KcXle/v1RaXrLVnNBJ2H2DmrbUMOZbQUFXe698qmJsqNpLXRA367xpZ54i8kC5DTXwDhfxWT" +
//		      "OZrBrh5sRKHcoVLumztIQjgWh37AzmSd1bLOfUGI0xjAL9zJWO3fRaeB0NS2KlmoKaVT5Y04zZEc06" +
//		      "waU2r6AU2Dc4uipJqJmObqKM+tfNKAS0rZr5IudRiC7pUwnmtaHRe5fgSI8M7yvypvm+13Wm4Gwd4V" +
//		      "nYiZvSxf8ImN3ZOG9wEzfyMIlH2+rKPUVHI+igsqla0Wd9m7ZUR9vFotj1uYV0OzG7hX0+huN2E/Id" +
//		      "gLDjbiapj1e2fKHrMmGFaIvI6xzzJIQJF9GiRZ7+0jNFLKSyzX/K3JAyFrIPObfwM+y+zAgE1sWcZ1" +
//		      "YnuBhICyRHBhaJDKIZL8MywrEfB2yF+R3k9wFG1oN48gSLyfrfEKuB/qgNp+BeTruWUk0AwRE9XVMU" +
//		      "uRbjpxa4YA67SKunFEgFGgUfHBeHJTivvUl0u4Dki1UKAT973P+nXy2O0u239If/kRpNUVhMg8kpk7" +
//		      "s8i6Arp7l/705/bLCx4kN5hHHSXIqkiG9tHdeNV8VYo5+72hgaCx3/uVoVLmtvxbOIvo120uTJbuLV" +
//		      "TvT8KtsOlb3DxwUrwLzaEMoAQAFk6Q9bNipHxfkRQER4kR7IYTMzSoW5mxh3H9O8Ge5BqVeYMEW36q" +
//		      "9wnOYfxOLNw6yQMf8f9sJN4KhZty02xm707S7VEfJJ1KNq7b5pP/3RjE0IKtB2gE6vAPRvRLzEohu0" +
//		      "m7q1aUp8wAvSiqjZy7FLaTtLEApXYvLvz6PEJdj4TegCZugj7c8bIOEqLXmloZ6EgVnjQ7/ttys7VF" +
//		      "ITB3mazzFiyQuKf4J6+b/a/Y");
	  
	  public static final String vendorCode = 
			  "+Xame75aNEy4NYJqx2o6VcuiXIXFspkJcUVePhQtAQcGAXrgV4t0xG8RbritsklE8Yhf6OuhdyJBi9gE" + 
			  "8mYTutaqOcO2fT7ve6+c0hah95qhnkjnoLLs17nNye+8Z+1XzTWbjC+GdLxg+TFpCMAiiOyQPPu0sXHR" + 
			  "FXLK+vTBG7/T8NIN/UpM0UUuPmCY2ShTJUBYBwISjHOTjOIroCotDYOh+WMcJcJ2t2QAQ0g+qJwCTQhu" + 
			  "19lMDZesw2R6JJuYMLN4sO4RQHDZYLFSKgleNuRkGibKx9HXHGlpnSLtPGFmB3/d2LhiyrRiYlSBDLNH" + 
			  "UT4S+hUKDg6sJmsq+1RCJVjalET3vqU9O15WfQQzk96jW015BVfG2zgDxPLjkxpuRf63okFOHmCva0cg" + 
			  "SQz5m9t9Lm7qTgQiVOBzSb76LX2Lauj9U9lMrtNEPL8IshJR+mCU0hob6/gOxcUUbaRo+H8yFYFDK9B/" + 
			  "0QK0ysX6lIhFIZrTK0I7ytQY4AbneDbs14n+SmsVwLA72dMOgftJ7GTmbfNWiobun1sU9SL8BOOBq0Zp" + 
			  "ojRIHhb6bu9XgX9OdPQSZlSthmH4MhwHanECcKBXVc3li1Gr32mpLmVXvYrK481ElpCzkjB6SK+E1mI0" + 
			  "B+aqmRuEqrQP4V3jP7ju2Dcr12AhkpaDxgiMUyhZF8TXCMLgXV49n2bYisgBsvercvxzvrsYNJ3C/iRO" + 
			  "ni7+3RerMOq9hYQhRHxAXe3lov2m4GRtm9BVL8ZdanSlPFJ5DqusVj1huUiybMnlPdRJrJkU9kM5fbXZ" + 
			  "XGwQvVvABV8yx70Ehe/8pTJfwmKCCAAcDIbh113m1uINn0Bg96hpbtb5KZ7dWgWkHcO98qhZwgFpNGBv" + 
			  "bu/5gmJrn8k4T9f3c2w/xv9KcOPf25QFjBr8Srhzof5FuNUq8gcxtffhAr0NK6AsSh/wwSu0c5A3k+nd" + 
			  "Pu11pfsP9Zu5vpsc3MYelg==";
	public static void login(String userName,String password,Model model,HttpServletRequest req) throws Exception
	{
		String infos, fname,h2r,h2h,perTime;
	      String recipient= null;
	      int input = 0;
	      int status;
	      Hasp hasp = new Hasp(Hasp.HASP_DEFAULT_FID);
	      char[] filename = new char[256];
	      byte[] update_buffer;
	      RandomAccessFile f=null,f1=null;
	      InputStreamReader reader = new InputStreamReader(System.in);
	      BufferedReader in = new BufferedReader(reader);
	      String ack_data;
	      String updatedata;
/*

        *//******************************************************************
         * hasp.getInfo        HASP_UPDATEINFO
         *   generate update information on customer site
         */
        String scope = new String("<haspscope>"+
                                  "<license_manager hostname=\"localhost\" />"+
                                  "</haspscope>\n");
        infos = hasp.getInfo(scope, Hasp.HASP_UPDATEINFO, vendorCode);
        status = hasp.getLastError();
    
        switch (status) {
          case HaspStatus.HASP_STATUS_OK:
            
            break;
          case HaspStatus.HASP_TOO_MANY_KEYS:
        	  throw new Exception("Too many keys connected");//System.out.println("Too many keys connected\n");
           
          case HaspStatus.HASP_INV_FORMAT:
        	  throw new Exception("Invalid XML info format");//System.out.println("Invalid XML info format\n");
            
          case HaspStatus.HASP_INV_SCOPE:
        	  throw new Exception("Invalid XML scope");//System.out.println("Invalid XML scope\n");
            
          default:
            throw new Exception("Invalid XML scope");
        } 
        //switch (status) 
      
        String scope_s = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
		"<haspscope/>";

		String format = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
		"<haspformat root=\"hasp_info\">" + 
		"    <feature>" + 
		"        <attribute name=\"id\" />" + 
		"        <element name=\"license\" />" + 
		"        <hasp>" + 
		"          <attribute name=\"id\" />" + 
		"          <attribute name=\"type\" />" + 
		"        </hasp>" + 
		"    </feature>" + 
		"</haspformat>" + 
		"";


		String info;

		info = hasp.getInfo(scope_s, format, vendorCode);
		
		int indexbegin = info.indexOf("<exp_date>");
        int indexend = info.indexOf("</exp_date>");
        
        perTime = info.substring(indexbegin + 10, indexend);
	
        Date now = new Date();
        Calendar   calendar   =   new   GregorianCalendar(); 
        calendar.setTime(now); 
        //calendar.add(calendar.DATE,30);
        Date perDate = new Date(Long.valueOf(perTime + "000"));
        now = calendar.getTime();
        if(perDate.before(now))
        {
//        	System.out.println("即将过期，请联系管理员");
        	throw new Exception("已过期，请联系管理员");
        }
        	
    	
		if(StringUtil.isNull(userName) || StringUtil.isNull(password))
		{
			throw new Exception("用户名或密码不允许为空");
		}
		WebServerProxy.login(userName, password);
		model.addAttribute("useName", WebServerProxy.getCurrentUser().getName());
		HttpSession session= req.getSession();
		
		session.setAttribute("loginUser",WebServerProxy.getCurrentUser().getName());
		
		
		
	}
	
	public static void loginSO(String userName,String password,Model model,HttpServletRequest req) throws Exception
	{
		String infos, fname,h2r,h2h,perTime;
	      String recipient= null;
	      int input = 0;
	      int status;
	      Hasp hasp = new Hasp(Hasp.HASP_DEFAULT_FID);
	      char[] filename = new char[256];
	      byte[] update_buffer;
	      RandomAccessFile f=null,f1=null;
	      InputStreamReader reader = new InputStreamReader(System.in);
	      BufferedReader in = new BufferedReader(reader);
	      String ack_data;
	      String updatedata;
/*

        *//******************************************************************
         * hasp.getInfo        HASP_UPDATEINFO
         *   generate update information on customer site
         */
        String scope = new String("<haspscope>"+
                                  "<license_manager hostname=\"localhost\" />"+
                                  "</haspscope>\n");
        infos = hasp.getInfo(scope, Hasp.HASP_UPDATEINFO, vendorCode);
        status = hasp.getLastError();
    
        switch (status) {
          case HaspStatus.HASP_STATUS_OK:
            
            break;
          case HaspStatus.HASP_TOO_MANY_KEYS:
        	  throw new Exception("Too many keys connected");//System.out.println("Too many keys connected\n");
           
          case HaspStatus.HASP_INV_FORMAT:
        	  throw new Exception("Invalid XML info format");//System.out.println("Invalid XML info format\n");
            
          case HaspStatus.HASP_INV_SCOPE:
        	  throw new Exception("Invalid XML scope");//System.out.println("Invalid XML scope\n");
            
          default:
            throw new Exception("Invalid XML scope");
        } 
        //switch (status) 
      
        String scope_s = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
		"<haspscope/>";

		String format = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
		"<haspformat root=\"hasp_info\">" + 
		"    <feature>" + 
		"        <attribute name=\"id\" />" + 
		"        <element name=\"license\" />" + 
		"        <hasp>" + 
		"          <attribute name=\"id\" />" + 
		"          <attribute name=\"type\" />" + 
		"        </hasp>" + 
		"    </feature>" + 
		"</haspformat>" + 
		"";


		String info;

		info = hasp.getInfo(scope_s, format, vendorCode);
		
		int indexbegin = info.indexOf("<exp_date>");
        int indexend = info.indexOf("</exp_date>");
        
        perTime = info.substring(indexbegin + 10, indexend);
	
        Date now = new Date();
        Calendar   calendar   =   new   GregorianCalendar(); 
        calendar.setTime(now); 
        //calendar.add(calendar.DATE,30);
        Date perDate = new Date(Long.valueOf(perTime + "000"));
        now = calendar.getTime();
        if(perDate.before(now))
        {
//        	System.out.println("即将过期，请联系管理员");
        	throw new Exception("已过期，请联系管理员");
        }
        	
    	
		if(StringUtil.isNull(userName) || StringUtil.isNull(password))
		{
			throw new Exception("用户名或密码不允许为空");
		}
		WebServerProxy.login(userName, password);
		User user =WebServerProxy.getCurrentUser();
		model.addAttribute("useName", WebServerProxy.getCurrentUser()
				.getName());
		model.addAttribute("station", user.getUDA("station").toString());
		
		HttpSession session = req.getSession();
		session.setAttribute("souser", user);
	}
}
