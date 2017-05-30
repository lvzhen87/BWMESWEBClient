/*
 * @(#) SiteController.java 2016年7月5日 下午4:45:06
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller;

import java.util.ArrayList;
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
import com.mes.compatibility.client.ATHandler;
import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.ATRowFilter;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.shopflow.common.constants.filtering.IATRowFilterAttributes;
import com.mes.webclient.controller.vo.PrinterBusinessVO;
import com.mes.webclient.controller.vo.PrinterVO;
import com.mes.webclient.service.impl.IMService;

@Controller("printerBusinessController")
@RequestMapping("/printerBusiness.sp")
public class PrinterBusinessController extends BaseController
{
	@Autowired
	IMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(Model model)
	{
		ArrayList<PrinterVO>  printerVOs = this.getPrinters(new PrinterVO());
		model.addAttribute(
				"PrinterVOs", printerVOs);
		return "im/printerBusiness/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(PrinterBusinessVO printerBusVO, Model model , HttpServletRequest request,
			HttpServletResponse response)
	{
		
		 
		long key = printerBusVO.getKey();
		model.addAttribute(INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		ArrayList<PrinterVO>  printerVOs = this.getPrinters(new PrinterVO());
		if (key > 0)
		{
			try
			{
				UTHandler atH = imService.getFunctions().createATHandler("printer_business");
				UTRowFilter  atf = imService.getFunctions().createATRowFilter("printer_business");
				atf.forUTRowKeyEqualTo(key);
				Vector<ATRow> currentList =  atH.getATRowsByFilter(atf, false);
				if(currentList.size() ==0){
					System.out.println("系统数据错误");
				}
				ATRow atr  = currentList.get(0);
				PrinterBusinessVO printerBusVODB = new PrinterBusinessVO() ;
				long dbKey = atr.getKey();
				String s = String.valueOf(atr.getValue("printer_key"));
				Long printer_key = Long.valueOf(s);
				 
				String pb =  (String) atr.getValue("printer_business");
				String flag =  (String)atr.getValue("flag");
				if("on".equals(flag)){
					printerBusVO.setFlag("1");
				}else{
					printerBusVO.setFlag(flag);
				}
				printerBusVO.setKey( dbKey );
				printerBusVO.setPrinterKey(printer_key);
				printerBusVO.setPrinterBusiness(pb);
 				model.addAttribute(VIEW_OBJECT, printerBusVO);
			}
			catch (Exception e)
			{

				e.printStackTrace();
			}
		}else{
			model.addAttribute(VIEW_OBJECT, printerBusVO);
		}
		model.addAttribute("PrinterVOs", printerVOs);
		return "im/printerBusiness/addOrEdit";
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
					UTHandler atH = imService.getFunctions().createATHandler("printer_business");
					UTRowFilter  atf = imService.getFunctions().createATRowFilter("printer_business");
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
	public String queryList( PrinterBusinessVO printerBusVO)
	{
		JSONArray rt = null;
		try
		{
			Long printerKey = printerBusVO.getPrinterKey();
			String printerBus = printerBusVO.getPrinterBusiness();
			UTHandler atH = imService.getFunctions().createATHandler("printer_business");
			UTRowFilter  atf = imService.getFunctions().createATRowFilter("printer_business");
			 
			if( printerKey !=0){
				atf.forColumnNameEqualTo("printer_name", printerKey);
			}
			
			if( printerBus !=null && ! printerBus.equals("")){
				atf.forColumnNameContaining("printer_business", printerBus);
			}
			atf.addOrderBy(IATRowFilterAttributes.CREATIONTIME, IFilterSortOrders.DESCENDING);
			atf.setMaxRows(printerBusVO.getQueryNum());
			Vector<ATRow> currentList = atf.exec();
			System.out.println( currentList.size() );
			Vector<Object> vos = new Vector<Object>();
			for(ATRow atr : currentList)
			{
				PrinterBusinessVO  vo = new PrinterBusinessVO();
				vo.setKey(atr.getKey());
				
				PrinterVO printerVO = new PrinterVO();
				printerVO.setKey(Long.valueOf(String.valueOf(atr.getValue("printer_key"))));
				printerVO = getPrinters(printerVO).get(0);
				vo.setV_printerName(printerVO.getPrinterName());
				vo.setPrinterBusiness((String)atr.getValue("printer_business") );
				vo.setFlag( String.valueOf(atr.getValue("flag")).equals("0")?"不启用":"启用");
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

	

	@RequestMapping(params = "save", method=RequestMethod.POST)  
	public 	@ResponseBody String save(  PrinterBusinessVO printerBusVO ,HttpServletRequest request ,HttpServletResponse response)
	{  
		try
		{
			long key =  printerBusVO.getKey();
			Long printerKey = printerBusVO.getPrinterKey();
			String pb = printerBusVO.getPrinterBusiness();
			String flag = printerBusVO.getFlag();
			if("on".equals(flag)){
				flag="1";
			}else{
				flag="0";
			}
	        //获取文件名
			if( key > 0 ){
				UTHandler atH = imService.getFunctions().createATHandler("printer_business");
				UTRowFilter  atf = imService.getFunctions().createATRowFilter("printer_business");
				 
				atf.forUTRowKeyEqualTo(key);
				Vector<ATRow> v =  atH.getATRowsByFilter(atf, false);
				 
				ATRow atrow =  v.elementAt(0);
				atrow.setValue("printer_key", printerKey);
				atrow.setValue("printer_business", pb);
				atrow.setValue("flag", flag);
				Response res = atrow.save(imService.getFunctions().getDBTime(), null, null);
				if(res.isOk()){
		            	return ajaxDoneSuccess();
		        }else{
		            	return ajaxDoneError(res.getFirstErrorMessage());
		        }
			}else{	
				//验证重复
				UTHandler atH = imService.getFunctions().createATHandler("printer_business");
				UTRow atrow = atH.createATRow();
				atrow.setValue("printer_key", printerKey);
				atrow.setValue("printer_business", pb);
				atrow.setValue("flag", flag);
				
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
	 
	public ArrayList<PrinterVO> getPrinters(PrinterVO vo){
		 
		Long key = vo.getKey();
		String atDefinitionName = "printer_config";
		UTHandler atHandler;
		ArrayList<PrinterVO>  printerVOs = new ArrayList<>();
		try {
		
			atHandler = imService.getFunctions().createATHandler(atDefinitionName);		
			UTRowFilter filter = imService.getFunctions().createATRowFilter(atDefinitionName);
			if(key>0){
				filter.forUTRowKeyEqualTo(key);
			}
			Vector<ATRow> rows = atHandler.getATRowsByFilter(filter, false);
			if(rows!=null&&rows.size()>0){
				for(ATRow atRow:rows){
					PrinterVO tmp = new PrinterVO();
//					tmp = (PrinterVO)ATUtil.getATObject(PrinterVO.class, atRow);
					tmp.setPrinterName(String.valueOf(atRow.getValue("printer_name")));
					tmp.setKey(atRow.getKey());
					printerVOs.add(tmp);	
				}
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return printerVOs;
	}
	
}