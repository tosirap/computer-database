package com.excilys.cdb.servletServer;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.DTOCompany;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurCompany;
import com.excilys.cdb.transfert.MappeurComputer;

@Controller
public class EditComputerServlet {

	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;
	private MappeurComputer mappeurComputer;
	private MappeurCompany mappeurCompany;
	// private ValidatorComputerUIweb validatorComputerUIweb;
	static Logger logger = LoggerFactory.getLogger(EditComputerServlet.class);
	private final String message = "Insertion effectuée !! ";
	private final String messageErreur = "Erreur /!\\";

	public EditComputerServlet(ServiceComputer serviceComputer, ServiceCompany serviceCompany,
			MappeurComputer mappeurComputer, MappeurCompany mappeurCompany) {

		this.serviceComputer = serviceComputer;
		this.serviceCompany = serviceCompany;
		this.mappeurComputer = mappeurComputer;
		this.mappeurCompany = mappeurCompany;
		// validatorComputerUIweb = wac.getBean(ValidatorComputerUIweb.class);
	}

	@GetMapping(value = { "/editComputer" })
	public String get(@RequestParam(value = "id") String id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "intro", required = false) String introduced,
			@RequestParam(value = "discon", required = false) String discontinued,
			@RequestParam(value = "company", required = false) String companyId, Model model) {
		ArrayList<DTOCompany> alCompany = mappeurCompany.companyToDTO(serviceCompany.listAllElements());
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("intro", introduced);
		model.addAttribute("discon", discontinued);
		model.addAttribute("company", companyId);
		model.addAttribute("alCompany", alCompany);
		return "editComputer";
	}

	@PostMapping(value = { "/editComputer" })
	public String post(@RequestParam(value = "id") String id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "intro", required = false) String introduced,
			@RequestParam(value = "discon", required = false) String discontinued,
			@RequestParam(value = "company", required = false) String companyId, Model model) {

		DTOComputer dtoComputer = new DTOComputer(id, name, introduced, discontinued, companyId, "");
		if (serviceComputer.update(mappeurComputer.DTOToComputer(dtoComputer))) {
			model.addAttribute("message", message);
		} else {
			model.addAttribute("messageErreur", messageErreur);
		}
		return "editComputer";
	}
}
