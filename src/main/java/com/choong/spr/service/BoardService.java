package com.choong.spr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choong.spr.domain.BoardDto;
import com.choong.spr.mapper.BoardMapper;


@Service
public class BoardService {
	@Autowired
	BoardMapper mapper;
	// 게시글 목록
	public List<BoardDto> findOrder() {
		return mapper.selectOrder();
	}

	// 게시글
	public BoardDto getBoard(int id) {
		return mapper.getBoard(id);
	}
	
	// 게시글 삭제
	
	// 게시글 수정

}
