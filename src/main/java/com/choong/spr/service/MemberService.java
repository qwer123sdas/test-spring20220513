package com.choong.spr.service;

import javax.servlet.http.HttpSession;

import com.choong.spr.domain.MemberDto;

public interface MemberService {
	// 로그인 하기
	public boolean userLogin(MemberDto dto, HttpSession session) throws Exception;

	// 회원가입
	public void signUp(MemberDto dto) throws Exception;

	// 로그인 되어있는지 확인
	public boolean isLogin(String id);

	// 아이디 중복검사 및 session에 로그인 한 정보 저장(이름)
	public void setSession(HttpSession session, MemberDto dto, String userName, String userId);

	// 아이디 중복 검사
	public int idCheck(String id);

	// 로그 아웃
	public void logout(HttpSession session);

	// 회원홈페이지 들어갈 때, 회원정보 가져오기
	public MemberDto userDetail(String id);

	// (비밀번호 제외)회원 정보 수정
	public boolean editUser(MemberDto dto, String inputPW, String newPW);

	// add auth(권한 주기)
	public void addAuth(int memberNo);

	// 비밀번호 찾기(아이디, 이메일 검사)
	public boolean selectMemberByNameAndEmail(String id, String email);

	// 비밀번호 찾기(비밀번호 변경)
	public void newPWChange(String id, String pw);
}
