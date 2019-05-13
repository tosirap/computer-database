package com.excilys.cdb.servletServer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.service.ServiceComputer;

@WebServlet(urlPatterns= "/delete")
public class SuppresionPcServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ServiceComputer serviceComputer = ServiceComputer.getInstance();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectionSuppression = "";
		String[] tabSelection;
		if(request.getParameter("selection")!=null && request.getParameter("selection")!="") {
			selectionSuppression = request.getParameter("selection");
			tabSelection = selectionSuppression.split(",");
			for(int i =0; i < tabSelection.length; i++) {
				serviceComputer.delete(tabSelection[i]);
			}
		}
		response.sendRedirect("dashboard");
	}
}
