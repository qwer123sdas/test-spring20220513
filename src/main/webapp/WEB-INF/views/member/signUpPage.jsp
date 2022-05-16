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
<title>회원가입</title>
<script>
	function checks() {
		// 값 불러오기
		var getId = document.getElementById("id");

		// value 불러오기
		var id = getId.value;

		// 유효성 검사?
		var regExp = /&[a-zA-Z0-9]{3,12}$/;

		// 
		if (!regExp.test(id)) {
			alert("아이디 다시 설정!");
			getId.value = "";
			getId.focus();
			return false;
		}
	}
</script>

<script>
	$(document).ready(function() {
		$("#sign-cancle").click(function(e) {
			e.preventDefault();
			if (confirm("취소하시겠습니까?")) {
				let form = $("#formButton");
				let actionAttr = "${appRoot }/member/loginPage";
				form.attr("action", actionAttr);
				form.submit();
			}
		});
	});
</script>
<script>
	$(document).ready(function() {
		$("#idCheck").on('click', function() {
			$.ajax({
				type : 'POST',
				url : '${appRoot}/member/idCheck?id=' + $('#memberID').val(),
				success : function(data) {
					if (data == 0) {
						alert("사용가능")
						$('#id-success').removeClass("d-none");
						$('#id-danger').addClass("d-none");
					} else {
						alert("사용 불가능")
						$('#id-success').addClass("d-none");
						$('#id-danger').removeClass("d-none");
						$('#memberID').val('');
					}
				}
			});
		});
	});
</script>
</head>

<style>
</style>
<body>
	<h1>회원가입</h1>
	<form action="${appRoot }/member/signUp" method="post" id="formButton"
		onSubmit="return checks()">
		<div class="field">
			<label class="label">ID</label>
			<div class="control has-icons-left has-icons-right">
				<input class="input is-success" type="text" name="memberID"
					id="memberID" placeholder="ID를 입력하세요.">
				<span class="icon is-small is-left">
					<i class="fas fa-user"></i>
				</span>
				<span class="icon is-small is-right">
					<i class="fas fa-check"></i>
				</span>
			</div>
			<button type="button" id="idCheck">중복 확인</button>
			<p class="help is-success d-none" id="id-success">This ID is available</p>
			<p class="help is-danger d-none" id="id-danger">This ID is invalid</p>
		</div>

		<div class="field">
			<label class="label">PassWord</label>
			<div class="control has-icons-left has-icons-right">
				<input class="input is-success" type="password" name="memberPW"
					placeholder="비밀번호를 입력하세요.">
				<span class="icon is-small is-left">
					<i class="fas fa-user"></i>
				</span>
				<span class="icon is-small is-right">
					<i class="fas fa-check"></i>
				</span>
			</div>
			<p class="help is-danger">This PassWord is invalid</p>
		</div>

		<div class="field">
			<label class="label">Re PassWord</label>
			<div class="control has-icons-left has-icons-right">
				<input class="input is-success" type="password" name="memberTempPW"
					placeholder="비밀번호를 한번 더 입력하세요.">
				<span class="icon is-small is-left">
					<i class="fas fa-user"></i>
				</span>
				<span class="icon is-small is-right">
					<i class="fas fa-check"></i>
				</span>
			</div>
			<p class="help is-success">This PassWord is available</p>
			<p class="help is-danger">This PassWord is invalid</p>
		</div>


		<div class="field">
			<label class="label">이름</label>
			<div class="control">
				<input class="input" type="text" name="memberNAME"
					placeholder="Text input">
			</div>
		</div>


		<div class="field">
			<label class="label">Email</label>
			<div class="control has-icons-left has-icons-right">
				<input class="input is-danger" type="email" name="memberEmail"
					placeholder="Email input" value="hello@">
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
			<label class="label">전화번호</label>
			<div class="control">
				<input type="text" name="memberRole" />
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
</body>
</html>