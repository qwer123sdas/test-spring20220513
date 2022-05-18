package com.choong.spr.controller.naver;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


public class NaverLoginController2 {
	/*
	 * private String CLIENT_ID = ""; private String CLI_SECRET = ""; // 네이버 로그인
	 * ----------------------------------------------------
	 * 
	 * @RequestMapping("naverlogin") public void naverLogin() {
	 * 
	 * }
	 * 
	 * @RequestMapping("naverCallBack") public void callback() { }
	 * 
	 * 
	 * 
	 * // 로그인 화면 페이지-------------------
	 * 
	 * @RequestMapping("/naver") public String testNaver(HttpSession session, Model
	 * model)throws UnsupportedEncodingException, UnknownHostException { String
	 * redirectURI = URLEncoder.encode("http://localhost:8080/naver/callback1",
	 * "UTF-8"); SecureRandom random = new SecureRandom(); String state = new
	 * BigInteger(130, random).toString(); String apiURL =
	 * "https://nid.naver.com/oauth2.0/authorize?response_type=code"; apiURL +=
	 * String.format("&client_id=%s&redirect_uri=%s&state=%s", CLIENT_ID,
	 * redirectURI, state); session.setAttribute("state", state);
	 * model.addAttribute("apiURL", apiURL); return "test-naver"; }
	 */
}
