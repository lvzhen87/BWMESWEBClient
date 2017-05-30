
package com.mes.webclient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.mes.webclient.controller.vo.PlanScheduleConVO;
import com.mes.webclient.controller.vo.PlanSchedulePropsVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("PlanScheduleConController")
@RequestMapping("/planschedulecon.sp")
public class PlanScheduleConController extends BaseController
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
	public String toAddOrEdit(PlanScheduleConVO planscheduleConVO, Model model)
	{
		long key = planscheduleConVO.getKey();
		List<String> factoryl = new ArrayList<String>();
		List<String> workshopl = new ArrayList<String>();
		List<String> attrul = new ArrayList<String>();
		Vector<String[]> facVec;
		Vector<String[]> wsVec;
		Vector<String[]> attruVec;
		try
		{
			facVec = planScheduleAPI.getFactories();
			for(int i=0; facVec!=null && i<facVec.size(); i++)
			{
				String factory = facVec.get(i)[0];
				wsVec = planScheduleAPI.getWorkshopByFactory(factory);
				factoryl.add(factory);
				if( wsVec!=null && wsVec.size()>0)
				{
					String workshop = wsVec.get(0)[0];
					workshopl.add(workshop);
					attruVec = planScheduleAPI.getSubAttru(factory, workshop);
					if(attruVec!=null && attruVec.size()>0)
					{
						String subAttru = attruVec.get(0)[0];
						attrul.add(subAttru);
						
					}
					
					
				}
				
			}
		}
		catch (MESException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		model.addAttribute(
//			"WORKSHOP_LIST", workshopl);
//		model.addAttribute(
//			"A_LIST", attrul);
//		model.addAttribute(
//			"B_LIST", attrul);
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
//			factoryl = new ArrayList<String>();
			workshopl = new ArrayList<String>();
			attrul = new ArrayList<String>();
			
			try
			{
				UTRow utRow = planScheduleAPI.getRowByKey(key, "PlanScheduleCon");
				if(utRow!=null)
				{
					String factory = utRow.getValue("factory").toString();
					String workshop = utRow.getValue("workshop").toString();
					String type = utRow.getValue("type").toString();
					String subattru_a = utRow.getValue("subattru_a").toString();
					String subattru_b = utRow.getValue("subattru_b").toString();
					long interval = Integer.parseInt(utRow.getValue("interval").toString());
					
					planscheduleConVO.setFactory(factory);
					planscheduleConVO.setWorkshop(workshop);
					planscheduleConVO.setType(type);
					planscheduleConVO.setSubattru_a(subattru_a);
					planscheduleConVO.setSubattru_b(subattru_b);
					planscheduleConVO.setInterval(interval);
					
//					String factory = facVec.get(i)[0];
					wsVec = planScheduleAPI.getWorkshopByFactory(factory);
//					factoryl.add(factory);
					for (int k=0; wsVec != null && k<wsVec.size(); k++)
					{
						String ws = wsVec.get(k)[0];
						workshopl.add(ws);
						
						if (ws != null && ws.equals(workshop))
						{
							attruVec = planScheduleAPI.getSubAttru(
								factory, workshop);
							for (int m = 0; attruVec != null && m < attruVec.size(); m++)
							{
								String subAttru = attruVec.get(m)[0];
								attrul.add(subAttru);

							}
							model.addAttribute(
								"A_LIST", attrul);
							model.addAttribute(
								"B_LIST", attrul);
						}
						
					}
					model.addAttribute(
						"WORKSHOP_LIST", workshopl);
				}
			}

			catch (MESException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		else
//		{
		model.addAttribute(
			VIEW_OBJECT, planscheduleConVO);
		model.addAttribute(
			"FACTORY_LIST", factoryl);
		
		
//		}
		return "im/orderSchedule/addOrEditCon";
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
			UTRow utRow = planScheduleAPI.getRowByKey(key, "PlanScheduleCon");
//			Vector<String[]> uTRows = planScheduleAPI.getSubUTRowByParentkey(key);
			if(utRow == null)
			{
				return null;
			}
			Response response = utRow.delete(planScheduleAPI.getDBTime(), " ", null);
			if (response.isError())
			{
				throw new Exception("删除排程排程禁止条件失败，原因为：" + response.getFirstErrorMessage());
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
	String toList(PlanScheduleConVO planscheduleConVO)
	{
		JSONArray rt = null;
		try
		{
//			String name = PlanScheduleConVO.getName();
			Vector<String[]> conVec = planScheduleAPI.getConditions();
			
			

			Vector<Object> vos = new Vector<Object>();
			for (String[] con : conVec)
			{
				PlanScheduleConVO planScheduleConVO = new PlanScheduleConVO();
				planScheduleConVO.setKey(Integer.parseInt(con[0]));
				planScheduleConVO.setFactory(con[1]);
				planScheduleConVO.setWorkshop(con[2]);
				if("1".equals(con[3]))
				{
					planScheduleConVO.setType("间隔台数");
				}
				else if("2".equals(con[3]))
				{
					planScheduleConVO.setType("计划提前调整范围");
				}
				planScheduleConVO.setSubattru_a(con[4]);
				planScheduleConVO.setSubattru_b(con[5]);
				planScheduleConVO.setInterval(Integer.parseInt(con[6]));
				vos.add(planScheduleConVO);
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
	 * 查询车间
	 */
	@RequestMapping(params = "toListWorkshop")
	public @ResponseBody
	void toListWorkshop(@RequestParam("factoryName") String factoryName,  HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		List<String> l = new ArrayList<String>();
		try
		{
//			String name = PlanScheduleConVO.getName();
			Vector<String[]> wsVec = planScheduleAPI.getWorkshopByFactory(factoryName);

//			for (int i = 0; wsVec != null && i < wsVec.size(); i++)
//			{
//				String workShop = wsVec.get(i)[0];
//				l.add(workShop);
//			}
//			

			Vector<Object> vos = new Vector<Object>();
			for (String[] con : wsVec)
			{
				PlanScheduleConVO planScheduleConVO = new PlanScheduleConVO();
//				planScheduleConVO.setKey(Integer.parseInt(con[0]));
				planScheduleConVO.setWorkshop(con[0]);
//				planScheduleConVO.setWorkshop(con[2]);
//				planScheduleConVO.setSubattru_a(con[3]);
//				planScheduleConVO.setSubattru_b(con[4]);
				vos.add(planScheduleConVO);
			}
			
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
//			rt = JSONArray.fromObject(vos);

		}
		catch (Exception e)
		{
			logger.error(e);
		}
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
//		model.addAttribute(
//			"WORKSHOP_LIST", l);
//		return rt.toString();
	}
	
	/**
	 * 查询车间
	 */
	@RequestMapping(params = "toListSubAttru")
	public @ResponseBody
	void toListSubAttru(@RequestParam("factoryName") String factoryName, @RequestParam("workShopName") String workShopName, HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		List<String> l = new ArrayList<String>();
		try
		{
//			String name = PlanScheduleConVO.getName();
			Vector<String[]> subAttruVec = planScheduleAPI.getSubAttru(factoryName, workShopName);

//			for (int i = 0; wsVec != null && i < wsVec.size(); i++)
//			{
//				String workShop = wsVec.get(i)[0];
//				l.add(workShop);
//			}
//			

			Vector<Object> vos = new Vector<Object>();
			for (String[] subattru : subAttruVec)
			{
				PlanScheduleConVO planScheduleConVO = new PlanScheduleConVO();
//				planScheduleConVO.setKey(Integer.parseInt(con[0]));
				planScheduleConVO.setSubattru_a(subattru[0]);
//				planScheduleConVO.setWorkshop(con[2]);
//				planScheduleConVO.setSubattru_a(con[3]);
//				planScheduleConVO.setSubattru_b(con[4]);
				vos.add(planScheduleConVO);
			}
			
			jsonObj.put("result", vos);
			res.setContentType("text/html; charset=UTF-8");
//			rt = JSONArray.fromObject(vos);

		}
		catch (Exception e)
		{
			logger.error(e);
		}
		try
		{
			res.getWriter().write(jsonObj.toString());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error(e);
		}
//		model.addAttribute(
//			"WORKSHOP_LIST", l);
//		return rt.toString();
	}
	
	@RequestMapping(params = "save")
	public @ResponseBody
	String save(PlanScheduleConVO planscheduleConVO)
	{
		try
		{
			long key = planscheduleConVO.getKey();
			UTRow utRow = planScheduleAPI.getRowByKey(key, "PlanScheduleCon");
			if (utRow == null)
			{
				utRow = planScheduleAPI.createUTRow("PlanScheduleCon");

			}

			utRow.setValue("factory", planscheduleConVO.getFactory());
			utRow.setValue("workshop", planscheduleConVO.getWorkshop());
			utRow.setValue("type", planscheduleConVO.getType());
			utRow.setValue("subattru_a", planscheduleConVO.getSubattru_a());
			utRow.setValue("subattru_b", planscheduleConVO.getSubattru_b());
			utRow.setValue("interval", planscheduleConVO.getInterval());
			Response response = utRow.save(planScheduleAPI.getDBTime(), " ", null);
			if (response.isError())
			{
				throw new Exception("保存禁止条件失败，原因为:" + response.getFirstErrorMessage());
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
