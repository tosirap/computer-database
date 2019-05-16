package com.excilys.cdb.transfert;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;

@Component
public class MappeurCompany {

	public MappeurCompany(){
		super();
	}
	
	
	/**
	 * From a list of Company to a list of DTO
	 * Function that transform an ArrayList of Company to an Array List of DTOCompany
	 */
	public ArrayList<DTOCompany> companyToDTO(ArrayList<Company> ALCompany) {
		ArrayList<DTOCompany> ALDTO = new ArrayList<DTOCompany>();
		for(Company company : ALCompany) {
			ALDTO.add(new DTOCompany(String.valueOf(company.getId()),company.getName()));
		}
		return ALDTO;
	}
	
	public DTOCompany companyToDTO(Company company) {
		if(company == null) {
			return null;
		}
		return new DTOCompany(String.valueOf(company.getId()),company.getName());
	}

}
