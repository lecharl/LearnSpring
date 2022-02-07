package com.kh.app24.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@EnableAspectJAutoProxy
@Component
@Aspect
@Slf4j
public class MyAspect {

	//advice, 조인포인트JoinPoint, 포인트컷
	//이 안에서 사용할 메소드를 advice라고 명명한다.
	
	//언제 : HomeController가 실행되기 전에 : @Before
	//대상 : "target(대상)" : 대상에 클래스 또는 인터페이스 지정
	//대상 : "within(대상)" : 대상에 패키지 또는 클래스 지정
	//대상 : "execution(대상)" : 대상에 표현식(접근제한자 반환형 패키지명.클래스명.메소드명(매개변수))
//	@Before("target(com.kh.app24.HomeController)")
	public void myAdvice01() {
		log.info("Before =============");
	}
	
//	@After("target(com.kh.app24.HomeController)")
	public void myAdvice02() {
		log.info("After ==============");
	}
	
//	@Before("target(com.kh.app24.aop.service.MyService)")
	public void myAdvice03() {
		log.info("MyService 클래스 실행 이전~~");
	}
	
//	@After("target(com.kh.app24.aop.service.MyService)")
	public void myAdvice04() {
		long time = System.currentTimeMillis();
		log.info("현재시간(ms) ::: {}", time);
	}
	
	/* @Around(대상)
	 * 리턴타입 : Object
	 * 파라미터 : ProceedingJoinPoint
	 * 예외 : Throwable
	 * Before, After 둘다 간섭 가능
	 * */
//	@Around("target(com.kh.app24.aop.service.MyService)")
//	@Around("target(com.kh.app24.aop.dao.MyDao)")
//	@Around("within(com.kh.app24.aop..*)")
//	@Around("within(com..*)")
//	@Around("execution(public void com.kh.app24.aop.dao.MyDao.test())")
	//public 은 생략 가능
	//리턴타입 상관없으면 *
	//매개변수에 * : 매개변수 1개 이상
	//매개변수에 .. : 매개변수 0개 이상
	//패키지명, 클래스명, 메소드명 : 표현식으로 처리 가능
//	@Around("execution(* com.kh.app24.aop.dao.MyDao.select*(..))")
//	@Around("execution(* com.kh.app24.aop.dao.MyDao.te*(..))")
//	@Around("execution(* com.kh.app24.aop.dao.MyDao.*st(..))")
//	@Around("execution(* com.kh.app24.aop.dao.*Dao.*(..))")
//	@Around("execution(* com.kh.app24..*.*(..))")
	@Around("execution(* com..*.*(..))")
//	@Around("execution(* com..*Dao*.*(..))")
	public Object myAdvice05(ProceedingJoinPoint jp) throws Throwable {
		
		System.out.println("elapse check start ==========com.kh.app24.aop..");
		
		//before
		long start = System.currentTimeMillis();
//		long start = System.nanoTime();
		
		//타겟 메소드 호출
		Object obj = jp.proceed();
		
		//after
		long end = System.currentTimeMillis();
		
		long elapse = end - start;
		log.info("elapse : {}", elapse);
		
		return obj;
	}
	
}
