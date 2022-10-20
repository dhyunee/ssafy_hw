package com.mycom.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.mybatis.dao.UserDao;
import com.mycom.mybatis.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Override
	public List<UserDto> GetList() {
		
		return userDao.GetList();
	}

	@Override
	public int Register(UserDto userDto) {
		return userDao.Register(userDto);
	}

	@Override
	public UserDto UserInfo(String userId) {
		
		return userDao.UserInfo(userId);
	}

}
