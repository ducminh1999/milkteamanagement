<%@page import="spring.mvc.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.UserDto"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Subin MilkTea | Admin</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed"">
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="menu.jsp"></jsp:include>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<div class="p-4">
				<div class="card card-primary">
					<div class="card-header">
						<div class="row">
							<div class="">
								<h3 class="card-title">List Employee</h3>
							</div>
							<div class="form-inline ml-5">
								<form action="<%=request.getContextPath()%>/admin/user/search" method="POST">
									<div class="input-group">
										<input class="form-control" name="nameSearch" type="search" placeholder="Search" aria-label="Search">
										<button class="btn btn-sidebar" type="submit">
											<i class="fas fa-search"></i>
										</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<section class="content">
						<%
							List<UserDto> list = (List<UserDto>) request.getAttribute("listUser");
						String contextPath = request.getContextPath();
						int i = 0;
						String messageServer = (String) request.getAttribute("messageServer");
						out.println("<script type=\"text/javascript\">");
						if ("Add user successful".equals(messageServer))
							out.println("alert('Add user successful');");
						else if ("Edit user successful".equals(messageServer))
							out.println("alert('Edit user successful');");
						else if ("Delete user successful".equals(messageServer))
							out.println("alert('Delete user successful');");
						out.println("</script>");
						%>
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">STT</th>
									<th scope="col">UserName</th>
									<th scope="col">FullName</th>
									<th scope="col">Email</th>
									<th scope="col">Phone</th>
									<th scope="col">Lock</th>
									<th scope="col">Edit</th>
									<th scope="col">Delete</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (UserDto user : list) {
									i++;
								%>
								<tr>
									<td scope="row"><%=i%></td>
									<td><%=user.getUserName()%></td>
									<td><%=user.getFullName()%></td>
									<td><%=user.getEmail()%></td>
									<td><%=user.getPhone()%></td>
									<%
										if (user.isStatus()) {
									%>
									<td><a href="<%=contextPath%>/admin/lockUser?id=<%=user.getId()%>"><button name="lock"
												class="btn btn-success">Lock</button></a></td>
									<%
										} else {
									%>
									<td><a href="<%=contextPath%>/admin/lockUser?id=<%=user.getId()%>"><button name="lock"
												class="btn btn-secondary">Unlock</button></a></td>
									<%
										}
									%>
									<td><a href="<%=contextPath%>/admin/editUser?id=<%=user.getId()%>"><button name="edit"
												class="btn btn-success">Edit</button></a></td>
									<td><a href="<%=contextPath%>/admin/deleteUser?id=<%=user.getId()%>"><button name="delete"
												class="btn btn-danger">Delete</button></a></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<%
							if (list.size() == 0) {
						%>
						<div>Danh sach trong</div>
						<%
							}
						%>
					</section>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
