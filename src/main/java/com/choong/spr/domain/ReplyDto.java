package com.choong.spr.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReplyDto {
	private int id;
	private int boardId; // db는 board_Id
	private String writerNickName;
	private String memberId; // db는 member_id
	private String content;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime inserted;
	
	private boolean own;
}
