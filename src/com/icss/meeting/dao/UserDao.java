package com.icss.meeting.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.icss.meeting.dto.DeptMeetingDto;
import com.icss.meeting.dto.EmpDto;
import com.icss.meeting.dto.PersonalMeeting;
import com.icss.meeting.entity.TEmployee;
import com.icss.meeting.entity.TMeeting;
import com.icss.meeting.entity.TMeetingData;
import com.icss.meeting.entity.TurnPagePara;
import com.icss.meeting.entity.Tuser;

public class UserDao extends BaseDao{
	/**
	 * 用户登录
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public Tuser login(String uname, String pwd)throws Exception{
		String sql = "select empno,pwd,role from tuser where empno=? and pwd=?";
		Tuser user = null;
		this.openConnection(this.DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, uname);
		ps.setString(2, pwd);
	    ResultSet rs = ps.executeQuery();
	    while(rs.next()){
	    	user = new Tuser();
	    	user.setUname(rs.getString("empno"));
	    	user.setPwd(rs.getString("pwd"));
	    	user.setRole(rs.getInt("role"));
	    }
		return user;
	}
	/**
	 * 获得员工信息
	 * @return
	 * @throws Exception
	 */
	public List<TEmployee> getEmployeeList(String empname,TurnPagePara tp)throws Exception{
		String sql = "select * from temp  where empname like'%" + empname+ "%'";
		int allrows = this.getpageCount(sql);
		tp.RecordAllCount = allrows;
		int iStart = (tp.CurrentPageNo-1)*tp.OnePageCount + 1;
		int iEnd = iStart + tp.OnePageCount;
		int allPages = (allrows-1)/tp.OnePageCount + 1;	
		tp.PageAllCount = allPages;
		if(tp.CurrentPageNo > allPages){
			tp.CurrentPageNo = allPages;
		}
		String newSql =this.pageSql(sql,iStart,iEnd) ;
		List<TEmployee> employees = null;
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(newSql);
		ResultSet rs = ps.executeQuery();
		employees = new ArrayList<TEmployee>();
		while (rs.next()){
			TEmployee employee = new TEmployee();
			employee.setEmpno(rs.getString("empno"));
			employee.setEmpname(rs.getString("empname"));
			employee.setDeptno(rs.getString("deptno"));
			employee.setEmail(rs.getString("email"));
			employee.setJob(rs.getString("job"));
			employees.add(employee);
		}
		rs.close();
		ps.close();
		return employees;
		
	}
	/**
	 * 获得员工信息
	 * @param empno
	 * @return
	 * @throws Exception
	 */
	public TEmployee getEmployee(String empno)throws Exception{
		TEmployee employee = null;
		String sql = "select * from temp where empno = ?";
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, empno);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			employee = new TEmployee();
			employee.setEmpno(rs.getString("empno"));
			employee.setEmpname(rs.getString("empname"));
			employee.setDeptno(rs.getString("deptno"));
			employee.setEmail(rs.getString("email"));
			employee.setJob(rs.getString("job"));
		}
		rs.close();
		ps.close();
		return employee;
	}
	/**
	 * 获得员工信息
	 * @param emps
	 * @return
	 * @throws Exception
	 */
	public List<TEmployee>  getEmployeeList(Set<String> emps,TurnPagePara tp)throws Exception{
		String empnos = "";
		int i= 0;
		for(String empno:emps){
			if(i==0){
				empnos =  empno ;
			}else{
				empnos = empnos +"','" +empno ;
			}
			i++ ;
		}
			
			
		String sql = "select * from temp t where empno in('" + empnos +"')";
		int allrows = this.getpageCount(sql);
		tp.RecordAllCount = allrows;
		int iStart = (tp.CurrentPageNo-1)*tp.OnePageCount + 1;
		int iEnd = iStart + tp.OnePageCount;
		int allPages = (allrows-1)/tp.OnePageCount + 1;	
		tp.PageAllCount = allPages;
		if(tp.CurrentPageNo > allPages){
			tp.CurrentPageNo = allPages;
		}
		String newSql =this.pageSql(sql,iStart,iEnd) ;
		
		List<TEmployee> employees = null;
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(newSql);
		ResultSet rs = ps.executeQuery();
		employees = new ArrayList<TEmployee>();
		while (rs.next()){
			TEmployee employee = new TEmployee();
			employee.setEmpno(rs.getString("empno"));
			employee.setEmpname(rs.getString("empname"));
			employee.setDeptno(rs.getString("deptno"));
			employee.setEmail(rs.getString("email"));
			employee.setJob(rs.getString("job"));
			employees.add(employee);
		}
		rs.close();
		ps.close();
		return employees;
		
	}
	/**
	 * 添加参会人员
	 * @param empnos
	 * @throws Exception
	 */
	public void addJoinEmp(String mno,String empno)throws Exception{
		String sql = "insert into tmeetingperson  values((select nvl(max(aid),0)+1 from tmeetingperson),?,?)";
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, mno);
		ps.setString(2, empno);
		ps.executeUpdate();
		ps.close();
		
	}
	/**
	 * 
	 * @param meeting
	 * @throws Exception
	 */
	public void addMeeting(TMeeting meeting)throws Exception{
		String sql = "insert  into tmeeting values(?,?,?,?,?,?,?,?)";
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, meeting.getMno());
		ps.setString(2, meeting.getRoomno());
		ps.setString(3, meeting.getSpno());
		ps.setString(4, meeting.getTopic());
		ps.setTimestamp(5, new java.sql.Timestamp(meeting.getBegintime().getTime()));
		ps.setTimestamp(6, new java.sql.Timestamp(meeting.getEndtime().getTime()));
		ps.setTimestamp(7, new java.sql.Timestamp(meeting.getApplytime().getTime()));
		ps.setString(8, meeting.getTell());
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * 查询个人会议预订
	 * @return
	 * @throws Exception
	 */
	public List<PersonalMeeting> getPersonalMeeting(String empno)throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		String sql = "select m.mno,m.roomno,m.empno,m.begintime, m.endtime,m.applytime,r.rname from tmeeting m,troom r where r.roomno = m.roomno and m.empno = ? order by  applytime asc";
		List<PersonalMeeting>  pmeetings = null;
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, empno);
		ResultSet rs = ps.executeQuery();
		pmeetings = new ArrayList<PersonalMeeting>();
		while(rs.next()){
			PersonalMeeting pmeeting = new PersonalMeeting();
			pmeeting.setMno(rs.getString("mno"));
			pmeeting.setRoomno(rs.getString("roomno"));
			pmeeting.setRoomname(rs.getString("rname"));
			pmeeting.setSpno(rs.getString("empno"));
			pmeeting.setBegintime( sdf1.format(rs.getTimestamp("begintime")));
			pmeeting.setEndtime(sdf1.format(rs.getTimestamp("endtime")));
			pmeeting.setApplytime(sdf.format(rs.getTimestamp("applytime")));
			pmeetings.add(pmeeting);
			
		}
		rs.close();
		ps.close();
		return pmeetings;
	}
	/**
	 * 查询个人以及部门信息
	 * @param empno
	 * @return
	 * @throws Exception
	 */
		public EmpDto getEmp(String empno)throws Exception{
			String sql = "select e.empno,e.deptno,e.empname,e.job,e.email,d.deptname from temp e,tdept d where d.deptno = e.deptno and e.empno = ?";
			EmpDto emp = null;
			this.openConnection(DB_URL);
			PreparedStatement ps= this.conn.prepareStatement(sql);
			ps.setString(1, empno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				emp = new EmpDto();
				emp.setEmpname(rs.getString("empname"));
				emp.setDeptname(rs.getString("deptname"));
				emp.setDeptno(rs.getString("deptno"));
				emp.setEmpno(rs.getString("empno"));
				emp.setJob(rs.getString("job"));
				emp.setEmail(rs.getString("email"));
			}
			rs.close();
			ps.close();
			return emp;
		}
		/**
		 * 获得所属部门信息
		 * @param empno
		 * @throws Exception
		 */
		
		public List<DeptMeetingDto> getDeptMeeting(String empno)throws Exception{
			List<DeptMeetingDto> dmDtos = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
			String sql = "select m.topic,m.begintime,m.endtime,m.applytime,m.mno,e.empname,r.rname,e.job,d.deptname,d.deptno,e.empno,r.roomno,e.email" + 
		                  " from tmeeting m,temp e,tdept d,troom r where e.empno = m.empno and d.deptno = e.deptno and r.roomno = m.roomno and e.deptno = " +
		                  " (select deptno from temp where empno = ? ) order by applytime asc";
			this.openConnection(DB_URL);
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setString(1, empno);
			ResultSet rs = ps.executeQuery();
			dmDtos = new ArrayList<DeptMeetingDto>(20);
			while(rs.next()){
				DeptMeetingDto dmDto = new DeptMeetingDto();
				dmDto.setApplytime(sdf.format(rs.getTimestamp("applytime")));
				dmDto.setTopic(rs.getString("topic"));
				dmDto.setBegintime(sdf1.format(rs.getTimestamp("begintime")));
				dmDto.setEndtime(sdf1.format(rs.getTimestamp("endtime")));
				dmDto.setEmpname(rs.getString("empname"));
				dmDto.setMno(rs.getString("mno"));
				dmDto.setRname(rs.getString("rname"));
				dmDto.setJob(rs.getString("job"));
				dmDto.setDeptname(rs.getString("deptname"));
				dmDto.setDeptno(rs.getString("deptno"));
				dmDto.setEmpno(rs.getString("empno"));
				dmDto.setRoomno(rs.getString("roomno"));
				dmDto.setEmail(rs.getString("email"));
				dmDtos.add(dmDto);
				
			}
			rs.close();
			ps.close();
			return dmDtos;
		}
		/**
		 * 添加会议资料
		 * @param mdata
		 * @throws Exception
		 */
		public void addMeetingData(TMeetingData mdata)throws Exception{
			String sql = "insert into tmeetdata values ((select nvl(max(aid),0)+1 from tmeetdata),?,?,?)";
			this.openConnection(DB_URL);
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setString(1, mdata.getMno());
			ps.setString(2, mdata.getMname());
			ps.setString(3, mdata.getUrl());
			ps.executeUpdate();
			ps.close();
		}
		/**
		 * 获得会议资料
		 * @param mno
		 * @return
		 * @throws Exception
		 */
		public TMeetingData getData(String mno)throws Exception{
			TMeetingData mdata = null;
			String sql = "select * from tmeetdata where mno = ? ";
		    this.openConnection(DB_URL);
		    PreparedStatement ps = this.conn.prepareStatement(sql);
		    ps.setString(1, mno);
		    ResultSet rs = ps.executeQuery();
		    mdata = new TMeetingData();
		    while(rs.next()){
		    	mdata.setUrl(rs.getString("url"));
		    	mdata.setMname(rs.getString("mname"));
		    }
			return mdata;
		}

	
}
