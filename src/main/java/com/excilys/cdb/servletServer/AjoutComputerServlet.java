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

@WebServlet(urlPatterns= "/addComputer")
public class AjoutComputerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ServiceComputer serviceComputer = ServiceComputer.getInstance();
	ServiceCompany serviceCompany  = ServiceCompany .getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<DTOCompany> alCompany = serviceCompany.listAllElements();
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
		if(checkDate(introduced,discontinued)) {
			DTOComputer dtoComputer = new DTOComputer(name, introduced, discontinued, companyId);
			boolean b = serviceComputer.createWithCompanyName(dtoComputer);
			if(b) {
				request.setAttribute("reussite", "Insertion effectuée !");
			}
			else {
				request.setAttribute("echec", "Insertion ratée !");
			}
		}
		else {
			request.setAttribute("echec", "Erreur dans la date");
		}
		
		ArrayList<DTOCompany> alCompany = serviceCompany.listAllElements();
		request.setAttribute("listCompany", alCompany);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/addComputer.jsp");
		rd.forward(request, response);
	}
	
	/*
	 * fonction auxiliaire qui sert à vérifier si les 2 dates sont correctes et sont
	 * dans le bon ordre chronologique
	 */
	public boolean checkDate(String str1, String str2) {
		System.out.println();
		try {
			if (str1 != null && !str1.equals("") && str2 != null && !str2.equals("")) {
				Date d1 = Date.valueOf(str1);
				Date d2 = Date.valueOf(str2);
				if (d1.compareTo(d2) > 0) {
					System.out.println("La date de mise en service doit etre antérieur a la date de retrait");
					return false;
				}
			} else if (str1 != null && !str1.equals("")) {
				Date.valueOf(str1);
			} else if (str2 != null && !str2.equals("")) {
				Date.valueOf(str2);
			}

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage()+ "Erreur dans la date avec: "+ str1 + ", "+ str2 );
			return false;
		}
	}
}
