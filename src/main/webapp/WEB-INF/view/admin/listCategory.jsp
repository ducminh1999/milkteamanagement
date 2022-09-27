<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.CategoryDto"%>
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
								<h3 class="card-title">List Category</h3>
							</div>
							<div class="form-inline ml-5">
								<form action="<%=request.getContextPath()%>/admin/category/search" method="POST">
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
							List<CategoryDto> list = (List<CategoryDto>) request.getAttribute("listCategory");
						String contextPath = request.getContextPath();
						int i = 0;
						String messageServer = (String) request.getAttribute("messageServer");
						out.println("<script type=\"text/javascript\">");
						if ("Add category successful".equals(messageServer))
							out.println("alert('Add category successful');");
						else if ("Edit category successful".equals(messageServer))
							out.println("alert('Edit category successful');");
						else if ("Delete category successful".equals(messageServer))
							out.println("alert('Delete category successful');");
						out.println("</script>");
						%>
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">STT</th>
									<th scope="col">Name</th>
									<th scope="col">Create At</th>
									<th scope="col">Edit</th>
									<th scope="col">Delete</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (CategoryDto category : list) {
									i++;
								%>
								<tr>
									<td scope="row"><%=i%></td>
									<td><%=category.getName()%></td>
									<td><%=category.getCreateAt()%></td>
									<td><a href="<%=contextPath%>/admin/category/edit?id=<%=category.getId()%>"><button name="edit"
												class="btn btn-success">Edit</button></a></td>
									<td><a href="<%=contextPath%>/admin/category/delete?id=<%=category.getId()%>"><button name="delete"
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
