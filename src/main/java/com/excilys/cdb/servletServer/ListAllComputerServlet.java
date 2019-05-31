package com.excilys.cdb.servletServer;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.MappeurComputer;

@Controller
@SessionAttributes(value = ListAllComputerServlet.PAGINATION_PATTERN, types = { Page.class })
public class ListAllComputerServlet {

	protected static final String PAGINATION_PATTERN = "pg";

	private ServiceComputer serviceComputer;
	private MappeurComputer mappeurComputer;

	public ListAllComputerServlet(ServiceComputer serviceComputer, MappeurComputer mappeurComputer) {
		this.serviceComputer = serviceComputer;
		this.mappeurComputer = mappeurComputer;
	}

	@ModelAttribute(PAGINATION_PATTERN)
	public Page addPaginationToSessionScope() {
		return new Page();
	}

	@GetMapping(value = { "/dashboard", "/" })
	public String get(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "PCparPage", required = false) Integer PCparPage,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "orderby", required = false) String orderBy,
			@RequestParam(value = "asc", required = false) String asc,
			@RequestParam(value = "lang", required = false) String lang,
			@ModelAttribute(PAGINATION_PATTERN) Page pagination, Model model) {

		if (page != null) {
			pagination.setPageInt(page);
		} else {
			pagination.setPageInt(1);
		}
		if (PCparPage != null) {
			pagination.setPCparPage(PCparPage);
		}
		if (search != null) {
			pagination.setSearch(search);
		}
		if (orderBy != null) {
			pagination.setOrderBy(orderBy);
		}
		if(lang !=null) {
			pagination.setLang(lang);
		}

		pagination.setOffset();
		ArrayList<DTOComputer> dtoComputers;
		int nbComputertotal = 0;

		if (pagination.getSearch() != null && !pagination.getSearch().isEmpty()) {
			dtoComputers = mappeurComputer
					.computerToDTO(serviceComputer.searchComputer(pagination.getSearch(), pagination.getPCparPage(),
							pagination.getOffset(), pagination.getOrderBy()));
			nbComputertotal = serviceComputer.searchComputerCount(pagination.getSearch());
		} else {
			dtoComputers = mappeurComputer.computerToDTO(serviceComputer.listPagination(pagination.getPCparPage(),
					pagination.getOffset(), pagination.getOrderBy()));
			nbComputertotal = serviceComputer.count();
		}

		pagination.setNbPcTotal(nbComputertotal);
		pagination.createPage();
		model.addAttribute("listComputer", dtoComputers);
		return "dashboard";
	}

}
