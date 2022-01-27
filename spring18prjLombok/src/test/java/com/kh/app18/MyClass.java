package com.kh.app18;

import org.junit.Test;

import com.kh.app18.member.vo.MemberVo;

public class MyClass {

	@Test
	public void test01() {
		System.out.println("test01 called...");
		
		MemberVo m = new MemberVo();
		m.setId("lsy");
		String id = m.getId();
		
		System.out.println("id: " + id);
		
		
		MemberVo mv = new MemberVo("lsy", "1234", "Helloffice");
		System.out.println("mv: " + mv);
	}
}
