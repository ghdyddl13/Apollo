package com.apollo.vo;

import java.util.ArrayList;
/**
 * 
  클래스명 : StepListTaskDTO
  날      짜 : 2018. 6. 24.
  작성자명 : 이 진 우
 */
public class StepListTaskDTO {
	private int tid;
	private String tname;
	private String sday;
	private String eday;
	private String overdue;
	private String detail;
	private int pid;
	private String ctime;
	private int tstatusid;
	private int sid;
	private String tstatus;
	private String color;
	private String date;
	private ArrayList<StepListStepDTO> steps;
	private ArrayList<StepListMemberDTO> members;
	
	
	
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
	public String getOverdue() {
		return overdue;
	}
	public void setOverdue(String overdue) {
		this.overdue = overdue;
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
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public int getTstatusid() {
		return tstatusid;
	}
	public void setTstatusid(int tstatusid) {
		this.tstatusid = tstatusid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public ArrayList<StepListStepDTO> getSteps() {
		return steps;
	}
	public void setSteps(ArrayList<StepListStepDTO> steps) {
		this.steps = steps;
	}
	public ArrayList<StepListMemberDTO> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<StepListMemberDTO> members) {
		this.members = members;
	}
	@Override
	public String toString() {
		return "StepListTaskDTO [tid=" + tid + ", tname=" + tname + ", sday=" + sday + ", eday=" + eday + ", overdue="
				+ overdue + ", detail=" + detail + ", pid=" + pid + ", ctime=" + ctime + ", tstatusid=" + tstatusid
				+ ", sid=" + sid + ", tstatus=" + tstatus + ", color=" + color + ", date=" + date + ", steps=" + steps
				+ ", members=" + members + "]";
	}

	
}
