package com.excilys.cdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.cdb.model.Company;


public class DAOCompany extends DAO<Company> {

	private final String GET = "SELECT * FROM company ";
	
	public DAOCompany() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Company find(int id) {
		// TODO Auto-generated method stub
		Company comp = new Company();
		try {
			ResultSet result = this.connect.createStatement(
			        ResultSet.TYPE_SCROLL_INSENSITIVE, 
			        ResultSet.CONCUR_READ_ONLY
			      ).executeQuery(GET+" WHERE id = " + id);
			        if(result.first())
			          comp = new Company(id, result.getString("name"));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}

	@Override
	public ArrayList<Company> findAll(){  //fonctionne
		ArrayList<Company> retAL = new ArrayList<Company>();
		Company tmp;
		try{
			ResultSet result = super.connect.createStatement().executeQuery(GET);
			while(result.next()) {
				tmp = new Company(result.getInt("id"), result.getString("name"));
				retAL.add(tmp);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
		return retAL;
	}

	@Override
	public ArrayList<Company> findPagination(int limit, int offset) {
		// TODO Auto-generated method stub
		ArrayList<Company> retAL = new ArrayList<Company>();
		Company tmp;
		try{
			ResultSet result = super.connect.createStatement().executeQuery(GET+"ORDER BY company.id" + " LIMIT "+ limit+" OFFSET "+offset);
			while(result.next()) {
				tmp = new Company(result.getInt("id"), result.getString("name"));
				retAL.add(tmp);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return retAL;
	}
}
