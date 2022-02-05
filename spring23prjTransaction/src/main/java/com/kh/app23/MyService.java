package com.kh.app23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
//@Transactional(isolation = )
//@Transactional(rollbackFor = Exception.class)
//@Transactional(noRollbackFor = NullPointerException.class)
//@Transactional(readOnly=true)
//오직 select쿼리만 날리겠다?
public class MyService {
	
	@Autowired
	private MyDao dao;

	public void test() {
		try {
			dao.testA();
			dao.testB();
		} catch (Exception e) {
			throw new RuntimeException("내가 생각못한 checked 예외 발생");
		}
	}

}
