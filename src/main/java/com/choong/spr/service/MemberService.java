package com.choong.spr.service;

import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.mapper.MemberMapper;

@Service
public class MemberService implements MemberServiceImpl {
	@Autowired
	MemberMapper mapper;

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
	@Override
	public void signUp(MemberDto dto) throws Exception {
		System.out.println(dto);
		dto.setMemberID(dto.getMemberID());
		dto.setMemberPW(dto.getMemberPW());
		dto.setMemberName(dto.getMemberName());
		dto.setMemberDate(LocalDateTime.now());
		dto.setMemberSex(dto.getMemberSex());
		dto.setMemberRole(dto.getMemberRole());
		
		dto.setMainAddress(dto.getMainAddress());
		dto.setDetailAddress(dto.getDetailAddress());
		dto.setZoneCode(dto.getZoneCode());
		
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

	// 로그 아웃
	public void logout(HttpSession session) {
		loginUsers.remove(session.getId());
		session.invalidate();
	}


	
	// 회원홈페이지 들어갈 때, 회원정보 가져오기
	public MemberDto userDetail(String id) {
		return mapper.userDetail(id);
	}

	// 회원 정보 수정
	public void editUser(MemberDto dto) {
		mapper.editUser(dto);
	}

	// 회원 탈퇴
	public void deleteUser(String id) {
		mapper.deletUser(id);
	}



}
