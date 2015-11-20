<%@ include file="header.jsp"%>

<!-- Login Form -->
			<form action="superServlet?action=login" method="post">
				<div class="panel panel-default">

					<div class="panel-body">
						<label for="exampleInputEmail1">Existing User?</label></br>
						<div class="form-group">
							<input
								type="name" class="form-control" name="username"
								id="exampleInputUser1" placeholder="Username">
						</div>
						<div class="form-group">
							<input
								type="password" class="form-control" name="password"
								id="exampleInputPassword1" placeholder="Password">
						</div>

						<button type="submit" class="btn btn-warning">Login</button>
					</div>
				</div>
			</form>
			
			<!-- Register Form -->
			<form action="superServlet?action=registerJSP" method="post">
				<div class="panel panel-default">
					<div class="panel-body">
						<label for="exampleInputEmail1">New User?</label></br>
						<button type="submit" class="btn btn-warning" name="register">Register</button>
					</div>
				</div>
			</form>

<%@ include file="footer.jsp"%>