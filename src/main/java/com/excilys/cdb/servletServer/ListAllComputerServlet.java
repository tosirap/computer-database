package com.excilys.cdb.servletServer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurComputer;
import com.excilys.cdb.validator.ValidatorPage;

@WebServlet(urlPatterns = "/dashboard")
public class ListAllComputerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = LoggerFactory.getLogger(ListAllComputerServlet.class);
	private final ValidatorPage validatorPage = new ValidatorPage();
	private ServiceComputer serviceComputer;
	private MappeurComputer mappeurComputer;
	private Page page;

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		serviceComputer = wac.getBean(ServiceComputer.class);
		mappeurComputer = wac.getBean(MappeurComputer.class);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!validatorPage.testSiCorrect(request)) {
			try {
				response.sendError(403);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		String pageNum = request.getParameter("page");
		String pCparPage = request.getParameter("PCparPage");
		String orderby = request.getParameter("orderby");
		String asc = request.getParameter("asc");
		page = new Page(pageNum, pCparPage, orderby, asc);

		String search = request.getParameter("search");
		ArrayList<DTOComputer> dtoComputers = null;
		int nbComputerTotal = 0;
		if (search == null || search.isEmpty()) {
			nbComputerTotal = serviceComputer.count();
			dtoComputers = mappeurComputer.computerToDTO(serviceComputer.listPagination(page.getPCparPageInt(),
					page.getOffset(), page.getOrderBy(), page.isAscendant()));
		} else {
			nbComputerTotal = serviceComputer.searchComputerCount(search);
			dtoComputers = mappeurComputer.computerToDTO(serviceComputer.searchComputer(search, page.getPCparPageInt(),
					page.getOffset(), page.getOrderBy(), page.isAscendant()));
			request.setAttribute("search", search);

		}
		page.createPage(nbComputerTotal);

		if (orderby!= null && !orderby.isEmpty()) {
			request.setAttribute("orderby", page.getOrderBy().toString());
		}
		if(!page.isAscendant()) {
			request.setAttribute("asc", page.isAscendant());
		}
		request.setAttribute("PCparPage", page.getPCparPageInt());
		request.setAttribute("page", page.getPageInt());
		request.setAttribute("listComputer", dtoComputers);
		request.setAttribute("sizeList", dtoComputers.size());
		request.setAttribute("total", nbComputerTotal);
		request.setAttribute("nbPageTotal", page.getNbPageTotal());
		request.setAttribute("begin", page.getBegin());
		request.setAttribute("end", page.getEnd());

		try {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/dashboard.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

}
