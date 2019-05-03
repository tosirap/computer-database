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
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.DTOCompany;

@WebServlet(urlPatterns= "/editComputer")
public class EditComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ServiceComputer serviceComputer = ServiceComputer.getInstance();
	ServiceCompany serviceCompany  = ServiceCompany .getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String name = ""  ;
		if(request.getParameter("name")!=null){
			 name = request.getParameter("name");
		}
		
		String introduced = "";
		if(request.getParameter("intro")!=null && request.getParameter("intro")!= ""){
			introduced = String.valueOf(request.getParameter("intro"));
		}
		
		String discontinued = "";
		if(request.getParameter("discon")!=null && request.getParameter("discon")!= ""){
			discontinued = String.valueOf(request.getParameter("discon"));
		}
		
		String companyName = "";
		if(request.getParameter("company")!=null){
			companyName = request.getParameter("company");
		}*/
		
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("name",request.getParameter("name"));
		request.setAttribute("intro",request.getParameter("intro"));
		request.setAttribute("discon",request.getParameter("discon"));
		request.setAttribute("company",request.getParameter("company"));
		
		ArrayList<DTOCompany> alCompany = serviceCompany.listAllElements();
	
		request.setAttribute("listCompany", alCompany);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/editComputer.jsp");
		rd.forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/editComputer.jsp");
		rd.forward(request, response);
	}
}
