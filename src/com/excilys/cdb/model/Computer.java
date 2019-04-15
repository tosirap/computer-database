package com.excilys.cdb.model;

import java.time.LocalDate;

public class Computer {
	public static int idGlobal = 0;
	
	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinuted;
	private int companyId;
	
	
	public Computer(int id, String name, LocalDate intro, LocalDate discon) {
		this.id = id;
		this.name = name;
		this.introduced = intro;
		this.discontinuted = discon;
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
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public LocalDate getDiscontinuted() {
		return discontinuted;
	}
	public void setDiscontinuted(LocalDate discontinuted) {
		this.discontinuted = discontinuted;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	
	
}
