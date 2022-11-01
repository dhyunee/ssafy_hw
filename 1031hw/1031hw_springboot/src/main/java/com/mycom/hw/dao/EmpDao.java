package com.mycom.hw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.hw.dto.EmpDto;
@Mapper
public interface EmpDao {
	List<EmpDto>list();
	int empInsert(EmpDto empDto);
	int empUpdate(EmpDto empDto);
	int delete(int employeeId);
	
}
