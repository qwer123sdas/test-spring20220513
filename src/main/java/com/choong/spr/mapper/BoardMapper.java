package com.choong.spr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.choong.spr.domain.BoardDto;

public interface BoardMapper {
	// 게시글 목록
	List<BoardDto> selectOrder();
	
	// id에 맞는 게시글 보기
	BoardDto getBoardById(int id);
	// // 여러가지 업로드된 데이터 가져오기
	List<String> selectFileNameByBoard(int boardId);
	
	// 게시글 삭제
	int deleteBoard(int id);
	
	// 게시글 수정
	int modifyBoard(BoardDto boardDto);
	
	// 게시글 작성
	void writeBoard(BoardDto boardDto);

	// 게시글 작성자 아이디 + 파일명
	void insertFile(@Param("id") int id, @Param("filename")String filename);


}
