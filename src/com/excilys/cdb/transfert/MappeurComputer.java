package com.excilys.cdb.transfert;

import java.sql.Date;
import java.util.ArrayList;

import com.excilys.cdb.model.Computer;

public class MappeurComputer {

	/*
	 * From a list of Computer to a list of DTO
	 */
	public ArrayList<DTOComputer> computerToDTO(ArrayList<Computer> ALComputer) {
		ArrayList<DTOComputer> ALDTO = new ArrayList<DTOComputer>();
		for (Computer computer : ALComputer) {
			ALDTO.add(computerToDTO(computer));
					
					/*new DTOComputer(String.valueOf(computer.getId()), computer.getName(),
					String.valueOf(computer.getIntroduced()), String.valueOf(computer.getDiscontinuted()),
					String.valueOf(computer.getCompanyId()),computer.getCompanyName()));*/
		}
		return ALDTO;
	}

	/*
	 * From a list of DTO to a list of Computer
	 */
	public ArrayList<Computer> DTOToComputer(ArrayList<DTOComputer> ALDTO) {
		ArrayList<Computer> ALComputer = new ArrayList<Computer>();
		for (DTOComputer dto : ALDTO) {
			Computer tmpComputer = DTOToComputer(dto);
					
					/*new Computer(Integer.parseInt(dto.getId()), dto.getName(),
					Date.valueOf(dto.getIntroduced()), Date.valueOf(dto.getDiscontinuted()),
					Integer.parseInt(dto.getCompanyId()),dto.getCompanyName());*/
			ALComputer.add(tmpComputer);
		}
		return ALComputer;
	}

	/*
	 * From a Computer to a DTO
	 */

	public DTOComputer computerToDTO(Computer computer) {
		return new DTOComputer(String.valueOf(computer.getId()), computer.getName(),
				String.valueOf(computer.getIntroduced()), String.valueOf(computer.getDiscontinuted()),
				String.valueOf(computer.getCompanyId()),computer.getCompanyName());
	}

	/*
	 * From a DTO to Computer
	 */

	public Computer DTOToComputer(DTOComputer dto) {
		return new Computer(Integer.parseInt(dto.getId()), dto.getName(),
				Date.valueOf(dto.getIntroduced()), Date.valueOf(dto.getDiscontinuted()),
				Integer.parseInt(dto.getCompanyId()),dto.getCompanyName());
	}
}
