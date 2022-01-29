package com.kh.app20.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

//아래 패키지 하위의 친구들을 감시, 배열 형식으로 다수의 패키지 넣을 수 있다.
//@ControllerAdvice(basePackages = {"com.kh.app20", "패키지2..."})
//@ControllerAdvice(basePackages = "com.kh.app20")
@ControllerAdvice(annotations = Controller.class)
public class MyControllerAdvisor {

	//감시할 대상
//	@GetMapping("/member/join")
//	@ExceptionHandler(내가 처리할 예외 종류)
	/*
	@ExceptionHandler(NullPointerException.class)
	public String method01(NullPointerException e) {
		System.out.println("==============");
		e.printStackTrace();
		return "error/npe";
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public String method02(NumberFormatException e) {
		System.out.println("================");
		e.printStackTrace();
		return "error/number";
	}
	
	@ExceptionHandler(ArithmeticException.class)
	public String method03(ArithmeticException e) {
		System.out.println("=================");
		e.printStackTrace();
		return "error/zero";
	}
	*/
	
	@ExceptionHandler(RuntimeException.class)
	public String method01(RuntimeException e) {
		System.out.println("런타임 에러");
		return "error/npe";
	}
	@ExceptionHandler(Exception.class)
	public String method01(Exception e) {
		System.out.println("아무튼 에러");
		return "error/npe";
	}
}
