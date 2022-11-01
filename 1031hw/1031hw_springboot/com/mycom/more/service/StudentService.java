package com.mycom.more.service;

import java.util.List;

import com.mycom.more.dto.StudentDto;

public interface StudentService {
	List<StudentDto> list();
	StudentDto detail(int studentId);
	int insert(StudentDto dto);
	int update(StudentDto dto);
	int delete(int studentId);
}
