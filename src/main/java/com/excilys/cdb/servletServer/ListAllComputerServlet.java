package com.excilys.cdb.servletServer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.controlleur.Controlleur;


@WebServlet(urlPatterns= "/dashboard")
public class ListAllComputerServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Controlleur controlleur = Controlleur.getInstance();
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page ;
		if(request.getParameter("page")==null){
			request.setAttribute("page", 1);
			page = "1";
		}
		else {
			page = request.getParameter("page");
			request.setAttribute("page", page);
			
		}
		
		String limit ;
		if(request.getParameter("PCparPage")==null) {
			request.setAttribute("PCparPage", 10);
			limit = "10";
		}
		else {
			limit = request.getParameter("PCparPage");
			request.setAttribute("PCparPage", limit);
		}
		int offset = Integer.valueOf(limit) * Integer.valueOf(page);
		
		
		ArrayList<String> listComputer =  controlleur.listComputerPagination(limit,String.valueOf(offset));
		int nbComputer = controlleur.countComputer();
		int nbPageTotal = nbComputer/Integer.valueOf(limit);
		if(nbComputer%Integer.valueOf(limit)!= 0) {
			nbPageTotal+=1;
		}
		request.setAttribute("listComputer", listComputer);
		request.setAttribute("sizeList", listComputer.size());
		request.setAttribute("total", nbComputer);
		request.setAttribute("nbPageTotal",nbPageTotal);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/dashboard.jsp");
		rd.forward(request, response);
	}
}
