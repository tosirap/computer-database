package com.excilys.cdb.servletServer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Page;


@WebServlet(urlPatterns= "/searchComputer")
public class SearchComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger logger  = LoggerFactory.getLogger(SearchComputerServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page page = new Page();
		
		try {
		RequestDispatcher rd = page.createPage(request, "searchComputer").getRequestDispatcher("WEB-INF/views/dashboard.jsp");
		rd.forward(request, response);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
}
