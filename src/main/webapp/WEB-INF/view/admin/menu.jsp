
<%@page import="spring.mvc.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<%
		String contextPath = request.getContextPath();
		int roleId = (int) session.getAttribute("roleId");
	String url = request.getRequestURI();
	url = url.replace("/WEB-INF/view/", "/");
	url = url.replace(".jsp", "");
	%>
	<a href="<%=contextPath%>/home" class="brand-link"> <img src="/static/img/ruby.jpg" alt="Logo"
		class="brand-image img-circle elevation-3" style="opacity: .8"> <span class="brand-text font-weight-light">Subin
			MilkTea</span>
	</a>
	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img src="/static/img/user-avatar-160x160.jpg" class="img-circle elevation-2" alt="User">
			</div>
			<div class="info">
				<a href="<%=contextPath%>/admin/home" class="d-block"><%=session.getAttribute("userName").toString()%></a>
			</div>
		</div>
		<!-- SidebarSearch Form -->
		
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
				<li class="nav-item"><a href="<%=contextPath%>/admin/home" <%if (url.equals("/admin/home")) {%>
					class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="nav-icon fas fa-tachometer-alt"></i>
						<p>Dashboard</p>
				</a></li>
				<!-- /.employee -->
				<%if(roleId==1){ %>
				<li class="nav-item"><a href="#" <%if (url.equals("/admin/listUser") || url.equals("/admin/addUser")) {%>
					class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="nav-icon fas fa-users"></i>
						<p>
							Employee management <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" <%if (url.equals("/admin/listUser") || url.equals("/admin/addUser")) {%>
						style="display: block" <%} else {%> style="display: none" <%}%>>
						<li class="nav-item"><a href="<%=contextPath%>/admin/listUser" <%if (url.equals("/admin/listUser")) {%>
							class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="far fa-circle nav-icon ml-3"></i>
								<p>List of employee</p>
						</a></li>
						<li class="nav-item"><a href="<%=contextPath%>/admin/addUser" <%if (url.equals("/admin/addUser")) {%>
							class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="far fa-circle nav-icon ml-3"></i>
								<p>Add new</p>
						</a></li>
					</ul></li>
				<%} %>
				<!-- /.product -->
				<li class="nav-item"><a href="#" <%if (url.equals("/admin/listProduct") || url.equals("/admin/addProduct")) {%>
					class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="nav-icon fas fa-barcode"></i>
						<p>
							Product Management <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" <%if (url.equals("/admin/listProduct") || url.equals("/admin/addProduct")) {%>
						style="display: block" <%} else {%> style="display: none" <%}%>>
						<li class="nav-item"><a href="<%=contextPath%>/admin/product/list"
							<%if (url.equals("/admin/listProduct")) {%> class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i
								class="far fa-circle nav-icon ml-3"></i>
								<p>List of products</p>
						</a></li>
						<li class="nav-item"><a href="<%=contextPath%>/admin/product/add" <%if (url.equals("/admin/addProduct")) {%>
							class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="far fa-circle nav-icon ml-3"></i>
								<p>Add new</p>
						</a></li>
					</ul></li>
				<!-- /.category -->
				<li class="nav-item"><a href="#"
					<%if (url.equals("/admin/listCategory") || url.equals("/admin/addCategory")) {%> class="nav-link active"
					<%} else {%> class="nav-link" <%}%>> <i class="nav-icon far fa-plus-square"></i>
						<p>
							Category Management <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" <%if (url.equals("/admin/listCategory") || url.equals("/admin/addCategory")) {%>
						style="display: block" <%} else {%> style="display: none" <%}%>>
						<li class="nav-item"><a href="<%=contextPath%>/admin/category/list"
							<%if (url.equals("/admin/listCategory")) {%> class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i
								class="far fa-circle nav-icon ml-3"></i>
								<p>List of categories</p>
						</a></li>
						<li class="nav-item"><a href="<%=contextPath%>/admin/category/add"
							<%if (url.equals("/admin/addCategory")) {%> class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i
								class="far fa-circle nav-icon ml-3"></i>
								<p>Add new</p>
						</a></li>
					</ul></li>
				<!-- /.area -->
				<li class="nav-item"><a href="#" <%if (url.equals("/admin/listArea") || url.equals("/admin/addArea")) {%>
					class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="nav-icon fas fa-th"></i>
						<p>
							Area Management <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" <%if (url.equals("/admin/listArea") || url.equals("/admin/addArea")) {%>
						style="display: block" <%} else {%> style="display: none" <%}%>>
						<li class="nav-item"><a href="<%=contextPath%>/admin/area/list" <%if (url.equals("/admin/listArea")) {%>
							class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="far fa-circle nav-icon ml-3"></i>
								<p>List of Areas</p>
						</a></li>
						<li class="nav-item"><a href="<%=contextPath%>/admin/area/add" <%if (url.equals("/admin/addArea")) {%>
							class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="far fa-circle nav-icon ml-3"></i>
								<p>Add new</p>
						</a></li>
					</ul></li>
				<!-- /.table -->
				<li class="nav-item"><a href="#" <%if (url.equals("/admin/listTable") || url.equals("/admin/addTable")) {%>
					class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="nav-icon fas fa-chair"></i>
						<p>
							Table Management <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" <%if (url.equals("/admin/listTable") || url.equals("/admin/addTable")) {%>
						style="display: block" <%} else {%> style="display: none" <%}%>>
						<li class="nav-item"><a href="<%=contextPath%>/admin/table/list" <%if (url.equals("/admin/listTable")) {%>
							class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="far fa-circle nav-icon ml-3"></i>
								<p>List of Tables</p>
						</a></li>
						<li class="nav-item"><a href="<%=contextPath%>/admin/table/add" <%if (url.equals("/admin/addTable")) {%>
							class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="far fa-circle nav-icon ml-3"></i>
								<p>Add new</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="<%=contextPath%>/admin/bill/list" <%if (url.equals("/admin/listBill")) {%>
					class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i class="nav-icon fas fa-inbox"></i>
						<p>Orders Management</p>
				</a></li>
				<li class="nav-item"><a href="<%=contextPath%>/admin/changePassword"
					<%if (url.equals("/admin/changePassword")) {%> class="nav-link active" <%} else {%> class="nav-link" <%}%>> <i
						class="nav-icon fas fa-th"></i>
						<p>Change Password</p>
				</a></li>
				
				<%if(roleId==1){ %>
				<li class="nav-item"><a href="<%=contextPath%>/admin/bill/revenue" class="nav-link"> <i class="nav-icon fas fa-tachometer-alt"></i>
						<p>Revenue statistics</p>
				</a></li>
				
				<%} %>
				<li class="nav-item"><a href="<%=contextPath%>/logoutAdmin" class="nav-link"> <i
						class="nav-icon fas fa-sign-out-alt"></i>
						<p>Logout</p>
				</a></li>
			</ul>
		</nav>
	</div>
	<!-- /.sidebar -->
</aside>
<script>
 <%--  function loadd(){
	  console.log("url","<%=url%>");
	} --%>
</script>