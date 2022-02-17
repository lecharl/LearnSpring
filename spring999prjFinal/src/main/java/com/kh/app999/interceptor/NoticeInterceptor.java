package com.kh.app999.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.app999.member.entity.MemberDto;


//인터셉터 만드는 방법 2가지
//1. extend HandlerInterceptorAdapter
//2. implements HandlerInterceptor
public class NoticeInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		MemberDto loginUser = (MemberDto) request.getSession().getAttribute("loginUser");
		
		//관리자인지 확인 (레벨로 하면 더 좋음, 여기서는 그냥 아이디로)
		if("admin".equals(loginUser.getUserId())) {
			return true;
		}else {
			//홈으로 보내버리기, app999
//			response.sendRedirect(request.getServletContext().getContextPath());
			request.setAttribute("msg", "관리자 계정이 아닙니다.");
			request.getRequestDispatcher("/WEB-INF/views/error/errorPage.jsp").forward(request, response);
			return false;
		}
		
	}
}
