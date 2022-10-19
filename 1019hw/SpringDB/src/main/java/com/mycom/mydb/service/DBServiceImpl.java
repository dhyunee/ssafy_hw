package com.mycom.mydb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.mydb.dao.DBDao;
import com.mycom.mydb.dto.UserDto;

@Service
public class DBServiceImpl implements DBService{
	@Autowired
	DBDao dbDao;

	@Override
	public List<UserDto> GetList() {
	
		return dbDao.GetList();
	}

	@Override
	public int Register(UserDto userDto) {
		
		return dbDao.Register(userDto);
	}

	@Override
	public UserDto UserInfo(String userId) {
		
		return dbDao.UserInfo(userId);
	}
}
