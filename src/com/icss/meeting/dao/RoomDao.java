
package com.icss.meeting.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.icss.meeting.dto.DeviceRoomDto;
import com.icss.meeting.dto.RoomDto;
import com.icss.meeting.entity.TDevice;
import com.icss.meeting.entity.TMeeting;

public class RoomDao extends BaseDao{
	/**
	 * 获得可以预订的会议室
	 * @return
	 * @throws Exception
	 */
	public  List<RoomDto> getroom(Date begintime,Date endtime,String rtype,int state,String roomno)throws Exception{
		List<RoomDto> rooms = null;
		String sql = "  select  t.roomno,t.rname,t.tno,t.state,y.roomsize from troom t,troomtype y where y.tno = t.tno and state = ? ";
		String newSql =sql;
		if(rtype!=null&&!rtype.equals("")){
			int newRtype = Integer.parseInt(rtype);
			newSql = newSql + " and y.roomsize = " + newRtype;
		}
		if(roomno != null){
			newSql = newSql + " and t.roomno = '" + roomno+"' ";
		}
		if(begintime !=null && endtime!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			String begin = sdf.format(begintime);
			String end =sdf.format(endtime);
			newSql= newSql + " and roomno not in( select r.roomno from troom r left  join tmeeting m  on  r.roomno = m.roomno where "
                    + " (begintime <= to_date('" + begin + "','yyyymmdd hh24:mi:ss')  and  endtime >=to_date('"+ end + "','yyyymmdd hh24:mi:ss') )" 
                    + " or( endtime >= to_date('" + begin + "','yyyymmdd hh24:mi:ss')  and endtime <= to_date('"+ end + "','yyyymmdd hh24:mi:ss'))"
                    + " or( begintime >= to_date('" + begin + "','yyyymmdd hh24:mi:ss')   and  begintime <= to_date('"+ end + "','yyyymmdd hh24:mi:ss') ) ) order by roomno asc"	;	
	
		}
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(newSql);
		ps.setInt(1, state);
		ResultSet rs = ps.executeQuery();
		rooms = new ArrayList<RoomDto>();
		while(rs.next()){
			RoomDto room = new RoomDto();
			room.setRoomno(rs.getString("roomno"));
			room.setRooname(rs.getString("rname"));
			room.setTno(rs.getString("tno"));
			room.setRoomsize(rs.getInt("roomsize"));
			rooms.add(room);
		}
		
		rs.close();
		ps.close();
		return rooms;
	}
	
	/**
	 * 获得会议室预订情况
	 * @param roomno
	 * @return
	 * @throws Exception
	 */
	public List<TMeeting> getMeeting(String date,String roomno)throws Exception{
		List<TMeeting> meetings  = null;
		String sql = "select * from tmeeting t where begintime >= to_date('" +date +" 00:00:00','yyyymmdd hh24:mi:ss') and endtime <=to_date('" + date+" 23:59:59','yyyymmdd hh24:mi:ss') and roomno = ? order by begintime asc";
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, roomno);
		ResultSet rs = ps.executeQuery();
		meetings = new ArrayList<TMeeting>();
		while(rs.next()){
			TMeeting meeting = new TMeeting();
			meeting.setBegintime(rs.getTimestamp("begintime"));
			meeting.setEndtime(rs.getTimestamp("endtime"));
			meetings.add(meeting);
		}
		rs.close();
		ps.close();
		return meetings;
	}
	/**
	 * 通过roomno 获得会议室信息
	 * @param roomno
	 * @return
	 * @throws Exception
	 */
	public DeviceRoomDto getDeviceRoom(String roomno) throws Exception{
		String sql = "select t.dno,troom.roomno,troom.rname,troomtype.roomsize,troom.tno,troom.state,tdevice.tno," +
                    " tdevicetype.typename,tdevice.dname,tdevice.stateflag from tdeviceuse t,troom,troomtype,tdevice,tdevicetype"+
                    " where troom.roomno = t.roomno and troomtype.tno = troom.tno and tdevice.dno = t.dno and tdevicetype.tno = tdevice.tno and troom.roomno = ?";
		DeviceRoomDto room = null;
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			room = new DeviceRoomDto();
			room.setDno(rs.getString("dno"));
			room.setRoomno(rs.getString("roomno"));
			room.setRname(rs.getString("rname"));
			room.setRoomsize(rs.getInt("roomsize"));
			room.setTno(rs.getString("tno"));
			room.setRoomstate(rs.getInt("state"));
			room.setTypename(rs.getString("typename"));
			room.setDname(rs.getString("dname"));
			room.setDevicestate(rs.getInt("stateflag"));
			
		}
		rs.close();
		ps.close();
		return room;
	}
	/**
	 * 获得设备信息
	 * @param roomno
	 * @return
	 * @throws Exception
	 */
	public List<TDevice> getDevice(String roomno)throws Exception{
		List<TDevice> devices = null;
		String sql = "select t.dno,t.roomno,tdevice.tno,tdevice.dname,tdevice.stateflag,tdevicetype.typename" + 
                       " from tdeviceuse t,tdevice,tdevicetype where tdevice.dno = t.dno and tdevicetype.tno = tdevice.tno and roomno = '" + roomno +"'";
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		devices = new ArrayList<TDevice>();
		while(rs.next()){
			TDevice device = new TDevice();
			device.setDno(rs.getString("dno"));
			device.setDname(rs.getString("dname"));
			device.setTno(rs.getString("tno"));
			device.setDevicestate(rs.getInt("stateflag"));
			devices.add(device);
		}
		rs.close();
		ps.close();
		return devices;
	}
	/**
	 * 获得会议室信息
	 * @param roomno
	 * @return
	 * @throws Exception
	 */
	public RoomDto getRoom(String roomno)throws Exception{
		String sql = "select t.roomno,t.rname,t.tno,t.state,troomtype.roomsize from troom t,troomtype where troomtype.tno = t.tno and  roomno = ?";
		RoomDto room = null;
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setString(1, roomno);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			room = new RoomDto();
			room.setRoomno(rs.getString("roomno"));
			room.setRooname(rs.getString("rname"));
			room.setState(rs.getInt("state"));
			room.setTno(rs.getString("tno"));
			room.setRoomsize(rs.getInt("roomsize"));
		}
		rs.close();
		ps.close();
		return room;
	}



}
