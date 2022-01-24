package com.kh.app15.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kh.app15.member.entity.MemberDto;
import com.kh.app15.member.repository.MemberRepositoryInterface;

@Component
public class MemberServiceImpl implements MemberServiceInterface{

	@Autowired
	private MemberRepositoryInterface memberRepositoryInterface;
	@Autowired
	private PasswordEncoder pe;
	
	@Override
	public int join(MemberDto m) {
		//암호화
//		String newPwd = pe.encode(m.getPwd());
//		m.setPwd(newPwd);
		//암호화 사용하지 않고 그냥 insert
		//DB에 insert
		return memberRepositoryInterface.join(m);
	}

}
