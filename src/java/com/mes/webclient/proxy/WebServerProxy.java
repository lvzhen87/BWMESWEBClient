package com.mes.webclient.proxy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mes.compatibility.client.AccessPrivilege;
import com.mes.compatibility.client.AccessPrivilegeFilter;
import com.mes.compatibility.client.Account;
import com.mes.compatibility.client.AddOn;
import com.mes.compatibility.client.Area;
import com.mes.compatibility.client.AreaFilter;
import com.mes.compatibility.client.BillOfMaterials;
import com.mes.compatibility.client.BomFilter;
import com.mes.compatibility.client.BomItem;
import com.mes.compatibility.client.Box;
import com.mes.compatibility.client.BoxFilter;
import com.mes.compatibility.client.Carrier;
import com.mes.compatibility.client.CarrierFilter;
import com.mes.compatibility.client.DataCollectionSet;
import com.mes.compatibility.client.DataCollectionSetFilter;
import com.mes.compatibility.client.DsImage;
import com.mes.compatibility.client.DsList;
import com.mes.compatibility.client.DsMessages;
import com.mes.compatibility.client.Equipment;
import com.mes.compatibility.client.EquipmentClass;
import com.mes.compatibility.client.EquipmentClassFilter;
import com.mes.compatibility.client.EquipmentFilter;
import com.mes.compatibility.client.EventSheetHolder;
import com.mes.compatibility.client.EventSheetHolderFilter;
import com.mes.compatibility.client.Filter;
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
import com.mes.compatibility.client.PartClass;
import com.mes.compatibility.client.PartFilter;
import com.mes.compatibility.client.ProductionLine;
import com.mes.compatibility.client.ProductionLineFilter;
import com.mes.compatibility.client.Report;
import com.mes.compatibility.client.ReportDataDefinition;
import com.mes.compatibility.client.ReportDataDefinitionFilter;
import com.mes.compatibility.client.ReportDesign;
import com.mes.compatibility.client.ReportDesignFilter;
import com.mes.compatibility.client.Route;
import com.mes.compatibility.client.RouteFilter;
import com.mes.compatibility.client.ServerInfo;
import com.mes.compatibility.client.Shift;
import com.mes.compatibility.client.ShiftFilter;
import com.mes.compatibility.client.Site;
import com.mes.compatibility.client.SiteFilter;
import com.mes.compatibility.client.Station;
import com.mes.compatibility.client.StationFilter;
import com.mes.compatibility.client.TestDefinition;
import com.mes.compatibility.client.TestDefinitionFilter;
import com.mes.compatibility.client.TestInstance;
import com.mes.compatibility.client.TestInstanceFilter;
import com.mes.compatibility.client.UDADefinitionFilter;
import com.mes.compatibility.client.UTColumnDefinitionFilter;
import com.mes.compatibility.client.UTDefinition;
import com.mes.compatibility.client.UTDefinitionFilter;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.Unit;
import com.mes.compatibility.client.UnitFilter;
import com.mes.compatibility.client.User;
import com.mes.compatibility.client.UserFilter;
import com.mes.compatibility.client.UserGroup;
import com.mes.compatibility.client.UserGroupFilter;
import com.mes.compatibility.client.UserSequence;
import com.mes.compatibility.client.UserSequenceFilter;
import com.mes.compatibility.client.WorkCenter;
import com.mes.compatibility.client.WorkCenterFilter;
import com.mes.compatibility.client.WorkInstruction;
import com.mes.compatibility.client.WorkSchedule;
import com.mes.compatibility.manager.BomManager;
import com.mes.compatibility.manager.LotManager;
import com.mes.compatibility.manager.PartManager;
import com.mes.compatibility.manager.ServerImpl;
import com.mes.compatibility.ui.Time;
import com.mes.eventmanager.sheet.EventSheet;
import com.mes.shopflow.common.constants.ITestCollectionLevels;
import com.mes.shopflow.common.exceptions.MESProxyException;
import com.mes.shopflow.proxies.ProxyFactory;
import com.mes.transactiongrouping.UserTransaction;
import com.mes.webclient.filter.im.PDSessionFilter;
import com.mes.webclient.server.WebObjectFactory;

/**
 * 与JBoss服务器交互的代理类
 * 
 * @author Nemo, 2014-1-20 上午11:17:03
 */
public class WebServerProxy
{
	private static String IIOP_URL;

	private static String HTTP_URL;

	private static String APP_SERVER_NAME;

	private static WebObjectFactory  serverImpl;

	public static Map<String, User> loginList = new HashMap<String, User>();
	public static Map<String, String> oldPassword = new HashMap<String, String>();

	protected static Logger logger = Logger.getLogger(WebServerProxy.class);

	public static String AP_SERVER_NAME = null;

	static
	{
		Properties prop = new Properties();
//		InputStream in = WebServerProxy.class.getResourceAsStream("/SYSTEM-CONFIG.properties");
		 String path=System.getProperty("catalina.base")+"/conf/SYSTEM-CONFIG.properties";

		try
		{
			 InputStream in=new FileInputStream(path);
			prop.load(in);
			IIOP_URL = prop.getProperty("IIOP_URL");
			System.out.println(IIOP_URL);
			HTTP_URL = prop.getProperty("HTTP_URL");
			APP_SERVER_NAME = prop.getProperty("APP_SERVER_NAME");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public WebServerProxy(ServerImpl serverImpl)
	{
		this.serverImpl = (WebObjectFactory) serverImpl;
	}

	public static WebServerProxy getInstance()
		throws MESException
	{
		return new WebServerProxy(getCurrentServerImpl());
	}

	public WebObjectFactory getServerImpl()
	{	
		return this.serverImpl;
	}

	/**
	 * 登录到服务器
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @throws MESProxyException
	 */
	public static WebObjectFactory login(String username, String password)
		throws MESException, MESProxyException
	{
		// computerName = "10.18.113.90";
		System.out.println("用户名为：" + username);
		System.setProperty(
			ProxyFactory.J2EE_VENDOR_SYSTEM_PROPERTY, APP_SERVER_NAME);
		WebObjectFactory webObjectFactory = new WebObjectFactory(new ServerInfo(IIOP_URL, HTTP_URL));

		// if (proxySessionMap.size() > 1000)
		// {
		// throw new Exception("在线数量大于1000，请稍后登录");
		// }
		User user = webObjectFactory.login(
			username, password);
		webObjectFactory.fetchLoggedInUser();
		serverImpl=webObjectFactory;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes()).getRequest();
		request.getSession().setAttribute(
			"user", webObjectFactory);
		PDSessionFilter.loginList.put(username, webObjectFactory);
		oldPassword.put(username, password);
		System.out.println(webObjectFactory.isLoggedIn());

		return webObjectFactory;
	}

	/**
	 * 登出服务器
	 */
	public static void logout()
		throws Exception
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes()).getRequest();
		ServerImpl serverImpl = (WebObjectFactory) request.getSession().getAttribute(
			"user");
		if (serverImpl != null)
		{
			String userName = serverImpl.getLoggedUser().getName();
			serverImpl.logout();
			loginList.remove(userName);
		}
	}

	public static User getCurrentUser()
	{
		try
		{
			return getCurrentServerImpl().getLoggedUser();
		}
		catch (MESException e)
		{
			return null;
		}
	}

	public static WebObjectFactory getCurrentServerImpl()
	{
		try{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
			.getRequestAttributes()).getRequest();
		return (WebObjectFactory) request.getSession().getAttribute(
			"user");}
		catch(Exception e){
				return serverImpl;
			}
	}

	/**
	 * 根据批次名称获得批次
	 * 
	 * @param name
	 *            批次名称
	 * @return 批次
	 */
	public Lot getLotByName(String name)
		throws MESException
	{
		LotManager lotManager = getServerImpl().getLotManager();
		return (Lot) lotManager.getObject(name);
	}

	public LotFilter createLotFilter()
	{
		return new LotFilter(getServerImpl());
	}

	public WorkInstruction createWorkInstruction(String name)
	{
		return getServerImpl().getWorkInstructionManager().createWorkInstruction(
			name);
	}

	public UTRowFilter createATRowFilter(String name)
	{
		return new UTRowFilter(getServerImpl(), name);
	}

	public Vector<UTRow> getFilteredATRows(UTRowFilter atRowFilter, boolean isDetials)
		throws MESException
	{
		return getServerImpl().getATManager(
			getATDefinition(atRowFilter.getUTDefinitionName())).getATRows(
			atRowFilter, isDetials);
	}

	public int getFilteredATRowsCount(UTRowFilter atRowFilter)
		throws MESException
	{
		return getServerImpl().getATManager(
			getATDefinition(atRowFilter.getUTDefinitionName())).getATRowCount(
			atRowFilter);
	}

	public Carrier getCarrierByKey(long key)
		throws MESException
	{
		return (Carrier) getServerImpl().getCarrierManager().getObject(
			key);
	}

	public Carrier getCarrierByName(String carrierName)
		throws MESException
	{
		return (Carrier) getServerImpl().getCarrierManager().getObject(
			carrierName);
	}

	public Time getDBTime()
		throws MESException
	{
		return getServerImpl().getUtilityManager().getDBTime();
	}

	public Long getFilteredLotCount(LotFilter lotFilter)
		throws MESException
	{
		return getServerImpl().getLotManager().getCount(
			lotFilter);
	}

	public Vector<Lot> getFilteredLots(LotFilter lotFilter)
		throws MESException
	{
		return (Vector<Lot>) getServerImpl().getLotManager().getObjects(
			lotFilter);
	}

	public String getMessage(String messagePackName, String id)
		throws MESException
	{
		DsMessages dsMessage = (DsMessages) getServerImpl().getMessagesManager().getObject(
			messagePackName);
		return dsMessage.getValue(id);
	}

	public UTDefinition getATDefinition(String atDefinitonName)
		throws MESException
	{
		return (UTDefinition) getServerImpl().getATDefinitionManager().getObject(
			atDefinitonName);
	}

	public Order getWorkOrder(String orderNumber)
		throws MESException
	{
		return (Order) getServerImpl().getOrderManager().getObject(
			orderNumber);
	}

	public Vector<Order> getFilteredWorkOrders(OrderFilter orderFilter)
		throws MESException
	{
		return (Vector<Order>) getServerImpl().getOrderManager().getObjects(
			orderFilter);
	}

	public Vector<Unit> getFilteredUnit(UnitFilter unitFilter)
		throws MESException
	{
		return (Vector<Unit>) getServerImpl().getUnitManager().getObjects(
			unitFilter);
	}

	public Vector<TestInstance> getFilteredTestInstance(TestInstanceFilter testInstanceFilter)
		throws MESException
	{
		return (Vector<TestInstance>) getServerImpl().getTestDefinitionManager().getTestInstances(
			testInstanceFilter);
	}

	public DsList getList(String listName)
		throws MESException
	{
		return (DsList) getServerImpl().getListManager().getObject(
			listName);
	}

	public Equipment getEquipment(String equipmentName)
		throws MESException
	{
		return (Equipment) getServerImpl().getEquipmentManager().getObject(
			equipmentName);
	}

	public Equipment getEquipment(long key)
		throws MESException
	{
		return (Equipment) getServerImpl().getEquipmentManager().getObject(
			key);
	}

	public AccessPrivilege getAccessPrivilege(long key)
		throws MESException
	{
		return (AccessPrivilege) getServerImpl().getAccessPrivilegeManager().getObject(
			key);
	}

	public AccessPrivilege getAccessPrivilege(String name)
		throws MESException
	{
		return (AccessPrivilege) getServerImpl().getAccessPrivilegeManager().getObject(
			name);
	}

	public PartClass getPartClass(long key)
		throws MESException
	{
		return (PartClass) getServerImpl().getPartClassManager().getObject(
			key);
	}

	public PartClass getPartClass(String name)
		throws MESException
	{
		return (PartClass) getServerImpl().getPartClassManager().getObject(
			name);
	}

	public ProductionLine getProductionLine(long key)
		throws MESException
	{
		return (ProductionLine) getServerImpl().getProductionLineManager().getObject(
			key);
	}

	public ProductionLine getProductionLine(String productionLineName)
		throws MESException
	{
		return (ProductionLine) getServerImpl().getProductionLineManager().getObject(
			productionLineName);
	}

	public Shift getShift(long key)
		throws MESException
	{
		return (Shift) getServerImpl().getShiftManager().getObject(
			key);
	}

	public Shift getShift(String name)
		throws MESException
	{
		return (Shift) getServerImpl().getShiftManager().getObject(
			name);
	}

	public User getUser(long key)
		throws MESException
	{
		return (User) getServerImpl().getUserManager().getObject(
			key);
	}

	public UserFilter createUserFilter()
		throws MESException
	{
		return new UserFilter(getServerImpl());
	}

	public UserGroup getUserGroup(long key)
		throws MESException
	{
		return (UserGroup) getServerImpl().getUserGroupManager().getObject(
			key);
	}

	public User getUser(String name)
		throws MESException
	{
		return (User) getServerImpl().getUserManager().getObject(
			name);
	}

	public UserGroup getUserGroup(String name)
		throws MESException
	{
		return (UserGroup) getServerImpl().getUserGroupManager().getObject(
			name);
	}

	public Vector<Equipment> getFilteredEquipments(EquipmentFilter equipmentFilter)
		throws MESException
	{
		return (Vector<Equipment>) getServerImpl().getEquipmentManager().getObjects(
			equipmentFilter);
	}

	public Vector<Carrier> getFilteredCarriers(CarrierFilter carrierFilter)
		throws MESException
	{
		return (Vector<Carrier>) getServerImpl().getCarrierManager().getObjects(
			carrierFilter);
	}

	public Vector<ProductionLine> getFilteredProductionLines(
		ProductionLineFilter productionLineFilter)
		throws MESException
	{
		return (Vector<ProductionLine>) getServerImpl().getProductionLineManager().getObjects(
			productionLineFilter);
	}

	public Vector<Part> getFilteredParts(PartFilter partFilter)
		throws MESException
	{
		return (Vector<Part>) getServerImpl().getPartManager().getObjects(
			partFilter);
	}

	public long getFilteredPartsCount(PartFilter partFilter)
		throws MESException
	{
		return getServerImpl().getPartManager().getCount(
			partFilter);
	}

	public Part getPart(long key)
		throws MESException
	{
		return (Part) getServerImpl().getPartManager().getObject(
			key);
	}

	public Part getPart(String partNumber, String partRevision)
		throws MESException
	{
		return getServerImpl().getPartManager().getPart(
			partNumber, partRevision);
	}
	public Part getPartWithNoCache(String partNumber, String partRevision)
			throws MESException
		{
			serverImpl.getSiteCache().getPartCache().clear();
			return getServerImpl().getPartManager().getPart(
				partNumber, partRevision);
		}

	public Vector<UTRow> getFilteredATRows(UTRowFilter atRowFilter)
		throws MESException
	{
		UTDefinition atDefinition = (UTDefinition) getServerImpl().getATDefinitionManager()
			.getObject(
				atRowFilter.getUTDefinitionName());
		return getServerImpl().getATManager(
			atDefinition).getATRows(
			atRowFilter, false);
	}

	public Vector<Route> getFilteredRoutes(RouteFilter routeFilter)
		throws MESException
	{
		return (Vector<Route>) getServerImpl().getRouteManager().getObjects(
			routeFilter);
	}

	public Vector<Shift> getFilteredShifts(ShiftFilter shiftFilter)
		throws MESException
	{
		return (Vector<Shift>) getServerImpl().getShiftManager().getObjects(
			shiftFilter);
	}

	public EquipmentFilter createEquipmentFilter()
	{
		return new EquipmentFilter(getServerImpl());
	}

	public PartFilter createPartFilter()
	{
		return new PartFilter(getServerImpl());
	}

	public Route getRoute(String routeName)
		throws MESException
	{
		return (Route) getServerImpl().getRouteManager().getObject(
			routeName);
	}

	public PartClass createPartClass(String partClassName)
	{
		return getServerImpl().getPartClassManager().createPartClass(
			partClassName);
	}

	public User createUser(String userName)
	{
		return getServerImpl().getUserManager().createUser(
			userName);
	}

	public Carrier createCarrier(String carrierName)
	{
		return getServerImpl().getCarrierManager().createCarrier(
			carrierName);
	}

	public UserGroup createUserGroup(String userGroupName)
	{
		return getServerImpl().getUserGroupManager().createUserGroup(
			userGroupName);
	}

	public AccessPrivilege createAccessPrivilege(String accessPrivilegeName)
	{
		return getServerImpl().getAccessPrivilegeManager().createAccessPrivilege(
			accessPrivilegeName);
	}

	public UTHandler createATHandler(String atDefinitionName)
		throws MESException
	{
		UTDefinition atDefinition = (UTDefinition) getServerImpl().getATDefinitionManager()
			.getObject(
				atDefinitionName);
		UTHandler atHandler = null;

		if (atDefinition != null && !atDefinition.isDependent())
		{
			atHandler = ManagerSupport.createATHandler(atDefinition);
		}
		return atHandler;
	}

	public BomFilter createBOMFilter()
	{
		return new BomFilter(getServerImpl());
	}

	public ProductionLine createProductionLine(String productionLineName)
	{
		return getServerImpl().getProductionLineManager().createProductionLine(
			productionLineName);
	}

	public Equipment createEquipment(String equipmentName)
	{
		return getServerImpl().getEquipmentManager().createEquipment(
			equipmentName);
	}

	public Site createSite(String siteName)
	{
		return getServerImpl().getSiteManager().createSite(
			siteName);
	}

	public Area createArea(String name)
	{
		return getServerImpl().getAreaManager().createArea(
			name);
	}

	public BillOfMaterials createBillOfMaterial(String name, String revision)
	{
		return getServerImpl().getBomManager().createBillOfMaterials(
			name, revision);
	}

	public BomItem createBomItem()
	{
		return ManagerSupport.createBomItem(getServerImpl());
	}

	public WorkSchedule createWorkSchedule(String name)
	{
		return getServerImpl().getWorkScheduleManager().createWorkSchedule(
			name);
	}

	public Shift createShift(String name)
	{
		return getServerImpl().getShiftManager().createShift(
			name);
	}

	public Route getRoute(long key)
		throws MESException
	{
		return (Route) getServerImpl().getRouteManager().getObject(
			key);
	}

	public Route createRoute(String name)
	{
		return getServerImpl().getRouteManager().createRoute(
			name);
	}

	public Operation createOperation(String operation)
	{
		return getServerImpl().getOperationManager().createOperation(
			operation);
	}

	public WorkCenter createWorkCenter(String name)
	{
		return getServerImpl().getWorkCenterManager().createWorkCenter(
			name);
	}

	public Part createPart(String partNumber, String partRevision)
	{

		return getServerImpl().getPartManager().createPart(
			partNumber, partRevision);
	}

	public WorkCenter getWorkCenter(long key)
		throws MESException
	{
		return (WorkCenter) getServerImpl().getWorkCenterManager().getObject(
			key);
	}

	public WorkCenter getWorkCenter(String workCenterName)
		throws MESException
	{
		return (WorkCenter) getServerImpl().getWorkCenterManager().getObject(
			workCenterName);
	}

	public TestDefinition createTestDefinition(String name)
	{
		TestDefinition testDefinition = getServerImpl().getTestDefinitionManager()
			.createTestDefinition(
				name);
		testDefinition.setLevel(ITestCollectionLevels.UNIT_LEVEL);
		return testDefinition;
	}

	public DsList createList(String name)
	{
		return getServerImpl().getListManager().createList(
			name);
	}

	public UserSequence getUserSequenceByName(String sequenceName)
		throws MESException
	{
		return (UserSequence) getServerImpl().getUserSequenceManager().getObject(
			sequenceName);
	}

	public UserSequence createUserSequence(String sequenceName)
	{
		return (UserSequence) getServerImpl().getUserSequenceManager().createUserSequence(
			sequenceName);
	}

	public UserSequenceFilter createUserSequenceFilter()
	{
		return new UserSequenceFilter(getServerImpl());
	}

	public Vector getFilteredUserSequences(UserSequenceFilter userSequenceFilter)
		throws MESException
	{
		return (Vector) getServerImpl().getUserSequenceManager().getObjects(
			userSequenceFilter);
	}

	public void removeUserSequence(Long userSequenceKey)
	{
		getServerImpl().getUserSequenceManager().removeUserSequenceByKey(
			userSequenceKey);
	}

	public EquipmentClassFilter createEquipmentClassFilter()
	{
		return new EquipmentClassFilter(getServerImpl());
	}

	public Unit getUnitBySerialNumber(String serialNumber)
		throws MESException
	{
		UnitFilter f = new UnitFilter(getServerImpl());
		f.forSerialNumberEqualTo(serialNumber);
		// f.addSearchBy(
		// IUnitFilterAttributes.OLD_LIFECYCLE,
		// IFilterComparisonOperators.EQUAL_TO, 0);
		f.orderByCreationTime(false);

		List l = getServerImpl().getUnitManager().getObjects(
			f);
		if (l.size() > 0)
		{
			return (Unit) l.get(0);
		}
		else
		{
			return null;
		}
	}

	public UserTransaction getUserTransaction()
		throws Throwable

	{
		UserTransaction userTransaction = new UserTransaction(serverImpl);
		userTransaction.setTransactionTimeout(180);
		return userTransaction;
	}

	public UnitFilter createUnitFilter()
	{
		return new UnitFilter(getServerImpl());
	}

	public TestInstanceFilter createTestInstanceFilter()
	{
		return new TestInstanceFilter(getServerImpl());
	}

	public Unit getUnit(long key)
		throws MESException
	{
		return (Unit) getServerImpl().getUnitManager().getObject(
			key);
	}

	public OrderFilter createOrderFilter()
	{
		return new OrderFilter(getServerImpl());
	}

	public List<Order> getfilteredOrder(OrderFilter orderFilter)
		throws MESException
	{
		return getServerImpl().getOrderManager().getObjects(
			orderFilter);
	}

	public ListFilter createListFilter()
	{
		return new ListFilter(getServerImpl());
	}

	public List<DsList> getFilteredLists(ListFilter listFilter)
		throws MESException
	{
		return getServerImpl().getListManager().getObjects(
			listFilter);
	}

	public TestDefinitionFilter createTestDefinitionFilter()
	{
		return new TestDefinitionFilter(getServerImpl());
	}

	public List<TestDefinition> getFilteredtestDefinition(TestDefinitionFilter testDefinitionFilter)
		throws MESException
	{
		return getServerImpl().getTestDefinitionManager().getObjects(
			testDefinitionFilter);
	}

	public Lot createLot(Order order, String lotName)
	{
		Lot lot = getServerImpl().getLotManager().createLot(
			lotName);
		ManagerSupport.setupNewLot(
			lot, order);
		return lot;
	}

	public Order createOrder(String orderName)
	{
		return getServerImpl().getOrderManager().createOrder(
			orderName);
	}

	public OrderItem createOrderItem()
	{
		return ManagerSupport.createOrderItem(getServerImpl());
	}

	public Vector<String[]> getArrayDataFromActive(String sql, int timeout)
		throws MESException
	{
		return getServerImpl().getUtilityManager().getArrayDataFromActive(
			sql, timeout);
	}

	public Vector<String[]> getArrayDataFromHistorical(String sql, int timeout)
		throws MESException
	{
		return getServerImpl().getUtilityManager().getArrayDataFromHistorical(
			sql, timeout);
	}

	public int[] executeStatements(String[] sqlStatements)
		throws MESException
	{
		return getServerImpl().getUtilityManager().executeStatements(
			sqlStatements, null);
	}

	public ShiftFilter createShiftFilter()
	{
		return new ShiftFilter(getServerImpl());
	}

	public Account createAccount(String name)
	{
		return getServerImpl().getAccountManager().createAccount(
			name);
	}

	public EquipmentClass createEquipmentClass(String name)
	{
		return getServerImpl().getEquipmentClassManager().createEquipmentClass(
			name);
	}

	public UTRow createATRow(String atDefinitionName)
		throws Exception
	{
		UTDefinition atDefinition = getATDefinition(atDefinitionName);
		if (atDefinition == null)
		{
			throw new Exception("无法找到AT表" + atDefinitionName);
		}
		return atDefinition.createUTRow_();
	}

	public Account getAccount(String accountName)
		throws MESException
	{
		return (Account) getServerImpl().getAccountManager().getObject(
			accountName);
	}

	public Box getBox(String boxNumber)
		throws MESException
	{
		return (Box) getServerImpl().getBoxManager().getObject(
			boxNumber);
	}

	public synchronized static void clearCache()
	{
		Collection<User> users = loginList.values();
		for (User user : users)
		{	
				user.getServerImpl().getSiteCache().clearAllCaches();
			
		}
	}

	public WorkCenterFilter createWorkCenterFilter()
	{
		return new WorkCenterFilter(getServerImpl());
	}

	public ProductionLineFilter createProductionLineFilter()
	{
		return new ProductionLineFilter(getServerImpl());
	}

	public BillOfMaterials getBillOfMaterial(long key)
		throws MESException
	{
		return getServerImpl().getBomManager().getBillOfMaterials(
			key);
	}

	public UserGroupFilter createUserGroupFilter()
	{

		return new UserGroupFilter(getServerImpl());
	}

	public AccessPrivilegeFilter createAccessPrivilegeFilter()
	{
		return new AccessPrivilegeFilter(getServerImpl());
	}

	public AreaFilter createAreaFilter()
	{
		return new AreaFilter(getServerImpl());
	}

	public Area getArea(long key)
		throws MESException
	{
		return (Area) getServerImpl().getAreaManager().getObject(
			key);
	}

	public Area getArea(String name)
		throws MESException
	{
		return (Area) getServerImpl().getAreaManager().getObject(
			name);
	}

	public SiteFilter createSiteFilter()
	{
		return new SiteFilter(getServerImpl());
	}

	public Site getSite(long key)
		throws MESException
	{
		return (Site) getServerImpl().getSiteManager().getObject(
			key);
	}

	public Site getSite(String name)
		throws MESException
	{
		return (Site) getServerImpl().getSiteManager().getObject(
			name);
	}

	public OperationFilter createOperationFilter()
	{
		return new OperationFilter(getServerImpl());
	}

	public Operation getOperation(long key)
		throws MESException
	{
		return (Operation) getServerImpl().getOperationManager().getObject(
			key);
	}
	
	public Operation getOperation(String name)
		throws MESException
	{
		return (Operation) getServerImpl().getOperationManager().getObject(
			name);
	}

	public TestDefinition getTestDefinitionByKey(long key)
		throws MESException
	{

		return (TestDefinition) getServerImpl().getTestDefinitionManager().getObject(
			key);
	}

	public UTDefinitionFilter createAtDefinitionFilter()
	{

		return new UTDefinitionFilter(getServerImpl());
	}

	public UTDefinition createAtDefinition()
	{

		return getServerImpl().getATDefinitionManager().create(
			"atdefinition");
	}

	public UTDefinition getAtDefinition(long key)
		throws MESException
	{

		return (UTDefinition) getServerImpl().getATDefinitionManager().getObject(
			key);
	}

	public UTColumnDefinitionFilter createaAtColumnDefinitionFilter()
		throws MESException
	{

		return new UTColumnDefinitionFilter(getServerImpl());
	}

	public CarrierFilter createCarrierFilter()
	{
		return new CarrierFilter(getServerImpl());
	}

	public Carrier getCarrier(long key)
		throws MESException
	{
		return (Carrier) getServerImpl().getCarrierManager().getObject(
			key);
	}

	public RouteFilter createRouteFilter()
	{
		return new RouteFilter(getServerImpl());
	}

	public DataCollectionSetFilter createDataCollectionSetFilter()
	{
		return new DataCollectionSetFilter(getServerImpl());

	}

	public DataCollectionSet getDataCollectionSetByKey(long key)
		throws MESException
	{

		return (DataCollectionSet) getServerImpl().getDcsManager().getObject(
			key);
	}

	public BoxFilter createBoxFilter()
	{

		return new BoxFilter(getServerImpl());
	}

	public Order getOrderByKey(long key)
		throws MESException
	{

		return (Order) getServerImpl().getOrderManager().getObject(
			key);
	}

	public OrderItemFilter createOrderItemFilter()
	{
		return new OrderItemFilter(getServerImpl());
	}

	public Order getOrderByName(String name)
		throws MESException
	{

		return (Order) getServerImpl().getOrderManager().getObject(
			name);
	}

	public Lot getLotByKey(long key)
		throws MESException
	{
		return (Lot) getServerImpl().getLotManager().getObject(
			key);
	}

	public Box createBox(String name)
	{

		return getServerImpl().getBoxManager().createBox(
			name);
	}

	public BillOfMaterials getBillOfMaterial(String bomName, String bomRevision)
		throws MESException
	{
		return getServerImpl().getBomManager().getBillOfMaterials(
			bomName, bomRevision);
	}
	public BillOfMaterials getBillOfMaterialWithNoCache(String bomName, String bomRevision)
			throws MESException
		{
		getServerImpl().getSiteCache().getBomCache().clear();
		 
			return getServerImpl().getBomManager().getBillOfMaterials(
				bomName, bomRevision);
		}
	public DataCollectionSet getDataCollectionSetByName(String name)
		throws MESException

	{
		return (DataCollectionSet) getServerImpl().getDcsManager().getObject(
			name);
	}

	public Unit getUnitByKey(long key)
		throws MESException
	{
		return (Unit) getServerImpl().getUnitManager().getObject(
			key);
	}

	public EventSheetHolderFilter createEventSheetHolderFilter()
	{
		return new EventSheetHolderFilter(getServerImpl());
	}

	public EventSheetHolder createEventSheetHolder()
	{
		EventSheetHolder esh = getServerImpl().getEventSheetManager().createEventSheetHolder(
			"eventsheet");
		EventSheet eventSheet = new EventSheet("eventsheet");
		esh.setEventSheet(eventSheet.toByteArray());
		return esh;
	}

	public EventSheetHolder getEventSheetHolder(long key)
		throws MESException
	{
		return (EventSheetHolder) getServerImpl().getEventSheetManager().getObject(
			key);
	}

	public AddOn createAddOn()
	{

		return serverImpl.getAddOnManager().createAddOn(
			"AddOn");

	}

	public UDADefinitionFilter createUDADefinitionFilter()
		throws MESException
	{
		return new UDADefinitionFilter(getServerImpl());
	}

	public DsImage createImage(String name)
		throws MESException
	{
		return getServerImpl().getImageManager().createImage(
			name);

	}

	public DsImage getImage(String name)
		throws MESException
	{
		return (DsImage) getServerImpl().getImageManager().getObject(
			name);

	}

	public ImageFilter createImageFilter()
		throws MESException
	{
		return new ImageFilter(getServerImpl());
	}

	public ReportDataDefinition createReportDataDefinition(String reportDataDefinitionName,
		String reportDataDefinitionRevision)
		throws MESException
	{
		return getServerImpl().getReportDataDefinitionManager().createReportDataDefinition(
			reportDataDefinitionName, reportDataDefinitionRevision);
	}

	public ReportDataDefinitionFilter createReportDataDefinitionFilter()
		throws MESException
	{
		return new ReportDataDefinitionFilter(getServerImpl());
	}

	public ReportDesign createReportDesign(String designName, String designRevision)
		throws MESException
	{
		return getServerImpl().getReportDesignManager().createReportDesign(
			designName, designRevision);
	}

	public ReportDesignFilter createReportDesignFilter()
		throws MESException
	{
		return new ReportDesignFilter(getServerImpl());
	}

	public Report createReport(String designName, String designRevision)
		throws MESException
	{
		ReportDesign rptDesign = getServerImpl().getReportDesignManager().getReportDesign(
			designName, designRevision);
		if (rptDesign == null)
		{
			try
			{
				throw new com.mes.shopflow.common.exceptions.MESException("报表对象【" + designName
					+ "】版本【" + designRevision + "】不存在");
			}
			catch (com.mes.shopflow.common.exceptions.MESException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return getServerImpl().getReportManager().createReport(
			rptDesign);
	}
	
	public Station createStation(String name)
	{
		return getServerImpl().getStationManager().createStation(
			name);
	}


	public StationFilter createStationFilter()
	{
		return new StationFilter(getServerImpl());
	}

	public Station getStation(long key)
		throws MESException
	{
		return (Station) getServerImpl().getStationManager().getObject(
			key);
	}
	
	public Station getStation(String name)
		throws MESException
	{
		return (Station) getServerImpl().getStationManager().getObject(
			name);
	}
	
	public Vector<Station> getFilteredStations(StationFilter stationFilter)
		throws MESException
	{
		return  (Vector<Station>) getServerImpl().getStationManager().getObjects((stationFilter));
	}

	public void changePassWord(String userName, String oldPassWord, String passWord)
		throws MESException
	{
		getServerImpl().changePassword(
			userName, oldPassWord, passWord, null, null);
	}
	
	public Object getAllStations()
		throws MESException
	{
		return  getServerImpl().getStationManager().getObjects(((Filter)null));
	}
}
