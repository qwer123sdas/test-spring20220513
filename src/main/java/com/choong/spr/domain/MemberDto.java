package com.choong.spr.domain;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class MemberDto {
	private int memberNo;
	private String memberID;
	private int memberPW;
	private String memberName;
	private DateTimeFormat memberDate;
	private String memberSex;
	private String memberEmail;
	private String memberRole;
	private int memberKaKao;
	
}
