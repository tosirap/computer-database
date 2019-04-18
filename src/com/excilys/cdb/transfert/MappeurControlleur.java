package com.excilys.cdb.transfert;

import java.util.ArrayList;

public class MappeurControlleur {
	
	/*
	 * String -> dtoComputer
	 */
	public String dtoToString(DTOComputer dto) {
		return dto.getId()+";"+dto.getName()+";"+dto.getIntroduced()+";"+dto.getDiscontinuted()+";"+dto.getCompanyId()+";"+dto.getCompanyName();
	}
	
	/*
	 * String -> dtoCompany
	 */
	public String dtoToString(DTOCompany dto) {
		return dto.getId()+";"+dto.getName();
	}
	
	/*
	 * Array list de dto copmuter  -> array list string
	 */
	public ArrayList<String> dtoComputerALToStringAL(ArrayList<DTOComputer> dtoAL){
		ArrayList<String> stringAL = new ArrayList<String>();
		for(DTOComputer dto : dtoAL) {
			stringAL.add(dtoToString(dto));
		}
		return stringAL;
	}
	

	/*
	 * Array list de dto copmany  -> array list string
	 */
	public ArrayList<String> dtoCompanyALToStringAL(ArrayList<DTOCompany> dtoAL){
		ArrayList<String> stringAL = new ArrayList<String>();
		for(DTOCompany dto : dtoAL) {
			stringAL.add(dtoToString(dto));
		}
		return stringAL;
	}
	
	public DTOComputer createDTOComputer(String name, String introduced, String discontinuted, String companyId, String companyName) {
		return new DTOComputer(name,introduced,discontinuted,companyId, companyName);
	}
	
	public DTOComputer createDTOComputer(String id ,String name, String introduced, String discontinuted, String companyId, String companyName) {
		return new DTOComputer(id,name,introduced,discontinuted,companyId, companyName);
	}
	
}
