package com.icss.meeting.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;



public class LoginFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
        Object obj = request.getSession().getAttribute("user");
        if(obj!=null){
        	arg2.doFilter(request, arg1);
        }else{
        	request.setAttribute("msg", "ÇëµÇÂ¼£¡");
        	request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, arg1);
        	//HttpServletResponse response = (HttpServletResponse)arg1;
        	//response.sendRedirect("/WEB-INF/main/login.jsp");
        	
        }
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

	

}
