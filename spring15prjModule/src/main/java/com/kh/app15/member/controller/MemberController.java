package com.kh.app15.member.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.app15.member.entity.MemberDto;
import com.kh.app15.member.service.MemberService;
import com.kh.app15.member.service.MemberServiceInterface;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private PasswordEncoder pe;
	
	@Autowired
	private MemberServiceInterface memberServiceInterface;
	
	// ***회원가입***
	@GetMapping("join")
	public String join() {
		System.out.println("join~~");
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(MemberDto m) {
		
		// ***모듈화 시작!
		//1. 값을 받기 : 파라미터(MemberDto)로 받음
		
		//2. 나머지 작업(암호화, db에 insert)은 서비스 레이어한테 맡기기
		int result = memberServiceInterface.join(m);
		
		//3. 화면 선택
		//view
		if(result > 0) {
			return "redirect:/";
		} else {
			return "redirect:/member/join";
		}
	}
	
	// ***로그인***
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("login")
	public String login(MemberDto m, HttpSession session) {
		//아이디 갖고 디비가서 비번 가져오기
		MemberDto loginUser = sqlSession.selectOne("member.login", m);
		if(loginUser == null) {
			return "redirect:/member/login";
		}
		String dbPwd = loginUser.getPwd();
		
		//가져온 비번과, 사용자입력 비번이랑 일치하는지 확인
		String userPwd = m.getPwd();
		if(pe.matches(userPwd, dbPwd)) {
			//success
			//session에 담기
			session.setAttribute("loginUser", loginUser);
			return "redirect:/";
		} else {
			//fail
			return "redirect:/member/login";
		}
	}
	
	// ***로그아웃***
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
