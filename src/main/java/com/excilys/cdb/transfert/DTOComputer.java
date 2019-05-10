package com.excilys.cdb.transfert;

public class DTOComputer {
	private String id;
	private String name;
	private String introduced;
	private String discontinuted;
	private String companyId;
	private String companyName;
	
	public DTOComputer(String id, String name, String introduced, String discontinuted, String companyId, String companyName) {
		this.id = id;
		this.name =  name;
		this.introduced = introduced;
		this.discontinuted = discontinuted;
		this.companyId = companyId;
		this.companyName = companyName;
	}
	
	public DTOComputer( String name, String introduced, String discontinuted, String companyId, String companyName) { //sans id
		this.id = "-1";
		this.name =  name;
		this.introduced = introduced;
		this.discontinuted = discontinuted;
		this.companyId = companyId;
		this.companyName = companyName;
	}
	
	public DTOComputer( String name, String introduced, String discontinuted,  String companyID) { //sans id
		this.id = "-1";
		this.name =  name;
		this.introduced = introduced;
		this.discontinuted = discontinuted;
		this.companyId = companyID;
		this.companyName = "";
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
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "DTOComputer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinuted="
				+ discontinuted + ", companyId=" + companyId + ", companyName=" + companyName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((discontinuted == null) ? 0 : discontinuted.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
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
		DTOComputer other = (DTOComputer) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (discontinuted == null) {
			if (other.discontinuted != null)
				return false;
		} else if (!discontinuted.equals(other.discontinuted))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
