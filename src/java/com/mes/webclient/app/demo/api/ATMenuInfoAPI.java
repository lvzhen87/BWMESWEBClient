package com.mes.webclient.app.demo.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.hibernate.dialect.function.VarArgsSQLFunction;

import com.mes.compatibility.client.DsList;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.functions.box.returnBoxHistory;
import com.mes.compatibility.functions.utility.logErrorMessage;
import com.mes.webclient.service.impl.BaseService;
import com.mes.webclient.service.impl.IMService;



public class ATMenuInfoAPI extends BaseService {
	
	private int timeout = 30000;
	IMService imService;
	
	//得到用户组，查询到相关keys
	public List getMenukeys(long group_key) throws MESException{
		
		List menulist  = new ArrayList<>();
	
	    System.out.println("GroupKey:"+ group_key);
		String sql = "select menukey_S from UD_ATAuthority where userGroupkey_S="+group_key;
		Vector<String[]> vec = getFunctions().getArrayDataFromActive(sql, timeout);
		
//		String[] arr;
//		String[] menu;
		if(vec!=null && vec.size()>0)
		{
			String menuKeys = vec.get(0)[0];
			String[] menuKeysArr = menuKeys.split(",");
			menulist = new ArrayList(Arrays.asList(menuKeysArr));
		}
// 		for(int i=0;vec!=null && i<vec.size();i++){
//		    arr  = vec.get(i);
//			menu = arr[0].split(",");
//			
//		}
		return menulist; 
	}
	
	
	public List<Map<String, Object>> getMenukeys(ArrayList menulist) throws MESException{
		
//		System.out.println("我是接收到的List大小:"+ menulist.size());
		/**
		 * atr_key 本身编号
		 * menuName_S 菜单名称
		 * menuSrc_S  菜单连接
		 * parent_S   0为父节点，1为子节点
		 * pid_S      0为最顶层父节点，是谁的子节点
		 * menuIcon_S 菜单图标
		 */
		
		//创建List集合存放查到的值
	    List<Map<String, Object>> data = new ArrayList<>();
		
		
		for(int i=0; menulist!=null && i<menulist.size(); i++){
			//得到第一个menuKey
			String menukey = (String)menulist.get(i);
			int atr_key =Integer.valueOf(menukey);
//		    System.out.println("第"+i+"个MenuKey:"+ atr_key);
			//组合查询的sql语句
			String sql = "select atr_key,menuName_S,menuIcon_S,menuSrc_S,parent_S,pid_S from UD_ATMenu where atr_key ="+atr_key ;
		    //执行sql查询数据
			Vector<String[]> vec = getFunctions().getArrayDataFromActive(sql, timeout);
			//将查询到的值遍历出来放到集合中
	    	String[] arr = vec.get(0);
	    	Map<String,Object> item = new HashMap<String, Object>();
	    	item.put("selfId", arr[0]);
	    	item.put("menuName", arr[1]);
	    	item.put("menuIcon", arr[2]);
	    	item.put("menuSrc", arr[3]);
	    	item.put("isParent", arr[4]);
	    	item.put("pid", arr[5]);
	        data.add(item);
	    }
		
	    return data;		 
	}
	
public Map<String, Object> getMenuData(int key) throws MESException{
		
		/**
		 * atr_key 本身编号
		 * menuName_S 菜单名称
		 * menuSrc_S  菜单连接
		 * parent_S   0为父节点，1为子节点
		 * pid_S      0为最顶层父节点，是谁的子节点
		 */
			//组合查询的sql语句
			String sql = "select atr_key,menuName_S,menuIcon_S,menuSrc_S,parent_S,pid_S from UD_ATMenu where atr_key ="+key ;
		    //执行sql查询数据
			Vector<String[]>   vec =   getFunctions().getArrayDataFromActive(sql, timeout);
			//将查询到的值遍历出来放到集合中
	    	String[] arr = vec.get(0);
	    	Map<String,Object> item = new HashMap();
	     	item.put("selfId", arr[0]);
	    	item.put("menuName", arr[1]);
	    	item.put("menuIcon", arr[2]);
	    	item.put("menuSrc", arr[3]);
	    	item.put("isParent", arr[4]);
	    	item.put("pid", arr[5]);
		
	    return item;		 
	}

public List<Map<String, Object>> getParentKey()throws MESException{
    List<Map<String, Object>> data = new ArrayList<>();
	String parentKeySql = " select atr_key,menuName_S,menuIcon_S,menuSrc_S,parent_S,pid_S  from [dbo].[UD_ATMenu]  where parent_S = '1' and pid_S = '0' ";
	Vector<String[]> vec = getFunctions().getArrayDataFromActive(parentKeySql, timeout);
	for(int i=0; vec!=null && i<vec.size(); i++){
    	String[] arr = vec.get(i);
    	Map<String,Object> item = new HashMap<String, Object>();
    	item.put("selfId", arr[0]);
    	item.put("menuName", arr[1]);
    	item.put("menuIcon", arr[2]);
    	item.put("menuSrc", arr[3]);
    	item.put("isParent", arr[4]);
    	item.put("pid", arr[5]);
        data.add(item);
    }
    return data;		
	 
}
public List getSons(String fatherId) throws MESException{
	
	   System.out.println("我是接收到的fatherId:"+ fatherId);
	    //创建List集合存放查到的值
        List data = new ArrayList<>();
		//组合查询的sql语句
		String sql = "select atr_key from UD_ATMenu where pid_S ="+ fatherId ;
	    //执行sql查询数据
		Vector<String[]>   vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		//将查询到的值遍历出来放到集合中
		if(vec.size()>0){
			for(int i=0;i<vec.size();i++){
			 	    String menuKey = vec.get(i)[0];
			 		HashMap  dataMap = (HashMap) this.getMenuData(Integer.parseInt(menuKey));
			 		data.add(dataMap);
			 }
			
			System.out.println("size:"+data.size());
			 return data;
		}
		else{
			return null;
		}	       		 
}

public List getSons(int fatherId, ArrayList allMenuKeys) throws MESException{
	
	System.out.println("我是接收到的fatherId:"+ fatherId);

	
	    //创建List集合存放查到的值
        List data = new ArrayList<>();
    
		//组合查询的sql语句
		String sql = "select atr_key from UD_ATMenu where pid_S ="+ fatherId ;
	    //执行sql查询数据
		Vector<String[]>   vec =   getFunctions().getArrayDataFromActive(sql, timeout);
		
		//将查询到的值遍历出来放到集合中
		if(vec.size()>0){
			for(int i=0;i<vec.size();i++){
			 	String menuKey = vec.get(i)[0];
			 	if(allMenuKeys.contains(menuKey))
			 	{
			 		HashMap  dataMap = (HashMap) this.getMenuData(Integer.parseInt(menuKey));
			 		data.add(dataMap);
			 	}
			}
			System.out.println("size:"+data.size());
			 return data;
		}
		else{
			return null;
		}	       		 
}
	
	// API 获取菜单名称List
	public List<String> getMenuList() throws MESException
	{
		DsList list = getFunctions().getList(
			"MenuList");
		return list.getItems();
	}

	// API 获取菜单图标List
	public List<String> getMenuIconList() throws MESException
	{
		DsList list = getFunctions().getList(
			"MenuIconList");
		list.refresh();
		return list.getItems();
	}
	
	//获取部门组别的所有Name 
	public List<String> getDepartmentName() throws MESException {
		String sql = "select d_name_S from UD_ATDepartment";
		Vector<String[]> vec = getFunctions().getArrayDataFromActive(sql, timeout);
		
		List<String> list = new ArrayList<>();
    	for(int i=0;i<vec.size();i++){
			String dep = vec.get(i)[0];
			list.add(dep);
		}
    	return list;
	}
	
	
	//获取所有站点
	public List<String> getStationName() throws MESException{
		List<String> list = new ArrayList<>();
	    list = (List<String>) getFunctions().getAllStations();
		return list;
	}
}


