package com.apollo.vo;

import java.sql.Date;

public class ProjectDTO {
	private int pid;
	private String pname;
	private String sday;
	private String eday;
	private int methodologyid;
	private String mid;
	private int pstatuscode;
	private String stmtime;
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
	public int getMethodologyid() {
		return methodologyid;
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

	public String getStmtime() {
		return stmtime;
	}
	public void setStmtime(String stmtime) {
		this.stmtime = stmtime;
	}

	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Override
	public String toString() {
		return "ProjectDTO [pid=" + pid + ", pname=" + pname + ", sday=" + sday + ", eday=" + eday + ", methodologyid="
				+ methodologyid + ", mid=" + mid + ", pstatuscode=" + pstatuscode + ", stmtime=" + stmtime + ", detail="
				+ detail + "]";
	}
	
	
}
