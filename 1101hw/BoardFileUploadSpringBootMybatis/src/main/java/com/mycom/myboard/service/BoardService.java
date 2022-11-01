package com.mycom.myboard.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myboard.dto.BoardDto;
import com.mycom.myboard.dto.BoardParamDto;
import com.mycom.myboard.dto.BoardResultDto;

public interface BoardService {
	// 목록
	// 총 건수를 구현하는 부분이 boardList에 포함되어 있음
	BoardResultDto boardList(BoardParamDto boardParamDto);
	BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
	
	// 상세
	BoardResultDto boardDetail(BoardParamDto boardParamDto);
	
	// 수정
	BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);
	
	// 삭제
	BoardResultDto boardDelete(int boardId);
	
	// 등록
    BoardResultDto boardInsert(BoardDto boardDto, MultipartHttpServletRequest request);
}
