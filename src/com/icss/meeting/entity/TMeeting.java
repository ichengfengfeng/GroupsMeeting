package com.icss.meeting.entity;

import java.util.Date;

public class TMeeting {
	private String mno;
	private String roomno;
	private String spno;
	private String topic;
	private Date begintime;
	private Date endtime;
	private Date applytime;
	private String tell;
	
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getSpno() {
		return spno;
	}
	public void setSpno(String spno) {
		this.spno = spno;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Date getApplytime() {
		return applytime;
	}
	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
	
	

}
