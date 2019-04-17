package com.excilys.cdb.service;

import java.util.ArrayList;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.transfert.DTOCompany;
import com.excilys.cdb.transfert.MappeurCompany;

public class ServiceCompany implements ServiceInterface<DTOCompany>{
	private DAOCompany daoCompany;
	private MappeurCompany mappeurCompany;
	
	public ServiceCompany() {
		this.daoCompany = new DAOCompany();
		mappeurCompany = new MappeurCompany();
	}
	
	public DAOCompany getDaoCompany() {
		return daoCompany;
	}

	public void setDaoCompany(DAOCompany daoCompany) {
		this.daoCompany = daoCompany;
	}
	
	public MappeurCompany getMappeurCompany() {
		return mappeurCompany;
	}

	public void setMappeurCompany(MappeurCompany mappeurCompany) {
		this.mappeurCompany = mappeurCompany;
	}
	
	/*
	 * Appelle la fonction findAll du DAO et renvoie une list de DTO au controlleur
	 */
	@Override
	public ArrayList<DTOCompany> listAllElements() { //ok
		// TODO Auto-generated method stub
		ArrayList<Company> ALCompany = this.daoCompany.findAll();
		ArrayList<DTOCompany> ALDTO = mappeurCompany.companyToDTO(ALCompany);
		return ALDTO;
	}

	

	

}
