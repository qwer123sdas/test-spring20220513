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

<title>회원정보 페이지</title>
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function(){
    document.getElementById("address_kakao").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
            	// 주소의 변수
            	var addr = '';
            	if(data.userSelectedType === 'R'){ // 사용자가 도로명 주소 선택
            		addr = data.roadAddress;
            	}else{                             // 지번 주소 선택
            		addr = data.jibunAddress;
            	}
            		
            	document.getElementById("zoneCode").value = data.zonecode  // 새 우편번호 넣기
                document.getElementById("mainAddress").value = addr; // 주소 넣기
                document.querySelector("input[name=detailAddress]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
</script>
<script>
	$(document).ready(function() {
		$("#updateMember").click(function() {
			$("#memberDetail").addClass("d-none");
			$("#memberS").addClass("d-none");
			$("#updateMemberBoard").removeClass("d-none");
			$(".propil").addClass("is-active");
		});

		$("#deleteMember").click(function(e) {
			e.preventDefault();
			if (confirm("삭제하시겠습니까?")) {
				let form = $("#updateMemberForm");
				let actionAttr = "${appRoot }/member/deleteUser";
				form.attr("action", actionAttr);

				form.submit();
			}
		});
		// 비밀번호 수정하기 버튼
		$('#passwordModify').click(function(e){
			e.preventDefault();
			$('#passwordModifyArea').removeClass('d-none');
			
			let form1 = $("#formBoard");
			let actionAttr = "${appRoot }/member/editUserAndPW";
			form1.attr("action", actionAttr);
		});
		// 비밀번호 수정취소
		$('#passwordModifyCancle').click(function(e){
			e.preventDefault();
			$('#passwordModifyArea').addClass('d-none');
			
			let form1 = $("#formBoard");
			let actionAttr = "${appRoot }/member/editUser";
			form1.attr("action", actionAttr);
		});

	});
	
	function pwConfirm() {
		var pw = document.getElementById("newPW");
		var tempPw = document.getElementById("tempPw");

		var temp_pw_msg = document.getElementById("temp_pw_msg");

		if (!pwCheck()) {
			pw_msg.innerHTML = "다시 확인해주세요.";
			temp_pw_msg.innerHTML = "";
			tempPw.value = "";
			pw.focus();
			return;
		} else if (tempPw.value == "") {
			temp_pw_msg.innerHTML = "필수 정보입니다.";
			return;
		} else if (pw.value != tempPw.value) {
			temp_pw_msg.innerHTML = "비밀번호가 일치하지 않습니다.";
			tempPw.focus();
			return;
		} else {
			temp_pw_msg.innerHTML = "";
			return true;
		}
	}
</script>

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

		<p class="panel-heading">
			<a class="navbar-item" href="${appRoot }/ex01/list">
				<i class="fa-solid fa-house m"> </i>
			</a>
			회원 관리
		</p>
		<p class="panel-tabs"></p>
		<div class="panel-block">
			<p class="control has-icons-left">
				<input class="input is-success" type="text" placeholder="Search">
				<span class="icon is-left">
					<i class="fas fa-search" aria-hidden="true"></i>
				</span>
			</p>
		</div>
		<a class="panel-block propil" id="updateMember">
			<span class="panel-icon">
				<i class="fas fa-book" aria-hidden="true"></i>
			</span>
			내 프로필 관리
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
<c:if test="${empty sessionScope.name }">
	<form action="${appRoot }/spr/naverlogin"></form>
	<button>로그인 하시오 </button>
</c:if>
	
<c:if test="${not empty sessionScope.name }">
	<div class="card border-secondary mb-3" id="memberDetail">
		<div class="card-header">기본정보</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">이름</h5>
			<p class="card-text">${memberDto.memberName }</p>
		</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">아이디</h5>
			<p class="card-text">${memberDto.memberID }</p>
		</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">비밀번호</h5>
			<p class="card-text">*********</p>
		</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">핸드폰번호</h5>
			<p class="card-text">${memberDto.memberRole }</p>
		</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">이메일 주소</h5>
			<p class="card-text">${memberDto.memberEmail }</p>
		</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">생년월일</h5>
			<p class="card-text">${memberDto.memberDate }</p>
		</div>
		<div class="card-body text-secondary">
			<h5 class="card-title">집주소</h5>
			<p class="card-text">${memberDto.zoneCode }</p>
			<p class="card-text">${memberDto.mainAddress }</p>
			<p class="card-text">${memberDto.detailAddress }</p>
		</div>
	</div>

	<div class="card border-secondary mb-3" id="memberS">
		<div class="card-header">보안설정</div>
		<div class="card-body text-secondary">
			<h5 class="card-title"></h5>
			<p class="card-text">Some quick example text to build on the card
				title and make up the bulk of the card's content.</p>
		</div>
	</div>

	<!-- 회원 개인정보 수정-->
	<form action="${appRoot }/member/editUser" method="post"
		id="updateMemberForm">
		<div class="card border-secondary mb-3 d-none" id="updateMemberBoard">
			<div class="card-header">기본정보</div>
			<div class="card-body text-secondary">
				<h5 class="card-title">이름</h5>
				<input class="input is-success" type="text" name="memberName"
					value="${memberDto.memberName }" style="border: 1 solid black" />
			</div>
			<div class="card-body text-secondary">
				<h5 class="card-title">아이디</h5>
				<p class="card-text">${memberDto.memberID }</p>
			</div>
			<div class="card-body text-secondary">
				<h5 class="card-title"> 현재 비밀번호 <span class="tag is-primary" id="passwordModify" >수정</span></h5>
				<input class="input is-success" type="text" name="memberPW"
					value="" style="border: 1 solid black" />
				<div class="d-none" id="passwordModifyArea">
					<h5 class="card-title"> 신규 비밀번호</h5>
					<input class="input is-success " type="text" name="newPW"
						value="" style="border: 1 solid black" />
					<h5 class="card-title"> 신규 비밀번호 재입력</h5>
					<input class="input is-success" type="text" name="tempPW"
						value="" style="border: 1 solid black" />
					<p class="help is-danger" id="temp_pw_msg"></p>
					<span class="tag is-primary" id="passwordModifyCancle" >취소</span>
				</div>
			</div>
			
			<div class="card-body text-secondary">
				<h5 class="card-title">핸드폰번호</h5>
				<input class="input is-success" type="text" name="memberRole"
					value="${memberDto.memberRole }" style="border: 1 solid black" />
			</div>
			<div class="card-body text-secondary">
				<h5 class="card-title">이메일 주소</h5>
				<input class="input is-success" type="text" name="memberEmail"
					value="${memberDto.memberEmail }" style="border: 1 solid black" />
			</div>

			<div class="field">
				<label class="label">주소</label>
				<div class="control">
					<input class="input" type="text" id="zoneCode" name="zoneCode"
						value="${memberDto.zoneCode }">
				</div>
				<div class="control">
					<input class="input" type="text" id="mainAddress" name="mainAddress"
						value="${memberDto.mainAddress }">
					<span class="tag is-primary" id="address_kakao" >우편번호 찾기</span>
					<!-- <button id="address_kakao">우편번호 찾기</button> -->
				</div>
			</div>
			<div class="field">
				<label class="label">상세 주소</label>
				<div class="control">
					<input class="input" type="text" name="detailAddress"
						value="${memberDto.detailAddress }">
				</div>
			</div>
			<input type="hidden" name="memberID" value="${memberDto.memberID }" />
			<button>수정 완료</button>
			<hr />

			<div class="card-body text-secondary" id="deleteMember">
				<button>회원 탈퇴</button>
			</div>
		</div>
	</form>
	
	<form action="">
		
	</form>
</c:if>

</body>
</html>