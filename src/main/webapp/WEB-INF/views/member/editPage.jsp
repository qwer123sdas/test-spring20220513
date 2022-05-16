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

<title>Insert title here</title>
</head>
<style>
</style>
<body>
	<h1>회원정보 페이지</h1>
	<article class="panel is-success">
		<p class="panel-heading">Success</p>
		<p class="panel-tabs">
			<a class="is-active">All</a>
			<a>Public</a>
			<a>Private</a>
			<a>Sources</a>
			<a>Forks</a>
		</p>
		<div class="panel-block">
			<p class="control has-icons-left">
				<input class="input is-success" type="text" placeholder="Search">
				<span class="icon is-left">
					<i class="fas fa-search" aria-hidden="true"></i>
				</span>
			</p>
		</div>
		<a class="panel-block is-active">
			<span class="panel-icon">
				<i class="fas fa-book" aria-hidden="true"></i>
			</span>
			내 프로필
		</a>
		<a class="panel-block">
			<span class="panel-icon">
				<i class="fas fa-book" aria-hidden="true"></i>
			</span>
			보안설정
		</a>
		<a class="panel-block">
			<span class="panel-icon">
				<i class="fas fa-book" aria-hidden="true"></i>
			</span>
			미정
		</a>
	</article>
</body>
</html>