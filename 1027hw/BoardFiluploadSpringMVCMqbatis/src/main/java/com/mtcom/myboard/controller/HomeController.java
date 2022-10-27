package com.mtcom.myboard.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//jsp 3개의 이동에 대한 처리만
@Controller
public class HomeController {
	
	@GetMapping(value= {"/","/board"})
	public String home() {
		return "/board/boardMain";
	}
	
	@GetMapping(value= "/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping(value= "/register")
	public String register() {
		return "/register";
	}
}
