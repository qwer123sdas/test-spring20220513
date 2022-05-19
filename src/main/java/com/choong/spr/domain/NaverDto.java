package com.choong.spr.domain;

import com.choong.spr.controller.naver.NaverUser;


public class NaverDto extends MemberDto{
	
	@SuppressWarnings("unused")
	private NaverDto(){}
	
	public NaverDto(String memberName, String memberPW) {
		super(memberName);
	}

	public NaverDto(String memberName, NaverUser naverUser){
		super(memberName);
		this.snsId = naverUser.getId();
		this.snsName = naverUser.getName();
		this.snsType = "NAVER";
	}
	
	
	private int id;
	private String snsId;
	private String snsName;
	private String snsType;
	
	public int getId() {
		return id;
	}
	public String getSnsId() {
		return snsId;
	}
	public String getSnsName() {
		return snsName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}
	public void setSnsName(String snsName) {
		this.snsName = snsName;
	}
	
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	public String getSnsType() {
		return snsType;
	}
}
