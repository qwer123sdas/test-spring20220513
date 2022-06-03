package com.choong.spr.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.choong.spr.domain.BoardDto;
import com.choong.spr.mapper.BoardMapper;
import com.choong.spr.mapper.ReplyMapper;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class BoardService {
	@Autowired
	BoardMapper mapper;
	
	@Autowired
	ReplyMapper replyMapper;
	
	@org.springframework.beans.factory.annotation.Value("${aws.s3.bucketName}")
	private String bucketName;
	
	private S3Client s3; 
	
	@PostConstruct
	public void init() {
		Region region = Region.AP_NORTHEAST_2;
		this.s3 = S3Client.builder()
						.region(region)
						.build();
	}
	
	// 게시글 목록
	public List<BoardDto> findOrder() {
		return mapper.selectOrder();
	}

	// 게시글 출력
	public BoardDto getBoard(int id) {
		return mapper.getBoard(id);
	}
	
	// 게시글 작성
	@Transactional
	public void writeBoard(BoardDto boardDto, MultipartFile file) {
		//boardDto.setInserted(LocalDateTime.now());
		
		// 게시글 등록
		mapper.writeBoard(boardDto);
		
		// 파일 등록
		if(file.getSize() > 0) {
			mapper.insertFile(boardDto.getId(), file.getOriginalFilename());
			// aws s3에 업로드(저장)
			saveFileAwsS3(boardDto.getId(), file); 
		}
	}
	// aws s3에 업로드(저장)
	private void saveFileAwsS3(int id, MultipartFile file) {
		// board/37/12344.png
		String key = "folder/" + id + "/" + file.getOriginalFilename();
		
		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.acl(ObjectCannedACL.PUBLIC_READ) 		 // acl : 권한 설정
				.bucket(bucketName) 					// bucket 위치 설정
				.key(key)								// 키
				.build(); 								 // 이를 통해 PutObjectRequest객체 만듬
		
		RequestBody requestBody;
		try {
			requestBody = RequestBody.fromInputStream(file.getInputStream(), file.getSize());
			s3.putObject(putObjectRequest, requestBody);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e); // 트랜잭션 때문에 모두 실패????????????
		}
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
	
	
	
}
