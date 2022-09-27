<%@page import="spring.mvc.dto.OrderItemDto"%>
<%@page import="spring.mvc.dto.BillDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.ProductDto"%>
<%@page import="spring.mvc.dto.CategoryDto"%>
<%@page import="spring.mvc.dto.DeliverDto"%>
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
						<h3 class="card-title">Bill Detail</h3>
					</div>
					<%
						BillDto bill = (BillDto) request.getAttribute("bill");
					List<OrderItemDto> listOrder = (List<OrderItemDto>) request.getAttribute("listOrder");
					List<ProductDto> listProduct = (List<ProductDto>) request.getAttribute("listProduct");
					List<DeliverDto> listDeliver = (List<DeliverDto>) request.getAttribute("listDeliver");
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
						<p id="error">
						<div class="p-2">
							<div>
								<div class="form-group row">
									<label for="staticEmail" class="col">Product Detail</label>
									<table class="table table-borderless">
										<thead>
											<tr>
												<th>Product name</th>
												<th>Quantity</th>
												<th>Price</th>
											</tr>
										</thead>
										<tbody>
											<%
												for (int i = 0; i < listOrder.size(); i++) {
											%>
											<tr>
												<th><%=listProduct.get(i).getName()%></th>
												<td><%=listOrder.get(i).getQuantity()%></td>
												<td><%=listProduct.get(i).getPrice() * listOrder.get(i).getQuantity()%> VND</td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
								<hr>
								<div class="form-group row">
									<label for="staticEmail" class="col-2">Total</label>
									<div class="col-10">
										<input type="text" readonly class="form-control-plaintext" value="<%=bill.getTotal()%>">
									</div>
								</div>
								<div class="form-group row">
									<label for="staticEmail" class="col-2">Create At</label>
									<div class="col-10">
										<input type="text" readonly class="form-control-plaintext" value="<%=bill.getCreateAt()%>">
									</div>
								</div>
								<div class="form-group row">
									<label for="staticEmail" class="col-2">Status</label>
									<div class="col-10">
										<%
											if (bill.getStatus() == 0) {
										%><p style="color: red;">Pending</p>
										<%
											} else if (bill.getStatus() == 1) {
										%><p style="color: #9c27b0">Delivering</p>
										<%
											} else if (bill.getStatus() == 2) {
										%><p style="color: #32c67b;">Completed</p>
										<%
											} else {
										%><p style="color: #847713;">Cancelled</p>
										<%
											}
										%>
									</div>
								</div>
								<form method="post" action="<%=contextPath%>/admin/bill/deliver" id="detailSubmit">
									<%
										if (bill.getStatus() == 0) {
									%>
									<input type="hidden" class="form-control" name="billId" value="<%=bill.getId()%>">
									<div class="form-group row">
										<label for="deliverId" class="col-2 col-form-label">Deliver code:</label>
										<div class="col-10">
											<input type="text" class="form-control" name="deliverId" id="deliverId" placeholder="Deliver code">
										</div>
									</div>
									<div class="form-group row">
										<div class="offset-2 col-6">
											<a href="<%=contextPath%>/admin/bill/list" class="btn btn-secondary"> Back </a>
											<button name="submit" type="submit" class="btn btn-primary">Deliver</button>
										</div>
									</div>
									<%
										} else {
									%>
								</form>
								<%
									if ((bill.getStatus() == 1 || bill.getStatus() == 2) && listDeliver.size() > 0) {
								%>
								<hr>
								<div class="form-group row">
									<label for="staticEmail" class="col">Deliver Status</label>
									<table class="table table-borderless">
										<thead class="thead-dark">
											<tr>
												<th>Detail</th>
												<th>Location</th>
												<th>Time</th>
												<th>Status</th>
											</tr>
										</thead>
										<tbody>
											<%
												for (int i = 0; i < listDeliver.size(); i++) {
											%>
											<tr>
												<th><%=listDeliver.get(i).getTrackingDetail()%></th>
												<td scope="row"><%=listDeliver.get(i).getLocation()%></td>
												<td><%=listDeliver.get(i).getCheckpointDate()%></td>
												<td><%=listDeliver.get(i).getCheckpointDeliveryStatus()%></td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
								<%
									}
								%>
								<a href="<%=contextPath%>/admin/bill/list">
									<button name="cancel" class="btn btn-secondary">Back</button>
								</a>
								<%
									}
								%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
		<script src="\static\js\jquery-billDetail.js "></script>
	</div>
</body>
</html>
