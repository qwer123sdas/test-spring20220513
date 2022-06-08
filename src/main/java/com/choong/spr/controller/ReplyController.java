package com.choong.spr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choong.spr.domain.ReplyDto;
import com.choong.spr.service.ReplyService;

@Controller
@RequestMapping("ex01")
public class ReplyController {
	@Autowired
	ReplyService service;
	// 댓글 목록 출력
	@PostMapping(path="reply/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ReplyDto> list(int boardId, Principal principal){
		if(principal == null) {
			// 로그인 안할 때
			return service.getReplyByBoardId(boardId);
		}else {
			// 로그인 한 상태일 때
			return service.listReplyWithOwnByBoardId(boardId, principal.getName());
		}
	}
	
	// 댓글 추가
	@RequestMapping(path="reply/add", produces = "text/plain;charset=UTF-8")
	public String addReply(ReplyDto reply, Principal principal) {
		if(principal != null) {
			String memberId = principal.getName();
			reply.setMemberId(memberId);
			service.addReply(reply);
		}
		
		return "redirect:/ex01/board/" + reply.getBoardId();
	}
	// 댓글 수정
	@PutMapping(path = "reply/modify", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> modifyReply(@RequestBody ReplyDto reply) {
		service.modifyReply(reply);
		return ResponseEntity.ok("수정완료");
	}
	// 댓글 삭제
	@DeleteMapping("reply/remove/{id}")
	@ResponseBody
	public ResponseEntity<String> removeReply(@PathVariable("id") int id) {
		service.removeReply(id);
		return ResponseEntity.ok("삭제완료");
		
	}
	
	
	
	
}
