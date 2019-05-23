package com.excilys.cdb.model;

import java.sql.Date;

public class Computer {

	private int id;
	private String name;
	private Date introduced;
	private Date discontinuted;
	private Company company;

	public Computer(int id, String name, Date intro, Date discon, int companyID, String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = intro;
		this.discontinuted = discon;
		this.company = new Company(companyID, companyName);
	}

	/*
	 * public Computer(String id, String name, String intro, String discon, String
	 * companyID, String companyName) { // constructeur utilisé dans le DAO if (id
	 * == null || id.equals("NULL") || id.equals("")) { this.id = -1; } else {
	 * this.id = Integer.parseInt(id); } this.name = name; if (intro == null ||
	 * intro.equals("")) { this.introduced = null; } else { this.introduced =
	 * Date.valueOf(intro); } if (discon == null || discon.equals("")) {
	 * this.discontinuted = null; } else { this.discontinuted =
	 * Date.valueOf(discon); }
	 * 
	 * if (companyID == null || companyID.equals("NULL") || companyID.equals("")) {
	 * this.company = null; } else { this.company = new
	 * Company(Integer.parseInt(companyID), companyName); } }
	 */

	public Computer(int id, String name, String introduced, String discontinuted, int companyId, String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = Date.valueOf(introduced);
		this.discontinuted = Date.valueOf(discontinuted);
		this.company = new Company(companyId, companyName);
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinuted="
				+ discontinuted + ", companyId=" + company.getId() + ", companyName=" + company.getName() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
