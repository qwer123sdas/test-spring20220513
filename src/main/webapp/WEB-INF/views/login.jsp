<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("utf-8");
%>
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

<title>Insert title here</title>
</head>
<style>
body {
	box-sizing: border-box;
	margin: 0px;
	font-family: Consolas, monospace;
}

.jb-container {
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
<body>
	<div class="container jb-container">
		<div class="list-item-content">
			<form action="${appRoot }/login" method="post">
				<h1 class="h3 mb-3 fw-normal">Please sign in</h1>

				<div class="field" style="float: none; margin: 0 auto;">
					<label class="label">아이디</label>
					<div class="control has-icons-left ">
						<input class="input" type="text" name="username">
						<span class="icon is-small is-left">
							<i class="fas fa-user"></i>
						</span>
					</div>
				</div>
				<div class="field">
					<label class="label">패스워드</label>
					<div class="control has-icons-left">
						<input class="input" type="password" name="password">
						<span class="icon is-small is-left">
							<i class="fa-solid fa-unlock-keyhole"></i>
						</span>
					</div>
				</div>

				<div class="checkbox mb-3">
					<label>
						<input type="checkbox" value="remember-me">
						Remember me
					</label>
				</div>
				<button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
				<div class="text-success">
					<hr>
				</div>
				<!-- 네이버 로그인 창으로 이동 -->
				<div id="naver_id_login" style="text-align: center">
					<a href="${url }">
						<img width="223"
							src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" />
					</a>
				</div>
				<div class="text-success">
					<hr>
				</div>
				<div class="text-center">
					<a class="small" href="forgot-password.html">비밀번호 찾기</a>
				</div>
				<div class="text-center">
					<a class="small" href="register.html">회원가입 하러 가기</a>
				</div>
				<div class="tabs is-centered is-6-desktop">
					<ul>
						<li>
							<a target="_blank" href="${appRoot }/member/pw_find"
								class="find_text">비밀번호 찾기</a>
						</li>
						<li>
							<a target="_blank" href="" class="find_text">아이디 찾기</a>
						</li>
						<li>
							<a target="_blank" href="${appRoot }/member/signUpPage"
								class="find_text">회원가입</a>
						</li>
					</ul>
				</div>

			</form>



			<!-- 메세지 -->
			<c:if test="${not empty message }">
				<div class="alert alert-primary">${sessionScope.message }
					<c:remove var="message" scope="session"></c:remove>
				</div>
			</c:if>

		</div>
	</div>

	</div>
</body>
</html>