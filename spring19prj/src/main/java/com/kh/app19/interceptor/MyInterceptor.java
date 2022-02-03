package com.kh.app19.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 인터셉터 만드는 방법 2가지
 1. HandlerInterceptor 인터페이스 구현하기
 2. HandlerInterceptorAdapter 클래스 상속받기
 */

public class MyInterceptor extends HandlerInterceptorAdapter{

	//인터셉터는 3가지 시점에 관여를 한다.
	//3가지 메소드 ()
	//1) preHandle
	
	//1. handle (컨트롤러 가기) 전에
	//리턴값에 따라 요청 통과시킬지 말지 결정 가능
	//핸들러 : 처리할 메소드 정보 확인 가능
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle called ...");
		System.out.println("handler : "+handler);
		//HomeController의 home(Locale, Model)가 호출됨
		
		return true;
	}
	
	//2. handle 끝나고
	//리턴값이 없음 -> 우리가 커트할 수 없음
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle called~~");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	//3. 화면 넘겨주기 전에
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion called!!!!!!!!!!!!");
		System.out.println("ex: "+ ex);
		super.afterCompletion(request, response, handler, ex);
	}
}
