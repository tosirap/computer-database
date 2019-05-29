package com.excilys.cdb.servletServer;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.cdb.model.DTOCompany;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.MappeurCompany;
import com.excilys.cdb.transfert.MappeurComputer;

@Controller
public class AjoutComputerServlet {

	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;
	private MappeurComputer mappeurComputer;
	private MappeurCompany mappeurCompany;
	// private ValidatorComputerUIweb validatorComputerUIweb;

	static Logger logger = LoggerFactory.getLogger(AjoutComputerServlet.class);
	private final String message = "Insertion effectuee !! ";
	private final String messageErreur = "Erreur /!\\";

	public AjoutComputerServlet(ServiceComputer serviceComputer, ServiceCompany serviceCompany,
			MappeurComputer mappeurComputer, MappeurCompany mappeurCompany) {

		this.serviceComputer = serviceComputer;
		this.serviceCompany = serviceCompany;
		this.mappeurComputer = mappeurComputer;
		this.mappeurCompany = mappeurCompany;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@GetMapping(value = { "/addComputer" })
	public String get(@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "messageErreur", required = false) String messageErreur, Model model) {
		ArrayList<DTOCompany> alCompany = mappeurCompany.companyToDTO(serviceCompany.listAllElements());
		if (message != null) {
			model.addAttribute("message", message);
		}
		if (messageErreur != null) {
			model.addAttribute("messageErreur", messageErreur);
		}
		model.addAttribute("alCompany", alCompany);
		return "addComputer";
	}

	@PostMapping(value = { "/addComputer" })
	public RedirectView post(@RequestParam(value = "computerName") String name,
			@RequestParam(value = "introduced") String introduced,
			@RequestParam(value = "discontinued") String discontinued,
			@RequestParam(value = "companyId") Long companyId, Model model) {

		DTOComputer dtoComputer = new DTOComputer(name, introduced, discontinued, companyId);
		if (serviceComputer.create(mappeurComputer.DTOToComputer(dtoComputer))) {
			model.addAttribute("message", message);
		} else {
			model.addAttribute("messageErreur", messageErreur);
		}
		return new RedirectView("addComputer");
	}
}
