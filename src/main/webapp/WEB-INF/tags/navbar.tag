<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- navBar -->
<nav class="navbar" role="navigation" aria-label="main navigation">
	<div class="navbar-brand has-icons-left" >
		<a class="navbar-item" href="${appRoot }/ex01/list">
			HOME
			<i class="fa-solid fa-house m"> </i>
		</a>
		<a role="button" class="navbar-burger" aria-label="menu"
			aria-expanded="false" data-target="navbarBasicExample">
			<span aria-hidden="true"></span>
			<span aria-hidden="true"></span>
			<span aria-hidden="true"></span>
		</a>
	</div>

	<div id="navbarBasicExample" class="navbar-menu">
		<div class="navbar-start">
			<sec:authorize access="isAuthenticated()">
				<a class="navbar-item" href="${appRoot }/ex01/write"> 글 작성 </a>
				<a class="navbar-item" href="${appRoot }/member/userDetailPage"> 회원 정보</a>
			</sec:authorize>
		</div>



		<div class="navbar-end">
			<div class="navbar-item">
				<sec:authorize access="!isAuthenticated()">
					<div class="buttons">
						<button class="button is-light"
							onclick="location.href='/spr/member/signUpPage'">
							<strong>Sign up</strong>
						</button>
						<button class="button is-light"
							onclick="location.href='/spr/naverlogin'">Log in</button>
					</div>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<div class="buttons">
						<p>${sessionScope.name }회원님</p>
						<button class="button is-light nav-link" type="submit" form="logoutForm1">로그아웃</button>
						<div class="d-none">
							<form action="${appRoot }/logout" id="logoutForm1" method="post">로그아웃</form>
						</div>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>
</nav>

