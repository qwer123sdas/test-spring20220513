package com.choong.spr.domain;

import java.time.LocalDateTime;


import lombok.Data;
@Data
public class MemberDto {
	public MemberDto() {}
	
	public MemberDto(String memberName) {
		super();
		this.memberName = memberName;
	}

	private int memberNO;
	private String memberID;
	private String memberPW;
	private String memberName;
	private String memberNickName;
	private String memberEmail;
	private String memberPhone;
	
	private int zoneCode;
	private String mainAddress;
	private String detailAddress;
	
	private String role;
	
}
