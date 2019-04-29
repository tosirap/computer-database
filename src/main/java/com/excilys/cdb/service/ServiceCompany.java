package com.excilys.cdb.service;

import java.sql.SQLException;
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
	
	private ServiceCompany() throws ClassNotFoundException, SQLException {
		this.daoCompany = DAOCompany.getInstance();
		mappeurCompany = MappeurCompany.getInstance();
	}
	
	
	/** Instance unique non préinitialisée */
    private static ServiceCompany INSTANCE = null;
     
    /** Point d'accès pour l'instance unique du singleton 
     * @throws SQLException 
     * @throws ClassNotFoundException */
    public static ServiceCompany getInstance() throws ClassNotFoundException, SQLException
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
	
	public ArrayList<DTOCompany> listAllElements() throws SQLException { //ok
		// TODO Auto-generated method stub
		ArrayList<Company> ALCompany = this.daoCompany.findAll();
		ArrayList<DTOCompany> ALDTO = mappeurCompany.companyToDTO(ALCompany);
		return ALDTO;
	}

	/*
	 * List les elements par pagination
	 */
	public ArrayList<DTOCompany> listPagination(int limit, int offset) throws SQLException {
		ArrayList<Company> ALCompany = this.daoCompany.findPagination(limit, offset);
		ArrayList<DTOCompany> ALDTO = mappeurCompany.companyToDTO(ALCompany);
		return ALDTO;
	}

	

}
