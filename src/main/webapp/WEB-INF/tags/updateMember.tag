<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<h5 class="card-title">핸드폰번호</h5>
				<input class="input is-success" type="text" name="memberRole"
					value="${memberDto.memberRole }" style="border: 1 solid black" />
			</div>
			<div class="card-body text-secondary">
				<h5 class="card-title">이메일 주소</h5>
				<input class="input is-success" type="text" name="memberEmail"
					value="${memberDto.memberEmail }" style="border: 1 solid black" />
			</div>
			<input type="hidden" name="memberID" value="${memberDto.memberID }"/>
			<button>수정 완료</button>
			<hr />
			<div class="card-body text-secondary">
				<a class="card-title" id="removeButton">회원 탈퇴</a>
			</div>