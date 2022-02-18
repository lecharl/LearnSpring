package com.kh.app999.notice.service;

import java.util.List;

import com.kh.app999.PageVo;
import com.kh.app999.notice.vo.NoticeVo;

public interface NoticeService {

	int write(NoticeVo vo) throws Exception;

	List<NoticeVo> getNoticeList(PageVo pageVo) throws Exception;

	int deleteNotice(String str) throws Exception;

	int getNoticeCnt() throws Exception;

}
