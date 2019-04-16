package com.excilys.cdb.vue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.excilys.cdb.controlleur.Controlleur;

public class UI {
	
	public void affichage(String str) {
		System.out.println(str);
	}
	
	public static int affichageChoixUser() {
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		String input = "";
		int res = 0;
		while(!ok) {
			System.out.println("Pour lister les PC, tapez 1");
			System.out.println("Pour lister les companies, tapez 2");
			System.out.println("Pour voir les détails d'un PC, tapez 3");
			System.out.println("Pour insérer un ordinateur, tapez 4");
			System.out.println("Pour mettre à jour un ordinateur, tapez 5");
			System.out.println("Pour supprimer un ordinateur, tapez 6");
			
			System.out.println("Pour quitter, tapez 7");
			input = scanner.nextLine();
			res = intFromString(input);
			if(res>0 && res <= 8) {
				ok = true;
			}
			else{
				System.out.println("entrez une valeur entre 1 et 8 !");
			}
			
		}
		scanner.close();
		return res;
	}
	
	public static int intFromString(String s) {
		int res = -1;
		try {
			res = Integer.parseInt(s);
		}catch(NumberFormatException e) {
			System.out.println("entrez un chiffre!!");
			e.printStackTrace();
		}
		return res;
	}
	
	public static LocalDate localDateFromString(String s) {
		LocalDate ld = null;
		try {
			ld = LocalDate.parse(s);
		}catch(DateTimeParseException e) {
			System.out.println("entrez un chiffre!!");
			e.printStackTrace();
		}
		return ld;
	}
	
	public static void operations() {
		boolean ok = true;
		while(ok) {
			int val = affichageChoixUser();
			switch(val) {
				case 1:
					Controlleur.listComputer();
					break;
				case 2:
					Controlleur.listCompany();		
					break;
				case 3:
					//par id ou par nom?
					operationsComputerDetails();
					break;
				case 4:
					operationsInsertionPC();
					break;
				case 5:
					
					break;
				case 6:
					
					break;
				case 7: 
					ok = false;
					break;
			}
		
		}
	}
	
	public static void operationsComputerDetails() {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		System.out.println("Entrez l'id du pc à rechercher");
		input = scanner.nextLine();
		int value = intFromString(input);
		String res = "";
		if(value != -1) {
			res = Controlleur.computerDetails(value); //a partir d'un id
		}
		else {
			res = Controlleur.computerDetails(input);//a partir d'un nom
		}
		System.out.println(res);
		scanner.close();
	}
	
	//insertion pc, name, date intro, date discon, string company
	public static void operationsInsertionPC() {
		Scanner scanner = new Scanner(System.in);
		String name = "";
		LocalDate ld1 = null;
		LocalDate ld2 = null;
		String company = "" ;
		System.out.println("Entrez le nom du PC");
		name = scanner.nextLine();
		boolean okLd1 = false;
		while(!okLd1) {
			System.out.println("Entrez la date d'introduction du PC");
			ld1 = localDateFromString(scanner.nextLine());
			if(ld1 == null) {
				System.out.println("Mauvais format de date!");
			}
			else {
				okLd1 = true;
			}
		}
		
		boolean okLd2 = false;
		while(!okLd2) {
			System.out.println("Entrez la date d'enlevage du PC");
			ld2 = localDateFromString(scanner.nextLine());
			if(ld2 == null) {
				System.out.println("Mauvais format de date!");
			}
			else {
				okLd2 = true;
			}
		}
		System.out.println("Entrez la date d'enlevage");
		company = scanner.nextLine();
		Controlleur.createComputer(name,ld1,ld2,company);
		
		scanner.close();
	}

}
