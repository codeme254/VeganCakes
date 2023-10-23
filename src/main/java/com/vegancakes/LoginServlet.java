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

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/vcakes_v1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
        	Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                request.getSession().setAttribute("user", resultSet.getString("email"));
                request.getSession().setAttribute("firstName", resultSet.getString("first_name"));
                request.getSession().setAttribute("lastName", resultSet.getString("last_name"));
                request.getSession().setAttribute("contact", resultSet.getString("contact"));
                request.getSession().setAttribute("location", resultSet.getString("location"));
                response.sendRedirect("listing.jsp");
            } else {
                request.setAttribute("loginError", "Login was not successful. Please check your credentials.");
                request.getRequestDispatcher("loginerror.html").forward(request, response);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("loginError", "An error occurred. Please try again later.");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }
}
