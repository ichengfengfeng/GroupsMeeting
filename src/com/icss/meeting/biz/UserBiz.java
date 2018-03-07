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
	 * �û���¼
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
			throw new Exception("�������Ϊ�գ�");
		}
		return user;
	}
	
	/**
	 * ��ÿ���Ԥ���Ļ�����
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
			throw new Exception("��������");
		}
		
		return rooms;
	}
	/**
	 * ��û�����Ԥ�����
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
			throw new Exception("��������");
		}
		
		return meetings;
	}
	
	
	
	
	/**
	 * ���Ա����Ϣ
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
	 * ���Ա����Ϣ
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
	 * ����empnoԱ����Ż��Ա����Ϣ
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
			throw new Exception("δ��Ӳλ���Ա");
		}
		return employees;
	}
	/**
	 * ͨ��roomno ��û�������Ϣ
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
			throw new Exception("�����ұ��Ϊ��");
		}
		
		return rdto;
	}
	/**
	 * Ԥ��������
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
					throw new TimeConflictException("��������дʱ���");
				}
				
			} catch (Exception e) {
				udao.rollback();
				throw e;
			}finally{
				udao.closeConnection();
			}
		}else{
			throw new Exception("�յĻ����յĲλ���Ա");
		}
		
	}
	/**
	 * ��ѯ���˻���Ԥ��
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
			throw new Exception("Ա�����Ϊ�գ�");
		}
		
		return pmeetings;
		}
	/**
	 * ��ѯ�����Լ�������Ϣ
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
		 * �������������Ϣ
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
				throw new Exception("Ա�����Ϊ��");
			}
			
			return dmDtos;
		}
		/**
		 * ��û�������
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
				throw new NotFoundFileException("δ�ϴ��ļ���");
			}
			return mdata;
		}

}
