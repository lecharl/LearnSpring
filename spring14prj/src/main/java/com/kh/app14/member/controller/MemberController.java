package com.kh.app14.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.app14.member.entity.MemberDto;

@Controller
@RequestMapping("member")
public class MemberController {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//회원가입***********
	@GetMapping("join")
	public String join() {
		return "member/join";
	}
	@PostMapping("join")
	public String join(@ModelAttribute MemberDto m) {
		//m 객체를 DB에 insert
		//암호화
		String newPwd = passwordEncoder.encode(m.getPwd());
		System.out.println("회원가입 pwd : "+newPwd);
		m.setPwd(newPwd);
		sqlSession.insert("member.insertMember", m);
		return "redirect:/";
	}
	
	//로그인************
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	@PostMapping("login")
	public String login(String id, String pwd) {
		String newPwd = passwordEncoder.encode(pwd);
		System.out.println("로그인 pwd : "+newPwd);
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", newPwd);
		
		//DB에서 현재 ID의 PWD 가져오기
		//그리고 그 PWD와 화면에서 입력받은 pwd를 비교하기
		//비교한 결과값이 로그인 성공 여부
		
		boolean x = passwordEncoder.matches(pwd, newPwd);
		System.out.println(x);
		MemberDto loginUser = sqlSession.selectOne("member.login", map);
		System.out.println("loginUser : "+loginUser);
		return "redirect:/";
	}
	
	//회원목록************
	@GetMapping("list")
	public String list(Model model, String searchType, String searchValue) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("t", searchType);
		map.put("v", searchValue);
		if("age".equals(searchType)) {
			map.put("v", searchValue.split(","));
		}
		//모든회원 조회
		List<MemberDto> memberList = sqlSession.selectList("member.search", map);
		
		//memberList를 화면에 전달해주기
		model.addAttribute("memberList", memberList);
		
		return "member/list";
	}
}
//미간(단위 만원) : 보톨렉스?-3 / 엘레건 -6인데 행사중 4.5 / 제오민-5