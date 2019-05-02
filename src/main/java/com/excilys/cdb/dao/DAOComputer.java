package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.excilys.cdb.model.Computer;

public class DAOComputer {

	private final String CREATE = "INSERT INTO computer(id ,name, introduced, discontinued, company_id) "
			+ "VALUES (NULL , ?, ?,?,?)";
	private final String DELETE = "DELETE FROM computer WHERE id = ? ";
	private final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ? ";
	private final String GET = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id ";
	private final String GET_ONE = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ? ";
	private final String GET_PAGINATION = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.id LIMIT ?  OFFSET ? ";
	private final String GET_ONE_BY_NAME = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name = ? LIMIT 1";
	private final String GET_MULTI_BY_NAME = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name = ? ";
	private final String COUNT = "SELECT COUNT(*) AS total FROM computer";
	
	protected Connection connect = null;

	private DAOComputer() throws SQLException, ClassNotFoundException {
		if (this.connect == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC", "admincdb", "qwerty1234");
		}
	}

	/** Instance unique non préinitialisée */
	private static DAOComputer INSTANCE = null;

	/**
	 * Point d'accès pour l'instance unique du singleton
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static DAOComputer getInstance() throws ClassNotFoundException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new DAOComputer();
		}
		return INSTANCE;
	}

	public boolean create(Computer computer) throws SQLException { // fonctionne
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = connect.prepareStatement(CREATE);
		preparedStatement.setString(1, computer.getName());
		preparedStatement.setDate(2, computer.getIntroduced());
		preparedStatement.setDate(3, computer.getDiscontinuted());
		if (computer.getCompanyId() == 0 || computer.getCompanyId() == -1) {
			preparedStatement.setObject(4, null);
		} else {
			preparedStatement.setInt(4, computer.getCompanyId());
		}
		preparedStatement.executeUpdate();
		preparedStatement.close();
		return true;

	}
	


	public boolean delete(int id) throws SQLException {
		PreparedStatement preparedStatement = connect.prepareStatement(DELETE);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		return true;

	}

	public boolean delete(Computer computer) throws SQLException { // fonctionne
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = connect.prepareStatement(DELETE);
		preparedStatement.setInt(1, computer.getId());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		return true;

	}

	public boolean update(Computer computer) throws SQLException { // fonctionne
		// TODO Auto-generated method stub

		Computer cpt = find(computer.getId());
		if (cpt.getId() <= 0) {
			return false; // rien n'a update, il n'y a pas de pc
		}

		PreparedStatement preparedStatement = connect.prepareStatement(UPDATE);
		preparedStatement.setString(1, computer.getName());
		preparedStatement.setDate(2, computer.getIntroduced());
		preparedStatement.setDate(3, computer.getDiscontinuted());
		if (computer.getCompanyId() == 0 || computer.getCompanyId() == -1) {
			preparedStatement.setObject(4, null);
		} else {
			preparedStatement.setInt(4, computer.getCompanyId());
		}
		preparedStatement.setInt(5, computer.getId());
		preparedStatement.executeUpdate();
		return true;
	}

	public ArrayList<Computer> findAll() throws SQLException { // fonctionne
		ArrayList<Computer> retAL = new ArrayList<Computer>();
		Computer tmp;

		ResultSet result = connect.createStatement().executeQuery(GET);
		while (result.next()) {
			tmp = new Computer(result.getInt("id"), result.getString("name"), result.getDate("introduced"),
					result.getDate("discontinued"), result.getInt("company_id"), result.getString("company.name"));
			retAL.add(tmp);
		}
		result.close();

		return retAL;
	}

	public Computer find(int id) throws SQLException { // fonctionne
		// TODO Auto-generated method stub
		Computer comp = new Computer();
		PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE);
		preparedStatement.setInt(1, id);
		ResultSet result = preparedStatement.executeQuery();
		if (result.first()) {
			comp = new Computer(result.getInt("id"), result.getString("name"), result.getDate("introduced"),
					result.getDate("discontinued"), result.getInt("company_id"), result.getString("company.name"));
		}
		result.close();

		return comp;
	}

	public ArrayList<Computer> findPagination(int limit, int offset) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Computer> retAL = new ArrayList<Computer>();
		Computer tmp;
		if (limit < 0 || offset < 0) {
			return retAL;
		}
		PreparedStatement preparedStatement = connect.prepareStatement(GET_PAGINATION);
		preparedStatement.setInt(1, limit);
		preparedStatement.setInt(2, offset);
		ResultSet result = preparedStatement.executeQuery();
		while (result.next()) {
			tmp = new Computer(result.getInt("id"), result.getString("name"), result.getDate("introduced"),
					result.getDate("discontinued"), result.getInt("company_id"), result.getString("company.name"));
			retAL.add(tmp);
		}
		result.close();

		return retAL;
	}

	public Computer findbyName(String namePC) throws SQLException{
		Computer comp = new Computer();
		PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE_BY_NAME);
		preparedStatement.setString(1, namePC);
		ResultSet result = preparedStatement.executeQuery();
		if (result.first()) {
			comp = new Computer(result.getInt("id"), result.getString("name"), result.getDate("introduced"),
					result.getDate("discontinued"), result.getInt("company_id"), result.getString("company.name"));
		}
		result.close();
		return comp;
	}

	public ArrayList<Computer> findbyNameMulti(String namePC) throws SQLException {
		ArrayList<Computer> retAL = new ArrayList<>();
		Computer tmp;
		ResultSet result = connect.createStatement().executeQuery(GET_MULTI_BY_NAME);
		while (result.next()) {
			tmp = new Computer(result.getInt("id"), result.getString("name"), result.getDate("introduced"),
					result.getDate("discontinued"), result.getInt("company_id"), result.getString("company.name"));
			retAL.add(tmp);
		}
		result.close();
		
		return retAL;
	}

	public int count() throws SQLException {
		int i = 0;
	
		ResultSet result = connect.createStatement().executeQuery(COUNT);
		if (result.first()) {
			i= result.getInt("total");
		}
		return i;
	}

	
}
