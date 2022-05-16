package com.choong.spr.service;

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

	// 로그인
	@Override
	public boolean userLogin(MemberDto dto, HttpSession session) throws Exception {
		boolean isLogin = isLogin(dto.getMemberID());
		if (!isLogin) {
			String result = mapper.userLogin(dto);
			if (result != null || result != "") {
				setSession(session, dto);
			}
			return true;
		}
		return false;
	}

	@Override
	public void userJoin(MemberDto dto) throws Exception {
		// TODO Auto-generated method stub

	}

	/*	public int loginProcess (String userID, int userPW) {
			int cnt = 0;
			try {
				MemberDto dto = mapper.getUser(userID, userPW);
				
				String name = dto.getMember_Name();
				String ID = dto.getMember_ID();
				int PW = dto.getMember_PW();
				// ModelAndView model = new ModelAndView();
				
				 * -2 : 아이디 없음
				 * -1 : 서버 오류
				 * 0 :비밀번호 틀림
				 * 1 : 로그인 성공
				 
				if(name !=null ) {
					cnt = -2;
				}else {
					cnt =  1;
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				cnt = -1;
			}
			return cnt;
		}*/

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

	public void setSession(HttpSession session, MemberDto dto) {
		loginUsers.put(session.getId(), dto.getMemberID());
		session.setAttribute("id", dto.getMemberID());
	}

}
