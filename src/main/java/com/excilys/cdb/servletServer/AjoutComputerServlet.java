package com.excilys.cdb.servletServer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.controlleur.Controlleur;

@WebServlet(urlPatterns= "/addComputer")
public class AjoutComputerServlet  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Controlleur controlleur = Controlleur.getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> alCompany = controlleur.listCompany();
		request.setAttribute("listCompany", alCompany);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/addComputer.jsp");
		rd.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name  ;
		if(request.getParameter("computerName")==null){
			 name = "";
		}
		name = request.getParameter("computerName");
		
		String introduced;
		if(request.getParameter("introduced")==null){
			introduced = "";
		}
		introduced = request.getParameter("introduced");
		
		String discontinued;
		if(request.getParameter("discontinued")==null){
			discontinued = "";
		}
		discontinued = request.getParameter("discontinued");
		
		String companyId;
		if(request.getParameter("companyId")==null){
			companyId = "";
		}
		companyId = request.getParameter("companyId");
		
		boolean b = controlleur.createComputer(name, introduced, discontinued, companyId, null);
		if(b) {
			request.setAttribute("reussite", "Insertion effectuée !");
		}
		else {
			request.setAttribute("reussite", "Insertion ratée !");
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/addComputer.jsp");
		rd.forward(request, response);
	}
}