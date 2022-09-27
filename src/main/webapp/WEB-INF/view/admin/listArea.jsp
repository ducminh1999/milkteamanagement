<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.AreaDto"%>
<%@page import="java.util.List"%>
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
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<div class="p-4">
				<div class="card card-primary">
					<div class="card-header">
						<div class="row">
							<div class="">
								<h3 class="card-title">List Area</h3>
							</div>
							<div class="form-inline ml-5">
								<form action="<%=request.getContextPath()%>/admin/area/search" method="POST">
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
							List<AreaDto> list = (List<AreaDto>) request.getAttribute("listArea");
						String contextPath = request.getContextPath();
						int i = 0;
						String messageServer = (String) request.getAttribute("messageServer");
						out.println("<script type=\"text/javascript\">");
						if ("Add area successful".equals(messageServer))
							out.println("alert('Add area successful');");
						else if ("Edit area successful".equals(messageServer))
							out.println("alert('Edit area successful');");
						else if ("Delete area successful".equals(messageServer))
							out.println("alert('Delete area successful');");
						out.println("</script>");
						%>
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">STT</th>
									<th scope="col">Name</th>
									<th scope="col">Create At</th>
									<th scope="col">Status</th>
									<th scope="col">Edit</th>
									<th scope="col">Delete</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (AreaDto area : list) {
									i++;
								%>
								<tr>
									<td scope="row"><%=i%></td>
									<td><%=area.getName()%></td>
									<td><%=area.getCreateAt()%></td>
									<%
										if (area.isStatus()) {
									%>
									<td><a href="<%=contextPath%>/admin/area/lock?id=<%=area.getId()%>"><button name="on"
												class="btn btn-success">on</button></a></td>
									<%
										} else {
									%>
									<td><a href="<%=contextPath%>/admin/area/lock?id=<%=area.getId()%>"><button name="off"
												class="btn btn-secondary">off</button></a></td>
									<%
										}
									%>
									<td><a href="<%=contextPath%>/admin/area/edit?id=<%=area.getId()%>"><button name="edit"
												class="btn btn-success">Edit</button></a></td>
									<td><a href="<%=contextPath%>/admin/area/delete?id=<%=area.getId()%>"><button name="delete"
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
