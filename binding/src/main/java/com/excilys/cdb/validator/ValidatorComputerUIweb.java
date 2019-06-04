package com.excilys.cdb.validator;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.model.DTOCompany;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.transfert.MappeurCompany;

@Component
public class ValidatorComputerUIweb {

	private MappeurCompany mappeurCompany;
	private DAOCompany daoCompany;
	static Logger logger = LoggerFactory.getLogger(ValidatorComputerUIweb.class);

	public ValidatorComputerUIweb(MappeurCompany mappeurCompany,DAOCompany daoCompany) {
		this.mappeurCompany = mappeurCompany;
		this.daoCompany = daoCompany;
	}

	public boolean testSiCorrect(DTOComputer dtoComputer) {
		if (!checkName(dtoComputer.getName())) {
			return false;
		}
		if (!checkDate(dtoComputer.getIntroduced(), dtoComputer.getDiscontinuted())) { // test date
			return false;
		}
		if (!checkCompanyExiste(dtoComputer.getCompanyId())) { // test companyId
			return false;
		}
		return true;
	}

	private boolean checkName(String name) {
		return !(name == null || name.equals("") || name.equals("null"));
	}

	private boolean checkDate(String str1, String str2) {
		Date dLimit = Date.valueOf("1970-01-01");
		try {
			if (str1 != null && !str1.equals("")) {
				Date d1 = Date.valueOf(str1);
				if (d1.before(dLimit)) {
					System.out.println("La date de mise en service doit etre apres 1970");
					return false;
				}
			}

			if (str2 != null && !str2.equals("")) {
				Date d2 = Date.valueOf(str2);
				if (d2.before(dLimit)) {
					System.out.println("La date de mise en service doit etre apres 1970");
					return false;
				}
			}

			if (str1 != null && !str1.equals("") && str2 != null && !str2.equals("")) {
				Date d1 = Date.valueOf(str1);
				Date d2 = Date.valueOf(str2);
				if (d1.after(d2)) {
					System.out.println("La date de mise en service doit etre antérieur a la date de retrait");
					return false;
				}
			} else if (str1 != null && !str1.equals("")) {
				Date.valueOf(str1);
			} else if (str2 != null && !str2.equals("")) {
				Date.valueOf(str2);
			}

			return true;
		} catch (Exception e) {
			logger.info(e.getMessage() + "Erreur dans la date avec: " + str1 + ", " + str2);
			return false;
		}
	}

	private boolean checkCompanyExiste(Long id) {
		try {

			DTOCompany dtoCompany = mappeurCompany.companyToDTO(daoCompany.find(id));
			if (dtoCompany == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return false;
	}
}
