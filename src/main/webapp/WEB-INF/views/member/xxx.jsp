<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
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
<title>네이버 로그인</title>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<!-- 네이버아이디로로그인 버튼 노출 영역 -->
	<div id="naver_id_login"></div>
	<!-- //네이버아이디로로그인 버튼 노출 영역 -->
	<script type="text/javascript">
		var naver_id_login = new naver_id_login("myKQG3U17i94iAlkHWR4",
				"http://localhost:8080/spr/member/naverCallBack");
		var state = naver_id_login.getUniqState();
		naver_id_login.setButton("white", 2, 40);
		naver_id_login.setDomain("YOUR_SERVICE_URL");
		naver_id_login.setState(state);
		naver_id_login.setPopup();
		naver_id_login.init_naver_id_login();
	</script>
	
	
	
	
	
	
		<c:choose>
		<c:when test="${sessionNaver != null}">
			<h2>콜백 없이 네이버 아이디 로그인 성공하셨습니다!!</h2>
			<h3>'${name}' 님 환영합니다!</h3>
			<h3>
				<a href="http://localhost:8080/spr/naverlogout">로그아웃</a>
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
	
</html>