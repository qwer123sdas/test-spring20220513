package com.choong.spr.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.mapper.MemberMapper;

@Service
public class MemberService {
	@Autowired
	MemberMapper mapper;
	
	public int loginProcess(String userID, int userPW) {
		int cnt = 0;
		// 정보 일치 여부 확인
		cnt = mapper.getUser(userID);
		
		/*
		 * -2 : 아이디 없음
		 * -1 : 서버 오류
		 * 0 :비밀번호 틀림
		 * 1 : 로그인 성공
		 */
		
		
		
		

	}
	
}
