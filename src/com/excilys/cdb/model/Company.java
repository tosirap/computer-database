package com.excilys.cdb.model;

public class Company {
	private int id;
	private String name;
	
	
	public Company(int identifiant, String nameCompany) {
		this.id = identifiant;
		this.name = nameCompany;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	
}
