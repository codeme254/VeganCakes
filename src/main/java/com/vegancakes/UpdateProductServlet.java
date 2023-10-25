package com.vegancakes;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.PrintWriter;

public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/vcakes_v1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    public UpdateProductServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String category = request.getParameter("category");

        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "UPDATE Products SET cake_name = ?, cake_description = ?, cake_price = ?, cake_category = ?, cake_quantity = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, price);
            preparedStatement.setString(4, category);
            preparedStatement.setInt(5, quantity);
            preparedStatement.setInt(6, productId);
            out.println("before update");
            int rowsUpdated = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (rowsUpdated > 0) {
                response.sendRedirect("admin_all_products.jsp");
            } else {
            	out.println("There was an error doing the update");
            }
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.print("there was en error updating, please try again");
        }
    }
}
