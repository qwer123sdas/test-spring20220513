<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--font-awesome  -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!--bootstrap  -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css"
	integrity="sha512-GQGU0fMMi238uA+a/bdWJfpUGKUkBdgfFdgBm72SUQ6BeyWjoY/ton0tEjH+OSH9iP4Dfh+7HM0I9f5eR0L/4w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!--Jquery -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	referrerpolicy="no-referrer"></script>
<title>Insert title here</title>
</head>
<script>
function naverSignInCallback() {
	  var naver_id_login = new naver_id_login("myKQG3U17i94iAlkHWR4", "http://localhost:8080/spr/member/naverCallBack");
	  // 접근 토큰 값 출력
	  alert(naver_id_login.oauthParams.access_token);
	  // 네이버 사용자 프로필 조회
	  naver_id_login.get_naver_userprofile("naverSignInCallback()");
	  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	  
	// naver_id_login.getProfileData('프로필항목명');
	// 프로필 항목은 개발가이드를 참고하시기 바랍니다.
	alert(naver_id_login.getProfileData('email'));
	alert(naver_id_login.getProfileData('nickname'));
	alert(naver_id_login.getProfileData('age'));
}


function naverCallBack(){
		const email = naver_ig_login.getProfileDate('email');
		let body = $('body');
		body.append('<h4>이메일' + email + '</h4>');
}
</script>
</script>


<body>
	<c:choose>
		<c:when test="${sessionId != null}">
			<h2>네이버 아이디 로그인 성공하셨습니다!!</h2>
			<h3>'${sessionId}' 님 환영합니다!</h3>
			<h3>
				<a href="logout">로그아웃</a>
			</h3>
		</c:when>
		<c:otherwise>
			<form action="login.userdo" method="post" name="frm"
				style="width: 470px;">
				<h2>로그인</h2>
				<input type="text" name="id" id="id" class="w3-input w3-border"
					placeholder="아이디" value="${id}">
				<br>
				<input type="password" id="pwd" name="pwd"
					class="w3-input w3-border" placeholder="비밀번호">
				<br>
				<input type="submit" value="로그인" onclick="#">
				<br>
			</form>
			<br>
			<!-- 네이버 로그인 창으로 이동 -->
			<div id="naver_id_login" style="text-align: center">
				<a href="${url}">
					<img width="223"
						src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" />
				</a>
			</div>
			<br>
		</c:otherwise>
	</c:choose>

</body>
</html>