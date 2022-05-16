package com.choong.spr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	// 로그인 페이지-----------------------------------------------------
	@GetMapping("loginPage")
	public void userLoginPage1() {
		
	}
	// 로그인 하기
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
	
	// 로그아웃 하기
	@GetMapping("logout")
	public String logout(HttpSession session, String path) {
		service.logout(session);
		return "redirect:/ex01/list";
	}
	
	// 회원 가입 페이지------------------------------------------------------------------

	// 회원가입
	@GetMapping("signUpPage")
	public void signUpPage() {
	}
	
	
	@PostMapping("signUp")
	public String signUp(@ModelAttribute MemberDto dto) throws Exception{
		service.signUp(dto);
		return "redirect:/member/loginPage";
	}
	
	// 아이디 중복 검사
	@PostMapping("/idCheck")
	public void idCheck(String id, HttpServletResponse res) throws Exception{
		int result = 0;
		if(service.idCheck(id) != 0) {
			result = 1;
		}
		res.getWriter().print(result);
	}

	
	// 회원 정보 페이지 --------------------------------------------------------------------
	@GetMapping("editPage")
	public void eidtPage() {
		
	}
	
	

}
