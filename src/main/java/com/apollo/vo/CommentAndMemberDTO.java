package com.apollo.vo;

import java.sql.Date;

public class CommentAndMemberDTO {
	private int cmtid;
	private String comments;
	private int tid;
	private String mid;
	private int cmtkind;
	private String cmtmtime;
	private String mname;
	private String pwd;
	private Date rday;
	private String pnum;
	private String image;
	private String deptname;
	private String apollokey;
	private String position;
	public int getCmtid() {
		return cmtid;
	}
	public void setCmtid(int cmtid) {
		this.cmtid = cmtid;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getCmtmtime() {
		return cmtmtime;
	}
	public void setCmtmtime(String cmtmtime) {
		this.cmtmtime = cmtmtime;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRday() {
		return rday;
	}
	public void setRday(Date rday) {
		this.rday = rday;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getApollokey() {
		return apollokey;
	}
	public void setApollokey(String apollokey) {
		this.apollokey = apollokey;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
}
