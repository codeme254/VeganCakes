package com.vegancakes;

import jakarta.servlet.ServletException;
import java.io.PrintWriter;
import java.util.ArrayList;

//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			ArrayList<Cart> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));
			Cart cm = new Cart();
			cm.setId(id);
			cm.setQuntity(1);
			
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			if (cart_list == null) {
				cart_list = new ArrayList<>();
			    session.setAttribute("cart-list", cart_list);
				response.sendRedirect("explore.jsp");
			} else {
				//out.println("cart exists");
				cartList = cart_list;
				boolean exists = false;
				
				for (Cart c: cart_list) {
					if (c.getId() == id) {
						exists = true;
						out.println(""
								+ "<h3 style='text-align: center;'>Item already exists in your Cart</h3>"
								+ "<p style='text-align: center;'>Visit <a href='cart.jsp'>Your cart here</a> to view all items in your cart");
					}
				}
				if (!exists) {
					cartList.add(cm);
					response.sendRedirect("explore.jsp");
				}
			}
			for (Cart c: cart_list) {
//				out.println(c.getId());
			}
		}
	}

}
