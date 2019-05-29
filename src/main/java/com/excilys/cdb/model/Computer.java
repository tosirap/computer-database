package com.excilys.cdb.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="computer")
public class Computer {

	@Id
	private Long id;
	@Column
	private String name;
	@Column(columnDefinition = "TIMESTAMP")
	private Date introduced;
	@Column(columnDefinition = "TIMESTAMP")
	private Date discontinued;
	@ManyToOne
	private Company company;

	public Computer(Long id, String name, Date intro, Date discon, Long companyID, String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = intro;
		this.discontinued = discon;
		this.company = new Company(companyID, companyName);
	}

	public Computer(Long id, String name, String introduced, String discontinuted, Long companyId, String companyName) {
		this.id = id;
		this.name = name;
		if (introduced != null && !introduced.trim().isEmpty()) {
			this.introduced = Date.valueOf(introduced);
		} else {
			this.introduced = null;
		}
		if (discontinuted != null && !discontinuted.trim().isEmpty()) {
			this.discontinued = Date.valueOf(discontinuted);
		} else {
			this.discontinued = null;
		}
		this.company = new Company(companyId, companyName);
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinuted() {
		return discontinued;
	}

	public void setDiscontinuted(Date discontinuted) {
		this.discontinued = discontinuted;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinuted="
				+ discontinued + ", companyId=" + company.getId() + ", companyName=" + company.getName() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
