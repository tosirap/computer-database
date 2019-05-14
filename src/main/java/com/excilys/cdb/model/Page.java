package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurComputer;
import com.excilys.cdb.validator.ValidatorComputerUIweb;
import com.excilys.cdb.validator.ValidatorPage;

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


	public HttpServletRequest createPage(HttpServletRequest request, String mode) {
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

		int nbComputer = 0;
		if (mode == null || mode.isEmpty() || mode.equals("dashboard")) {
			listDTOComputer = mappeurComputer.computerToDTO(serviceComputer.listPagination(PCparPageInt, offset));
			nbComputer = serviceComputer.count();
		} else if (mode.equals("searchComputer")) {
			listDTOComputer = mappeurComputer.computerToDTO(
					serviceComputer.searchComputer(request.getParameter("search"), PCparPageInt, offset));
			request.setAttribute("search", request.getParameter("search"));
			nbComputer = serviceComputer.searchComputerCount(request.getParameter("search"));
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
		
		request.setAttribute("PCparPage", PCparPage);
		request.setAttribute("page", pageNum);
		request.setAttribute("listComputer", listDTOComputer);
		request.setAttribute("sizeList", listDTOComputer.size());
		request.setAttribute("total", nbComputer);
		request.setAttribute("nbPageTotal", nbPageTotal);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("mode", mode);
		return request;
	}

}
