package com.mycom.mydb.dao;

import java.util.List;

import com.mycom.mydb.dto.UserDto;

public interface DBDao {
	List<UserDto> GetList();
	int Register(UserDto userDto);
	UserDto UserInfo(String userId);
	
}
