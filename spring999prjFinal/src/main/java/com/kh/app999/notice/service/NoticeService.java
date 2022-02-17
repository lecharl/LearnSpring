package com.kh.app999.notice.service;

import java.util.List;

import com.kh.app999.notice.vo.NoticeVo;

public interface NoticeService {

	int write(NoticeVo vo) throws Exception;

	List<NoticeVo> getNoticeList() throws Exception;

	int deleteNotice(String str) throws Exception;

}
