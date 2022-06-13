package com.choong.spr.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.choong.spr.domain.BoardDto;
import com.choong.spr.domain.SummerNoteDto;
import com.choong.spr.mapper.BoardMapper;
import com.choong.spr.mapper.ReplyMapper;
import com.choong.spr.mapper.SummerNoteMapper;
import com.google.gson.JsonObject;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class BoardService {
	@Autowired
	BoardMapper mapper;
	
	@Autowired
	ReplyMapper replyMapper;
	
	@Autowired
	SummerNoteMapper summerNoteMapper;
	
	@org.springframework.beans.factory.annotation.Value("${aws.s3.bucketName}")
	private String bucketName;
	
	private S3Client amazonS3; 
	private final String awsS3Url = "https://bucket0207-spring0520-teacher-test.s3.ap-northeast-2.amazonaws.com/";
	
	private String boardImageName;
	
	@PostConstruct   // s3 빈 생성
	public void init() {
		Region region = Region.AP_NORTHEAST_2;
		this.amazonS3 = S3Client.builder()
						.region(region)
						.build();
	}
	@PreDestroy // s3 빈이 사라지기 전
	public void destroy() {
		this.amazonS3.close();
	}
	
	// 게시글 목록
	public List<BoardDto> findOrder() {
		return mapper.selectOrder();
	}

	// id에 맞는 게시글 출력
	public BoardDto getBoard(int id) {
		BoardDto board = mapper.getBoardById(id);
		
		// 여러가지 업로드된 데이터 가져오기
		List<String> fileNames = mapper.selectFileNameByBoard(id);
		board.setFileName(fileNames);
		
		return board;
	}

	
	// 게시글 작성 Start----------------
	
	@Transactional
	public void writeBoard(BoardDto boardDto, MultipartFile file, String member_id) {
		//boardDto.setInserted(LocalDateTime.now());
		boardDto.setMemberId(member_id);
		// 게시글 등록
		mapper.writeBoard(boardDto);
		
		// 업로드 파일 등록-----------------------
		if(file.getSize() > 0) {
			mapper.insertFile(boardDto.getId(), file.getOriginalFilename());
			
			// aws s3에 업로드(저장)
			saveFileAwsS3(boardDto.getId(), file); 
		}
		// 업로드 파일 등록 끝---------------------
		
		
		// 서머노트 파일 등록 ---------------------
		
		int mainId = boardDto.getId();
		
		
		// 임시 s3에서 진짜 s3로 넣기
			// 먼저 db에서 파일명 가져오기
		SummerNoteDto summerDto = summerNoteMapper.getFileName(member_id);
		int summerId = summerDto.getId();
		String fileName = summerDto.getFile();
			// s3에 넣기
		// copyFileAwsS3("bucket0207-spring0520-teacher-test", "folder/temp/"+ summerId + "/" + fileName, "folder/" + mainId + "/" + fileName );
		saveFileAwsS3(mainId, fileName);
			// db에 넣기
		
		// 임시 aws 파일 삭제
		deletesummerNoteFromAwsS3(fileName);
		
		// 임시 테이블 db삭제
		summerNoteMapper.deleteObject(member_id);
		
		// 서머노트 끝-------------------------
	}

	// 메인 aws s3에 업로드(저장)
	private void saveFileAwsS3(int id, MultipartFile file) {
		// board/temp/12344.png
		String key = "folder/" + id + "/" + file.getOriginalFilename();
		
		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.acl(ObjectCannedACL.PUBLIC_READ) 		 // acl : 권한 설정
				.bucket(bucketName) 					// bucket 위치 설정
				.key(key)								// 키
				.build(); 								 // 이를 통해 PutObjectRequest객체 만듬
		
		RequestBody requestBody;
		try {
			requestBody = RequestBody.fromInputStream(file.getInputStream(), file.getSize());
			amazonS3.putObject(putObjectRequest, requestBody);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e); // 트랜잭션 때문에 모두 실패????????????
		}
	}
	
	// temp aws s3에서 메인 s3로 저장
	private void saveFileAwsS3(int id, String file) {
		// board/temp/12344.png
		String key = "folder/" + id + "/" + file;
		
		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.acl(ObjectCannedACL.PUBLIC_READ) 		 // acl : 권한 설정
				.bucket(bucketName) 					// bucket 위치 설정
				.key(key)								// 키
				.build(); 								 // 이를 통해 PutObjectRequest객체 만듬
		
		RequestBody requestBody;
		try {
			requestBody = RequestBody.fromInputStream(file.getInputStream(), file.getSize());
			amazonS3.putObject(putObjectRequest, requestBody);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e); // 트랜잭션 때문에 모두 실패????????????
		}
	}
	
	// aws s3, 다른 폴더로 복사
	/*
	private void copyFileAwsS3(String bucketName, String sourceKey, String destinationKey) {
		try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new ProfileCredentialsProvider())
                    .withRegion(clientRegion)
                    .build();

            CopyObjectRequest copyObjRequest = new CopyObjectRequest(bucketName, sourceKey, bucketName, destinationKey);
            amazonS3.copyObject(copyObjRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}
	*/

	// summernote aws s3사진 삭제 메소드
	private void deletesummerNoteFromAwsS3(String savedFileName) {
		String url = "folder/temp/" + savedFileName;
		DeleteObjectRequest deleteBucketRequest;
		deleteBucketRequest = DeleteObjectRequest.builder()
												 .bucket(bucketName)
												 .key(url)
												 .build();
		amazonS3.deleteObject(deleteBucketRequest);
	}
	


	// 게시글 작성 End---------------

	
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
