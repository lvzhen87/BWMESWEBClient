
package com.mes.webclient.controller;

import java.util.List;
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
import com.mes.compatibility.client.UTColumnDefinition;
import com.mes.compatibility.client.UTDefinition;
import com.mes.compatibility.client.UTRow;
import com.mes.shopflow.common.constants.IDataTypes;
import com.mes.webclient.app.demo.api.PlanScheduleAPI;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.controller.vo.PlanScheduleSubPropsVO;
import com.mes.webclient.service.impl.IMService;
import com.mes.webclient.util.DateTimeUtils;

@Controller("PlanScheduleSubController")
@RequestMapping("/planschedulesub.sp")
public class PlanScheduleSubController extends BaseController
{

	@Autowired
	IMService imService;
	PlanScheduleAPI planScheduleAPI = new PlanScheduleAPI();

	@RequestMapping(params = "toList")
	public @ResponseBody
	String loadSubAttribute(@RequestParam("parentKey") long parentKey)
	{
		JSONArray rt = null;
		try
		{
			Vector<String[]> vec = planScheduleAPI.getSubUTRowByParentkey(parentKey);
			if (vec == null)
			{
				return null;
			}

//			List<UTColumnDefinition> atColumnDefinitions = atDefinition.getUTColumnDefinitions();
			Vector<Object> vos = new Vector<Object>();
			for (String[] subattru : vec)
			{
				PlanScheduleSubPropsVO planScheduleSubPropsVO = new PlanScheduleSubPropsVO();
				planScheduleSubPropsVO.setKey(Integer.parseInt(subattru[0]));
				planScheduleSubPropsVO.setSubname(subattru[1]);
				planScheduleSubPropsVO.setSubattribute(subattru[2]);
				planScheduleSubPropsVO.setSubpripority(Integer.parseInt(subattru[3]));
				planScheduleSubPropsVO.setSubBatch(Integer.parseInt(subattru[4]));
				vos.add(planScheduleSubPropsVO);
			}
			rt = JSONArray.fromObject(vos);
		}
		catch (Exception e)
		{
			logger.error(e);
		}

		return rt.toString();
	}

	/**
	 * 添加或编辑
	 * 
	 * @throws MESException
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(@RequestParam("parentKey") long parentKey,
		PlanScheduleSubPropsVO planScheduleSubPropsVO,
		Model model)
	{

		long key = planScheduleSubPropsVO.getKey();	

		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			UTRow utRow;
			try
			{
				utRow = planScheduleAPI.getRowByKey(key, "PlanScheduleAttSub");
				if(utRow!=null)
				{
					planScheduleSubPropsVO.setSubname(utRow.getValue("subname").toString());
					planScheduleSubPropsVO.setSubattribute(utRow.getValue("subattribute").toString());
					planScheduleSubPropsVO.setSubpripority(Integer.parseInt(utRow.getValue("subpripority").toString()));
					planScheduleSubPropsVO.setParentKey(parentKey);
					if(utRow.getValue("subBatch")!=null)
					{
						planScheduleSubPropsVO.setSubBatch(Integer.parseInt(utRow.getValue("subBatch").toString()));
					}
				}
			}
			catch (MESException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
		model.addAttribute(
			VIEW_OBJECT, planScheduleSubPropsVO);
		model.addAttribute(
			"parentKey", parentKey);
		return "/im/orderSchedule/addOrEditSubAttr";
	}

	@RequestMapping(params = "save")
	public @ResponseBody
	String save(@RequestParam("parentKey") long parentKey,
		PlanScheduleSubPropsVO planScheduleSubPropsVO)
	{
		try
		{

			long key = planScheduleSubPropsVO.getKey();
			UTRow utRow = planScheduleAPI.getRowByKey(key, "PlanScheduleAttSub");
			if (utRow == null)
			{
				utRow = planScheduleAPI.createUTRow("PlanScheduleAttSub");

			}

			utRow.setValue("subname", planScheduleSubPropsVO.getSubname());
			utRow.setValue("subattribute", planScheduleSubPropsVO.getSubattribute());
			utRow.setValue("subpripority", planScheduleSubPropsVO.getSubpripority());
			utRow.setValue("subBatch", planScheduleSubPropsVO.getSubBatch());
			utRow.setValue("parentKey", parentKey);
			Response response = utRow.save(planScheduleAPI.getDBTime(), " ", null);
			if (response.isError())
			{
				throw new Exception("保存排程子属性失败，原因为:" + response.getFirstErrorMessage());
			}
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping(params = "del")
	public @ResponseBody
	String del(@RequestParam("key") long key)
	{

		try
		{
			UTRow utRow = planScheduleAPI.getRowByKey(key, "PlanScheduleAttSub");
			if(utRow == null)
			{
				return null;
			}
			Response response = utRow.delete(planScheduleAPI.getDBTime(), " ", null);
			if (response.isError())
			{
				throw new Exception("删除排程子属性失败，原因为：" + response.getFirstErrorMessage());
			}
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ajaxDoneError(e.getLocalizedMessage());
		}
	}

}
