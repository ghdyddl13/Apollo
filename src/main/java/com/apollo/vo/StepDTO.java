package com.apollo.vo;


public class StepDTO {

	private int sid;
	private String sname;
	private String sday;
	private String eday;
	private int pid;
	private String pname;
	private String fid;
	private int methodologyid;
	private String mid;
	private String detail;
	
	
	
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
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "StepDTO [sid=" + sid + ", sname=" + sname + ", sday=" + sday + ", eday=" + eday + ", pid=" + pid
				+ ", pname=" + pname + ", fid=" + fid + ", methodologyid=" + methodologyid + ", mid=" + mid
				+ ", detail=" + detail + "]";
	}
	

}
