package com.excilys.cdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Company;

public class DAOCompany extends DAO<Company> {

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
			      ).executeQuery("SELECT * FROM company WHERE id = " + id);
			        if(result.first())
			          comp = new Company(id, result.getString("name"));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}

}
