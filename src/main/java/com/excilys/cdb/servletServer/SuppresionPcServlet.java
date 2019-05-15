package com.excilys.cdb.servletServer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.service.ServiceComputer;

@WebServlet(urlPatterns= "/delete")
public class SuppresionPcServlet extends HttpServlet {

	//static Logger logger = LoggerFactory.getLogger(SuppresionPcServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ServiceComputer serviceComputer = ServiceComputer.getInstance();
	static Logger logger  = LoggerFactory.getLogger(SuppresionPcServlet.class);

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectionSuppression = "";
		String[] tabSelection;
		if(request.getParameter("selection")!=null && !request.getParameter("selection").equals("")) {
			selectionSuppression = request.getParameter("selection");
			tabSelection = selectionSuppression.split(",");
			for(int i =0; i < tabSelection.length; i++) {
				serviceComputer.delete(tabSelection[i]);
			}
		}
		try {
			response.sendRedirect("dashboard");
		}catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
}
