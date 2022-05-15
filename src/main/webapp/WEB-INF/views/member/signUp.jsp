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
</head>
<body>
<h1>회원가입</h1>
	<div class="field">
		<label class="label">ID</label>
		<div class="control has-icons-left has-icons-right">
			<input class="input is-success" type="text" name="member_ID" placeholder="ID를 입력하세요."
				value="bulma"> <span class="icon is-small is-left"> <i class="fas fa-user"></i>
			</span> <span class="icon is-small is-right"> <i class="fas fa-check"></i>
			</span>
		</div>
		<p class="help is-success">This username is available</p>
		<p class="help is-danger">This username is invalid</p>
	</div>

	<div class="field">
		<label class="label">PassWord</label>
		<div class="control has-icons-left has-icons-right">
			<input class="input is-success" type="password" name="member_PW" placeholder="비밀번호를 입력하세요."
				value="bulma"> <span class="icon is-small is-left"> <i class="fas fa-user"></i>
			</span> <span class="icon is-small is-right"> <i class="fas fa-check"></i>
			</span>
		</div>
		<p class="help is-danger">This PassWord is invalid</p>
	</div>
	
	<div class="field">
		<label class="label">Re PassWord</label>
		<div class="control has-icons-left has-icons-right">
			<input class="input is-success" type="password" name="member_tempPW" placeholder="비밀번호를 한번 더 입력하세요."
				value="bulma"> <span class="icon is-small is-left"> <i class="fas fa-user"></i>
			</span> <span class="icon is-small is-right"> <i class="fas fa-check"></i>
			</span>
		</div>
		<p class="help is-success">This PassWord is available</p>
		<p class="help is-danger">This PassWord is invalid</p>
	</div>


	<div class="field">
		<label class="label">이름</label>
		<div class="control">
			<input class="input" type="text" name="member_NAME"
				placeholder="Text input">
		</div>
	</div>


	<div class="field">
		<label class="label">Email</label>
		<div class="control has-icons-left has-icons-right">
			<input class="input is-danger" type="email" placeholder="Email input"
				value="hello@"> <span class="icon is-small is-left">
				<i class="fas fa-envelope"></i>
			</span> <span class="icon is-small is-right"> <i
				class="fas fa-exclamation-triangle"></i>
			</span>
		</div>
		<p class="help is-success">This email is available</p>
		<p class="help is-danger">This email is invalid</p>
	</div>
	
	<div class="field">
		<div class="control">
			<label class="radio"> 성별 <input type="radio" name="question">
				남
			</label> <label class="radio"> <input type="radio" name="question">
				여
			</label>
		</div>
	</div>


	<div class="field">
		<label class="label">전화번호</label>
		<div class="control">
			<input type="text" />
		</div>
	</div>

	<div class="field">
		<div class="control">
			<label class="checkbox"> <input type="checkbox"> 
				I agree to the <a href="#">terms and conditions</a>
			</label>
		</div>
	</div>


	<div class="field is-grouped">
		<div class="control">
			<button class="button is-link">Submit</button>
		</div>
		<div class="control">
			<button class="button is-link is-light">Cancel</button>
		</div>
	</div>
</body>
</html>