package com.icss.meeting.biz;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.icss.meeting.dao.RoomDao;
import com.icss.meeting.dao.UserDao;
import com.icss.meeting.dto.DeptMeetingDto;
import com.icss.meeting.dto.EmpDto;
import com.icss.meeting.dto.PersonalMeeting;
import com.icss.meeting.dto.RoomDto;
import com.icss.meeting.entity.TDevice;
import com.icss.meeting.entity.TEmployee;
import com.icss.meeting.entity.TMeeting;
import com.icss.meeting.entity.TMeetingData;
import com.icss.meeting.entity.TurnPagePara;
import com.icss.meeting.entity.Tuser;
import com.icss.meeting.exception.NotFoundFileException;
import com.icss.meeting.exception.TimeConflictException;

public class UserBiz {
	/**
	 * 用户登录
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public Tuser login(String uname, String pwd)throws Exception{
		Tuser  user = null;
		if(uname != null && pwd != null){
			UserDao udao = new UserDao();
			try {
				user = udao.login(uname, pwd);
			} catch (Exception e) {
				throw e;
			}finally{
				udao.closeConnection();
			}
		}else{
			throw new Exception("输入参数为空！");
		}
		return user;
	}
	
	/**
	 * 获得可以预订的会议室
	 * @return
	 * @throws Exception
	 */
	public  List<RoomDto> getroom(Date begintime,Date endtime,String rtype,String roomno)throws Exception{
		List<RoomDto> rooms = null;
		if(begintime != null && endtime != null){
			RoomDao rdao = new RoomDao();
			try {
				rooms =rdao.getroom(begintime, endtime,rtype,1,roomno);
				
			} catch (Exception e) {
				throw e;
			}finally{
				rdao.closeConnection();
			}
		}else{
			throw new Exception("参数错误");
		}
		
		return rooms;
	}
	/**
	 * 获得会议室预订情况
	 * @param roomno
	 * @return
	 * @throws Exception
	 */
	public List<TMeeting> getMeeting(String date,String roomno)throws Exception{
		List<TMeeting> meetings = null;
		if(date != null && roomno != null){
			RoomDao rdao = new RoomDao();
			try {
				meetings = rdao.getMeeting(date, roomno);
			} catch (Exception e) {
				throw e;
			}finally{
				rdao.closeConnection();
			}
		}else{
			throw new Exception("参数错误");
		}
		
		return meetings;
	}
	
	
	
	
	/**
	 * 获得员工信息
	 * @return
	 * @throws Exception
	 */
	public List<TEmployee> getEmployeeList(String empname,TurnPagePara tp)throws Exception{
		UserDao udao = new UserDao();
		List<TEmployee> employees;
		if(empname==null){
			empname= "";
		}
		try {
			employees = udao.getEmployeeList(empname,tp);
		} catch (Exception e) {
			throw e;
		}finally{
			udao.closeConnection();
		}
		return employees;
		
	}
	/**
	 * 获得员工信息
	 * @param empno
	 * @return
	 * @throws Exception
	 */
	public TEmployee getEmployee(String empno)throws Exception{
		UserDao udao = new UserDao();
		TEmployee emp ;
		try {
			emp = udao.getEmployee(empno);
		} catch (Exception e) {
			throw e;
		}finally{
			udao.closeConnection();
		}
	
		return emp;
	}
	/**
	 * 根据empno员工编号获得员工信息
	 * @param emps
	 * @return
	 * @throws Exception
	 */
	public List<TEmployee>  getEmployeeList(Set<String> emps,TurnPagePara tp)throws Exception{
		UserDao udao = new UserDao();
		List<TEmployee> employees = null;
		if(emps!=null){
			try {
				employees = udao.getEmployeeList(emps, tp);
			} catch (Exception e) {
				throw e;
			}finally{
				udao.closeConnection();
			}
			
		}else{
			throw new Exception("未添加参会人员");
		}
		return employees;
	}
	/**
	 * 通过roomno 获得会议室信息
	 * @param roomno
	 * @return
	 * @throws Exception
	 */
	public RoomDto geDevicetRoom(String roomno) throws Exception{
		RoomDto rdto = null;
		if(roomno!=null){
			RoomDao rdao = new RoomDao();
			try {    
				 List<TDevice> devices = rdao.getDevice(roomno);
				 rdto = rdao.getRoom(roomno);
				 rdto.setDevices(devices);
			} catch (Exception e) {
				throw e;
			}finally{
				rdao.closeConnection();
			}
			
		}else{
			throw new Exception("会议室编号为空");
		}
		
		return rdto;
	}
	/**
	 * 预订会议室
	 * @param meeting
	 * @param empnos
	 * @throws Exception
	 */
	public void reserveRoom(TMeeting meeting,Set<String> empnos,TMeetingData mdata)throws TimeConflictException,Exception{
		if(meeting!=null&&empnos!=null){
			UserDao udao = new UserDao();
			RoomDao rdao = new RoomDao();
			try {
				List<RoomDto> rdto =rdao.getroom(meeting.getBegintime(), meeting.getEndtime(), null, 1, meeting.getRoomno());
				if(rdto.size()!=0){
					udao.beginTransaction();
					udao.addMeeting(meeting);
					udao.addMeetingData(mdata);
					for(String empno:empnos){
						udao.addJoinEmp(meeting.getMno(),empno);
					}
					
					udao.commit();
				}else{
					throw new TimeConflictException("请重新填写时间段");
				}
				
			} catch (Exception e) {
				udao.rollback();
				throw e;
			}finally{
				udao.closeConnection();
			}
		}else{
			throw new Exception("空的会议或空的参会人员");
		}
		
	}
	/**
	 * 查询个人会议预订
	 * @return
	 * @throws Exception
	 */
	public List<PersonalMeeting> getPersonalMeeting(String empno)throws Exception{
		List<PersonalMeeting> pmeetings = null;
		if(empno != null){
			UserDao udao = new UserDao();
			try {
				pmeetings =	udao.getPersonalMeeting(empno);
			} catch (Exception e) {
				throw e;
			}finally{
				udao.closeConnection();
			}
			
		}else{
			throw new Exception("员工编号为空！");
		}
		
		return pmeetings;
		}
	/**
	 * 查询个人以及部门信息
	 * @param empno
	 * @return
	 * @throws Exception
	 */
		public EmpDto getEmp(String empno)throws Exception{
			EmpDto emp= null;
			if(empno != null){
				UserDao udao = new UserDao();
				try {
					emp = udao.getEmp(empno);
				} catch (Exception e) {
					throw e;
				}finally{
					udao.closeConnection();
				}
			}
			return emp;
		}
		/**
		 * 获得所属部门信息
		 * @return
		 * @throws Exception
		 */
		public List<DeptMeetingDto> getDeptMeeting(String empno)throws Exception{
			List<DeptMeetingDto>  dmDtos = null;
			if(empno != null){
				UserDao udao = new UserDao();
				try {
					dmDtos = udao.getDeptMeeting(empno);
				} catch (Exception e) {
					throw e;
				}finally{
					udao.closeConnection();
				}
			}else{
				throw new Exception("员工编号为空");
			}
			
			return dmDtos;
		}
		/**
		 * 获得会议资料
		 * @param mno
		 * @return
		 * @throws Exception
		 */
		public TMeetingData getData(String mno)throws NotFoundFileException,Exception{
			TMeetingData mdata = null;
			if(mno != null){
				UserDao  udao = new UserDao();
				try {
					mdata = udao.getData(mno);
				}catch (Exception e) {
					throw e;
				}finally{
					udao.closeConnection();
				}
			}else{
				throw new NotFoundFileException("未上传文件！");
			}
			return mdata;
		}

}
