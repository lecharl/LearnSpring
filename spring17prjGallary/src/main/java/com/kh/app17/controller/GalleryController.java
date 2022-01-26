package com.kh.app17.controller;

import java.io.File;
import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.app17.service.GalleryService;
import com.kh.app17.vo.GalleryVo;

@Controller
@RequestMapping("gallery")
public class GalleryController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private GalleryService galleryService;

	@GetMapping("upload")
	public String upload() {
		return "gallery/upload";
	}
	
	@PostMapping("upload")
	public String upload(GalleryVo g, MultipartFile f) throws IllegalStateException, IOException {
//		System.out.println(g.getTitle());
//		System.out.println(g.getContent());
//		System.out.println(f.getOriginalFilename());
		
		//파일 없으면
		if(f.isEmpty()) {
			return "redirect:/error404";
		}
		
//		Service
		int result = galleryService.upload(g, f);
		if(result > 0) {
			return "redirect:/gallery/upload";
		}else {
			return "redirect:/error404";
		}
		
	}
}
