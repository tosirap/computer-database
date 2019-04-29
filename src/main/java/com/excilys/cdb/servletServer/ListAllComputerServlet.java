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


@WebServlet("/ListAllComputerServlet")
public class ListAllComputerServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Controlleur controlleur = Controlleur.getInstance();
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> listComputer =  controlleur.listComputer();
		System.out.println(listComputer.size()+ " adjiajiaidaiiii");
		request.setAttribute("listComputer", listComputer);
		RequestDispatcher rd = request.getRequestDispatcher("views/dashboard.jsp");
		rd.forward(request, response);
	}
}
