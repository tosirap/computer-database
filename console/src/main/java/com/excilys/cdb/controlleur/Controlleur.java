package com.excilys.cdb.controlleur;

import java.sql.Date;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.DTOCompany;
import com.excilys.cdb.dto.DTOComputer;
import com.excilys.cdb.enums.OrderBy;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.MappeurCompany;
import com.excilys.cdb.transfert.MappeurComputer;
import com.excilys.cdb.transfert.MappeurControlleur;

@Component
public class Controlleur {

	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;
	MappeurControlleur mappeurControlleur;
	MappeurComputer mappeurComputer;
	MappeurCompany mappeurCompany;

	Logger logger = LoggerFactory.getLogger(Controlleur.class);

	private Controlleur(ServiceComputer serviceComputer, ServiceCompany serviceCompany, MappeurComputer mappeurComputer,
			MappeurCompany mappeurCompany, MappeurControlleur mappeurControlleur) {
		this.serviceComputer = serviceComputer;
		this.serviceCompany = serviceCompany;
		this.mappeurComputer = mappeurComputer;
		this.mappeurCompany = mappeurCompany;
		this.mappeurControlleur = mappeurControlleur;
	}

	/*
	 * retourne la liste des pc
	 */
	public ArrayList<String> listComputer() { // ok
		ArrayList<DTOComputer> dtoAL = null;
		dtoAL = mappeurComputer.computerToDTO(serviceComputer.listAllElements());
		return mappeurControlleur.dtoComputerALToStringAL(dtoAL);
	}

	/*
	 * retourne la liste des pc par pagination
	 */
	public ArrayList<String> listComputerPagination(String li, String of) {
		try {
			int limit = Integer.parseInt(li);
			int offset = Integer.parseInt(of);
			if (limit <= 0) {
				System.out.println("Limit doit etre d'au moins 1");
				return null;
			}
			if (offset < 0) {
				System.out.println("offset doit etre positif");
				return null;
			}
			ArrayList<DTOComputer> dtoAL = mappeurComputer
					.computerToDTO(serviceComputer.listPagination(limit, offset, OrderBy.COMPUTER_ID));
			return mappeurControlleur.dtoComputerALToStringAL(dtoAL);
		} catch (Exception e) {
			System.out.println("Entrez 2 entiers");
			logger.info(e.getMessage() + "Probleme de type : " + li + ", " + of);
		}
		return null;

	}

	/*
	 * retourne la liste des company
	 */
	public ArrayList<String> listCompany() { // ok
		ArrayList<DTOCompany> dtoAL = null;
		dtoAL = mappeurCompany.companyToDTO(serviceCompany.listAllElements());
		return mappeurControlleur.dtoCompanyALToStringAL(dtoAL);
	}

	/*
	 * retourne la liste des company par pagination
	 */
	public ArrayList<String> listCompanyPagination(String li, String of) {
		try {
			int limit = Integer.parseInt(li);
			int offset = Integer.parseInt(of);
			if (limit <= 0) {
				System.out.println("Limit doit etre d'au moins 1");
				return null;
			}
			if (offset < 0) {
				System.out.println("offset doit etre positif");
				return null;
			}
			ArrayList<DTOCompany> dtoAL = mappeurCompany.companyToDTO(serviceCompany.listPagination(limit, offset));
			return mappeurControlleur.dtoCompanyALToStringAL(dtoAL);
		} catch (Exception e) {
			System.out.println("Entrez 2 entiers");
			logger.info(e.getMessage() + "probleme de type: " + li + ", " + of);
		}
		return null;

	}

	/*
	 * return les details d'un PC
	 */
	public String computerDetails(String idPC) {
		// retourne les infos d'un PC a partir d'un id
		try {
			Long id = Long.parseLong(idPC);
			return mappeurControlleur.dtoToString(mappeurComputer.computerToDTO(serviceComputer.listElement(id)));
		} catch (Exception e) {
			// ou alors on cherche par le nom
			logger.info(e.getMessage() + "Erreur, veuillez entrez un entier  !! " + idPC);
			System.out.println("Erreur, veuillez entrez un entier  !! ");
		}
		return "Erreur, veuillez entrez un entier  !! ";
	}

	/*
	 * return les details d'un PC
	 */
	public String computerDetailsName(String namePC) {
		// retourne les infos d'un PC a partir d'un name
		try {
			return mappeurControlleur
					.dtoToString(mappeurComputer.computerToDTO(serviceComputer.listElementName(namePC)));
		} catch (Exception e) {
			// ou alors on cherche par le nom
			logger.info(e.getMessage());
		}
		return "Erreur , element introuvable";
	}

	/*
	 * return les PC ayant ce nom
	 */
	/*
	 * public ArrayList<String> computerListDetailsName(String namePC) { // retourne
	 * les infos d'un PC a partir d'un name try { ArrayList<DTOComputer> dtoAL =
	 * mappeurComputer.computerToDTO(serviceComputer.listMultiElementByName(namePC))
	 * ; return mappeurControlleur.dtoComputerALToStringAL(dtoAL); } catch
	 * (Exception e) { // ou alors on cherche par le nom
	 * logger.info(e.getMessage());
	 * 
	 * } return null; }
	 */

	/*
	 * essaye de créer un pc
	 */
	public boolean createComputer(String name, String introduced, String discontinuted, String companyID,
			String companyName) {
		if (checkDate(introduced, discontinuted)) {
			System.out.println("createComputer");
			try {
				Long companyIdInt = Long.parseLong(companyID);
				return serviceComputer.create(mappeurComputer.DTOToComputer(mappeurControlleur.createDTOComputer(name,
						introduced, discontinuted, companyIdInt, companyName)));
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		return false;
	}

	/*
	 * essaye de créer un pc
	 */
	public boolean createComputerWithCompanyName(String name, String introduced, String discontinuted,
			String companyName) {
		if (checkDate(introduced, discontinuted)) {
			System.out.println("createComputerByName");
			try {
				return serviceComputer.createWithCompanyName(mappeurComputer.DTOToComputer(mappeurControlleur
						.createDTOComputerWithCompanyName(name, introduced, discontinuted, companyName)));
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		return false;
	}

	/*
	 * updat d'un pc en fonction d'un id
	 */
	public boolean updateComputer(String idComputerAmodifier, String name, String introduced, String discontinuted,
			String companyID) {
		// update pc, true reussi | false echec
		try {
			Long id = Long.parseLong(idComputerAmodifier);
			Long companyIDInt = Long.parseLong(companyID);
			if (checkDate(introduced, discontinuted)) {
				return serviceComputer.update(mappeurComputer.DTOToComputer(
						mappeurControlleur.createDTOComputer(id, name, introduced, discontinuted, companyIDInt, "")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	/*
	 * suppression d'un PC en fonction de son id
	 */
	public boolean supprComputer(String idComputerAsuppr) {
		// suppr pc, true reussi | false echec
		try {
			Long id = Long.parseLong(idComputerAsuppr);
			if (id < 0) {
				return false;
			}
			return serviceComputer.delete(mappeurComputer
					.DTOToComputer(mappeurControlleur.createDTOComputer(id, "", "2017-07-07", "2017-07-07", (long)1, "")));
		} catch (Exception e) {
			System.out.println("l'id doit etre un int");
			logger.info(e.getMessage() + "Erreur de format dans supprComputer de controlleur" + idComputerAsuppr);
		}
		return false;

	}

	/*
	 * fonction auxiliaire qui sert à vérifier si les 2 dates sont correctes et sont
	 * dans le bon ordre chronologique
	 */
	public boolean checkDate(String str1, String str2) {
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
			logger.info(e.getMessage() + "Erreur dans la date avec: " + str1 + ", " + str2);
			return false;
		}
	}

	public int countComputer() {
		try {
			return serviceComputer.count();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

	public boolean deleteCompany(String idstr) {
		try {
			Long id = Long.parseLong(idstr);
			return serviceCompany.deleteCompany(id);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return false;
	}
}
