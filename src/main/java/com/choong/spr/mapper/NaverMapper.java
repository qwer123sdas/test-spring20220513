package com.choong.spr.mapper;

import org.apache.ibatis.annotations.Param;

public interface NaverMapper {

	void method();
	
	// 회원가입 유무
	int signUpCheck(@Param("name") String name, @Param("memberId")String memberId);
	

}
