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
<!-- bootstrap - JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-12 col-lg-6"> <!-- 고정 크기 12, lg변화에 따라 6고정 -->
				<h1>새 비밀번호 등록</h1>
				<form action="${appRoot }/member/pw_new" method="POST">
					<input type="hidden" name="id" value="${id }"/>
					<label for="usernameInput1" class="form-label"> 새 비밀번호 </label>
					<input type="text" name="password" placeholder="새 비밀번호를 입력해주세요.">
					<br />
					<label for="usernameInput1" class="form-label"> 새 비밀번호 </label>
					<input type="text" name="passwordTemp" placeholder="새 비밀번호를 다시 한번 입력해주세요.">
					<br />
					<input class="btn btn-primary" type="submit" id="check" value="비밀번호 변경 완료">

				</form>
			</div>
		</div>
	</div>
	
	
</body>
</html>