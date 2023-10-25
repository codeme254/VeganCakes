package com.vegancakes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.vegancakes.dao.AllOrdersDao;

public class AllOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AllOrdersServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AllOrdersDao ordersDao = new AllOrdersDao();
        List<OrderData> orderList = ordersDao.getAllOrders();
        System.out.println(orderList);
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("admin_all_orders_view.jsp").forward(request, response);
    }


}
