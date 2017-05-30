/*
 * @(#) UtillityController.java 2016-7-3 下午10:51:15
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.controller;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes.compatibility.client.Area;
import com.mes.compatibility.client.AreaFilter;
import com.mes.compatibility.client.BillOfMaterials;
import com.mes.compatibility.client.BomFilter;
import com.mes.compatibility.client.Equipment;
import com.mes.compatibility.client.EquipmentFilter;
import com.mes.compatibility.client.Lot;
import com.mes.compatibility.client.LotFilter;
import com.mes.compatibility.client.Operation;
import com.mes.compatibility.client.OperationFilter;
import com.mes.compatibility.client.Order;
import com.mes.compatibility.client.OrderFilter;
import com.mes.compatibility.client.OrderItem;
import com.mes.compatibility.client.Part;
import com.mes.compatibility.client.PartFilter;
import com.mes.compatibility.client.ProductionLine;
import com.mes.compatibility.client.ProductionLineFilter;
import com.mes.compatibility.client.Route;
import com.mes.compatibility.client.RouteFilter;
import com.mes.compatibility.client.Step;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.User;
import com.mes.compatibility.client.UserFilter;
import com.mes.compatibility.client.UserGroup;
import com.mes.compatibility.client.UserGroupFilter;
import com.mes.compatibility.client.WorkCenter;
import com.mes.compatibility.client.WorkCenterFilter;
import com.mes.shopflow.common.constants.filtering.IAreaFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IEquipmentFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IFilterComparisonOperators;
import com.mes.shopflow.common.constants.filtering.IPartFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IProductionLineFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IRouteFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IRouteOperationFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IUserFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IUserGroupFilterAttributes;
import com.mes.shopflow.common.constants.filtering.IWorkCenterFilterAttributes;
import com.mes.webclient.app.demo.api.MaterialTrackAPI;
import com.mes.webclient.controller.vo.AreaVO;
import com.mes.webclient.controller.vo.BOMVO;
import com.mes.webclient.controller.vo.BaiscPartVO;
import com.mes.webclient.controller.vo.EquipmentVO;
import com.mes.webclient.controller.vo.FactoryBrandVO;
import com.mes.webclient.controller.vo.LotVO;
import com.mes.webclient.controller.vo.OperationQualityPartVO;
import com.mes.webclient.controller.vo.OperationVO;
import com.mes.webclient.controller.vo.OrderItemVO;
import com.mes.webclient.controller.vo.OrderVO;
import com.mes.webclient.controller.vo.PartVO;
import com.mes.webclient.controller.vo.ProductionLineVO;
import com.mes.webclient.controller.vo.RouteVO;
import com.mes.webclient.controller.vo.StepVO;
import com.mes.webclient.controller.vo.UserGroupVO;
import com.mes.webclient.controller.vo.UserVO;
import com.mes.webclient.controller.vo.WorkCenterVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.service.impl.IMService;
import com.mes.webclient.util.StringUtil;

@Controller("utilityController")
@RequestMapping("/utility.sp")
public class UtilityController extends BaseController
{
	@Autowired
	IIMService imService;
	@Autowired
	IMService implService ;
	MaterialTrackAPI materialTrackAPI = new MaterialTrackAPI();
	/**
	 * 进入子设备页面
	 */
	@RequestMapping(params = "toSingleChildEquipment")
	public String toChildEquipmentMainPage()
	{
		return "util/im/singleChildEquipment";
	}

	/**
	 * 查询子设备
	 */
	@RequestMapping(params = "singleChildEquipmentList")
	public String queryChildEquipment(EquipmentVO equipmentVO, Model model)
	{
		try
		{
			String equipmentNumber = equipmentVO.getEquipmentNumber();
			String equipmentName = equipmentVO.getEquipmentName();

			EquipmentFilter equipmentFilter = imService.createEquipmentFilter();
			if (StringUtil.isNotNull(equipmentNumber))
			{
				equipmentFilter.forNameContaining(equipmentNumber);
			}
			if (StringUtil.isNotNull(equipmentName))
			{
				equipmentFilter.addSearchBy(
					IEquipmentFilterAttributes.DESCRIPTION, IFilterComparisonOperators.CONTAINING,
					equipmentName);
			}
			Vector<Equipment> equipments = equipmentFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, equipmentVO);
			model.addAttribute(
				QUERY_LIST, equipments);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleChildEquipment";
	}

	/**
	 * 进入设备页面
	 */
	@RequestMapping(params = "toSingleEquipment")
	public String toEquipmentMainPage()
	{
		return "util/im/singleEquipment";
	}

	/**
	 * 查询设备
	 */
	@RequestMapping(params = "singleEquipmentList")
	public String query(EquipmentVO equipmentVO, Model model)
	{
		try
		{
			String equipmentNumber = equipmentVO.getEquipmentNumber();
			String equipmentName = equipmentVO.getEquipmentName();

			EquipmentFilter equipmentFilter = imService.createEquipmentFilter();
			if (StringUtil.isNotNull(equipmentNumber))
			{
				equipmentFilter.forNameContaining(equipmentNumber);
			}
			if (StringUtil.isNotNull(equipmentName))
			{
				equipmentFilter.addSearchBy(
					IEquipmentFilterAttributes.DESCRIPTION, IFilterComparisonOperators.CONTAINING,
					equipmentName);
			}
			Vector<Equipment> equipments = equipmentFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, equipmentVO);
			model.addAttribute(
				QUERY_LIST, equipments);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleEquipment";
	}

	/**
	 * 进入工作中心页面
	 */
	@RequestMapping(params = "toSingleWorkCenter")
	public String toWorkerCenterMainPage()
	{
		return "util/im/singleWorkCenter";
	}

	/**
	 * 查询工作中心
	 */
	@RequestMapping(params = "singleWorkCenterList")
	public String query(WorkCenterVO workCenterVO, Model model)
	{
		try
		{
			String workCenterNumber = workCenterVO.getWorkCenterNumber();
			String workCenterName = workCenterVO.getWorkCenterName();

			WorkCenterFilter workCenterFilter = imService.createWorkCenterFilter();
			if (StringUtil.isNotNull(workCenterNumber))
			{
				workCenterFilter.forNameContaining(workCenterNumber);
			}
			if (StringUtil.isNotNull(workCenterName))
			{
				workCenterFilter.addSearchBy(
					IWorkCenterFilterAttributes.DESCRIPTION, IFilterComparisonOperators.CONTAINING,
					workCenterName);
			}
			Vector<WorkCenter> workCenters = workCenterFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, workCenterVO);
			model.addAttribute(
				QUERY_LIST, workCenters);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleWorkCenter";
	}

	/**
	 * 进入生产线页面
	 */
	@RequestMapping(params = "toSingleProductionLine")
	public String toProductionLineMainPage()
	{
		return "util/im/singleProductionLine";
	}

	/**
	 * 查询生产线
	 */
	@RequestMapping(params = "singleProductionLineList")
	public String query(ProductionLineVO productionLineVO, Model model)
	{
		try
		{
			String productionLineNumber = productionLineVO.getProductionLineNumber();
			String productionLineName = productionLineVO.getProductionLineName();

			ProductionLineFilter productionLineFilter = imService.createProductionLineFilter();
			if (StringUtil.isNotNull(productionLineNumber))
			{
				productionLineFilter.forNameContaining(productionLineNumber);
			}
			if (StringUtil.isNotNull(productionLineName))
			{
				productionLineFilter.addSearchBy(
					IProductionLineFilterAttributes.DESCRIPTION,
					IFilterComparisonOperators.CONTAINING, productionLineName);
			}
			Vector<ProductionLine> productionLines = productionLineFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, productionLineVO);
			model.addAttribute(
				QUERY_LIST, productionLines);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleProductionLine";
	}

	/**
	 * 进入车间页面
	 */
	@RequestMapping(params = "toSingleArea")
	public String toAreaMainPage()
	{
		return "util/im/singleArea";
	}

	/**
	 * 查询车间
	 */
	@RequestMapping(params = "singleAreaList")
	public String query(AreaVO areaVO, Model model)
	{
		try
		{
			String areaNumber = areaVO.getAreaNumber();
			String areaName = areaVO.getAreaName();

			AreaFilter areaFilter = imService.createAreaFilter();
			if (StringUtil.isNotNull(areaNumber))
			{
				areaFilter.forNameContaining(areaNumber);
			}
			if (StringUtil.isNotNull(areaName))
			{
				areaFilter.addSearchBy(
					IAreaFilterAttributes.DESCRIPTION, IFilterComparisonOperators.CONTAINING,
					areaName);
			}
			Vector<Area> areas = areaFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, areaVO);
			model.addAttribute(
				QUERY_LIST, areas);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleArea";
	}

	/**
	 * 进入用户组页面
	 */
	@RequestMapping(params = "toSingleUserGroup")
	public String toSingleUserGroup()
	{
		return "util/im/singleUserGroup";
	}

	/**
	 * 用户组查询
	 */
	@RequestMapping(params = "singleUserGroupList")
	public String query(UserGroupVO userGroupVO, Model model)
	{
		try
		{
			String name = userGroupVO.getName();
			String description = userGroupVO.getDescription();

			UserGroupFilter userGroupFilter = imService.createUserGroupFilter();
			if (StringUtil.isNotNull(name))
			{
				userGroupFilter.forNameContaining(name);
			}
			if (StringUtil.isNotNull(description))
			{
				userGroupFilter.addSearchBy(
					IUserGroupFilterAttributes.DESCRIPTION, IFilterComparisonOperators.CONTAINING,
					description);
			}
			Vector<UserGroup> userGroups = userGroupFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, userGroupVO);
			model.addAttribute(
				QUERY_LIST, userGroups);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleUserGroup";
	}

	/**
	 * 进入用户页面
	 */
	@RequestMapping(params = "toSingleUser")
	public String toSingleUser()
	{
		return "util/im/singleUser";
	}

	/**
	 * 用户查询
	 */
	@RequestMapping(params = "singleUserList")
	public String query(UserVO userVO, Model model)
	{
		try
		{
			String name = userVO.getName();
			String description = userVO.getDescription();

			UserFilter userFilter = imService.createUserFilter();
			if (StringUtil.isNotNull(name))
			{
				userFilter.forUserNameContaining(name);
			}
			if (StringUtil.isNotNull(description))
			{
				userFilter.addSearchBy(
					IUserFilterAttributes.DESCRIPTION, IFilterComparisonOperators.CONTAINING,
					description);
			}
			Vector<User> users = userFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, userVO);
			model.addAttribute(
				QUERY_LIST, users);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleUser";
	}

	/**
	 * Step进入工作中心页面
	 */
	@RequestMapping(params = "toSingleWorkCenterListForStep")
	public String toWorkerCenterMainPageStep(@RequestParam("routeKey") long routeKey, Model model)
	{
		model.addAttribute(
			"routeKey", routeKey);
		return "util/im/singleWorkCenterForStep";
	}

	/**
	 * Step查询工作中心
	 */
	@RequestMapping(params = "singleWorkCenterForStep")
	public String query(@RequestParam("routeKey") long routeKey, WorkCenterVO workCenterVO,
		Model model)
	{
		try
		{
			String workCenterNumber = workCenterVO.getWorkCenterNumber();
			String workCenterName = workCenterVO.getWorkCenterName();
			Vector<ProductionLine> productionLines = imService.getRouteByKey(
				routeKey).getProductionLines();

			String[] productionLineKeys = new String[productionLines.size()];
			for (int i = 0; i < productionLines.size(); i++)
			{
				ProductionLine productionLine = productionLines.get(i);
				String key = "" + productionLine.getKey();
				productionLineKeys[i] = key;
			}
			ProductionLineFilter productionLineFilter = imService.createProductionLineFilter();
			productionLineFilter.addSearchBy(
				IProductionLineFilterAttributes.KEY, IFilterComparisonOperators.IN,
				productionLineKeys);
			WorkCenterFilter workCenterFilter = imService.createWorkCenterFilter();
			workCenterFilter.forParentProductionLinesIn(productionLineFilter);
			if (StringUtil.isNotNull(workCenterNumber))
			{
				workCenterFilter.forNameContaining(workCenterNumber);
			}
			if (StringUtil.isNotNull(workCenterName))
			{
				workCenterFilter.addSearchBy(
					IWorkCenterFilterAttributes.DESCRIPTION, IFilterComparisonOperators.CONTAINING,
					workCenterName);
			}
			Vector<WorkCenter> workCenters = workCenterFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, workCenterVO);
			model.addAttribute(
				QUERY_LIST, workCenters);
			model.addAttribute(
				"routeKey", routeKey);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleWorkCenterForStep";
	}

	
	/**
	 * Step select Operation进入页面
	 */
	@RequestMapping(params = "toSelectOperation")
	public String toSelectOperation(@RequestParam("routeKey") long routeKey, Model model)
	{
		model.addAttribute(
			"routeKey", routeKey);
		return "util/im/singleSelectOperation";
	}
	/**
	 * Step select Operation
	 */
	@RequestMapping(params = "selectOperation")
	public String query(@RequestParam("routeKey") long routeKey, OperationVO operationVO,
		Model model)
	{
		try
		{
			String operationName = operationVO.getOperationName();
			String operationNumber = operationVO.getOperationNumber();
			System.out.println(operationName);
			System.out.println(operationNumber);
			OperationFilter operationFilter = imService.createOperationFilter();
			if (StringUtil.isNotNull(operationNumber))
			{
				operationFilter.addSearchBy(
					IRouteOperationFilterAttributes.NAME, IFilterComparisonOperators.CONTAINING,
					operationNumber);
			}
			if (StringUtil.isNotNull(operationName))
			{
				operationFilter.addSearchBy(
					IRouteOperationFilterAttributes.DESCRIPTION,
					IFilterComparisonOperators.CONTAINING, operationName);
			}
			Vector<Operation> operations = operationFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, operationVO);
			model.addAttribute(
				QUERY_LIST, operations);
			model.addAttribute(
				"routeKey", routeKey);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleSelectOperation";
	}
	
	/**
	 * OrderItem select Part进入页面
	 */
	@RequestMapping(params = "toSelectPart")
	public String toSelectPart()
	{
		return "util/im/singleSelectPart";
	}
	/**
	 * OrderItem select Part
	 */
	@RequestMapping(params = "selectPart")
	public String query(PartVO partVO,
		Model model)
	{
		try
		{
			String partNumber = partVO.getPartNumber();
			String partName = partVO.getPartName();
			PartFilter partFilter = imService.createPartFilter();
			if (StringUtil.isNotNull(partNumber)) {
				partFilter.addSearchBy(IPartFilterAttributes.NUMBER,IFilterComparisonOperators.CONTAINING, partNumber);
			
			}
			if (StringUtil.isNotNull(partName)) {
				partFilter.addSearchBy(IPartFilterAttributes.DESCRIPTION, IFilterComparisonOperators.CONTAINING, partName);
			}
			Vector<Part> parts = partFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, partVO);
			model.addAttribute(
				QUERY_LIST, parts);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleSelectPart";
	}
	

		
	/**
	 * OrderItem select Route进入页面
	 */
	@RequestMapping(params = "toSelectRoute")
	public String toSelectRoute()
	{
		return "util/im/singleSelectRoute";
	}
	/**
	 * OrderItem select Route
	 */
	@RequestMapping(params = "selectRoute")
	public String query(RouteVO routeVO,
		Model model)
	{
		try
		{
			String routeNumber = routeVO.getRouteNumber();
			String routeName = routeVO.getRouteName();
			RouteFilter routeFilter = imService.createRouteFilter();
			if (StringUtil.isNotNull(routeNumber))
			{
				routeFilter.addSearchBy(
					IRouteFilterAttributes.NAME, 
					IFilterComparisonOperators.CONTAINING, routeNumber);
			}
			if (StringUtil.isNotNull(routeName))
			{
				routeFilter.addSearchBy(
					IRouteFilterAttributes.DESCRIPTION, 
					IFilterComparisonOperators.CONTAINING,routeName);
			}
			Vector<Route> routes = routeFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, routeVO);
			model.addAttribute(
				QUERY_LIST, routes);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleSelectRoute";
	}
	
	/**
	 * OrderItem select ProductionLine after Route
	 */
	@RequestMapping(params = "selectProductionLine")
	public String query(@RequestParam("routeNumber") String routeNumber, ProductionLineVO productionLineVO,
		Model model)
	{
		try
		{
			Route route = imService.getRouteByName(routeNumber);
			Vector<ProductionLine> productionLines = route.getProductionLines();

			model.addAttribute(
				"routeNumber", routeNumber);
			model.addAttribute(
				VIEW_OBJECT, productionLineVO);
			model.addAttribute(
				QUERY_LIST, productionLines);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleSelectProductionLine";
	}
	
	/**
	 * OrderItem select BOM进入页面
	 */
	@RequestMapping(params = "toSelectBOM")
	public String toSelectBOM()
	{
		return "util/im/singleSelectBOM";
	}
	/**
	 * OrderItem select BOM
	 */
	@RequestMapping(params = "selectBOM")
	public String query(BOMVO bomVO,
		Model model)
	{
		try
		{
			String bomNumber = bomVO.getBomNumber();
			String bomName = bomVO.getBomName();
				
			BomFilter bomFilter = imService.createBOMFilter();
			if (StringUtil.isNotNull(bomNumber))
			{
				bomFilter.forBomNameContaining(bomNumber);
			}
			if (StringUtil.isNotNull(bomName))
			{
				bomFilter.forDescriptionContaining(bomName);
			}
			Vector<BillOfMaterials> boms = bomFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, bomVO);
			model.addAttribute(
				QUERY_LIST, boms);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleSelectBOM";
	}
		
	/**
	 * Lot select Order进入页面
	 */
	@RequestMapping(params = "toSelectOrder")
	public String toSelectOrder()
	{
		return "util/im/singleSelectOrder";
	}
	/**
	 * Lot select Order
	 */
	@RequestMapping(params = "selectOrder")
	public String query(OrderVO orderVO,
		Model model)
	{
		try
		{
			String orderNumber = orderVO.getOrderNumber();
			
			OrderFilter orderFilter = imService.createOrderFilter();
			if(StringUtil.isNotNull(orderNumber))
			{
				orderFilter.forOrderNumberContaining(orderNumber);
			}
			Vector<Order> orders = orderFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, orderVO);
			model.addAttribute(
				QUERY_LIST, orders);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleSelectOrder";
	}
		
	/**
	 * Lot select OrderItem after Order
	 */
	@RequestMapping(params = "selectOrderItem")
	public String query(@RequestParam("orderNumber") String orderNumber, OrderItemVO orderItemVO,
		Model model)
	{
		try
		{

			Order order = imService.getOrderByName(orderNumber);
			Vector<OrderItem> orderItems = order.getOrderItems();

			model.addAttribute(
				"orderNumber", orderNumber);
			model.addAttribute(
				VIEW_OBJECT, orderItemVO);
			model.addAttribute(
				QUERY_LIST, orderItems);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleSelectOrderItem";
	}	
	
	/**
	 * select Lot进入页面
	 */
	@RequestMapping(params = "toSelectLot")
	public String toSelectLot()
	{
		return "util/im/singleSelectLot";
	}
	/**
	 * select Lot
	 */
	@RequestMapping(params = "selectLot")
	public String query(LotVO lotVO,
		Model model)
	{
		try
		{
			String lotNumber = lotVO.getName();
			String lotType = lotVO.getLotType(); 
			LotFilter lotFilter = imService.createLotFilter();
			if(StringUtil.isNotNull(lotNumber))
			{
				lotFilter.forLotNameContaining(lotNumber);
			}
			lotFilter.forLotTypeSerialized();
			
			Vector<Lot> lots = lotFilter.exec();
			model.addAttribute(
				VIEW_OBJECT, lotVO);
			model.addAttribute(
				QUERY_LIST, lots);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleSelectLot";
	}
	
	/**
	 * select Step by RouteKey
	 */
	@RequestMapping(params = "selectStep")
	public String query(@RequestParam("routeKey") long routeKey, StepVO stepVO,
		Model model)
	{
		try
		{

			Route route = imService.getRouteByKey(routeKey);
			Vector<Step> steps = route.getSteps();

			model.addAttribute(
				"routeKey", routeKey);
			model.addAttribute(
				VIEW_OBJECT, stepVO);
			model.addAttribute(
				QUERY_LIST, steps);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleSelectStep";
	}	
	
	
	
	

	/**
	 * 进入零件列表页面
	 */
	@RequestMapping(params = "toSinglePart")
	public String toSinglePart()
	{
		return "util/im/singleQualityPart";
	}
	
	@RequestMapping(params = "singleQualityPartList")
	public String singleQualityPartList(OperationQualityPartVO oqpVO, Model model)
	{
		try
		{
			String level1P = oqpVO.getLevel1();
			String level2P = oqpVO.getLevel2();
			String level3P = oqpVO.getLevel3();
			String level4P = oqpVO.getLevel4();
			String level5P = oqpVO.getLevel5();
			
		    String sql = "	select atr_key , level1_S ,level2_S,level3_S,level4_S,level5_S from UD_quality_part where 1=1  ";
		    if(level1P != null && !"".equals(level1P)){
		    	sql = sql + " and   level1_S like '%"+level1P+"%' " ;
		    }
		    if(level2P != null && !"".equals(level2P)){
		    	sql = sql + " and   level2_S like '%"+level2P+"%' " ;
		    }
		    
		    Vector<String[]>  currentList = implService.getFunctions().getArrayDataFromActive(sql, 100 ) ;
			 
			Vector<OperationQualityPartVO> VOs = new Vector<OperationQualityPartVO>();
			for(String[] array : currentList)
			{
				OperationQualityPartVO vo = new OperationQualityPartVO();
				
				String qpKey = array[0];
				String level1 = array[1];
				String level2 = array[2];
				String level3 = array[3];
				String level4 = array[4];
				String level5 = array[5];
				
				
				vo.setQpKey(qpKey);
				vo.setLevel1(level1);
				vo.setLevel2(level2);
				vo.setLevel3(level3);
				vo.setLevel4(level4);
				vo.setLevel5(level5);
				VOs.add(vo);
			}
				

			model.addAttribute(
				VIEW_OBJECT, oqpVO);
			model.addAttribute(
				QUERY_LIST, VOs);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return  toSinglePart();
	}
	
	
	
	/**
	 * 进入工厂品牌质量门页面
	 */
	@RequestMapping(params = "toSingleFactoryBrandQualityGate")
	public String toSingleFactoryBrandQualityGate()
	{
		return "util/im/singleFactoryBrandQualityGate";
	}
	
	@RequestMapping(params = "singleFactoryBrandQualityGateList")
	public String singleFactoryBrandQualityGateList(OperationQualityPartVO oqpVO, Model model)
	{
		try
		{
			String factoryNameP = oqpVO.getFactoryName();
			String brandNameP = oqpVO.getBrandName();
			String qualityGate_1P =oqpVO.getQualityGate_1() ;
			String qualityGate_2P =oqpVO.getQualityGate_2() ;
		    String sql = "	 select  f.atr_key, f.factory_name_S ,f.brand_name_S , q.atr_key , q.quality_gate_1_S , q.quality_gate_2_S    from  UD_quality_gate q , UD_factory_brand f WHERE  f.atr_key =   q.factory_brand_key_I ";
		    if(factoryNameP != null && !"".equals(factoryNameP)){
		    	sql = sql + " and   f.factory_name_S  like '%"+factoryNameP+"%' " ;
		    }
		    if(brandNameP != null && !"".equals(brandNameP)){
		    	sql = sql + " and   f.brand_name_S   like '%"+brandNameP+"%' " ;
		    }
		    if(qualityGate_1P != null && !"".equals(qualityGate_1P)){
		    	sql = sql + " and    q.quality_gate_1_S like '%"+qualityGate_1P+"%' " ;
		    }
		    if(qualityGate_2P != null && !"".equals(qualityGate_2P)){
		    	sql = sql + " and    q.quality_gate_2_S  like '%"+qualityGate_2P+"%' " ;
		    }
		    Vector<String[]>  currentList = implService.getFunctions().getArrayDataFromActive(sql, 100 ) ;
			 
			Vector<OperationQualityPartVO> VOs = new Vector<OperationQualityPartVO>();
			for(String[] array : currentList)
			{
				OperationQualityPartVO vo = new OperationQualityPartVO();
				
				String fbKey = array[0];
				String factoryName = array[1];
				String brandName = array[2];
				
				String qgKey = array[3];
			 
				String qualityGate_1 = array[4];
				String qualityGate_2 = array[5];
				
			 
				
						
				
				vo.setFbKey(fbKey);
				vo.setFactoryName(factoryName);
				vo.setBrandName(brandName);
				
				vo.setQgKey(qgKey);
				vo.setQualityGate_1(qualityGate_1);
				vo.setQualityGate_2(qualityGate_2);
				
			 
				VOs.add(vo);
			}
				

			model.addAttribute(
				VIEW_OBJECT, oqpVO);
			model.addAttribute(
				QUERY_LIST, VOs);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return  toSingleFactoryBrandQualityGate();
	}
	
	/**
	 * 进入工厂品牌页面
	 */
	@RequestMapping(params = "toSingleFactoryBrand")
	public String toSingleFactoryBrand()
	{
		return "util/im/singleFactoryBrand";

	}
	
	
	/**
	 * 查询工厂品牌
	 */
	@RequestMapping(params = "singleFactoryBrandList")
	public String query(FactoryBrandVO factoryBrandVO, Model model)
	{
		try
		{
			UTRow atrow = null ;
			String factoryNameP = factoryBrandVO.getFactoryName();
			String brandNameP = factoryBrandVO.getBrandName();
			
		 
			UTHandler athandler  = implService.getFunctions().createATHandler("factory_brand") ;
			UTRowFilter filter =  implService.getFunctions().createATRowFilter("factory_brand");
			if( factoryNameP != null && !"".equals(factoryNameP)){
				filter.forColumnNameContaining("factory_name", factoryNameP);
			}
			if( brandNameP != null && !"".equals(brandNameP)){
				filter.forColumnNameContaining("brand_name", brandNameP);
			}
			
			
			Vector<UTRow> currentList = athandler.getATRowsByFilter(filter, false);
			Vector<FactoryBrandVO> factoryBrandVOs = new Vector<FactoryBrandVO>();
			for(UTRow row : currentList)
			{
				FactoryBrandVO vo = new FactoryBrandVO();
				long key1 = row.getKey();
				String brandName = row.getValue("brand_name").toString() ;
				String factoryName = row.getValue("factory_name").toString();
				vo.setKey(key1);
				vo.setFactoryName(factoryName);
				vo.setBrandName(brandName);
				factoryBrandVOs.add(vo);
			}
			
			

			model.addAttribute(
				VIEW_OBJECT, factoryBrandVO);
			model.addAttribute(
				QUERY_LIST, factoryBrandVOs);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleFactoryBrand";
	}
	
	/**
	 * select Part进入页面
	 */
	@RequestMapping(params = "toSelectBasicPart")
	public String toSelectBasicPart()
	{
		return "util/im/singleSelectBasicPart";
//		return "util/im/singleUser";
	}
	/**
	 * OrderItem select Part
	 */
	@RequestMapping(params = "selectBasicPart")
	public String queryBaiscPart(BaiscPartVO basicpartVO,
		Model model)
	{
		try
		{
			String partNumber = basicpartVO.getPart_num();
			String partName = basicpartVO.getPart_name();
			
			Vector<String[]> parts = materialTrackAPI.getPartData(partNumber, partName);
			
			model.addAttribute(
				VIEW_OBJECT, basicpartVO);
			model.addAttribute(
				QUERY_LIST, parts);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return "util/im/singleSelectBasicPart";
	}
	
}
