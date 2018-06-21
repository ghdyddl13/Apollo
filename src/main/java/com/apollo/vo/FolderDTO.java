package com.apollo.vo;

public class FolderDTO {
	private String fid;
	private String fname;
	private int pid;
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	@Override
	public String toString() {
		return "FolderDTO [fid=" + fid + ", fname=" + fname + ", pid=" + pid + "]";
	}
}
