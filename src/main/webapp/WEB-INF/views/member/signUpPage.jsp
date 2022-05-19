<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!-- Naver -->
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	
	
<title>회원가입</title>
<script>
	function checks() {
		// 값 불러오기  // value 불러오기
		var id = document.getElementById("id").value;
		var pW = document.getElementById("pw").value;
		var tempPw = document.getElementById("tempPw").value;
		var zoneCode = document.getElementById("zoneCode").value;
		var detailAddress = document.getElementById("detailAddress").value;
		var memberRole = document.getElementById("memberRole").value;
		var email = document.getElementById("email").value;
		if (id != "" && pw != "" && tempPw != "" && name != "" && zoneCode !="" && detailAddress != "" && memberRole != "" && email !="") {
			return true;
		} else {
			alert("전부 입력하시오");
			return false;
		}
	}

	// 아이디 중복 여부 
	$(document).ready(function() {
		var id = document.getElementById("id");
		$("#idCheck").on('click', function() {
			$.ajax({
				type : 'POST',
				url : '${appRoot}/member/idCheck?id=' + $('#id').val(),
				success : function(data) {
					if (data == 0) {
						alert("사용가능")
						$('#id-success').removeClass("d-none");
						$('#id-danger').addClass("d-none");
					} else {
						alert("사용 불가능")
						$('#id-success').addClass("d-none");
						$('#id-danger').removeClass("d-none");
						id.value = "";
						$('#memberID').val('');
					}
				}
			});
		});
	});
	// 비밀 번호 확인
	// inpu 태그에 onchange="pwCheck()" 넣기
	function pwCheck() {
		var pwReg = /^[a-zA-Z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,16}$/;
		var pw = document.getElementById("pw").value;
		var pw_msg = document.getElementById("pw_msg");

		if (pw == "") {
			pw_msg.innerHTML = "필수 정보입니다.";
			return;
		} else if (!pwReg.test(pw)) {
			pw_msg.innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
			return;
		} else {
			pw_msg.innerHTML = "";
			return true;
		}
	}
	// 비밀번호 재확인
	// input 태그에 onchange="pwConfirm()" 넣기
	function pwConfirm() {
		var pw = document.getElementById("pw");
		var tempPw = document.getElementById("tempPw");

		var pw_msg = document.getElementById("pw_msg");
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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	window.onload = function() {
		document.getElementById("address_kakao").addEventListener("click",function() {   //주소입력칸을 클릭하면
			new daum.Postcode( //카카오 지도 발생
					{
						oncomplete : function(data) { //선택시 입력값 세팅
							// 주소의 변수
							var addr = '';
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소 선택
								addr = data.roadAddress;
							} else { // 지번 주소 선택
								addr = data.jibunAddress;
							}

							document.getElementById("zoneCode").value = data.zonecode // 새 우편번호 넣기
							document.getElementById("mainAddress").value = addr; // 주소 넣기
							document.querySelector("input[name=detailAddress]").focus(); //상세입력 포커싱
						}
					}).open();
			});
	}
</script>
</head>

<style>
</style>
<body>

	<div class="box">
		<div class="column is-one-third  ">
			<p class="title is-1 is-centered">회원가입</p>
			<form action="${appRoot }/member/signUp" method="post"
				id="formButton" onsubmit="return checks()">
				<div class="field">
					<label class="label">ID</label>
					<div class="control has-icons-left has-icons-right">
						<input class="input is-success" type="text" name="memberID"
							id="id" placeholder="ID를 입력하세요." value="${id}">
						<span class="icon is-small is-left">
							<i class="fas fa-user"></i>
						</span>
						<span class="icon is-small is-right">
							<i class="fas fa-check"></i>
						</span>
					</div>
					<button type="button" id="idCheck">중복 확인</button>
					<p class="help is-success d-none" id="id-success">This ID is
						available</p>
					<p class="help is-danger d-none" id="id-danger">This ID is
						invalid</p>
				</div>

				<div class="field">
					<label class="label">비밀번호</label>
					<div class="control has-icons-left has-icons-right">
						<input class="input is-success" type="password" name="memberPW"
							id="pw" onchange="pwCheck()" placeholder="비밀번호를 입력하세요.">
						<span class="icon is-small is-left">
							<i class="fas fa-user"></i>
						</span>
						<span class="icon is-small is-right">
							<i class="fas fa-check"></i>
						</span>
					</div>
					<p class="help is-danger" id="pw_msg"></p>
				</div>

				<div class="field">
					<label class="label">비밀번호 재확인</label>
					<div class="control has-icons-left has-icons-right">
						<input class="input is-success" type="password"
							name="memberTempPW" id="tempPw" onchange="pwConfirm()"
							placeholder="비밀번호를 한번 더 입력하세요.">
						<span class="icon is-small is-left">
							<i class="fas fa-user"></i>
						</span>
						<span class="icon is-small is-right">
							<i class="fas fa-check"></i>
						</span>
					</div>
					<p class="help is-danger" id="temp_pw_msg"></p>
				</div>


				<div class="field">
					<label class="label">이름</label>
					<div class="control">
						<input class="input" type="text" name="memberName" id="name"
							placeholder="Text input" value="${name}">
					</div>
				</div>

				<!-- 주소  -->
				<div class="field">
					<label class="label">주소</label>
					<div class="control">
						<input class="input" type="text" id="zoneCode" name="zoneCode"
							placeholder="우편번호" readonly>
					</div>
					<div class="control">
						<input class="input" type="text" id="mainAddress"
							name="mainAddress" placeholder="주소">
						<span class="tag is-primary" id="address_kakao" >우편번호 찾기</span>
						<button id="address_kakao">우편번호 찾기</button>
					</div>
				</div>
				<div class="field">
					<label class="label">상세 주소</label>
					<div class="control">
						<input class="input" type="text" name="detailAddress" id="detailAddress"
							placeholder="상세주소">
					</div>
				</div>

				<div class="field">
					<label class="label">전화번호</label>
					<div class="control">
						<input type="text" name="memberRole" id="memberRole"/>
					</div>
				</div>

				<div class="field">
					<label class="label">Email</label>
					<div class="control has-icons-left has-icons-right">
						<input class="input is-danger" type="email" name="memberEmail"
							id="email" placeholder="Email input" value="hello@">
						<span class="icon is-small is-left">
							<i class="fas fa-envelope"></i>
						</span>
						<span class="icon is-small is-right">
							<i class="fas fa-exclamation-triangle"></i>
						</span>
					</div>
					<p class="help is-success">This email is available</p>
					<p class="help is-danger">This email is invalid</p>
				</div>
				<div class="field">
					<label class="label">생년월일</label>
					<div class="control">
						<input type="text" name="memberDate" />
					</div>
				</div>

				<div class="field">
					<div class="control">
						<label class="radio">
							성별
							<input type="radio" name="memberSex">
							남
						</label>
						<label class="radio">
							<input type="radio" name="memberSex">
							여
						</label>
					</div>
				</div>



				<div class="field">
					<div class="control">
						<label class="checkbox">
							<input type="checkbox">
							I agree to the
							<a href="#">terms and conditions</a>
						</label>
					</div>
				</div>


				<div class="field is-grouped">
					<div class="control">
						<button class="button is-link" id="sign-submit">Submit</button>
					</div>
					<div class="control">
						<button class="button is-link is-light" id="sign-cancle">Cancel</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	var naver_id_login = new naver_id_login("myKQG3U17i94iAlkHWR4",
			"http://localhost:8080/spr/member/naverCallBack");
	// 네이버 사용자 프로필 조회
	naver_id_login.get_naver_userprofile("naverSignInCallback()");

	function naverSignInCallback() {
		if(naver_id_login.getProfileData('id')){
			var id = naver_id_login.getProfileData('id');
			var name = naver_id_login.getProfileData('name');
			var email = naver_id_login.getProfileData('email');
			var gender = naver_id_login.getProfileData('gender');
			var birthday = naver_id_login.getProfileData('birthday');
			var mobile = naver_id_login.getProfileData('mobile');
		}
	}
		
	
	
	function signUpCheck(id, name, email){
		$.ajax({
			type: "POST",
			url: "${appRoot}/member/signUpCheck?memberKaKao=" + id + "&name=" + name+ "&email=" + email,
			data: {"memberKaKao" : "id", "memberName" : "name", "memberEmail" : "email"},
			contentType: "application/json; charset=utf-8",
			success : function(result){
							if(result == 0){
								alter("회원가입을 해야 합니다.")
							}else if(result == 1){
								alter("이미 회원가입이 되어있습니다.")
							}
					}
		});
	};
</script>
</html>