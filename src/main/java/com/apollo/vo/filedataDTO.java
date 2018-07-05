/**
 프로젝트 : Apollo
 파일이름 : filedataDTO.java 
 날      짜 : 2018. 7. 5.
 작 성  자 : 이 진 우
*/

package com.apollo.vo;

public class filedataDTO {
	private String filename;
	private String fileSize;
	private String fileType;
	private byte[] bytes;
	
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	
	
}
