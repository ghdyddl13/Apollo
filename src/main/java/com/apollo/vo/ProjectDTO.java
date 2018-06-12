package com.apollo.vo;

import java.sql.Date;

public class ProjectDTO {
	private int pid;
	private String pname;
	private Date sday;
	private Date eday;
	private int methodologyid;
	private String mid;
	private int pstatuscode;
	private Date stmtime;
	private String detail;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
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
	public int getPstatuscode() {
		return pstatuscode;
	}
	public void setPstatuscode(int pstatuscode) {
		this.pstatuscode = pstatuscode;
	}
	public Date getStmtime() {
		return stmtime;
	}
	public void setStmtime(Date stmtime) {
		this.stmtime = stmtime;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
