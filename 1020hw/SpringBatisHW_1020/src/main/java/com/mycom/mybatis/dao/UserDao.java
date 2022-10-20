package com.mycom.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.mybatis.dto.UserDto;
@Mapper
public interface UserDao {
	List<UserDto> GetList();
	int Register(UserDto userDto);
	UserDto UserInfo(String userId);
}
