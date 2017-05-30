package com.mes.webclient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes.compatibility.client.ATRow;
import com.mes.compatibility.client.DeAnzaForm;
import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.Response;
import com.mes.compatibility.client.UTHandler;
import com.mes.compatibility.client.UTRow;
import com.mes.compatibility.client.UTRowFilter;
import com.mes.compatibility.client.User;
import com.mes.compatibility.manager.FormManager;
import com.mes.webclient.app.demo.api.ATMenuInfoAPI;
import com.mes.webclient.constants.IDateFormat;
import com.mes.webclient.controller.vo.ATMenuVO;
import com.mes.webclient.controller.vo.UserVO;
import com.mes.webclient.controller.vo.WebFormVO;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.ATUtil;
import com.mes.webclient.util.DateTimeUtils;
import com.mes.webclient.util.StringUtil;
 
@Controller("ATMenuController")
@RequestMapping("menu.sp")
public class ATMenuController extends BaseController {

	@Autowired
	IIMService imService;
	ATMenuInfoAPI menu = new ATMenuInfoAPI();
	/**
	 * 进入页面
	 */
	@RequestMapping(params = "toMainPage")
	public String toMainPage(String dName, Model model)
	{		
		return "/im/menu/list";
	}

	/**
	 * 添加或编辑
	 * @throws Exception 
	 */
	@RequestMapping(params = "toAddOrEdit")
	public String toAddOrEdit(ATMenuVO atMenuVO, Model model)  
	{
		/**
		 * 获取菜单名称和菜单图标集合
		 * */		
		List<String> Menulist = new ArrayList<String>();
		List<String> MenuIconlist = new ArrayList<String>();

		long key = atMenuVO.getKey();
		List<ATMenuVO> list = new ArrayList();
		System.out.println("key====="+key);
		model.addAttribute(
			INVOKE_TYPE, key < 0 ? INVOKE_TYPE_ADD : INVOKE_TYPE_EDIT);
		Vector<UTRow> v = new Vector<UTRow>();
		try
		{
			Menulist = menu.getMenuList();
	    	MenuIconlist = menu.getMenuIconList();
			String atDefinitionName = "ATMenu";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
			filter.forUTRowKeyEqualTo(key);
 			
 			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
			if(rows!=null&&rows.size()>0){
				UTRow atRow = rows.get(0);
				atMenuVO.setKey(key);			
				atMenuVO.setMenuName(String.valueOf(atRow.getValue("menuName"))); 
				if(String.valueOf(atRow.getValue("menuSrc"))==null||("null").equals(String.valueOf(atRow.getValue("menuSrc")))){
					atMenuVO.setMenuSrc("");
				}else{
					atMenuVO.setMenuSrc(String.valueOf(atRow.getValue("menuSrc")));
				}
				atMenuVO.setPid(String.valueOf(atRow.getValue("pid")));
				atMenuVO.setParent(String.valueOf(atRow.getValue("parent")));	
				atMenuVO.setMenuIcon(String.valueOf(atRow.getValue("menuIcon"))); //by me 
			}
			ATMenuVO vo = new ATMenuVO();
			vo.setParent("1");
			v = this.getATMenuVO(vo);
			for(UTRow tmpv:v){
				ATMenuVO tmpATMenuVO = new ATMenuVO();
				tmpATMenuVO = (ATMenuVO)ATUtil.getATObject(ATMenuVO.class, tmpv);
				tmpATMenuVO.setKey(tmpv.getKey());
				list.add(tmpATMenuVO);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(
			VIEW_OBJECT, atMenuVO);
		model.addAttribute(
				"ParentMenu", list);
		
		model.addAttribute("menus",Menulist);//by luo 
		model.addAttribute("icon", MenuIconlist);//by luo
		
		return "im/menu/addOrEdit";
	}

	/**
	 * 添加或编辑
	 */
	@RequestMapping(params = "del")
	public @ResponseBody
	String del(@RequestParam("key") long key)
	{
		try
		{
			if (key > 0)
			{
				String atDefinitionName = "ATMenu";
				UTHandler atHandler = imService.getAtHandler(atDefinitionName);
				UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);	
				filter.forUTRowKeyEqualTo(key);
				
				Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
				if(rows!=null&&rows.size()>0){
 					UTRow atRow = rows.get(0);
					atRow.delete();
					Response response = atRow.save(null, null, null);
					if (response.isError())
					{
						throw new Exception("删除用户失败，原因为：" + response.getFirstErrorMessage());
					}
				}				 
				return ajaxDoneSuccess();
			}
			else
			{
				throw new Exception("无法找到该对象的key");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return ajaxDoneError(e.getLocalizedMessage());
		}
	}

	@RequestMapping(params = "getUserByName")
	public @ResponseBody
	String getUserByName(String name)
	{
		JSONArray rt = null;
		try
		{
			UserVO userVO = new UserVO();
			User user = imService.GetUserByName(name);
			Vector<Object> vos = new Vector<Object>();
			userVO = new UserVO();
			userVO.setKey(user.getKey());
			userVO.setName(user.getName());
			userVO.setDescription(user.getDescription());
			userVO.setFirstName(user.getFirstName());
			userVO.setLastName(user.getLastName());
			userVO.setNote(user.getNote());
			userVO.setPasswordModifiable(user.isPasswordModifiable());
			userVO.setPasswordExpiration(DateTimeUtils.formatDate(
				user.getPasswordExpiration(), IDateFormat.TIME_LONG));
			userVO.setStatus(user.getStatus());
			userVO.setUserExpiration(DateTimeUtils.formatDate(
				user.getUserExpiration(), IDateFormat.TIME_LONG));
			userVO.setEmail(user.getEmail());
			vos.add(userVO);
			rt = JSONArray.fromObject(vos);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rt.toString();
	}

	/**
	 * 查询
	 */
	@RequestMapping(params = "toList")
	public @ResponseBody
	String toList(ATMenuVO atMenuVO)
	{
		JSONArray rt = null;
		System.out.println("list================="+atMenuVO.getMenuName());
		try
		{
			String menuName = atMenuVO.getMenuName();
			String menusrc = atMenuVO.getMenuSrc();
			String atDefinitionName = "ATMenu";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			if(menuName!=null&&!"".equals(menuName)){
				filter.forColumnNameEqualTo("menuName",menuName);
			}
			if(menusrc!=null&&!"".equals(menusrc)){
				filter.forColumnNameEqualTo("menuSrc",menusrc);
			}
			Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
 		    ArrayList<Object> vos = new ArrayList<>();		    
		    filter.setMaxRows(10);
		    int i = 0;
		    for (UTRow atRow : rows)
			{
		     
				ATMenuVO vo = new ATMenuVO();
 				  
 				vo = (ATMenuVO)ATUtil.getATObject(ATMenuVO.class, atRow);
				vo.setKey(atRow.getKey());
				//获得父级菜单信息
				String pid = String.valueOf(atRow.getValue("pid"));//上级菜单key
				vo.setV_pName("无");
				if(!"0".equals(pid)){
				 
					if(StringUtil.isNotNull(pid)){
						ATMenuVO tmpvo = new ATMenuVO();
						tmpvo.setKey(Long.valueOf(pid));
						tmpvo.setParent("2");
						Vector v = this.getATMenuVO(tmpvo);
						if(v!=null&&v.size()>0){
							UTRow tmpatRow = this.getATMenuVO(tmpvo).get(0);
							tmpvo = (ATMenuVO)ATUtil.getATObject(ATMenuVO.class, tmpatRow);
							if(tmpvo!=null){
								vo.setV_pName(tmpvo.getMenuName());
							}
						}
					}
					 
				}
				if(vo.getParent().equals("0")){
					vo.setV_parentS("否");
				}else{
					vo.setV_parentS("是");
				}
			 
				vos.add(vo);
			}
 			rt = JSONArray.fromObject(vos);
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		
		return rt.toString();
	}
	
	public Vector<UTRow> getATMenuVO(ATMenuVO vo){
		String atDefinitionName = "ATMenu";
		UTHandler atHandler;
		Vector<UTRow> rows = null;
		try {
			atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			if(vo.getKey()>0){
				filter.forUTRowKeyEqualTo(vo.getKey());
			}
			if(!vo.getParent().equals("2")){
				filter.forColumnNameEqualTo("parent",vo.getParent());
			}
			rows = atHandler.getATRowsByFilter(filter, false);
			 
		} catch (MESException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return rows;
	}

	/**
	 * 保存
	 */
	@RequestMapping(params = "save")
	public @ResponseBody
	String save(ATMenuVO atMenuVO)
	{
		try
		{
			String atDefinitionName = "ATMenu";
			UTHandler atHandler = imService.getAtHandler(atDefinitionName);
			UTRowFilter filter = imService.createAtRowFilter(atDefinitionName);
			
			long key = atMenuVO.getKey();
			UTRow atRow;
 			if (key > 0)
			{
				filter.forUTRowKeyEqualTo(key);
				Vector<UTRow> rows = atHandler.getATRowsByFilter(filter, false);
				atRow = rows.get(0);
			}else{
				atRow = atHandler.createATRow();
			} 
			atRow.setValue("menuName", atMenuVO.getMenuName());
			atRow.setValue("menuSrc", atMenuVO.getMenuSrc());
			atRow.setValue("parent", atMenuVO.getV_parentS());
			atRow.setValue("menuIcon", atMenuVO.getMenuIcon());//by me
			atRow.setValue("pid", atMenuVO.getPid());
			 
			Response response = atRow.save(null, null, null);		 
			if (response.isError())
			{
				throw new Exception("保存菜单失败，原因为:" + response.getFirstErrorMessage());
			}
			return ajaxDoneSuccess();
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getMessage());
		}
	}

	@RequestMapping(params = "getUserByKey")
	public @ResponseBody
	String getUserByKey(@RequestParam("key") long key)
	{
		try
		{
			if (key > 0)
			{
				User user = imService.getUserByKey(key);
				if (user != null)
				{
					UserVO userVO = new UserVO();
					userVO.setKey(key);
					userVO.setName(user.getName());
					userVO.setDescription(user.getDescription());
					JSONObject obj = JSONObject.fromObject(userVO);
					logger.info(obj.toString());
					return obj.toString();
				}
				return null;
			}
			else
			{
				throw new Exception("无法找到该对象的key");
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			return ajaxDoneError(e.getLocalizedMessage());
		}
	}

	@RequestMapping(params = "getFormByUserName")
	public void getFormByUserName(String name, HttpServletRequest req, HttpServletResponse res)
	{
		JSONObject jsonObj = new JSONObject();

		try
		{
			User user = imService.GetUserByName(name);
			FormManager formManager = imService.getFormManager();
			long key = user.getFormKey();
			DeAnzaForm form = (DeAnzaForm) formManager.getObject(key);
			if (form != null)
			{
				WebFormVO webFormVO = new WebFormVO();
				webFormVO.setText(form.getName());
				webFormVO.setContent(new String(form.getForm()));

				ArrayList<WebFormVO> formlist = new ArrayList<WebFormVO>();
				formlist.add(webFormVO);

				jsonObj.put(
					"code", 1);
				jsonObj.put(
					"formlist", formlist);
			}
			else
			{
				jsonObj.put(
					"code", 0);
			}

			res.setContentType("text/html; charset=UTF-8");
		}
		catch (Exception e)
		{

			jsonObj.put(
				"code", 0);
			e.printStackTrace();
		}

		try
		{
			res.getWriter().write(
				jsonObj.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
