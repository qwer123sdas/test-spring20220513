package com.choong.spr.mapper;

import org.apache.ibatis.annotations.Param;

import com.choong.spr.domain.MemberDto;

public interface MemberMapper {
	// 유저 정보 가져오기
	MemberDto getUser(@Param("userID") String userID, @Param("userPW") int userPW);

}
