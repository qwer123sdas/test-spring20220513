package com.choong.spr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.choong.spr.domain.SummerNoteDto;

@Mapper
public interface SummerNoteMapper {

	// 임시 테이블 에 넣기
	void InsertSummerNoteTable(SummerNoteDto dto);

	// 게시글 작성완료 할 때, s3옮기기 위해 db 파일명 가져오기
	SummerNoteDto getFileName(String memberId);
	
	// 임시 테이블 db삭제
	void deleteObject(String memberId);

}
