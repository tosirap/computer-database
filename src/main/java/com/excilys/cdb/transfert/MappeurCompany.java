package com.excilys.cdb.transfert;

import java.util.ArrayList;

import com.excilys.cdb.model.Company;

public class MappeurCompany {

	private MappeurCompany(){
		
	}
	
	/** Instance unique pré-initialisée */
    private static MappeurCompany INSTANCE = new MappeurCompany();
     
    /** Point d'accès pour l'instance unique du singleton */
    public static MappeurCompany getInstance()
    {   return INSTANCE;
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
	
	/**
	 * From a list of DTO to a list of Company
	 * Function that transform an ArrayList of DTOCompany to an Array List of Company
	 */
	/*public ArrayList<Company> DTOToCompany(ArrayList<DTOCompany> ALDTO){
		 ArrayList<Company> ALCompany = new ArrayList<Company>();
		 for(DTOCompany dto: ALDTO) {
			 ALCompany.add(new Company(Integer.parseInt(dto.getId()),dto.getName()));
		 }
		 return ALCompany;
	}*/
}
