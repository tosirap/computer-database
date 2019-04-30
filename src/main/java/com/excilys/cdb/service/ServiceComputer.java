package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurComputer;

public class ServiceComputer {

	private DAOComputer daoComputer;
	private MappeurComputer mappeurComputer;

	private ServiceComputer() throws ClassNotFoundException, SQLException {
		this.daoComputer = DAOComputer.getInstance();
		this.mappeurComputer = MappeurComputer.getInstance();
	}

	/** Instance unique non préinitialisée */
	private static ServiceComputer INSTANCE = null;

	/** Point d'accès pour l'instance unique du singleton 
	 * @throws SQLException 
	 * @throws ClassNotFoundException */
	public static ServiceComputer getInstance() throws ClassNotFoundException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new ServiceComputer();
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
	
	public ArrayList<DTOComputer> listAllElements() throws SQLException {
		ArrayList<Computer> ALComputer = this.daoComputer.findAll();
		ArrayList<DTOComputer> ALDTO = mappeurComputer.computerToDTO(ALComputer);
		return ALDTO;
	}

	/*
	 * List les elements par pagination
	 */

	public ArrayList<DTOComputer> listPagination(int limit, int offset) throws SQLException {
		ArrayList<Computer> ALComputer = this.daoComputer.findPagination(limit, offset);
		ArrayList<DTOComputer> ALDTO = mappeurComputer.computerToDTO(ALComputer);
		return ALDTO;
	}

	/*
	 * Recupere un element par un id
	 */
	public DTOComputer listElement(int id) throws SQLException {
		Computer computer = this.daoComputer.find(id);
		DTOComputer dto = mappeurComputer.computerToDTO(computer);
		return dto;
	}

	/*
	 * MaJ d'un element
	 */
	public boolean update(DTOComputer dto) throws SQLException, ClassNotFoundException {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		if (testIdCompany(computer.getCompanyId())) {
			return this.daoComputer.update(computer);
		} else {
			System.out.println("Id de la company introuvable");
			return false;
		}
	}

	/*
	 * creation d'un element
	 */

	public boolean create(DTOComputer dto) throws SQLException, ClassNotFoundException {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		if (testIdCompany(computer.getCompanyId())) {
			return this.daoComputer.create(computer);
		} else {
			System.out.println("Id de la company introuvable");
			return false;
		}
	}

	/*
	 * suppresion d'un element
	 */
	public boolean delete(DTOComputer dto) throws SQLException {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		return this.daoComputer.delete(computer);
	}

	/**
	 * Test si l'idCompany donné en parametre existe dans la BDD
	 * 
	 * @param id
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean testIdCompany(int id) throws SQLException, ClassNotFoundException {
		DAOCompany daoCompany = DAOCompany.getInstance();
		Company comp = daoCompany.find(id);
		if (comp == null || comp.getId() == -1) {
			return false;
		}
		return true;

	}

	public DTOComputer listElementName(String namePC) throws SQLException {
		Computer computer = this.daoComputer.findbyName(namePC);
		DTOComputer dto = mappeurComputer.computerToDTO(computer);
		return dto;
	}

	public ArrayList<DTOComputer> listMultiElementByName(String namePC) throws SQLException {
		ArrayList<Computer> computerList = this.daoComputer.findbyNameMulti(namePC);
		ArrayList<DTOComputer> ALDTO = mappeurComputer.computerToDTO(computerList);
		return ALDTO;
	}

	public int count() throws SQLException {
		return this.daoComputer.count();
	}

}
