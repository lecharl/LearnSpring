package com.kh.app21;

import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestClass {

	@Test
	public void Test() {
		
		//직접 만들기
//		Logger logger = LoggerFactory.getLogger(TestClass.class);
		
		//롬복 -> @Slf4j
		
		
		System.out.println("test~~~");
		
		log.trace("trace");
		log.debug("debug");
		log.info("logger info~~~~");
		log.warn("warn");
		log.error("error");
		
	}
}
