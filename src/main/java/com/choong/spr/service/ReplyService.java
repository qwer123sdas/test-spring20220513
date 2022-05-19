package com.choong.spr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choong.spr.domain.ReplyDto;
import com.choong.spr.mapper.ReplyMapper;

@Service
public class ReplyService {
	@Autowired
	ReplyMapper mapper;
	
	// 게시글에 댓글 목록 보기
	public List<ReplyDto> listReplyByBoardId(int id) {
		return mapper.listReplyByBoardId(id);
	}
	// 댓글 추가
	public void addReply(ReplyDto reply) {
		//reply.setInserted(LocalDateTime.now());
		mapper.addReply(reply);
	}
	// 댓글 수정
	public void modifyReply(ReplyDto reply) {
		mapper.modifyReply(reply);
	}
	// 댓글 삭제
	public void removeReply(int id) {
		mapper.removeReply(id);
	}

}
