package cn.ac.catarc.qj.function;

import java.util.HashMap;

import com.mes.webclient.service.impl.BaseService;

public abstract class PassStation extends BaseService{
	
	/**
	 * 运行参数
	 */
	public HashMap<String, Object> parameterMap;

	public HashMap<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(HashMap<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	/**
	 * 返回消息
	 */
	public abstract Message run();
	
//	public final ATRow getOrderByVin(String vin){
//		try {
//			ATRowFilter filter = getFunctions().createATRowFilter("Car");
//			filter.forColumnNameEqualTo("vin", vin);
//			Vector<ATRow> vectorData = filter.exec();
//			if(vectorData.size()>0){
//				return vectorData.get(0);
//			}
//			else{
//				return null;
//			}
//		} catch (MESException e) {
//			return null;
//		}
//	}
	public static final String convert2String(Object obj){
		if(null==obj){
			return "";
		}
		return String.valueOf(obj);
	}
}