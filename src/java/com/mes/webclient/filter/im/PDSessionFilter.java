package com.mes.webclient.filter.im;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mes.compatibility.client.MESException;
import com.mes.compatibility.client.User;
import com.mes.webclient.server.WebObjectFactory;

/**
 * 
 * author machen
 */
@WebFilter("/SessionFilter")
public class PDSessionFilter implements Filter {
	public static Map<String, WebObjectFactory> loginList = new HashMap<String, WebObjectFactory>();
	public PDSessionFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		WebObjectFactory param = (WebObjectFactory) session
				.getAttribute("user");

		Map<String, String> params = request.getParameterMap();
		String flag =((HttpServletRequest)request).getServletPath();
		if (flag.contains("loginso.sp")) {
			chain.doFilter(request, response);
			return;
		}
		for (String key : params.keySet()) {
			if (key.equals("toLogin") || key.equals("toLoginMainPage")||key.equals("loginOut")) {
				chain.doFilter(request, response);
				return;
			}
			User  user = null;
			try {
				user = param.getLoggedUser();
				String name = user.getName();
				WebObjectFactory temp = loginList.get(name);
				if (temp == null)
				{
					request.setAttribute("message", "该用户密码已经被修改，请重新登录");
					request.getRequestDispatcher(
						"WEB-INF/view/im/timeout.jsp").forward(request,
						response);
					return;
				}
				else if (temp.equals(param)) 
				{
					chain.doFilter(request, response);
					return;
				} else {
					loginList.remove(name);
					request.setAttribute("message", "该用户已在其它客户端登录，请重新登录");
					request.getRequestDispatcher(
							"WEB-INF/view/im/timeout.jsp").forward(request,
							response);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
