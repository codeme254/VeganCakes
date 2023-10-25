<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" type="text/css" href="styles/styles.css">
    <style>
        .update-product-form {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
        }

        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .btn-update {
            background-color: #3498db;
            color: #fff;
            padding: 10px 20px;
            border: none;
            text-decoration: none;
            border-radius: 3px;
        }
    </style>
</head>
<body>
    <jsp:include page="includes/admin-header.jsp" />
    <div class="update-product-form">
        <h2>Update Product</h2>
        <form action="UpdateProductServlet" method="post">
            <div class="form-group">
                <label for="name">Product Name</label>
                <input type="text" id="name" name="name" value="${param.name}" required>
            </div>
            <div class="form-group">
                <label for="description">Product Description</label>
                <textarea id="description" name="description" rows="4" required>${param.description}</textarea>
            </div>
            <div class="form-group">
                <label for="price">Product Price</label>
                <input type="number" id="price" name="price" value="${param.price}" required>
            </div>
            <div class="form-group">
                <label for="category">Product Category</label>
                <input type="text" id="category" name="category" value="${param.category}" required>
            </div>
            <div class="form-group">
                <label for="quantity">Product quantity</label>
                <input type="number" id="quantity" name="quantity" value="${param.quantity}" required>
            </div>
            <input type="hidden" name="id" value="${param.id}">
            <button class="btn-update" type="submit">Update</button>
        </form>
    </div>
</body>
</html>
