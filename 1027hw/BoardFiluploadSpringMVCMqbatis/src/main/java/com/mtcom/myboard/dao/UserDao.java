package com.mtcom.myboard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mtcom.myboard.dto.UserDto;

@Mapper
public interface UserDao {
	int userRegister(UserDto userDto);
	
}
