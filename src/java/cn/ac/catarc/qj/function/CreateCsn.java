package cn.ac.catarc.qj.function;

import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UserSequence;
import com.mes.webclient.util.StringUtil;
import com.mes.compatibility.client.UserSequenceValue;

/**
 * @author 
 *
 */
public class CreateCsn extends PassStation implements FunctionConstants{
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
		try {
			String csnName = getCsnNameByStage(stage);
			int csnNumber = getCsnNumber(csnName);
			String csn = generateCsn(stage, getFunctions().getDBTime().getYear(), csnNumber);
			if(StringUtil.isNull(csn)){
				return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】在工位【"+station+"】生成CSN号为空");
			}
			return saveCsn(order, stage, csn);
		} catch (Exception e) {
			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+vin+"】在工位【"+station+"】生成CSN号异常:"+e.getMessage());
		}
	};
	
	public static String getCsnNameByStage(String stage) throws Exception{
		if(FunctionConstants.STAGE_ORDER_ADJUST.equals(stage)){
			return "ZZ";
		}
		else if(FunctionConstants.STAGE_WB_ON.equals(stage)){
			return "WB";
		}
		else if(FunctionConstants.STAGE_PB_ON.equals(stage)){
			return "PB";
		}
		else if(FunctionConstants.STAGE_AB_ON.equals(stage)){
			return "AB";
		}
		else{
			throw new Exception("stage【"+stage+"】没有对应的CSN");
		}
	}
	
	public int getCsnNumber(String csnName) throws Exception{
		int csnNumber = 1;
		UserSequence us = getFunctions().getUserSequenceByName("CSN_"+csnName);
		if(null==us){
			us = getFunctions().createUserSequence("CSN_"+csnName);
			us.setIncrementValue(1);
			us.setInitialValue(1);
			us.setMaxValue(999999);
			us.save(null);
		}
		else{
			csnNumber = ((UserSequenceValue)us.getNextValue().getResult()).getValue();
		}
		return csnNumber;
	}
	
	public static String generateCsn(String stage, int year, int csnNumber) throws Exception{
		String prefix = getCsnNameByStage(stage);
		String csn = "000000"+csnNumber;
		csn = csn.substring(csn.length()-6);
		return prefix + year + csn;
	}
	
	private Message saveCsn(ATRow order, String stage, String csn){
		if(FunctionConstants.STAGE_ORDER_ADJUST.equals(stage)){
			order.setValue("csn", csn);
		}
		else if(FunctionConstants.STAGE_WB_ON.equals(stage)){
			order.setValue("wb_csn", csn);
		}
		else if(FunctionConstants.STAGE_PB_ON.equals(stage)){
			order.setValue("pb_csn", csn);
		}
		else if(FunctionConstants.STAGE_AB_ON.equals(stage)){
			order.setValue("ab_csn", csn);
		}
		else{
			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+order.getValue("vin")+"】在stage【"+stage+"】不应生成CSN号");
		}
		try {
			Response r = order.save(getFunctions().getDBTime(), "", null);
			if(r.isOk()){
				return new Message();
			}
			else{
				return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+order.getValue("vin")+"】在stage【"+stage+"】保存CSN号【"+csn+"】失败:"+r.getFirstErrorMessage());
			}
		} catch (MESException e) {
			return new Message(FunctionConstants.MESSAGE_TYPE_ERROR, "VIN【"+order.getValue("vin")+"】在stage【"+stage+"】保存CSN号【"+csn+"】异常:"+e.getMessage());
		}
	}
}