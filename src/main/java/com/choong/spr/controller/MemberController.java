package com.choong.spr.controller;


import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	private String CLIENT_ID = "myKQG3U17i94iAlkHWR4";
	// 로그인 페이지-----------------------------------------------------
	@GetMapping("loginPage")
	public void userLoginPage1(HttpSession session, Model model) throws Exception{

	}

	// 로그인 하기
	@RequestMapping("login")
	public ModelAndView userLogin(@ModelAttribute MemberDto dto, HttpSession session, Model model) throws Exception {
		boolean result = service.userLogin(dto, session);
		ModelAndView mav = new ModelAndView();

		if (result == true) {
			mav.setViewName("redirect:/ex01/list");
		} else {
			//model.addAttribute(, );
			System.out.println("로그인 안됨");
			model.addAttribute("message", "다시 로그인 해주세요.");
			mav.setViewName("login");
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

	@PostMapping("signUp")                   // 네이버 고유 id 처리
	public String signUp(@ModelAttribute MemberDto dto, int id) throws Exception {
		service.signUp(dto, id);
		return "redirect:/member/loginPage";
	}

	// 아이디 중복 검사
	@PostMapping("/idCheck")
	public void idCheck(String id, HttpServletResponse res) throws Exception {
		int result = 0;
		if (service.idCheck(id) != 0) {
			result = 1;
		}
		res.getWriter().print(result);
	}

	// 회원 정보 페이지 --------------------------------------------------------------------
	@RequestMapping("userDetailPage")
	public void userDetailPage(HttpSession session, Model model) throws Exception {
		String id = (String) session.getAttribute("id");
		MemberDto memberDto = service.userDetail(id);

		session.setAttribute("name", memberDto.getMemberName());
		model.addAttribute("memberDto", memberDto);
	}

	// 회원 정보 수정
	@PostMapping("editUser")
	public String editPage(MemberDto dto, HttpSession session) {
		service.editUser(dto);

		return "redirect:/member/userDetailPage";
	}

	// 회원 탈퇴
	@PostMapping("deleteUser")
	public String deleteUser(HttpSession session) {
		String id = (String) session.getAttribute("id");
		service.deleteUser(id);

		return "redirect:/member/loginPage";
	}


}
