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

import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.DTOComputer;


@WebServlet(urlPatterns= "/dashboard")
public class ListAllComputerServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
		ServiceComputer serviceComputer = ServiceComputer.getInstance();
	
	
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
		String PCparPage ;
		if(request.getParameter("PCparPage")==null) {
			request.setAttribute("PCparPage", 10);
			PCparPage = "10";
		}
		else {
			PCparPage = request.getParameter("PCparPage");
			request.setAttribute("PCparPage", PCparPage);
		}
		int offset =0;
		int PCparPageInt = 0;
		int pageInt =0;
		try {
			PCparPageInt = Integer.valueOf(PCparPage);
			pageInt = Integer.valueOf(page);
			offset = (PCparPageInt * (pageInt-1));
			
		}catch(Exception  e) {
			System.out.println("ici erreure");
		}
		
		ArrayList<DTOComputer> listComputer =  serviceComputer.listPagination(PCparPageInt,offset);
		int nbComputer = serviceComputer.count();
		int nbPageTotal = nbComputer/PCparPageInt;
		if(nbComputer%PCparPageInt!= 0) {
			nbPageTotal+=1;
		}
		int begin = 0, end = 0;
		if(pageInt <= 3 ) {
			begin =1;
			end = 5;
		}
		
		else if(pageInt>= nbPageTotal- 3) {
			begin = nbPageTotal-6;
			end = nbPageTotal;
		}
		else {
			begin = pageInt-3;
			end = pageInt+3;
		}
		request.setAttribute("listComputer", listComputer);
		request.setAttribute("sizeList", listComputer.size());
		request.setAttribute("total", nbComputer);
		request.setAttribute("nbPageTotal",nbPageTotal);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/dashboard.jsp");
		rd.forward(request, response);
	}
}
