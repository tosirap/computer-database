package com.excilys.cdb.controlleur;

import java.time.LocalDate;
import java.util.ArrayList;


public class Controlleur {
	
	public static ArrayList<String> listComputer(){
		ArrayList<String> res = null;
		//retourne la liste des pc
		System.out.println("ListComputer");
		return res;
	}
	public static ArrayList<String> listCompany(){
		ArrayList<String> res = null;
		//retourne la liste des company
		System.out.println("ListCompany");
		return res;
	}
	
	public static String computerDetails(String namePC) {
		String s = "";
		//retourne les infos d'un PC a partir d'un nom
		System.out.println("ComputerDetails1");
		return s;
	}
	
	public static String computerDetails(int idPC) {
		String s = "";
		//retourne les infos d'un PC a partir d'un id
		System.out.println("ComputerDetails2");
		return s;
	}
	
	public static String createComputer(String name, LocalDate introduced,LocalDate discontinued, String company ) {
		//insertion pc, true reussi | false echec 
		//ou on retourne id du nouveau pc et -1 si echec
		String s = "";
		System.out.println("createComputer");
		return s;
	}
	public static String updateComputer(int idComputerAmodifier, String name, LocalDate introduced,LocalDate discontinued, String companyID ) {
		//update pc, true reussi | false echec 
		String s = "";
		System.out.println("updateComputer");
		return s;
	}
	public static String supprComputer(int idComputerAsuppr) {
		//suppr pc, true reussi | false echec 
		String s = "";
		if(idComputerAsuppr < 0) {
			return "Impossible de supprimer un PC d'id négatif ";
		}
		System.out.println("supprComputer");
		return s;
	}
	
	
}
