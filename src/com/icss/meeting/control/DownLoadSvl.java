package com.icss.meeting.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.icss.meeting.biz.UserBiz;
import com.icss.meeting.entity.TMeetingData;
import com.icss.meeting.exception.NotFoundFileException;
import com.icss.meeting.util.Log;
import com.jspsmart.upload.SmartUpload;

public class DownLoadSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DownLoadSvl() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("gbk");
		request.setCharacterEncoding("gbk");
		String mno = request.getParameter("mno");
		if (mno != null && !"".equals(mno)) {
			SmartUpload smart = new SmartUpload();
			try {
				// PageContext是jsp的内置对象，在servlet不能直接使用，需要做一些处理
				JspFactory _jspxFactory = null;
				PageContext pageContext = null;
				_jspxFactory = JspFactory.getDefaultFactory();
				pageContext = _jspxFactory.getPageContext(this, request,
						response, "", true, 8192, true);

				smart.initialize(pageContext);// 初始化上传操作
				smart.setCharset("gbk");
				System.out.println("获取的ip为"
						+ InetAddress.getLocalHost().getHostAddress());
				// 如果要实现文件的批量上传，则只需用for循环，将getFile(0)中的0改为i即可
				UserBiz ubiz = new UserBiz();
				TMeetingData mdata = ubiz.getData(mno);
				smart.setContentDisposition(null);
				smart.downloadFile(mdata.getUrl());

			} catch (NotFoundFileException ex) {
				Log.logger.error(ex.getMessage());
				PrintWriter out = response.getWriter();
				out.print("未上传文件！");
				out.flush();
				out.close();
			} catch (java.lang.IllegalArgumentException ex) {
				Log.logger.error(ex.getMessage());
				PrintWriter out = response.getWriter();
				out.print("未上传文件！");
				out.flush();
				out.close();
			} 
			
			catch (Exception e) {
				Log.logger.error(e.getMessage());
				e.printStackTrace();
				request.getRequestDispatcher("/error.jsp").forward(request,
						response);
			}
		} else {
			PrintWriter out = response.getWriter();
			out.print("未上传文件！");
			out.flush();
			out.close();
		}

	}

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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
