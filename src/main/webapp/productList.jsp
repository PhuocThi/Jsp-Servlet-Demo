<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Products</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="title">
			<h1>Product Management</h1>
		</div>
		<div class="add-btn">
			<a class="btn btn-primary mb-3" href="product?action=new">
				Add new Product
			</a>
		</div>
		<div>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${listProducts}">
						<tr>
							<td><c:out value="${product.id}"/></td>
							<td><c:out value="${product.name}"/></td>
							<td><c:out value="${product.description}"/></td>
							<td><c:out value="${product.price}"/></td>
							<td>
								<a href="product?action=edit&id=<c:out value="${product.id}"/>"
									class="btn btn-info">Edit</a>
								<a href="product?action=delete&id=<c:out value="${product.id}"/>"
									class="btn btn-danger">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>