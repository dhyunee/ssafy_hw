package com.mycom.myboard.dto;

import java.util.List;

public class BoardResultDto {
	// Spring에서 response를 json으로 처리하기 위해 미리 response에서 리턴값으로 사용하는 항목들을 dto로 만든다.
	private int result;
	private BoardDto dto; // 게시글 하나
	private List<BoardDto> list; // 게시글 목록
	private int count; // 게시글 총 건수
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public BoardDto getDto() {
		return dto;
	}
	public void setDto(BoardDto dto) {
		this.dto = dto;
	}
	public List<BoardDto> getList() {
		return list;
	}
	public void setList(List<BoardDto> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
