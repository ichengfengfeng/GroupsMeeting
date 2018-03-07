package com.icss.meeting.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.biz.UserBiz;
import com.icss.meeting.entity.Tuser;
import com.icss.meeting.util.Log;

public class LoginSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginSvl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String uname = request.getParameter("uname").trim();
		String pwd = request.getParameter("password").trim();
		String verifyCode = request.getParameter("verifyCode").trim();
		String rand = request.getSession().getAttribute("rand").toString();
		UserBiz ubiz = new UserBiz();
		if(verifyCode.equals(rand.toString())){
			try {
				Tuser user = ubiz.login(uname, pwd);
				if(user != null){
					//登陆成功
					request.getSession().setAttribute("user", user);
					out.print("ok");
					
				}else{
					out.print("用户名或密码错误!");
					
				}
				
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
			    request.setAttribute("errmsg", "网络繁忙！");
			    request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}else{
			out.print("验证码错误!");
			//request.setAttribute("msg", "校验码错误");
			//request.getRequestDispatcher("/WEB-INF/main/login.jsp").forward(request, response);
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
