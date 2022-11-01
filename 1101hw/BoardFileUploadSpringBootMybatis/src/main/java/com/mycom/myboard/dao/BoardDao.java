package com.mycom.myboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myboard.dto.BoardDto;
import com.mycom.myboard.dto.BoardFileDto;
import com.mycom.myboard.dto.BoardParamDto;

// dao layer는 Controller, Service를 바라보고 코드를 작성하지 않는다. DB 등 Persistance를 보고 작성한다.
@Mapper
public interface BoardDao {
	
	// 목록
	// limit, offset
	List<BoardDto> boardList(BoardParamDto boardParamDto);
	int boardListTotalCount();
	
	// searchWord
	List<BoardDto> boardListSearchWord(BoardParamDto boardParamDto);
	int boardListSearchWordTotalCount(BoardParamDto boardParamDto);
	
	// 게시글 상세
	BoardDto boardDetail(BoardParamDto boardParamDto);
	// 특정 게시글에 첨부된 첨부파일 정보들
	List<BoardFileDto> boardDetailFileList(int boardId);
	
	// 게시글 수정
	int boardUpdate(BoardDto dto);
	// 해당 게시글에 첨부된 파일 url => 물리적인 파일 삭제에 사용
	List<String> boardFileUrlDeleteList(int boardId); 
	
	// 삭제
	int boardDelete(int boardId);
	int boardFileDelete(int boardId); // 게시글 파일 정보 삭제
	
	
	// 게시글 등록
    int boardInsert(BoardDto dto);
    
    // 첨부 파일 등록
    int boardFileInsert(BoardFileDto dto);
	
	// 조회수 관련
    // 1 user 1 view 조건으로 readCount 처리
    int boardUserReadCount(BoardParamDto boardParamDto); // 2개의 파라미터 mybatis map 대응 <- Dto
    int boardUserReadInsert(@Param("boardId") int boardId, @Param("userSeq") int userSeq); // 2개의 파라미터 my batis map 대응 <- @Param
    
    int boardReadCountUpdate(int boardId); // 게시글 조회수 1 증가
    int boardReadCountDelete(int boardId); // 게시글 조회수 테이블 삭제
}
