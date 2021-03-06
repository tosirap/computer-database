package com.excilys.cdb.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;



public class DTOComputer {
	@PositiveOrZero
	private Long id;
	
	@NotEmpty
	private String name;
	@Pattern(regexp=DATE_PATTERN)
	private String introduced;
	@Pattern(regexp=DATE_PATTERN)
	private String discontinuted;
	@PositiveOrZero
	private Long companyId;
	@NotEmpty
	private String companyName;

	
	private static final String DATE_PATTERN = "^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
	
	public DTOComputer(Long id, String name, String introduced, String discontinuted, Long companyId,
			String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinuted = discontinuted;
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public DTOComputer(String name, String introduced, String discontinuted, Long companyId, String companyName) { // sans
																													// id
		this.id = (long)0;
		this.name = name;
		this.introduced = introduced;
		this.discontinuted = discontinuted;
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public DTOComputer(String name, String introduced, String discontinuted, Long companyID) { // sans id
		this.id = (long)0;
		this.name = name;
		this.introduced = introduced;
		this.discontinuted = discontinuted;
		this.companyId = companyID;
		this.companyName = "Entreprise";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((discontinuted == null) ? 0 : discontinuted.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
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
		if (companyId != other.companyId)
			return false;
		if (discontinuted == null) {
			if (other.discontinuted != null)
				return false;
		} else if (!discontinuted.equals(other.discontinuted))
			return false;
		if (id != other.id)
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
