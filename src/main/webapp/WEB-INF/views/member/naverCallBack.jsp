<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		var naver_id_login = new naver_id_login("myKQG3U17i94iAlkHWR4",
				"http://localhost:8080/spr/member/naverCallBack");
		// 접근 토큰 값 출력
		$('body').append('<h4>접속토큰:' + naver_id_login.oauthParams.access_token
							+ '</h4>');
		// 네이버 사용자 프로필 조회
		naver_id_login.get_naver_userprofile("naverSignInCallback()");
		// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
		function naverSignInCallback() {
			if(naver_id_login.getProfileData('id')){
				var id = naver_id_login.getProfileData('id');
				var name = naver_id_login.getProfileData('name');
				var email = naver_id_login.getProfileData('email');
				var gender = naver_id_login.getProfileData('gender');
				var birthday = naver_id_login.getProfileData('birthday');
				var mobile = naver_id_login.getProfileData('mobile');
			}
		}
			
		
		
		function signUpCheck(id, name, email){
			$.ajax({
				type: "POST",
				url: "${appRoot}/member/signUpCheck",
				
				data: {"memberKaKao" : "id", "memberName" : "name", "memberEmail" : "email"},
				contentType: "application/json; charset=utf-8",
				success : function(result){
								if(result == 0){
									alter("회원가입을 해야 합니다.")
								}else if(result == 1){
									alter("이미 회원가입이 되어있습니다.")
								}
						}
			});
		};
	</script>
</body>
</html>