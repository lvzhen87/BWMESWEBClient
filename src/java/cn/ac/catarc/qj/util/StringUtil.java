package cn.ac.catarc.qj.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {
	/**
	 * 重写对象的toString方法，解决对象为空时报错的问题，需要toString的地方调用这个方法
	 * @param o
	 * @return String
	 */
	public static String toString(Object o){
		return o==null?"":o.toString();
	}
	/**
	 * 转换计划状态
	 * @param code
	 * @return
	 */
	public static String transferPlanStatus(String code){
		Map<String, String> map = new HashMap<String, String>();
		map.put("-1", "下载");
		map.put("0", "分解");
		map.put("1", "排序");
		return toString(map.get(code));
	}
	/**
	 * 转换order状态
	 * @param code
	 * @return
	 */
	public static String transferOrderStatus(String code){
		Map<String, String> map = new HashMap<String, String>();
		map.put("", "分解");
		map.put("-1", "分解");
		map.put("0", "锁定");
		map.put("1", "发布");
		return toString(map.get(code));
	}
	public static boolean isNull(String str){
		if(str == null || str.trim().isEmpty())
			return true;
		return false;
	}
	
	public static boolean isNotNull(String str){
		return !isNull(str);
	}
}
