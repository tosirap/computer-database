package com.excilys.cdb.dao;

import java.sql.Connection;
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
	private final String GET = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id ";
	private final String GET_ONE = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ? ";
	private final String GET_PAGINATION = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.id LIMIT ?  OFFSET ? ";
	private final String GET_ONE_BY_NAME = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name = ? LIMIT 1";
	private final String GET_MULTI_BY_NAME = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
			+ " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name = ? ";
	private final String COUNT = "SELECT COUNT(*) AS total FROM computer";
	private final String SEARCH = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer LEFT JOIN "
			+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY computer.id LIMIT ? OFFSET ? ";
	private final String SEARCH_COUNT = "SELECT COUNT(*) AS total FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE "
			+ "computer.name LIKE ? OR company.name LIKE ?";
	
	
	private DAOComputer() {

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
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(CREATE);) {
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setDate(2, computer.getIntroduced());
			preparedStatement.setDate(3, computer.getDiscontinuted());
			if (computer.getCompany() == null || computer.getCompany().getId() == 0
					|| computer.getCompany().getId() == -1) {
				preparedStatement.setObject(4, null);
			} else {
				preparedStatement.setInt(4, computer.getCompany().getId());
			}
			preparedStatement.executeUpdate();
		}
		return true;
	}

	public boolean delete(int id) throws SQLException {
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(DELETE);) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
		return true;

	}

	public boolean delete(Computer computer) throws SQLException { // fonctionne
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(DELETE);) {
			preparedStatement.setInt(1, computer.getId());
		}

		return true;

	}

	public boolean update(Computer computer) throws SQLException { // fonctionne
		Computer cpt = find(computer.getId());
		if (cpt.getId() <= 0) {
			return false; // rien n'a update, il n'y a pas de pc
		}
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(UPDATE);) {
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setDate(2, computer.getIntroduced());
			preparedStatement.setDate(3, computer.getDiscontinuted());
			if (computer.getCompany() == null || computer.getCompany().getId() == 0
					|| computer.getCompany().getId() == -1) {
				preparedStatement.setObject(4, null);
			} else {
				preparedStatement.setInt(4, computer.getCompany().getId());
			}
			preparedStatement.setInt(5, computer.getId());
			preparedStatement.executeUpdate();
		}

		return true;
	}

	public ArrayList<Computer> findAll() throws SQLException { // fonctionne
		ArrayList<Computer> retAL = new ArrayList<Computer>();
		Computer tmp;
		try (Connection connect = DAOFactory.getInstance().getConnection();
				ResultSet result = connect.createStatement().executeQuery(GET);) {
			while (result.next()) {
				tmp = new Computer(result.getInt("computer.id"), result.getString("name"), result.getDate("introduced"),
						result.getDate("discontinued"), result.getInt("company.id"), result.getString("company.name"));
				retAL.add(tmp);
			}
		}
		return retAL;
	}

	public Computer find(int id) throws SQLException { // fonctionne
		Computer comp = new Computer();
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE);) {
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if (result.first()) {
				comp = new Computer(result.getInt("computer.id"), result.getString("name"),
						result.getDate("introduced"), result.getDate("discontinued"), result.getInt("company.id"),
						result.getString("company.name"));
			}
		}
		return comp;
	}

	public ArrayList<Computer> findPagination(int limit, int offset) throws SQLException {
		ArrayList<Computer> retAL = new ArrayList<Computer>();
		Computer tmp;
		if (limit < 0 || offset < 0) {
			return retAL;
		}
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(GET_PAGINATION);) {
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				tmp = new Computer(result.getInt("computer.id"), result.getString("name"), result.getDate("introduced"),
						result.getDate("discontinued"), result.getInt("company.id"), result.getString("company.name"));
				retAL.add(tmp);
			}
		}
		return retAL;
	}

	public Computer findbyName(String namePC) throws SQLException {
		Computer comp = new Computer();
		try (Connection connect = DAOFactory.getInstance().getConnection();

				PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE_BY_NAME);) {
			preparedStatement.setString(1, namePC);
			ResultSet result = preparedStatement.executeQuery();
			if (result.first()) {
				comp = new Computer(result.getInt("computer.id"), result.getString("name"),
						result.getDate("introduced"), result.getDate("discontinued"), result.getInt("company.id"),
						result.getString("company.name"));
			}
		}
		return comp;
	}

	public ArrayList<Computer> findbyNameMulti(String namePC) throws SQLException {
		ArrayList<Computer> retAL = new ArrayList<>();
		Computer tmp;
		try (Connection connect = DAOFactory.getInstance().getConnection();
				ResultSet result = connect.createStatement().executeQuery(GET_MULTI_BY_NAME);) {
			while (result.next()) {
				tmp = new Computer(result.getInt("computer.id"), result.getString("name"), result.getDate("introduced"),
						result.getDate("discontinued"), result.getInt("company.id"), result.getString("company.name"));
				retAL.add(tmp);
			}
		}
		return retAL;
	}

	public int count() throws SQLException {
		int i = 0;
		try (Connection connect = DAOFactory.getInstance().getConnection();
				ResultSet result = connect.createStatement().executeQuery(COUNT);) {
			if (result.first()) {
				i = result.getInt("total");
			}
		}
		return i;
	}

	public ArrayList<Computer> searchComputer(String string, int limit, int offset) throws SQLException {
		ArrayList<Computer> retAL = new ArrayList<>();
		Computer tmp;
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SEARCH);) {
			preparedStatement.setString(1, "%" + string + "%");
			preparedStatement.setString(2, "%" + string + "%");
			preparedStatement.setInt(3, limit);
			preparedStatement.setInt(4, offset);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				tmp = new Computer(result.getInt("computer.id"), result.getString("name"), result.getDate("introduced"),
						result.getDate("discontinued"), result.getInt("company.id"), result.getString("company.name"));
				retAL.add(tmp);
			}
		}
		return retAL;
	}

	public int searchComputerCount(String string) throws SQLException {
		int res = 0;
		
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SEARCH_COUNT);) {
			preparedStatement.setString(1, "%" + string + "%");
			preparedStatement.setString(2, "%" + string + "%");
			ResultSet result = preparedStatement.executeQuery();
			if (result.first()) {
				res = result.getInt("total");
			}
		}
		return res;
	}

}
