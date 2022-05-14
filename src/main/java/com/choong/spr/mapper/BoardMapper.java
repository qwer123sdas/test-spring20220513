package com.choong.spr.mapper;

import java.util.List;

import com.choong.spr.domain.BoardDto;

public interface BoardMapper {
	// 게시글 목록
	List<BoardDto> selectOrder();
	
	// 게시글 보기
	BoardDto getBoard(int id);
	
	// 게시글 삭제
	int deleteBoard(int id);
	
	// 게시글 수정
	int modifyBoard(BoardDto boardDto);
	
	// 게시글 작성
	void writeBoard(BoardDto boardDto);

}
