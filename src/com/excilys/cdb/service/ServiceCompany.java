package com.excilys.cdb.service;

import java.util.ArrayList;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.transfert.DTOCompany;
import com.excilys.cdb.transfert.DTOComputer;
import com.excilys.cdb.transfert.MappeurCompany;

public class ServiceCompany {
	private DAOCompany daoCompany;
	private MappeurCompany mappeurCompany;
	
	private ServiceCompany() {
		this.daoCompany = DAOCompany.getInstance();
		mappeurCompany = new MappeurCompany();
	}
	
	
	/** Instance unique non préinitialisée */
    private static ServiceCompany INSTANCE = null;
     
    /** Point d'accès pour l'instance unique du singleton */
    public static ServiceCompany getInstance()
    {           
        if (INSTANCE == null)
        {   INSTANCE = new ServiceCompany(); 
        }
        return INSTANCE;
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
	
	public ArrayList<DTOCompany> listAllElements() { //ok
		// TODO Auto-generated method stub
		ArrayList<Company> ALCompany = this.daoCompany.findAll();
		ArrayList<DTOCompany> ALDTO = mappeurCompany.companyToDTO(ALCompany);
		return ALDTO;
	}

	/*
	 * List les elements par pagination
	 */
	public ArrayList<DTOCompany> listPagination(int limit, int offset) {
		ArrayList<Company> ALCompany = this.daoCompany.findPagination(limit, offset);
		ArrayList<DTOCompany> ALDTO = mappeurCompany.companyToDTO(ALCompany);
		return ALDTO;
	}

	

}
