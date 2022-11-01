package com.mycom.more.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


// lombok 는 setter 나 getter 등을 자동으로 생성해 주는 library 인데
// 이것이 가능하려면 현재 사용하고 있는 IDE - STS 와 lombok library 가 소통해야 됨.

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDto {

	private int studentId;
	private String studentNm;
	private String email;
	private String phone;
	
	// setters & getters
	// constructors
	// toString
	
	
}
