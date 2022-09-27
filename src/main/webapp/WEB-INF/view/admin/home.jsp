<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
							<h1 class="m-0">Mangement</h1>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->
			<%
				String contextPath = request.getContextPath();
			int countEmployee = (int) request.getAttribute("countEmployee");
			int countArea = (int) request.getAttribute("countArea");
			int countProduct = (int) request.getAttribute("countProduct");
			int countBill = (int) request.getAttribute("countBill");
			long sumBillDay = (long) request.getAttribute("sumBillDay");
			long sumBillMonth = (long) request.getAttribute("sumBillMonth");
			%>
			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<!-- Small boxes (Stat box) -->
					<div class="row">
						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-info">
								<div class="inner">
									<h3><%=countProduct%></h3>
									<p>
									<h4>Product</h4>
									</p>
								</div>
								<div class="icon">
									<i class="ion ion-coffee"></i>
								</div>
								<a href="<%=contextPath%>/admin/product/list" class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-success">
								<div class="inner">
									<h3><%=countEmployee%></h3>
									<p>
									<h4>Employee</h4>
									</p>
								</div>
								<div class="icon">
									<i class="ion ion-person"></i>
								</div>
								<a href="<%=contextPath%>/admin/listUser" class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-warning">
								<div class="inner">
									<h3><%=countArea%></h3>
									<p>
									<h4>Area</h4>
									</p>
								</div>
								<div class="icon">
									<i class="ion ion-grid"></i>
								</div>
								<a href="<%=contextPath%>/admin/area/list" class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-danger">
								<div class="inner">
									<h3><%=countBill%></h3>
									<p>
									<h4>Bill</h4>
									</p>
								</div>
								<div class="icon">
									<i class="ion ion-trophy"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
					</div>
					<!-- /.row -->
					<!-- Main row -->
					<div class="row">
						<!-- Left col -->
						<section class="col-lg-7 connectedSortable" >
							<!-- Custom tabs (Charts with tabs)-->
							<div class="card" style="height: 300px">
								<div class="card-header">
									<h3 class="card-title">
										<i class="fas fa-chart-pie mr-1"></i> Today's Revenue
									</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<h4>
										<%=sumBillDay%> VNĐ</h4></div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</section>
						<!-- /.Left col -->
						<!-- right col (We are only adding the ID to make the widgets sortable)-->
						<section class="col-lg-5 connectedSortable">
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
									<h4>
										<%=sumBillMonth%> VNĐ</h4></div>
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
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
