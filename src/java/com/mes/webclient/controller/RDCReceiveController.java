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
import com.mes.webclient.controller.vo.ReceiveInfoVO;
import com.mes.webclient.controller.vo.LogisticsMaindataVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("RDCReceiveController")
@RequestMapping("rdcreceive.sp")
public class RDCReceiveController extends BaseController
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
			return "/im/rdcreceive/list";
		}
		return "/im/rdcreceive/list";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(params = "toList")
	public @ResponseBody
	String toList(ReceiveInfoVO receiveInfoVO)
	{
		JSONArray rt = null;
		try
		{
			String bill_no = receiveInfoVO.getBill_no();
			String part_no = receiveInfoVO.getPart_no();
			
		    
			Vector<String[]> deliverInfoVec = logisticsAPI.getRDCReceiveConfigures(bill_no, part_no);
			
			Vector<Object> vos = new Vector<Object>();
			for (String[] deliver : deliverInfoVec)
			{
				ReceiveInfoVO receiveInfoVOs = new ReceiveInfoVO();
				receiveInfoVOs.setKey(Integer.parseInt(deliver[0]));
				receiveInfoVOs.setBill_no(deliver[1]);
				receiveInfoVOs.setPart_no(deliver[2]);
				receiveInfoVOs.setPart_name(deliver[3]);
				receiveInfoVOs.setPart_type(deliver[4]);
				receiveInfoVOs.setBatch_date(deliver[5]);
				receiveInfoVOs.setSeq_no(deliver[6]);
				receiveInfoVOs.setSupplier(deliver[7]);
				receiveInfoVOs.setBatch_no(Integer.parseInt(deliver[8]));
				receiveInfoVOs.setOperator(deliver[9]);
				receiveInfoVOs.setReceive_time(deliver[10]);

				vos.add(receiveInfoVOs);
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