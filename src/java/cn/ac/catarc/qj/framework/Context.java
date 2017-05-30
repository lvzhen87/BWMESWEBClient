package cn.ac.catarc.qj.framework;

public class Context
{
	private static int maxRows = 1000;

	public static void setMaxRows(int maxRows)
	{
		Context.maxRows = maxRows;
	}

	public static int getMaxRows()
	{
		return maxRows;
	}

	private static boolean userTransactionEnabled = true;

	public static void enableUserTransaction(boolean b)
	{
		Context.userTransactionEnabled = b;
	}

	public static boolean isUserTransactionEnabled()
	{
		return userTransactionEnabled;
	}

	/**
	 * TODO Please provide description for the method
	 * 
	 * @return
	 */
	private static String immoBuildDataATName = "FDGEV_Interface_IMMO_Sale";

	public static String getImmoBuildDataATName()
	{
		return immoBuildDataATName;
	}

	public static void setImmoBuildDataATName(String immoBuildDataATName)
	{
		Context.immoBuildDataATName = immoBuildDataATName;
	}
	
	/**
	 * TODO Please provide description for the method
	 * 
	 * @return
	 */
	private static String materialAT = "FDGEV_Interface_Material";

	public static void setMaterialATName(String atName)
	{
		Context.materialAT = atName;
	}

	public static String getMaterialATName()
	{
		return materialAT;
	}

	/**
	 * TODO Please provide description for the method
	 * 
	 * @return
	 */
	private static String bomHeadAT = "FDGEV_Interface_VSNBOM_H";

	public static String getBOMHeadATName()
	{
		// TODO Auto-generated method stub
		return bomHeadAT;
	}

	public static void setBOMHeadATName(String atName)
	{
		Context.bomHeadAT = atName;
	}

	/**
	 * TODO Please provide description for the method
	 * 
	 * @return
	 */
	private static String bomItemAT = "FDGEV_Interface_VSNBOM";

	public static String getBOMItemATName()
	{
		// TODO Auto-generated method stub
		return bomItemAT;
	}

	public static void setBOMItemATName(String atName)
	{
		Context.bomItemAT = atName;
	}

	/**
	 * TODO Please provide description for the method
	 * 
	 * @return
	 */
	private static String planAT = "FDGEV_Interface_ERP_Order";

	public static String getPlanATName()
	{
		// TODO Auto-generated method stub
		return planAT;
	}

	public static void setPlanATName(String atName)
	{
		Context.planAT = atName;
	}

	private static String ifLogAT = "FDGEV_Interface_Log";

	public static String getInterfaceLogATName()
	{
		return ifLogAT;
	}

	public static void setIFLogATName(String atName)
	{
		Context.ifLogAT = atName;
	}

	private static String shiftPlanAT = "FDGEV_Interface_Portal_SP";

	public static void setShiftPlanATName(String atName)
	{
		Context.shiftPlanAT = atName;
	}

	public static String getShiftPlanATName()
	{
		return shiftPlanAT;
	}
	
	private static String plcATName = "FDGEV_PM_PLC";	
	public static void setPLCATName(String atName)
	{
		Context.plcATName = atName;
	}
	public static String getPLCATName()
	{
		return plcATName;
	}

	private static String carMoveAT = "FDGEV_Interface_Car_Move";

	public static void setCarMoveATName(String atName)
	{
		Context.carMoveAT = atName;
	}

	public static String getCarMoveATName()
	{
		return carMoveAT;
	}

	private static String carRobotCodeAT = "FDGEV_SYS_Carbody";

	private static void setCarRobotCodeATName(String atName)
	{
		Context.carRobotCodeAT = atName;
	}

	public static String getCarRobotCodeATName()
	{
		return carRobotCodeAT;
	}

	private static String assemblyCode = "FDGEV_Interface_AssemblePC";

	private static void setAssemblyCodeATName(String atName)
	{
		Context.assemblyCode = atName;
	}

	public static String getAssemblyCodeATName()
	{
		return assemblyCode;
	}

	private static String carBodyCodeATName = "FDGEV_Interface_TbCarbody";

	private static void setCarBodyCodeATName(String atName)
	{
		Context.carBodyCodeATName = atName;
	}

	public static String getCarBodyCodeATName()
	{
		return carBodyCodeATName;
	}

	private static String cocATName = "FDGEV_Interface_3C_COP";

	private static void setCOCATNmae(String atName)
	{
		Context.cocATName = atName;
	}

	public static String getCOCATName()
	{
		return cocATName;
	}

	private static String cocBreakpointATName = "FDGEV_SYS_BreakPointMain";

	private static void setCOCBreakpointATName(String atName)
	{
		Context.cocBreakpointATName = atName;
	}

	public static String getCOCBreakpointATName()
	{
		return cocBreakpointATName;
	}

	private static String companyInfoATName = "FDGEV_SYS_Company";

	private static void setCompanyInfoATName(String atName)
	{
		Context.companyInfoATName = atName;
	}

	public static String getCompanyInfoATName()
	{
		return companyInfoATName;
	}

	private static String engineATName = "FDGEV_SYS_Engine_Code";

	private static void setEngineATName(String atName)
	{
		Context.engineATName = atName;
	}

	public static String getEngineATName()
	{
		return engineATName;
	}

	private static String fuelATName = "FDGEV_SYS_Fuel";

	private static void setFuelATName(String atName)
	{
		Context.fuelATName = atName;
	}

	public static String getFuelATName()
	{
		return fuelATName;
	}

	private static String markModeATName = "FDGEV_Interface_MarkMode";

	private static void setMarkModeATName(String atName)
	{
		Context.markModeATName = atName;
	}

	public static String getMarkModeATName()
	{
		return markModeATName;
	}

	private static String paintColorATName = "FDGEV_SYS_Color";

	private static void setPaintColarATName(String atName)
	{
		Context.paintColorATName = atName;
	}

	public static String getPaintColorATName()
	{
		return paintColorATName;
	}

	private static String regularOrderATName = "FDGEV_SYS_RegularOrder";

	private static void setRegularOrderATName(String atName)
	{
		Context.regularOrderATName = atName;
	}

	public static String getRegularOrderATName()
	{
		return regularOrderATName;
	}

	private static String planWorkShop = "车身车间";

	public static String getPlanWorkShop()
	{
		return planWorkShop;
	}

	public static void setPlanWorkShop(String workShop)
	{
		Context.planWorkShop = workShop;
	}

	private static String PlanGA = "总装";

	public static String getPlanGA()
	{
		return PlanGA;
	}

	public static void setPlanGA(String planGA)
	{
		PlanGA = planGA;
	}

	public static String getPlanBD()
	{
		return PlanBD;
	}

	public static void setPlanBD(String planBD)
	{
		PlanBD = planBD;
	}

	private static String PlanBD = "车身";

	private static String GAWorkShop = "总装车间";

	public static String getGAWorkShop()
	{
		return GAWorkShop;
	}

	public static void setGAWorkShop(String workShop)
	{
		Context.GAWorkShop = workShop;
	}
	
	private static String GAWorkShopCode = "B";
	
	/**
	 * TODO Please provide description for the method
	 * 
	 * @return
	 */
	private static String regularCodeAT = "FDGEV_SYS_RegularOrder";

	public static String getRegularCodeATName()
	{
		// TODO Auto-generated method stub
		return regularCodeAT;
	}
	public static void setRegularCodeATName(String atName)
	{
		Context.regularCodeAT = atName;
	}
	
	private static String specialOrderCodeAT = "FDGEV_SYS_SpecialOrder";
	public static String getSpecialOrderCodeATName()
	{
		// TODO Auto-generated method stub
		return specialOrderCodeAT;
	}
	public static void setSpecialOrderATName(String atName)
	{
		Context.specialOrderCodeAT = atName;
	}

	private static String colorCodeAT = "FDGEV_SYS_Color";

	public static String getColorCodeATName()
	{
		// TODO Auto-generated method stub
		return colorCodeAT;
	}

	public static void setColorCodeATName(String atName)
	{
		Context.colorCodeAT = atName;
	}

	/**
	 * TODO Please provide description for the method
	 * 
	 * @return
	 */
	private static String rpoAT = "FDGEV_Interface_RPO";

	public static String getRPOATName()
	{
		// TODO Auto-generated method stub
		return rpoAT;
	}

	public static void setPlanRPOATName(String atName)
	{
		Context.planRpoAT = atName;
	}
	
	private static String planRpoAT = "FDGEV_PP_Order_RPO";

	public static String getPlanRPOATName()
	{
		// TODO Auto-generated method stub
		return planRpoAT;
	}

	public static void setRPOATName(String atName)
	{
		Context.rpoAT = atName;
	}

	public static String STANDARD_TYPE = "标准型";

	public static String AIRCONDITION_CN = "空调";

	public static String VEHICLE_MOVE_LOGISTICSID_HARDCODE = "ANJI";

	public static String getLogisticName()
	{
		return VEHICLE_MOVE_LOGISTICSID_HARDCODE;
	}
	public static void setLogisticName(String name)
	{
		VEHICLE_MOVE_LOGISTICSID_HARDCODE = name;
	}
	
	public static String NORMAL_ORDER = "常规车";
	public static String CUSTOMER_ORDER = "订单车";
	public static String NORMAL_ORDER_BATCH_NO = "A0000";
	
	private static String PortalATName = "FDGEV_Interface_Portal_SP";
	public static void setPortalATName(String portalATName)
	{
		PortalATName = portalATName;
	}

	public static String getPortalATName()
	{
		return PortalATName;
	}
		
	private static String logFilePath = "C:\\SOS\\";
	public static void setLogFilePath(String path)
	{
		logFilePath = path;
	}
	
	public static String getLogFilePath()
	{
		return logFilePath;
	}
	
	private static String xmlFIlePath = "D:\\XMLFile\\";
	public static void setXMLFilePath(String path)
	{
		xmlFIlePath = path;
	}
	public static String getXMLFilePath()
	{
		return xmlFIlePath;
	}
	
	private static String companyID = "";
	public static String getCompanyID()
	{
		return companyID;
	}
	public static void setCompanyID(String id)
	{
		companyID = id;
	}
	
	private static String compayCode = "WDS0";
	public static String getCompayCode()
	{
		return compayCode;
	}
	
	public static void setCompayCode(String code)
	{
		compayCode = code;
	}
	
	public static void setPlantID(String plantID)
	{
		Context.plantID = plantID;
	}

	public static String getPlantID()
	{
		return plantID;
	}

	private static String plantID = "8000";
	private static String plantName = "宝骏基地整车工厂";
	public static void setPlantName(String plantName)
	{
		Context.plantName = plantName;
	}

	public static String getPlantName()
	{
		return plantName;
	}
	
	private static String atWorkShiftName = "FDGEV_SYS_Work_Shift";
	public static String getWorkShiftATName()
	{
		return Context.atWorkShiftName;
	}
	public static void setWorkShiftATName(String atName)
	{
		Context.atWorkShiftName = atName;
	}
	
	private static String vinWeightATName = "Interface_VINWeight";
	public static String getVinWeightATName()
	{
		return Context.vinWeightATName;
	}
	public static void setVinWeightATName(String atName)
	{
		Context.vinWeightATName = atName;
	}
	
	private static String transactionLogATName = "FDGEV_SYS_Transaction_Log";
	public static void setTransactionLogATName(String atName)
	{
		Context.transactionLogATName = atName;
	}
	public static String getTransactionLogATName()
	{
		return Context.transactionLogATName;
	}

	public static void setGAWorkShopCode(String gAWorkShopCode)
	{
		GAWorkShopCode = gAWorkShopCode;
	}

	public static String getGAWorkShopCode()
	{
		return GAWorkShopCode;
	}
	
	private static String certificationATName = "FDGEV_Interface_CertInfo";
	public static void setCertificationATName(String atName)
	{
		Context.certificationATName = atName;
	}
	public static String getCertificationATName()
	{
		return Context.certificationATName;
	}
	
	private static String exportCertificationATName = "FDGEV_PR_Export_Certificate";
	public static void setExportCertificationATName(String atName)
	{
		Context.exportCertificationATName = atName;
	}
	public static String getExportCertificationATName()
	{
		return Context.exportCertificationATName;
	}

	/**
	 * TODO Please provide description for the method
	 * @return
	 */
	private static String fuelLabelATName = "FDGEV_PR_Fuellabel";
	public static String getFuelLabelATName()
	{
		return fuelLabelATName;
	}
	public static void setFuelLabelATName(String  atName)
	{
		fuelLabelATName = atName;
	}

	/**
	 * TODO Please provide description for the method
	 * @return
	 */
	private static String exhaustATName = "FDGEV_Interface_Exhaust";
	public static String getExhaustATName()
	{ 
		return exhaustATName;
	}
	
	public static void setExhaustATName(String atName)
	{ 
		exhaustATName = atName;
	}

	/**
	 * TODO Please provide description for the method
	 * @return
	 */
	//private static String ecuBuildDataATName = "FDGEV_PM_ECU_BuildDataInfo"; 
	private static String ecuBuildDataATName = "FDGEV_Interface_ECU_Sale";
	public static String getECUBuildDataATName()
	{
		return ecuBuildDataATName;
	}
	
	public static void setECUBuildDataATName(String atName)
	{
		ecuBuildDataATName = atName;
	}

	/**
	 * TODO Please provide description for the method
	 * @return
	 */
	private static String cardGartATName = "FDGEV_Interface_CardGart";
	public static String getCardGartATName()
	{ 
		return cardGartATName;
	}
	public static void setCardGartATName(String atName)
	{ 
		cardGartATName = atName;
	}
	
	private static String cardGartDependentATName = "FDGEV_ICG_TRACE";
	public static String getCardGartDependentATName()
	{
		return cardGartDependentATName;
	}
	public static void setCardGartDependentATName(String atName)
	{
		cardGartDependentATName = atName;
	}
	
	private static String materialTraceATName = "FDGEV_QM_MaterialTrack";
	public static String getMaterialTraceATName()
	{
		return materialTraceATName;
	}
	public static void setMaterialTraceATName(String atName)
	{
		materialTraceATName = atName;
	}
	
	private static String CarGGATName = "FDGEV_SYS_CarGG";
	public static String getCarGGATName()
	{
		return CarGGATName;
	}
	public static void setCarGGATName(String atName)
	{
		CarGGATName = atName;
	}
	 
	private static String COCBreakPointATName = "FDGEV_SYS_BreakPointMain";
	public static String getCOCBreakPointATName()
	{
		return COCBreakPointATName;
	}
	public static void setCOCBreakPointATName(String atName)
	{
		COCBreakPointATName = atName;
	}
	
	private static String TireATName = "FDGEV_SYS_Tire";
	public static String getTireATName()
	{
		return TireATName;
	}
	public static void setTireATName(String atName)
	{
		TireATName = atName;
	}
	
	private static String transmissionATName = "FDGEV_SYS_Transmission";
	public static String getTransmissionATName()
	{
		return transmissionATName;
	}
	public static void setTransmissionATName(String atName)
	{
		transmissionATName = atName;
	}

	/**
	 * TODO Please provide description for the method
	 * @return
	 */
	private static String carMoveLESATName = "FDGEV_IF_LES";
	public static String getCarMoveLESATName()
	{
		return carMoveLESATName;
	}
	public static void setCarMoveLESATName(String atName)
	{
		carMoveLESATName = atName;
	}
	
	private static String carMoveERPATName = "FDGEV_IF_ERP";
	public static String getCarMoveERPATName()
	{
		return carMoveERPATName;
	}
	public static void setCarMoveERPATName(String atName)
	{
		carMoveERPATName = atName;
	}
	
	private static String carMoveAVIATName = "FDGEV_IF_AVI";
	public static String getCarMoveAVIATName()
	{
		return carMoveAVIATName;
	}
	public static void setCarMoveAVIATName(String atName)
	{
		carMoveAVIATName = atName;
	}
	
	private static String carMovePRTATName = "FDGEV_IF_PRT";
	public static String getCarMovePRTATName()
	{
		return carMovePRTATName;
	}
	public static void setCarMovePRTATName(String atName)
	{
		carMovePRTATName = atName;
	}
	
	private static String carMoveODSATName = "FDGEV_IF_ODS";
	public static String getCarMoveODSATName()
	{
		return carMoveODSATName;
	}
	public static void setCarMoveODSATName(String atName)
	{
		carMoveODSATName = atName;
	}
	
	private static String carMoveGOVATName = "FDGEV_IF_GOV";
	public static String getCarMoveGOVATName()
	{
		return carMoveGOVATName;
	}
	public static void setCarMoveGOVATName(String atName)
	{
		carMoveGOVATName = atName;
	}
	
	private static String masterOrderATName = "FDGEV_PP_Order";
	public static String getMasterOrderATName()
	{
		return masterOrderATName;
	}
	public static void setMasterOrderATName(String atName)
	{
		masterOrderATName = atName;
	}
	
	private static String equipmentParameterATName = "FDGEV_SYS_Equipment_Config";
	public static String getEquipmentParameterATName()
	{
		return equipmentParameterATName;
	}
	public static void setEquipmentParameterATName(String atName)
	{
		equipmentParameterATName = atName;
	}
	private static String numberPunchEquipmentType = "车身刻印";
	public static String getNumberPunchEquipmentType()
	{
		return numberPunchEquipmentType;
	}
	public static void setNumberPunchEquipmentType(String atName)
	{
		numberPunchEquipmentType = atName;
	}
	
	public static final String BD_PRODUCTIONLINE_PREFIX = "车身总拼" ;
}
