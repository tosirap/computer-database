package com.excilys.cdb.service;

import java.util.ArrayList;

import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurComputer;

public class ServiceComputer implements ServiceInterface<DTOComputer> {
	
	private DAOComputer daoComputer;
	private MappeurComputer mappeurComputer;
	
	
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
	@Override
	public ArrayList<DTOComputer> listAllElements() {
		ArrayList<Computer> ALComputer = this.daoComputer.findAll();
		ArrayList<DTOComputer> ALDTO = mappeurComputer.computerToDTO(ALComputer);
		return ALDTO;
	}
	
	/*
	 * Recupere un element par un id
	 */
	public DTOComputer listElement(String id) {
		Computer computer = this.daoComputer.find(Integer.parseInt(id));
		DTOComputer dto =  mappeurComputer.computerToDTO(computer);
		return dto;
	}
	
	/*
	 * MaJ d'un element
	 */
	public boolean update(DTOComputer dto) {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		return this.daoComputer.update(computer);
	}
	
	/*
	 * creation d'un element
	 */
	public boolean create(DTOComputer dto) {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		return this.daoComputer.create(computer);
	}
	
	/*
	 * suppresion d'un element
	 */
	public boolean delete(DTOComputer dto) {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		return this.daoComputer.delete(computer);
	}
	
}
