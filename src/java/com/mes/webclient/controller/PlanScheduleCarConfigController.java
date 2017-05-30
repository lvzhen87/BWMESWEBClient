
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
import com.mes.webclient.controller.vo.PlanScheduleCarConfigVO;
import com.mes.webclient.controller.vo.PlanScheduleConVO;
import com.mes.webclient.controller.vo.PlanSchedulePropsVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("PlanScheduleCarConfigController")
@RequestMapping("/planscheduleconfig.sp")
public class PlanScheduleCarConfigController extends BaseController
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

		return "/im/orderScheduleConfig/list";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(PlanScheduleCarConfigVO planscheduleCarConfigVO, Model model)
	{
		long key = planscheduleCarConfigVO.getKey();
		List<String> factoryl = new ArrayList<String>();
		List<String> typel = new ArrayList<String>();
		Vector<String[]> facVec;
		Vector<String[]> typeVec;
		Vector<String[]> wsVec;
		Vector<String[]> attruVec;
		Vector<String[]> subattruVec;
		try
		{
			facVec = planScheduleAPI.getFactories();
			for(int i=0; facVec!=null && i<facVec.size(); i++)
			{
				String factory = facVec.get(i)[0];
				factoryl.add(factory);
			}
			
			typeVec = planScheduleAPI.getVehicleTypes();
			for(int i=0; typeVec!=null && i<typeVec.size(); i++)
			{
				String type = typeVec.get(i)[0];
				typel.add(type);
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
			List<String> workshopl = new ArrayList<String>();
			List<String> attrul = new ArrayList<String>();
			List<String> subattrul = new ArrayList<String>();
			try
			{
				UTRow utRow = planScheduleAPI.getRowByKey(key, "CarTypeAttruConfig");
				if(utRow!=null)
				{
					String factory = utRow.getValue("factory").toString();
					String workshop = utRow.getValue("workshop").toString();
					String vehicle_type = utRow.getValue("vehicle_type").toString();
					String attru_name = utRow.getValue("attru_name").toString();
					String subattru_name = utRow.getValue("subattru_name").toString();
					planscheduleCarConfigVO.setVehicle_type(vehicle_type);
					planscheduleCarConfigVO.setFactory(factory);
					planscheduleCarConfigVO.setWorkshop(workshop);
					planscheduleCarConfigVO.setAttru_name(attru_name);
					planscheduleCarConfigVO.setSubattru_name(subattru_name);
					
					wsVec = planScheduleAPI.getWorkshopByFactory(factory);
					for(int j=0; wsVec!=null && j < wsVec.size(); j++)
					{
						String ws = wsVec.get(j)[0];
						workshopl.add(ws);
						if (ws != null && ws.equals(workshop))
						{
							attruVec = planScheduleAPI.getAttru(
								factory, ws);
							for (int m = 0; attruVec != null && m < attruVec.size(); m++)
							{
								
								String attru = attruVec.get(m)[0];
								attrul.add(attru);
								if (attru != null && attru.equals(attru_name))
								{
									subattruVec = planScheduleAPI.getSubAttru(
										factory, ws, attru);
									for (int k = 0; subattruVec != null && k < subattruVec.size(); k++)
									{
										String subattru = subattruVec.get(k)[0];
										subattrul.add(subattru);
									}
									model.addAttribute(
										"SUBATTRU_LIST", subattrul);
								}
							}

							model.addAttribute(
								"ATTRU_LIST", attrul);
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
		model.addAttribute(
			VIEW_OBJECT, planscheduleCarConfigVO);
		model.addAttribute(
			"FACTORY_LIST", factoryl);
		model.addAttribute(
			"TYPE_LIST", typel);
		return "im/orderScheduleConfig/addOrEdit";
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
				PlanScheduleCarConfigVO planScheduleCarConfigVO = new PlanScheduleCarConfigVO();
//				planScheduleConVO.setKey(Integer.parseInt(con[0]));
				planScheduleCarConfigVO.setWorkshop(con[0]);
//				planScheduleConVO.setWorkshop(con[2]);
//				planScheduleConVO.setSubattru_a(con[3]);
//				planScheduleConVO.setSubattru_b(con[4]);
				vos.add(planScheduleCarConfigVO);
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
	@RequestMapping(params = "toListAttru")
	public @ResponseBody
	void toListAttru(@RequestParam("factoryName") String factoryName, @RequestParam("workShopName") String workShopName, HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		List<String> l = new ArrayList<String>();
		try
		{
//			String name = PlanScheduleConVO.getName();
			Vector<String[]> attruVec = planScheduleAPI.getAttru(factoryName, workShopName);

//			for (int i = 0; wsVec != null && i < wsVec.size(); i++)
//			{
//				String workShop = wsVec.get(i)[0];
//				l.add(workShop);
//			}
//			

			Vector<Object> vos = new Vector<Object>();
			for (String[] attru : attruVec)
			{
				PlanScheduleCarConfigVO planScheduleCarConfigVO = new PlanScheduleCarConfigVO();
				planScheduleCarConfigVO.setAttru_name(attru[0]);
				vos.add(planScheduleCarConfigVO);
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
	void toListSubAttru(@RequestParam("factoryName") String factoryName, @RequestParam("workShopName") String workShopName, @RequestParam("attruName") String attruName, HttpServletRequest req,
		HttpServletResponse res)
	{
//		JSONArray rt = null;
		JSONObject jsonObj = new JSONObject();
		List<String> l = new ArrayList<String>();
		
		try
		{
//			String name = PlanScheduleConVO.getName();
//			req.setCharacterEncoding("UTF-8");
			
			Vector<String[]> subAttruVec = planScheduleAPI.getSubAttru(factoryName, workShopName, attruName);

//			for (int i = 0; wsVec != null && i < wsVec.size(); i++)
//			{
//				String workShop = wsVec.get(i)[0];
//				l.add(workShop);
//			}
//			

			Vector<Object> vos = new Vector<Object>();
			for (String[] subattru : subAttruVec)
			{
				PlanScheduleCarConfigVO planScheduleCarConfigVO = new PlanScheduleCarConfigVO();
				planScheduleCarConfigVO.setSubattru_name(subattru[0]);
				vos.add(planScheduleCarConfigVO);
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
			UTRow utRow = planScheduleAPI.getRowByKey(key, "CarTypeAttruConfig");
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
	String toList(PlanScheduleCarConfigVO planscheduleCarConfigVO)
	{
		JSONArray rt = null;
		try
		{
			String factory = planscheduleCarConfigVO.getFactory();
			String workshop = planscheduleCarConfigVO.getWorkshop();
			String type = planscheduleCarConfigVO.getVehicle_type();
			Vector<String[]> configVec = planScheduleAPI.getConfigures(factory, workshop, type);
			
			

			Vector<Object> vos = new Vector<Object>();
			for (String[] config : configVec)
			{
				PlanScheduleCarConfigVO planScheduleCarConfigVO = new PlanScheduleCarConfigVO();
				planScheduleCarConfigVO.setKey(Integer.parseInt(config[0]));
				planScheduleCarConfigVO.setVehicle_type(config[1]);
				planScheduleCarConfigVO.setFactory(config[2]);
				planScheduleCarConfigVO.setWorkshop(config[3]);
				planScheduleCarConfigVO.setAttru_name(config[4]);
				planScheduleCarConfigVO.setSubattru_name(config[5]);
				
				vos.add(planScheduleCarConfigVO);
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
	String save(PlanScheduleCarConfigVO planscheduleCarConfigVO)
	{
		try
		{
			long key = planscheduleCarConfigVO.getKey();
			UTRow utRow = planScheduleAPI.getRowByKey(key, "CarTypeAttruConfig");
			if (utRow == null)
			{
				utRow = planScheduleAPI.createUTRow("CarTypeAttruConfig");

			}

			utRow.setValue("factory", planscheduleCarConfigVO.getFactory());
			utRow.setValue("workshop", planscheduleCarConfigVO.getWorkshop());
			utRow.setValue("vehicle_type", planscheduleCarConfigVO.getVehicle_type());
			utRow.setValue("attru_name", planscheduleCarConfigVO.getAttru_name());
			utRow.setValue("subattru_name", planscheduleCarConfigVO.getSubattru_name());
			Response response = utRow.save(planScheduleAPI.getDBTime(), " ", null);
			if (response.isError())
			{
				throw new Exception("保存车型排程属性配置失败，原因为:" + response.getFirstErrorMessage());
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
