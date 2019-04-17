package com.excilys.cdb.transfert;

import java.util.ArrayList;

import com.excilys.cdb.model.Company;

public class MappeurCompany {

	/*
	 * From a list of Company to a list of DTO
	 */
	public ArrayList<DTOCompany> companyToDTO(ArrayList<Company> ALCompany) {
		ArrayList<DTOCompany> ALDTO = new ArrayList<DTOCompany>();
		for(Company company : ALCompany) {
			ALDTO.add(new DTOCompany(String.valueOf(company.getId()),company.getName()));
		}
		return ALDTO;
	}
	
	/*
	 * From a list of DTO to a list of Company
	 */
	public ArrayList<Company> DTOToCompany(ArrayList<DTOCompany> ALDTO){
		 ArrayList<Company> ALCompany = new ArrayList<Company>();
		 for(DTOCompany dto: ALDTO) {
			 ALCompany.add(new Company(Integer.parseInt(dto.getId()),dto.getName()));
		 }
		 return ALCompany;
	}
}
