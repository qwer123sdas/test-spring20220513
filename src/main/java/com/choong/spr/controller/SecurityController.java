package com.choong.spr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.service.MemberService;


public class SecurityController {
	@Autowired
	MemberService service;
	
	@RequestMapping("login")
	public String currentUser(HttpSession session, String id, RedirectAttributes rttr) {
		System.out.println(session);
		System.out.println(id);
		MemberDto dto =  service.userDetail(id);
		String userId = dto.getMemberID();
		String userName = dto.getMemberName();
		rttr.addAttribute("id", userId);
		rttr.addAttribute("name", userName);
		return "redirect:/ex01/list";
	}
}
