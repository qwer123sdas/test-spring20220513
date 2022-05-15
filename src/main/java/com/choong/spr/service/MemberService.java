package com.choong.spr.service;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.mapper.MemberMapper;

@Service
public class MemberService{
	@Autowired
	MemberMapper mapper;
	
	public int loginProcess (String userID, int userPW) {
		int cnt = 0;
		try {
			MemberDto dto = mapper.getUser(userID, userPW);
			
			String name = dto.getMember_Name();
			String ID = dto.getMember_ID();
			int PW = dto.getMember_PW();
			// ModelAndView model = new ModelAndView();
			/*
			 * -2 : 아이디 없음
			 * -1 : 서버 오류
			 * 0 :비밀번호 틀림
			 * 1 : 로그인 성공
			 */
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
	}



	
}
