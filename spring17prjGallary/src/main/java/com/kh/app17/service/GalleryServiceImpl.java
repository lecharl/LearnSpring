package com.kh.app17.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.app17.repository.GalleryRepository;
import com.kh.app17.vo.GalleryVo;

@Service
public class GalleryServiceImpl implements GalleryService {
	@Autowired
	private GalleryRepository galleryRepository;

	@Override
	public int upload(GalleryVo g, MultipartFile f) throws IllegalStateException, IOException {
		
		//f로 받은 걸 -> fName, fSize, fType 에 넣기 
		g.setfName(f.getOriginalFilename());
		g.setfSize(f.getSize());
		g.setfType(f.getContentType());
		
//		Repository
		//mapper에 있는 getSeq로 -> no 에 넣기
		int no = galleryRepository.getSeq();
		g.setNo(no);
				
		//디비에 VO 저장
		System.out.println(g);
		int result = galleryRepository.upload(g);
				
		//서버(내컴퓨터)에 저장
		//file 만들기
		File file = new File("D:\\uploadForSpring", String.valueOf(no));
				
		//file에 옮기기
		f.transferTo(file);		
		
		return result;
	}

}
