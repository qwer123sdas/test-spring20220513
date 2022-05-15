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

<title>${board.id }번게시글</title>
<script>
	$(document).ready(function() {
		$("#edit-button1").click(function(e) {
			e.preventDefault();
			$("#input1").removeAttr("readonly");
			$("#textarea1").removeAttr("readonly");
			$("#edit-button1").addClass("d-none");
			$("#delete-submit1").addClass("d-none");
			$("#modify-submit1").removeClass("d-none");
		});

		$("#delete-submit1").click(function(e) {
			e.preventDefault();
			if (confirm("삭제하시겠습니까?")) {
				let form1 = $("#formBoard");
				let actionAttr = "${appRoot }/ex01/board/delete";
				form1.attr("action", actionAttr);

				form1.submit();
			}
		});

		/*  댓글  */
		$("#addReply1").click(function() {
			let form = $("$formAddReply");
			let actionAttr = "${appRoot }/ex01/reply/add";
			form.attr("action", actionAttr);
			form.submit();
		});


	});
	
	function addReply() {
		let formAddReply = $("$formAddReply");
		let actionAttr = "${appRoot}/ex01/reply/add";
		formAddReply.attr("action", actionAttr);
		formAddReply.submit();
	};
</script>
</head>
<body>
	<!-- navBar -->
	<nav class="navbar" role="navigation" aria-label="main navigation">
		<div class="navbar-brand">
			<a class="navbar-item" href="${appRoot }/ex01/list"> HOME<i
				class="fa-solid fa-house m"> </i>
			</a> <a role="button" class="navbar-burger" aria-label="menu"
				aria-expanded="false" data-target="navbarBasicExample"> <span
				aria-hidden="true"></span> <span aria-hidden="true"></span> <span
				aria-hidden="true"></span>
			</a>
		</div>

		<div id="navbarBasicExample" class="navbar-menu">
			<div class="navbar-start">
				<a class="navbar-item" href="${appRoot }/ex01/write"> 글 작성 </a> <a
					class="navbar-item"> Documentation </a>


			</div>

			<div class="navbar-end">
				<div class="navbar-item">
					<div class="buttons">
						<a class="button is-primary"> <strong>Sign up</strong>
						</a> <a class="button is-light"> Log in </a>
					</div>
				</div>
			</div>
		</div>
	</nav>


	<h1>${board.id }번게시글</h1>

	<form class="box" id="formBoard" action="${appRoot }/ex01/board/modify" method="post">
		<input type="hidden" name="id" value="${board.id }" />
		<div class="field">
			<label class="label">제목</label>
			<div class="control">
				<input class="input" required id="input1" type="text" name="title"
					value="${board.title }" readonly>
			</div>
		</div>
		<label class="label">내용</label>
		<textarea class="textarea" id="textarea1" name="body"
			placeholder="10 lines of textarea" rows="10" readonly>${board.body } </textarea>

		<div class="field">
			<label class="label">작성일시</label>
			<div class="control">
				<input class="input" type="datetime-local"
					value="${board.inserted }" readonly>
			</div>
		</div>
		<button class="button is-primary" id="edit-button1">수정하기</button>
		<button class="button is-primary d-none" id="modify-submit1">수정완료</button>
		<button class="button is-primary" id="delete-submit1">삭제하기</button>
	</form>

	<!-- 댓글 ------------------------------------------------------------------------>
	<c:url value="/ex01/reply/add" var="replyAddLink"></c:url>
	
	<div class="card mb-2">
		<div class="card-header bg-light">
			<i class="fa fa-comment fa"></i> 댓글작성
		</div>
		<div class="card-body">
			<ul class="list-group list-group-flush">
				<li class="list-group-item"><textarea class="form-control"
						id="exampleFormControlTextarea1" rows="3"></textarea>
						
					<button type="button" class="btn btn-dark mt-3" id="addReply1" onClick="addReply();">
						<!-- onClick="javascript:addReply();" -->
						댓글 쓰기
					</button></li>
			</ul>
		</div>
	</div>

	<c:forEach items="${replyList }" var="reply">
		<div class="d-flex">
			<div class="p-2">
				<i class='mt-3 fa fa-reply fa fa-rotate-180' aria-hidden='true'></i>
			</div>
			<div class="flex-fill">
				<div class="card mt-2">
					<div class="card-header">
						<table>
							<tr class="align-middle">
								<td rowspan="2" class="pr-2"><i class="fa fa-user-o fa-2x"></i></td>
								<td class="ml">${reply.id }</td>
							</tr>
							<tr>
								<td><font size="2">${reply.inserted }</font> <!--삭제 버튼  -->
									<span style="cursor: pointer"><i
										class="fa fa-window-close fa" aria-hidden="true"></i></span></td>
							</tr>
						</table>
					</div>
					<div class="card-body">
						<p class="card-text">${reply.content }</p>
						<!-- <span class="badge badge-dark" style="cursor:pointer"><a onClick="">답글</a></span> -->
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<!-- ------------------------------------------------------------------------------------------- -->

	<label for=""> 댓글 작성 > </label>
	<form action="${replyAddLink }" method="post">
		<input type="hidden" name="boardId" value="${board.id }" /> 댓글 : <input
			type="text" name="content" size="50" />
		<button>쓰기</button>
	</form>

	<hr />
	<div>
		<c:forEach items="${replyList }" var="reply">
			<div style="border: 1px solid black; margin-bottom: 3px;">
				${reply.inserted } : ${reply.content }
				<!-- 수정  -->
				<c:url value="/ex01/reply/modify" var="replyModifyLink"></c:url>
				<form action="${replyModifyLink }" method="post">
					<input type="hidden" name="id" value="${reply.id }" /> <input
						type="hidden" name="boardId" value="${board.id }" /> <input
						type="text" value="${reply.content }" name="content" />
					<button>수정</button>
				</form>
				<!-- 삭제 -->
				<c:url value="/ex01/reply/remove" var="replyRemoveLink" />
				<form action="${replyRemoveLink }" method="post">
					<input type="hidden" name="id" value="${reply.id }" /> <input
						type="hidden" name="boardId" value="${board.id }" />
					<button>삭제</button>
				</form>
			</div>

		</c:forEach>
	</div>

</body>
</html>