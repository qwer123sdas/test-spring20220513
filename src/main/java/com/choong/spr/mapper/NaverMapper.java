package com.choong.spr.mapper;

import org.apache.ibatis.annotations.Param;

import com.choong.spr.domain.MemberDto;

public interface NaverMapper {

	void method();
	
	// 회원가입 유무
	int signUpCheck(@Param("name") String name, @Param("memberId")String memberId);
	// 로그인 회원 아이디
	MemberDto loginMemberPWAndAuth(String memberId);
	

}
