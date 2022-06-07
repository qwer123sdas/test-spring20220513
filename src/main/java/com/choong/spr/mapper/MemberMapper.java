package com.choong.spr.mapper;

import org.apache.ibatis.annotations.Param;

import com.choong.spr.controller.naver.NaverUser;
import com.choong.spr.domain.MemberDto;
import com.choong.spr.domain.NaverDto;

public interface MemberMapper {
	// 로그인 하기
	String userLogin(MemberDto dto) throws Exception;
	
	// 회원 가입
	int singUp(MemberDto dto);
	int insertAuth(@Param("memberNO") int memberNO, @Param("role") String role);
	
	// 아이디 중복 검사
	int idCheck(String id);
	
	// 회원 정보 페이지
	MemberDto userDetail(String id);

	// 회원 정보 수정
		// 비밀번호 수정 x
	int editUserExceptPW(MemberDto dto);
		// 비밀번호 수정 o
	int editUserALL(@Param("dto")MemberDto dto, @Param("encodedNewPW")String encodedNewPW);
	
	// 회원 탈퇴
	void deletUser(int memberNO);
	
	// BO, 유저 이름 가져오기
	MemberDto getUserByUserName(String userName);
	
	// BO, 유저 아이디 가져오기
	NaverDto getUserBySnsId(String snsId);
	
	// 스프링시큐리티, 유저 아이디로 유저 이름 가져오기
	MemberDto getUserNameById(String id);
	
	// 회원 탈퇴시,, 유저 고유 번호 가져오기
	int getUserNumberById(String id);

	// 비밀번호 찾기(이름, 이메일 검사)
	int selectMemberByNameAndEmail(@Param("id")String id, @Param("email")String email);
	// 비밀번호 찾기(비밀번호 초기화)
	void restPW(@Param("resetPW") String resetPW, @Param("memberID")String memberID);


	

}


