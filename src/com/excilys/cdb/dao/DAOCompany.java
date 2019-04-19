package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.cdb.model.Company;


public class DAOCompany  {

	private final String GET = "SELECT * FROM company ";
	private final String GET_ONE = "SELECT * FROM company WHERE id = ";
	
	protected Connection connect = null;
	
	public DAOCompany() {
		if (this.connect == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC", "admincdb",
						"qwerty1234");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Erreur class");
				e.printStackTrace();
			}
		}
	}



	public Company find(int id) {
		// TODO Auto-generated method stub
		Company comp = new Company();
		try {
			ResultSet result = this.connect.createStatement(
			        ResultSet.TYPE_SCROLL_INSENSITIVE, 
			        ResultSet.CONCUR_READ_ONLY
			      ).executeQuery(GET_ONE + id);
			        if(result.first())
			          comp = new Company(id, result.getString("name"));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}


	public ArrayList<Company> findAll(){  //fonctionne
		ArrayList<Company> retAL = new ArrayList<Company>();
		Company tmp;
		try{
			ResultSet result = connect.createStatement().executeQuery(GET);
			while(result.next()) {
				tmp = new Company(result.getInt("id"), result.getString("name"));
				retAL.add(tmp);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
		return retAL;
	}

	
	public ArrayList<Company> findPagination(int limit, int offset) {
		// TODO Auto-generated method stub
		ArrayList<Company> retAL = new ArrayList<Company>();
		Company tmp;
		try{
			ResultSet result = connect.createStatement().executeQuery(GET+"ORDER BY company.id" + " LIMIT "+ limit+" OFFSET "+offset);
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
