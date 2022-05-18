package com.choong.spr.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.choong.spr.domain.BoardDto;
import com.choong.spr.domain.ReplyDto;
import com.choong.spr.service.BoardService;
import com.choong.spr.service.ReplyService;

@Controller
@RequestMapping("ex01")
public class BoardController {
	
	private BoardService service;
	private ReplyService replyService;
	@Autowired
	BoardController(BoardService service, ReplyService replyService){
		this.service = service;
		this.replyService = replyService;
	}

	
	// 게시판 목록 
	@RequestMapping("list")
	public void orderList(Model model, HttpSession session) {
		List<BoardDto> boardList = service.findOrder();
		model.addAttribute("boardList", boardList);
		System.out.println("list 세션 " + session.getAttribute("name"));
	}
	
	// 게시글 보기 + 댓글 목록 보기
	@RequestMapping("board/{id}")
	public String getBoard(@PathVariable int id, Model model) {
		BoardDto board = service.getBoard(id);
		model.addAttribute("board", board);
		
		// 댓글작성한 내용 보이게 하는 메서드
		List<ReplyDto> replyList = replyService.listReplyByBoardId(id);
		model.addAttribute("replyList", replyList);
				
		return "ex01/board";
	}
	
	// 게시글 삭제
	@PostMapping("board/delete")
	public String removeBoard(int id) {
		boolean success = service.deleteBoard(id);
		return "redirect:/ex01/list";
	}
	
	
	// 게시글 수정
	@PostMapping("board/modify")
	public String modifyBoard(BoardDto boardDto) {
		boolean success = service.modifyBoard(boardDto);
		
		return "redirect:/ex01/board/" + boardDto.getId();
	}
	
	// 게시글 작성
	@GetMapping("write")
	public void writeBoard() {
		
	}
	@PostMapping("board/write")
	public String writeBoardProcess(BoardDto boardDto) {
		service.writeBoard(boardDto);
		return "redirect:/ex01/board/" + boardDto.getId();
	}
	
	
}
