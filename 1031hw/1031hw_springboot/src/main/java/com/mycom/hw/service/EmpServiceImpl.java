package com.mycom.hw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.hw.dao.EmpDao;
import com.mycom.hw.dto.EmpDto;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpDao dao;
	
	@Override
	public List<EmpDto> list() {
		return dao.list();
	}

	@Override
	public int empInsert(EmpDto empDto) {
		return dao.empInsert(empDto);
	}

	@Override
	public int empUpdate(EmpDto empDto) {
		return dao.empUpdate(empDto);
	}

	@Override
	public int delete(int employeeId) {
		return dao.delete(employeeId);
	}

	
}
