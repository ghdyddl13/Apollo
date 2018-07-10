/**
 프로젝트 : Apollo
 파일이름 : AssigneeDTO.java 
 날      짜 : 2018. 7. 3.
 작 성  자 : 이 진 우
*/

package com.apollo.vo;

public class AssigneeDTO {
	
	private int tid;
	private String mid;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "AssigneeDTO [tid=" + tid + ", mid=" + mid + "]";
	}
}
