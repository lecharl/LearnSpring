package com.kh.app999.notice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.app999.notice.vo.NoticeVo;

@Repository
public class NoticeDaoImpl implements NoticeDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int write(NoticeVo vo) throws Exception {
		return sqlSession.insert("notice.insertNotice", vo);
	}

	@Override
	public List<NoticeVo> getNoticeList() throws Exception {
		return sqlSession.selectList("notice.getNoticeList");
	}

	@Override
	public int deleteNotice(String[] delArr) throws Exception {
		return sqlSession.update("notice.deleteNotice", delArr);
	}

}
