package com.kh.app15.member.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kh.app15.member.entity.MemberDto;

@Component
public class MemberRepository implements MemberRepositoryInterface{
	
	@Autowired
	private SqlSession sqlSession;

	//회원가입
	public int join(MemberDto m) {
		return sqlSession.insert("member.join", m);
	}
}
