package com.choong.spr.controller;



import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choong.spr.configuration.ApplicationConfig;
import com.choong.spr.domain.Mail;
import com.choong.spr.domain.MemberDto;
import com.choong.spr.service.MailService;
import com.choong.spr.service.MemberServiceImpl;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberServiceImpl service;

	@Autowired
	MailService mailService;
	
	
	// 로그인 페이지-----------------------------------------------------
	@GetMapping("loginPage")
	public void userLoginPage1(HttpSession session, Model model) throws Exception{

	}

	// 로그인 하기
	// 스프링 시큐리티 쓰면서 필요없어짐
	/*  
	@RequestMapping("login") 
	public ModelAndView userLogin(@ModelAttribute MemberDto dto, HttpSession session, RedirectAttributes rttr) throws Exception{ 
		boolean result = service.userLogin(dto, session); 
		ModelAndView mav = new ModelAndView();
	  
		if (result == true) { 
			 mav.setViewName("redirect:/ex01/list"); 
		}else {
			//model.addAttribute(, ); System.out.println("로그인 안됨");
			 
			rttr.addFlashAttribute("message", "다시 로그인 해주세요.");
			mav.setViewName("redirect:/naverlogin"); 
		} 
		
		return mav; 
	}
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
		
		model.addAttribute("memberDto", memberDto);
		return "member/userDetailPage";
	}

	// 회원 정보 수정
	@PostMapping("editUser")
	public String editPage(MemberDto dto, String inputPW, String newPW, HttpSession session) {
		System.out.println("비밀번호 수정 ? : " + newPW);
		
		if(newPW.equals("") || newPW == null) {
			System.out.println("비밀번호 수정 x");
			boolean success = service.editUser(dto, inputPW, "");
		}else {
			System.out.println("비밀번호 수정 o");
			boolean success = service.editUser(dto, inputPW, newPW);
			
		}

		return "redirect:/member/userDetailPage";
	}
	
	
	// 비밀번호 찾기--------------------------------------------------------------
	@RequestMapping("pw_find")
	public void pwFind() {
		System.out.println("비밀번호 찾기 홈페이지");
	}
	
	@PostMapping("pw_auth")
	public ModelAndView pwAuth(String id, String email, HttpServletRequest request) {
		System.out.println("비밀번호 인증 홈페이지");
		boolean success = service.selectMemberByNameAndEmail(id, email);
		if(success) {
			// 랜덤 난수 설정
			Random random = new Random();
			int num = random.nextInt(9999);
			System.out.println(num);
			String setfrom = "temp@naver.com"; // naver 
			String tomail = email; //받는사람
			String content = System.getProperty("line.separator") + "안녕하세요 회원님" 
							+ System.getProperty("line.separator") + "임시 비밀번호찾기(변경) 인증번호는 " + num + " 입니다."
							+ System.getProperty("line.separator"); 
			
			// 메일 보내기
	        Mail mail = new Mail();
	        mail.setMailFrom(setfrom);
	        mail.setMailTo(tomail);
	        
	        mail.setMailSubject("[TEST]비밀번호변경 인증 이메일 입니다.");
	        mail.setMailContent(content);
	        mailService.sendEmail(mail);
	        
	        ModelAndView mv = new ModelAndView();
	        mv.addObject("num", num);
	        mv.addObject("id", id);
	        
	        mv.setViewName("member/pw_auth");
	        return mv;
		}else {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("member/pw_find");
			return mv;
		}
		
	}
	
	@RequestMapping("pw_set")
	public String pwAuth(@RequestParam(value="email_injeung") String email_injeung,
						@RequestParam(value = "num") String num,
						@RequestParam(value = "id") String id,
						Model model) {
		if(email_injeung.equals(num)) {
			model.addAttribute("id", id);
			return "member/pw_new";
		}else {
			return "member/pw_find";
		}
	}
	
	@GetMapping("pw_new")
	public void pwNew() {
		
	}
	@PostMapping("pw_new")
	public String pwFindChange(String id, String password) {
		service.newPWChange(id, password);
		return "redirect:/naverlogin";
	}
	// ------------------------------------------------------------------------------

	// 회원 탈퇴
	@PostMapping("deleteUser")
	public String deleteUser(HttpSession session) {
		String id = (String) session.getAttribute("id");
		service.deleteUser(id);
		session.invalidate();
		return "redirect:/naverlogin";
	}


}
