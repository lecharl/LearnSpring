package com.kh.app17.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.app17.vo.GalleryVo;

@Repository
public class GalleryRepositoryImpl implements GalleryRepository {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int getSeq() {
		return sqlSession.selectOne("gallery.getSeq");
	}

	@Override
	public int upload(GalleryVo g) {
		return sqlSession.insert("gallery.upload", g);
	}

	@Override
	public GalleryVo getGallery(int no) {
		// no를 기준으로 row 하나 가져오기
		return sqlSession.selectOne("gallery.getByNo", no);
	}

}
