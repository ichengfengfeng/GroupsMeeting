package com.icss.meeting.dto;

public class DeviceRoomDto {
	private String dno;
	private String roomno;
	private String rname;
	private int roomsize;
	private String tno;
	private int roomstate;
	private String typename;
	private String dname;
	private int devicestate;
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getRoomsize() {
		return roomsize;
	}
	public void setRoomsize(int roomsize) {
		this.roomsize = roomsize;
	}
	public int getRoomstate() {
		return roomstate;
	}
	public void setRoomstate(int roomstate) {
		this.roomstate = roomstate;
	}
	public int getDevicestate() {
		return devicestate;
	}
	public void setDevicestate(int devicestate) {
		this.devicestate = devicestate;
	}
	
	
	

}
