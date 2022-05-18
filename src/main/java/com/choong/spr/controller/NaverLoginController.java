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

import com.choong.spr.BO.MemberBO;
import com.choong.spr.controller.naver.NaverLoginBO;
import com.choong.spr.controller.naver.NaverUser;
import com.choong.spr.domain.SnsUserDto;
import com.choong.spr.service.NaverService;
import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
public class NaverLoginController {
	@Autowired
	NaverService service;
	@Autowired
	private NaverLoginBO naverLoginBO;
	@Autowired
	private MemberBO memberBO;
	
	// NaverUser naverUser = null;과 아래는 같음  : 사용자의 프로필 정보 조회하기 위함
	private String apiResult = null;


	// 네이버 로그인 ----------------------------------------------------
	// login
	@RequestMapping("naverlogin")
	public String login(Model model, HttpSession session) {
		System.out.println("로그인메소드");
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		/* 로그인 페이지에 적용할 네이버 아이디로 로그인 버튼에 대한 링크 */
		
		
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		
		
		//네이버에서 가져온 정보 보내기
		model.addAttribute("url", naverAuthUrl);
		
		// 이미 callback을 거친 상태 라면.....
		return "login";
	}

	// callback
	@RequestMapping("member/naverCallBack")
	public String callback(RedirectAttributes rttr, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException{
		System.out.println("콜백!!");
		/* 네이버 아이디로 로그인 인증이 끝나면 callback처리과정에서 AccessToken을 발급받을 수 있다. */
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		//1. 로그인 사용자 정보를 읽어온다.
		/* 발급받은 AccessToken을 이용하여 현재 로그인한 네이버의 사용자 프로필 정보를 조회할 수 있다. */
		apiResult = naverLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
		/** apiResult json 구조
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		**/
		
		// 무엇을 가져오는 것일 까? 22
		NaverUser naverUser = memberBO.getNaverUserSession(session);
		 /* ++네이버 사용자 프로필 정보를 이용하여 가입되어있는 사용자를 DB에서 조회하여 가져온다. */
		SnsUserDto snsUser = memberBO.getUserByNaverUser(naverUser);
		
		 /* ++ 만약 일치하는 사용자가 없다면 현재 로그인한 네이버 사용자 계정으로 회원가입이 가능하도록 가입페이지로 전달한다 */
		if (snsUser == null) {
			// 계정 없음
			System.out.println("꼐정이 없다!!");
			return "redirect:/member/signUpPage";
		}
		
		
		
		//2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		//3. 데이터 파싱
		//Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		//response의 nickname값 파싱
		String name = (String)response_obj.get("name");
		
		// ++ 회원가입된 계정 유무 확인

		/* ++ 만약 일치하는 사용자가 있다면 현재 세션에 사용자 로그인 정보를 저장 */
		//4.파싱 닉네임 세션으로 저장
		System.out.println("세션 저장 :  "+ name);
		session.setAttribute("name", name); //세션 생성
		rttr.addAttribute("result", apiResult);
		return "redirect:/ex01/list";

		
	}
	
	//로그아웃---------------------------------------------------------------------------
	@RequestMapping("naverlogout")
		public String logout(HttpSession session)throws IOException {
		System.out.println("여기는 네이버 logout");
		session.invalidate();
		return "redirect:/member/loginPage";
	}


}
