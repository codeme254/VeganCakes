package com.vegancakes;

import jakarta.servlet.ServletException;
import java.io.PrintWriter;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class QuantityIncDecServlet
 */
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public QuantityIncDecServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Inc dec");
	}

}
