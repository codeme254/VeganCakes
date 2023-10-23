<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>vegan cakes - add new product as admin</title>
<link rel="stylesheet" type="text/css" href="./styles/styles.css" />
</head>
<style>
        /* Add your custom styles here */
        .form-container {
            max-width: 90rem;
            margin: 5rem auto;
            padding: 20px;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
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
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .submit-button,
        .cancel-button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        .submit-button {
            background-color: #007BFF;
            color: #fff;
        }

        .cancel-button {
            background-color: #ccc;
            color: #333;
        }
    </style>
<body>
	<jsp:include page="includes/admin-header.jsp" />
	<div class="form-container">
        <h2>Add New Cake</h2>
        <form action="NewProductServlet" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="cakeImage">Cake Image:</label>
                <input type="file" id="cakeImage" name="cakeImage" accept="image/*" required>
            </div>

            <div class="form-group">
                <label for="cakeName">Cake Name:</label>
                <input type="text" id="cakeName" name="cakeName">
            </div>

            <div class="form-group">
                <label for="cakeDescription">Cake Description:</label>
                <textarea id="cakeDescription" name="cakeDescription" rows="4" ></textarea>
            </div>

            <div class="form-group">
                <label for="cakeCategory">Category:</label>
                <input type="text" id="cakeCategory" name="cakeCategory" >
            </div>

            <div class="form-group">
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" >
            </div>

            <div class="form-group">
                <label for="unitPrice">Unit Price:</label>
                <input type="number" id="unitPrice" name="unitPrice" >
            </div>

            <div class="button-container">
                <input type="submit" value="Submit" class="submit-button">
                <input type="button" value="Cancel" class="cancel-button">
            </div>
        </form>
    </div>
</body>
</html>