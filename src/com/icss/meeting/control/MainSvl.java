package com.icss.meeting.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.biz.UserBiz;
import com.icss.meeting.dto.RoomDto;
import com.icss.meeting.entity.TMeeting;
import com.icss.meeting.util.Log;

public class MainSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MainSvl() {
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
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		String date = request.getParameter("date");
		request.setAttribute("idate", date);
		if(date!=null&&!date.equals("")){
			if(date.equals("today")){
				
		    	date = sdf1.format(new Date());
		    }
		    if(date.equals("tomorrow")){
		    	Calendar ca = new GregorianCalendar();
		    	ca.setTime(new Date());
		    	ca.add(ca.DATE, +1);
		    	date = sdf1.format(ca.getTime());
		    }
		}else{
			request.setAttribute("idate", "today");
			date = sdf1.format(new Date());
		}
	    
	    String   begintime = request.getParameter("begintime");
	    request.setAttribute("begintime", begintime);
	    if(begintime == null || begintime.equals("")){
	    	begintime = "00:00:00";
	    }
		String endtime = request.getParameter("endtime");
		 request.setAttribute("endtime", endtime);
		if(endtime == null || endtime.equals("")){
			endtime = "00:00:00";
		}
		UserBiz ubiz = new UserBiz();
		String rtype = request.getParameter("rtype");
		request.setAttribute("rtype", rtype);
		String constantdate = date + " 08:00:00";
		try {
			Date newconstantdate = sdf.parse(constantdate);
			Date begin = sdf.parse(date + " " + begintime);
			Date end = sdf.parse(date + " " + endtime);
			List<RoomDto> rooms = ubiz.getroom(begin, end,rtype,null);	
			for(RoomDto room:rooms){
				List<TMeeting> meetings = ubiz.getMeeting(date, room.getRoomno());
				List<String> td = new ArrayList<String>();
			    	if(meetings.size()!=0){
			    		int beginM = 0;
			    		int endM = 0;
			    		long endP = newconstantdate.getTime();//��һ�ν���ʱ��
			    		int i = 0;
			    		for(TMeeting meeting: meetings){
								beginM = (int)(meeting.getBegintime().getTime()-endP)/60000  ;
								
							    endM = (int)(meeting.getEndtime().getTime()-endP)/60000 ;
								if(beginM!=0){
									String s = "<td colspan = '" +beginM +"' ></td>";
									td.add(s);
								}
								String red = "<td colspan = '" +(endM-beginM) + "'  style='background-color: #BE77FF'> </td>";
								td.add(red);
								i++;
								if(i == meetings.size()){
									if( (600-(int)(meeting.getEndtime().getTime()-newconstantdate.getTime())/60000)!=0){
									String later = "<td colspan = '" + (600-(int)(meeting.getEndtime().getTime()-newconstantdate.getTime())/60000) +"'></td>";
								    td.add(later);
									}
								}
								endP = meeting.getEndtime().getTime();
			    		}
			    	}else{
			    		String s = "<td colspan = 600 width='600px'></td>";
						td.add(s);
			    	}
				room.setTd(td);
			}
			request.setAttribute("date", date);
		    request.setAttribute("rooms", rooms);
		 request.getRequestDispatcher("/WEB-INF/main/main.jsp").forward(request, response);
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
			request.setAttribute("errmsg", "���緱æ");
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
