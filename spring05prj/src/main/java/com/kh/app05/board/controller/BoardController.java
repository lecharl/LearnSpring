package com.kh.app05.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.app05.board.model.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@GetMapping("/insert")
	public String show() {
		System.out.println("board show~!");
		return "board/insert";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute BoardVo b) {
		System.out.println("board insert~!");
		
		System.out.println("title : "+b.getTitle());
		System.out.println("content : "+b.getContent());
		
		//리다이렉트! 절대경로로?
		return "redirect:/board/insert_result";
	}
	
	@GetMapping("/insert_result")
	public String insert_result() {
		return "board/insert_result";
	}
}
