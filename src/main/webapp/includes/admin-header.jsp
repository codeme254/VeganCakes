<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${empty sessionScope.username}">
        <c:redirect url="admin_login.html" />
    </c:when>
    <c:otherwise>
        <header class="admin-header">
            <div class="admin-header-left">
                <h1 class="admin-title">Vegan Cakes Admin</h1>
            </div>
            <div class="admin-header-right">
                <nav class="admin-nav">
                    <ul class="admin-nav-list">
                        <li class="admin-nav-item"><a href="admin_all_products.jsp" class="admin-nav-link">All Products</a></li>
                        <li class="admin-nav-item"><a href="new_product.jsp" class="admin-nav-link">New Product</a></li>
                        <li class="admin-nav-item"><a href="admin_all_orders_view.jsp" class="admin-nav-link">All Orders</a></li>
                    </ul>
                </nav>
                <p class="user-greeting admin-user-greeting">Hey, ${sessionScope.username}</p>
            </div>
        </header>
    </c:otherwise>
</c:choose>
