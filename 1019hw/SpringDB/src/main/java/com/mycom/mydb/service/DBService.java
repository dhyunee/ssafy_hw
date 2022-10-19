package com.mycom.mydb.service;

import java.util.List;

import com.mycom.mydb.dto.UserDto;

public interface DBService {
	List<UserDto> GetList();
	int Register(UserDto userDto);
	UserDto UserInfo(String userId);
}
