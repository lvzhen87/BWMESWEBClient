
package com.mes.webclient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.mes.compatibility.client.UTDefinitionFilter;
import com.mes.compatibility.client.UTRow;
import com.mes.shopflow.common.constants.IATDefinitionDataManagementTypes;
import com.mes.shopflow.common.constants.IDataTypes;
import com.mes.shopflow.common.constants.IDatabaseGrowthTypes;
import com.mes.transactiongrouping.UserTransaction;
import com.mes.webclient.app.demo.api.PlanScheduleAPI;
import com.mes.webclient.controller.vo.PlanSchedulePropsVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("PlanScheduleController")
@RequestMapping("/planschedule.sp")
public class PlanScheduleController extends BaseController
{
	@Autowired
	IIMService imService;
	PlanScheduleAPI planScheduleAPI = new PlanScheduleAPI();

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{

		return "/im/orderSchedule/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(PlanSchedulePropsVO planschedulePropsVO, Model model)
	{
		long key = planschedulePropsVO.getKey();
		List<String> factoryl = new ArrayList<String>();
		Vector<String[]> facVec;
		try
		{
			facVec = planScheduleAPI.getAllFactories();
			for(int i=0; facVec!=null && i<facVec.size(); i++)
			{
				String factory = facVec.get(i)[0];
				factoryl.add(factory);
			}
		}
		catch (MESException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{

			try
			{
				UTRow utRow = planScheduleAPI.getRowByKey(key, "PlanScheduleAtt");
				if(utRow!=null)
				{
					planschedulePropsVO.setName(utRow.getValue("name").toString());
					planschedulePropsVO.setFactory(utRow.getValue("factory").toString());
					planschedulePropsVO.setWorkshop(utRow.getValue("workshop").toString());
					planschedulePropsVO.setAttribute(utRow.getValue("attribute").toString());
					planschedulePropsVO.setBatch(Integer.parseInt(utRow.getValue("batch").toString()));
					planschedulePropsVO.setPripority(Integer.parseInt(utRow.getValue("pripority").toString()));
				}

			}
			catch (MESException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute(
			VIEW_OBJECT, planschedulePropsVO);
		model.addAttribute(
			"FACTORY_LIST", factoryl);
		return "im/orderSchedule/addOrEdit";
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

//			UserTransaction userTrx = planScheduleAPI.getUserTrx();
//			userTrx.begin();
			UTRow utRow = planScheduleAPI.getRowByKey(key, "PlanScheduleAtt");
			Vector<String[]> subUTRows = planScheduleAPI.getSubUTRowByParentkey(key);
			if(subUTRows!=null && subUTRows.size()>0)
			{
				for(int i=0; i<subUTRows.size();i++)
				{
					long childKey = Integer.parseInt(subUTRows.get(i)[0]);
					UTRow subUTRow = planScheduleAPI.getRowByKey(childKey, "PlanScheduleAttSub");
					Response response = subUTRow.delete(planScheduleAPI.getDBTime(), " ", null);
					if (response.isError())
					{
						throw new Exception("删除排程属性失败，原因为：" + response.getFirstErrorMessage());
					}
				}
			}
			if(utRow == null)
			{
				return null;
			}
			Response response = utRow.delete(planScheduleAPI.getDBTime(), " ", null);
			if (response.isError())
			{
				throw new Exception("删除排程属性失败，原因为：" + response.getFirstErrorMessage());
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
	String toList(PlanSchedulePropsVO planschedulePropsVO)
	{
		JSONArray rt = null;
		try
		{
			String factory = planschedulePropsVO.getFactory();
			String workshop = planschedulePropsVO.getWorkshop();
			String name = planschedulePropsVO.getName();
			Vector<String[]> attruVec = planScheduleAPI.getAttributes(factory, workshop, name);
			
			

			Vector<Object> vos = new Vector<Object>();
			for (String[] attru : attruVec)
			{
				PlanSchedulePropsVO planSchedulePropsVO = new PlanSchedulePropsVO();
				planSchedulePropsVO.setKey(Integer.parseInt(attru[0]));
				planSchedulePropsVO.setFactory(attru[1]);
				planSchedulePropsVO.setWorkshop(attru[2]);
				planSchedulePropsVO.setName(attru[3]);
				planSchedulePropsVO.setAttribute(attru[4]);
				planSchedulePropsVO.setBatch(Integer.parseInt(attru[5]));
				planSchedulePropsVO.setPripority(Integer.parseInt(attru[6]));
				vos.add(planSchedulePropsVO);
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
	String save(PlanSchedulePropsVO planschedulePropsVO)
	{
		try
		{
			long key = planschedulePropsVO.getKey();
			UTRow utRow = planScheduleAPI.getRowByKey(key, "PlanScheduleAtt");
			if (utRow == null)
			{
				utRow = planScheduleAPI.createUTRow("PlanScheduleAtt");

			}

			utRow.setValue("factory", planschedulePropsVO.getFactory());
			utRow.setValue("workshop", planschedulePropsVO.getWorkshop());
			utRow.setValue("name", planschedulePropsVO.getName());
			utRow.setValue("attribute", planschedulePropsVO.getAttribute());
			utRow.setValue("batch", planschedulePropsVO.getBatch());
			utRow.setValue("pripority", planschedulePropsVO.getPripority());
			Response response = utRow.save(planScheduleAPI.getDBTime(), " ", null);
			if (response.isError())
			{
				throw new Exception("保存排程属性失败，原因为:" + response.getFirstErrorMessage());
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
