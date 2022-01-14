package com.kh.app05.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.app05.member.model.vo.MemberVo;

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
//	public String join2(@RequestParam(defaultValue = "ddd") String id, @RequestParam String pwd, @RequestParam String nick, int age) {
		public String join2(@ModelAttribute MemberVo m) {
		System.out.println("join post 요청 처리함");
		
		//방법 1 : req.getParameter()
//		String id = req.getParameter("id");
//		String pwd = req.getParameter("pwd");
//		String nick = req.getParameter("nick");
		
		//방법 2 : 메소드 자체의 파라미터(@RequestParam)
		
		//방법 3 : @ModelAttribute
		
		
		System.out.println("id : "+m.getId());
		System.out.println("pwd : "+m.getPwd());
		System.out.println("nick : "+m.getNick());
		System.out.println("age : "+m.getAge());
		
//		회원가입 성공하면 아래로
		return "member/join_result";
	}
}
