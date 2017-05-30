/*
 * @(#) UserController.java 2016年7月4日 下午3:01:20
 *
 * Copyright 2016 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mes.webclient.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import com.mes.webclient.util.DateTimeUtils;

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
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.controller.vo.ReceiveInfoVO;
import com.mes.webclient.controller.vo.LogisticsMaindataVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("InRDCReceiveController")
@RequestMapping("inrdcreceive.sp")
public class InRDCReceiveController extends BaseController
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
			return "/im/inrdcreceive/list";
		}
		return "/im/inrdcreceive/list";
	}

	/**
	 * 查询
	 */
	@RequestMapping(params = "toList")
	public @ResponseBody
	String toList(ReceiveInfoVO receiveInfoVO,Model model)
	{
		JSONArray rt = null;
		try
		{
			String bill_no = receiveInfoVO.getBill_no();
			
			 

//			Vector<String[]> deliverInfoVec = logisticsAPI.getRDCDeliverConfigures(bill_no, part_no);
			HashMap deliverInfoVec = logisticsAPI.parseBillNO(bill_no);
			Vector<Object> vos = new Vector<Object>();

				ReceiveInfoVO receiveInfoVOs = new ReceiveInfoVO();
				
				long partKey = logisticsAPI.getPartKeyByNo(deliverInfoVec.get("part_no").toString());
				 if(partKey > 0)
				 {
						receiveInfoVOs.setKey(1);
						receiveInfoVOs.setBill_no(bill_no);
						receiveInfoVOs.setPart_no(deliverInfoVec.get("part_no").toString());
						receiveInfoVOs.setPart_name(deliverInfoVec.get("part_name").toString());
						receiveInfoVOs.setPart_type(deliverInfoVec.get("part_type").toString());
						receiveInfoVOs.setBatch_date(deliverInfoVec.get("batch_date").toString());
						receiveInfoVOs.setSeq_no(deliverInfoVec.get("seq_no").toString());
						receiveInfoVOs.setSupplier(deliverInfoVec.get("supplier").toString());
						receiveInfoVOs.setPack_size(deliverInfoVec.get("pack_size").toString());
						receiveInfoVOs.setPack_code(deliverInfoVec.get("pack_code").toString());

						receiveInfoVOs.setColor(deliverInfoVec.get("color").toString());
						vos.add(receiveInfoVOs);
					
				 }else {
					 receiveInfoVOs.setKey(0);
					 receiveInfoVOs.setPart_no(deliverInfoVec.get("part_no").toString());
					 vos.add(receiveInfoVOs);
//					 model.addAttribute("ErrorMsg", "");
				}
					rt = JSONArray.fromObject(vos);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		
		if(rt!=null)
		{
			return rt.toString();
		}
		else
		{
			return "";
		}
	}

	/**
	 * 保存发货信息
	 */
	@RequestMapping(params = "save")
	public @ResponseBody
	String save(@RequestParam("batch_no") String batch_no,@RequestParam("bill_no") String bill_no,@RequestParam("username") String username,ReceiveInfoVO receiveInfoVO,Model model)
	{
		try
		{
			receiveInfoVO.setBill_no(bill_no);
			receiveInfoVO.setBatch_no(Integer.parseInt(batch_no));
			receiveInfoVO.setOperator(username);
			receiveInfoVO.setReceive_time(DateTimeUtils.formatDate(new Date(), IDateFormat.TIME_LONG));

			HashMap deliverInfoVec = logisticsAPI.parseBillNO(bill_no);
			Vector<Object> vos = new Vector<Object>();

			receiveInfoVO.setPart_no(deliverInfoVec.get("part_no").toString());
			receiveInfoVO.setPart_name(deliverInfoVec.get("part_name").toString());
			receiveInfoVO.setPart_type(deliverInfoVec.get("part_type").toString());
			receiveInfoVO.setBatch_date(deliverInfoVec.get("batch_date").toString());
			receiveInfoVO.setSeq_no(deliverInfoVec.get("seq_no").toString());
			receiveInfoVO.setSupplier(deliverInfoVec.get("supplier").toString());

			receiveInfoVO.setColor(deliverInfoVec.get("color").toString());
			
			String result = logisticsAPI.addInstore(deliverInfoVec.get("part_no").toString(), Integer.parseInt(batch_no));
			if("Success".equals(result))
			{
				Response response = logisticsAPI.saveReceiveInfoData(receiveInfoVO);
				
				if (response.isError())
				{
					throw new Exception("保存收货信息失败，原因为:" + response.getFirstErrorMessage());
				}
			}else {
				//反馈错误信息给前端页面
				model.addAttribute("ErrorMsg", result);
				
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
