package com.excilys.cdb.servletServer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurComputer;


@WebServlet(urlPatterns= "/searchComputer")
public class SearchComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ServiceComputer serviceComputer = ServiceComputer.getInstance();
	MappeurComputer mappeurComputer = MappeurComputer.getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		int offset =0;
		int PCparPageInt = 0;
		int pageInt =0;
		String page ;
		if(request.getParameter("page")==null){
			page = "1";
		}
		else {
			page = request.getParameter("page");
			
			
		}
		String PCparPage ;
		if(request.getParameter("PCparPage")==null) {
			PCparPage = "10";
		}
		else {
			PCparPage = request.getParameter("PCparPage");
			
		}
		
		
		try {
			PCparPageInt = Integer.valueOf(PCparPage);
			pageInt = Integer.valueOf(page);
			if(pageInt<=1) {
				pageInt = 1;
				page="1";
			}
			
			if(PCparPageInt<1) {
				PCparPageInt=1;
				PCparPage="1";
			}
				
			offset = (PCparPageInt * (pageInt-1));
			
		}catch(Exception  e) {
			System.out.println("ici erreure");
		}
		
		
		ArrayList<DTOComputer> aldto = mappeurComputer.computerToDTO(serviceComputer.searchComputer(search));
		int nbComputer = aldto.size();
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
		if(pageInt>=nbPageTotal) {
			pageInt=nbPageTotal-1;
			page=String.valueOf(pageInt);
		}
		
		request.setAttribute("PCparPage", PCparPage);
		request.setAttribute("page", page);
		request.setAttribute("total", nbComputer);
		request.setAttribute("nbPageTotal",nbPageTotal);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("listComputer", aldto);
		request.setAttribute("sizeList", aldto.size());
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/dashboard.jsp");
		rd.forward(request, response);
	}
}
