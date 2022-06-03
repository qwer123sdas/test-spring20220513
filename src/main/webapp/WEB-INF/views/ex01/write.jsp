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

<title>Insert title here</title>
</head>
<body>
	<nav:navbar></nav:navbar>

	<hr />
	<c:url value="/ex01/board/write" var="writeLink" />
	<!-- 글 작성  -->
	<%-- ${appRoot}/ex01/write2 --%>
	<form class="box" action="${writeLink }" method="post" enctype="multipart/form-data">
		<div class="field">
			<label class="label">제목</label>
			<div class="control">
				<input class="input" type="text" name="title"
					placeholder="제목을 입력해주세요.">
			</div>
		</div>
		<label class="label">내용</label>
		<textarea class="textarea" name="body"
			placeholder="10 lines of textarea" rows="10"
			placeholder="내용을 입력해주세요."> </textarea>
		<label class="label">파일</label>
		<input type="file" name="file" accept="image/*"/>
		<div class="mb-5"></div>
		<button class="button is-primary">등록</button>
	</form>


</body>
</html>