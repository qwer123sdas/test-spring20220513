package com.choong.spr.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberService service;
	
	// 로그인
	@GetMapping("loginPage")
	public void userLoginPage() {
		
	}
	
	@PostMapping("login")
	public ModelAndView userLogin(@ModelAttribute MemberDto dto, HttpSession session, Model model) throws Exception {
		boolean result = service.userLogin(dto, session);
		
		ModelAndView mav = new ModelAndView();
		
		if(result == true) {
			mav.setViewName("redirect:/ex01/list");
		}else {
			//model.addAttribute(, );
			mav.setViewName("/member/loginPage");
		}
		return mav;
	}
	
	
	/*	public String loginPrecess(String userID, int userPW) {
			int cnt = service.loginProcess(userID, userPW);
			
			if(cnt != 1) {
				return "redirect:/member/login";
			}
			
			return "redirect:/ex01/list";
		}
	*/
	
	// 회원가입
	@GetMapping("signUp")
	public void singUp() {
		
	}
	@PostMapping("signUp")
	public String singUpByLogin() {
		return "";
	}

}
