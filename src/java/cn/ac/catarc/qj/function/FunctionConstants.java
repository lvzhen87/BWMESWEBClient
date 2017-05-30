package cn.ac.catarc.qj.function;

public interface FunctionConstants{
	//////////////////////////////////////////////////////////////
	/**
	 * 运行结果：OK
	 */
	String FUNCTION_RUN_RESULT_OK = "OK";
	
	/**
	 * 运行结果：NG
	 */
	String FUNCTION_RUN_RESULT_NG = "NG";

	//////////////////////////////////////////////////////////////
	/**
	 * 消息级别：普通
	 */
	String MESSAGE_LEVEL_INFO = "INFO";
	
	/**
	 * 消息级别：错误
	 */
	String MESSAGE_LEVEL_ERROR = "ERROR";

	//////////////////////////////////////////////////////////////
	/**
	 * 消息类型：错误
	 */
	String MESSAGE_TYPE_ERROR = "错误";
	
	/**
	 * 消息类型：警告
	 */
	String MESSAGE_TYPE_WARNING = "警告";
	
	/**
	 * 消息类型：忽略
	 */
	String MESSAGE_TYPE_IGNORE = "忽略";

	//////////////////////////////////////////////////////////////
	/**
	 * 参数类型：VIN
	 */
	String PARAMETER_TYPE_VIN = "vin";
	
	/**
	 * 参数类型：ORDER
	 */
	String PARAMETER_TYPE_ORDER = "order";
	
	/**
	 * 参数类型：工位
	 */
	String PARAMETER_TYPE_STATION = "station";
	
	/**
	 * 参数类型：stage
	 */
	String PARAMETER_TYPE_STAGE = "stage";
	
	/**
	 * 参数类型：过站时间
	 */
	String PARAMETER_TYPE_ENTRYTIME = "entryTime";
	//////////////////////////////////////////////////////////////
	
	/**
	 * 订单状态：已上线
	 */
	String ORDER_STATUS_ONLINE = "5";
	
	/**
	 * 订单状态：已打印合格证
	 */
	String ORDER_STATUS_CERTIFICATE = "8";
	
	/**
	 * 订单状态：已报废
	 */
	String ORDER_STATUS_SCRAP = "9";
	//////////////////////////////////////////////////////////////
	
	/**
	 * stage:订单发布
	 */
	String STAGE_ORDER_ADJUST = "0010";
	
	/**
	 * stage:焊接上线
	 */
	String STAGE_WB_ON = "1010";
	
	/**
	 * stage:涂装上线
	 */
	String STAGE_PB_ON = "3010";
	
	/**
	 * stage:总装上线
	 */
	String STAGE_AB_ON = "5010";
	
	/**
	 * stage:合格证打印
	 */
	String STAGE_CERTIFICATE_PRINT = "8000";
}