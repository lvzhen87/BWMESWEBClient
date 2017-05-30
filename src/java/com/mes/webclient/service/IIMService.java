/*
 * @(#) IIMService.java 2016-6-30 下午8:30:47
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.service;

import java.util.Vector;

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
import com.mes.compatibility.client.ImageFilter;
import com.mes.compatibility.client.ListFilter;
import com.mes.compatibility.client.Lot;
import com.mes.compatibility.client.LotFilter;
import com.mes.compatibility.client.MESException;
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

/**
 * 基础数据业务接口
 * 
 * <p>
 * 
 * @author Nemo, 2016-6-30 下午8:40:53
 */
public interface IIMService extends IBaseService
{
	/**
	 * 创建一个工作中心查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public WorkCenterFilter createWorkCenterFilter()
		throws MESException;

	/**
	 * 根据KEY获得工作中心
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public WorkCenter getWorkCenterByKey(long key)
		throws MESException;

	/**
	 * 根据Name获得工作中心
	 * 
	 * @param name
	 * @return
	 * @throws MESException
	 */
	public WorkCenter getWorkCenterByName(String name)
		throws MESException;

	/**
	 * 创建一个工作中心
	 * 
	 * @return
	 * @throws MESException
	 */
	public WorkCenter createWorkCenter()
		throws MESException;

	/**
	 * 创建一个生产线查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public ProductionLineFilter createProductionLineFilter()
		throws MESException;

	/**
	 * 根据KEY获得生产线
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public ProductionLine getProductionLineByKey(long key)
		throws MESException;

	/**
	 * 根据Name获得生产线
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public ProductionLine getProductionLineByName(String name)
		throws MESException;

	/**
	 * 创建一个生产线
	 * 
	 * @return
	 * @throws MESException
	 */
	public ProductionLine createProductionLine()
		throws MESException;

	/**
	 * 创建物料选择器
	 * 
	 * @return PartFilter
	 * @throws MESException
	 */
	public PartFilter createPartFilter()
		throws MESException;

	/**
	 * 根据KEY获得物料
	 * 
	 * @param key
	 * @return Part
	 * @throws MESException
	 */
	public Part getPartByKey(long key)
		throws MESException;

	/**
	 * 创建一个物料
	 * 
	 * @return Part
	 * @throws MESException
	 */
	public Part createPart()
		throws MESException;

	/**
	 * 创建一个设备查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public EquipmentFilter createEquipmentFilter()
		throws MESException;

	/**
	 * 根据KEY获得设备
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public Equipment getEquipmentByKey(long key)
		throws MESException;

	/**
	 * 根据Name获得设备
	 * 
	 * @param name
	 * @return
	 * @throws MESException
	 */
	public Equipment getEquipmentByName(String name)
		throws MESException;

	/**
	 * 创建一个设备
	 * 
	 * @return
	 * @throws MESException
	 */
	public Equipment createEquipment()
		throws MESException;

	/**
	 * 创建一个BOM查询器
	 */
	public BomFilter createBOMFilter()
		throws MESException;

	/**
	 * 创建一个BOM
	 * 
	 * @return
	 */
	public BillOfMaterials createBOM()
		throws MESException;

	/**
	 * 根据KEY获得BOM
	 */
	public BillOfMaterials getBillOfMaterialsByKey(long key)
		throws MESException;

	/**
	 * 创建一个车间查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public AreaFilter createAreaFilter()
		throws MESException;

	/**
	 * 创建一个用户查询器
	 * 
	 * @return UserFilter
	 * @throws MESException
	 */
	public UserFilter createUserFilter()
		throws MESException;

	/**
	 * 创建一个用户
	 * 
	 * @return User
	 * @throws MESException
	 */
	public User createUser()
		throws MESException;

	/**
	 * 根据key获取用户
	 * 
	 * @param key
	 * @return User
	 * @throws MESException
	 */
	public User getUserByKey(long key)
		throws MESException;

	/**
	 * 创建一个用户组选择器
	 * 
	 * @return UserGroup
	 * @throws MESException
	 */
	public UserGroupFilter createUserGroupFilter()
		throws MESException;

	/**
	 * 创建一个用户组
	 * 
	 * @return
	 * @throws MESException
	 */
	public UserGroup createUserGroup()
		throws MESException;

	/**
	 * 根据key查找用户组
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public UserGroup getUserGroupByKey(long key)
		throws MESException;

	/**
	 * 创建权限选择器
	 * 
	 * @return
	 * @throws MESException
	 */
	public AccessPrivilegeFilter createAccessPrivilegeFilter()
		throws MESException;

	/**
	 * 创建权限
	 * 
	 * @return
	 * @throws MESException
	 */
	public AccessPrivilege createAccessPrivilege()
		throws MESException;

	/**
	 * 根据key查找权限
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public AccessPrivilege getAccessPrivilegeByKey(long key)
		throws MESException;

	/**
	 * 根据KEY获得车间
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public Area getAreaByKey(long key)
		throws MESException;

	/**
	 * 根据Name获得车间
	 * 
	 * @param name
	 * @return
	 * @throws MESException
	 */
	public Area getAreaByName(String name)
		throws MESException;

	/**
	 * 创建一个车间
	 * 
	 * @return
	 * @throws MESException
	 */
	public Area createArea()
		throws MESException;

	/**
	 * 创建一个工厂查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public SiteFilter createSiteFilter()
		throws MESException;

	/**
	 * 根据KEY获得工厂
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public Site getSiteByKey(long key)
		throws MESException;

	/**
	 * 根据Name获得工厂
	 * 
	 * @param name
	 * @return
	 * @throws MESException
	 */
	public Site getSiteByName(String name)
		throws MESException;

	/**
	 * 创建一个工厂
	 * 
	 * @return
	 * @throws MESException
	 */
	public Site createSite()
		throws MESException;

	/**
	 * 创建一个工序查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public OperationFilter createOperationFilter()
		throws MESException;

	/**
	 * 根据KEY获得工序类型
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public Operation getOperationByKey(long key)
		throws MESException;
	
	/**
	 * 根据Name获得工序类型
	 * 
	 * @param name
	 * @return
	 * @throws MESException
	 */
	public Operation getOperationByName(String name)
		throws MESException;

	/**
	 * 创建一个工序类型
	 * 
	 * @return
	 * @throws MESException
	 */
	public Operation createOperation()
		throws MESException;

	/**
	 * 创建一个检查项目查询器
	 * 
	 * @return
	 */
	public TestDefinitionFilter createTestDefinitionFilter()
		throws MESException;

	/**
	 * 根据key获取检查项目
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public TestDefinition getTestDefinitionByKey(long key)
		throws MESException;

	/**
	 * 创建一个检查项目
	 * 
	 * @return
	 * @throws MESException
	 */
	public TestDefinition createTestDefinition()
		throws MESException;

	/**
	 * 创建一个list查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public ListFilter createListFilter()
		throws MESException;

	/**
	 * 创建自定义表查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public UTDefinitionFilter createaAtDefinitionFilter()
		throws MESException;

	/**
	 * 根据key获取自定义表
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public UTDefinition getAtDefinitionByKey(long key)
		throws MESException;

	/**
	 * 创建一个自定义表
	 * 
	 * @return UTDefinition
	 * @throws MESException
	 */
	public UTDefinition createatdAtDefinition()
		throws MESException;

	/**
	 * 根据名字获取codelist
	 * 
	 * @param listname
	 * @return
	 * @throws MESException
	 */
	public DsList getDsListByName(String listname)
		throws MESException;

	/**
	 * 创建一个自定义字段查询器
	 * 
	 * @return UTColumnDefinitionFilter
	 * @throws MESException
	 */
	public UTColumnDefinitionFilter createAtColumnDefinitionFilter()
		throws MESException;

	/**
	 * 创建一个输送工具查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public CarrierFilter createCarrierFilter()
		throws MESException;

	/**
	 * 根据KEY获得输送工具
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public Carrier getCarrierByKey(long key)
		throws MESException;

	/**
	 * 根据Name获得输送工具
	 * 
	 * @param name
	 * @return
	 * @throws MESException
	 */
	public Carrier getCarrierByName(String name)
		throws MESException;

	/**
	 * 创建一个输送工具
	 * 
	 * @return
	 * @throws MESException
	 */
	public Carrier createCarrier()
		throws MESException;

	/**
	 * 创建一个工艺路径查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public RouteFilter createRouteFilter()
		throws MESException;

	/**
	 * 根据KEY获得工艺路径
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public Route getRouteByKey(long key)
		throws MESException;

	/**
	 * 根据Name获得工艺路径
	 * 
	 * @param name
	 * @return
	 * @throws MESException
	 */
	public Route getRouteByName(String routeName)
		throws MESException;

	/**
	 * 创建一个工艺路径
	 * 
	 * @return
	 * @throws MESException
	 */
	public Route createRoute()
		throws MESException;

	/**
	 * 根据序列获得产品
	 * 
	 * @param Serial
	 * @return Unit
	 * @throws MESException
	 */
	public Unit getUnitBySerialNumber(String SerialNumber)
		throws MESException;

	/**
	 * 根据物料编号和版本号获得物料
	 * 
	 * @param partNumber
	 * @return Part
	 * @throws MESException
	 */
	public Part getPart(String partNumber, String partRevision)
		throws MESException;

	/**
	 * 创建一个质量数据采集查询器
	 * 
	 * @return DataCollectionSetFilter
	 * @throws MESException
	 */
	public DataCollectionSetFilter createDataCollectionSetFilter()
		throws MESException;

	/**
	 * 根据key获得质量数据采集
	 * 
	 * @param key
	 * @return DataCollectionSet
	 * @throws MESException
	 */
	public DataCollectionSet getDataCollectionSetByKey(long key)
		throws MESException;

	/**
	 * 创建一个工单查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public OrderFilter createOrderFilter()
		throws MESException;

	/**
	 * 根据KEY获得工单
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public Order getOrderByKey(long key)
		throws MESException;

	/**
	 * 创建一个工单
	 * 
	 * @return
	 * @throws MESException
	 */
	public Order createOrder()
		throws MESException;

	/**
	 * 得到FormManager
	 * 
	 * @return FormManager
	 * @throws MESException
	 */
	public FormManager getFormManager()
		throws MESException;

	/**
	 * 创建UDADefinition
	 * 
	 * @return UDADefinition
	 * @throws MESException
	 */
	public UDADefinition createUDADefinition(DUDADefinition dUDADefinition)
		throws MESException;
	
	
	public UDADefinitionFilter createUDADefinitionFilter() throws MESException;

	/**
	 * 创建一个包装箱查询器
	 * 
	 * @return BoxFilter
	 * @throws MESException
	 */
	public BoxFilter createBoxFilter()
		throws MESException;

	/**
	 * 根据名字获取包装箱
	 * 
	 * @param name
	 * @return
	 * @throws MESException
	 */
	public Box getBoxByName(String boxNumber)
		throws MESException;

	/**
	 * 创建一个包装箱
	 * 
	 * @return Box
	 * @throws MESException
	 */
	public Box createBox(String name)
		throws MESException;

	/**
	 * 创建一个排程查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public OrderItemFilter createOrderItemFilter()
		throws MESException;

	/**
	 * 创建一个排程
	 * 
	 * @return
	 * @throws MESException
	 */
	public OrderItem createOrderItem()
		throws MESException;

	/**
	 * 创建一个批次查询器
	 * 
	 * @return
	 * @throws MESException
	 */
	public LotFilter createLotFilter()
		throws MESException;

	/**
	 * 创建一个批次
	 * 
	 * @return
	 * @throws MESException
	 */
	public Lot createLot(Order order, String lotName)
		throws MESException;

	/**
	 * 根据NAME获得批次
	 * 
	 * @param name
	 * @return
	 * @throws MESException
	 */
	public Lot getLotByName(String name)
		throws MESException;

	/**
	 * 根据KEY获得批次
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public Lot getLotByKey(long key)
		throws MESException;

	/**
	 * 根据NAME获得工单
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public Order getOrderByName(String name)
		throws MESException;

	/**
	 * 根据KEY获得产品
	 * 
	 * @param key
	 * @return
	 * @throws MESException
	 */
	public Unit getUnitByKey(long key)
		throws MESException;

	public BillOfMaterials getBillOfMaterials(String bomName, String bomRevision)
		throws MESException;

	/**
	 * 创建一个产品过滤器
	 * 
	 * @return
	 * @throws MESException
	 */
	public UnitFilter createUnitFilter()
		throws MESException;

	/**
	 * 根据name获取DCS
	 * 
	 * @param name
	 * @return
	 * @throws MESException
	 */
	public DataCollectionSet getDataCollectionSetByName(String name)
		throws MESException;

	public AddOnFilter createAddOnFilter()
		throws MESException;

	public AddOn createAddOn()
		throws MESException;

	public User GetUserByName(String name)
		throws MESException;

	public ImageFilter createImageFilter()
		throws MESException;

	public Vector<String[]> getArrayDataFromActive(String sql)
		throws MESException;

	public UTHandler getAtHandler(String atDefinitionName)
		throws MESException;

	public UTRowFilter createAtRowFilter(String atDefinitionName)
		throws MESException;


	
	/**
	 * 创建一个Station
	 * 
	 * @return Station
	 * @throws MESException
	 */
	public Station createStation()
		throws MESException;
	/**
	 * 创建一个Station查询器
	 * 
	 * @return StationFilter
	 * @throws MESException
	 */
	public StationFilter createStationFilter()
		throws MESException;

	/**
	 * 根据KEY获得Station
	 * 
	 * @param key
	 * @return Station
	 * @throws MESException
	 */
	public Station getStationByKey(long key)
		throws MESException;

	/**
	 * 根据Name获得Station
	 * 
	 * @param name
	 * @return Station
	 * @throws MESException
	 */
	public Station getStationByName(String name)
		throws MESException;
	
	public UDADefinition getUdaDefinitionByType(short tyep) throws MESException;

	BillOfMaterials getBillOfMaterialsWithNoCache(String bomName,
			String bomRevision) throws MESException;

	Part getPartWithNoCache(String partNumber, String partRevision)
			throws MESException;
	
	public String getOldPassWord(long userKey) throws MESException;
	
	public void changePassWord(String userName, String oldPassWord, String passWord) throws MESException;
	
}
