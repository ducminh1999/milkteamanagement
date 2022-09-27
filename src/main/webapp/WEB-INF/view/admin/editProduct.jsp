<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="spring.mvc.dto.ProductDto"%>
<%@page import="spring.mvc.dto.CategoryDto"%>
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
						<h3 class="card-title">Edit Product</h3>
					</div>
					<%
						ProductDto product = null;
					product = (ProductDto) request.getAttribute("product");
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
							<form method="post" action="<%=contextPath%>/admin/product/edit?id=<%=product.getId()%>" id="editProduct">
								<div class="form-group row">
									<label for="name" class="col-2 col-form-label">Name</label>
									<div class="col-10">
										<input id="name" name="name" type="text" required="required" class="form-control"
											value="<%=product.getName() == null ? "" : product.getName()%>" />
									</div>
								</div>
								<div class="form-group row">
									<label for="price" class="col-2 col-form-label">Price</label>
									<div class="col-10">
										<input id="price" name="price" type="text" required="required" class="form-control"
											value="<%=product.getPrice() == 0 ? "0" : product.getPrice()%>" />
									</div>
								</div>
								<div class="form-group row">
									<label for="category" class="col-2 col-form-label">Category</label>
									<div class="col-4">
										<select id="categoryId" name="categoryId" class="custom-select">
											<%
												List<CategoryDto> listCategory = null;
											listCategory = (List<CategoryDto>) request.getAttribute("listCategory");
											for (CategoryDto category : listCategory) {
											%>
											<option value="<%=category.getId()%>"><%=category.getName()%></option>
											<%
												}
											%>
										</select>
									</div>
								</div>
								<div class="form-group row">
									<label for="description" class="col-2 col-form-label">Description</label>
									<div class="col-10">
										<textarea id="description" name="description" cols="40" rows="2" class="form-control"><%=product.getDescription() == null ? "" : product.getDescription()%></textarea>
									</div>
								</div>
								<div class="form-group row">
									<label for="imageData" class="col-2 col-form-label">Image</label>
									<div class="col-10 input-group mb-3">
										<div class="custom-file">
											<input class="form-control" id="imageFile" name="imageFile" type="text" value="<%=product.getImageFile() == null ? "" : product.getImageFile()%>">
										</div>
									</div>
								</div>
								<div class="form-group row">
									<div class="offset-2 col-6">
										<a href="<%=contextPath%>/admin/product/list">
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
