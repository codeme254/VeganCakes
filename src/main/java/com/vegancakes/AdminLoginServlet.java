package com.vegancakes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the username and password from the form
        String enteredUsername = request.getParameter("username");
        String enteredPassword = request.getParameter("password");

        // Initialize the database connection parameters
        String url = "jdbc:mysql://localhost:3306/vcakes_v1";
        String dbUser = "root";
        String dbPassword = "root";

        try {
        	Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
            String query = "SELECT username, password FROM Admins WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, enteredUsername);
            statement.setString(2, enteredPassword);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	request.getSession().setAttribute("username", enteredUsername);
                response.sendRedirect("new_product.jsp");
            } else {
                request.setAttribute("loginError", "Invalid username or password. Please try again.");
                request.getRequestDispatcher("admin_login.jsp").forward(request, response);
            }            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
