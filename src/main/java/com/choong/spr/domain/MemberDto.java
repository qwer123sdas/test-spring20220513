package com.choong.spr.domain;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class MemberDto {
	private int memberNo;
	private String memberID;
	private int memberPW;
	private String memberName;
	private LocalDateTime memberDate;
	private String memberSex;
	private String memberEmail;
	private String memberRole;
	private int memberKaKao;
	
}
