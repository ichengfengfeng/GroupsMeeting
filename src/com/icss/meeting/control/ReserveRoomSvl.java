package com.icss.meeting.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.icss.meeting.biz.UserBiz;
import com.icss.meeting.entity.TMeeting;
import com.icss.meeting.entity.TMeetingData;
import com.icss.meeting.entity.Tuser;
import com.icss.meeting.exception.TimeConflictException;
import com.icss.meeting.util.Log;
import com.jspsmart.upload.SmartUpload;

public class ReserveRoomSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReserveRoomSvl() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("gbk");
		request.setCharacterEncoding("gbk");
		SmartUpload smart = new SmartUpload();
		try {
			JspFactory _jspxFactory = null;
	         PageContext pageContext = null;
	         _jspxFactory = JspFactory.getDefaultFactory();
	         pageContext = _jspxFactory.getPageContext(this,request,response,"",true,8192,true);
	          
	        smart.initialize(pageContext);//初始化上传操作
	        smart.setCharset("gbk");
	        smart.setAllowedFilesList("rar");
	        smart.upload();
	        com.jspsmart.upload.Request  request1 =  smart.getRequest();
	        String date = request1.getParameter("date");
	        String mno = "M-" + date + new Date().getTime();
	        String fileName = smart.getFiles().getFile(0).getFileName();
	        System.out.println("获取 的文件名为"+fileName);
	        String realPath = this.getServletContext().getRealPath("");
	        String fDest = realPath + "\\upload" + "\\ " +mno;
	        java.io.File file = new java.io.File(fDest);
	        if(!file.exists()){
	        	 file.mkdir();
	        }
	        smart.getFiles().getFile(0).saveAs(fDest + "\\" +fileName);
	        Object obj = request.getSession().getAttribute("emps");
			String roomno = request1.getParameter("roomno");
			String begintime = request1.getParameter("begintime");
			String endtime = request1.getParameter("endtime");
			String theme = request1.getParameter("theme");
			String idate = request1.getParameter("idate");
			if (theme != null) {
				theme = new String(theme.getBytes("ISO-8859-1"), "gbk");
			}
			String tell = request.getParameter("tell");
			Object tuser = ((HttpServletRequest) request).getSession()
					.getAttribute("user");
			Tuser user = (Tuser) tuser;
			String empno = user.getUname();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			if (date == null || date.equals("")) {
				date = sdf1.format(new Date());
			}
			if (obj != null) {
				Set<String> emps = (Set<String>) obj;
				UserBiz ubiz = new UserBiz();
				try {
					Date begin = sdf.parse(date + " " + begintime);
					Date end = sdf.parse(date + " " + endtime);
					TMeeting meeting = new TMeeting();
					TMeetingData mdata = new TMeetingData();
					mdata.setMname(fileName);
					mdata.setMno(mno);
					mdata.setUrl(fDest + "\\" +fileName);
					meeting.setMno(mno);
					meeting.setRoomno(roomno);
					meeting.setSpno(empno);
					meeting.setTopic(theme);
					meeting.setBegintime(begin);
					meeting.setEndtime(end);
					meeting.setTell(tell);
					meeting.setApplytime(new Date());
					ubiz.reserveRoom(meeting, emps,mdata);
					emps.removeAll(emps);
					request.getRequestDispatcher("/WEB-INF/main/reserveLater.jsp")
							.forward(request, response);
				} catch (TimeConflictException e) {
					Log.logger.error(e.getMessage());
					request.setAttribute("msg", e.getMessage());
					request.setAttribute("date", date);
					request.setAttribute("roomno", roomno);
					request.setAttribute("idate", idate);
					request.getRequestDispatcher("/user/RoomInfoSvl").forward(request,response);
				} catch (Exception e) {
					Log.logger.error(e.getMessage());
					request.setAttribute("errmsg", "网络繁忙");
					request.getRequestDispatcher("/error.jsp").forward(request,
							response);
				}
			} else {
				request.setAttribute("errmsg", "未选择参会人员");
				request.getRequestDispatcher("/error.jsp")
						.forward(request, response);
			}
		}catch (java.lang.SecurityException e) {
			Log.logger.error(e.getMessage());
			request.setAttribute("errmsg", "请上传*.rar文件");
			request.getRequestDispatcher("/error.jsp")
					.forward(request, response);
		}catch (Exception e) {
			Log.logger.error(e.getMessage());
			e.printStackTrace();
			request.setAttribute("errmsg", "上传失败");
			request.getRequestDispatcher("/error.jsp")
					.forward(request, response);

		}
		

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
