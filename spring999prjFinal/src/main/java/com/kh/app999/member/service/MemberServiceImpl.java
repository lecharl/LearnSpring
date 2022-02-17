package com.kh.app999.member.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder pe;
	
	@Override
	public int join(MemberDto dto, HttpServletRequest req) throws Exception{
		//회원가입 처리
		
		//회원번호, 시퀀스 nextval
		int no = dao.getMemberSeq();
		//insert 처리
		dto.setUserNo(no);
		//암호화
		dto.setUserPwd(pe.encode(dto.getUserPwd()));
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
			
			//파일을 톰캣에 저장
			String path = req.getServletContext().getRealPath("/resources/upload/profile/");
			//파일을 서버에 저장
			//getREalPath == /spring999prjFinal/src/main/webapp
//			File file = new File("D:/uploadForSpring/999prj/profile/"+ changeName);
			File file = new File(path+ changeName);
			f.transferTo(file);
			
			//db에 insert
			dao.uploadProfile(dto);
		}
		
		return result;
	}

	@Override
	public MemberDto login(MemberDto dto) throws Exception {
		//DB에서 회원 정보 조회(기준:id)
		MemberDto dbUser = dao.getMember(dto);
		//없는 id여도 에러 안나게
		if(dbUser == null) {
			return null;
		}
		
		//비번 일치 체크
		if(pe.matches(dto.getUserPwd(), dbUser.getUserPwd())) {
			//일치함-> 멤버 리턴
			return dbUser;
		}else {
			//불일치-> null 리턴
			return null;
		}
	}

	@Override
	public MemberDto editMember(MemberDto dto) throws Exception {
		//비밀번호 한번 더 확인..근데 여기선 패스
		//비번을 입력했을 때만 수정 가능!!
		if(dto.getUserPwd().length()>0) {
			dto.setUserPwd(pe.encode(dto.getUserPwd()));
		}
		int result = dao.updateMember(dto);
		MemberDto m = null;
		if(result > 0) {
			m = dao.getMember(dto);
		}
		return m;
	}

}
