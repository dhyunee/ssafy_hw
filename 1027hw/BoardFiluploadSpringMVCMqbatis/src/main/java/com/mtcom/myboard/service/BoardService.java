package com.mtcom.myboard.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mtcom.myboard.dto.BoardDto;
import com.mtcom.myboard.dto.BoardParamDto;
import com.mtcom.myboard.dto.BoardResultDto;
import com.mtcom.myboard.dto.UserDto;

public interface BoardService {
	//총 건수를 구하는 부분이 boardList에 포함
	BoardResultDto boardList(BoardParamDto boardParamDto);
	BoardResultDto boardListSearchWord(BoardParamDto boardParamDto); 
	
	//상세
	BoardResultDto boardDetail(BoardParamDto boardParamDto);
	
	//글 수정
	BoardResultDto boardUpdate(BoardDto boardDto);
	
	//글 삭제
	BoardResultDto boardDelete(int boardId);
	
	//글 등록
	BoardResultDto boardInsert(BoardDto boardDto, MultipartHttpServletRequest request);
	
}
