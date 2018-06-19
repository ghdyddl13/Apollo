package com.apollo.vo;

import java.sql.Date;
import java.util.ArrayList;
/**
 * 
  클래스명 : MyWorkTaskDTO
  날      짜 : 2018. 6. 19.
  작성자명 : 이 진 우
 */
public class MyWorkTaskDTO {
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
	private String date;
	private ArrayList<MyWorkStepDTO> steps;
	private ArrayList<MyWorkMemberDTO> members;
	
	

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
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
	public ArrayList<MyWorkStepDTO> getSteps() {
		return steps;
	}
	public void setSteps(ArrayList<MyWorkStepDTO> steps) {
		this.steps = steps;
	}
	public ArrayList<MyWorkMemberDTO> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<MyWorkMemberDTO> members) {
		this.members = members;
	}
	@Override
	public String toString() {
		return "MyWorkTaskDTO [tid=" + tid + ", tname=" + tname + ", sday=" + sday + ", eday=" + eday + ", detail="
				+ detail + ", pid=" + pid + ", importance=" + importance + ", ctime=" + ctime + ", tstatusid="
				+ tstatusid + ", tstatus=" + tstatus + ", color=" + color + ", date=" + date + ", steps=" + steps
				+ ", members=" + members + "]";
	}

	
	
}
