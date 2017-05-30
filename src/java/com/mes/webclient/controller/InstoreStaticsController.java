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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.webclient.app.demo.api.LogisticsManageAPI;
import com.mes.webclient.controller.vo.InstoreStaticsVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("InstoreStaticsController")
@RequestMapping("instorestatics.sp")
public class InstoreStaticsController extends BaseController
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
			return "/im/instoreStatics/list";
		}
		return "/im/instoreStatics/list";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(params = "toList")
	public @ResponseBody
	String toList(InstoreStaticsVO instoreStaticsVO)
	{
		JSONArray rt = null;
		try
		{
			String part_name = instoreStaticsVO.getPart_name();
			String part_no = instoreStaticsVO.getPart_no();
			String part_type = instoreStaticsVO.getPart_type();
		
			Vector<String[]> instoreVec = logisticsAPI.getInstoreData(part_name, part_type, part_no);
			
			Vector<Object> vos = new Vector<Object>();
			for (String[] instore : instoreVec)
			{
				InstoreStaticsVO instorestaticsVO = new InstoreStaticsVO();
				instorestaticsVO.setKey(Integer.parseInt(instore[0]));
				instorestaticsVO.setPart_no(instore[1]);
				instorestaticsVO.setPart_name(instore[2]);
				instorestaticsVO.setPart_type(instore[3]);
//				instorestaticsVO.setSupplier(instore[4]);
				instorestaticsVO.setNum(Integer.parseInt(instore[4]));
				vos.add(instorestaticsVO);
			}
			rt = JSONArray.fromObject(vos);

		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return rt.toString();
	}

}