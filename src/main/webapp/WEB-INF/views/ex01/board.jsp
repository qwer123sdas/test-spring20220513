<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--font-awesome  -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!--bootstrap  -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" integrity="sha512-GQGU0fMMi238uA+a/bdWJfpUGKUkBdgfFdgBm72SUQ6BeyWjoY/ton0tEjH+OSH9iP4Dfh+7HM0I9f5eR0L/4w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!--Jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
<title>${board.id }번 게시글</title>
</head>
<body>
	<h1>${board.id }번 게시글</h1>
	<container>
		<form class="row g-3">
		  <div class="col-md-6">
		    <label for="inputEmail4" class="form-label">제목</label>
		    <input type="text" class="form-control" id="title"  value="${board.title }" >
		  </div>
		  <div class="col-md-6">
		    <label for="inputAddress" class="form-label">날짜</label>
		    <input type="datetime-local" class="form-control" name="inserted" value="${board.inserted }" >
		  </div>
		  
			<div class="input-group">
			  <span class="input-group-text">내용</span>
			  <textarea class="form-control" name="body" cols="10" rows="10">${board.body }</textarea>
			</div>
	
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary">수정</button>
		  </div>
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary">삭제</button>
		  </div>
		</form>
	</container>
	
	
</body>
</html>