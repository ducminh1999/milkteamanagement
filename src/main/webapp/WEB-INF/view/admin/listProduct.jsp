<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.ProductDto"%>
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
								<h3 class="card-title">List Product</h3>
							</div>
							<div class="form-inline ml-5">
								<form action="<%=request.getContextPath()%>/admin/product/search" method="POST">
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
							List<ProductDto> list = (List<ProductDto>) request.getAttribute("listProduct");
						List<String> listCategory = (List<String>) request.getAttribute("listCategory");
						String contextPath = request.getContextPath();
						int i = 0;
						String messageServer = (String) request.getAttribute("messageServer");
						out.println("<script type=\"text/javascript\">");
						if ("Add product successful".equals(messageServer))
							out.println("alert('Add product successful');");
						else if ("Edit product successful".equals(messageServer))
							out.println("alert('Edit product successful');");
						else if ("Delete product successful".equals(messageServer))
							out.println("alert('Delete product successful');");
						out.println("</script>");
						%>
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">STT</th>
									<th scope="col">Image</th>
									<th scope="col">Product name</th>
									<th scope="col">Price</th>
									<th scope="col">Category name</th>
									<th scope="col">Create At</th>
									<th scope="col">Lock</th>
									<th scope="col">Edit</th>
									<th scope="col">Delete</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (ProductDto product : list) {
									i++;
								%>
								<tr>
									<td scope="row"><%=i%></td>
									<td><img src="<%=product.getImageFile()%>" style="max-height: 35px" /></td>
									<td><%=product.getName()%></td>
									<td><%=product.getPrice()%></td>
									<td><%=listCategory.get(i - 1)%></td>
									<td><%=product.getCreateAt()%></td>
									<%
										if (product.isStatus()) {
									%>
									<td><a href="<%=contextPath%>/admin/product/lock?id=<%=product.getId()%>"><button name="lock"
												class="btn btn-success">Lock</button></a></td>
									<%
										} else {
									%>
									<td><a href="<%=contextPath%>/admin/product/lock?id=<%=product.getId()%>"><button name="lock"
												class="btn btn-secondary">Unlock</button></a></td>
									<%
										}
									%>
									<td><a href="<%=contextPath%>/admin/product/edit?id=<%=product.getId()%>"><button name="edit"
												class="btn btn-success">Edit</button></a></td>
									<td><a href="<%=contextPath%>/admin/product/delete?id=<%=product.getId()%>"><button name="delete"
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
