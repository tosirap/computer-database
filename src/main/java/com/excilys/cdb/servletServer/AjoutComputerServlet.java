package com.excilys.cdb.servletServer;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.DTOCompany;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurCompany;
import com.excilys.cdb.transfert.MappeurComputer;
import com.excilys.cdb.validator.ValidatorComputerUIweb;

@WebServlet(urlPatterns= "/addComputer")
public class AjoutComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ServiceComputer serviceComputer = ServiceComputer.getInstance();
	ServiceCompany serviceCompany  = ServiceCompany .getInstance();
	MappeurComputer mappeurComputer = MappeurComputer.getInstance();
	MappeurCompany mappeurCompany = MappeurCompany.getInstance();
	ValidatorComputerUIweb validatorComputerUIweb = new ValidatorComputerUIweb();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<DTOCompany> alCompany = mappeurCompany.companyToDTO(serviceCompany.listAllElements());
		request.setAttribute("listCompany", alCompany);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/addComputer.jsp");
		rd.forward(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = ""  ;
		if(request.getParameter("computerName")!=null){
			 name = request.getParameter("computerName");
		}
		
		String introduced = "";
		if(request.getParameter("introduced")!=null && request.getParameter("introduced")!= ""){
			introduced = String.valueOf(request.getParameter("introduced"));
		}
		
		String discontinued = "";
		if(request.getParameter("discontinued")!=null && request.getParameter("discontinued")!= ""){
			discontinued = String.valueOf(request.getParameter("discontinued"));
		}
		
		String companyId = "";
		if(request.getParameter("companyId")!=null){
			companyId = request.getParameter("companyId");
		}
		//ici validation
		DTOComputer dtoComputer = new DTOComputer(name, introduced, discontinued, companyId);
		if(validatorComputerUIweb.testSiCorrect(dtoComputer)) {
			serviceComputer.create(mappeurComputer.DTOToComputer(dtoComputer));
			request.setAttribute("reussite", "Insertion effectuée !");
		}
		else {
			request.setAttribute("echec", "Insertion ratée !");
		}
		ArrayList<DTOCompany> alCompany = mappeurCompany.companyToDTO(serviceCompany.listAllElements());
		request.setAttribute("listCompany", alCompany);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/addComputer.jsp");
		rd.forward(request, response);
	}
	
	/*
	 * fonction auxiliaire qui sert à vérifier si les 2 dates sont correctes et sont
	 * dans le bon ordre chronologique
	 */

}
