package com.choong.spr.domain;

import lombok.Data;

@Data
public class SummerNoteDto {
	private	int id;  // db는 id
	private String fileName;
	private String fileUrl;
	private int memberId;
	private int boardId;
}
