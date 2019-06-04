package com.excilys.cdb.servletServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.cdb.service.ServiceComputer;

@Controller
public class SuppresionPcServlet {

	private ServiceComputer serviceComputer;
	static Logger logger = LoggerFactory.getLogger(SuppresionPcServlet.class);

	public SuppresionPcServlet(ServiceComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}

	@PostMapping(value = { "/delete" })
	public RedirectView post(@RequestParam(value = "selection", required = false) String selection, Model model) {
		String[] tabSelection = selection.split(",");
		for (String s : tabSelection) {
			serviceComputer.delete(s);
		}
		return new RedirectView("dashboard");

	}
}
