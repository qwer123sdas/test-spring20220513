package com.choong.spr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choong.spr.domain.ReplyDto;
import com.choong.spr.service.ReplyService;

@Controller
@RequestMapping("ex01")
public class ReplyController {
	@Autowired
	ReplyService service;
	// 댓글 목록 출력
	@PostMapping("reply/list")
	@ResponseBody
	public List<ReplyDto> list(int boardId){
		return service.listReplyByBoardId(boardId);
	}
	
	// 댓글 추가
	@RequestMapping("reply/add")
	public String addReply(ReplyDto reply) {
		service.addReply(reply);
		
		return "redirect:/ex01/board/" + reply.getBoardId();
	}
	// 댓글 수정
	@PostMapping("reply/modify")
	public String modifyReply(@ModelAttribute ReplyDto reply) {
		service.modifyReply(reply);
		return "redirect:/ex01/board/" + reply.getBoardId();
	}
	// 댓글 삭제
	@PostMapping("reply/remove")
	public String removeReply(ReplyDto reply) {
		service.removeReply(reply.getId());
		return "redirect:/ex01/board/" + reply.getBoardId(); 
	}
	
	
	
	
}
