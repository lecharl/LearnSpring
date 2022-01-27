package com.kh.app17.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.app17.repository.GalleryRepository;
import com.kh.app17.service.GalleryService;
import com.kh.app17.vo.GalleryVo;

@Controller
@RequestMapping("gallery")
public class GalleryController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private GalleryRepository galleryRepository;

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
	
//	파일 다운로드 방법1) response로 처리
	@GetMapping("downnn/{no}")
	@ResponseBody
	public void down(@PathVariable int no, HttpServletResponse response) throws IOException {
		
		//1-1) {no}에 맞는 파일 정보를 가져오기
		// Repository : db에 가서 no값으로 row 가져오기
		GalleryVo g = galleryRepository.getGallery(no);
		System.out.println(g);
		
		if(g == null) {
			//사용자가 없는 거 달라고 하면 에러
			response.sendError(400);
		}
		
		//1-2) 파일 정보(g) 가져온 걸 바탕으로 클라이언트한테 파일 전송
		response.setHeader("Content-Type", "application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(g.getfName(), "UTF-8") + "\"");
		response.setHeader("Content-Length", String.valueOf(g.getfSize()));
		
		//서버 컴퓨터의 파일을 가져옴
		File file = new File("D:\\uploadForSpring", String.valueOf(no));
		//그 파일을 바이트로 변환
		byte[] data = FileUtils.readFileToByteArray(file);
		
		//data를 클라이언트에게 보내기
		response.getOutputStream().write(data);
		
	}
	
//	파일 다운로드 방법2) 스프링에서 ResponseEntity로 처리
//	ResponseEntity == 응답 데이터 덩어리
	@GetMapping("down/{no}")
	public ResponseEntity down(@PathVariable int no) throws IOException {

		//2-1) {no}에 맞는 파일 정보를 가져오기
		// Repository : db에 가서 no값으로 row 가져오기
		GalleryVo g = galleryRepository.getGallery(no);
		System.out.println(g);

		if(g == null) {
			//파일 없을 때 == 사용자가 없는 거 달라고 하면 에러
			return ResponseEntity.notFound().build();	//빌드패턴
		}
		
		//파일 있을 때
		//2-2) 파일 전송
		//파일 가져오기(서버 컴퓨터)
		File file = new File("D:\\uploadForSpring", String.valueOf(no));
		//파일을 바이트로 변환
		byte[] data = FileUtils.readFileToByteArray(file);
		
		//스프링이 원하는 타입인 ByteArrayResource 타입으로 맞추기
		ByteArrayResource res = new ByteArrayResource(data);
		
		//res(바이트 덩어리)를 ResponseEntity에 담아서 클라이언트에게 보내기
		return ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_OCTET_STREAM)
						.contentLength(g.getfSize())
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(g.getfName(), "UTF-8") + "\"")
						.header(HttpHeaders.CONTENT_ENCODING, "UTF-8")
						.body(res);
	}
	
	
	/**
	@GetMapping("test")
	@ResponseBody
//		ResponseBody 로 리턴값이 클라이언트에 직접 줄 때, 화면에 보여줌
//		+ @Controller == @RestController : 메소드가 아닌 클래스에
	public String test(HttpServletResponse response) throws IOException {
//		response.getWriter().write("zzzzzzzzzz");
		return "helloworld";
	}
	**/
}
