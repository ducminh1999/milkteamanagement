<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.SeatDto"%>
<%@page import="spring.mvc.dto.AreaDto"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Subin MilkTea | Admin</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed" onload="checkPass">
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="menu.jsp"></jsp:include>
		<div class="content-wrapper">
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Change Password</h1>
						</div>
					</div>
				</div>
			</div>
			<%
				SeatDto table = null;
			table = (SeatDto) request.getAttribute("table");
			String contextPath = request.getContextPath();
			String messageServer = (String) request.getAttribute("messageServer");
			if (messageServer == null)
				messageServer = "";
			%>
			<div class="p-4">
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">Change Password</h3>
					</div>
					<form method="post" action="<%=contextPath%>/admin/changePassword">
						<div class="card">
							<div class="card-body">
								<div class="flashes" <%="".equals(messageServer)?"hidden":""%>>
									<div class="alert alert-danger">
										<%=messageServer%>
									</div>
								</div>
								<div>
									<div class="form-group">
										<span> Old Pasword: </span> <input type="password" class="form-control" name="passOld" required>
									</div>
									<div class="form-group">
										<span> New Password: </span> <input type="password" class="form-control" name="passNew" id="passNew"
											onkeyup="checkPass();" required>
									</div>
									<div class="form-group">
										<span> Retype New Password: </span> <input type="password" class="form-control" name="rePassNew"
											id="rePassNew" onkeyup="checkPass();" required> <span id="message" style="color:red;"></span>
									</div>
								</div>
							</div>
						</div>
						<!-- /.card -->
						<div class="card-footer">
							<button type="submit" class="btn btn-primary">Change Password</button>
						</div>
					</form>
					<!-- /.form-box -->
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<script>
		function checkPass() {
			var passNew = document.getElementById("passNew").value
			var rePassNew = document.getElementById("rePassNew").value
			if (passNew !== rePassNew) {
				document.getElementById("message").innerHTML = "Not Matching Password";
			} else	{
				document.getElementById("message").innerHTML = "";}
		}
	</script>
</body>
</html>
