package com.choong.spr.controller;



import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberService service;

	// 로그인 페이지-----------------------------------------------------
	@GetMapping("loginPage")
	public void userLoginPage1(HttpSession session, Model model) throws Exception{

	}

	// 로그인 하기
	/*
	 * @RequestMapping("login") public ModelAndView userLogin(@ModelAttribute
	 * MemberDto dto, HttpSession session, RedirectAttributes rttr) throws Exception
	 * { boolean result = service.userLogin(dto, session); ModelAndView mav = new
	 * ModelAndView();
	 * 
	 * if (result == true) { mav.setViewName("redirect:/ex01/list"); } else {
	 * //model.addAttribute(, ); System.out.println("로그인 안됨");
	 * rttr.addFlashAttribute("message", "다시 로그인 해주세요.");
	 * mav.setViewName("redirect:/naverlogin"); } return mav; }
	 */
	
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
	public String signUp(@ModelAttribute MemberDto dto) throws Exception {
		service.signUp(dto);
		System.out.println(dto.getMemberNO());
		service.addAuth(dto.getMemberNO());
		return "redirect:/naverlogin";
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
	public String userDetailPage(HttpSession session, Model model) throws Exception {
		String id = (String) session.getAttribute("id");
		MemberDto memberDto = service.userDetail(id);

		session.setAttribute("name", memberDto.getMemberName());
		model.addAttribute("memberDto", memberDto);
		return "member/userDetailPage";
	}

	// 회원 정보 수정
	@PostMapping("editUser")
	public String editPage(MemberDto dto, String inputPW, String newPW, HttpSession session) {
		if(newPW.equals("") || newPW == null) {
			boolean success = service.editUser(dto, inputPW, null);
		}else {
			boolean success = service.editUser(dto, inputPW, newPW);
			
		}

		return "redirect:/member/userDetailPage";
	}


	

	// 회원 탈퇴
	@PostMapping("deleteUser")
	public String deleteUser(HttpSession session) {
		String id = (String) session.getAttribute("id");
		service.deleteUser(id);
		session.invalidate();
		return "redirect:/naverlogin";
	}


}
