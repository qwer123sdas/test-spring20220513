package com.choong.spr.BO;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choong.spr.controller.naver.NaverUser;
import com.choong.spr.domain.MemberDto;
import com.choong.spr.domain.NaverDto;
import com.choong.spr.mapper.MemberMapper;
@Service
public class MemberBO {
	private static final String USER_SESSION = "USER";
	private static final String SNS_USER_SESSION = "SNS_USER";
	private static final String JOIN_USER_SESSION = "JOIN_USER";
	private static final String NAVER_SESSION = "NAVER_USER";
	
	@Autowired
	private MemberMapper mapper;
	
	// 유저이름 가져오기
	public MemberDto getUserByUserName(String userName) {
		return mapper.getUserByUserName(userName);
	}
	
	// 유저
	public void setUserSession(HttpSession session, MemberDto dto) {
		session.setAttribute(USER_SESSION, dto);
	}
	
	// ???
	public NaverUser getNaverUserSession(HttpSession session) {
		NaverUser naverUser = (NaverUser)session.getAttribute(NAVER_SESSION);
		return naverUser;
	}
	
	// 어떻게 무엇을 가져 오는 것?? 회원 정보?
	public NaverDto getUserByNaverUser(NaverUser naverUser) {
		/* TODO: implement */
		if(naverUser != null){
			String snsId = naverUser.getId();
			return (NaverDto)mapper.getUserBySnsId(snsId);
		}
		return null;
	}
	

}
