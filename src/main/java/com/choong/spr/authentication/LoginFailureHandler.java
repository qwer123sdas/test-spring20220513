package com.choong.spr.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String url="/spr/naverlogin";
		
		exception.printStackTrace();
		writePrintErrorResponse(response, exception);
		
		response.sendRedirect(url);
	}
	
	private void writePrintErrorResponse(HttpServletResponse response, AuthenticationException exception) {
		
	}

}
