package com.mycom.hw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.hw.dto.EmpDto;
import com.mycom.hw.service.EmpService;

@RestController
public class EmpController {
	@Autowired
	EmpService service;
	
	@GetMapping("/emp")
	public List<EmpDto>list(){
		List<EmpDto>list=service.list();
		for(EmpDto dto:list) {
			System.out.println(dto);
		}
		return list;
	}
	
	@PostMapping("/emp")
	public int empInsert(EmpDto dto) {
		return service.empInsert(dto);
	}
	
	@PutMapping("/emp/{employeeId}")
	public int empUpdate(EmpDto dto) {
		return service.empUpdate(dto);
	}
	
	@DeleteMapping("/emp/{employeeId}")
	public int empDelete(@PathVariable int employeeId) {
		return service.delete(employeeId);
	}
}
