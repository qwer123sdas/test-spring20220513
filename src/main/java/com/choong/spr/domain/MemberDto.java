package com.choong.spr.domain;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class MemberDto {
	private int member_No;
	private String member_ID;
	private int member_PW;
	private String member_Name;
	private DateTimeFormat member_Date;
	private String member_Sex;
	private String member_Email;
	private String member_Role;
	private int member_KaKao;
	
}
