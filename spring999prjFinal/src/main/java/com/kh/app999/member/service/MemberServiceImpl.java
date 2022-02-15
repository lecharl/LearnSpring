package com.kh.app999.member.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.app999.member.dao.MemberDao;
import com.kh.app999.member.entity.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	@Override
	public int join(MemberDto dto) throws Exception{
		//회원가입 처리
		
		//회원번호, 시퀀스 nextval
		int no = dao.getMemberSeq();
		//insert 처리
		dto.setUserNo(no);
		log.info(dto.toString());
		int result = dao.insertMember(dto);
		
		
		//////////////////////
		
		//파일 업로드(우리 서버에)
		MultipartFile f = dto.getF();
		
		if(!f.isEmpty()) {
			//원래 이름	-> db에 저장
			//변경된 이름 -> db에 저장
			//서비스 레이어에서 작업하도록
			String changeName = System.currentTimeMillis()+"_"+f.getOriginalFilename();
			dto.setChangeName(changeName);
			//사이즈, 타입
			System.out.println("===============");
			System.out.println(f.getOriginalFilename());
			System.out.println(f.getSize());
			System.out.println(f.getContentType());
			System.out.println("===============");
			
			//파일을 서버에 저장
			File file = new File("D:/uploadForSpring/999prj/profile/"+ f.getOriginalFilename());
			f.transferTo(file);
			
			//db에 insert
			dao.uploadProfile(dto);
		}
		
		return result;
	}

}
