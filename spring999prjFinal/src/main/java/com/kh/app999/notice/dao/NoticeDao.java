package com.kh.app999.notice.dao;

import java.util.List;

import com.kh.app999.PageVo;
import com.kh.app999.notice.vo.NoticeVo;

public interface NoticeDao {

	int write(NoticeVo vo) throws Exception;

	List<NoticeVo> getNoticeList(PageVo pageVo) throws Exception;

	int deleteNotice(String[] delArr) throws Exception;

	int getNoticeCnt() throws Exception;

}
