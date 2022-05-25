package com.choong.spr.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReplyDto {
	private int id;
	private int boardId; // db는 board_Id
	private String content;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime inserted;
}
