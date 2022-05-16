<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="path"%>

<!-- navBar -->
<nav class="navbar" role="navigation" aria-label="main navigation">
	<div class="navbar-brand">
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
			<a class="navbar-item" href="${appRoot }/ex01/write"> 글 작성 </a>
			<a class="navbar-item"> Documentation </a>


		</div>

		<div class="navbar-end">
			<div class="navbar-item">
				<div class="buttons">
					<a class="button is-primary"
						href="http://localhost:8080/spr/member/signUp">
						<strong>Sign up</strong>
					</a>
					<a class="button is-light"
						href="http://localhost:8080/spr/member/login"> Log in </a>
				</div>
			</div>
		</div>
	</div>
</nav>

