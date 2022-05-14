package com.choong.spr.mapper;

import java.util.List;

import com.choong.spr.domain.ReplyDto;

public interface ReplyMapper {
	// 게시글에서 댓글 목록
	List<ReplyDto> listReplyByBoardId(int id);
	
	// 댓글 추가
	void addReply(ReplyDto reply);

	// 댓글 수정
	void modifyReply(ReplyDto reply);

	// 댓글 삭제
	void removeReply(int id);

}
