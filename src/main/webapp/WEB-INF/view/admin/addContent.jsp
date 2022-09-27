<%-- <%@page import="spring.mvc.dto.ContentDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Add</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="_header.jsp"></jsp:include>
		<!-- content -->
		<div class="row">
			<jsp:include page="_menu.jsp"></jsp:include>
			<!-- Profile From Elements -->
			<%
				String contextPath = (String) request.getContextPath();
				ContentDto con = (ContentDto) request.getAttribute("content");
				String title = null;
				String brief = null;
				String content = null;
				int id = 0;
				if (con != null) {
					id = con.getId();
					title = con.getTitle();
					brief = con.getBrief();
					content = con.getContent();
				}
				if (title == null)
					title = "";
				if (brief == null)
					brief = "";
				if (content == null)
					content = "";
				String url = "/user/addContent";
				if (id != 0) {
					url = "/user/editContent?id=" + id;
				}
			%>
			<p class="mt-5 ml-5 pl-4" id="loading"></p>
			<div class="col-sm-10 a" id="content1">
				<%
					if (title == "") {
				%>
				<div class="row border-bottom div-lable">Add Content</div>
				<%
					} else {
				%>
				<div class="row border-bottom div-lable">Edit Content</div>
				<%
					}
				%>
				<div class="row-10 border rounded div-form">
					<form method="post" action="<%=contextPath + url%>" id="addForm">
						<div class="card-header">
							<p class="card-title p-0 m-0">Content From Elements</p>
						</div>
						<div class="card-body w-75">
							<p id="error"></p>
							<div class="form-group font-weight-bolder">
								<label for="title">Title</label> <input type="text" class="form-control" name="title" id="title"
									placeholder="Enter the title" value="<%=title%>" required>
							</div>
							<div class="form-group font-weight-bolder">
								<label for="brief">Brief</label>
								<textarea class="form-control" name="brief" id="brief" rows="3" required><%=brief%></textarea>
							</div>
							<div class="form-group font-weight-bolder">
								<label for="content2">Content</label>
								<textarea class="form-control" name="content" id="content2" rows="6" required><%=content%></textarea>
							</div>
							<div class="form-group form-inline">
								<button type="submit" class="btn btn-success border mr-2">Submit Button</button>
								<button type="button" onclick="resetAddContent()" class="btn btn-success border mr-2">Reset Button</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="../static/js/jquery-3.5.1.slim.min.js"></script>
	<script src="../static/js/bootstrap.bundle.min.js"></script>
	<script src="../static/js/jquery.js"></script>
	<script src="../static/js/jquery-addcontent.js "></script>
</body>
</html> --%>