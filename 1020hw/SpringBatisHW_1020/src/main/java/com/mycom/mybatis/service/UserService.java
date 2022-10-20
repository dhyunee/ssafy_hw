package com.mycom.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.mybatis.dto.UserDto;

public interface UserService {
	
	List<UserDto> GetList();
	int Register(UserDto userDto);
	UserDto UserInfo(String userId);

}
