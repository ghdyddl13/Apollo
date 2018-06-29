package com.apollo.vo;



public class CommentDTO {
	private int cmtid;
	private String comments;
	private int tid;
	private String mid;
	private String mname;
	private int cmtkind;
	private String cmtmtime;
	private int isarchive;
	private String tname;
	private String pname;
	private String image;
	
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
