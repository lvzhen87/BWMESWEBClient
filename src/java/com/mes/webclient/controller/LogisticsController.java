/*
 * @(#) UserController.java 2016年7月4日 下午3:01:20
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller;

import java.util.Vector;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTRow;
import com.mes.webclient.app.demo.api.LogisticsManageAPI;
import com.mes.webclient.controller.vo.LogisticsMaindataVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("LogisticsController")
@RequestMapping("logistics.sp")
public class LogisticsController extends BaseController
{
	@Autowired
	IIMService imService;
	
	LogisticsManageAPI logisticsAPI = new LogisticsManageAPI();
	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(String userName, Model model)
	{
		if (StringUtil.isNotNull(userName))
		{
			model.addAttribute(
				"userName", userName);
			return "/im/logisticsmaindata/list";
		}
		return "/im/logisticsmaindata/list";
	}
	
	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(LogisticsMaindataVO logisticsMaindataVO, Model model)
	{
		long key = logisticsMaindataVO.getKey();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		
		if (key > 0)
		{

			try
			{
				UTRow utRow = logisticsAPI.getRowByKey(key, "maindata_part");
				if(utRow!=null)
				{
					/* part_name,pack_code,part_no,pack_size,part_type*/
					logisticsMaindataVO.setPart_name(utRow.getValue("part_name").toString());
					logisticsMaindataVO.setPack_code(utRow.getValue("pack_code").toString());
					logisticsMaindataVO.setPart_no(utRow.getValue("part_no").toString());
					logisticsMaindataVO.setPack_size(utRow.getValue("pack_size").toString());
					logisticsMaindataVO.setPart_type(utRow.getValue("part_type").toString());
				}

			}
			catch (MESException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute(
			VIEW_OBJECT, logisticsMaindataVO);
		return "im/logisticsmaindata/addOrEdit";
	}
	
	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "del")
	public @ResponseBody
	String del(@RequestParam("key") long key)
	{
		try
		{

//			UserTransaction userTrx = planscheduleAPI.getUserTrx();
//			userTrx.begin();
			UTRow utRow = logisticsAPI.getRowByKey(key, "maindata_part");
			if(utRow == null)
			{
				return null;
			}
			Response response = utRow.delete(logisticsAPI.getDBTime(), " ", null);
			if (response.isError())
			{
				throw new Exception("删除物料主数据失败，原因为：" + response.getFirstErrorMessage());
			}
			return ajaxDoneSuccess();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			return ajaxDoneError(e.getLocalizedMessage());
		}
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(params = "toList")
	public @ResponseBody
	String toList(LogisticsMaindataVO logisticsmaindataVO)
	{
		JSONArray rt = null;
		try
		{
			String part_name = logisticsmaindataVO.getPart_name();
			String part_type = logisticsmaindataVO.getPart_type();
		    System.out.println("okokok"+part_name+""+part_type);
			Vector<String[]> logisticsVec = logisticsAPI.getConfigures(part_type, part_name);
			
			Vector<Object> vos = new Vector<Object>();
			for (String[] logistics : logisticsVec)
			{
				LogisticsMaindataVO logisticsMaindataVOs = new LogisticsMaindataVO();
				logisticsMaindataVOs.setKey(Integer.parseInt(logistics[0]));
				logisticsMaindataVOs.setPart_name(logistics[1]);
				logisticsMaindataVOs.setPack_code(logistics[2]);
				logisticsMaindataVOs.setPart_no(logistics[3]);
				logisticsMaindataVOs.setPack_size(logistics[4]);
				logisticsMaindataVOs.setPart_type(logistics[5]);
				vos.add(logisticsMaindataVOs);
			}
			rt = JSONArray.fromObject(vos);

		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return rt.toString();
	}

	@RequestMapping(params = "save")
	public @ResponseBody
	String save(LogisticsMaindataVO logisticsMaindataVO)
	{
		try
		{
			Response response = logisticsAPI.saveMainData(logisticsMaindataVO);
			if (response.isError())
			{
				throw new Exception("保存物料主数据配置失败，原因为:" + response.getFirstErrorMessage());
			}
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
	}

}