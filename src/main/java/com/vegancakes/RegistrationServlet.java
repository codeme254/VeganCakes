package com.vegancakes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/vcakes_v1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    public RegistrationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String location = request.getParameter("location");
        String contact = request.getParameter("contact");
        String password = request.getParameter("password");

        try {
        	Class.forName("com.mysql.jdbc.Driver");
            // Database connection
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Inserting into users table
            String sql = "INSERT INTO Users (first_name, last_name, email, location, contact, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, contact);
            preparedStatement.setString(6, password);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Registration was successful
                response.sendRedirect("index.html");
            } else {
                // Registration failed
                response.sendRedirect("SignUpError.html");
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("SignUpError.html");
        }
    }
}
