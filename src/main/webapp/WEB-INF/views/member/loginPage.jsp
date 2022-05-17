<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags"%>

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
<!-- Bulma  -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<title>로그인 화면</title>
</head>

<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<body>
	<nav class="level">
		<p class="level-item has-text-centered">
			<a class="link is-info">Home</a>
		</p>
		<p class="level-item has-text-centered">
			<a class="link is-info">Menu</a>
		</p>
		<p class="level-item has-text-centered">
			<img src="https://bulma.io/images/bulma-type.png" alt=""
				style="height: 30px;">
		</p>
		<p class="level-item has-text-centered">
			<a class="link is-info">Reservations</a>
		</p>
		<p class="level-item has-text-centered">
			<a class="link is-info">Contact</a>
		</p>
	</nav>
	<div class="columns is-mobile is-centered">
		<div class="column is-one-third">
			<!-- 로그인 -->
			<form action="${appRoot }/member/login" method="post">
				<div class="field" style="float: none; margin: 0 auto;">
					<label class="label">아이디</label>
					<div class="control has-icons-left ">
						<input class="input" type="text" name="memberID">
						<span class="icon is-small is-left">
							<i class="fas fa-user"></i>
						</span>
					</div>
				</div>

				<div class="field">
					<label class="label">패스워드</label>
					<div class="control has-icons-left">
						<input class="input" type="password" name="memberPW">
						<span class="icon is-small is-left">
							<i class="fa-solid fa-unlock-keyhole"></i>
						</span>
					</div>
				</div>
				<div class=>
					<button class="button is-link is-fullwidth">
						<span>로그인</span>
					</button>
				</div>
			</form>
			<div class="tabs is-centered is-6-desktop">
				<ul>
					<li>
						<a target="_blank" href="" class="find_text">비밀번호 찾기</a>
					</li>
					<li>
						<a target="_blank" href="" class="find_text">아이디 찾기</a>
					</li>
					<li>
						<a target="_blank"
							href="http://localhost:8080/spr/member/signUpPage"
							class="find_text">회원가입</a>
					</li>

				</ul>
			</div>
		</div>
	</div>
	<!-- 네이버아이디로로그인 버튼 노출 영역 -->
	<div id="naver_id_login"></div>
	<!-- //네이버아이디로로그인 버튼 노출 영역 -->
	
	<script type="text/javascript">
		var naver_id_login = new naver_id_login("myKQG3U17i94iAlkHWR4",
				"http://localhost:8080/spr/member/naverCallBack");
		var state = naver_id_login.getUniqState();
		naver_id_login.setButton("green", 3, 40);
		naver_id_login.setDomain("YOUR_SERVICE_URL");
		naver_id_login.setState(state);
		naver_id_login.setPopup();
		naver_id_login.init_naver_id_login();
	</script>

	<!-- 메세지 -->
	<c:if test="${not empty message }">
		<div class="alert alert-primary">아이디나 비밀번호가 일치 하지 않습니다.</div>
	</c:if>
</body>


</html>