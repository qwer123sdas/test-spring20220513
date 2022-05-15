package com.choong.spr.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choong.spr.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberService service;
	
	// 로그인
	@GetMapping("login")
	public void login() {
		
	}
	
	@PostMapping("login")
	public String loginPrecess(String userID, int userPW) {
		int cnt = service.loginProcess(userID, userPW);
		
		if(cnt != 1) {
			return "redirect:/member/login";
		}
		
		return "redirect:/ex01/list";
	}
	
	// 회원가입
	@GetMapping("signUp")
	public void singUp() {
		
	}
	@PostMapping("signUp")
	public String singUpByLogin() {
		return "";
	}

}
