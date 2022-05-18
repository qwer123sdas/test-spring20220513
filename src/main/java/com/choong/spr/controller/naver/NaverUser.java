package com.choong.spr.controller.naver;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class NaverUser {
	@JsonProperty("email")
	private String email;
	@JsonProperty("nickname")
	private String nickname;
	@JsonProperty("enc_id")
	private String encId;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;


	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("nickname")
	public String getNickname() {
		return nickname;
	}

	@JsonProperty("nickname")
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	@JsonProperty("enc_id")
	public String getEncId() {
		return encId;
	}

	@JsonProperty("enc_id")
	public void setEncId(String encId) {
		this.encId = encId;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
