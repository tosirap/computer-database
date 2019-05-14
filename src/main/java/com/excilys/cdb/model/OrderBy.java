package com.excilys.cdb.model;

public enum OrderBy {
	COMPUTER_ID("computer.id"),
	COMPUTER_NAME("computer.name"),
	COMPUTER_INTRODUCED("computer.introduced"),
	COMPUTER_DISCONTINUED("computer.discontinued"),
	COMPANY_NAME("company.name");

	private String name = "";

	// Constructeur
	OrderBy(String name) {
		this.name = name;
	}

	public String toString() {
		return name;

	}
}
