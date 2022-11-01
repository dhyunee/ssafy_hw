package com.mycom.more.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.more.dto.StudentDto;

@Mapper
public interface StudentDao {
	List<StudentDto> list();
	StudentDto detail(int studentId);
	int insert(StudentDto dto);
	int update(StudentDto dto);
	int delete(int studentId);
}
