
package com.mes.webclient.controller;

import java.util.Vector;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.BomItem;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Queue;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.Route;
import com.mes.compatibility.client.Step;
import com.mes.compatibility.client.Unit;
import com.mes.compatibility.client.UnitHistory;
import com.mes.compatibility.client.WorkCenter;
import com.mes.shopflow.common.constants.ITrackedObjectConstants;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.controller.bean.UIUnit;
import com.mes.webclient.controller.vo.ConsumptionVO;
import com.mes.webclient.controller.vo.StationControlVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.DateTimeUtils;

@Controller("stationControlController")
@RequestMapping("/stationControl.sp")
public class StationControlController extends BaseController
{
	@Autowired
	IIMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{
		return "im/stationcontrol/list";
	}

	/**
	 * 查询
	 */
	@RequestMapping(params = "toList")
	public String toList(@RequestParam("unitSerialNumber") String unitSerialNumber, Model model)
	{

		try
		{
			Unit unit = imService.getUnitBySerialNumber(unitSerialNumber);
			if (unit == null)
			{
				return "im/stationcontrol/list";
			}
			UIUnit uiUnit = new UIUnit(unit);
			model.addAttribute(
				"unit", uiUnit);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}

		return "im/stationcontrol/list";
	}

	/**
	 * 查询
	 */
	@RequestMapping(params = "toListUnitHistories")
	public @ResponseBody
	String toListUnitHistories(StationControlVO stationControlVO)
	{
		JSONArray rt = null;
		try
		{
			Unit unit = imService.getUnitBySerialNumber(stationControlVO.getUnitSerialNumber());
			if(unit==null)
			{
				return "";
			}
			Vector<UnitHistory> unitHistories = unit.getUnitHistory();
			Vector<Object> vos = new Vector<Object>();
			if (unitHistories.size() > stationControlVO.getQueryNum())
			{
				for (int i = 0; i < stationControlVO.getQueryNum(); i++)
				{
					StationControlVO newStationControlVO = new StationControlVO();
					UnitHistory unitHistory = unitHistories.get(i);
					newStationControlVO.setKey(unitHistory.getKey());
					newStationControlVO.setRouteName(unitHistory.getRouteName());
					newStationControlVO.setRouteStepName(unitHistory.getRouteStepName());
					newStationControlVO.setProductionLineName(unitHistory.getProductionLineName());
					newStationControlVO.setWorkCenterName(unitHistory.getWorkCenterName()); 
					newStationControlVO.setStartedTime(DateTimeUtils.formatDate(
						unitHistory.getStartedTime(), IDateFormat.TIME_LONG));
					newStationControlVO.setStartedUserName(unitHistory.getStartedUserName());
					newStationControlVO.setCompletedTime(DateTimeUtils.formatDate(
						unitHistory.getCompletedTime(), IDateFormat.TIME_LONG));
					newStationControlVO.setCompletedUserName(unitHistory.getCompletedUserName());
					vos.add(newStationControlVO);

				}
			}
			else
			{
				for (UnitHistory unitHistory : unitHistories)
				{
					StationControlVO newStationControlVO = new StationControlVO();
					newStationControlVO.setKey(unitHistory.getKey());
					newStationControlVO.setRouteName(unitHistory.getRouteName());
					newStationControlVO.setRouteStepName(unitHistory.getRouteStepName());
					newStationControlVO.setProductionLineName(unitHistory.getProductionLineName());
					newStationControlVO.setWorkCenterName(unitHistory.getWorkCenterName());
					newStationControlVO.setStartedTime(DateTimeUtils.formatDate(
						unitHistory.getStartedTime(), IDateFormat.TIME_LONG));
					newStationControlVO.setStartedUserName(unitHistory.getStartedUserName());
					newStationControlVO.setCompletedTime(DateTimeUtils.formatDate(
						unitHistory.getCompletedTime(), IDateFormat.TIME_LONG));
					newStationControlVO.setCompletedUserName(unitHistory.getCompletedUserName());
					vos.add(newStationControlVO);

				}
			}
			rt = JSONArray.fromObject(vos);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e);
		}

		return rt.toString();
	}

	/**
	 * 变更工序
	 */
	@RequestMapping(params = "toChangeRouteStep")
	public String tooChangeRouteStep(@RequestParam("unitSerialNumber") String unitSerialNumber,
		Model model)
	{
		try
		{
			Unit unit = imService.getUnitBySerialNumber(unitSerialNumber);
			String status = unit.getCurrentStatus();
			if (status.equals(ITrackedObjectConstants.STATUS_STR_CREATED)
				|| status.equals(ITrackedObjectConstants.STATUS_STR_COMPLETED))
			{
				Route route = unit.getRoute();
				String routeName = route.getName();
				Vector<Queue> queues = route.getQueues();
				model.addAttribute(
					"unitSerialNumber", unitSerialNumber);
				model.addAttribute(
					"routeName", routeName);
				model.addAttribute(
					QUERY_LIST, queues);
				return "im/stationcontrol/changeRouteStep";
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存变更工序
	 */

	@RequestMapping(params = "saveChangeRouteStep")
	public @ResponseBody
	String saveChangeRouteStep(StationControlVO stationControlVO)
	{

		try
		{
			String unitSerialNumber = stationControlVO.getUnitSerialNumber();
			String queueName = stationControlVO.getQueueName();
			Unit unit = imService.getUnitBySerialNumber(unitSerialNumber);
			Route route = unit.getRoute();
			Queue queue = route.getQueue(queueName);
			Response response = unit.addToQueue(queue);
			if (response.isError())
			{
				throw new Exception("变更失败，原因为:" + response.getFirstErrorMessage());
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
	 * 开始工序
	 */
	@RequestMapping(params = "toStartRouteStep")
	public String toStartRouteStep(@RequestParam("unitSerialNumber") String unitSerialNumber,
		Model model)
	{
		try
		{
			Unit unit = imService.getUnitBySerialNumber(unitSerialNumber);
			String status = unit.getCurrentStatus();
			if (status.equals(ITrackedObjectConstants.STATUS_STR_CREATED)
				|| status.equals(ITrackedObjectConstants.STATUS_STR_COMPLETED))
			{
				Queue queue = unit.getQueue();

				Vector<Step> steps = queue.getSteps();
				Vector<WorkCenter> workCenters = steps.get(
					0).getWorkCenters();
				model.addAttribute(
					"unitSerialNumber", unitSerialNumber);
				model.addAttribute(
					QUERY_LIST, steps);
				model.addAttribute(
					"workCenters", workCenters);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "im/stationcontrol/startRouteStep";
	}

	@RequestMapping(params = "toListWorkCenters")
	public @ResponseBody
	String toListWorkCenters(@RequestParam("unitSerialNumber") String unitSerialNumber,
		@RequestParam("stepName") String stepName)
	{

		Unit unit = null;
		try
		{
			unit = imService.getUnitBySerialNumber(unitSerialNumber);
		}
		catch (MESException e)
		{
			e.printStackTrace();
		}
		Route route = unit.getRoute();
		Step step = route.getStep(stepName);
		Vector<WorkCenter> workCenters = step.getWorkCenters();
		String jsonArray = JSONArray.fromObject(
			workCenters).toString();
		return jsonArray;

	}

	/**
	 * 保存开始工序
	 */

	@RequestMapping(params = "saveStartRouteStep")
	public @ResponseBody
	String saveStartRouteStep(StationControlVO stationControlVO)
	{

		try
		{
			String unitSerialNumber = stationControlVO.getUnitSerialNumber();
			String workCenterName = stationControlVO.getWorkCenterName();
			String stepName = stationControlVO.getStepName();
			if (stepName == null)
			{
				throw new Exception("没有下一步工序");
			}
			Unit unit = imService.getUnitBySerialNumber(unitSerialNumber);
			Response response = unit.startAtStepAtWorkCenter(
				stepName, workCenterName);

			if (response.isError())
			{
				throw new Exception("开始工序失败，原因为:" + response.getFirstErrorMessage());
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
	 * 结束工序
	 */
	@RequestMapping(params = "toCompleteRouteStep")
	public String toCompleteRouteStep(@RequestParam("unitSerialNumber") String unitSerialNumber,
		Model model)
	{
		try
		{
			Unit unit = imService.getUnitBySerialNumber(unitSerialNumber);
			String status = unit.getCurrentStatus();
			if (status.equals(ITrackedObjectConstants.STATUS_STR_STARTED))
			{
				UIUnit uiUnit = new UIUnit(unit);
				model.addAttribute(
					"unit", uiUnit);
				model.addAttribute(
					"unitSerialNumber", unitSerialNumber);
				return "im/stationcontrol/completeRouteStep";
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存结束工序
	 */

	@RequestMapping(params = "saveCompleteRouteStep")
	public @ResponseBody
	String saveCompleteRouteStep(StationControlVO stationControlVO)
	{

		try
		{
			String unitSerialNumber = stationControlVO.getUnitSerialNumber();
			Unit unit = imService.getUnitBySerialNumber(unitSerialNumber);

			Step step = unit.getRouteStep();
			String stepName = step.getName();
			String workCenterName = unit.getWorkCenterName();
			Response response = unit.completeAtStepAtWorkCenter(
				stepName, workCenterName, null);

			if (response.isError())
			{
				throw new Exception("结束工序失败，原因为:" + response.getFirstErrorMessage());
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
