<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.UserDto"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Subin MilkTea | Admin</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="menu.jsp"></jsp:include>
		<div class="content-wrapper">
			<div class="p-4">
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">Edit Employee</h3>
					</div>
					<%
						UserDto user = (UserDto) request.getAttribute("user");
					String contextPath = request.getContextPath();
					String messageServer = (String) request.getAttribute("messageServer");
					if (messageServer == null)
						messageServer = "";
					%>
					<div class="p-4">
						<%
							if (!"".equals(messageServer)) {
						%>
						<div class="flashes col-12">
							<div class="alert alert-danger">
								<%=messageServer%>
							</div>
						</div>
						<%
							}
						%>
						<div class="p-2">
							<form method="post" action="<%=contextPath%>/admin/editUser" id="editUser">
								<div class="form-group row">
									<label for="fullName" class="col-2 col-form-label">FullName</label> <input type="hidden" name="id"
										value="<%=user.getId()%>" />
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
									<label for="salary" class="col-2 col-form-label">Salary</label>
									<div class="col-10">
										<input id="salary" name="salary" type="text" class="form-control" value="<%=user.getSalary()%>" />
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
									<label class="col-2">Position</label>
									<div class="col-10">
										<div class="custom-control custom-radio custom-control-inline">
											<input name="roleId" id="role_0" type="radio" class="custom-control-input" value="1"
												<%if (user.getRoleId() == 1) {%> checked="checked" <%}%> /> <label for="roleId_0"
												class="custom-control-label">Manager</label>
										</div>
										<div class="custom-control custom-radio custom-control-inline">
											<input name="roleId" id="roleId_1" type="radio" class="custom-control-input" value="2"
												<%if (user.getRoleId() == 2) {%> checked="checked" <%}%> /> <label for="roleId_1"
												class="custom-control-label">Employee</label>
										</div>
									</div>
								</div>
								<div class="form-group row">
									<label for="description" class="col-2 col-form-label">Description</label>
									<div class="col-10">
										<textarea id="description" name="description" cols="40" rows="2" class="form-control"><%=user.getDescription() == null ? "" : user.getDescription()%></textarea>
									</div>
								</div>
								<div class="form-group row">
									<div class="offset-2 col-6">
										<a href="<%=contextPath%>/admin/product/list">
											<button name="cancel" class="btn btn-secondary">Cancel</button>
										</a>
										<button name="submit" type="submit" class="btn btn-primary">Submit</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
