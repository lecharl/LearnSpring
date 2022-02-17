package com.kh.app999.notice.dao;

import java.util.List;

import com.kh.app999.notice.vo.NoticeVo;

public interface NoticeDao {

	int write(NoticeVo vo) throws Exception;

	List<NoticeVo> getNoticeList() throws Exception;

	int deleteNotice(String[] delArr) throws Exception;

}
