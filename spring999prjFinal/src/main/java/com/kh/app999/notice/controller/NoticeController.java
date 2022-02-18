package com.kh.app999.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.app999.PageVo;
import com.kh.app999.notice.service.NoticeService;
import com.kh.app999.notice.vo.NoticeVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("notice")
@Slf4j
public class NoticeController {
	
	@Autowired
	private NoticeService service;

	//공지사항 화면 보여주기
//	@GetMapping(value = "/list")
//		public String list(Model model) throws Exception {
	@GetMapping(value = {"/list/{page}", "/list"})
	public String list(Model model, @PathVariable(required=false) String page) throws Exception {
		if(page == null) page = "1";
		
		//페이징 객체 필요함
		int cntPerPage = 10; //한 페이지당 row10개씩 보여주기
		int pageBtnCnt = 5;	//한번에 보여줄 페이지버튼 개수
		int totalRow = service.getNoticeCnt();	//db에 있는 모든 row 개수
		PageVo pageVo = new PageVo(page, cntPerPage, pageBtnCnt, totalRow);
		
		//리스트 조회
		List<NoticeVo> list = service.getNoticeList(pageVo);
		model.addAttribute("list", list);
		model.addAttribute("page", pageVo);
		return "notice/list";
	}
	
	//공지사항 작성 화면 보여주기(디비가서 리스트 조회)
	@GetMapping("write")
	public String write() {
		
		return "notice/write";
	}
	
	//공지사항 작성 로직 처리
	@PostMapping("write")
	public String write(NoticeVo vo, HttpServletRequest req) throws Exception {
		System.out.println(vo);
		
//		한번에 많이 만들때 이렇게
//		for(int i=0; i<500; i++) {
//			service.write(vo);
//		}
		
		int result = service.write(vo);
		if(result>0) {
			return "redirect:/notice/list";
		}else {
			req.setAttribute("msg", "공지사항 작성 실패");
			return "error/errorPage";
		}
	}
	
	//공지사항 삭제
	@PostMapping("delete")
	@ResponseBody
	public String delete(String str) throws Exception {
		System.out.println(str);	// 삭제할 번호들: 1, 5, ...
		System.out.println(str.length()/2);	// 
		
		int result = service.deleteNotice(str);
		
		log.warn("선택한 row 개수 : {}", result);
		
		if(result == str.length()/2) {
			return "ok";
		}else {
			return "fail_" + result;
		}
		
	}	
	
}
