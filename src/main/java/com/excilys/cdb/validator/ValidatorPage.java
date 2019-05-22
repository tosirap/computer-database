package com.excilys.cdb.validator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.OrderBy;


@Component
public class ValidatorPage {
	static Logger logger = LoggerFactory.getLogger(ValidatorPage.class);

	public boolean testSiCorrect(HttpServletRequest request) {
		return checkPage(request) && checkPCparPage(request) && /*checkMode(request) &&*/ checkOrderBy(request)
				&& checkAscendant(request);
	}

	private boolean checkPage(HttpServletRequest request) {
		if (request.getParameter("page") == null || request.getParameter("page").isEmpty()) {
			// si vide ou null, valeur par defaut = 1
			return true;
		}
		int pageNum = 0;
		try {
			pageNum = Integer.parseInt(request.getParameter("page"));
			if (pageNum >= 0) {
				return true;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return false;
	}

	private boolean checkPCparPage(HttpServletRequest request) {
		if (request.getParameter("PCparPage") == null || request.getParameter("PCparPage").isEmpty()) {
			// si vide ou null, valeur par defaut = 10
			return true;
		} else {
			try {
				int PCparPageInt = Integer.parseInt(request.getParameter("PCparPage"));
				if (PCparPageInt >= 1) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		return false;
	}

	/*private boolean checkMode(HttpServletRequest request) {
		return (request.getParameter("mode") == null || request.getParameter("mode").isEmpty()
				|| request.getParameter("mode").equals("dashboard")
				|| request.getParameter("mode").equals("searchComputer"));
	}*/

	private boolean checkOrderBy(HttpServletRequest request) {
		if (request.getParameter("orderby") == null || request.getParameter("orderby").isEmpty()) {
			return true;
		}
		for (OrderBy b : OrderBy.values()) {
			if (b.toString().equals(request.getParameter("orderby"))) {
				return true;
			}
		}
		return false;
	}

	private boolean checkAscendant(HttpServletRequest request) {
		if (request.getParameter("asc") == null || request.getParameter("asc").isEmpty()
				|| request.getParameter("asc").equals("true") || request.getParameter("asc").equals("false")) {
			return true;
		}
		return false;
	}
}
