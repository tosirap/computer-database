package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.model.Company;

@Component
public class ServiceCompany {
	private DAOCompany daoCompany;
	static Logger logger = LoggerFactory.getLogger(ServiceComputer.class);

	public ServiceCompany(DAOCompany daoCompany) {
		this.daoCompany = daoCompany;
	}

	public DAOCompany getDaoCompany() {
		return daoCompany;
	}

	public void setDaoCompany(DAOCompany daoCompany) {
		this.daoCompany = daoCompany;
	}
	/*
	 * Appelle la fonction findAll du DAO et renvoie une list de DTO au controlleur
	 */

	public ArrayList<Company> listAllElements() { // ok
		// TODO Auto-generated method stub
		ArrayList<Company> ALCompany = null;
		try {
			ALCompany = this.daoCompany.findAll();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return ALCompany;
	}

	/*
	 * List les elements par pagination
	 */
	public ArrayList<Company> listPagination(int limit, int offset) {
		ArrayList<Company> ALCompany = null;
		try {
			ALCompany = this.daoCompany.findPagination(limit, offset);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return ALCompany;
	}

	public Company getOneCompany(int id) {
		Company company = null;
		try {
			company = this.daoCompany.find(id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return company;
	}

}
