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
    <title>Vegan Cakes - Order Success</title>
    <link rel="stylesheet" type="text/css" href="styles/styles.css">
    <style>
        /* Style for the form container */
        .form-container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f2f2f2;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        /* Style for form input fields */
        .form-input {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        /* Style for the proceed button */
        .proceed-button {
            width: 100%;
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <jsp:include page="includes/header.jsp" />
    <h2>Pay for your order.</h2>
    <div class="form-container">
        <h3>Enter Credit Card Details</h3>
        <form method="post">
            <label for="credit-card-number">Credit Card Number:</label>
            <input type="text" id="credit-card-number" name="creditCardNumber" class="form-input" required>
            <button type="submit" class="proceed-button" id="validate-btn">Proceed</button>
        </form>
        <p id="credit-card-validator"></p>
    </div>
    <script src="js/validate_card_number.js"></script>
</body>
</html>
