package com.choong.spr.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
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

	// 로그인 페이지-----------------------------------------------------
	@GetMapping("loginPage")
	public void userLoginPage1() {

	}

	// 로그인 하기
	@PostMapping("login")
	public ModelAndView userLogin(@ModelAttribute MemberDto dto, HttpSession session, Model model) throws Exception {
		boolean result = service.userLogin(dto, session);
		ModelAndView mav = new ModelAndView();

		if (result == true) {
			mav.setViewName("redirect:/ex01/list");
		} else {
			//model.addAttribute(, );
			System.out.println("로그인 안됨");
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
	public String signUp(@ModelAttribute MemberDto dto) throws Exception {
		service.signUp(dto);
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

	// 네이버 로그인 ----------------------------------------------------
	@RequestMapping("naverlogin")
	public void naverLogin() {

	}

	@RequestMapping("naverCallBack")
	public void callback() {
	}


	@RequestMapping("personalInfo")
	public void personalInfo(HttpServletRequest request, HttpSession session) throws Exception {
		String token = "AAAAOCEQBBeCjzP9sTGcOpbD9DNpYyYcgm75YANDzlPcmDk2ZNNoKUv5I3Sm5NiKRgLPjH-IJ70aoyqFFzh9y5UeTWs";
		// 네이버 로그인 접근 토큰; 여기에 복사한 토큰값을 넣어줍니다.       
		String header = "Bearer " + token; // Bearer 다음에 공백 추가     
		try {
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출           
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생          
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
