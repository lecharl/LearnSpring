package com.kh.app15.member.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kh.app15.member.entity.MemberDto;
import com.kh.app15.member.repository.MemberRepository;
import com.kh.app15.member.repository.MemberRepositoryInterface;

@Component
public class MemberService implements MemberServiceInterface{
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private PasswordEncoder pe;
	@Autowired
	private MemberRepositoryInterface memberRepositoryInterface;
	

	public int join(MemberDto m) {
		//ìí¸í
		String newPwd = pe.encode(m.getPwd());
		m.setPwd(newPwd);
		//DBì insert
		return memberRepositoryInterface.join(m);
	}

}
