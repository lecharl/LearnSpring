package com.kh.app16.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.app16.member.vo.MemberVo;

@Controller
@RequestMapping("member")
public class MemberController {

	@GetMapping("join")
	public String join() {
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(MemberVo m) throws IllegalStateException, IOException {
		
		List<MultipartFile> list = m.getProfileList();
		
		//파일 존재 여부 체크
		if(m.isFileOk()) {
			return "redirect:/errorPage";
		}

		//파일 값들 확인
		System.out.println("size: "+ list.size());
		System.out.println("첫번째 파일이 비어있나요? "+list.get(0).isEmpty());
		
		for(MultipartFile p : list) {
			System.out.println("=======");
			System.out.println("p: "+p);
			System.out.println("p파일비어있나요? "+ p.isEmpty());
			System.out.println("p 이름: "+p.getOriginalFilename());
			System.out.println("p 크기: "+p.getSize());
			System.out.println("p 타입: "+p.getContentType());
			
			//저장, File - java.io
//		File file = new File("경로", "내가 설정하고 싶은 이름");
			File file = new File("D:/uploadForSpring", p.getOriginalFilename());
			//사용자가 올린 파일의 바이트를 읽어서
			//저 파일에다가 바이트를 넣어줘야 함
			p.transferTo(file);
		}
		
		return "redirect:/";
	}
	
}
