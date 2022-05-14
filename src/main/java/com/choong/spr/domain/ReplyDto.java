package com.choong.spr.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReplyDto {
	private int id;
	private int boardId; // db는 board_Id
	private String content;
	private LocalDateTime inserted;
}
