package com.mycom.mydb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.mydb.dto.UserDto;
import com.mycom.mydb.service.DBService;

@Controller
public class DBController {
	@Autowired
	DBService service;

	@GetMapping(value = "/")
	public String DBTest() {
		System.out.println("DBController /");
		return "DBTest";
	}
	
	@GetMapping(value="/UserInfo/{userId}")
	@ResponseBody
	public UserDto UserInfo(@PathVariable String userId) {
		System.out.println(userId);
		UserDto dto=service.UserInfo(userId);
		return dto;
	}
	
	@GetMapping(value="/userList")
	@ResponseBody
	public List<UserDto> userList() {
		List<UserDto> list=service.GetList();
		return list;
	}
	
	@PostMapping(value="/Register")
	@ResponseBody
	public int Register(UserDto empDto) {
		System.out.println(empDto);
		int ret=service.Register(empDto);
		return ret;
	}
}
