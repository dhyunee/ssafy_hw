package com.mtcom.myboard.service;

import com.mtcom.myboard.dto.UserDto;
import com.mtcom.myboard.dto.UserResultDto;

public interface UserService {
	UserResultDto userRegister(UserDto userDto);
}
