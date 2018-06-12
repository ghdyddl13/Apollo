package kr.or.apollo.vo;

public class SubtaskDTO {
	private int subtaskid;
	private int tid;
	private String subtask;
	private int ischecked;
	
	
	
	public int getSubtaskid() {
		return subtaskid;
	}
	public void setSubtaskid(int subtaskid) {
		this.subtaskid = subtaskid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getSubtask() {
		return subtask;
	}
	public void setSubtask(String subtask) {
		this.subtask = subtask;
	}
	public int getIschecked() {
		return ischecked;
	}
	public void setIschecked(int ischecked) {
		this.ischecked = ischecked;
	}
	
	
}
