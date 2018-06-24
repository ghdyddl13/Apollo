package com.apollo.vo;
/**
 * 
  클래스명 : StepListStepDTO
  날      짜 : 2018. 6. 24.
  작성자명 : 이 진 우
 */
public class StepListStepDTO {

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
