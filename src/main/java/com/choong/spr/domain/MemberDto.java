package com.choong.spr.domain;

import java.time.LocalDateTime;


import lombok.Data;
@Data
public class MemberDto {
	protected MemberDto() {}
	
	public MemberDto(String memberName) {
		super();
		this.memberName = memberName;
	}

	private int memberNo;
	private String memberID;
	private String memberPW;
	private String memberName;
	private LocalDateTime memberDate;
	private String memberSex;
	private String memberEmail;
	private String memberRole;
	
	private int zoneCode;
	private String mainAddress;
	private String detailAddress;
	
	private int memberKaKao;
	
}
