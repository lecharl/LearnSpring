package com.kh.app999.member.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.app999.member.entity.MemberDto;
import com.kh.app999.member.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService service;

	//로그인 화면 보여주기
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	//로그인 로직 처리
	@PostMapping("login")
	public String login(MemberDto dto) {
		return "member/login";
	}
	
	//회원가입 화면 보여주기
	@GetMapping("join")
	public String join() {
		return "member/join";
	}
	
	//회원가입 로직 처리
	@PostMapping("join")
	public String join(MemberDto dto) throws Exception {
		System.out.println(dto);
		
		int result = service.join(dto);
		return "member/join";
	}
}
