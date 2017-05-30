
package com.mes.webclient.controller;

import java.util.Vector;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.DeAnzaForm;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.Station;
import com.mes.compatibility.client.StationFilter;
import com.mes.compatibility.client.WorkCenter;
import com.mes.compatibility.manager.FormManager;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.shopflow.common.constants.filtering.IStationFilterAttributes;
import com.mes.webclient.controller.vo.StationVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("stationcontroller")
@RequestMapping("/station.sp")
public class StationController extends BaseController
{
	@Autowired
	IIMService imService;

	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage()
	{
		return "im/station/list";
	}
	
	/**
	 * 查询
	 */
	@ResponseBody
	@RequestMapping(params = "toList")
	public String toMainPage(StationVO stationVO)
	{
		JSONArray rt = null;	
		try
		{
			String stationNumber = stationVO.getStationNumber();
			String stationName = stationVO.getStationName();
			StationFilter stationFilter = imService.createStationFilter();
			if (StringUtil.isNotNull(stationNumber))
			{
				stationFilter.addSearchBy(
					IStationFilterAttributes.NAME, 
					IFilterComparisonOperators.CONTAINING, stationNumber);
			}
			if (StringUtil.isNotNull(stationName))
			{
				stationFilter.addSearchBy(
					IStationFilterAttributes.DESCRIPTION,
					IFilterComparisonOperators.CONTAINING, stationName);
			}
			stationFilter.setMaxRows(stationVO.getQueryNum());
			Vector<Station> stations = stationFilter.exec();
			Vector<Object> vos = new Vector<Object>();
			for(Station station : stations)
			{
				StationVO vo = new StationVO();
				vo.setKey(station.getKey());
				vo.setStationNumber(station.getName());;
				vo.setStationName(station.getDescription());
				vo.setStationCategory(station.getCategory());
				WorkCenter wc = station.getWorkCenter();
				if(wc != null)
				{
					vo.setWorkCenterNumber(wc.getName());
				}
				DeAnzaForm form = station.getForm();
				if(form != null)
				{
					vo.setFormName(form.getName());
				}
				
				vos.add(vo);
			}
			rt = JSONArray.fromObject(vos);
			return rt.toString();
		}
		catch (MESException e)
		{
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(StationVO stationVO, Model model)
	{
		long key = stationVO.getKey();
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		if (key > 0)
		{
			try
			{
				Station station = imService.getStationByKey(key);
				stationVO.setKey(key);
				stationVO.setStationNumber(station.getName());
				stationVO.setStationName(station.getDescription());
				stationVO.setStationCategory(station.getCategory());
				WorkCenter wc = station.getWorkCenter();
				if(wc != null)
				{
					stationVO.setWorkCenterNumber(wc.getName());
				}
				DeAnzaForm form = station.getForm();
				if(form != null)
				{
					stationVO.setFormName(form.getName());
				}
			
				stationVO.setUda0(station.getUDA(0));
				stationVO.setUda1(station.getUDA(1));
				stationVO.setUda2(station.getUDA(2));
				stationVO.setUda3(station.getUDA(3));
				stationVO.setUda4(station.getUDA(4));
			
			}
			catch (MESException e)
			{

				e.printStackTrace();
			}
		}
		model.addAttribute(VIEW_OBJECT, stationVO);
		return "im/station/addOrEdit";
	}
	
	 /**
		 * 保存
		 */
		@RequestMapping(params = "save")
		public @ResponseBody
		String save(StationVO stationVO)
		{
			try
			{
				long key = stationVO.getKey();
				Station station = null;
				if (key < 0)
				{
					station = imService.createStation();
				}
				else
				{
					station = imService.getStationByKey(key);
				}
				WorkCenter workCenter = imService.getWorkCenterByName(stationVO.getWorkCenterNumber());
				FormManager formManager = imService.getFormManager();
				DeAnzaForm form = (DeAnzaForm) formManager.getObject(stationVO.getFormName());
				station.setName(stationVO.getStationNumber());
				station.setDescription(stationVO.getStationName());
				station.setCategory(stationVO.getStationCategory());
				station.setWorkCenter(workCenter);
				station.setForm(form);
				
				station.setUDA(stationVO.getUda0(), 0);
				station.setUDA(stationVO.getUda1(), 1);
				station.setUDA(stationVO.getUda2(), 2);
				station.setUDA(stationVO.getUda3(), 3);
				station.setUDA(stationVO.getUda4(), 4);
				
				Response response = station.save(
					null, null, null);
				if (response.isError())
				{
					throw new Exception("保存工作站失败，原因为:" + response.getFirstErrorMessage());
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
		public @ResponseBody String del(@RequestParam("key") long key)
		{
				try
				{
					if (key > 0)
					{
						Station station = imService.getStationByKey(key);
						Response response = station.delete(
							null, null);
						if (response.isError())
						{
							throw new Exception(response.getFirstErrorMessage());
						}
						return ajaxDoneSuccess();
					}
					else
					{
						throw new Exception("无法找到该对象的key");
					}
				}
				catch (Exception e)
				{
					logger.error(e);
					return ajaxDoneError(e.getLocalizedMessage());
				}
		}
}
