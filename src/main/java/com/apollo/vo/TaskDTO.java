package com.apollo.vo;

import java.sql.Date;

public class TaskDTO {
	private int tid;
	private String tname;
	private String sday;
	private String eday;
	private String detail;
	private int pid;
	private int importance;
	private Date ctime;
	private int tstatusid;
	private String tstatus;
	private String color;

	
	
	
	public int getTid() {
		return tid;
	}




	public void setTid(int tid) {
		this.tid = tid;
	}




	public String getTname() {
		return tname;
	}




	public void setTname(String tname) {
		this.tname = tname;
	}




	public String getSday() {
		return sday;
	}




	public void setSday(String sday) {
		this.sday = sday;
	}




	public String getEday() {
		return eday;
	}




	public void setEday(String eday) {
		this.eday = eday;
	}




	public String getDetail() {
		return detail;
	}




	public void setDetail(String detail) {
		this.detail = detail;
	}




	public int getPid() {
		return pid;
	}




	public void setPid(int pid) {
		this.pid = pid;
	}




	public int getImportance() {
		return importance;
	}




	public void setImportance(int importance) {
		this.importance = importance;
	}




	public Date getCtime() {
		return ctime;
	}




	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}




	public int getTstatusid() {
		return tstatusid;
	}




	public void setTstatusid(int tstatusid) {
		this.tstatusid = tstatusid;
	}




	public String getTstatus() {
		return tstatus;
	}




	public void setTstatus(String tstatus) {
		this.tstatus = tstatus;
	}




	public String getColor() {
		return color;
	}




	public void setColor(String color) {
		this.color = color;
	}




	@Override
	public String toString() {
		return "TaskDTO [tid=" + tid + ", tname=" + tname + ", sday=" + sday + ", eday=" + eday + ", detail=" + detail
				+ ", pid=" + pid + ", importance=" + importance + ", ctime=" + ctime + ", statusid=" + tstatusid
				+ ", tstatus=" + tstatus + ", color=" + color + "]";
	}
	
}
