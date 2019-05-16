package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurComputer;

public class Page {
	private ServiceComputer serviceComputer = ServiceComputer.getInstance();
	private MappeurComputer mappeurComputer = MappeurComputer.getInstance();


	private List<DTOComputer> listDTOComputer = new ArrayList<DTOComputer>();
	private int offset;
	private int PCparPageInt;
	private int pageInt;
	private int nbPageTotal;
	private int begin;
	private int end;
	private OrderBy orderBy = OrderBy.COMPUTER_ID;
	private boolean ascendant = false; //ASC /DESC de la bdd


	public HttpServletRequest createPage(HttpServletRequest request) {
		String pageNum;
		if (request.getParameter("page") == null) {
			pageNum = "1";
		} else {
			pageNum = request.getParameter("page");

		}
		String PCparPage;
		if (request.getParameter("PCparPage") == null) {
			PCparPage = "10";
		} else {
			PCparPage = request.getParameter("PCparPage");
		}

		try {
			PCparPageInt = Integer.valueOf(PCparPage);
			pageInt = Integer.valueOf(pageNum);
			if (pageInt < 1) {
				pageInt = 1;
				pageNum = "1";
			}

			if (PCparPageInt < 1) {
				PCparPageInt = 1;
				PCparPage = "1";
			}
			offset = (PCparPageInt * (pageInt - 1));

		} catch (Exception e) {
			System.out.println("ici erreure");
		}
		
		String orderByStr = "";
		if (request.getParameter("orderby") == null || request.getParameter("orderby").isEmpty()) {
			orderByStr = "computer.id";
		} else {
			orderByStr = request.getParameter("orderby");
		}
		
		for(OrderBy ob : OrderBy.values()) {
			if(ob.toString().equals(orderByStr)) {
				orderBy = ob;
			}
		}
		
		if (request.getParameter("asc") != null && request.getParameter("asc").equals("false")) {
			ascendant = false;
		} else {
			ascendant = true;
		}
		int nbComputer = 0;
		String search = request.getParameter("search");
		if (search == null || search.isEmpty() || search.equals("dashboard")) {
			listDTOComputer = mappeurComputer.computerToDTO(serviceComputer.listPagination(PCparPageInt, offset, orderBy, ascendant));
			nbComputer = serviceComputer.count();
		} else  {
			listDTOComputer = mappeurComputer.computerToDTO(
					serviceComputer.searchComputer(search, PCparPageInt, offset, orderBy, ascendant));
			request.setAttribute("search", search);
			nbComputer = serviceComputer.searchComputerCount(search);
		}

		nbPageTotal = nbComputer / PCparPageInt;
		if (nbComputer % PCparPageInt != 0) {
			nbPageTotal += 1;
		}
		if (nbPageTotal <= 5) {
			begin = 1;
			end = nbPageTotal;
		} else {
			if (pageInt <= 3) {
				begin = 1;
				end = 5;
			}

			else if (pageInt >= nbPageTotal - 3) {
				begin = nbPageTotal - 6;
				end = nbPageTotal;
			} else {
				begin = pageInt - 3;
				end = pageInt + 3;
			}

		}
		if (pageInt > nbPageTotal) {
			pageInt = nbPageTotal-1;
			pageNum = String.valueOf(pageInt);
		}
		request.setAttribute("orderby", orderByStr);
		request.setAttribute("asc", ascendant);
		request.setAttribute("PCparPage", PCparPage);
		request.setAttribute("page", pageNum);
		request.setAttribute("listComputer", listDTOComputer);
		request.setAttribute("sizeList", listDTOComputer.size());
		request.setAttribute("total", nbComputer);
		request.setAttribute("nbPageTotal", nbPageTotal);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		return request;
	}

}
