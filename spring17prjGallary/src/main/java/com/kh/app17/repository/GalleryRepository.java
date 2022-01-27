package com.kh.app17.repository;

import com.kh.app17.vo.GalleryVo;

public interface GalleryRepository {

	//파일번호 가져오기
	int getSeq();
	
	//디비에 VO 저장
	int upload(GalleryVo g);
	
	GalleryVo getGallery(int no);
}
