package com.excilys.cdb.validator;

import java.sql.Date;

import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.transfert.DTOCompany;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurCompany;

public class ValidatorComputerUIweb {

	//MappeurComputer mappeurComputer = MappeurComputer.getInstance();
	MappeurCompany mappeurCompany = MappeurCompany.getInstance();
	ServiceCompany serviceCompany = ServiceCompany.getInstance();
	
	public ValidatorComputerUIweb() {
		
	}
	
	public boolean testSiCorrect(DTOComputer dtoComputer) {
		if(!checkName(dtoComputer.getName())) {
			return false;
		}
		if(!checkDate(dtoComputer.getIntroduced(),dtoComputer.getDiscontinuted())) { //test date
			return false;
		}
		if(!checkCompanyExiste(dtoComputer.getCompanyId())) { //test companyId
			return false;
		}
		return true;
	}
	
	private boolean checkName(String name) {
		return !(name==null || name.equals("") || name.equals("null")) ;
	}

	private boolean checkDate(String str1, String str2) {
		System.out.println();
		try {
			if (str1 != null && !str1.equals("") && str2 != null && !str2.equals("")) {
				Date d1 = Date.valueOf(str1);
				Date d2 = Date.valueOf(str2);
				if (d1.compareTo(d2) > 0) {
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
			System.out.println(e.getMessage()+ "Erreur dans la date avec: "+ str1 + ", "+ str2 );
			return false;
		}
	}
	
	private boolean checkCompanyExiste(String idCompany) {
		try {
			int id = Integer.parseInt(idCompany);
			DTOCompany dtoCompany = mappeurCompany.companyToDTO(serviceCompany.getOneCompany(id));
			if(dtoCompany == null) {
				return false;
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
