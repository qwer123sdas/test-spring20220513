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

	// 게시글 출력
	public BoardDto getBoard(int id) {
		return mapper.getBoard(id);
	}
	
	// 게시글 삭제
	public boolean deleteBoard(int id) {
		int cnt = mapper.deleteBoard(id);
		return cnt == 1;
				
	}
	// 게시글 수정
	public boolean modifyBoard(BoardDto boardDto) {
		int cnt = mapper.modifyBoard(boardDto);
		return cnt == 1;
	}
	// 게시글 작성
	public void writeBoard(BoardDto boardDto) {
		mapper.writeBoard(boardDto);
	}
	
	
}
