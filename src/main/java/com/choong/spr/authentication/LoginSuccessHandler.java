package com.choong.spr.authentication;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.choong.spr.domain.MemberDto;
import com.choong.spr.domain.UserDto;
import com.choong.spr.mapper.MemberMapper;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//인증된 사용자의 정보를 추출"
		//UserDto dto = (UserDto)authentication.getPrincipal();
		
		// 권한 리스트 추출
		//Collection<GrantedAuthority> authList = dto.getPassword();
		String url="/spr/ex01/list";
		String id = authentication.getName();
		
		MemberDto dto = memberMapper.getUserNameById(id);
		String name = dto.getMemberName();
	

		request.getSession().setAttribute("name", name);
		request.getSession().setAttribute("id", id);
		request.getAttribute(name);
		request.getAttribute(id);
		response.sendRedirect(url);
		
	}

}
