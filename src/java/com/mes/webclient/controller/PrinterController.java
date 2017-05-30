/*
 * @(#) SiteController.java 2016年7月5日 下午4:45:06
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller;

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
import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.shopflow.common.constants.filtering.IATRowFilterAttributes;
import com.mes.webclient.controller.vo.PrinterVO;
import com.mes.webclient.service.impl.IMService;
import com.mes.webclient.util.PrinterUtil;

@Controller("printerController")
@RequestMapping("/printer.sp")
public class PrinterController extends BaseController
{
	@Autowired
	IMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{
		return "im/printer/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(PrinterVO printerVO, Model model , HttpServletRequest request,
			HttpServletResponse response)
	{
	 
		String[] printerNames = PrinterUtil.getLocalPrinters();
		model.addAttribute("PrinterNames", printerNames);
		
		long key = printerVO.getKey();
		model.addAttribute(INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			try
			{
				//printerVO
				UTHandler atH = imService.getFunctions().createATHandler("printer_config");
				UTRowFilter  atf = imService.getFunctions().createATRowFilter("printer_config");
				atf.forUTRowKeyEqualTo(key);
				
				Vector<ATRow> currentList =  atH.getATRowsByFilter(atf, false);
				if(currentList.size() ==0){
					System.out.println("系统数据错误");
				}
				ATRow atr  = currentList.get(0);
			
				PrinterVO printerVODB = new PrinterVO() ;
				long dbKey = atr.getKey();
				String name = (String)atr.getValue("printer_name");
				String ip =  (String) atr.getValue("printer_ip");
				String bak =  (String)atr.getValue("printer_bak");
				
				printerVODB.setKey( dbKey );
				printerVODB.setPrinterName(name);
				printerVODB.setPrinterIP(ip);
				printerVODB.setPrinterBak(bak);
				model.addAttribute(VIEW_OBJECT, printerVODB);
			}
			catch (Exception e)
			{

				e.printStackTrace();
			}
		}else{
			model.addAttribute(VIEW_OBJECT, printerVO);
		}
		return "im/printer/addOrEdit";
	}

	
	 
	/**
	 * 删除
	 * @return 
	 */
	@RequestMapping(params = "del")
	public @ResponseBody String del(@RequestParam("key") long key)
	{
			try
			{
				if (key > 0)
				{
					UTHandler atH = imService.getFunctions().createATHandler("printer_config");
					UTRowFilter  atf = imService.getFunctions().createATRowFilter("printer_config");
					atf.forUTRowKeyEqualTo(key);
					
					Vector<ATRow> currentList =  atH.getATRowsByFilter(atf, false);
					if(currentList.size() ==0){
						System.out.println("系统数据错误");
					}
					ATRow atRow  = currentList.get(0);
					Response response = atRow.delete(imService.getFunctions().getDBTime(), null,null);
					if (response.isError())
					{
						throw new Exception(response.getFirstErrorMessage());
					}
					return ajaxDoneSuccess();
				}
				else
				{
					throw new Exception("无法找到该对象的key");
				}
			}
			catch (Exception e)
			{
				logger.error(e);
				return ajaxDoneError(e.getLocalizedMessage());
			}
	}

	/**
	 * 查询
	 */
	@ResponseBody
	@RequestMapping(params = "toList")
	public String queryList( PrinterVO printerVO)
	{
		JSONArray rt = null;
		try
		{
			String printerName = printerVO.getPrinterName();
			String ip = printerVO.getPrinterIP();
			UTHandler atH = imService.getFunctions().createATHandler("printer_config");
			UTRowFilter  atf = imService.getFunctions().createATRowFilter("printer_config");
			 
			if( printerName !=null && !printerName.equals("")){
				atf.forColumnNameContaining("printer_name", printerName);
			}
			
			if( ip !=null && ! ip.equals("")){
				atf.forColumnNameContaining("printer_ip", ip);
			}
			atf.addOrderBy(IATRowFilterAttributes.CREATIONTIME, IFilterSortOrders.DESCENDING);
			atf.setMaxRows(printerVO.getQueryNum());
			Vector<ATRow> currentList = atf.exec();
			System.out.println( currentList.size() );
			Vector<Object> vos = new Vector<Object>();
			for(ATRow atr : currentList)
			{
				PrinterVO  vo = new PrinterVO();
				vo.setKey(atr.getKey());
				vo.setPrinterName((String)atr.getValue("printer_name"));
				vo.setPrinterIP((String)atr.getValue("printer_ip") );
				vo.setPrinterBak( atr.getValue("printer_bak") == null?"":atr.getValue("printer_bak").toString());
				vos.add(vo);
			}
			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return null;

	}

	

	@RequestMapping(params = "save", method=RequestMethod.POST)  
	public 	@ResponseBody String save(  PrinterVO printerVO ,HttpServletRequest request ,HttpServletResponse response)
	{  
		try
		{
			long key =  printerVO.getKey();
			String printerName = printerVO.getPrinterName();
			String ip = printerVO.getPrinterIP();
			String bak = printerVO.getPrinterBak();
			if( printerName == null || "".equals(printerName)){
				return ajaxDoneError("打印机名称不能为空");
			}
			if( ip == null || "".equals(ip)){
				return ajaxDoneError("打印机地址不能为空");
			}
			String sql = " select atr_key from UD_printer_config u where u.printer_name_S = '"+printerName+"' and u.printer_ip_S = '"+ip+"' " ;
			if( key > 0 ){
				sql = sql + " and atr_key !=  " + key ;
			}
			Vector arrayData = imService.getFunctions().getArrayDataFromActive(sql, 600);
			if(arrayData.size() >0 ){
				return ajaxDoneError("当前记录已经存在");
			}
	        //获取文件名
			if( key > 0 ){
				UTHandler atH = imService.getFunctions().createATHandler("printer_config");
				UTRowFilter  atf = imService.getFunctions().createATRowFilter("printer_config");
				atf.forUTRowKeyEqualTo(key);
				Vector<ATRow> v =  atH.getATRowsByFilter(atf, false);
				if(v.size() == 0 ){
					 return ajaxDoneError("当前打印机记录["+key+"]不存在");
				}
				ATRow atrow =  v.elementAt(0);
				atrow.setValue("printer_name", printerName);
				atrow.setValue("printer_ip", ip);
				atrow.setValue("printer_bak", bak);
				Response res = atrow.save(imService.getFunctions().getDBTime(), null, null);
				if(res.isOk()){
		            	return ajaxDoneSuccess();
		        }else{
		            	return ajaxDoneError(res.getFirstErrorMessage());
		        }
			}else{	
				//验证重复
				UTHandler atH = imService.getFunctions().createATHandler("printer_config");
				UTRow atrow = atH.createATRow();
				atrow.setValue("printer_name", printerName);
				atrow.setValue("printer_ip", ip);
				atrow.setValue("printer_bak", bak);
				
				Response res = atrow.save(imService.getFunctions().getDBTime(), null, null);
				if(res.isOk()){
		            	return ajaxDoneSuccess();
		        }else{
		            	return ajaxDoneError(res.getFirstErrorMessage());
		        }
			}
			 
           
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
	}
	 
	
}