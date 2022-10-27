package com.mtcom.myboard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mtcom.myboard.dto.UserDto;

@Mapper
public interface LoginDao {
	UserDto login(String userEmail);
}
