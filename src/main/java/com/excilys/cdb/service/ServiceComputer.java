package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.OrderBy;


@Component
public class ServiceComputer {

	private DAOComputer daoComputer;
	private DAOCompany daoCompany;
	static Logger logger = LoggerFactory.getLogger(ServiceComputer.class);

	public ServiceComputer(DAOComputer daoComputer,DAOCompany daoCompany) {
		super();
		this.daoComputer = daoComputer;
		this.daoCompany = daoCompany;
	}


	public DAOComputer getDaoComputer() {
		return daoComputer;
	}

	public void setDaoComputer(DAOComputer daoComputer) {
		this.daoComputer = daoComputer;
	}


	/*
	 * List tous les elements
	 */

	public ArrayList<Computer> listAllElements() {
		ArrayList<Computer> ALComputer = null;
		try {
			ALComputer = this.daoComputer.findAll();
		} catch (DataAccessException e) {
			logger.info(e.getMessage() + "Probleme de listAllElements");
		}
		return ALComputer;
	}

	/*
	 * List les elements par pagination
	 */

	public ArrayList<Computer> listPagination(int limit, int offset, OrderBy orderby, boolean b) {
		ArrayList<Computer> ALComputer = null;
		try {
			ALComputer = this.daoComputer.findPagination(limit, offset, orderby, b);
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		return ALComputer;
	}

	/*
	 * Recupere un element par un id
	 */
	public Computer listElement(int id) {
		Computer computer = null;
		try {
			computer = this.daoComputer.find(id);
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		
		return computer;
	}

	/*
	 * MaJ d'un element
	 */
	public boolean update(Computer computer) {
		try {
			if (testIdCompany(computer.getCompany().getId())) {
				return this.daoComputer.update(computer);
			} else {
				return false;
			}
		} catch (DataAccessException e) {
			logger.info(e.getMessage()+" id de la company introuvable");
		}
		return false;
	}

	/*
	 * creation d'un element
	 */

	public boolean create(Computer computer) {
		try {
			if (testIdCompany(computer.getCompany().getId())) {
				return this.daoComputer.create(computer);
			} else {
				return false;
			}
		} catch (DataAccessException e) {
			logger.info(e.getMessage()+ " Id de la company introuvable");
		}

		return false;
	}

	public boolean createWithCompanyName(Computer computer) {
		Company company = null;
		try {
			company = daoCompany.find(computer.getCompany().getName());
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		computer.setCompany(company);
		try {
			return this.daoComputer.create(computer);
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/*
	 * suppresion d'un element
	 */
	public boolean delete(Computer computer) {
		try {
			return this.daoComputer.delete(computer);
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/*
	 * suppresion d'un element par son id
	 */
	public boolean delete(String id) {
		try {
			int idInt = Integer.parseInt(id);
			return this.daoComputer.delete(idInt);
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * Test si l'idCompany donné en parametre existe dans la BDD
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean testIdCompany(int id) {
		Company comp = null;
		try {
			comp = daoCompany.find(id);
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		if (comp == null || comp.getId() == -1) {
			return false;
		}
		return true;

	}

	public Computer listElementName(String namePC) {
		Computer computer = null;
		try {
			computer = this.daoComputer.findbyName(namePC);
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		return computer;
	}

	/*public ArrayList<Computer> listMultiElementByName(String namePC) {
		ArrayList<Computer> computerList = null;
		try {
			computerList = this.daoComputer.findbyNameMulti(namePC);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return computerList;
	}*/

	public int count() {
		try {
			return this.daoComputer.count();
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

	public ArrayList<Computer> searchComputer(String string, int limit, int offset, OrderBy orderby, boolean b) {
		ArrayList<Computer> computerList = null;
		try {
			computerList = this.daoComputer.searchComputer(string, limit, offset, orderby, b);
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		return computerList ;
	}
	
	public int searchComputerCount(String string) {
		int res = 0;
		try {
			res = this.daoComputer.searchComputerCount(string);
		} catch (DataAccessException e) {
			logger.info(e.getMessage());
		}
		return res ;
	}

	

}
