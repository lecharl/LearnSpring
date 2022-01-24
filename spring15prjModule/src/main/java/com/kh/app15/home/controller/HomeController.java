package com.kh.app15.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	//홈화면 보여주기
	@RequestMapping("/")
	public String home() {
		return "home";
	}
}
