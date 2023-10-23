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
<style>
.explore-home-section{
	min-height: 60vh;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	background: linear-gradient(rgba(0, 0, 0, .7), rgba(0, 0, 0, .6)), url(images/bg.jpg);
	background-position: center;
	background-size: cover;
	background-repeat: no-repeat;
	margin-bottom: 8rem;
}

.explore-home-section h2{
	color: #fff;
	font-size: 4rem;
	text-transform: capitalize;
	margin-bottom: 1rem;
}

.explore-home-section a, .explore-home-section a:visited{
	text-decoration: none;
	display: inline-block;
	padding: 1.4rem;
	color: #fff;
	border-radius: 100px;
	background-color: #007bff;
	margin-top: 3rem;
}

.explore-home-section h5{
	color: #fff;
	font-size: 3rem;
}
.products_container{
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(40rem, 1fr));
	gap: 2rem;
	justify-items: center;
	margin-bottom: 8rem;
}

.product_box{
	width: 40rem;
	background-color: #f2f2f2;
	border-radius: 8px;
	overflow: hidden;
	height: max-content;
}

.product_box__image{
	height: 25rem;
	position: relative;
}

.product_box__image--img{
	width: 100%;
	height: 100%;
	display: block;
	object-fit: cover;
}

.product_box--price{
	position: absolute;
	bottom: 0;
	left: 0;
	padding: 1.8rem;
	background-color: #007bff;
	color: #fff;
	font-size: 2rem;
	border: 3px solid #fff;
}

.product_box__body{
	padding: 1rem;
}

.product_box--title{
	text-align: center;
}

.product_box--category{
	--border: 1px solid #999;
	border-top: var(--border);
	border-bottom: var(--border);
	margin: 1rem 0;
	padding: 1rem;
}

.add-to-cart-button{
	display: block;
	padding: 1.5rem 2rem;
	background-color: #007bff;
	color: #fff;
	border: none;
	outline: none;
	width: 98%;
	border-radius: 5px;
	margin: .5rem auto;
	cursor: pointer;
}
</style>
</head>
<body>
<jsp:include page="includes/header.jsp" />
<section class="explore-home-section">
	<h2>Vegan Cakes - baking goodness, baking vegan</h2>
	<h5>Test the future, one cake at a time</h5>
	<a href="#explore">Explore some delicacies</a>
</section>
<div class="products_container" id="explore">
<%
	if(!products.isEmpty()){
		for (Product p:products){%>
			<div class="product_box">
				<div class="product_box__image">
					<img class="product_box__image--img" src="images/<%=p.getCake_image()%>" alt="cake image" />
					<h4 class="product_box--price">$<%=p.getCake_price() %></h4>
				</div>
				<div class="product_box__body">
					<h3 class="product_box--title"><%= p.getCake_name() %></h3>
					<p><%= p.getCake_description() %></p>
					<p class="product_box--category">Category: <%= p.getCake_category() %></p>
					<p><%=p.getCake_quantity() %> cakes left</p>
				</div>
				<button class="add-to-cart-button">Add to cart</button>
			</div>
		<%}
	} else {
		out.println("No products");
	}
%>
</div>
</body>
</html>