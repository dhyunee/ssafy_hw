package com.mtcom.myboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mtcom.myboard.dto.BoardDto;
import com.mtcom.myboard.dto.BoardFileDto;
import com.mtcom.myboard.dto.BoardParamDto;
import com.mtcom.myboard.dto.UserDto;

//dao layer는 Controller, Service를 바라보고 코드를 작성X , DB 등 Persistance를 보고 작성
@Mapper
public interface BoardDao {
	//목록
	//limit, offset
	public List<BoardDto>boardList(BoardParamDto boardParamDto); 
	int boardListTotalCount();
	
	//searchWord
	public List<BoardDto>boardListSearchWord(BoardParamDto boardParamDto); 
	int boardListSearchWordTotalCount(BoardParamDto boardParamDto);
	
	//글 상세
	BoardDto boardDetail(BoardParamDto boardParamDto);
	
	//글 수정
	int boardUpdate(BoardDto dto);
	
	//글 삭제
	int boardDelete(int boardId);
	
	//글 등록
	int boardInsert(BoardDto dto);//게시글 등록
	int boardFileInsert(BoardFileDto dto);//첨부파일 등록
	List<String> boardFileUrlDeleteList(int boardId);
	void boardFileDelete(int boardId);

}
