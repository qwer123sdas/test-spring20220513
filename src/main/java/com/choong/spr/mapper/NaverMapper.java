package com.choong.spr.mapper;

import org.apache.ibatis.annotations.Param;

public interface NaverMapper {

	public int signUpCheck(@Param("name") String name, @Param("id") int id);
	

}
