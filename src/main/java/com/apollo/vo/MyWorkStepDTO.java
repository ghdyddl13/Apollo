package com.apollo.vo;

public class MyWorkStepDTO {

	private int sid;
	private String sname;
	private int tid;
	
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
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	@Override
	public String toString() {
		return "MyWorkStepDTO [sid=" + sid + ", sname=" + sname + ", tid=" + tid + "]";
	}
	
	
	
}
