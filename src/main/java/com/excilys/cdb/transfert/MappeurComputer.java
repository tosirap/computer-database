package com.excilys.cdb.transfert;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Computer;

@Component
public class MappeurComputer {

	public MappeurComputer() {
		super();
	}

	/**
	 * From a list of Computer to a list of DTO Function that transform an Array
	 * List of Computer to an ArrayList of DTOComputer
	 */
	public ArrayList<DTOComputer> computerToDTO(ArrayList<Computer> ALComputer) {
		ArrayList<DTOComputer> ALDTO = new ArrayList<DTOComputer>();
		if (ALComputer != null && !ALComputer.isEmpty()) {
			for (Computer computer : ALComputer) {
				ALDTO.add(computerToDTO(computer));
			}
		}
		return ALDTO;
	}

	public DTOComputer computerToDTO(Computer computer) {
		int companyID = 0;
		String companyName;
		if (computer.getCompany() == null) {
			companyName = null;
		} else {
			companyID = computer.getCompany().getId();
			companyName = computer.getCompany().getName();
		}
		return new DTOComputer(computer.getId(), computer.getName(), String.valueOf(computer.getIntroduced()),
				String.valueOf(computer.getDiscontinuted()), companyID, companyName);
	}

	/**
	 * From a DTO to Computer Function that transform a DTOComputer to a computer
	 */
	public Computer DTOToComputer(DTOComputer dto) {
		return new Computer(dto.getId(), dto.getName(), dto.getIntroduced(), dto.getDiscontinuted(), dto.getCompanyId(),
				dto.getCompanyName());
	}
}
