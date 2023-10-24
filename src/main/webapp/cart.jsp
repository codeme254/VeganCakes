<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vegancakes.Cart" %>
<%@ page import="com.vegancakes.dao.ProductDao" %>
<%@ page import="com.vegancakes.connection.DbCon" %>

<%
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null){
	ProductDao pDao = new ProductDao(DbCon.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	request.setAttribute("cart_list", cart_list);
}

%>
<!DOCTYPE html>
<html>
<head>
    <title>Vegan cakes - Your Cart</title>
    <link rel="stylesheet" type="text/css" href="styles/styles.css">
    <style>
        .cart__header{
        	display: flex;
        	align-items: center;
        	margin-bottom: 3rem;
        }
        .cart-container {
            max-width: 100rem;
            margin: 0 auto;
            padding: 20px;
        }
        .total-price {
            font-size: 20px;
            margin-right: 20px;
        }

        .checkout-button {
            background-color: #3498db;
            color: #fff;
            padding: 10px 20px;
            border: none;
            text-decoration: none;
            border-radius: 3px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }
        
        .quantity-input {
            width: 80px;
            text-align: center;
            padding: 1rem;
        }

        .quantity-button {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 1rem;
            text-align: center;
            cursor: pointer;
        }

        .remove-button {
            background-color: crimson;
            color: #fff;
            padding:1rem 1.5rem;
            text-decoration: none;
            border-radius: 8px;
        }
    </style>
</head>
<body>
	<jsp:include page="includes/header.jsp" />
    <div class="cart-container">
        <div class="cart__header">
        	<div class="total-price">
            	Total price $400
        	</div>
        	<a class="checkout-button" href="#">Checkout</a>
        </div>

        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Cancel</th>
                </tr>
            </thead>
            <tbody>
            <% if(cart_list != null){
            	for (Cart c: cartProduct){%>
            		<tr>
                    <td><%=c.getCake_name() %></td>
                    <td><%=c.getCake_category() %></td>
                    <td><%=c.getCake_price() %></td>
                    <td>
                        <button class="quantity-button">-</button>
                        <input class="quantity-input" type="number" value="1" min="0">
                        <button class="quantity-button">+</button>
                    </td>
                    <td>
                        <a class="remove-button" href="#">Remove</a>
                    </td>
                </tr>
            	<%}
            } %>
            </tbody>
        </table>
    </div>
</body>
</html>
