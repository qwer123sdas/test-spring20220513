package com.choong.spr.mapper;

import java.util.List;

import com.choong.spr.domain.BoardDto;

public interface BoardMapper {
	// 게시글 목록
	List<BoardDto> selectOrder();
	
	// 게시글
	BoardDto getBoard(int id);

}
