package com.mycom.more.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.more.dao.StudentDao;
import com.mycom.more.dto.StudentDto;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentDao dao;

	@Override
	public List<StudentDto> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public StudentDto detail(int studentId) {
		// TODO Auto-generated method stub
		return dao.detail(studentId);
	}

	@Override
	public int insert(StudentDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(StudentDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int studentId) {
		// TODO Auto-generated method stub
		return dao.delete(studentId);
	}
}
