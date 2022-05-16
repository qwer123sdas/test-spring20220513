package com.choong.spr.mapper;

import com.choong.spr.domain.MemberDto;

public interface MemberMapper {
	// 로그인 하기
	String userLogin(MemberDto dto) throws Exception;
	
	// 회원 가입
	void singUp(MemberDto dto);
	
	// 아이디 중복 검사
	int idCheck(String id);
}


