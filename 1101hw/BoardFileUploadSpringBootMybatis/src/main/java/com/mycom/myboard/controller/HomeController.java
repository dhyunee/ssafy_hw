package com.mycom.myboard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// @RestController 아님. 비동기 요청에 대한 처리 controller가 아님
// 페이지 이동에 대한 역할 수행
@Controller
public class HomeController {

	@GetMapping(value = "/")
	public String home() {
		return "index.html"; // static folder의 index.html로 이동
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "/login.html";
	}
	
	@GetMapping(value = "/register")
	public String register() {
		System.out.println("Register");
		return "/register.html";
	}
	
	@GetMapping(value = "/board")
	public String board() {
		return "/board/boardMain.html";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 초기화
		return "/login.html";
	}
}
