package cn.ac.catarc.qj.function;

import com.mes.compatibility.client.ATHandler;
import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.ui.Time;

/**
 * @author 
 *
 */
public class SaveTrackedRecord extends PassStation implements FunctionConstants{
	/**
	 * 返回消息
	 */
	@Override
	public Message run(){
		String vin = (String)parameterMap.get(FunctionConstants.PARAMETER_TYPE_VIN);
		String station = (String)parameterMap.get(FunctionConstants.PARAMETER_TYPE_STATION);
		String stage = (String)parameterMap.get(FunctionConstants.PARAMETER_TYPE_STAGE);
		Time entryTime = (Time)parameterMap.get(FunctionConstants.PARAMETER_TYPE_ENTRYTIME);
		try {
			UTHandler atHandler = getFunctions().createATHandler("MES_PM_CarMove");
			UTRow atRow = atHandler.createATRow();
			atRow.setValue("vin", vin);
			atRow.setValue("station", station);
			atRow.setValue("stage", stage);
			atRow.setValue("entry_time", entryTime);
			Response r = atRow.save(getFunctions().getDBTime(), "", null);
			if(r.isOk()){
				return new Message();
			}
			else{
				return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】在工位【"+station+"】保存过站记录失败:"+r.getFirstErrorMessage());
			}
		}
		catch (MESException e) {
			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】在工位【"+station+"】保存过站记录异常:"+e.getMessage());
		}
	}
}