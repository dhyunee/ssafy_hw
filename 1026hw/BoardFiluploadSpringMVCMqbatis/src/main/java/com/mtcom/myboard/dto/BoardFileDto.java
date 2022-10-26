package com.mtcom.myboard.dto;

import java.time.LocalDateTime;

public class BoardFileDto {
	private int fielId;
	private int baordId;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private String fileUrl;
	private LocalDateTime regDt;
	
	
	public int getFielId() {
		return fielId;
	}
	public void setFielId(int fielId) {
		this.fielId = fielId;
	}
	public int getBaordId() {
		return baordId;
	}
	public void setBaordId(int baordId) {
		this.baordId = baordId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public LocalDateTime getRegDt() {
		return regDt;
	}
	public void setRegDt(LocalDateTime regDt) {
		this.regDt = regDt;
	}
	
	@Override
	public String toString() {
		return "BoardFileDto [fielId=" + fielId + ", baordId=" + baordId + ", "
				+ (fileName != null ? "fileName=" + fileName + ", " : "") + "fileSize=" + fileSize + ", "
				+ (fileContentType != null ? "fileContentType=" + fileContentType + ", " : "")
				+ (fileUrl != null ? "fileUrl=" + fileUrl + ", " : "") + (regDt != null ? "regDt=" + regDt : "") + "]";
	}
	
	
}
