package com.icss.meeting.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.icss.meeting.biz.UserBiz;
import com.icss.meeting.entity.TEmployee;
import com.icss.meeting.entity.TurnPagePara;
import com.icss.meeting.util.JsonUtil;
import com.icss.meeting.util.Log;

public class JoinEmpListSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public JoinEmpListSvl() {
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
		doPost(request, response);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		Object obj = request.getSession().getAttribute("emps");
		response.setContentType("text/json; charset=gbk");
		response.setCharacterEncoding("gbk");

		if (obj != null) {
			Set<String> emps = (Set<String>) obj;
			if (emps.size() != 0) {
				String page = request.getParameter("page");
				String rows = request.getParameter("rows");
				UserBiz ubiz = new UserBiz();
				TurnPagePara tp = new TurnPagePara();
				if (page != null) {
					tp.CurrentPageNo = Integer.parseInt(page);
				}
				if (rows != null) {
					tp.OnePageCount = Integer.parseInt(rows);
				}
				try {
					List<TEmployee> employees = ubiz.getEmployeeList(emps, tp);
					request.setAttribute("employees", employees);
					JSONArray jsonArray = JSONArray.fromObject(employees);
					PrintWriter out = response.getWriter();
					String personstr = jsonArray.toString();
					personstr = JsonUtil.getDatagridJSON(tp.RecordAllCount,
							personstr);
					out.print(personstr);
					out.flush();
					out.close();

				} catch (Exception e) {
					Log.logger.error(e.getMessage());
					response.getWriter().print("错误");
				}

			} else {
				response.getWriter().print("还未添加参会人员");
			}
		} else {
			response.getWriter().print("还未添加参会人员");

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
