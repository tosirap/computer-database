package com.excilys.cdb.service;

import java.util.ArrayList;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurComputer;

public class ServiceComputer implements ServiceInterface<DTOComputer> {
	
	private DAOComputer daoComputer;
	private MappeurComputer mappeurComputer;
	
	private ServiceComputer() {
		this.daoComputer = DAOComputer.getInstance(); 
		this.mappeurComputer = new MappeurComputer();
	}
	
	/** Instance unique non préinitialisée */
    private static ServiceComputer INSTANCE = null;
     
    /** Point d'accès pour l'instance unique du singleton */
    public static ServiceComputer getInstance()
    {           
        if (INSTANCE == null)
        {   INSTANCE = new ServiceComputer(); 
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
	@Override
	public ArrayList<DTOComputer> listAllElements() {
		ArrayList<Computer> ALComputer = this.daoComputer.findAll();
		ArrayList<DTOComputer> ALDTO = mappeurComputer.computerToDTO(ALComputer);
		return ALDTO;
	}
	
	
	/*
	 * List les elements par pagination
	 */
	
	public ArrayList<DTOComputer> listPagination(int limit, int offset) {
		ArrayList<Computer> ALComputer = this.daoComputer.findPagination(limit, offset);
		ArrayList<DTOComputer> ALDTO = mappeurComputer.computerToDTO(ALComputer);
		return ALDTO;
	}
	
	/*
	 * Recupere un element par un id
	 */
	public DTOComputer listElement(int id) {
		Computer computer = this.daoComputer.find(id);
		DTOComputer dto =  mappeurComputer.computerToDTO(computer);
		return dto;
	}
	
	/*
	 * MaJ d'un element
	 */
	public boolean update(DTOComputer dto) {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		if(testIdCompany(computer.getCompanyId())){
			return this.daoComputer.update(computer);
		}
		else{
			System.out.println("Id de la company introuvable");
			return false;
		}
	}
	
	/*
	 * creation d'un element
	 */
	
	public boolean create(DTOComputer dto) {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		if(testIdCompany(computer.getCompanyId())){
			return this.daoComputer.create(computer);
		}
		else {
			System.out.println("Id de la company introuvable");
			return false;
		}
	}
	
	/*
	 * suppresion d'un element
	 */
	public boolean delete(DTOComputer dto) {
		Computer computer = mappeurComputer.DTOToComputer(dto);
		return this.daoComputer.delete(computer);
	}
	
	public boolean testIdCompany(int id) {
		DAOCompany daoCompany = DAOCompany.getInstance();
		Company comp = daoCompany.find(id);
		if(comp == null || comp.getId() == -1) {
			return false;
		}
		return true;
		
	}
	
}
