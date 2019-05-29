package com.excilys.cdb.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Company;

@Component
public class ServiceCompany {
	private DAOCompany daoCompany;
	private DAOComputer daoComputer;
	static Logger logger = LoggerFactory.getLogger(ServiceComputer.class);

	public ServiceCompany(DAOCompany daoCompany, DAOComputer daoComputer) {
		this.daoCompany = daoCompany;
		this.daoComputer = daoComputer;
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
		ArrayList<Company> ALCompany = null;
		try {
			ALCompany = this.daoCompany.findAll();
		} catch (DataAccessException e) {
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
			logger.info(e.getMessage());
		}
		return ALCompany;
	}

	public Company getOneCompany(Long id) {
		Company company = null;
		try {
			company = this.daoCompany.find(id);
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		return company;
	}

	@Transactional
	public boolean deleteCompany(Long id) {
		// TODO Auto-generated method stub
		try {
			return (daoCompany.delete(id) && daoComputer.deleteByCompanyId(id));
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return false;
	}

}
