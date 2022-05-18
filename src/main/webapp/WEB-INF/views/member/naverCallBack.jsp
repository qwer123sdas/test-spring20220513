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
		//$('body').append('<h4>접속토큰:' + naver_id_login.oauthParams.access_token + '</h4>');
		// 네이버 사용자 프로필 조회
		naver_id_login.get_naver_userprofile("naverSignInCallback()");
		
		
		// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
		function naverSignInCallback() {
			if (naver_id_login.getProfileData('id')) {
				var id = naver_id_login.getProfileData('id');
				var name = naver_id_login.getProfileData('name');
				var email = naver_id_login.getProfileData('email');
			}
		}

	</script>
	<h1>콜백 페이지, 회원한텐 안보여야 함</h1>
	<button id="signUpCheck">버튼</button>
	<h3>'${name}' 님 환영합니다! </h3>
	<h3><a href="http://localhost:8080/spr/naverlogout">로그아웃</a></h3>

</body>
</html>