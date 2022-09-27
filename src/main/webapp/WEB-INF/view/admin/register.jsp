<!DOCTYPE html>
<%@page import="spring.mvc.dto.UserDto"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<link rel="stylesheet" href="./static/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="./static/fontawesome-5.15.3/css/all.css" type="text/css" />
<link rel="stylesheet" href="./static/css/style.css" type="text/css" />
</head>
<body style="height: 100vh">
	<div class="login-form">
		<div class="row-10 border rounded div-register">
			<div class="card-header">
				<p class="card-title h3">Register</p>
			</div>
			<%
				String contextPath = (String) request.getContextPath();
			String message = (String) request.getAttribute("messageServer");
			if (message == null)
				message = "";

			UserDto user = null;
			user = (UserDto) request.getAttribute("user");
			%>
			<div class="p-4">
				<%
					if (!"".equals(message)) {
				%>
				<div class="flashes col-12">
					<div class="alert alert-danger">
						<%=message%>
					</div>
				</div>
				<%
					}
				%>
				<form method="post" action="<%=contextPath%>/register" id="addUser">
					<div class="form-group row">
						<label for="fullName" class="col-2 col-form-label">FullName</label>
						<div class="col-10">
							<input id="fullName" name="fullName" type="text" required="required" class="form-control"
								value="<%=user.getFullName() == null ? "" : user.getFullName()%>" />
						</div>
					</div>
					<div class="form-group row">
						<label for="userName" class="col-2 col-form-label">UserName</label>
						<div class="col-10">
							<input id="userName" name="userName" type="text" required="required" class="form-control"
								value="<%=user.getUserName() == null ? "" : user.getUserName()%>" />
						</div>
					</div>
					<div class="form-group row">
						<label for="address" class="col-2 col-form-label">Address</label>
						<div class="col-10">
							<input id="address" name="address" type="text" class="form-control"
								value="<%=user.getAddress() == null ? "" : user.getAddress()%>" />
						</div>
					</div>
					<div class="form-group row">
						<label for="phone" class="col-2 col-form-label">Phone</label>
						<div class="col-10">
							<input id="phone" name="phone" type="text" class="form-control"
								value="<%=user.getPhone() == null ? "" : user.getPhone()%>" />
						</div>
					</div>
					<div class="form-group row">
						<label for="email" class="col-2 col-form-label">Email</label>
						<div class="col-10">
							<input id="email" name="email" type="text" class="form-control"
								value="<%=user.getEmail() == null ? "" : user.getEmail()%>" />
						</div>
					</div>
					<div class="form-group row">
						<label for="email" class="col-2 col-form-label">Password</label>
						<div class="col-10">
							<input type="password" class="form-control" name="password" id="password" onkeyup="checkPass();" required
								value="<%=user.getPassword() == null ? "" : user.getPassword()%>" />
						</div>
					</div>
					<div class="form-group row">
						<label for="email" class="col-2 col-form-label">RePassword</label>
						<div class="col-10">
							<input type="password" class="form-control" name="rePassword" id="rePassword" onkeyup="checkPass();" required>
							<span id="message" style="color: red;"></span>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-2">Gender</label>
						<div class="col-10">
							<div class="custom-control custom-radio custom-control-inline">
								<input name="gender" id="gender_0" type="radio" class="custom-control-input" value="1"
									<%if (user.isGender()) {%> checked="checked" <%}%> /> <label for="gender_0" class="custom-control-label">Male</label>
							</div>
							<div class="custom-control custom-radio custom-control-inline">
								<input name="gender" id="gender_1" type="radio" class="custom-control-input" value="0"
									<%if (!user.isGender()) {%> checked="checked" <%}%> /> <label for="gender_1" class="custom-control-label">Female</label>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<div class="offset-4 col-8">
							<button name="submit" type="submit" class="btn btn-primary">Submit</button>
						</div>
					</div>
					<div class="link-register">
						<a href="<%=contextPath%>/login">Click here to Login</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		function checkPass() {
			console.log("aaaa")
			var password = document.getElementById("password").value
			var rePassword = document.getElementById("rePassword").value
			if (password !== rePassword) {
				document.getElementById("message").innerHTML = "Not Matching Password";
			} else {
				document.getElementById("message").innerHTML = "";
			}
		}
	</script>
	<script src=".\static\js\jquery-3.5.1.slim.min.js"></script>
	<script src=".\static\js\bootstrap.bundle.min.js"></script>
	<script src=".\static\js\jquery.js "></script>
	<script src=".\static\js\jquery-register.js "></script>
</body>
</html>