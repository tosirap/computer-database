package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.cdb.model.Company;


public class DAOCompany  {

	private final String GET = "SELECT * FROM company ";
	private final String GET_ONE = "SELECT * FROM company WHERE id = ?";
	private final String GET_PAGINATION = "SELECT * FROM company ORDER BY company.id LIMIT ?  OFFSET ? ";
	
	protected Connection connect = null;
	
	private DAOCompany() {
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
	
	 /** Instance unique non préinitialisée */
    private static DAOCompany INSTANCE = null;
     
    /** Point d'accès pour l'instance unique du singleton */
    public static DAOCompany getInstance()
    {           
        if (INSTANCE == null)
        {   INSTANCE = new DAOCompany(); 
        }
        return INSTANCE;
    }


	public Company find(int id) {
		// TODO Auto-generated method stub
		Company comp = null;
		try (PreparedStatement preparedStatement =  connect.prepareStatement(GET_ONE)) {
			preparedStatement.setObject(1,id);
			ResultSet result = preparedStatement.executeQuery();
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
		try(PreparedStatement preparedStatement =  connect.prepareStatement(GET_PAGINATION)){
			preparedStatement.setObject(1,limit);
			preparedStatement.setObject(2,offset);
			ResultSet result = preparedStatement.executeQuery();
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
