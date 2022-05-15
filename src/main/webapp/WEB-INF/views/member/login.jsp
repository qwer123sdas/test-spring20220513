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
<!-- Bulma  -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<title>로그인 화면</title>
</head>
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

	<!-- 로그인 -->
	<form action="${appRoot }/member/login" method="post">
		<div class="field">
			<label class="label">아이디</label>
			<div class="control">
				<input class="input" type="text" name="userID" >
			</div>
		</div>

		<div class="field">
			<label class="label">패스워드</label>
			<div class="control">
				<input class="input" type="password" name="userPW">
			</div>
		</div>
		<button>로그인</button>
	</form>
	
	<!-- 메세지 -->
	<c:if test="${not empty error }">
		<c:if test="${param.error }">
			<div class="alert alert-primary">아이디나 비밀번호가 일치 하지 않습니다.</div>
		</c:if>
	</c:if>
</body>
</html>