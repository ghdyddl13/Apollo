package com.apollo.vo;

import java.sql.Date;

public class StepDTO {

	private int sid;
	private String sname;
	private Date sday;
	private Date eday;
	private int pid;
	private int fid;
	private int methodologyid;
	private String mid;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Date getSday() {
		return sday;
	}
	public void setSday(Date sday) {
		this.sday = sday;
	}
	public Date getEday() {
		return eday;
	}
	public void setEday(Date eday) {
		this.eday = eday;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getMethodologyid() {
		return methodologyid;
	}
	public void setMethodologyid(int methodologyid) {
		this.methodologyid = methodologyid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "StepDTO [sid=" + sid + ", sname=" + sname + ", sday=" + sday + ", eday=" + eday + ", pid=" + pid
				+ ", fid=" + fid + ", methodologyid=" + methodologyid + ", mid=" + mid + "]";
	}
	
	
	
}
