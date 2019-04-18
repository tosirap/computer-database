package com.excilys.cdb.model;

import java.sql.Date;

public class Computer {
	public static int idGlobal = 0;
	
	private int id;
	private String name;
	private Date introduced;
	private Date discontinuted;
	private int companyId;
	private String companyName;
	
	public Computer() {
		this.id=-1;
		this.name = "";
		this.introduced = null;
		this.discontinuted = null;
		this.companyId=-1;
		this.companyName = "";
	}
	
	public Computer(int id, String name, Date intro, Date discon, int companyID, String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = intro;
		this.discontinuted = discon;
		this.companyId = companyID; 
		this.companyName = companyName;
	}
	
	public Computer(String id, String name, String intro, String discon, String companyID, String companyName) { //constructeur utilisé dans le DAO
		if(id == null || id.equals("NULL")||id.equals("")) {
			this.id = -1;
		}
		else {
			this.id = Integer.parseInt(id);
		}
		this.name = name;
		if(intro == null || intro.equals("")) {
			this.introduced = null;
		}
		else{
			this.introduced = Date.valueOf(intro);
		}
		if(discon == null || discon.equals("") ) {
			this.discontinuted = null;
		}
		else{
			this.discontinuted = Date.valueOf(discon);
		}
		
		
		if(companyID == null || companyID.equals("NULL")||companyID.equals("")) {
			this.companyId = -1;
		}
		else {
			this.companyId = Integer.parseInt(companyID); 
		}
		this.companyName = companyName;
		
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
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getDiscontinuted() {
		return discontinuted;
	}
	public void setDiscontinuted(Date discontinuted) {
		this.discontinuted = discontinuted;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinuted="
				+ discontinuted + ", companyId=" + companyId + ", companyName=" + companyName + "]";
	}
	

	
	
}
