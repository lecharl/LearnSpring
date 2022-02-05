package com.kh.app23;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyDao {

	@Autowired
	private SqlSession session;
	
	public void testA() {
		session.insert("test.a");
	}
	
	public void testB() {
		session.insert("test.b");
	}

}
