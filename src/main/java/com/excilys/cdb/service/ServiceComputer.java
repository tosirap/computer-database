package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.controlleur.Controlleur;
import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurComputer;

public class ServiceComputer {

	private DAOComputer daoComputer;
	private DAOCompany daoCompany;
	private MappeurComputer mappeurComputer;
	Logger logger = LoggerFactory.getLogger(ServiceComputer.class);

	private ServiceComputer() {
		try {
			this.daoComputer = DAOComputer.getInstance();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mappeurComputer = MappeurComputer.getInstance();
		try {
			this.daoCompany = DAOCompany.getInstance();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** Instance unique non préinitialisée */
	private static ServiceComputer INSTANCE = null;

	/**
	 * Point d'accès pour l'instance unique du singleton
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ServiceComputer getInstance() {
		if (INSTANCE == null) {
			try {
				INSTANCE = new ServiceComputer();
			} catch (Exception e) {

			}
		}
		return INSTANCE;
	}

	public DAOComputer getDaoComputer() {
		return daoComputer;
	}

	public void setDaoComputer(DAOComputer daoComputer) {
		this.daoComputer = daoComputer;
	}

	public MappeurComputer getMappeurComputer() {
		return mappeurComputer;
	}

	public void setMappeurComputer(MappeurComputer mappeurComputer) {
		this.mappeurComputer = mappeurComputer;
	}

	/*
	 * List tous les elements
	 */

	public ArrayList<DTOComputer> listAllElements() {
		ArrayList<Computer> ALComputer = null;
		try {
			ALComputer = this.daoComputer.findAll();
		} catch (Exception e) {
			logger.info(e.getMessage() + "Probleme de listAllElements");
		}

		ArrayList<DTOComputer> ALDTO = mappeurComputer.computerToDTO(ALComputer);
		return ALDTO;
	}

	/*
	 * List les elements par pagination
	 */

	public ArrayList<DTOComputer> listPagination(int limit, int offset) {
		ArrayList<Computer> ALComputer = null;
		try {
			ALComputer = this.daoComputer.findPagination(limit, offset);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		ArrayList<DTOComputer> ALDTO = mappeurComputer.computerToDTO(ALComputer);
		return ALDTO;
	}

	/*
	 * Recupere un element par un id
	 */
	public DTOComputer listElement(int id) {
		Computer computer = null;
		try {
			computer = this.daoComputer.find(id);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		DTOComputer dto = mappeurComputer.computerToDTO(computer);
		return dto;
	}

	/*
	 * MaJ d'un element
	 */
	public boolean update(DTOComputer dto) {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		try {
			if (testIdCompany(computer.getCompanyId())) {
				return this.daoComputer.update(computer);
			} else {
				System.out.println("Id de la company introuvable");
				return false;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/*
	 * creation d'un element
	 */

	public boolean create(DTOComputer dto) {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		try {
			if (testIdCompany(computer.getCompanyId())) {
				return this.daoComputer.create(computer);
			} else {
				System.out.println("Id de la company introuvable");
				return false;
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}

		return false;
	}

	public boolean createWithCompanyName(DTOComputer dto) {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		Company company = null;
		try {
			company = daoCompany.find(dto.getCompanyName());
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		computer.setCompanyId(company.getId());
		try {
			return this.daoComputer.create(computer);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/*
	 * suppresion d'un element
	 */
	public boolean delete(DTOComputer dto) {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		try {
			return this.daoComputer.delete(computer);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return false;
	}

	/*
	 * suppresion d'un element
	 */
	public boolean delete(String id) {
		try {
			int idInt = Integer.parseInt(id);
			return this.daoComputer.delete(idInt);
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		if (comp == null || comp.getId() == -1) {
			return false;
		}
		return true;

	}

	public DTOComputer listElementName(String namePC) {
		Computer computer = null;
		try {
			computer = this.daoComputer.findbyName(namePC);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		DTOComputer dto = mappeurComputer.computerToDTO(computer);
		return dto;
	}

	public ArrayList<DTOComputer> listMultiElementByName(String namePC) {
		ArrayList<Computer> computerList = null;
		try {
			computerList = this.daoComputer.findbyNameMulti(namePC);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		ArrayList<DTOComputer> ALDTO = mappeurComputer.computerToDTO(computerList);
		return ALDTO;
	}

	public int count() {
		try {
			return this.daoComputer.count();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		return 0;
	}

	public ArrayList<DTOComputer> searchComputer(String string) {
		ArrayList<Computer> computerList = null;
		try {
			computerList = this.daoComputer.searchComputer(string);
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
		ArrayList<DTOComputer> ALDTO = mappeurComputer.computerToDTO(computerList);
		return ALDTO;
	}

}
