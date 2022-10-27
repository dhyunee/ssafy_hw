package com.mtcom.myboard.dto;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class BoardDto {
	private int boardId;
	private int userSeq;
	private String userName;
	private String userProfileImageUrl;
	private String title;
	private String content;
	private LocalDateTime regDt;
	private int readCount;//조회수
	
	private boolean sameUser;//게시글을 보는 user와 작성한 user가 같은지 여부
	
	private List<BoardFileDto>fileList;//해당 게시글에 첨부된 파일 목록

	public BoardDto() {}
	
	public BoardDto(int userSeq, String title, String content) {
		super();
		this.userSeq = userSeq;
		this.title = title;
		this.content = content;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}

	public void setUserProfileImageUrl(String userProfileImageUrl) {
		
		if(userProfileImageUrl==null||"null".equals(userProfileImageUrl)||"".equals(userProfileImageUrl)) {
			this.userProfileImageUrl="/img/noProfile.png";
		}else this.userProfileImageUrl = userProfileImageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		//Mybatis date mapping
		this.regDt = LocalDateTime.ofInstant(regDt.toInstant(), ZoneId.systemDefault());
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public boolean isSameUser() {
		return sameUser;
	}

	public void setSameUser(boolean sameUser) {
		this.sameUser = sameUser;
	}

	public List<BoardFileDto> getFileList() {
		return fileList;
	}

	public void setFileList(List<BoardFileDto> fileList) {
		this.fileList = fileList;
	}

	
	@Override
	public String toString() {
		return "BoardDto [boardId=" + boardId + ", userSeq=" + userSeq + ", "
				+ (userName != null ? "userName=" + userName + ", " : "")
				+ (userProfileImageUrl != null ? "userProfileImageUrl=" + userProfileImageUrl + ", " : "")
				+ (title != null ? "title=" + title + ", " : "") + (content != null ? "content=" + content + ", " : "")
				+ (regDt != null ? "regDt=" + regDt + ", " : "") + "readCount=" + readCount + ", sameUser=" + sameUser
				+ ", " + (fileList != null ? "fileList=" + fileList : "") + "]";
	}
	
}
