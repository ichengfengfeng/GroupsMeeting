package com.icss.meeting.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.biz.UserBiz;
import com.icss.meeting.constant.DeviceState;
import com.icss.meeting.constant.RoomState;
import com.icss.meeting.dto.RoomDto;
import com.icss.meeting.util.Log;

public class RoomInfoSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RoomInfoSvl() {
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
		String roomno = request.getParameter("roomno");
		String date = request.getParameter("date");
	    String idate = request.getParameter("idate");
		if(roomno !=null){
			UserBiz ubiz = new UserBiz();
			try {
				RoomDto rdto = ubiz.geDevicetRoom(roomno);
				int size = rdto.getRoomsize();
				String spec ="";
				if(size==10){
					spec = "6-10";
				}else if(size==20){
					spec = "10-20";
				}else if(size ==30){
					spec = "20-30";
				}
					request.setAttribute("spec", spec);
					request.setAttribute("rdto", rdto);
					request.setAttribute("date", date);
					request.setAttribute("idate", idate);
					request.getRequestDispatcher("/WEB-INF/main/reserveBefore.jsp").forward(request, response);
				
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				request.setAttribute("errmsg", "ÍøÂç·±Ã¦");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			
		}else{
			request.setAttribute("errmsg", "ÍøÂç·±Ã¦");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		

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
		String roomno = (String) request.getAttribute("roomno");
		String date =  (String) request.getAttribute("date");
	    String idate = (String) request.getAttribute("idate");
	    String msg = (String) request.getAttribute("msg");
		if(roomno !=null){
			UserBiz ubiz = new UserBiz();
			try {
				RoomDto rdto = ubiz.geDevicetRoom(roomno);
				int size = rdto.getRoomsize();
				String spec ="";
				if(size==10){
					spec = "6-10";
				}else if(size==20){
					spec = "10-20";
				}else if(size ==30){
					spec = "20-30";
				}
					request.setAttribute("spec", spec);
					request.setAttribute("rdto", rdto);
					request.setAttribute("date", date);
					request.setAttribute("idate", idate);
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("/WEB-INF/main/reserveBefore.jsp").forward(request, response);
				
			} catch (Exception e) {
				Log.logger.error(e.getMessage());
				request.setAttribute("errmsg", "ÍøÂç·±Ã¦");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			
		}else{
			request.setAttribute("errmsg", "ÍøÂç·±Ã¦");
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
