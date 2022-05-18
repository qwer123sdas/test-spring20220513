package com.choong.spr.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choong.spr.controller.naver.NaverLoginBO;
import com.choong.spr.domain.NaverDto;
import com.choong.spr.service.NaverService;
import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
public class NaverLoginController {
	@Autowired
	NaverService service;
	@Autowired
	private NaverLoginBO naverLoginBO;
	
	private String apiResult = null;


	// 네이버 로그인 ----------------------------------------------------
	// login
	@RequestMapping("naverlogin")
	public String login(Model model, HttpSession session) {
		System.out.println("로그인메소드");
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		//네이버
		model.addAttribute("url", naverAuthUrl);
		
		// 이미 callback을 거친 상태 라면.....
		return "login";
	}

	// callback
	@RequestMapping("member/naverCallBack")
	public String callback(RedirectAttributes rttr, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException{
		System.out.println("콜백!!");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		//1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
		/** apiResult json 구조
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		**/
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		//response의 nickname값 파싱
		String name = (String)response_obj.get("name");
		String id = (String)response_obj.get("id");
		// ++ 회원가입된 계정 유무 확인
		int result = service.signUpCheck(name, id);
		if ( result == 0) {
			// 계정 없음
			System.out.println("꼐정이 없다!!");
			rttr.addFlashAttribute("id", id);
			rttr.addFlashAttribute("name", name);
			return "redirect:/member/signUpPage";
		}else if(result == 1){
			// 계정 있음
			//4.파싱 닉네임 세션으로 저장
			System.out.println("세션 저장 :  "+ name);
			session.setAttribute("name", name); //세션 생성
			rttr.addAttribute("result", apiResult);
			return "redirect:/ex01/list";
		}
		return "";
		
	}
	
	//로그아웃---------------------------------------------------------------------------
	@RequestMapping("naverlogout")
		public String logout(HttpSession session)throws IOException {
		System.out.println("여기는 네이버 logout");
		session.invalidate();
		return "redirect:/member/loginPage";
	}


}
