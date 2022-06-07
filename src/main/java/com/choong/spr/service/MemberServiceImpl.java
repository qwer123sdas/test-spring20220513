package com.choong.spr.service;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper mapper;
	
	@Autowired // 비밀번호 암호화
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private static Hashtable<String, String> loginUsers = new Hashtable<String, String>();

	// 로그인 하기
	@Override
	public boolean userLogin(MemberDto dto, HttpSession session) throws Exception {
		boolean isLogin = isLogin(dto.getMemberID());
		
		if (!isLogin) {
			String name = mapper.userLogin(dto);
			if (name != null && name != "") {
				MemberDto user = mapper.userDetail(dto.getMemberID());
				String userName = user.getMemberName();
				String userId = user.getMemberID();
				setSession(session, dto, userName, userId);
				return true;
			}
			
		}
		return false;
	}

	
	// 회원가입
	public void signUp(MemberDto dto) throws Exception {
		
		// 암호 인코더
		String encodePW = passwordEncoder.encode(dto.getMemberPW());
		dto.setMemberPW(encodePW);
		dto.setEnable(1);
		mapper.singUp(dto);
	}
	
	
	// 로그인 되어있는지 확인
	public boolean isLogin(String id) {
		boolean isLogin = false;

		Enumeration<String> e = loginUsers.keys();
		String key = "";

		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			if (id.equals(loginUsers.get(key))) {
				isLogin = true;
			}
		}
		return isLogin;

	}
	
	// 아이디 중복검사 및 session에 로그인 한 정보 저장(이름)
	public void setSession(HttpSession session, MemberDto dto, String userName, String userId) {
		loginUsers.put(session.getId(), dto.getMemberID());
		session.setAttribute("name", userName);
		session.setAttribute("id", userId);
	}

	// 아이디 중복 검사
	public int idCheck(String id) {
		return mapper.idCheck(id);
	}
	//  닉네임 중복 검사
	public int nickNameCheck(String nickName) {
		return mapper.nickNameCheck(nickName);
	}


	// 로그 아웃
	public void logout(HttpSession session) {
		loginUsers.remove(session.getId());
		session.invalidate();
	}


	
	// 회원홈페이지 들어갈 때, 회원정보 가져오기
	public MemberDto userDetail(String id) {
		return mapper.userDetail(id);
	}

	// (비밀번호 제외)회원 정보 수정
	public boolean editUser(MemberDto dto, String inputPW, String newPW) {
		
		MemberDto presentDto = mapper.userDetail(dto.getMemberID());
		String presentEncodePW = presentDto.getMemberPW();

		System.out.println("inputPW");
		System.out.println(presentEncodePW);
	
		// 기존 password가 일치할 때만 계속 진행
		if(passwordEncoder.matches(inputPW, presentEncodePW)) {
			
			// 비밀번호를 수정안할 때
			if(newPW.equals("")) {
				System.out.println("비밀번호 제외 수정 성공");
				presentDto.setMemberPW(passwordEncoder.encode(newPW));
				return mapper.editUserExceptPW(dto) == 1;
			}
			// 비밀번호 수정할 때
			System.out.println("비밀번호까지 수정 성공!");
			String encodedNewPW = passwordEncoder.encode(newPW);
			return mapper.editUserALL(dto, encodedNewPW) == 1;
		}
		System.out.println("결과 false");
		
		return false;
	}
	
	
	
	public void addAuth(int memberNo) {
		// add auth(권한 주기)
		mapper.insertAuth(memberNo, "ROLE_USER");  //'ROLE_'를 통해 보안~~에 줌~~ 
		
	}
	

	// 회원 탈퇴
	public void deleteUser(String id) {
		// 댓글 삭제
		
		// 게시글 삭제
		
		
	// 프로시저로 처리하기
		// 고유 번호 가져오기
		int memberNO = mapper.getUserNumberById(id);
		
		// 권위 삭제 + 계정 데이터 삭제
		mapper.deletUser(memberNO);
	}

	// 비밀번호 찾기(아이디, 이메일 검사)
	public boolean selectMemberByNameAndEmail(String id, String email) {
		return mapper.selectMemberByNameAndEmail(id, email) == 1;
	}

	// 비밀번호 찾기(비밀번호 변경)
	public void newPWChange(String id, String pw) {
		String EncodedPW = passwordEncoder.encode(pw); 
		mapper.restPW(id, EncodedPW);
	}








	







}
