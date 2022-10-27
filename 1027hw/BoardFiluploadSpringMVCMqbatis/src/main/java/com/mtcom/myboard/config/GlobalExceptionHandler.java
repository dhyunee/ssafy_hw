package com.mtcom.myboard.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mtcom.myboard.dto.BoardResultDto;

//개별 컨트롤러에서 처리되지 않은 예외에 대한 글로벌한 처리 담당
@ControllerAdvice
public class GlobalExceptionHandler {
	
	//모든 예외를 전부 처리
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BoardResultDto>handleErrorResponseEntity(Exception e){
		e.printStackTrace();
		//error jsp에 대한 분기가 아니라 
		//주된 요청이 비동기여서 json으로 결과(-1)를 리턴하는 형태로 작성
		System.out.println("GlobalExceptionHandelr!!");
		BoardResultDto boardResultDto=new BoardResultDto();
		boardResultDto.setResult(-1);//1성공  -1실패
		return new ResponseEntity<BoardResultDto>(boardResultDto,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
