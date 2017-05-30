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
import com.mes.webclient.controller.vo.DeliverInfoVO;
import com.mes.webclient.controller.vo.LogisticsMaindataVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("InRDCDeliverController")
@RequestMapping("inrdcdeliver.sp")
public class InRDCDeliverController extends BaseController
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
			return "/im/inrdcdeliver/list";
		}
		return "/im/inrdcdeliver/list";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(params = "toList")
	public @ResponseBody
	String toList(DeliverInfoVO deliverInfoVO)
	{
		JSONArray rt = null;
		try
		{
			String bill_no = deliverInfoVO.getBill_no();
			
//			Vector<String[]> deliverInfoVec = logisticsAPI.getRDCDeliverConfigures(bill_no, part_no);
			HashMap deliverInfoVec = logisticsAPI.parseBillNO(bill_no);
			Vector<Object> vos = new Vector<Object>();
		
				DeliverInfoVO deliverInfoVOs = new DeliverInfoVO();
				long partKey = logisticsAPI.getPartKeyByNo(deliverInfoVec.get("part_no").toString());
				 if(partKey > 0)
				 {
				deliverInfoVOs.setKey(1);
				deliverInfoVOs.setBill_no(bill_no);
				deliverInfoVOs.setPart_no(deliverInfoVec.get("part_no").toString());
				deliverInfoVOs.setPart_name(deliverInfoVec.get("part_name").toString());
				deliverInfoVOs.setPart_type(deliverInfoVec.get("part_type").toString());
				deliverInfoVOs.setBatch_date(deliverInfoVec.get("batch_date").toString());
				deliverInfoVOs.setSeq_no(deliverInfoVec.get("seq_no").toString());
				deliverInfoVOs.setSupplier(deliverInfoVec.get("supplier").toString());
				deliverInfoVOs.setColor(deliverInfoVec.get("color").toString());
				
				deliverInfoVOs.setPack_size(deliverInfoVec.get("pack_size").toString());
				deliverInfoVOs.setPack_code(deliverInfoVec.get("pack_code").toString());
//				deliverInfoVOs.setBatch_no(Integer.parseInt(deliverInfoVec.get("batch_no").toString()));
//				deliverInfoVOs.setDeliver_time(deliverInfoVec.get("part_no").toString());

				vos.add(deliverInfoVOs);
				 }
				else {
					deliverInfoVOs.setKey(0);
					deliverInfoVOs.setPart_no(deliverInfoVec.get("part_no").toString());
					 vos.add(deliverInfoVOs);
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
	String save(@RequestParam("batch_no") String batch_no,@RequestParam("bill_no") String bill_no,@RequestParam("username") String username,DeliverInfoVO deliverInfoVO)
	{
		try
		{
			deliverInfoVO.setBill_no(bill_no);
			deliverInfoVO.setBatch_no(Integer.parseInt(batch_no));
			deliverInfoVO.setOperator(username);
			deliverInfoVO.setDeliver_time(DateTimeUtils.formatDate(new Date(), IDateFormat.TIME_LONG));
			
			HashMap deliverInfoVec = logisticsAPI.parseBillNO(bill_no);
			Vector<Object> vos = new Vector<Object>();
			
			deliverInfoVO.setPart_no(deliverInfoVec.get("part_no").toString());
			deliverInfoVO.setPart_name(deliverInfoVec.get("part_name").toString());
			deliverInfoVO.setPart_type(deliverInfoVec.get("part_type").toString());
			deliverInfoVO.setBatch_date(deliverInfoVec.get("batch_date").toString());
			deliverInfoVO.setSeq_no(deliverInfoVec.get("seq_no").toString());
			deliverInfoVO.setSupplier(deliverInfoVec.get("supplier").toString());

			deliverInfoVO.setColor(deliverInfoVec.get("color").toString());
			String result = logisticsAPI.reduceInstore(deliverInfoVec.get("part_no").toString(), Integer.parseInt(batch_no));			
			if("Success".equals(result))
			{
				Response response = logisticsAPI.saveDeliverInfoData(deliverInfoVO);

				if (response.isError())
				{
					throw new Exception("保存发货信息失败，原因为:" + response.getFirstErrorMessage());
				}
			}
			else
			{
				return ajaxDoneError(result);
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