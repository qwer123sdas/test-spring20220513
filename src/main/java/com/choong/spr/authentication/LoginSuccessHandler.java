package com.choong.spr.authentication;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.domain.UserDto;
import com.choong.spr.service.MemberService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//인증된 사용자의 정보를 추출
		// authentication-success-handler-ref="LoginSuccessHandler"
		
		//UserDto dto = (UserDto)authentication.getPrincipal();
		//System.out.println("인증후=> " + dto);
		
		// 권한 리스트 추출
		//Collection<GrantedAuthority> authList = dto.getPassword();
		String url="/spr/ex01/list";
		String id = authentication.getName();

		request.getSession().setAttribute("id", id);
		request.getAttribute(id);
		response.sendRedirect(url);
		
	}

}
