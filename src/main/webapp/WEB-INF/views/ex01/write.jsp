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
<!-- summernote  -->
 <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
 <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

<title>게시글 작성하기 </title>

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

		<textarea class="textarea"  id="summernote" name="body"> </textarea>
		<label class="label">파일</label>
		<input type="file" name="file" accept="image/*"/>
		<div class="mb-5"></div>
		<button class="button is-primary">등록</button>
	</form>
	<script>
	$(document).ready(function() {
		//여기 아래 부분
		$('#summernote').summernote({
			  height: 300,                 // 에디터 높이
			  minHeight: null,             // 최소 높이
			  maxHeight: null,             // 최대 높이
			  focus: true,       
			  // 에디터 로딩후 포커스를 맞출지 여부
			  lang: "ko-KR",					// 한글 설정
			  placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
			  callbacks: {	//여기 부분이 이미지를 첨부하는 부분
					onImageUpload : function(files, editor, welEditable) {
			            // 파일 업로드(다중업로드를 위해 반복문 사용)
						for (let i = 0; i < files.length; i++) {
               		 		uploadImageToS3ForSummerNote(files[i]);
            			}
					}
			  }
		});
		
        function uploadImageToS3ForSummerNote(file) {
            data = new FormData();
            data.append("file", file);
            $.ajax({
                url: 'uploadImageToS3ForSummerNote',
                data: data,
                type: "POST",
                cache: false,
                contentType: false,
                processData: false,
                enctype: 'multipart/form-data',
                success: function(data) {
                	console.log(data);
                	console.log(data.fileUrl);
                	
                    $('#summernote').summernote('editor.insertImage', data.url);
                },
                error: function (data) {
                    alert(data.responseText);
                }
            });
        }
	});
	</script>
</body>
</html>