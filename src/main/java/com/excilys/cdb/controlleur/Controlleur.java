package com.excilys.cdb.controlleur;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.DTOCompany;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurControlleur;

public class Controlleur {

	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;
	MappeurControlleur mappeurControlleur;
	 Logger logger  = LoggerFactory.getLogger(Controlleur.class);

	private Controlleur() throws ClassNotFoundException, SQLException {
		this.serviceComputer = ServiceComputer.getInstance();
		this.serviceCompany = ServiceCompany.getInstance();
		mappeurControlleur = MappeurControlleur.getInstance();

	}

	/** Instance unique pré-initialisée */
	private static Controlleur INSTANCE = null;

	/** Point d'accès pour l'instance unique du singleton */
	public static Controlleur getInstance() {
		if (INSTANCE == null) {
			try {
				INSTANCE = new Controlleur();
			} catch (Exception e) {
				
				//logger.info(e.getMessage());
			}
		}
		return INSTANCE;
	}

	/*
	 * retourne la liste des pc
	 */
	public ArrayList<String> listComputer() { // ok
		ArrayList<DTOComputer> dtoAL = null;
		try {
			dtoAL = serviceComputer.listAllElements();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
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
			ArrayList<DTOComputer> dtoAL = serviceComputer.listPagination(limit, offset);
			return mappeurControlleur.dtoComputerALToStringAL(dtoAL);
		} catch (Exception e) {
			System.out.println("Entrez 2 entiers");
			logger.info(e.getMessage()+ "Probleme de type : "+ li + ", "+ of);
		}
		return null;

	}

	/*
	 * retourne la liste des company
	 */
	public ArrayList<String> listCompany() { // ok
		ArrayList<DTOCompany> dtoAL =  null;
		try {
			dtoAL = serviceCompany.listAllElements();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
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
			ArrayList<DTOCompany> dtoAL = serviceCompany.listPagination(limit, offset);
			return mappeurControlleur.dtoCompanyALToStringAL(dtoAL);
		} catch (Exception e) {
			System.out.println("Entrez 2 entiers");
			logger.info(e.getMessage()+ "probleme de type: "+ li +", "+ of);
		}
		return null;

	}

	/*
	 * return les details d'un PC
	 */
	public String computerDetails(String idPC) {
		// retourne les infos d'un PC a partir d'un id
		try {
			int id = Integer.parseInt(idPC);
			return mappeurControlleur.dtoToString(serviceComputer.listElement(id));
		} catch (Exception e) {
			// ou alors on cherche par le nom
			logger.info(e.getMessage()+ "Erreur, veuillez entrez un entier  !! "+ idPC);
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
			return mappeurControlleur.dtoToString(serviceComputer.listElementName(namePC));
		} catch (Exception e) {
			// ou alors on cherche par le nom
			logger.info(e.getMessage());
			
		}
		return "Erreur , element introuvable";
	}

	/*
	 * return les PC ayant ce nom
	 */
	public ArrayList<String> computerListDetailsName(String namePC) {
		// retourne les infos d'un PC a partir d'un name
		try {
			ArrayList<DTOComputer> dtoAL = serviceComputer.listMultiElementByName(namePC);
			return mappeurControlleur.dtoComputerALToStringAL(dtoAL);
		} catch (Exception e) {
			// ou alors on cherche par le nom
			logger.info(e.getMessage());
			
		}
		return null;
	}
	
	
	/*
	 * essaye de créer un pc
	 */
	public boolean createComputer(String name, String introduced, String discontinuted, String companyID,
			String companyName) {
		if (checkDate(introduced, discontinuted)) {
			System.out.println("createComputer");
			try {
				return serviceComputer.create(
						mappeurControlleur.createDTOComputer(name, introduced, discontinuted, companyID, companyName));
			} catch (Exception e) {
				logger.info(e.getMessage());
			} 
		}
		return false;
	}

	/*
	 * essaye de créer un pc
	 */
	public boolean createComputerWithCompanyName(String name, String introduced, String discontinuted, String companyName) {
		if (checkDate(introduced, discontinuted)) {
			System.out.println("createComputerByName");
			try {
				return serviceComputer.createWithCompanyName(
						mappeurControlleur.createDTOComputerWithCompanyName(name, introduced, discontinuted, companyName));
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
		if (checkDate(introduced, discontinuted)) {
			try {
				return serviceComputer.update(mappeurControlleur.createDTOComputer(idComputerAmodifier, name, introduced,
						discontinuted, companyID, ""));
			} catch (ClassNotFoundException | SQLException e) {
				logger.info(e.getMessage());
			}
		}
		return false;
	}

	/*
	 * suppression d'un PC en fonction de son id
	 */
	public boolean supprComputer(String idComputerAsuppr) {
		// suppr pc, true reussi | false echec
		try {
			if (Integer.parseInt(idComputerAsuppr) < 0) {
				return false;
			}
			return serviceComputer.delete(
					mappeurControlleur.createDTOComputer(idComputerAsuppr, "", "2017-07-07", "2017-07-07", "1", ""));
		} catch (Exception e) {
			System.out.println("l'id doit etre un int");
			logger.info( e.getMessage()+ "Erreur de format dans supprComputer de controlleur"+ idComputerAsuppr );
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
			logger.info(e.getMessage()+ "Erreur dans la date avec: "+ str1 + ", "+ str2 );
			return false;
		}
	}
	
	public int countComputer() {
		try {
			return serviceComputer.count();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

}
