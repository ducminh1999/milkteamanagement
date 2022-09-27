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
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Revenue statistics</h1>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->
			<%
				String contextPath = request.getContextPath();
			List<BillDto> list = null;
			list = (List<BillDto>) request.getAttribute("listBill");
			List<String> listUser = null;
			listUser = (List<String>) request.getAttribute("listUser");
			int i = 0;
			String messageServer = (String) request.getAttribute("messageServer");
			if (messageServer == null)
				messageServer = "";
			/* int countEmployee = (int) request.getAttribute("countEmployee");
			int countArea = (int) request.getAttribute("countArea");
			int countProduct = (int) request.getAttribute("countProduct");
			int countBill = (int) request.getAttribute("countBill");
			long sumBillDay = (long) request.getAttribute("sumBillDay");
			long sumBillMonth = (long) request.getAttribute("sumBillMonth"); */
			%>
			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<!-- Small boxes (Stat box) -->
					<!-- Main row -->
					<div class="row">
						<!-- Left col -->
						<section class="col-lg-6 connectedSortable">
							<!-- Custom tabs (Charts with tabs)-->
							<div class="card" style="height: 300px">
								<div class="card-header">
									<h3 class="card-title">
										<i class="fas fa-chart-pie mr-1"></i> Search
									</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<form method="post" action="<%=contextPath%>/admin/bill/revenue">
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
										<div class="form-group">
											<span> Start date: </span> <input type="date" class="form-control" name="startDate" id="startDate"
												required="required">
										</div>
										<div class="form-group">
											<span> End date: </span> <input type="date" class="form-control" name="endDate" id="endDate" required>
										</div>
										<!-- /.card -->
										<div>
											<button type="submit" class="btn btn-primary">Search</button>
										</div>
									</form>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</section>
						<!-- /.Left col -->
						<!-- right col (We are only adding the ID to make the widgets sortable)-->
						<section class="col-lg-6 connectedSortable">
							<!-- Map card -->
							<div class="card bg-gradient-primary" style="height: 300px">
								<div class="card-header border-0">
									<h3 class="card-title">
										<i class="fas fa-clipboard-check mr-1"></i> Monthly revenue
									</h3>
									<!-- card tools -->
									<div class="card-tools"></div>
									<!-- /.card-tools -->
								</div>
								<div class="card-body">
									<h2>
										${sumBill}
										<%-- <%sumBillMonth%> --%>
										VNĐ
									</h2>
								</div>
								<!-- /.card-body-->
								<div class="card-footer bg-transparent">
									<!-- /.row -->
								</div>
							</div>
							<!-- /.card -->
						</section>
						<!-- right col -->
					</div>
					<!-- /.row (main row) -->
					<div class="row m-2">
						<%
							if (list.size() > 0) {
						%>
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
									<td><%=listUser.get(i - 1)%></td>
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
							} else {
						%>
						<div>Danh sach trong</div>
						<%
							}
						%>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
