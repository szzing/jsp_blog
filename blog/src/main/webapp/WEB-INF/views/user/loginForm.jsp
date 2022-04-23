<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<br>

<div class="container">
	<form action="/auth/loginProc" method="POST">
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" class="form-control" placeholder="Enter Username" id="username" name="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
		</div>
		
		<!-- <div class="form-group form-check">
			<label class="form-check-label">
				<input class="form-check-input" type="checkbox"> Remember me
			</label>
		</div> -->

	<button type="submit" id="btn-login" class="btn btn-primary">로그인</button>
	</form>
</div>

<!-- <script src="/js/user.js"></script> -->

<br>
<%@ include file="../layout/footer.jsp" %>