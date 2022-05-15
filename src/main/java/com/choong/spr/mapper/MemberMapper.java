package com.choong.spr.mapper;


import com.choong.spr.domain.MemberDto;

public interface MemberMapper {
	// 유저 정보 가져오기
	int getUser(String userID);

}
