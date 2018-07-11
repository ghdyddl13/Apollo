/**
 프로젝트 : Apollo
 파일이름 : TstatusDTO.java 
 날      짜 : 2018. 6. 15.
 작 성  자 : 이 창 훈
*/

package com.apollo.vo;

public class TstatusDTO {
	private int tstatusid;
	private String tstatus;
	private String color;
	private int ordering;
	private int methodologyid;
	
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
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public int getMethodologyid() {
		return methodologyid;
	}
	public void setMethodologyid(int methodologyid) {
		this.methodologyid = methodologyid;
	}
	@Override
	public String toString() {
		return "TstatusDTO [tstatusid=" + tstatusid + ", tstatus=" + tstatus + ", color=" + color + ", ordering="
				+ ordering + ", methodologyid=" + methodologyid + "]";
	}
	
	
	

}
