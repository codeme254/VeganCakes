<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.vegancakes.dao.ProductDao" %>
<%@ page import="com.vegancakes.connection.DbCon" %>
<%@ page import="com.vegancakes.Product" %>
<%@ page import="com.vegancakes.Cart" %>
<%@ page import="java.util.*" %>

<%
ProductDao pd = new ProductDao(DbCon.getConnection());
List <Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null){
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vegan cakes - explore products</title>
<link rel="stylesheet" type="text/css" href="styles/styles.css">
<style>
.products_container{
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(40rem, 1fr));
	gap: 2rem;
	justify-items: center;
	margin-bottom: 8rem;
	margin-top: 3rem;
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
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
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
	text-align: center;
	text-decoration: none;
}
.card__actions{
	display: flex;
	align-items: center;
	justify-content: space-between;
	gap: 2rem;
	padding: 1rem;
}

.card__action{
	text-decoration: none;
	display: block;
	padding: 1rem 1.5rem;
	color: #fff;
	width: 45%;
	text-transform: capitalize;
	text-align: center;
	border-radius: 3px;
}

.card__action--edit{
	background-color: #007bff;
}

.card__action--delete{
	background-color: red;
}
</style>
</head>
<body>
<jsp:include page="includes/admin-header.jsp" />
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
				<!-- <a href="AddToCartServlet?id=<%=p.getId() %>" class="add-to-cart-button">Add to cart</a> -->
				<div class="card__actions">
					<a href="update_product.jsp?&id=<%=p.getId() %>&name=<%=p.getCake_name() %>&description=<%=p.getCake_description() %>&price=<%=p.getCake_price() %>&category=<%=p.getCake_category() %>&quantity=<%=p.getCake_quantity() %>" class="card__action card__action--edit">Edit info</a>
					<a href="DeleteProductServlet?id=<%=p.getId() %>" class="card__action card__action--delete">delete product</a>
				</div>
			</div>
		<%}
	} else {
		out.println("No products");
	}
%>
</div>
</body>
</html>