package com.excilys.cdb.transfert;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.DTOCompany;
import com.excilys.cdb.dto.DTOComputer;

@Component
public class MappeurControlleur {

	public MappeurControlleur() {
		super();
	}

	/**
	 * String -> dtoComputer Function that transform a DTOComputer to a String
	 */
	public String dtoToString(DTOComputer dto) {
		return /* dto.getId()+";"+ */dto.getName() + ";" + dto.getIntroduced() + ";" + dto.getDiscontinuted()
				+ ";"/* +dto.getCompanyId()+";" */ + dto.getCompanyName();
	}

	/*
	 * String -> dtoCompany
	 */
	public String dtoToString(DTOCompany dto) {
		return dto.getName();
	}

	/*
	 * Array list de dto copmuter -> array list string
	 */
	public ArrayList<String> dtoComputerALToStringAL(ArrayList<DTOComputer> dtoAL) {
		ArrayList<String> stringAL = new ArrayList<String>();
		for (DTOComputer dto : dtoAL) {
			stringAL.add(dtoToString(dto));
		}
		return stringAL;
	}

	/*
	 * Array list de dto copmany -> array list string
	 */
	public ArrayList<String> dtoCompanyALToStringAL(ArrayList<DTOCompany> dtoAL) {
		ArrayList<String> stringAL = new ArrayList<String>();
		for (DTOCompany dto : dtoAL) {
			stringAL.add(dtoToString(dto));
		}
		return stringAL;
	}

	public DTOComputer createDTOComputer(String name, String introduced, String discontinuted, Long companyId,
			String companyName) {
		return new DTOComputer(name, introduced, discontinuted, companyId, companyName);
	}

	public DTOComputer createDTOComputer(Long id, String name, String introduced, String discontinuted, Long companyId,
			String companyName) {
		return new DTOComputer(id, name, introduced, discontinuted, companyId, companyName);
	}

	public DTOComputer createDTOComputerWithCompanyName(String name, String introduced, String discontinuted,
			String companyName) {
		return new DTOComputer(name, introduced, discontinuted, (long)-1, companyName);
	}

}
