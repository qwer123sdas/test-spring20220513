package com.choong.spr.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.choong.spr.service.SummerNoteService;
import com.google.gson.JsonObject;

@Controller
public class SummerNoteContoller {
	@Autowired
	SummerNoteService service;
	
	private final String awsS3Url = "https://bucket0207-spring0520-teacher-test.s3.ap-northeast-2.amazonaws.com/";
	
	// summerNote를 통해 이미지 업로드 + 이를 aws에 저장 + 임시 테이블에 저장
	@PostMapping(value="uploadImageToS3ForSummerNote",  produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadImageToS3ForSummerNote(@RequestParam("file") MultipartFile multipartFile, Principal principal, HttpServletRequest request ) {
		JsonObject jsonObject = new JsonObject();
		String urlName = service.uploadImageToS3ForSummerNote(multipartFile, principal.getName());
		jsonObject.addProperty("url", awsS3Url + urlName);
		
		return jsonObject.toString();
	}
}
