package com.choong.spr.authentication;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String url = "/spr/naverlogin";

		exception.printStackTrace();
		String message = writePrintErrorResponse(response, exception);
		
		request.getSession().setAttribute("message", message);
		response.sendRedirect(url);
	}

	// 응답객체에 메세지를 만들기 위한 메서드
	private String writePrintErrorResponse(HttpServletResponse response, AuthenticationException exception) {

		String message = getExceptionMessage(exception);

		return message;
	}

	// 스프링 시큐리티 로그인 시 생기는 예외 들
	private String getExceptionMessage(AuthenticationException exception) {
		if (exception instanceof BadCredentialsException) {
			return "비밀번호가 불일치합니다.";
		} else if (exception instanceof UsernameNotFoundException ) {
			return "없는 계정입니다.";
		} else if (exception instanceof DisabledException){
			return "사용할 수 없는 계정입니다.";
		}else{	
			return "";  // 오류 없음
		}
	}

}
