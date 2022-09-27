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
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="menu.jsp"></jsp:include>
		<div class="content-wrapper">
			<div class="p-4">
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">Add Table</h3>
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
							<form method="post" action="<%=contextPath%>/admin/table/add" id="editTable">
								<div class="form-group row">
									<label for="name" class="col-2 col-form-label">Name</label>
									<div class="col-10">
										<input id="name" name="name" type="text" required="required" class="form-control"
											value="<%=table.getName() == null ? "" : table.getName()%>" />
									</div>
								</div>
								<div class="form-group row">
									<label for="areaId" class="col-2 col-form-label">Area</label>
									<div class="col-10">
										<select id="areaId" name="areaId" class="custom-select">
											<%
												List<AreaDto> listArea = null;
											listArea = (List<AreaDto>) request.getAttribute("listArea");
											for (AreaDto seat : listArea) {
											%>
											<option value="<%=seat.getId()%>"><%=seat.getName()%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>
								<div class="form-group row">
									<label for="description" class="col-2 col-form-label">Description</label>
									<div class="col-10">
										<textarea id="description" name="description" cols="40" rows="2" class="form-control"><%=table.getDescription() == null ? "" : table.getDescription()%></textarea>
									</div>
								</div>
								<div class="form-group row">
									<div class="offset-2 col-6">
										<a href="<%=contextPath%>/admin/table/list">
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
