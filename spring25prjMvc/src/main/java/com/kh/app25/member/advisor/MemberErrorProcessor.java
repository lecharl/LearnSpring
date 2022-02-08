package com.kh.app25.member.advisor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

//@ControllerAdvice(basePackages = "com.kh.app25.member.controller")
@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class MemberErrorProcessor {
	
	@ExceptionHandler(Exception.class)
	public String exceptionProcess(Exception e) {
		log.error("컨트롤러 어딘가에서 예외 발생함\n {}\n", e.getMessage());
		return "common/error/exception";
	}

}
