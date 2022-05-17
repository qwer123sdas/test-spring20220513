<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			<c:if test="${empty sessionScope.id }">
				<a class="navbar-item"> 글 작성 </a>
			</c:if>
			<c:if test="${not empty sessionScope.id }">
				<a class="navbar-item" href="${appRoot }/ex01/write"> 글 작성 </a>
				<a class="navbar-item" href="${appRoot }/member/userDetailPage"> 회원 정보
				</a>
			</c:if>
		</div>




		<div class="navbar-end">
			<div class="navbar-item">
				<c:if test="${empty sessionScope.id }">
					<div class="buttons">
						<button class="button is-light"
							onclick="location.href='/spr/member/signUpPage'">
							<strong>Sign up</strong>
						</button>
						<button class="button is-light"
							onclick="location.href='/spr/member/loginPage'">Log in</button>
					</div>
				</c:if>

				<c:if test="${not empty sessionScope.id }">
					<div class="buttons">
						<p>${sessionScope.id }회원님</p>
						<form action="${appRoot }/member/logout">
							<input type="hidden" name="path" value="${URI }" />
							<button class="button is-light">Log out</button>
						</form>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</nav>

