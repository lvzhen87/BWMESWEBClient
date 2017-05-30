package com.mes.webclient.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mes.compatibility.client.DeAnzaForm;
import com.mes.compatibility.client.User;
import com.mes.compatibility.manager.FormManager;
import com.mes.webclient.constants.LoginProxy;
import com.mes.webclient.controller.vo.LoginUserVO;
import com.mes.webclient.controller.vo.WebFormVO;
import com.mes.webclient.proxy.WebServerProxy;
import com.mes.webclient.service.IIMService;
import com.mes.webclient.util.StringUtil;

@Controller("loginSoController")
@RequestMapping("/loginso.sp")
public class LoginSOController extends BaseController {

	@Autowired
	IIMService imService;
	
	@RequestMapping(params = "toMainPage")
	public String toMainPage() {
		return "/so/loginso";
	}

	@RequestMapping(params = "ShopOperation")
	public String toLogin(LoginUserVO loginUserVO, Model model,
			HttpServletRequest req) {
		try {
			String userName = loginUserVO.getUserName();
			String password = loginUserVO.getPassword();
			if (StringUtil.isNull(userName) || StringUtil.isNull(password)) {
				throw new Exception("用户名或密码不允许为空");
			}
//			LoginProxy.loginSO(userName, password,model, req);
			WebServerProxy.login(loginUserVO.getUserName(),
					loginUserVO.getPassword());
			User user =WebServerProxy.getCurrentUser();
			model.addAttribute("useName", WebServerProxy.getCurrentUser()
					.getName());
			model.addAttribute("station", user.getUDA("station").toString());
			
			HttpSession session = req.getSession();
			session.setAttribute("souser", user);
		} catch (Exception e) {
			logger.error(e);
			if(e.getMessage().equals("用户名或密码不允许为空"))
			{
				model.addAttribute("errorMsg", "请先登录");
				
			}
			else{
				model.addAttribute("errorMsg", e.toString());
			}
			return "so/loginso";
		}
		return "so/soindex";
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
				jsonObj.put(
						"station", user.getUDA("station"));
					
			}
			else
			{
				jsonObj.put(
					"code", 0);
			}

			res.setContentType("text/html; charset=GBK");
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

	@RequestMapping(params = "logout")
	public String logOut(HttpServletRequest req) {
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("souser");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/so/loginso";

	}
}
