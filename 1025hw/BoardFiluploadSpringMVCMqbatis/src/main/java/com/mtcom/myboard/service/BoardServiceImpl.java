package com.mtcom.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtcom.myboard.dao.BoardDao;
import com.mtcom.myboard.dto.BoardDto;
import com.mtcom.myboard.dto.BoardParamDto;
import com.mtcom.myboard.dto.BoardResultDto;

//Controller의 요청에 의해 필요한 파라미터는 받고, 원하는 결과 데이터를 만들어서 리턴해준다.
//Business logic의 핵심을 바로 Service layer에서!!!
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;

	private final int SUCCESS = 1;
	private final int FAIL = -11;

	@Override
	public BoardResultDto boardList(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			// 목록, 총건수를 가져온다.
			List<BoardDto> list = dao.boardList(boardParamDto);
			int count = dao.boardListTotalCount();
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}

		return boardResultDto;
	}

	@Override
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {

		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			// 목록, 총건수를 가져온다.
			List<BoardDto> list = dao.boardListSearchWord(boardParamDto);
			int count = dao.boardListSearchWordTotalCount(boardParamDto);
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}

		return boardResultDto;
	}

	@Override
	public BoardResultDto boardDetail(BoardParamDto boardParamDto) {

		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			// db에서 게시글 정보를 가져온다.
			BoardDto boardDto = dao.boardDetail(boardParamDto);
			// 게시글 작성자와 현재 상세조회하고 있는 사용자의 동일인 여부 확인 필요
			if (boardDto.getUserSeq() == boardParamDto.getUserSeq()) {
				boardDto.setSameUser(true);
			} else
				boardDto.setSameUser(false);

			// boardResultDto의 일부인 boardDto를 설정
			boardResultDto.setDto(boardDto);
			boardResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}

		return boardResultDto;
	}

	@Override
	public BoardResultDto boardUpdate(BoardDto boardDto) {
		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			int ret = dao.boardUpdate(boardDto);
			if(ret==1) {//update 되는 건수
				boardResultDto.setResult(SUCCESS);
			}else boardResultDto.setResult(FAIL);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardDelete(int boardId) {
		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			int ret = dao.boardDelete(boardId);
			if(ret==1) {//delete 되는 건수
				boardResultDto.setResult(SUCCESS);
			}else boardResultDto.setResult(FAIL);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardInsert(BoardDto boardDto,int userSeq) {
		
		BoardDto dto=new BoardDto();
		dto.setContent(boardDto.getContent());
		dto.setTitle(boardDto.getTitle());
		dto.setUserSeq(userSeq);
		
		BoardResultDto boardResultDto = new BoardResultDto();
		
		System.out.println("service"+ userSeq);
		try {
			int ret = dao.boardInsert(dto);
			System.out.println(ret);
			if(ret==1) {//insert 되는 건수
				boardResultDto.setResult(SUCCESS);
			}else boardResultDto.setResult(FAIL);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

}
