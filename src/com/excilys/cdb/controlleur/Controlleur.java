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
	
	public Controlleur() {
		this.serviceComputer = new ServiceComputer();
		this.serviceCompany = new ServiceCompany();
		mappeurControlleur = new MappeurControlleur();
	}
	
	/*
	 * 		retourne la liste des pc
	 */
	public ArrayList<String> listComputer(){ //ok
		ArrayList<DTOComputer> dtoAL = serviceComputer.listAllElements();
		System.out.println("ListComputer");
		return mappeurControlleur.dtoComputerALToStringAL(dtoAL);
	}
	
	/*
	 * 		retourne la liste des company
	 */
	public ArrayList<String> listCompany(){ //ok
		ArrayList<DTOCompany> dtoAL = serviceCompany.listAllElements();
		System.out.println("ListCompany");
		return mappeurControlleur.dtoCompanyALToStringAL(dtoAL);
	}
	
	/*public String computerDetails(String namePC) {
		//retourne les infos d'un PC a partir d'un nom
		System.out.println("ComputerDetails1");
		return mappeurControlleur.dtoToString(listElement);
	}*/
	
	public String computerDetails(String idPC) {
		//retourne les infos d'un PC a partir d'un id
		try {
			int id = Integer.parseInt(idPC);
			System.out.println("ComputerDetails2");
			return mappeurControlleur.dtoToString(serviceComputer.listElement(id));
		}
		catch(Exception e) {
			//ou alors on cherche par le nom
			System.out.println("Erreur, veuillez entrez un entier  !! ");
		}
		return "Erreur, veuillez entrez un entier  !! ";
	}
	
	public boolean createComputer(String name, String introduced,String discontinuted, String company ) {
		//insertion pc, true reussi | false echec 
		//ou on retourne id du nouveau pc et -1 si echec
			
		if(checkDate(introduced)&& checkDate(discontinuted)) {
			System.out.println("createComputer");
			return serviceComputer.create(mappeurControlleur.createDTOComputer(name, introduced, discontinuted, company));
		}
		return false;
				
	}

	public boolean updateComputer(String idComputerAmodifier, String name, String introduced,String discontinuted, String companyID ) {
		//update pc, true reussi | false echec 
		if(checkDate(introduced)&& checkDate(discontinuted)) {
			System.out.println("updateComputer");
			return serviceComputer.update(mappeurControlleur.createDTOComputer(idComputerAmodifier, name, introduced,discontinuted,companyID));
		}
		return false;
	}
	public boolean supprComputer(String idComputerAsuppr) {
		//suppr pc, true reussi | false echec 
		try {
			if(Integer.parseInt(idComputerAsuppr) < 0) { 
				return false;
			}
			System.out.println("supprComputer");
			return serviceComputer.delete(mappeurControlleur.createDTOComputer(idComputerAsuppr,"","","",""));
		}
		catch(Exception e) {
			System.out.println("l'id doit etre un int");
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean checkDate(String str) {
		try {
			if(str != null && str != "") {
				Date.valueOf(str);
			}
			return true;
		}catch(Exception e) {
			System.out.println("Probleme dans le format de la date !!");
			e.printStackTrace();
			return false;
		}
	}
	
}
