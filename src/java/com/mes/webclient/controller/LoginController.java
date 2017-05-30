/*
 * @(#) LoginController.java 2016-7-1 上午11:55:56
 *
 * Copyright 2016 CIMIP, Inc. All rights reserved.
 * CIMIP PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.mes.webclient.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mes.compatibility.client.User;
import com.mes.compatibility.client.UserGroup;
import com.mes.webclient.app.demo.api.ATMenuInfoAPI;
import com.mes.webclient.constants.LoginProxy;
import com.mes.webclient.controller.vo.LoginUserVO;
import com.mes.webclient.filter.im.PDSessionFilter;
import com.mes.webclient.proxy.WebServerProxy;
import com.mes.webclient.server.WebObjectFactory;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;



 @Controller("loginController")
 @RequestMapping("/login.sp")
public class LoginController extends BaseController
{
	 
	 @Autowired
     IIMService imService;
	 ATMenuInfoAPI menuAPI = new ATMenuInfoAPI();
	 
	 @RequestMapping(params = "toLoginMainPage")
	 public String toMainPage()
	 {
		 
		 return "login";
	 }
	 
	 @RequestMapping(params = "toLogin")
	 public String toLogin(LoginUserVO loginUserVO,Model model,HttpServletRequest req)
	 {
		 try
		{
			 String userName = loginUserVO.getUserName();
			 String password = loginUserVO.getPassword();
			if(StringUtil.isNull(userName) || StringUtil.isNull(password))
			{
				throw new Exception("用户名或密码不允许为空");
			}
			WebServerProxy.login(loginUserVO.getUserName(), loginUserVO.getPassword());
			model.addAttribute("useName", WebServerProxy.getCurrentUser().getName());
			HttpSession session= req.getSession();
			session.setAttribute("loginUser",WebServerProxy.getCurrentUser().getName());
//			LoginProxy.login(userName, password,model, req);
			//判断用户身份，得到该权限组的对应菜单
			User user = imService.GetUserByName(userName);
			List<UserGroup> userGroups = user.getUserGroups();
			ArrayList<String> allUserGroupNameList = new ArrayList<>();
			for (int i = 0; userGroups!=null && i < userGroups.size(); i++)
			{
				UserGroup userGroup = (UserGroup) user.getUserGroups().get(
					i);
				String userGroupName = userGroup.getName();
				allUserGroupNameList.add(userGroupName);
			}

//		    username.equals("admin")
			/*if(!allUserGroupNameList.contains("CMESAdmin") ){
				model.addAttribute("isAdmin", false);
				  //遍历当前用户组,获取相关Keys
				
				ArrayList<String> allMenuKeys = new ArrayList<String>();//所有用户组去除重复后的keys
				ArrayList pList = new ArrayList<>();//父菜单集合
				HashMap allMaps = new HashMap<>();//子菜单Map
			
				ArrayList allUserGroup = new ArrayList<>();
				for (int i = 0; userGroups != null && i < userGroups.size(); i++)
				{
					UserGroup userGroup = (UserGroup) user.getUserGroups().get(
						i);
					long group_key = userGroup.getKey();
					List menuKeys = menuAPI.getMenukeys(group_key);
					for (int j = 0; menuKeys != null && j < menuKeys.size(); j++)
					{
						String key = (String) menuKeys.get(j);
						if (!allMenuKeys.contains(key))
						{
							allMenuKeys.add(key);
						}
					}
				}
			    //查询所有keys对应的菜单data
			    List<Map<String, Object>> detailData = menuAPI.getMenukeys(allMenuKeys);
			    

				
//				List childData = new ArrayList(); //Children data 
				  
				for(int i=0;detailData!=null && i<detailData.size();i++)
				{
				   HashMap dataMap = (HashMap) detailData.get(i);
			       if(dataMap.get("isParent").equals("1") && dataMap.get("pid").equals("0"))
			       {
						int parentKey = Integer.valueOf((dataMap.get("selfId").toString()));
						pList.add(dataMap);
						allMaps.put(
							dataMap.get("selfId"), menuAPI.getSons(parentKey, allMenuKeys));
//						childData.add(menuAPI.getSons(parentKey));
			       }
				}
			     
			    model.addAttribute("pList", pList);
				model.addAttribute("allMaps", allMaps);

			}else {
				model.addAttribute("isAdmin", true);
				ArrayList<String> allMenuKeys = new ArrayList<String>();//所有用户组去除重复后的keys
				ArrayList pList = new ArrayList<>();//父菜单集合
				HashMap allMaps = new HashMap<>();//子菜单Map
			    
				
				List<Map<String, Object>> detailData = menuAPI.getParentKey();
				for(int i=0;detailData!=null && i<detailData.size();i++)
				{
					   Map<String, Object> dataMap = (Map<String, Object>) detailData.get(i);
			       
						String parentKey =   dataMap.get("selfId") == null ? "":dataMap.get("selfId").toString() ;
						pList.add(dataMap);
						allMaps.put(parentKey , menuAPI.getSons(parentKey));
				}
			     
			    model.addAttribute("pList", pList);
				model.addAttribute("allMaps", allMaps);
			}*/

		}
		 
		 
		catch (Exception e)
		{
			logger.error(e);
			if(e.getMessage().equals("用户名或密码不允许为空"))
			{
				model.addAttribute("errorMsg", "请先登录");
				
			}
			else{
				model.addAttribute("errorMsg", e.toString());
			}
			return "login";
		}
		 return "index";
	 }
	 @RequestMapping(params = "loginOut")
	 public String loginOut(Model model,HttpServletRequest req)
	 {
		 try {
			HttpSession session = req.getSession(false);
			if (session != null) {
				session.removeAttribute("loginUser");
				WebObjectFactory fc=(WebObjectFactory) session.getAttribute("user");
				String name="";
				if (fc!=null) {
					name=fc.getLoggedUser().getName();
				}
				session.removeAttribute("user");
				PDSessionFilter.loginList.remove(name);
				session.invalidate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "login";
	 }
}
