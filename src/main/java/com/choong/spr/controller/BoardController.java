package com.choong.spr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void orderList(Model model) {
		List<BoardDto> boardList = service.findOrder();
		model.addAttribute("boardList", boardList);
	}
	
	// 게시글 보기
	@RequestMapping("board/{id}")
	public String getBoard(@PathVariable int id, Model model) {
		BoardDto board = service.getBoard(id);
		model.addAttribute("board", board);
		return "ex01/board";
	}
	
	// 게시글 삭제
	@RequestMapping("board/delete")
	public String removeBoard(int id, Model model) {
		service.deleteBoard(id);
		return "";
	}
	// 게시글 수정
	
	
	
}
