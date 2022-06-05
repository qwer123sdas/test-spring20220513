package com.choong.spr.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BoardDto {
	private	int id;  // db는 id
	private String title;
	private String body;
	private LocalDateTime inserted;
	private int numOfReply;
	
	private String writerNickName;
	
	private List<String> fileName;
	
	private boolean hasFile;
	
}
