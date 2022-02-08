package com.kh.app25.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.app25.member.entity.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{

	
	@Autowired
	private SqlSession session;
	
//	@Override
//	public void join() throws Exception{
//		int x = 1/0;
//		
//	}

	@Override
	public int join(MemberDto dto) throws Exception {
		return session.insert("member.join", dto);
	}

}
