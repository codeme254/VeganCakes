package com.vegancakes;
import com.vegancakes.dao.OrderDao;
import com.vegancakes.connection.*;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckOutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			boolean success = true;
			Integer userId = (Integer) request.getSession().getAttribute("user_id");
			if(userId == null) {
				response.sendRedirect("index.html");
				return;
			}
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if (cart_list != null) {
				for (Cart c: cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(userId);
					order.setQuantity(c.getQuntity());
					order.setDate(formatter.format(date));
					OrderDao oDao = new OrderDao(DbCon.getConnection());
					boolean result = oDao.insertOrder(order);
					if(!result) {
						success = false;
						response.sendRedirect("order_error.jsp");
						break;
					}
				}
				if (success) {
					cart_list.clear();
					response.sendRedirect("orders.jsp");
				}
			} else {
				response.sendRedirect("cart.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
