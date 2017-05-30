/*
 * @(#) IMService.java 2016-6-30 下午8:32:12
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.service.impl;

import java.util.Vector;

import org.springframework.stereotype.Component;

import com.mes.compatibility.client.AccessPrivilege;
import com.mes.compatibility.client.AccessPrivilegeFilter;
import com.mes.compatibility.client.AddOn;
import com.mes.compatibility.client.AddOnFilter;
import com.mes.compatibility.client.Area;
import com.mes.compatibility.client.AreaFilter;
import com.mes.compatibility.client.BillOfMaterials;
import com.mes.compatibility.client.BomFilter;
import com.mes.compatibility.client.Box;
import com.mes.compatibility.client.BoxFilter;
import com.mes.compatibility.client.Carrier;
import com.mes.compatibility.client.CarrierFilter;
import com.mes.compatibility.client.DataCollectionSet;
import com.mes.compatibility.client.DataCollectionSetFilter;
import com.mes.compatibility.client.DsList;
import com.mes.compatibility.client.Equipment;
import com.mes.compatibility.client.EquipmentFilter;
import com.mes.compatibility.client.EventSheetHolder;
import com.mes.compatibility.client.EventSheetHolderFilter;
import com.mes.compatibility.client.ImageFilter;
import com.mes.compatibility.client.ListFilter;
import com.mes.compatibility.client.Lot;
import com.mes.compatibility.client.LotFilter;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.ManagerSupport;
import com.mes.compatibility.client.Operation;
import com.mes.compatibility.client.OperationFilter;
import com.mes.compatibility.client.Order;
import com.mes.compatibility.client.OrderFilter;
import com.mes.compatibility.client.OrderItem;
import com.mes.compatibility.client.OrderItemFilter;
import com.mes.compatibility.client.Part;
import com.mes.compatibility.client.PartFilter;
import com.mes.compatibility.client.ProductionLine;
import com.mes.compatibility.client.ProductionLineFilter;
import com.mes.compatibility.client.Route;
import com.mes.compatibility.client.RouteFilter;
import com.mes.compatibility.client.Site;
import com.mes.compatibility.client.SiteFilter;
import com.mes.compatibility.client.Station;
import com.mes.compatibility.client.StationFilter;
import com.mes.compatibility.client.TestDefinition;
import com.mes.compatibility.client.TestDefinitionFilter;
import com.mes.compatibility.client.UDADefinition;
import com.mes.compatibility.client.UDADefinitionFilter;
import com.mes.compatibility.client.UTColumnDefinitionFilter;
import com.mes.compatibility.client.UTDefinition;
import com.mes.compatibility.client.UTDefinitionFilter;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.Unit;
import com.mes.compatibility.client.UnitFilter;
import com.mes.compatibility.client.User;
import com.mes.compatibility.client.UserFilter;
import com.mes.compatibility.client.UserGroup;
import com.mes.compatibility.client.UserGroupFilter;
import com.mes.compatibility.client.WorkCenter;
import com.mes.compatibility.client.WorkCenterFilter;
import com.mes.compatibility.manager.FormManager;
import com.mes.shopflow.common.dataobjects.DUDADefinition;
import com.mes.shopflow.common.utility.EncryptionUtils;
import com.mes.webclient.controller.bean.UIEventSheet;
import com.mes.webclient.service.IIMService;

@Component("imService")
public class IMService extends BaseService implements IIMService
{

	@Override
	public WorkCenterFilter createWorkCenterFilter()
		throws MESException
	{
		return getFunctions().createWorkCenterFilter();
	}

	@Override
	public WorkCenter getWorkCenterByKey(long key)
		throws MESException
	{
		return getFunctions().getWorkCenter(
			key);
	}

	@Override
	public WorkCenter getWorkCenterByName(String name)
		throws MESException
	{
		return getFunctions().getWorkCenter(
			name);
	}

	@Override
	public WorkCenter createWorkCenter()
		throws MESException
	{
		return getFunctions().createWorkCenter(
			"workCenter");
	}

	@Override
	public PartFilter createPartFilter()
		throws MESException
	{

		return getFunctions().createPartFilter();
	}

	@Override
	public Part getPartByKey(long key)
		throws MESException
	{

		return getFunctions().getPart(
			key);
	}

	@Override
	public Part createPart()
		throws MESException
	{
		return getFunctions().createPart(
			"part", "part");
	}

	@Override
	public EquipmentFilter createEquipmentFilter()
		throws MESException
	{
		return getFunctions().createEquipmentFilter();
	}

	@Override
	public Equipment getEquipmentByKey(long key)
		throws MESException
	{
		return getFunctions().getEquipment(
			key);
	}

	@Override
	public Equipment getEquipmentByName(String name)
		throws MESException
	{
		return getFunctions().getEquipment(
			name);
	}

	@Override
	public Equipment createEquipment()
		throws MESException
	{
		return getFunctions().createEquipment(
			"equipment");
	}

	@Override
	public ProductionLineFilter createProductionLineFilter()
		throws MESException
	{
		return getFunctions().createProductionLineFilter();
	}

	@Override
	public ProductionLine getProductionLineByKey(long key)
		throws MESException
	{

		return getFunctions().getProductionLine(
			key);
	}

	@Override
	public ProductionLine getProductionLineByName(String name)
		throws MESException
	{
		return getFunctions().getProductionLine(
			name);
	}

	@Override
	public ProductionLine createProductionLine()
		throws MESException
	{
		return getFunctions().createProductionLine(
			"productionLine");
	}

	@Override
	public UserFilter createUserFilter()
		throws MESException
	{

		return getFunctions().createUserFilter();
	}

	@Override
	public User createUser()
		throws MESException
	{

		return getFunctions().createUser(
			"user");
	}

	@Override
	public User getUserByKey(long key)
		throws MESException
	{

		return getFunctions().getUser(
			key);
	}

	@Override
	public UserGroupFilter createUserGroupFilter()
		throws MESException
	{

		return getFunctions().createUserGroupFilter();
	}

	@Override
	public UserGroup createUserGroup()
		throws MESException
	{

		return getFunctions().createUserGroup(
			"usergroup");
	}

	@Override
	public UserGroup getUserGroupByKey(long key)
		throws MESException
	{

		return getFunctions().getUserGroup(
			key);
	}

	@Override
	public AccessPrivilegeFilter createAccessPrivilegeFilter()
		throws MESException
	{

		return getFunctions().createAccessPrivilegeFilter();
	}

	@Override
	public AccessPrivilege createAccessPrivilege()
		throws MESException
	{

		return getFunctions().createAccessPrivilege(
			"accessPrivilegeName");
	}

	@Override
	public AccessPrivilege getAccessPrivilegeByKey(long key)
		throws MESException
	{
		return getFunctions().getAccessPrivilege(
			key);
	}

	@Override
	public BomFilter createBOMFilter()
		throws MESException
	{
		return getFunctions().createBOMFilter();
	}

	@Override
	public BillOfMaterials createBOM()
		throws MESException
	{
		return getFunctions().createBillOfMaterial(
			"bom", "1");
	}

	@Override
	public BillOfMaterials getBillOfMaterialsByKey(long key)
		throws MESException
	{
		return getFunctions().getBillOfMaterial(
			key);
	}

	@Override
	public AreaFilter createAreaFilter()
		throws MESException
	{
		return getFunctions().createAreaFilter();
	}

	@Override
	public Area getAreaByKey(long key)
		throws MESException
	{
		return getFunctions().getArea(
			key);
	}

	@Override
	public Area getAreaByName(String name)
		throws MESException
	{
		return getFunctions().getArea(
			name);
	}

	@Override
	public Area createArea()
		throws MESException
	{
		return getFunctions().createArea(
			"area");
	}

	@Override
	public SiteFilter createSiteFilter()
		throws MESException
	{
		return getFunctions().createSiteFilter();
	}

	@Override
	public Site getSiteByKey(long key)
		throws MESException
	{
		return getFunctions().getSite(
			key);
	}

	@Override
	public Site getSiteByName(String name)
		throws MESException
	{
		return getFunctions().getSite(
			name);
	}

	@Override
	public Site createSite()
		throws MESException
	{
		return getFunctions().createSite(
			"site");
	}

	@Override
	public OperationFilter createOperationFilter()
		throws MESException
	{
		return getFunctions().createOperationFilter();
	}

	@Override
	public Operation getOperationByKey(long key)
		throws MESException
	{
		return getFunctions().getOperation(
			key);
	}

	@Override
	public Operation createOperation()
		throws MESException
	{
		return getFunctions().createOperation(
			"operation");
	}

	@Override
	public TestDefinitionFilter createTestDefinitionFilter()
		throws MESException
	{

		return getFunctions().createTestDefinitionFilter();
	}

	@Override
	public CarrierFilter createCarrierFilter()
		throws MESException
	{
		return getFunctions().createCarrierFilter();
	}

	@Override
	public TestDefinition getTestDefinitionByKey(long key)
		throws MESException
	{

		return getFunctions().getTestDefinitionByKey(
			key);

	}

	@Override
	public Carrier getCarrierByKey(long key)
		throws MESException
	{
		return getFunctions().getCarrier(
			key);
	}

	@Override
	public Carrier getCarrierByName(String name)
		throws MESException
	{
		return getFunctions().getCarrierByName(
			name);
	}

	@Override
	public TestDefinition createTestDefinition()
		throws MESException
	{

		return getFunctions().createTestDefinition(
			"TestDefinition");
	}

	@Override
	public Carrier createCarrier()
		throws MESException
	{
		return getFunctions().createCarrier(
			"carrier");
	}

	@Override
	public ListFilter createListFilter()
		throws MESException
	{
		return getFunctions().createListFilter();
	}

	@Override
	public UTDefinitionFilter createaAtDefinitionFilter()
		throws MESException
	{

		return getFunctions().createAtDefinitionFilter();
	}

	@Override
	public UTDefinition getAtDefinitionByKey(long key)
		throws MESException
	{
		return getFunctions().getAtDefinition(
			key);
	}

	@Override
	public UTDefinition createatdAtDefinition()
		throws MESException
	{

		return getFunctions().createAtDefinition();
	}

	@Override
	public DsList getDsListByName(String listname)
		throws MESException
	{

		return getFunctions().getList(
			listname);
	}

	@Override
	public UTColumnDefinitionFilter createAtColumnDefinitionFilter()
		throws MESException
	{

		return getFunctions().createaAtColumnDefinitionFilter();
	}

	@Override
	public RouteFilter createRouteFilter()
		throws MESException
	{
		return getFunctions().createRouteFilter();
	}

	@Override
	public Route getRouteByKey(long key)
		throws MESException
	{
		return getFunctions().getRoute(
			key);
	}

	@Override
	public Route createRoute()
		throws MESException
	{
		return getFunctions().createRoute(
			"route");
	}

	@Override
	public Unit getUnitBySerialNumber(String serialNumber)
		throws MESException
	{

		return getFunctions().getUnitBySerialNumber(
			serialNumber);
	}

	@Override
	public DataCollectionSetFilter createDataCollectionSetFilter()
		throws MESException
	{
		return getFunctions().createDataCollectionSetFilter();
	}

	@Override
	public DataCollectionSet getDataCollectionSetByKey(long key)
		throws MESException
	{
		return getFunctions().getDataCollectionSetByKey(
			key);
	}

	@Override
	public FormManager getFormManager()
		throws MESException
	{
		return getFunctions().getCurrentServerImpl().getFormManager();
	}

	@Override
	public BoxFilter createBoxFilter()
		throws MESException
	{
		return getFunctions().createBoxFilter();
	}

	@Override
	public Box getBoxByName(String name)
		throws MESException
	{
		return getFunctions().getBox(
			name);
	}

	@Override
	public Box createBox(String name)
		throws MESException
	{
		return getFunctions().createBox(
			name);
	}

	@Override
	public OrderFilter createOrderFilter()
		throws MESException
	{
		return getFunctions().createOrderFilter();
	}

	@Override
	public Order getOrderByKey(long key)
		throws MESException
	{
		return getFunctions().getOrderByKey(
			key);
	}

	@Override
	public Order createOrder()
		throws MESException
	{
		return getFunctions().createOrder(
			"order");
	}

	@Override
	public OrderItemFilter createOrderItemFilter()
		throws MESException
	{
		return getFunctions().createOrderItemFilter();
	}

	@Override
	public OrderItem createOrderItem()
		throws MESException
	{
		return getFunctions().createOrderItem();
	}

	@Override
	public Route getRouteByName(String routeName)
		throws MESException
	{
		return getFunctions().getRoute(
			routeName);
	}

	@Override
	public LotFilter createLotFilter()
		throws MESException
	{
		return getFunctions().createLotFilter();
	}

	@Override
	public Lot createLot(Order order, String lotName)
		throws MESException
	{
		return getFunctions().createLot(
			order, lotName);
	}

	@Override
	public Lot getLotByName(String name)
		throws MESException
	{
		return getFunctions().getLotByName(
			name);
	}

	@Override
	public Lot getLotByKey(long key)
		throws MESException
	{
		return getFunctions().getLotByKey(
			key);
	}

	@Override
	public Order getOrderByName(String name)
		throws MESException
	{
		return getFunctions().getOrderByName(
			name);
	}

	@Override
	public Part getPart(String partNumber, String partRevision)
		throws MESException
	{

		return getFunctions().getPart(
			partNumber, partRevision);
	}
	@Override
	public Part getPartWithNoCache(String partNumber, String partRevision)
		throws MESException
	{

		return getFunctions().getPartWithNoCache(
			partNumber, partRevision);
	}

	public EventSheetHolderFilter createEventSheetHolderFilter()
		throws MESException
	{
		return getFunctions().createEventSheetHolderFilter();
	}

	public UIEventSheet createEventSheetHolder()
		throws MESException
	{
		EventSheetHolder eventSheetHolder = getFunctions().createEventSheetHolder();
		return new UIEventSheet(eventSheetHolder);
	}

	@Override
	public Unit getUnitByKey(long key)
		throws MESException
	{
		return getFunctions().getUnitByKey(
			key);
	}

	@Override
	public BillOfMaterials getBillOfMaterials(String bomName, String bomRevision)
		throws MESException
	{
		return getFunctions().getBillOfMaterial(
			bomName, bomRevision);
	}
	@Override
	public BillOfMaterials getBillOfMaterialsWithNoCache(String bomName, String bomRevision)
		throws MESException
	{
		
		return getFunctions().getBillOfMaterialWithNoCache(
			bomName, bomRevision);
	}

	@Override
	public UnitFilter createUnitFilter()
		throws MESException
	{

		return getFunctions().createUnitFilter();
	}

	public DataCollectionSet getDataCollectionSetByName(String name)
		throws MESException
	{

		return getFunctions().getDataCollectionSetByName(
			name);
	}

	public UIEventSheet getEventSheetHolder(long key)
		throws MESException
	{
		EventSheetHolder eventSheetHolder = getFunctions().getEventSheetHolder(
			key);
		return new UIEventSheet(eventSheetHolder);
	}

	@Override
	public AddOnFilter createAddOnFilter()
		throws MESException
	{
		return new AddOnFilter(getFunctions().getServerImpl());
	}

	@Override
	public AddOn createAddOn()
		throws MESException
	{
		return getFunctions().createAddOn();
	}

	@Override
	public User GetUserByName(String name)
		throws MESException
	{

		return getFunctions().getUser(
			name);
	}

	@Override
	public ImageFilter createImageFilter()
		throws MESException
	{
		return getFunctions().createImageFilter();
	}

	@Override
	public Vector<String[]> getArrayDataFromActive(String sql)
		throws MESException
	{
		return getFunctions().getArrayDataFromActive(
			sql, 1000);
	}

	@Override
	public UDADefinition createUDADefinition(DUDADefinition dUDADefinition)
		throws MESException
	{

		return ManagerSupport.createUDADefinition(
			null, getFunctions().getCurrentServerImpl());
	}

	@Override
	public UTHandler getAtHandler(String atDefinitionName)
		throws MESException
	{

		return getFunctions().createATHandler(
			atDefinitionName);
	}

	@Override
	public UTRowFilter createAtRowFilter(String atDefinitionName)
		throws MESException
	{

		return getFunctions().createATRowFilter(
			atDefinitionName);
	}

	@Override
	public UDADefinitionFilter createUDADefinitionFilter()
		throws MESException
	{

		return getFunctions().createUDADefinitionFilter();
	}

	@Override
	public Operation getOperationByName(String name)
		throws MESException
	{
		return getFunctions().getOperation(
			name);
	}

	@Override
	public Station createStation()
		throws MESException
	{
		return getFunctions().createStation(
			"station");
	}

	@Override
	public StationFilter createStationFilter()
		throws MESException
	{
		return getFunctions().createStationFilter();
	}

	@Override
	public Station getStationByKey(long key)
		throws MESException
	{
		return getFunctions().getStation(
			key);
	}

	@Override
	public Station getStationByName(String name)
		throws MESException
	{
		return getFunctions().getStation(
			name);
	}

	@Override
	public UDADefinition getUdaDefinitionByType(short type)
		throws MESException
	{
		
		return (UDADefinition) getFunctions().getServerImpl().getUDAManager().getObject((long)type);
	}

	@Override
	public void changePassWord(String userName, String oldPassWord, String passWord)
		throws MESException
	{
		getFunctions().changePassWord(userName, oldPassWord, passWord);
	}

	@Override
	public String getOldPassWord(long userkey)
		throws MESException
	{
		User user = getFunctions().getUser(userkey);
		if(user!=null)
		{
			return user.getPassword();
		}
		else
		{
			return null;
		}
	}
 
	
	
}
