package com.choong.spr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choong.spr.domain.BoardDto;
import com.choong.spr.mapper.BoardMapper;
import com.choong.spr.mapper.ReplyMapper;


@Service
public class BoardService {
	@Autowired
	BoardMapper mapper;
	
	@Autowired
	ReplyMapper replyMapper;
	
	// 게시글 목록
	public List<BoardDto> findOrder() {
		return mapper.selectOrder();
	}

	// 게시글 출력
	public BoardDto getBoard(int id) {
		return mapper.getBoard(id);
	}
	
	// 게시글 삭제 + 댓글 삭제
	@Transactional
	public boolean deleteBoard(int id) {
		int cnt = mapper.deleteBoard(id);
		
		replyMapper.deleteReplyByBoard(id);
		return cnt == 1;
				
	}
	// 게시글 수정
	public boolean modifyBoard(BoardDto boardDto) {
		int cnt = mapper.modifyBoard(boardDto);
		return cnt == 1;
	}
	// 게시글 작성
	public void writeBoard(BoardDto boardDto) {
		boardDto.setInserted(LocalDateTime.now());
		
		mapper.writeBoard(boardDto);
	}
	
	
}
