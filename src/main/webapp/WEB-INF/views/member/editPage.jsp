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

<title>회원정보 페이지</title>
</head>
<style>
.test {
	float: left;
	margin-right: 15px;
}

.panel-block {
	display: flex;
}

.container1 {
	display: flex;
}
</style>
<body>
	<article class="test panel is-success is-half">
		<p class="panel-heading">회원 관리</p>
		<p class="panel-tabs"></p>
		<div class="panel-block">
			<p class="control has-icons-left">
				<input class="input is-success" type="text" placeholder="Search">
				<span class="icon is-left">
					<i class="fas fa-search" aria-hidden="true"></i>
				</span>
			</p>
		</div>
		<a class="panel-block">
			<span class="panel-icon">
				<i class="fas fa-book" aria-hidden="true"></i>
			</span>
			내 프로필
		</a>
		<a class="panel-block">
			<span class="panel-icon">
				<i class="fas fa-book" aria-hidden="true"></i>
			</span>
			보안 설정
		</a>
		<a class="panel-block">
			<span class="panel-icon">
				<i class="fas fa-book" aria-hidden="true"></i>
			</span>
			미정
		</a>
	</article>

	<div class="card border-secondary mb-3">
		<div class="card-header">기본정보</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">이름</h5>
			<p class="card-text">Some quick example text to build on the card</p>
		</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">아이디</h5>
			<p class="card-text">Some quick example text to build on the card</p>
		</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">비밀번호</h5>
			<p class="card-text">Some quick example text to build on the card</p>
		</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">핸드폰번호</h5>
			<p class="card-text">Some quick example text to build on the card</p>
		</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">이메일 주소</h5>
			<p class="card-text">Some quick example text to build on the card</p>
		</div>
	</div>

	<div class="card border-secondary mb-3">
		<div class="card-header">Header</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">Secondary card title</h5>
			<p class="card-text">Some quick example text to build on the card
				title and make up the bulk of the card's content.</p>
		</div>
	</div>



</body>
</html>