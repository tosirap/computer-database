package com.excilys.cdb.controlleur;

import java.sql.Date;
import java.util.ArrayList;

import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.service.ServiceComputer;
import com.excilys.cdb.transfert.DTOCompany;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurControlleur;

public class Controlleur {

	ServiceComputer serviceComputer;
	ServiceCompany serviceCompany;
	MappeurControlleur mappeurControlleur;

	private Controlleur() {
		this.serviceComputer = ServiceComputer.getInstance();
		this.serviceCompany = ServiceCompany.getInstance();
		mappeurControlleur = MappeurControlleur.getInstance();
	}
	
	/** Instance unique pré-initialisée */
    private static Controlleur INSTANCE = new Controlleur();
     
    /** Point d'accès pour l'instance unique du singleton */
    public static Controlleur getInstance()
    {   return INSTANCE;
    }
	/*
	 * retourne la liste des pc
	 */
	public ArrayList<String> listComputer() { // ok
		ArrayList<DTOComputer> dtoAL = serviceComputer.listAllElements();
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
		}
		return null;

	}

	/*
	 * retourne la liste des company
	 */
	public ArrayList<String> listCompany() { // ok
		ArrayList<DTOCompany> dtoAL = serviceCompany.listAllElements();
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
			System.out.println("Erreur, veuillez entrez un entier  !! ");
		}
		return "Erreur, veuillez entrez un entier  !! ";
	}

	/*
	 * essaye de créer un pc
	 */
	public boolean createComputer(String name, String introduced, String discontinuted, String companyID,
			String companyName) {
		if (checkDate(introduced, discontinuted)) {
			System.out.println("createComputer");
			return serviceComputer.create(
					mappeurControlleur.createDTOComputer(name, introduced, discontinuted, companyID, companyName));
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
			return serviceComputer.update(mappeurControlleur.createDTOComputer(idComputerAmodifier, name, introduced,
					discontinuted, companyID, ""));
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
			e.printStackTrace();
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
				}
			} else if (str1 != null && !str1.equals("")) {
				Date.valueOf(str1);
			} else if (str2 != null && !str2.equals("")) {
				Date.valueOf(str2);
			}

			return true;
		} catch (Exception e) {
			System.out.println("Probleme dans le format de la date !!");
			return false;
		}
	}

}