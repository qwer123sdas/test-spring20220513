package com.choong.spr.service;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.choong.spr.domain.SummerNoteDto;
import com.choong.spr.mapper.SummerNoteMapper;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class SummerNoteService {
	@Autowired
	SummerNoteMapper mapper;
	
	@org.springframework.beans.factory.annotation.Value("${aws.s3.bucketName}")
	private String bucketName;
	
	private S3Client amazonS3; 
	
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
	
	
	// 서머노트에 사진 업로드
	public String uploadImageToS3ForSummerNote(MultipartFile multipartFile, String memberId) {
		// 파일 s3에 등록
		if(multipartFile.getSize() > 0) {
			// 이름의 겹치치 않게 파일명 전환, return 값은 바뀐 파일명
			String savedFileName = summerNoteUploadFile(multipartFile); 
			
			// 임시 db에 넣기
			SummerNoteDto dto = new SummerNoteDto();
			dto.setFile(savedFileName);
			dto.setMemberId(memberId);
			mapper.InsertSummerNoteTable(dto);
			
			String key = "folder/temp/"+ dto.getId() + "/" +  savedFileName;
			// s3에 넣기
			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
					.acl(ObjectCannedACL.PUBLIC_READ) 		 // acl : 권한 설정
					.bucket(bucketName) 					// bucket 위치 설정
					.key(key)								// 키
					.build(); 								 // 이를 통해 PutObjectRequest객체 만듬
			RequestBody requestBody;
			try {
				requestBody = RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize());
				amazonS3.putObject(putObjectRequest, requestBody);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e); // 트랜잭션 때문에 모두 실패????????????
			}
			
			return key;  // url로 보냄
		}
		return "";
	}
	
	public String summerNoteUploadFile(MultipartFile file) {
		// 이름의 겹치치 않게 파일명 전환
		String originalFileName = file.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		return savedFileName;
	}
}
