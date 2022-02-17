package com.kh.app999.notice.vo;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVo {

	private long no;
	private String title;
	private String content;
	private long writer;
	private Date writeDate;	//작성날짜
	private Date editDate;	//최종수정날짜
	private String del;
	//JOIN한 테이블의 컬럼을 넣는다고???
	private String userNick;
}
