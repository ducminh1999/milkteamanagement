<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.ProductDto"%>
<%@page import="spring.mvc.dto.CartDto"%>
<%@page import="spring.mvc.dto.UserDto"%>
<%@page import="spring.mvc.dto.CheckoutInfo"%>
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
			String contextPath = request.getContextPath();
		List<ProductDto> listProduct = (List<ProductDto>) request.getAttribute("listProduct");
		List<CartDto> listCart = (List<CartDto>) request.getAttribute("listCart");
		int countCart = (int) request.getAttribute("countCart");
		Object userId = null;
		int ship = 10000;
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
						<li>Cart</li>
						<li>Checkout</li>
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
			<section class="cart_section sec_ptb_120 bg_default_gray">
				<div class="container container_boxed">
					<div class="checkout_form">
						<h3 class="form_title text-uppercase wow fadeInUp">Personal Info</h3>
						<div class="row wow fadeInUp" data-wow-delay=".3s">
							<div class="col-lg-6">
								<div class="form_item">
									<h4 class="form_field_title">Full Name</h4>
									<input type="text" readonly="readonly" name="fullName" placeholder="Full Name" value="${user.fullName}">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form_item">
									<h4 class="form_field_title">Phone Number</h4>
									<input type="tel" readonly="readonly" name="phone" placeholder="Phone Number" value="+84 ${user.phone}">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form_item">
									<h4 class="form_field_title">Adress</h4>
									<input type="text" name="adress" placeholder="Adress" value="${user.address}">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form_item">
									<h4 class="form_field_title">
										Mail Address<sup>*</sup>
									</h4>
									<input type="email" name="email" placeholder="Mail Address" readonly="readonly" value="${user.email}">
								</div>
							</div>
						</div>
						<!-- Deliver -->
						<h3 class="form_title text-uppercase wow fadeInUp">Deliver Method</h3>
						<div class="row wow fadeInUp" data-wow-delay=".3s">
							<div class="col-lg-6">
								<div class="form_item">
									<input type="text" readonly name="deliverMethod" placeholder="" value="Viettel Post">
								</div>
							</div>
						</div>
						<hr>
						<h2 class="form_title text-uppercase wow fadeInUp" data-wow-delay=".1s">Order Summary</h2>
						<table class="table table-borderless">
							<tbody>
								<%
									for (int i = 0; i < listCart.size(); i++) {
								%>
								<tr>
									<th scope="row"><%=listProduct.get(i).getName()%></th>
									<td><%=listCart.get(i).getQuantity()%></td>
									<td><%=listProduct.get(i).getPrice() * listCart.get(i).getQuantity()%> VND</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<div class="form-group row m-2">
							<label class="col-sm-2 col-form-label">Product Total</label>
							<div class="col-sm-10">
								<b class="form-control-plaintext" id="sumTotal">${checkoutInfo.paymentVNTotal} VND</b>
							</div>
						</div>
						<div class="form-group row m-2">
							<label class="col-sm-2 col-form-label">Delivery charges</label>
							<div class="col-sm-10">
								<b class="form-control-plaintext" id="sumShip"><%=ship %> VND</b>
							</div>
						</div>
						<div class="form-group row m-2">
							<label class="col-sm-2 col-form-label">Payment Total</label>
							<div class="col-sm-10">
								<b class="form-control-plaintext" id="sumPayment">${checkoutInfo.paymentVNTotal+10000} VND</b>
							</div>
						</div>
						<hr>
						<h2 class="form_title text-uppercase wow fadeInUp" data-wow-delay=".1s">Select payment Method</h2>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="paymentMethod" id="tien" value="1" checked
								onclick="checkPayment();" /> <label class="form-check-label" for="tien">Cash on Delivery </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="paymentMethod" id="paypal1" value="2"
								onclick="checkPayment();" /> <label class="form-check-label" for="paypal1">Paypal<%=contextPath%></label>
						</div>
						<div id="tienDiv">
							<form action="<%=contextPath%>/cart/checkout" method="post">
								<input type="hidden" name = "paymentVNTotal" value="${checkoutInfo.paymentVNTotal+10000}"/>
								<input type="hidden" name = "paymentTotal" value="${checkoutInfo.paymentTotal}"/>
								<input type="hidden" name = "shippingTotal" value="<%=ship%>"/>
								<input type="hidden" name = "deliverDay" value="${checkoutInfo.deliverDay}"/>
								<input type="hidden" name = "deliverId" value="1"/>
								<input type="hidden" name = "orderId" value=""/>
								<input type="hidden" name = "paymentMethod" id = "paymentMethod" value="1"/>
								<button type="submit" class="btn btn_primary text-uppercase">Place Order</button>
							</form>
						</div>
						<div id="payment" hidden="hidden">
							<div id="paypal-button-container"></div>
						</div>
					</div>
				</div>
			</section>
			<script>
			function checkPayment(){
				if(document.getElementById('paypal1').checked) {
					document.getElementById("payment").hidden = false;
					document.getElementById("tienDiv").hidden = true;
					document.getElementById("paymentMethod").innerHTML = "2";
					}else if(document.getElementById('tien').checked) {
						document.getElementById("payment").hidden = true;
						document.getElementById("tienDiv").hidden = false;
						document.getElementById("paymentMethod").innerHTML = "1";
					} 
			}
   </script>
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
	<script type="text/javascript"
		src="https://www.paypal.com/sdk/js?client-id=AYY1MzlE2ICz97JIYtUZcrvgPgidx4scfWqi37PU5tMgWh3VGgDFcpkBJgGt0SAzBj6-oJ8x8JCD23O9">
	</script>
<!-- 	<script type="text/javascript"
		src="https://www.paypal.com/sdk/js?client-id=AY8YFJ4OrWw0MDA6uyBIpNL0rN7zLW5UjJhloC-hBDY_8Djhp-skMXmf0dBgUAfdsZxSbq_j5A57iSHG">
	</script> -->
	<!-- <script type="text/javascript" src="https://www.paypal.com/sdk/js?client-id=sb"></script> -->
	<!-- <script type="text/javascript" src="/static/js/paypal.js"></script> -->
	<script>
			paypal.Buttons({
				createOrder : function(data, actions) {
					return actions.order.create({
						intent : 'CAPTURE', // capture payment from buyer
						payer : {
							name : {
								give_name : "${user.fullName}",
								surname : ""
							},
							address : {
								address_line_1 : "${user.address}",
								address_line_2 : "",
								admin_area_2 : "",
								admin_area_1 : "",
								postal_code : "",
								country_code : "VN"
							},
							email_address : "${user.email}",
							phone : {
								phone_type : "MOBILE",
								phone_number : {
									//national_number : "969979754"
									national_number : "${user.phone}"
								}
							}
						}, 
						purchase_units : [ {
							amount : {
								value : "${checkoutInfo.paymentTotal}", 
								currency_code : "USD"
							}
						}]
					})
				},
				onApprove: function(data, actions){
					/* let checkoutInfo = new FormData();
					checkoutInfo.append('paymentTotal', 1);
					checkoutInfo.append('paymentVNTotal', 1);
					checkoutInfo.append('shippingTotal', 1);
					checkoutInfo.append('deliverDay', 1);
					checkoutInfo.append('deliverId', '1');
					checkoutInfo.append('orderId', '1');
					checkoutInfo.append('paymentMethod', 1); */
					
					// payment approved
					return actions.order.capture().then(function(details){
						console.log(details);
						orderId = details.id;
						console.log(orderId);
						status = details.status;
						checkoutInfo={
								paymentTotal: '${checkoutInfo.paymentTotal}',
								paymentVNTotal: '${checkoutInfo.paymentVNTotal+10000}',
								shippingTotal: '${10000}',
								deliverDay: '1',
								deliverId:'1',
								orderId:orderId,
								paymentMethod:'2'
						}
						return fetch('/cart/checkoutPaypal', {
					        method: 'post',
					        headers: {
					          'Content-type': 'application/json'
					          //'Content-type': 'application/x-www-form-urlencoded'
					        },
					       //body: checkoutInfo
					       body: JSON.stringify(checkoutInfo)
					    	   
					      }).then(function(res){
					    	  console.log(res);
					        alert('Payment has been made! Please see the delivery status on orders page!');
					        //window.location.href = redirect_url
					        window.location.href = "<%=contextPath%>/paymentHistory"
					      }); 
						alert("Thanks for your payment! Order ID: "+orderId+"; Status: "+status);
						
					});
				},
			onCancel: function(data){
				alert("Payment cancelled");
			},
			/* onError: function(err){
				console.log(err);
				alert("Something wrong with your address information that prevents checkout");
			} */

			}).render("#paypal-button-container");
		
	</script>
	<!-- ======================================================= -->
	<script>
		function totalPrice() {
			<%-- var sum = 0;
	<%for (int i = 0; i < listCart.size(); i++) {%>
		var sl =
	<%=listCart.get(i).getQuantity()%>
		;
			var price =
	<%=listProduct.get(i).getPrice()%>
		var total = price * sl;
			sum = sum + total;
	<%}%>
		document.getElementById("sumTotal").innerHTML = sum + " VND"; --%>
		/* 	document.getElementById("sumPayment").innerHTML = sum + " VND"; */
		}
	</script>
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
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDk2HrmqE4sWSei0XdKGbOMOHN3Mm2Bf-M&ver=2.1.6"></script>
	<script src="/static/assets/js/gmaps.min.js"></script>
	<!-- jquery-ui - jquery include -->
	<script src="/static/assets/js/jquery-ui.js"></script>
	<!-- off canvas sidebar - jquery include -->
	<script src="/static/assets/js/mCustomScrollbar.js"></script>
	<!-- custom - jquery include -->
	<script src="/static/assets/js/main.js"></script>
</body>
</html>