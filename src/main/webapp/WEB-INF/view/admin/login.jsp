<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Login</title>
<link rel="stylesheet" href="/static/css/bootstrap.min.css" />
<link rel="stylesheet" href="/static/fontawesome-5.15.3/css/all.css" />
<link rel="stylesheet" href="/static/css/style.css" />
</head>
<body style="height: 100vh">
	<div class="login-form">
		<!-- <div class="row-10 border rounded d-flex align-items-center justify-content-center" style="width:400px"> -->
		<div class="row-10 border rounded div-login">
			<%
				String messageServer = (String) request.getAttribute("messageServer");

			out.println("<script type=\"text/javascript\">");
			if ("Regist successful".equals(messageServer)) {
				out.println("alert('Regist successful');");
				messageServer = "";
			}
			out.println("</script>");
			String contextPath = request.getContextPath();
			if (messageServer == null)
				messageServer = "";
			String userName = (String) request.getAttribute("userName");
			if (userName == null)
				userName = "";
			String password = (String) request.getAttribute("password");
			if (password == null)
				password = "";
			%>
			<form id="loginForm" method="post">
				<div class="card-header">
					<p class="card-title h3">Please Sign In</p>
				</div>
				<p id="error"><%=messageServer%></p>
				<div class="card-body">
					<div class="form-group">
						<input type="text" name="userName" value="<%=userName%>" class="form-control" id="userName" placeholder="UserName"
							required />
					</div>
					<div class="form-group">
						<input type="password" name="password" value="<%=password%>" class="form-control" id="password"
							placeholder="Password" required />
					</div>
					<button type="submit" class="form-group btn btn-success form-control-file">Login</button>
				</div>
			</form>
		</div>
	</div>
	<script src="\static\js\jquery-3.5.1.slim.min.js"></script>
	<script src="\static\js\bootstrap.bundle.min.js"></script>
	<script src="\static\js\jquery.js "></script>
	<script src="\static\js\jquery-login.js "></script>
</body>
</html>
