package com.excilys.cdb.myException;

public class DatesNonCoherentes extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	public DatesNonCoherentes(String mess) {
		super("Dates reçu incohérentes "+ mess);
	}
}
