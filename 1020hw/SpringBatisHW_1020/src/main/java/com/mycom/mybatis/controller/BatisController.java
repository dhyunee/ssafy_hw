package com.mycom.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.mybatis.dto.UserDto;
import com.mycom.mybatis.service.UserService;

@Controller
public class BatisController {
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/")
	public String DBTest() {
		System.out.println("DBController /");
		return "DBTest";
	}
	
	@GetMapping(value="/UserInfo/{userId}")
	@ResponseBody
	public UserDto UserInfo(@PathVariable String userId) {
		System.out.println(userId);
		UserDto dto=userService.UserInfo(userId);
		return dto;
	}
	
	@GetMapping(value="/userList")
	@ResponseBody
	public List<UserDto> userList() {
		List<UserDto> list=userService.GetList();
		return list;
	}
	
	@PostMapping(value="/Register")
	@ResponseBody
	public int Register(UserDto userDto) {
		System.out.println(userDto);
		int ret=userService.Register(userDto);
		return ret;
	}

	
}
