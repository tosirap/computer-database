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

	protected Connection connect = null;

	private DAOComputer() {
		if (this.connect == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC", "admincdb",
						"qwerty1234");
			} catch (SQLException e) {
				System.out.println("Erreur sql exception");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Erreur class");
				e.printStackTrace();
			}
		}
	}

	/** Instance unique non préinitialisée */
	private static DAOComputer INSTANCE = null;

	/** Point d'accès pour l'instance unique du singleton */
	public static DAOComputer getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DAOComputer();
		}
		return INSTANCE;
	}

	public boolean create(Computer computer) { // fonctionne
		// TODO Auto-generated method stub

		try (PreparedStatement preparedStatement = connect.prepareStatement(CREATE)) {
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setDate(2, computer.getIntroduced());
			preparedStatement.setDate(3, computer.getDiscontinuted());
			if (computer.getCompanyId() == 0 || computer.getCompanyId() == -1) {
				preparedStatement.setObject(4, null);
			} else {
				preparedStatement.setInt(4, computer.getCompanyId());
			}
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int id) {
		try (PreparedStatement preparedStatement = connect.prepareStatement(DELETE)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Computer computer) { // fonctionne
		// TODO Auto-generated method stub
		try (PreparedStatement preparedStatement = connect.prepareStatement(DELETE)) {
			preparedStatement.setInt(1, computer.getId());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur SQLl dans le delete ");
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Computer computer) { // fonctionne
		// TODO Auto-generated method stub
		
		Computer cpt = find(computer.getId());
		if(cpt.getId()<=0) {
			return false; //rien n'a update, il n'y a pas de pc
		}

		try (PreparedStatement preparedStatement = connect.prepareStatement(UPDATE)) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Computer> findAll() { // fonctionne
		ArrayList<Computer> retAL = new ArrayList<Computer>();
		Computer tmp;
		try {
			ResultSet result = connect.createStatement().executeQuery(GET);
			while (result.next()) {
				tmp = new Computer(result.getInt("id"), result.getString("name"), result.getDate("introduced"),
						result.getDate("discontinued"), result.getInt("company_id"), result.getString("company.name"));
				retAL.add(tmp);
			}

			result.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return retAL;
	}

	public Computer find(int id) { // fonctionne
		// TODO Auto-generated method stub
		Computer comp = new Computer();
		try (PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE)) {
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if (result.first()) {
				comp = new Computer(result.getInt("id"), result.getString("name"), result.getDate("introduced"),
						result.getDate("discontinued"), result.getInt("company_id"), result.getString("company.name"));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}

	public ArrayList<Computer> findPagination(int limit, int offset) {
		// TODO Auto-generated method stub
		ArrayList<Computer> retAL = new ArrayList<Computer>();
		Computer tmp;
		if(limit < 0 || offset < 0) {
			return retAL;
		}
		try (PreparedStatement preparedStatement = connect.prepareStatement(GET_PAGINATION)) {
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				tmp = new Computer(result.getInt("id"), result.getString("name"), result.getDate("introduced"),
						result.getDate("discontinued"), result.getInt("company_id"), result.getString("company.name"));
				retAL.add(tmp);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retAL;
	}

}
