<%@ include file="header.jsp"%>
<form action="superServlet?action=register" method="post">
	<div class="panel panel-default">
		<div class="panel panel-heading">Registration Form</div>

		<div class="panel-body">
			<div class="form-group">
				<input type="name" class="form-control" name="name"
					id="exampleInputname" placeholder="name">
			</div>
			<div class="form-group">

				<c:if test="${message != null }">
					<h4 style="color: #780000">
						<c:out value="${message}" />
					</h4>
				</c:if>
				<input type="name" class="form-control" name="username"
					id="exampleInputUser1" placeholder="Username">
			</div>
			<div class="form-group">
				<input type="password" class="form-control" name="password"
					id="exampleInputPassword1" placeholder="Password">
			</div>
			<div class="form-group">
				<c:if test="${emailError != null }">
					<h4 style="color: #780000">
						<c:out value="${emailError}" />
					</h4>
				</c:if>
				<input type="email" class="form-control" name="email"
					id="exampleInputemail" placeholder="email">
			</div>

			<button type="submit" class="btn btn-info">Register</button>
		</div>
	</div>
</form>
<%@ include file="footer.jsp"%>