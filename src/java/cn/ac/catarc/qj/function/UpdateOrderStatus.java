package cn.ac.catarc.qj.function;

import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;

/**
 * @author 
 *
 */
public class UpdateOrderStatus extends PassStation implements FunctionConstants{
	/**
	 * 返回消息
	 */
	@Override
	public Message run(){
		String vin = (String)parameterMap.get(FunctionConstants.PARAMETER_TYPE_VIN);
		ATRow order = (ATRow)parameterMap.get(FunctionConstants.PARAMETER_TYPE_ORDER);
		String station = (String)parameterMap.get(FunctionConstants.PARAMETER_TYPE_STATION);
		String stage = (String)parameterMap.get(FunctionConstants.PARAMETER_TYPE_STAGE);
		if(FunctionConstants.STAGE_WB_ON.equals(stage)){
			order.setValue("status", Integer.parseInt(FunctionConstants.ORDER_STATUS_ONLINE));
		}
		else if(FunctionConstants.STAGE_CERTIFICATE_PRINT.equals(stage)){
			order.setValue("status", Integer.parseInt(FunctionConstants.ORDER_STATUS_CERTIFICATE));
		}
		else{
			return new Message();
		}
		try {
			Response r = order.save(getFunctions().getDBTime(), "", null);
			if(r.isOk()){
				return new Message();
			}
			else{
				return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】在工位【"+station+"】更新订单状态失败:"+r.getFirstErrorMessage());
			}
		}
		catch (MESException e) {
			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】在工位【"+station+"】更新订单状态异常:"+e.getMessage());
		}
	}
}