package com.excilys.cdb.vue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
	
	public void affichage(String str) {
		System.out.println(str);
	}
	
	public int affichageChoixUser() {
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		int input =-1;
		while(!ok) {
			System.out.println("Pour lister les PC, tapez 1");
			System.out.println("Pour lister les companies, tapez 2");
			System.out.println("Pour voir les détails d'un PC, tapez 3");
			System.out.println("Pour insérer un ordinateur, tapez 4");
			System.out.println("Pour mettre à jour un ordinateur, tapez 5");
			System.out.println("Pour supprimer un ordinateur, tapez 6");
			
			try {
				input = scanner.nextInt();
				if(input>0 && input <= 7) {
					ok = true;
				}
			}catch(InputMismatchException e) {
				e.printStackTrace();
			}
		}
		scanner.close();
		return input;
	}
	
	public void operations() {
		int val = affichageChoixUser();
		
		switch(val) {
			case 1:
				
				break;
			case 2:
							
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				
				break;
		}
	}
}
