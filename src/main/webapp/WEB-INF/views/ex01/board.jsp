<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css"
	integrity="sha512-GQGU0fMMi238uA+a/bdWJfpUGKUkBdgfFdgBm72SUQ6BeyWjoY/ton0tEjH+OSH9iP4Dfh+7HM0I9f5eR0L/4w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!--Jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	referrerpolicy="no-referrer"></script>
<!-- Bulma  -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
<!-- summernote  -->
 <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
 <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
 
<title>${board.id }번 게시글</title>
<script>
	$(document).ready(function() {
		/* 게시글 */
		$("#edit-button1").click(function(e) {
			e.preventDefault();
			$("#input1").removeAttr("readonly");
			$("#summernote").removeAttr("readonly");
			$("#edit-button1").addClass("d-none");
			$("#delete-submit1").addClass("d-none");
			$("#modify-submit1").removeClass("d-none");
			$("#body").addClass("d-none");
			$("#content").removeClass("d-none");
		});
		// 메인화면 페이지 로드 함수
        $('#summernote').summernote({
            placeholder: '내용을 작성하세요',
            height: 400,
            maxHeight: 400
        });
		/* 게시글 삭제  */
		$("#delete-submit1").click(function(e) {
			e.preventDefault();
			if (confirm("삭제하시겠습니까?")) {
				let form1 = $("#formBoard");
				let actionAttr = "${appRoot }/ex01/board/delete";
				form1.attr("action", actionAttr);
				
				
				
				form1.submit();
			}
		});
		/* --------------------------------------------------------------------------------- */
		/* 댓글목록 */
		const replyList = function(){
			const data = {boardId : ${board.id}};
			
			$.ajax({
	 			url : '${appRoot}/ex01/reply/list',
				type : 'POST',
				data : data,
				success : function(list){
					/* 로딩대기 전에 내용물 지우기? */ 
					const replyListElement = $('#replyList1');
			 		replyListElement.empty();
					for(let i = 0; i < list.length; i++){
						
						const replyElement = $("<div class='d-flex' />");
						//const replyElement = $("<p />");
						replyElement.html(`
								<div class="p-2">
									<i class='mt-3 fa fa-reply fa fa-rotate-180' aria-hidden='true'></i>
								</div>
								<div class="flex-fill">
									<div class="card mt-2">
										<div class="card-header">
											<table>
												<tr class="align-middle">
													<td rowspan="2" class="pr-2">
														<i class="fa fa-user-o fa-2x"></i>
													</td>
													<td class="ml">\${list[i].writerNickName }</td>
												</tr>
												<tr>
													<td>
														<font size="2">\${list[i].inserted }</font>
													
														<!--댓글 수정 삭제 버튼 위치 -->
														<form class="replyEditFormContainer\${list[i].id }" id="reply-submit">
														</form>
													</td>
												</tr>
											</table>
										</div>
										<div class="card-body">
											<p id="reply-content" class="card-text">\${list[i].content }</p>
										</div>
										<div id="replyEditFormContainer\${list[i].id }"">
											
										</div>
									</div>
								</div>
								`);
						replyListElement.append(replyElement);
						
						/* 로그인한 계정에 따라 댓글 삭제 수정할 수있도록 */
						if(list[i].own){
							$('.replyEditFormContainer'+list[i].id).html(`
									<!-- 삭제 버튼 -->
									<button class="reply-delete-button" data-reply-id="\${list[i].id }">
										<i class="fa fa-window-close fa" aria-hidden="true"></i>
									</button>
									<!-- 수정 버튼 -->
									<button id="edit-reply-button-open">수정</button>
									`);
							
							$('#replyEditFormContainer' + list[i].id).html(`
							<form id="reply-submit">
								<input type="hidden" name="id" value="\${list[i].id}"/>
								<input id="modify-reply-content" class="is-5 d-none" type="text"
									value="\${list[i].content }" name="content" style="border:0 solid black"/>
								<button class="is-5 d-none" id="edit-reply-button-close" data-reply-id="\${list[i].id}" >수정완료</button>
							</form>`);
						}
						
					}  // for문 끝
					
					
					/*  댓글 수정버튼 전환*/
					$("#edit-reply-button-open").click(function(e) {
						e.preventDefault();
						$("#reply-content").addClass("d-none")
						$("#modify-reply-content").removeClass("d-none");
						$("#edit-reply-button-open").addClass("d-none")
						$("#edit-reply-button-close").removeClass("d-none");
					});
					
					// 댓글 수정
					replyModify();
					
					// 댓글 삭제
					replyDelete();
				},
				error : function(){
					console.log("댓글 가져오기 실패");
				}
			});
		}
		
		// 댓글 리스트 호출
		replyList();
		
		// 댓글 수정
		const replyModify = function(){
			$('#edit-reply-button-close').click(function(e){
				e.preventDefault();
				const id = $(this).attr("data-reply-id");
				const formElem = $('#replyEditFormContainer' + id).find('form');
				
				const data = {
						id :  formElem.find("[name=id]").val(),
						content : formElem.find("[name=content]").val() 
				}
				$.ajax({
					url : '${appRoot}/ex01/reply/modify',
					type : 'PUT',
					data : JSON.stringify(data),
					contentType : 'application/json',
					success : function(data){
						replyList();
					}
				});
			});
		}
		
		// 댓글 삭제
		const replyDelete = function(){
			$('.reply-delete-button').click(function(e){
				e.preventDefault();
				const replyId = $(this).attr("data-reply-id");
				const message = "댓글을 삭제하시겠습니까?";
				
				if(confirm(message)){
					$.ajax({
						url : '${appRoot}/ex01/reply/remove/'+replyId,
						type : 'DELETE',
						success : function(){
							window.location.reload();
						}
					});
				}
			});
		}

		

	});
</script>
</head>
<body>
	<nav:navbar></nav:navbar>

	<h1></h1>

	<form class="box" id="formBoard" action="${appRoot }/ex01/board/modify"
		method="post">
		<input type="hidden" name="id" value="${board.id }" />
		<div class="field">
			<label class="label">${board.id }번 게시글 제목</label>
			<div class="control">
				<input class="input" required id="input1" type="text" name="title"
					value="${board.title }" readonly>
			</div>
		</div>
		<div class="field">
			<label class="label">작성자</label>
			<div class="control">
				<input class="input" required  type="text" name="writerNickName"
					value="${board.writerNickName }" readonly>
			</div>
		</div>
		<label class="label">내용</label>
		
		<div class="panel-body" id="body">${board.body }</div>
		<div class="d-none" id=content>
			<textarea class="textarea"  id="summernote" name="body"
				placeholder="10 lines of textarea" rows="10" readonly>${board.body } 
			</textarea>
		</div>
		
		<c:forEach items="${board.fileName }" var="file">
			<%
			String file = (String)pageContext.getAttribute("file");
			String encodedFileName = URLEncoder.encode(file, "utf-8");
			pageContext.setAttribute("encodedFileName", encodedFileName);
			%>
			<figure class="image is-128x128">
	        	<img src="${imageUrl }/folder/${board.id }/${encodedFileName }" alt="Placeholder image">
	         </figure>
        </c:forEach>
		<%-- <img src="https://bucket0207-spring0520-teacher-test.s3.ap-northeast-2.amazonaws.com/board/${board.id }/${board.fileName }" alt="" /> --%>
		<div class="field">
			<label class="label">작성일시</label>
			<div class="control">
				<input class="input" type="datetime-local"
					value="${board.inserted }" readonly>
			</div>
		</div>
		<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="principal"/>
			<c:if test="${principal.username == board.memberId }">
				<button class="button is-primary" id="edit-button1">수정하기</button>
				<button class="button is-primary d-none" id="modify-submit1">수정완료</button>
				<button class="button is-primary" id="delete-submit1">삭제하기</button>
			</c:if>
		</sec:authorize>
	</form>

	<!-- 댓글 ---------------+--------------------------------------------------------->
	<c:url value="/ex01/reply/add" var="replyAddLink"></c:url>
	<form action="${appRoot}/ex01/reply/add">
		<input type="hidden" name="boardId" value="${board.id }" />
		<div class="card mb-2"  data-board-id="${board.id }">
			<div class="card-header bg-light">
				<i class="fa fa-comment fa"></i>
				댓글 ${board.numOfReply } >
			</div>
			<div class="card-body">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
						<textarea class="form-control" id="exampleFormControlTextarea1"
							name="content" rows="3"></textarea>
						<button type="submit" class="btn btn-dark mt-3" id="addReply1">
							댓글 쓰기</button>
					</li>
				</ul>
			</div>
		</div>
	</form>

	<div id="replyList1">
	</div>
</body>
</html>