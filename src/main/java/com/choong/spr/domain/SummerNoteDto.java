package com.choong.spr.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SummerNoteDto {
	private	int id;  // db는 id
	private String file;
	private String memberId;
}
