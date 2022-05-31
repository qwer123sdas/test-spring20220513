package com.choong.spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.domain.NaverDto;
import com.choong.spr.mapper.NaverMapper;

@Service
public class NaverService {
	@Autowired
	NaverMapper mapper;
	
	public void method1() {
		mapper.method();
	}
	
	// 회원가입 유무
	public int signUpCheck(String name, String memberId) {
		int cnt = mapper.signUpCheck(name, memberId);
		return cnt;
	}
	// 회원 비밀번호
	public MemberDto loginMemberPWAndAuth(String memberId) {
		return mapper.loginMemberPWAndAuth(memberId);
	}
}
