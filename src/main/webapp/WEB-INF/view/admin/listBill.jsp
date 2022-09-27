<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.BillDto"%>
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
		<%
							List<BillDto> list = (List<BillDto>) request.getAttribute("listBill");
						List<String> listUser = (List<String>) request.getAttribute("listUser");
						String contextPath = request.getContextPath();
						int i = 0;
						String messageServer = (String) request.getAttribute("messageServer");
						out.println("<script type=\"text/javascript\">");
						if ("Add bill successful".equals(messageServer))
							out.println("alert('Add bill successful');");
						else if ("Edit bill successful".equals(messageServer))
							out.println("alert('Edit bill successful');");
						else if ("Delete bill successful".equals(messageServer))
							out.println("alert('Delete bill successful');");
						out.println("</script>");
						%>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<div class="p-4">
				<a href="<%=request.getContextPath()%>/admin/orderItem/add"><button class="btn btn-success mb-2">New
						Order</button></a>
				<div class="card card-primary">
					<div class="card-header">
						<div class="row">
							<div class="">
								<h3 class="card-title">List Order</h3>
							</div>
							<%-- <div class="form-inline ml-5">
								<form action="<%=request.getContextPath()%>/admin/bill/search" method="POST">
									<div class="input-group">
										<input class="form-control" name="nameSearch" type="search" placeholder="Search" aria-label="Search">
										<button class="btn btn-sidebar" type="submit">
											<i class="fas fa-search"></i>
										</button>
									</div>
								</form>
							</div> --%>
						</div>
					</div>
					<section class="content">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">STT</th>
									<th scope="col">User</th>
									<th scope="col">Total</th>
									<th scope="col">Create At</th>
									<th scope="col">Status</th>
									<th scope="col">Detail</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (BillDto bill : list) {
									i++;
								%>
								<tr>
									<td scope="row"><%=i%></td>
									<td><%=listUser.get(i-1) %></td>
									<td><%=bill.getTotal()%></td>
									<td><%=bill.getCreateAt()%></td>
									<td>
										<%
											if (bill.getStatus() == 0) {
										%><p style="color: red;">Pending</p> <%
										 	} else if (bill.getStatus() == 1) {
										 %><p style="color: #9c27b0">Delivering</p> <%
										 	} else if (bill.getStatus() == 2) {
										 %><p style="color: #32c67b;">Completed</p> <%
										 	} else {
										 %><p style="color: #847713;">Cancelled</p> <%
										 	}
										 %>
									</td>
									<td><a href="<%=contextPath%>/admin/bill/detail?id=<%=bill.getId()%>"><button name="Detail"
												class="btn btn-success">Detail</button></a></td>
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
