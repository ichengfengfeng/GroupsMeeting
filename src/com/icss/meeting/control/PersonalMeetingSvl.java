package com.icss.meeting.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.biz.UserBiz;
import com.icss.meeting.dto.EmpDto;
import com.icss.meeting.dto.PersonalMeeting;
import com.icss.meeting.entity.Tuser;
import com.icss.meeting.util.Log;

public class PersonalMeetingSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PersonalMeetingSvl() {
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
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object obj = request.getSession().getAttribute("user");
		Tuser user =null; ;
		if(obj!=null){
			user = (Tuser)obj;
		}
		UserBiz ubiz = new UserBiz();
		try {
			List<PersonalMeeting> meetings = ubiz.getPersonalMeeting(user.getUname());
			
			request.setAttribute("meetings", meetings);
			request.getRequestDispatcher("/WEB-INF/main/personal.jsp").forward(request, response);
		} catch (Exception e) {
		    Log.logger.error(e.getMessage());
		    request.setAttribute("errmsg", "��·��æ");
		    request.getRequestDispatcher("/error.jsp").forward(request, response);
		    
		}

		
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
