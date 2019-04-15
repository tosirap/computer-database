package com.excilys.cdb.controlleur;

import java.time.LocalDate;
import java.util.ArrayList;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class Controlleur {
	
	public ArrayList<Computer> listComputer(){
		ArrayList<Computer> res = null;
		//retourne la liste des pc
		
		return res;
	}
	public ArrayList<Company> listCompany(){
		ArrayList<Company> res = null;
		//retourne la liste des company
		
		return res;
	}
	
	public String computerDetails(String namePC) {
		String s = "";
		//rtetourne les infos d'un PC
		return s;
	}
	
	public boolean createComputer(String name, LocalDate introduced,LocalDate discontinued, int companyID ) {
		//insertion pc, true reussi | false echec 
		//ou on retourne id du nouveau pc et -1 si echec
		return false;
	}
}
