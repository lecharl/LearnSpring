package com.kh.app05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member")
public class MemberController {

	//get일 때만 처리하게끔 아래처럼
//	@RequestMapping(value = "/join", method = RequestMethod.GET)
	@GetMapping("/join")
	public String join() {
		System.out.println("join get 요청 처리함");
		// get+post해서, 구분을 안해놔서 두번 나옴
		// 페이지 로딩할때, submit할 때
		return "member/join";
	}
	
	//post일 때만 처리하게끔 아래처럼
//	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@PostMapping("/join")
	public String join2() {
		System.out.println("join post 요청 처리함");
//		회원가입 성공하면 아래로
		return "member/join_result";
	}
}
