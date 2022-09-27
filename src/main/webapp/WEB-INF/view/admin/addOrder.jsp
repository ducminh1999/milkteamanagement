<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.ProductDto"%>
<%@page import="spring.mvc.dto.CategoryDto"%>
<%@page import="spring.mvc.dto.CartDto"%>
<%@page import="spring.mvc.dto.SeatDto"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Subin MilkTea | Admin</title>
<link rel="stylesheet" type="text/css" href="/static/assets/css/style.css">
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
</head>
<body>
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="menu.jsp"></jsp:include>
		<div class="content-wrapper">
			<div class="p-4">
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">Add Order</h3>
					</div>
					<%
						List<CategoryDto> listCategory = null;
					listCategory = (List<CategoryDto>) request.getAttribute("listCategory");
					List<ProductDto> listProduct = (List<ProductDto>) request.getAttribute("listProduct");
					List<SeatDto> listSeat = (List<SeatDto>) request.getAttribute("listSeat");
					List<ProductDto> listProCate = new ArrayList<ProductDto>();
					int categoryIds = listCategory.get(0).getId();
					/* for (int i=0;i<listProduct.size() ;i++) {
						if (categoryIds == listProduct.get(i).getCategoryId()) {
							listProCate.add(listProduct.get(i));
						}
					} */
					String contextPath = request.getContextPath();
					String messageServer = (String) request.getAttribute("messageServer");
					if (messageServer == null)
						messageServer = "";
					List<ProductDto> listProductCart = (List<ProductDto>) request.getAttribute("listProductCart");
					List<CartDto> listCart = (List<CartDto>) request.getAttribute("listCart");
					int sum = 0;
					for (int i = 0; i < listCart.size(); i++) {
						sum += listCart.get(i).getQuantity() * listProductCart.get(i).getPrice();
					}
					Object userId = null;
					userId = session.getAttribute("loginUser");
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
							<form method="post" action="<%=contextPath %>/admin/orderItem/addOder" id="addOrder">
								<div class="form-group row">
									<label for="categoryId" class="col-2 col-form-label">Product</label>
									<div class="col-10">
										<select name="productId" id="productId" class="custom-select">
											<%
												for (ProductDto product : listProduct) {
											%>
											<option value="<%=product.getId()%>"><%=product.getName()%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>
								<div class="form-group row">
									<label for="categoryId" class="col-2 col-form-label">Quantity</label>
									<div class="col-10">
										<input name="quantity" id="quantity" class="form-control" required="required"/>
									</div>
								</div>
								<div class="form-group row">
									<div class="offset-2 col-6">
										<button name="submit" type="submit" class="btn btn-primary">Add</button>
									</div>
								</div>
							</form>
						</div>
						<div class="cart_table" <%if (listCart.size() == 0) {%> hidden <%}%>>
							<table class="table">
								<thead class="" data-wow-delay=".1s">
									<tr>
										<th scope="col">Product</th>
										<th scope="col">Price</th>
										<th scope="col">Quantity</th>
										<th scope="col">Subtotal</th>
									</tr>
								</thead>
								<tbody>
									<%
										for (int i = 0; i < listCart.size(); i++) {
									%>
									<form action="<%=contextPath%>/cart/edit" method="post">
										<input type="hidden" name="id" value="<%=listCart.get(i).getId()%>">
										<tr class="" data-wow-delay=".1s">
											<td>
												<div class="carttable_product_item">
													<a href="<%=contextPath%>/cart/delete?id=<%=listCart.get(i).getId()%>">
														<button type="button" class="remove_btn">
															<i class="fal fa-times"></i>
														</button>
													</a>
													<button type="submit" class="remove_btn" style="color: green">
														<i class="fal fa-check"></i>
													</button>
													<h3 class="item_title"><%=listProductCart.get(i).getName()%></h3>
												</div>
											</td>
											<td><span class="price_text1"><%=listProductCart.get(i).getPrice()%> VND</span></td>
											<td>
												<div class="quantity_input">
													<input class="input_number" name="quantity" id="sl<%=listProductCart.get(i).getId()%>"
														onkeyup="updatePrice();" type="text" min="0" max="30" value="<%=listCart.get(i).getQuantity()%>">
												</div>
											</td>
											<td><span class="price_text2" id="total<%=listProductCart.get(i).getId()%>"><%=listProductCart.get(i).getPrice() * listCart.get(i).getQuantity()%></span></td>
										</tr>
									</form>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
						<form action="<%=contextPath%>/admin/orderItem/checkout" method="POST">
							<ul class="carttable_footer ul_li_right wow fadeInUp" data-wow-delay=".1s">
								<li><div class="input-group">
										<div class="input-group-prepend">
											<label class="input-group-text" for="inputGroupSelect01">Table</label>
										</div>
										<select name="seatId" id="seatId" class="custom-select">
											<%
												for (SeatDto seat : listSeat) {
											%>
											<option value="<%=seat.getId()%>"><%=seat.getName()%></option>
											<%
												}
											%>
										</select>
									</div></li>
								<li>
									<div class="total_price text-uppercase">
										<span>total</span> <span id="sumTotal"><%=sum%></span>
									</div>
								</li>
								<li><button type="submit" class="btn btn_primary text-uppercase">Checkout</button></li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script>
		function loadData() {
<%-- 	<%int category = listCategory.get(0).getId();
for (ProductDto product : listProduct) {
	if (category == product.getCategoryId()) {
		listProCate.add(product);
	}
}		%> --%>
		}
		function clickCategory() {
			let categoryId = document.getElementById("categoryId").value;
			<%-- <%request.setAttribute("CustomerName",  out.println("<script>document.writeln(v)</script>");%>

			<%
			for (int i=0;i<listProduct.size() ;i++) {
				if (%>categoryId <% == listProduct.get(i).getCategoryId()) {
					listProCate.add(listProduct.get(i));
				}
			}
			%>--%>
		}
	</script>
		<script>
		function updatePrice() {
			var sum = 0;
			console.log("updatePrice")
			<%for (int i = 0; i < listCart.size(); i++) {%>
				var sl = document.getElementById("sl"+"<%=listProductCart.get(i).getId()%>").value;
				var price =
				<%=listProductCart.get(i).getPrice()%>
				var total = price * sl;
				sum = sum + total;
				var str = "total" +
					<%=listProductCart.get(i).getId()%>
				document.getElementById(str).innerHTML = total + " VND";
			<%}%>
			document.getElementById("sumTotal").innerHTML = sum + " VND";
		}
	</script>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
