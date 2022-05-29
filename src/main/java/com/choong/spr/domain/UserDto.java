package com.choong.spr.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class UserDto {
	private String id;
	private String userName;
	private String password;
	private boolean enabled;
	
	public UserDto() {}
	
	@Builder
	public UserDto(String id, String userName, String password, boolean enabled) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}
	
	
	
	
}
