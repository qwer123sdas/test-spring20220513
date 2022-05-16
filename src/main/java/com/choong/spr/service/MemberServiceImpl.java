package com.choong.spr.service;

import javax.servlet.http.HttpSession;

import com.choong.spr.domain.MemberDto;

public interface MemberServiceImpl {
	public boolean userLogin(MemberDto dto, HttpSession session) throws Exception; 
	public void userJoin(MemberDto dto) throws Exception;

	
}
