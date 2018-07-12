package com.apollo.vo;



public class CommentDTO {
	private int cmtid;
	private String comments;
	private int tid;
	private String mid;
	private String mid2;
	private String mname;
	private int cmtkind;
	private String cmtmtime;
	private int isarchive;
	private String tname;
	private String pname;
	private String image;
	private int newcheck;
	private int newcount;
	private int methodologyid;
	
	
	
	public int getMethodologyid() {
		return methodologyid;
	}
	public void setMethodologyid(int methodologyid) {
		this.methodologyid = methodologyid;
	}
	public int getNewcount() {
		return newcount;
	}
	public void setNewcount(int newcount) {
		this.newcount = newcount;
	}
	public String getMid2() {
		return mid2;
	}
	public void setMid2(String mid2) {
		this.mid2 = mid2;
	}
	public int getNewcheck() {
		return newcheck;
	}
	public void setNewcheck(int newcheck) {
		this.newcheck = newcheck;
	}
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
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
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
	public int getIsarchive() {
		return isarchive;
	}
	public void setIsarchive(int isarchive) {
		this.isarchive = isarchive;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "CommentDTO [cmtid=" + cmtid + ", comments=" + comments + ", tid=" + tid + ", mid=" + mid + ", mname="
				+ mname + ", cmtkind=" + cmtkind + ", cmtmtime=" + cmtmtime + ", isarchive=" + isarchive + ", tname="
				+ tname + ", pname=" + pname + ", image=" + image + "]";
	}

	
}
