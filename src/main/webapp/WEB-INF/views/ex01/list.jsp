<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<!-- bootstrap_JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- Bulma  -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">

<title>게시판</title>
</head>

<body>
  <nav:navbar></nav:navbar>
	<!-- .container>.row>.col>h1{글 목록} -->
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>게시글 목록</h1>

				<!-- 게시글 목록 -->
				<table class="table">
					<thead>
						<tr>
							<th>
								<i class="fa-solid fa-hashtag"></i>
							</th>
							<th>제목</th>
							<th>작성자</th>
							<th>
								작성일시
								<i class="fa-solid fa-calendar"></i>
							</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${boardList }" var="board">
							<tr>
								<td>${board.id }</td>
									<td>
										<a href="${appRoot }/ex01/board/${board.id }">
											${board.title } </a>
										<c:if test="${board.numOfReply > 0 }">
										<span class="badge rounded-pill bg-light text-dark">
											<i class="fa-solid fa-comment-dots"></i>
											${board.numOfReply }
										</span>
									</c:if>
									</td>
									<td>${board.writerNickName }</td>
									<td>${board.inserted }</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

			</div>
		</div>
	</div>
</body>
</html>