<%@page import="com.thi.demo.dto.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form Product</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<c:if test = "${product != null}">
				<h2>Update Product</h2>
			</c:if>
			<c:if test = "${product == null}">
				<h2>Add New Product</h2>
			</c:if>
		</div>
		<div class="row mb-3">
			<a class="btn btn-primary" href="product">Back to List</a>
		</div>
		<div class="formAction">
			
			<%
				String action = "insert";
			%>
			<c:if test = "${product != null}">
				<% action = "update"; %>
			</c:if>
			
			<form action="product?action=<%=action%>" method="post">
			
			<c:if test="${product != null }">
				<input type="hidden" name="id" id="id" value="<c:out value="${product.id}" />">
			</c:if>
			
			<div class="form-group">
				<label for="name">Name</label>
				<input class="form-control" type="text" name="name" id="name" value="<c:out value="${product.name}" />"
					placeholder="Enter product name" required>
			</div>
			
			<div class="form-group">
				<label for="name">Description</label>
				<input class="form-control" type="text" name="description" id="description" value="<c:out value="${product.description}" />"
					placeholder="Enter product description" required>
			</div>
			
			<div class="form-group">
				<label for="name">Price</label>
				<input class="form-control" type="text" name="price" id="price" value="<c:out value="${product.price}" />"
					placeholder="Enter product price" required>
			</div>
			
			<div class="row mb-3">
				<button class="btn btn-secondary" id="submit" type="submit">Submit</button>
			</div>
			
			</form>
		</div>
	</div>
	
</body>
</html>