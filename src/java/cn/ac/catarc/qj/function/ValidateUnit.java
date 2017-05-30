package cn.ac.catarc.qj.function;

import com.mes.compatibility.client.ATRow;

/**
 * @author 
 *
 */
public class ValidateUnit extends PassStation implements FunctionConstants{
	/**
	 * 返回消息
	 */
	@Override
	public Message run(){
		String vin = (String)parameterMap.get(FunctionConstants.PARAMETER_TYPE_VIN);
//		ATRow order = getOrderByVin(vin);
//		if(null==order){
//			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】订单不存在");
//		}
		ATRow order = (ATRow)parameterMap.get(FunctionConstants.PARAMETER_TYPE_ORDER);
		String station = (String)parameterMap.get(FunctionConstants.PARAMETER_TYPE_STATION);
		String stage = (String)parameterMap.get(FunctionConstants.PARAMETER_TYPE_STAGE);
		String status = convert2String(order.getValue("status"));
		if(FunctionConstants.ORDER_STATUS_CERTIFICATE.equals(status)){
			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】已打印合格证");
		}
		else if(FunctionConstants.ORDER_STATUS_SCRAP.equals(status)){
			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】已报废");
		}
		else if(FunctionConstants.STAGE_WB_ON.equals(stage) && FunctionConstants.ORDER_STATUS_ONLINE.equals(status)){
			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】在工位【"+station+"】状态不应为已上线");
		}
		else if(!FunctionConstants.STAGE_WB_ON.equals(stage) && !FunctionConstants.ORDER_STATUS_ONLINE.equals(status)){
			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】在工位【"+station+"】状态应为已上线");
		}
		else{
			return new Message();
		}
	}
}