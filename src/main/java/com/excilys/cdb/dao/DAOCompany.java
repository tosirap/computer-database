package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.excilys.cdb.model.Company;

public class DAOCompany {

	private final String GET = "SELECT * FROM company ";
	private final String GET_ONE = "SELECT * FROM company WHERE id = ?";
	private final String GET_PAGINATION = "SELECT * FROM company ORDER BY company.id LIMIT ?  OFFSET ? ";

	protected Connection connect = null;

	private DAOCompany() throws ClassNotFoundException, SQLException {
		if (this.connect == null) {

			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC", "admincdb", "qwerty1234");

		}
	}

	/** Instance unique non préinitialisée */
	private static DAOCompany INSTANCE = null;

	/** Point d'accès pour l'instance unique du singleton 
	 * @throws SQLException 
	 * @throws ClassNotFoundException */
	public static DAOCompany getInstance() throws ClassNotFoundException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new DAOCompany();
		}
		return INSTANCE;
	}

	public Company find(int id) throws SQLException {
		// TODO Auto-generated method stub
		Company comp = null;
		PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE);
		preparedStatement.setInt(1, id);
		ResultSet result = preparedStatement.executeQuery();
		if (result.first())
			comp = new Company(id, result.getString("name"));
		preparedStatement.close();
		result.close();
		return comp;
	}

	public ArrayList<Company> findAll() throws SQLException { // fonctionne
		ArrayList<Company> retAL = new ArrayList<Company>();
		Company tmp;

		ResultSet result = connect.createStatement().executeQuery(GET);
		while (result.next()) {
			tmp = new Company(result.getInt("id"), result.getString("name"));
			retAL.add(tmp);
		}
		result.close();

		return retAL;
	}

	public ArrayList<Company> findPagination(int limit, int offset) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Company> retAL = new ArrayList<Company>();
		Company tmp;
		if (limit < 0 || offset < 0) {
			return retAL;
		}
		PreparedStatement preparedStatement = connect.prepareStatement(GET_PAGINATION);
		preparedStatement.setInt(1, limit);
		preparedStatement.setInt(2, offset);
		ResultSet result = preparedStatement.executeQuery();
		while (result.next()) {
			tmp = new Company(result.getInt("id"), result.getString("name"));
			retAL.add(tmp);
		}

		return retAL;
	}
}
