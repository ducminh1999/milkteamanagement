<%@page import="spring.mvc.dto.BillDto"%>
<%@page import="spring.mvc.dto.CategoryDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.ProductDto"%>
<%@page import="spring.mvc.dto.CategoryDto"%>
<%@page import="spring.mvc.dto.OrderItemDto"%>
<%@page import="spring.mvc.dto.DeliverDto"%>
<%@page import="java.util.List"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Subin MilkTea</title>
<link rel="shortcut icon" href="/static/img/ruby.jpg">
<!-- fraimwork - css include -->
<link rel="stylesheet" type="text/css" href="/static/assets/css/bootstrap.min.css">
<!-- icon font - css include -->
<link rel="stylesheet" type="text/css" href="/static/assets/css/fontawesome.css">
<!-- animation - css include -->
<link rel="stylesheet" type="text/css" href="/static/assets/css/animate.css">
<!-- carousel - css include -->
<link rel="stylesheet" type="text/css" href="/static/assets/css/slick.css">
<link rel="stylesheet" type="text/css" href="/static/assets/css/slick-theme.css">
<!-- popup - css include -->
<link rel="stylesheet" type="text/css" href="/static/assets/css/magnific-popup.css">
<!-- jquery-ui - css include -->
<link rel="stylesheet" type="text/css" href="/static/assets/css/jquery-ui.css">
<!-- custom - css include -->
<link rel="stylesheet" type="text/css" href="/static/assets/css/style.css">
</head>
<body>
	<!-- body_wrap - start -->
	<div class="body_wrap">
		<!-- backtotop - start -->
		<div class="backtotop">
			<a href="#" class="scroll"> <i class="far fa-arrow-up"></i> <i class="far fa-arrow-up"></i>
			</a>
		</div>
		<!-- backtotop - end -->
		<!-- preloader - start -->
		<div id="preloader"></div>
		<%
			BillDto bill = (BillDto) request.getAttribute("bill");
		List<OrderItemDto> listOrder = (List<OrderItemDto>) request.getAttribute("listOrder");
		List<ProductDto> listProduct = (List<ProductDto>) request.getAttribute("listProduct");
		List<DeliverDto> listDeliver = (List<DeliverDto>) request.getAttribute("listDeliver");
		String messageServer = (String) request.getAttribute("messageServer");
		if (messageServer == null)
			messageServer = "";
		String contextPath = request.getContextPath();
		int countCart = (int) request.getAttribute("countCart");
		Object userId = null;
		userId = session.getAttribute("loginUser");
		%>
		<!-- preloader - end -->
		<!-- header_section - start
			================================================== -->
		<header class="header_section style_2">
			<div class="content_wrap">
				<div class="container maxw_1560">
					<div class="row align-items-center">
						<div class="col-lg-2 col-md-6 col-6">
							<div class="brand_logo">
								<a class="brand_link" href="<%=contextPath%>/home"> <img style="max-width: 60px; height: auto;"
									src="/static/img/ruby.jpg" alt="logo_not_found">
								</a> <span style="color: white; font-size: 25px; font-weight: 700">Subin MilkTea</span>
							</div>
						</div>
						<div class="col-lg-10 col-md-6 col-6">
							<nav class="main_menu navbar navbar-expand-lg">
								<button class="mobile_menu_btn navbar-toggler" type="button" data-bs-toggle="collapse"
									data-bs-target="#main_menu_dropdown" aria-controls="main_menu_dropdown" aria-expanded="false"
									aria-label="Toggle navigation">
									<span class="navbar-toggler-icon"><i class="fal fa-bars"></i></span>
								</button>
								<div class="main_menu_inner collapse navbar-collapse" id="main_menu_dropdown">
									<ul class="main_menu_list ul_li">
										<li class="dropdown"><a class="nav-link" href="<%=contextPath%>/home"> Home </a>
										<li class="dropdown"><a class="nav-link" href="<%=contextPath%>/aboutMe">About Me</a></li>
									</ul>
								</div>
								<ul class="header_btns_group ul_li_right">
									<li>
										<form action="<%=request.getContextPath()%>/search" method="POST">
											<div class="input-group">
												<input class="form-control mt-3 mb-3" style="height: 30px" name="nameSearch" type="search"
													placeholder="Search" aria-label="Search">
												<button class="btn btn-sidebar" type="submit">
													<i class="fas fa-search"></i>
												</button>
											</div>
										</form>
									</li>
									<li><a href="<%=contextPath%>/cart/list"><button type="button" class="cart_btn">
												<i class="fal fa-shopping-bag"></i> <small class="cart_counter"><%=countCart%></small>
											</button></a></li>
									<%
										if (userId == null) {
									%><li><a href="<%=contextPath%>/login" style="color: #fff">Login</a></li>
									<%
										} else {
									%>
									<li><a href="<%=contextPath%>/myAccount" style="color: #fff">My Account</a></li>
									<li><a href="<%=contextPath%>/logout" style="color: #fff">Logout</a></li>
									<%
										}
									%>
									<%
										if (userId == null) {
									%><li><a href="<%=contextPath%>/register" style="color: #fff">Register</a></li>
									<%
										}
									%>
									<li><a class="btn btn_primary text-uppercase" href="<%=contextPath%>/cart/checkout">Checkout</a></li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<!-- collapse search - start -->
			<div class="main_search_collapse collapse" id="main_search_collapse">
				<div class="main_search_form card">
					<div class="container maxw_1560">
						<form action="#">
							<div class="form_item">
								<input type="search" name="search" placeholder="Search here...">
								<button type="submit" class="submit_btn">
									<i class="fal fa-search"></i>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- collapse search - end -->
		</header>
		<!-- header_section - end
			================================================== -->
		<!-- main body - start
			================================================== -->
		<main>
			<!-- breadcrumb_section - start
				================================================== -->
			<section class="breadcrumb_section text-uppercase" style="background: #1c334a">
				<div class="container">
					<h1 class="page_title text-white wow fadeInUp" data-wow-delay=".1s">Online Shop</h1>
					<ul class="breadcrumb_nav ul_li wow fadeInUp" data-wow-delay=".2s">
						<li><a href="<%=contextPath%>/home"><i class="fas fa-home"></i> Home</a></li>
						<li><a href="<%=contextPath%>/myAccount">My Account</a></li>
						<li><a href="<%=contextPath%>/paymentHistory">Payment History</a></li>
					</ul>
				</div>
				<div class="breadcrumb_icon_wrap">
					<div class="container">
						<div class="breadcrumb_icon wow fadeInUp" data-wow-delay=".3s">
							<img src="/static/img/milktea.png" alt="icon_not_found"> <span class="bg_shape"></span>
						</div>
					</div>
				</div>
			</section>
			<!-- breadcrumb_section - end
				================================================== -->
			<!-- shop_section - start
        ================================================== -->
			<section class="details_section shop_details sec_ptb_120 bg_default_gray">
				<div class="container">
					<div class="row">
						<div id="sidebar" class="col-md-4">
							<div class="widget wid-categories">
								<div class="heading">
									<h4>&nbsp;</h4>
								</div>
								<div class="content">
									<ul class="nav flex-column">
										<li class="nav-item"><a class="nav-link active" href="<%=contextPath%>/myAccount">Edit Account</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=contextPath%>/paymentHistory" style="color: red;">Payment
												History</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=contextPath%>/changePassword">Change Password</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=contextPath%>/logout">Logout</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div id="main-content" class="col-md-8">
							<div class="widget wid-categories">
								<div class="heading">
									<h4>Payment history</h4>
								</div>
								<section class="content">
									<div class="p-2">
											<div class="form-group row">
												<label for="staticEmail" class="col">Product Detail</label>
												<table class="table table-borderless m-4">
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
											<div class="form-group row">
												<label for="staticEmail" class="col-2">Total</label>
												<div class="col-10">
													<p><%=bill.getTotal()%></p>
												</div>
											</div>
											<div class="form-group row">
												<label for="staticEmail" class="col-2">Create At</label>
												<div class="col-10">
													<p><%=bill.getCreateAt()%></p>
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
											<%
												if ((bill.getStatus() == 1 || bill.getStatus() == 2) && listDeliver.size() > 0) {
											%>
											<div class="form-group row">
												<label for="staticEmail" class="col">Deliver Status</label>
												<table class="table table-borderless ml-4 mr-4" style="width:95%">
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
											<a href="<%=contextPath%>/paymentHistory">
												<button name="cancel" class="btn btn-secondary">Back</button>
											</a>
											<%
												if (bill.getStatus() == 1 /*  && listDeliver.size() > 0 */) {
											%>
											<a href="<%=contextPath%>/confirmOrder?id=<%=bill.getId()%>">
												<button name="receive" class="btn btn-success">Order received</button>
											</a>
											<%
												}
											%>
									</div>
								</section>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- details_section - end
			<!-- shop_section - end
        ================================================== -->
		</main>
		<!-- main body - end
			================================================== -->
		<!-- footer_section - start
			================================================== -->
		<footer class="footer_section text-white deco_wrap"
			style="background-image: url(/static/assets/images/backgrounds/1920x910.jpg);">
			<div class="overlay"></div>
			<div class="footer_widget_area">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-5 col-md-6 col-sm-7">
							<div class="footer_subscribe_form text-center">
								<h2 class="form_title text-uppercase wow fadeInUp" data-wow-delay=".1s">Milk Tea Build your Fresh mind</h2>
								<form action="#">
									<div class="form_item wow fadeInUp" data-wow-delay=".2s">
										<input type="email" name="email" placeholder="Enter your email">
										<button class="btn btn_primary text-uppercase" type="submit">Subscribe Now</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="row justify-content-lg-between">
						<div class="col-lg-3 col-md-4 col-sm-6">
							<div class="footer_widget footer_about wow fadeInUp" data-wow-delay=".1s">
								<div class="brand_logo">
									<a class="brand_link" href="<%=contextPath%>/home"> <img style="max-width: 60px; height: auto"
										src="/static/img/ruby.jpg" alt="logo_not_found"> <span style="color: white; font-size: 20px">Subin
											MilkTea</span>
									</a>
									<p>Subin Milk Tea Shop is a food shop that attracts the attention of many customers, especially young
										people in Da Nang and tourists when coming to this young city, with delicious and attractive dishes. , natural
										ingredients and especially food safety and hygiene here have delighted many customers.</p>
								</div>
								<ul class="social_links social_icons ul_li">
									<li><a href="#!"><i class="fab fa-facebook-f"></i></a></li>
									<li><a href="#!"><i class="fab fa-twitter"></i></a></li>
									<li><a href="#!"><i class="fab fa-instagram"></i></a></li>
									<li><a href="#!"><i class="fab fa-youtube"></i></a></li>
									<li><a href="#!"><i class="fab fa-behance"></i></a></li>
								</ul>
							</div>
						</div>
						<div class="col-lg-3 col-md-4 col-sm-6">
							<div class="footer_widget footer_contact wow fadeInUp" data-wow-delay=".2s">
								<h3 class="footer_widget_title text-uppercase">Contact us</h3>
								<ul class="ul_li_block">
									<li><strong class="text-uppercase">Adress:</strong> Da Nang University Techonlogy</li>
									<li><strong class="text-uppercase">Mail:</strong> subinmilktea@gmail.com</li>
									<li><strong class="text-uppercase">Phone:</strong> +84 345 254 xxx</li>
								</ul>
							</div>
						</div>
						<div class="col-lg-3 col-md-4 col-sm-6">
							<div class="footer_widget footer_opening_time wow fadeInUp" data-wow-delay=".3s">
								<h3 class="footer_widget_title text-uppercase">Opening Hours</h3>
								<ul class="ul_li_block">
									<li>Monday <span>9:00 - 22:00</span>
									</li>
									<li>Tuesday <span>9:00 - 22:00</span>
									</li>
									<li>Wednesday <span>9:00 - 22:00</span>
									</li>
									<li>Thursday <span>9:00 - 22:00</span>
									</li>
									<li>Friday <span>9:00 - 22:00</span>
									</li>
									<li>Saturday <span>8:00 - 23:00</span>
									</li>
									<li>Sunday <span>8:00 - 23:00</span>
									</li>
								</ul>
							</div>
						</div>
						<div class="col-lg-3 col-md-4 col-sm-6">
							<div class="footer_widget footer_recent_post wow fadeInUp" data-wow-delay=".4s">
								<h3 class="footer_widget_title text-uppercase">Policy Info</h3>
								<ul class="text-uppercase">
									<li>Payments</li>
									<li>Cancellation & Returns</li>
									<li>Shipping and Delivery</li>
									<li>Privacy Policy</li>
									<li>T & C</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="footer_bottom text-center">
					<p class="copyright_text mb-0 wow fadeInUp" data-wow-delay=".2s">
						Copyright@ 2022 Desing by <a class="btn_text" href="#"><span>Nguyen Duc Minh</span></a>
					</p>
				</div>
			</div>
		</footer>
		<!-- footer_section - end
			================================================== -->
	</div>
	<!-- body_wrap - end -->
	<!-- fraimwork - jquery include -->
	<script src="/static/assets/js/jquery.min.js"></script>
	<script src="/static/assets/js/bootstrap.min.js"></script>
	<!-- animation - jquery include -->
	<script src="/static/assets/js/wow.min.js"></script>
	<!-- carousel - jquery include -->
	<script src="/static/assets/js/slick.min.js"></script>
	<!-- popup - jquery include -->
	<script src="/static/assets/js/magnific-popup.min.js"></script>
	<!-- isotope filter - jquery include -->
	<script src="/static/assets/js/isotope.pkgd.min.js"></script>
	<!-- google map - jquery include -->
	<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDk2HrmqE4sWSei0XdKGbOMOHN3Mm2Bf-M&ver=2.1.6"></script> -->
	<script src="/static/assets/js/gmaps.min.js"></script>
	<!-- jquery-ui - jquery include -->
	<script src="/static/assets/js/jquery-ui.js"></script>
	<!-- off canvas sidebar - jquery include -->
	<script src="/static/assets/js/mCustomScrollbar.js"></script>
	<!-- custom - jquery include -->
	<script src="/static/assets/js/main.js"></script>
</body>
</html>