package com.mes.webclient.filter.so;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mes.compatibility.client.User;


@WebFilter("/SOSessonFilter")
public class SOSessionFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User) session.getAttribute("souser");
		Map<String, String> params = request.getParameterMap();
		if (user == null) {

			for (String key : params.keySet()) {
				if (key.equals("ShopOperation")) {
					chain.doFilter(request, response);
					return;
				}
				if(key.equals("toMainPage")){
					chain.doFilter(request, response);
					return;
				}
				
				
			}
			request.setAttribute("errorMsg", "请先登录");
			request.getRequestDispatcher("sologin.jsp").forward(request,response);
			return;
		} else {
			for (String key : params.keySet()) {
				if (key.equals("ShopOperation")) {
					request.getRequestDispatcher("WEB-INF/view/so/soindex.jsp").forward(request,response);
					return;
				}
					
			}
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
