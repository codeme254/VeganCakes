package com.vegancakes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.vegancakes.OrderData;

public class AllOrdersDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/vcakes_v1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    public List<OrderData> getAllOrders() {
        List<OrderData> orderList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(getAllOrdersQuery());
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                OrderData orderData = new OrderData();
                orderData.setFirstName(resultSet.getString("first_name"));
                orderData.setLastName(resultSet.getString("last_name"));
                orderData.setLocation(resultSet.getString("location"));
                orderData.setContact(resultSet.getString("contact"));
                orderData.setCakeName(resultSet.getString("cake_name"));
                orderData.setCakePrice(resultSet.getDouble("cake_price"));
                orderData.setQuantity(resultSet.getInt("quantity"));
                orderData.setTotal(resultSet.getDouble("total"));
                orderList.add(orderData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception or throw a custom exception if needed
        }

        return orderList;
    }

    private String getAllOrdersQuery() {
        return "SELECT " +
                "u.first_name, u.last_name, u.location, u.contact, " +
                "p.cake_name, p.cake_price, o.o_quantity AS quantity, " +
                "(p.cake_price * o.o_quantity) AS total " +
                "FROM Users AS u " +
                "INNER JOIN orders AS o ON u.user_id = o.u_id " +
                "INNER JOIN Products AS p ON o.p_id = p.id";
    }
}
