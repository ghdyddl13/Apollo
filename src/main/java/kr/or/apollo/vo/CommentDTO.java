package kr.or.apollo.vo;

import java.sql.Date;


public class CommentDTO {
	private int cmtid;
	private String comment;
	private int tid;
	private String mid;
	private int cmtkind;
	private Date cmtmtime;
	

	public int getCmtid() {
		return cmtid;
	}
	public void setCmtid(int cmtid) {
		this.cmtid = cmtid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
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
	public int getCmtkind() {
		return cmtkind;
	}
	public void setCmtkind(int cmtkind) {
		this.cmtkind = cmtkind;
	}
	public Date getCmtmtime() {
		return cmtmtime;
	}
	public void setCmtmtime(Date cmtmtime) {
		this.cmtmtime = cmtmtime;
	}
	
	
}
