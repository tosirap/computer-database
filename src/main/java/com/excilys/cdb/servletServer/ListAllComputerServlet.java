package com.excilys.cdb.servletServer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Page;

@WebServlet(urlPatterns = "/dashboard")
public class ListAllComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page page = new Page();
		RequestDispatcher rd = page.createPage(request, "dashboard").getRequestDispatcher("WEB-INF/views/dashboard.jsp");
		rd.forward(request, response);
	}

}
