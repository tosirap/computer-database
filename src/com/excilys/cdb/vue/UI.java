package com.excilys.cdb.vue;

import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.cdb.controlleur.Controlleur;

public class UI {

	Controlleur controlleur;
	private Scanner scanner;

	public UI() {
		this.controlleur = new Controlleur();
		scanner = new Scanner(System.in);
	}

	public void affichage(String str) {
		System.out.println(str);
	}

	public int affichageChoixUser() {
		String input = "";
		int res = 0;
		input = null;
		System.out.println("Pour lister les PC, tapez 1");
		System.out.println("Pour lister les companies, tapez 2");
		System.out.println("Pour voir les détails d'un PC, tapez 3");
		System.out.println("Pour insérer un ordinateur, tapez 4");
		System.out.println("Pour mettre à jour un ordinateur, tapez 5");
		System.out.println("Pour supprimer un ordinateur, tapez 6");

		System.out.println("Pour quitter, tapez 7");
		input = scanner.nextLine();
		try {
			res = Integer.parseInt(input);
			return res;
		} catch (Exception e) {
			System.out.println("Tapez un entier !!!");
		}

		// }
		return res;
	}


	public void operations() {
		boolean fini = true;
		while (fini) {
			int val = affichageChoixUser();
			switch (val) {
			case 1:
				operationsListComputer();
				break;
			case 2:
				operationsListCompany();
				break;
			case 3:
				// par id ou par nom?
				operationsComputerDetails();
				break;
			case 4:
				operationsInsertionPC();
				break;
			case 5:
				operationsUpdatePC();
				break;
			case 6:
				operationsUpdatePC();
				break;
			case 7:
				fini = false;
				break;
			default:
				System.out.println("entrez une valeur entre 1 et 7 !");
				break;
			}

		}
	}

	public void operationsListComputer() {
		System.out.println("ici");
		ArrayList<String> stringAL = controlleur.listComputer();
		for (String str : stringAL) {
			System.out.println(str);
		}
	}

	public void operationsListCompany() {
		System.out.println("ici");
		ArrayList<String> stringAL = controlleur.listCompany();
		for (String str : stringAL) {
			System.out.println(str);
		}
	}

	public String operationsComputerDetails() {
		String input = "";
		System.out.println("Entrez l'id du pc à rechercher");
		input = scanner.nextLine();
		String res = controlleur.computerDetails(input); // a partir d'un id
		System.out.println(res);
		return res;
	}

	public void operationsUpdatePC() {
		String id = "";
		String name = "";
		String introduced = "";
		String discontinuted = "";
		String company = "";
		System.out.println("Entrez l'id du pc a changer");
		id = scanner.nextLine();
		System.out.println("Entrez le nom du PC");
		name = scanner.nextLine();
		System.out.println("Entrez la date d'introduction du PC");
		introduced = scanner.nextLine();
		System.out.println("Entrez la date d'enlevage du PC");
		discontinuted = scanner.nextLine();
		System.out.println("Entrez l'id de la company");
		company = scanner.nextLine();
		controlleur.updateComputer(id, name, introduced, discontinuted, company);
	}

	// insertion pc, name, date intro, date discon, string company
	public void operationsInsertionPC() {
		String name = "";
		String introduced = "";
		String discontinuted = "";
		String company = "";
		System.out.println("Entrez le nom du PC");
		name = scanner.nextLine();
		System.out.println("Entrez la date d'introduction du PC");
		introduced = scanner.nextLine();
		System.out.println("Entrez la date d'enlevage du PC");
		discontinuted = scanner.nextLine();
		System.out.println("Entrez l'id de la company");
		company = scanner.nextLine();
		controlleur.createComputer(name, introduced, discontinuted, company);

	}

}
