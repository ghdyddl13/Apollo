package com.apollo.vo;
/**
 * 
  클래스명 : MyWorkMemberDTO
  날      짜 : 2018. 6. 19.
  작성자명 : 이 진 우
 */
public class MyWorkMemberDTO {
	
	private int tid;
	private String mid;
	private String mname;
	private String image;

	
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
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "MyWorkMemberDTO [tid=" + tid + ", mid=" + mid + ", mname=" + mname + ", image=" + image + "]";
	}
	
	
}
