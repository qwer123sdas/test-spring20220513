package com.choong.spr.mapper;

import org.apache.ibatis.annotations.Param;

import com.choong.spr.domain.MemberDto;

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
	
	// 네이버 아이디 연동 여부 확인
	int signUpCheck(@Param("memberKaKao") int memberKaKao, @Param("name")String name, @Param("email")String email);
}


