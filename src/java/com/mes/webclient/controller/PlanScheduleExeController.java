
package com.mes.webclient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
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
import com.mes.compatibility.ui.Time;
import com.mes.shopflow.common.constants.IATDefinitionDataManagementTypes;
import com.mes.shopflow.common.constants.IDataTypes;
import com.mes.shopflow.common.constants.IDatabaseGrowthTypes;
import com.mes.transactiongrouping.UserTransaction;
import com.mes.webclient.app.demo.api.PlanScheduleAPI;
import com.mes.webclient.controller.vo.PlanScheduleCarConfigVO;
import com.mes.webclient.controller.vo.PlanScheduleConVO;
import com.mes.webclient.controller.vo.PlanSchedulePropsVO;
import com.mes.webclient.controller.vo.PlanScheduleVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("PlanScheduleExeController")
@RequestMapping("/planscheduleexe.sp")
public class PlanScheduleExeController extends BaseController
{
	@Autowired
	IIMService imService;
	PlanScheduleAPI planScheduleAPI = new PlanScheduleAPI();

	Vector<PlanScheduleVO> dataVec ;
	
	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{

		return "/im/orderScheduleExe/list";
	}

	/**
	 * 添加或编辑
	 */
//	@RequestMapping(params = "toAddOrEdit")
//	public String toAddOrEdit(PlanScheduleVO planscheduleVO, Model model)
//	{
//		long key = planscheduleCarConfigVO.getKey();
//		List<String> factoryl = new ArrayList<String>();
//		List<String> typel = new ArrayList<String>();
//		Vector<String[]> facVec;
//		Vector<String[]> typeVec;
//		Vector<String[]> wsVec;
//		Vector<String[]> attruVec;
//		Vector<String[]> subattruVec;
//		try
//		{
//			facVec = planScheduleAPI.getFactories();
//			for(int i=0; facVec!=null && i<facVec.size(); i++)
//			{
//				String factory = facVec.get(i)[0];
//				factoryl.add(factory);
//			}
//			
//			typeVec = planScheduleAPI.getVehicleTypes();
//			for(int i=0; typeVec!=null && i<typeVec.size(); i++)
//			{
//				String type = typeVec.get(i)[0];
//				typel.add(type);
//			}
//		}
//		catch (MESException e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		model.addAttribute(
//			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
//		if (key > 0)
//		{
//			List<String> workshopl = new ArrayList<String>();
//			List<String> attrul = new ArrayList<String>();
//			List<String> subattrul = new ArrayList<String>();
//			try
//			{
//				UTRow utRow = planScheduleAPI.getRowByKey(key, "CarTypeAttruConfig");
//				if(utRow!=null)
//				{
//					String factory = utRow.getValue("factory").toString();
//					String workshop = utRow.getValue("workshop").toString();
//					String vehicle_type = utRow.getValue("vehicle_type").toString();
//					String attru_name = utRow.getValue("attru_name").toString();
//					String subattru_name = utRow.getValue("subattru_name").toString();
//					planscheduleCarConfigVO.setVehicle_type(vehicle_type);
//					planscheduleCarConfigVO.setFactory(factory);
//					planscheduleCarConfigVO.setWorkshop(workshop);
//					planscheduleCarConfigVO.setAttru_name(attru_name);
//					planscheduleCarConfigVO.setSubattru_name(subattru_name);
//					
//					wsVec = planScheduleAPI.getWorkshopByFactory(factory);
//					for(int j=0; wsVec!=null && j < wsVec.size(); j++)
//					{
//						String ws = wsVec.get(j)[0];
//						workshopl.add(ws);
//						if (ws != null && ws.equals(workshop))
//						{
//							attruVec = planScheduleAPI.getAttru(
//								factory, ws);
//							for (int m = 0; attruVec != null && m < attruVec.size(); m++)
//							{
//								
//								String attru = attruVec.get(m)[0];
//								attrul.add(attru);
//								if (attru != null && attru.equals(attru_name))
//								{
//									subattruVec = planScheduleAPI.getSubAttru(
//										factory, ws, attru);
//									for (int k = 0; subattruVec != null && k < subattruVec.size(); k++)
//									{
//										String subattru = subattruVec.get(k)[0];
//										subattrul.add(subattru);
//									}
//									model.addAttribute(
//										"SUBATTRU_LIST", subattrul);
//								}
//							}
//
//							model.addAttribute(
//								"ATTRU_LIST", attrul);
//						}
//					}
//					
//					model.addAttribute(
//						"WORKSHOP_LIST", workshopl);
//				}
//
//			}
//			catch (MESException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		model.addAttribute(
//			VIEW_OBJECT, planscheduleCarConfigVO);
//		model.addAttribute(
//			"FACTORY_LIST", factoryl);
//		model.addAttribute(
//			"TYPE_LIST", typel);
//		return "im/orderScheduleConfig/addOrEdit";
//	}
	
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
	String toList(PlanScheduleVO planscheduleVO, Model model)
	{
		JSONArray rt = null;
		try
		{
//			String name = planschedulePropsVO.getName();
			dataVec = new Vector<PlanScheduleVO>();
			Vector<String[]> orderVec = planScheduleAPI.getOrderData(planscheduleVO.getCompletetime());
			
			

			Vector<Object> vos = new Vector<Object>();
			int i=1;
			for (String[] order : orderVec)
			{
				PlanScheduleVO planScheduleVO = new PlanScheduleVO();
				planScheduleVO.setKey(Integer.parseInt(order[0]));
				planScheduleVO.setFactory(order[1]);
				planScheduleVO.setVin(order[2]);
				planScheduleVO.setVehicletype(order[3]);
				planScheduleVO.setColor(order[4]);
				planScheduleVO.setIsSpecial(order[5]);
				planScheduleVO.setCsn(order[6]);
				planScheduleVO.setCompletetime(order[7]);
				planScheduleVO.setAbOnline(order[8]);
				planScheduleVO.setAbOffline(order[9]);
				planScheduleVO.setPbOnline(order[10]);
				planScheduleVO.setPbOffline(order[11]);
				planScheduleVO.setWbOnline(order[12]);
				planScheduleVO.setWbOffline(order[13]);
//				planScheduleVO.setSeq(Integer.parseInt(order[14]));
				planScheduleVO.setSeq(i);
				
				vos.add(planScheduleVO);
				i++;
				dataVec.add(planScheduleVO);
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
	 * 排程
	 */
	@RequestMapping(params = "toExecute")
	public @ResponseBody
	String toExecute(PlanScheduleVO planscheduleVO, Model model)
	{
		JSONArray rt = null;
		Vector<String> typeVec = new Vector<String>();
		String factory = "";
//		HashMap<String, Vector<PlanScheduleVO>> typeVOMap = new HashMap<String, Vector<PlanScheduleVO>>();
//		ArrayList typeList = new ArrayList();
//		HashMap<String, String[]> typeAttruMap = new HashMap<String, String[]>();
//
//		Vector<PlanScheduleVO> tempList = new Vector<PlanScheduleVO>();
//		HashMap<String, Integer> typeSizeMap = new HashMap<String, Integer>();
//		TreeMap<Float, PlanScheduleVO> pointMap = new TreeMap<Float, PlanScheduleVO>();
//		TreeMap typeMap = null;
//		TreeMap<Integer, String> attruMap = new TreeMap<Integer, String>();

		Vector<Object> vos = new Vector<Object>();

		try
		{
			if (dataVec != null && dataVec.size() > 0)
			{
				for (int i = 0; i < dataVec.size(); i++)
				{
					PlanScheduleVO planScheduleVO = dataVec.get(i);
					String type = planScheduleVO.getVehicletype();
					factory = planScheduleVO.getFactory();
					if (!typeVec.contains(type))
					{
						typeVec.add(type);
					}

					// typeVOMap.put(key, value)

				}
			}
//			for (int i = 0; typeVec != null && i < typeVec.size(); i++)
//			{
//				String type = typeVec.get(i);
//				Vector<PlanScheduleVO> typel = new Vector<PlanScheduleVO>();
//				for (int j = 0; j < dataVec.size(); j++)
//				{
//					PlanScheduleVO planScheduleVO = dataVec.get(j);
//					String vehicleType = planScheduleVO.getVehicletype();
//					if (vehicleType != null && vehicleType.equals(type))
//					{
//						typel.add(planScheduleVO);
//					}
//
//				}
//				typeVOMap.put(
//					type, typel);
//				typeSizeMap.put(
//					type, typel.size());
//			}
//			Vector<String[]> abAttruConfig = planScheduleAPI.getAttruConfigures(
//				factory, "AB");
//			for (int i = 0; abAttruConfig != null && i < abAttruConfig.size(); i++)
//			{
//				int attruPrio = Integer.parseInt(abAttruConfig.get(i)[1]);
//				String attruName = abAttruConfig.get(i)[0];
//				attruMap.put(
//					attruPrio, attruName);
//
//			}
//			if (attruMap != null)
//			{
//				Set setAttru = attruMap.keySet();
//				if (setAttru != null)
//				{
//					Iterator itAttru = setAttru.iterator();
//					while (itAttru.hasNext())
//					{
//						int attruprio = (int) itAttru.next();
//						String attruName = (String) attruMap.get(attruprio);
//						typeMap = new TreeMap();
//						for (int j = 0; typeVec != null && j < typeVec.size(); j++)
//						{
//
//							String type = typeVec.get(j);
//							String subAttru = planScheduleAPI.getSubAttruValue(
//								factory, "AB", type, attruName);
//							Vector<String[]> subVec = planScheduleAPI.getSubAttruProp(
//								factory, "AB", attruName, subAttru);
//							if (subVec != null && subVec.size() > 0)
//							{
//								String subAttruPriority = subVec.get(0)[1];
//								String subAttruBatch = subVec.get(0)[2];
//								if (subAttru != null && subAttruPriority != null
//									&& subAttruBatch != null)
//								{
//									String[] attruArray = new String[3];
//									attruArray[0] = subAttru;
//									attruArray[1] = subAttruPriority;
//									attruArray[2] = subAttruBatch;
//									typeAttruMap.put(
//										type, attruArray);
//									typeMap.put(
//										Integer.parseInt(subAttruPriority), type);
//								}
//							}
//
//						}
//
//						if (typeMap != null)
//						{
//							Set setType = typeMap.keySet();
//							if (setType != null && setType.size() > 0)
//							{
//								Iterator itType = setType.iterator();
//								while (itType.hasNext())
//								{
//									int keyPrio = (int) itType.next();
//									String type = (String) typeMap.get(keyPrio);
//									Vector<PlanScheduleVO> vecVO = typeVOMap.get(type);
//									String[] attruArray = (String[]) typeAttruMap.get(type);
//									for (int i = 0; i < vecVO.size(); i++)
//									{
//										PlanScheduleVO vo = (PlanScheduleVO) vecVO.get(i);
//										int batch = Integer.parseInt(attruArray[2]);
//										int total = typeSizeMap.get(type);
//										int no = total / batch + 1;
//										// if((i+1)%no == 0)
//										// {
//										// vo.setBatchNo(i/no);
//										// System.out.println("i: "+ i
//										// +" (i+1)%no: " + (i+1)%no +
//										// "  i/no:  "+i/no);
//										// }
//										// else
//										// {
//										vo.setBatchNo(i / batch + 1);
////										 System.out.println("i: "+ i +
////											 " no: " + no + "  batch: "+  batch +
////											 "  i / batch + 1:  "+(i / batch + 1));
////											}
//										tempList.add(vo);
//									}
//									// System.out.println("Key: " + key +
//									// "  Value:" + map.get(key));
//								}
//
//								for (int i = 0; tempList != null && i < tempList.size(); i++)
//								{
//									PlanScheduleVO planScheduleVO = tempList.get(i);
//									String type = planScheduleVO.getVehicletype();
//									String[] attruArray = (String[]) typeAttruMap.get(type);
//									int batch = Integer.parseInt(attruArray[2]);
//									// int total = typeSizeMap.get(type);
//									// int no = total/batch + 1;
//									int seq = i + 1;
//									int currntBatchNo = (int) planScheduleVO.getBatchNo();
//									float point = ((999999 / (batch + 1)) * currntBatchNo) + seq;
////									System.out.println("type: " + type + " pointKey: " + point
////										+ " NUM:  " + seq + " currntBatchNo:  " + currntBatchNo);
//									pointMap.put(
//										point, planScheduleVO);
//								}
//
//								if (pointMap != null)
//								{
//									Set set = pointMap.keySet();
//									if (set != null)
//									{
//										Iterator it = set.iterator();
//										int num = 1;
//										tempList = new Vector<PlanScheduleVO>();
//										while (it.hasNext())
//										{
//											float key = (float) it.next();
//											PlanScheduleVO vo = (PlanScheduleVO) pointMap.get(key);
//											vo.setSeq(num);
//											String completeTime = vo.getCompletetime();
//											Time starttime = new Time(completeTime);
//											starttime = starttime.addHours(8);
//											starttime = starttime.addSeconds(50*num);
//											vo.setAbOffline(starttime.toString());
////											tempList = new Vector<PlanScheduleVO>();
//											tempList.add(vo);
////											vos.add(vo);
//											num++;
//
//											System.out.println("AB  NO: " + num + "  CSN:" + vo.getCsn());
//										}
//									}
//
//								}
//							}
//
//						}
//
//					}
//				}
//			}
//			typeVOMap.clear();
//			typeSizeMap.clear();
//			pointMap.clear();
//			if(tempList!=null && tempList.size()>0)
//			{
//				for (int i = 0; typeVec != null && i < typeVec.size(); i++)
//				{
//					String type = typeVec.get(i);
//					Vector<PlanScheduleVO> typel = new Vector<PlanScheduleVO>();
//					for (int j = 0; j < tempList.size(); j++)
//					{
//						PlanScheduleVO planScheduleVO = tempList.get(j);
//						String vehicleType = planScheduleVO.getVehicletype();
//						if (vehicleType != null && vehicleType.equals(type))
//						{
//							typel.add(planScheduleVO);
//						}
//
//					}
//					typeVOMap.put(
//						type, typel);
//					typeSizeMap.put(
//						type, typel.size());
//				}
//			}
//			
//			attruMap.clear();
//			Vector<String[]> pbAttruConfig = planScheduleAPI.getAttruConfigures(
//				factory, "PB");
//			for (int i = 0; pbAttruConfig != null && i < pbAttruConfig.size(); i++)
//			{
//				int attruPrio = Integer.parseInt(abAttruConfig.get(i)[1]);
//				String attruName = pbAttruConfig.get(i)[0];
//				attruMap.put(
//					attruPrio, attruName);
//
//			}
//			if (attruMap != null)
//			{
//				Set setAttru = attruMap.keySet();
//				if (setAttru != null && setAttru.size()>0)
//				{
//					Iterator itAttru = setAttru.iterator();
//					while (itAttru.hasNext())
//					{
//						int attruprio = (int) itAttru.next();
//						String attruName = (String) attruMap.get(attruprio);
//						typeMap = new TreeMap();
//						for (int j = 0; typeVec != null && j < typeVec.size(); j++)
//						{
//
//							String type = typeVec.get(j);
//							String subAttru = planScheduleAPI.getSubAttruValue(
//								factory, "PB", type, attruName);
//							Vector<String[]> subVec = planScheduleAPI.getSubAttruProp(
//								factory, "PB", attruName, subAttru);
//							if (subVec != null && subVec.size() > 0)
//							{
//								String subAttruPriority = subVec.get(0)[1];
//								String subAttruBatch = subVec.get(0)[2];
//								if (subAttru != null && subAttruPriority != null
//									&& subAttruBatch != null)
//								{
//									String[] attruArray = new String[3];
//									attruArray[0] = subAttru;
//									attruArray[1] = subAttruPriority;
//									attruArray[2] = subAttruBatch;
//									typeAttruMap.put(
//										type, attruArray);
//									typeMap.put(
//										Integer.parseInt(subAttruPriority), type);
//								}
//							}
//
//						}
//
//						if (typeMap != null)
//						{
//							Set setType = typeMap.keySet();
//							if (setType != null && setType.size() > 0)
//							{
//								Iterator itType = setType.iterator();
//								tempList = new Vector<PlanScheduleVO>();
//								while (itType.hasNext())
//								{
//									int keyPrio = (int) itType.next();
//									String type = (String) typeMap.get(keyPrio);
//									Vector<PlanScheduleVO> vecVO = typeVOMap.get(type);
//									String[] attruArray = (String[]) typeAttruMap.get(type);
//									for (int i = 0; i < vecVO.size(); i++)
//									{
//										PlanScheduleVO vo = (PlanScheduleVO) vecVO.get(i);
//										int batch = Integer.parseInt(attruArray[2]);
//										int total = typeSizeMap.get(type);
//										int no = total / batch + 1;
//										// if((i+1)%no == 0)
//										// {
//										// vo.setBatchNo(i/no);
//										// System.out.println("i: "+ i
//										// +" (i+1)%no: " + (i+1)%no +
//										// "  i/no:  "+i/no);
//										// }
//										// else
//										// {
//										vo.setBatchNo(i / batch + 1);
////										 System.out.println("i: "+ i +
////										 " no: " + no + "  batch: "+  batch +
////										 "  i / batch + 1:  "+(i / batch + 1));
////										 }
//										tempList.add(vo);
//									}
//									// System.out.println("Key: " + key +
//									// "  Value:" + map.get(key));
//								}
//
//								for (int i = 0; tempList != null && i < tempList.size(); i++)
//								{
//									PlanScheduleVO planScheduleVO = tempList.get(i);
//									String type = planScheduleVO.getVehicletype();
//									String[] attruArray = (String[]) typeAttruMap.get(type);
//									int batch = Integer.parseInt(attruArray[2]);
//									// int total = typeSizeMap.get(type);
//									// int no = total/batch + 1;
//									int seq = i + 1;
//									int currntBatchNo = (int) planScheduleVO.getBatchNo();
//									float point = ((999999 / (batch + 1)) * currntBatchNo) + seq;
////									System.out.println("type: " + type + " pointKey: " + point
////										+ " NUM:  " + seq + " batch: "+batch+" currntBatchNo:  " + currntBatchNo);
//									pointMap.put(
//										point, planScheduleVO);
//								}
//
//								if (pointMap != null)
//								{
//									Set set = pointMap.keySet();
//									if (set != null)
//									{
//										Iterator it = set.iterator();
//										int num = 1;
//										tempList = new Vector<PlanScheduleVO>();
//										while (it.hasNext())
//										{
//											float key = (float) it.next();
//											PlanScheduleVO vo = (PlanScheduleVO) pointMap.get(key);
//											vo.setSeq(num);
//											String completeTime = vo.getCompletetime();
//											Time starttime = new Time(completeTime);
//											starttime = starttime.addHours(8);
//											starttime = starttime.addSeconds(70*num);
//											vo.setPbOffline(starttime.toString());
////											tempList = new Vector<PlanScheduleVO>();
//											tempList.add(vo);
////											vos.add(vo);
//											num++;
//											System.out.println("PB  NO: " + num + "  CSN:" + vo.getCsn());
//											// System.out.println("Key: " + key
//											// + "  Value:" + map.get(key));
//										}
//									}
//
//								}
//							}
//
//						}
//
//					}
//				}
//			}
			
			Vector<PlanScheduleVO> tempList = planScheduleAPI.exec(dataVec, typeVec, factory, "AB");
			
			tempList = planScheduleAPI.exec(tempList, typeVec, factory, "PB");
//			Vector<PlanScheduleVO> tempList = planScheduleAPI.exec(dataVec, typeVec, factory, "PB");
			tempList = planScheduleAPI.exec(tempList, typeVec, factory, "WB");
			
			for(int i=0; tempList!=null&&i<tempList.size(); i++)
			{
				PlanScheduleVO vo = tempList.get(i);
				vos.add(vo);
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
