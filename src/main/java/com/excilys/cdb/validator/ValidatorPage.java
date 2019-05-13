package com.excilys.cdb.validator;

import javax.servlet.http.HttpServletRequest;

public class ValidatorPage {

	public boolean testSiCorrect(HttpServletRequest request) {
		return checkPage(request) && checkPCparPage(request)&& checkMode(request);
	}

	private boolean checkPage(HttpServletRequest request) {
		if (request.getParameter("page") == null || request.getParameter("page").isEmpty()) {
			// si vide ou null, valeur par defaut = 1
			return true;
		}
		int pageNum = 0;
		try {
			pageNum = Integer.parseInt(request.getParameter("page"));
			if (pageNum >= 1) {
				return true;
			}
		} catch (Exception e) {
			return false;
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
				e.printStackTrace();
			}
		}
		return false;
	}

	private boolean checkMode(HttpServletRequest request) {
		return (request.getParameter("mode") == null || request.getParameter("mode").isEmpty()
				|| request.getParameter("mode").equals("dashboard")
						|| request.getParameter("mode").equals("searchComputer"));
	}
}
