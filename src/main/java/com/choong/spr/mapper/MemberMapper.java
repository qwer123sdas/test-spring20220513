package com.choong.spr.mapper;

import org.apache.ibatis.annotations.Param;

import com.choong.spr.controller.naver.NaverUser;
import com.choong.spr.domain.MemberDto;
import com.choong.spr.domain.NaverDto;

public interface MemberMapper {
	// 로그인 하기
	String userLogin(MemberDto dto) throws Exception;
	
	// 회원 가입
	void singUp(MemberDto dto);
	
	// 아이디 중복 검사
	int idCheck(String id);
	
	// 회원 정보 페이지
	MemberDto userDetail(String id);

	// 회원 정보 수정
	void editUser(MemberDto dto);

	// 회원 탈퇴
	void deletUser(String id);
	
	// BO, 유저 이름 가져오기
	MemberDto getUserByUserName(String userName);
	
	// BO, 유저 아이디 가져오기
	NaverDto getUserBySnsId(String snsId);
}


