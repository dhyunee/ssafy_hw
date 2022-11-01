package com.mycom.hw.service;

import java.util.List;

import com.mycom.hw.dto.EmpDto;

public interface EmpService {
	List<EmpDto>list();
	int empInsert(EmpDto empDto);
	int empUpdate(EmpDto empDto);
	int delete(int employeeId);
}
