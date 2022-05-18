package com.choong.spr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choong.spr.domain.NaverDto;
import com.choong.spr.mapper.NaverMapper;

@Service
public class NaverService {
	@Autowired
	NaverMapper mapper;
	
	public int signUpCheck(String name, String id) {
		return mapper.signUpCheck(name, Integer.valueOf(id));
	}

}
