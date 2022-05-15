package com.choong.spr.service;

import javax.servlet.http.HttpSession;

import com.choong.spr.domain.MemberDto;

public interface MemberServiceImpl {
	public String loginCheck(MemberDto dto, HttpSession session);
	public void logout(HttpSession session);
}
