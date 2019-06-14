package com.excilys.cdb.myException;

public class MauvaiseValueAttendue extends Exception {

	/**
	 * Quand on veut une valeur entre 1 et 8 et on a moins ou plus
	 */
	private static final long serialVersionUID = 1L;
	public MauvaiseValueAttendue(String mess) {
		super("Mauvaise value attendu "+mess);
	}
}
