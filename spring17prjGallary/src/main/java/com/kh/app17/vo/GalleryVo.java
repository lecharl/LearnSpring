package com.kh.app17.vo;

import java.sql.Date;

public class GalleryVo {

	private int no;
	private String title;
	private String content;
	private String fName;
	private long fSize;
	private String fType;
	private Date enrollDate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public long getfSize() {
		return fSize;
	}
	public void setfSize(long fSize) {
		this.fSize = fSize;
	}
	public String getfType() {
		return fType;
	}
	public void setfType(String fType) {
		this.fType = fType;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", title=" + title + ", content=" + content + ", fName=" + fName + ", fSize="
				+ fSize + ", fType=" + fType + ", enrollDate=" + enrollDate + "]";
	}
	
}
