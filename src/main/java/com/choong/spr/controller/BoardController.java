package com.choong.spr.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.choong.spr.domain.BoardDto;
import com.choong.spr.service.BoardService;

@Controller
@RequestMapping("ex01")
public class BoardController {
	
	private BoardService service;
	
	@Autowired
	BoardController(BoardService service){
		this.service = service;
	}

	
	// 게시판 목록 
	@RequestMapping("list")
	public void orderList(Model model, HttpSession session) {
		List<BoardDto> boardList = service.findOrder();

		model.addAttribute("boardList", boardList);
		System.out.println("list 세션 name " + session.getAttribute("name"));
		System.out.println("list 세션 id " + session.getAttribute("id"));
	}
	
	// 게시글 보기 
	@RequestMapping("board/{id}")
	public String getBoard(@PathVariable int id, Model model) {
		BoardDto board = service.getBoard(id);
		model.addAttribute("board", board);
		
		// 댓글작성한 내용 보이게 하는 메서드
		//List<ReplyDto> replyList = replyService.listReplyByBoardId(id);
		//model.addAttribute("replyList", replyList);
				
		return "ex01/board";
	}
	
	// 게시글 작성
	@GetMapping("write")
	public void writeBoard() {
		
	}
	@PostMapping("board/write")
	public String writeBoardProcess(BoardDto boardDto, MultipartFile file) {
		
		
		service.writeBoard(boardDto, file);
		return "redirect:/ex01/board/" + boardDto.getId();
	}
	
	// 게시글 수정
	@PostMapping("board/modify")
	public String modifyBoard(BoardDto boardDto) {
		boolean success = service.modifyBoard(boardDto);
		
		return "redirect:/ex01/board/" + boardDto.getId();
	}
	
	// 게시글 삭제
	@PostMapping("board/delete")
	public String removeBoard(int id) {
		boolean success = service.deleteBoard(id);
		return "redirect:/ex01/list";
	}
	
	
	
	
	
}
