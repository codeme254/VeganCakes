<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.vegancakes.dao.ProductDao" %>
<%@ page import="com.vegancakes.connection.DbCon" %>
<%@ page import="com.vegancakes.Product" %>
<%@ page import="java.util.List" %>

<%
ProductDao pd = new ProductDao(DbCon.getConnection());
List <Product> products = pd.getAllProducts();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vegan cakes - explore products</title>
<link rel="stylesheet" type="text/css" href="styles/styles.css">
</head>
<body>
<jsp:include page="includes/header.jsp" />
<h2>explore products</h2>
<%
	if(!products.isEmpty()){
		for (Product p:products){%>
			<div class="product_box">
				<div class="product_box__image">
					<img class="product_box__image--img" src="images/<%=p.getCake_image()%>" alt="cake image" />
					<h4>$<%=p.getCake_price() %></h4>
				</div>
				<div class="product_box__body">
					<h3><%= p.getCake_name() %></h3>
					<p><%= p.getCake_description() %></p>
					<p>Category: <%= p.getCake_category() %></p>
					<p><%=p.getCake_quantity() %> cakes left</p>
					<button>Add to cart</button>
				</div>
			</div>
		<%}
	} else {
		out.println("No products");
	}
%>
</body>
</html>