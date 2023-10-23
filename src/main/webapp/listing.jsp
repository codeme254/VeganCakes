<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Listing Page</title>
</head>
<body>
    <h1>Welcome to the Listing Page</h1>

    <div id="user-info">
        <!-- User information will be displayed here -->
        Logged in as: ${sessionScope.user}<br>
        First Name: ${sessionScope.firstName}<br>
        Last Name: ${sessionScope.lastName}
    </div>
</body>
</html>