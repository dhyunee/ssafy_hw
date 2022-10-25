package com.mtcom.myboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtcom.myboard.dto.UserDto;
import com.mtcom.myboard.dto.UserResultDto;
import com.mtcom.myboard.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	private final int SUCCESS=1;
	
	//httpstatus code로 처리 결과를 wrapping하기 위해 responseentity사용
	@PostMapping(value="/register")
	public ResponseEntity<Map<String,String>>register(UserDto dto){
		UserResultDto userResultDto=userService.userRegister(dto);
		Map<String,String>map=new HashMap<>();
		if(userResultDto.getResult()==SUCCESS) {
			map.put("result", "success");
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
			
		}map.put("result","fail");
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.NOT_FOUND);
	}
}
