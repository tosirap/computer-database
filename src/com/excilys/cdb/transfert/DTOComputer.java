package com.excilys.cdb.transfert;

public class DTOComputer {
	private String id;
	private String name;
	private String introduced;
	private String discontinuted;
	private String companyId;
	
	public DTOComputer(String id, String name, String introduced, String discontinuted, String companyId) {
		this.id = id;
		this.name =  name;
		this.introduced = introduced;
		this.discontinuted = discontinuted;
		this.companyId = companyId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinuted() {
		return discontinuted;
	}
	public void setDiscontinuted(String discontinuted) {
		this.discontinuted = discontinuted;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@Override
	public String toString() {
		return "DTOComputer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinuted="
				+ discontinuted + ", companyId=" + companyId + "]";
	}
}
