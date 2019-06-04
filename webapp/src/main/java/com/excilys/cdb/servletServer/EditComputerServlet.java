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

import com.excilys.cdb.dto.DTOCompany;
import com.excilys.cdb.dto.DTOComputer;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
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
	private final String message = "Insertion effectuee !! ";
	private final String messageErreur = "Erreur /!\\";

	public EditComputerServlet(ServiceComputer serviceComputer, ServiceCompany serviceCompany,
			MappeurComputer mappeurComputer, MappeurCompany mappeurCompany) {

		this.serviceComputer = serviceComputer;
		this.serviceCompany = serviceCompany;
		this.mappeurComputer = mappeurComputer;
		this.mappeurCompany = mappeurCompany;
		// validatorComputerUIweb = wac.getBean(ValidatorComputerUIweb.class);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@GetMapping(value = { "/editComputer" })
	public String get(@RequestParam(value = "id") Long id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "intro", required = false) String introduced,
			@RequestParam(value = "discon", required = false) String discontinued,
			@RequestParam(value = "company" , required = false) Long companyId,
			@RequestParam(value = "companyName" , required = false) String companyName,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "messageErreur", required = false) String messageErreur, Model model) {
		ArrayList<DTOCompany> alCompany = mappeurCompany.companyToDTO(serviceCompany.listAllElements());
		if (message != null) {
			model.addAttribute("message", message);
		}
		if (messageErreur != null) {
			model.addAttribute("messageErreur", messageErreur);
		}
		if (id != null) {
			model.addAttribute("id", id);
		}
		if (name != null) {
			model.addAttribute("name", name);
		}
		if (introduced != null) {
			model.addAttribute("intro", introduced);
		}
		if (discontinued != null) {
			model.addAttribute("discon", discontinued);
		}
		if (companyId != null) {
			model.addAttribute("company", companyId);
		}
		if (companyName != null) {
			model.addAttribute("companyName", companyName);
		}
		model.addAttribute("alCompany", alCompany);
		return "editComputer";
	}

	@PostMapping(value = { "/editComputer" })
	public RedirectView post(@RequestParam(value = "id") Long id, @RequestParam(value = "name") String name,
			@RequestParam(value = "intro", required = false) String introduced,
			@RequestParam(value = "discon", required = false) String discontinued,
			@RequestParam(value = "company", required = false) Long companyId, Model model) {

		DTOComputer dtoComputer = new DTOComputer(id, name, introduced, discontinued, companyId, "");

		if (serviceComputer.update(mappeurComputer.DTOToComputer(dtoComputer))) {
			model.addAttribute("message", message);
		} else {
			model.addAttribute("messageErreur", messageErreur);
		}
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		if (introduced != null) {
			model.addAttribute("intro", introduced);
		}
		if (discontinued != null) {
			model.addAttribute("discon", discontinued);
		}
		model.addAttribute("company", companyId);
		return new RedirectView("editComputer");
	}
}
