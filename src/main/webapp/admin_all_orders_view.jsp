<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.vegancakes.OrderData" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vegan Cakes - View All Orders</title>
    <link rel="stylesheet" type="text/css" href="styles/styles.css">
    <style>
        .orders__title {
            text-align: center;
            margin-top: 2rem;
        }

        table {
            border-collapse: collapse;
            width: 90%;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <jsp:include page="includes/admin-header.jsp" />
    <h2 class="orders__title">All Orders</h2>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Contact</th>
            <th>Location</th>
            <th>Product Name</th>
            <th>Product Quantity</th>
            <th>Unit Price</th>
            <th>Total</th>
        </tr>
        <c:forEach items="${orderList}" var="order">
            <tr>
                <td>${order.firstName}</td>
                <td>${order.lastName}</td>
                <td>${order.contact}</td>
                <td>${order.location}</td>
                <td>${order.cakeName}</td>
                <td>${order.quantity}</td>
                <td>${order.cakePrice}</td>
                <td>${order.total}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
