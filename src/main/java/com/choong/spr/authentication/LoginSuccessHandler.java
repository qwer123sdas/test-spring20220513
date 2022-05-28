package com.choong.spr.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.choong.spr.domain.MemberDto;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//인증된 사용자의 정보를 추출
		// authentication-success-handler-ref="LoginSuccessHandler" 
		MemberDto dto = new MemberDto();
		System.out.println("인증후=>"+dto.getMemberID());
		
	}

}
