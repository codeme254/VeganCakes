package com.vegancakes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/vcakes_v1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    public DeleteProductServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            String sql = "DELETE FROM Products WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            if (rowsDeleted > 0) {
                response.sendRedirect("admin_all_products.jsp");
            } else {
                response.getWriter().println("Product with ID " + productId + " not found or not deleted.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
