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
			String result = mapper.userLogin(dto);
			if (result != null && result != "") {
				setSession(session, dto);
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
	
	// 아이디 중복검사 및 session에 로그인 한 정보 저장
	public void setSession(HttpSession session, MemberDto dto) {
		loginUsers.put(session.getId(), dto.getMemberID());
		session.setAttribute("id", dto.getMemberID());
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


	
	// 회원홈페이지 들어갈 때
	public MemberDto userDetail(String name) {
		return mapper.userDetail(name);
	}



}
