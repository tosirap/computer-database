package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.cdb.model.Company;

public class DAOCompany {

	private final String GET = "SELECT company.id, company.name FROM company ";
	private final String GET_ONE = "SELECT company.id, company.name FROM company WHERE id = ?";
	private final String GET_ONE_BY_NAME = "SELECT company.id, company.name FROM company WHERE name = ?";
	private final String GET_PAGINATION = "SELECT company.id, company.name FROM company ORDER BY company.id LIMIT ?  OFFSET ? ";
	private final String DELETE_COMPANY = "DELETE FROM company WHERE id = ? ";
	private final String DELETE_COMPUTERS = "DELETE FROM computer WHERE company_id = ? ";

	// protected Connection connect;

	private DAOCompany() {

	}

	/** Instance unique non préinitialisée */
	private static DAOCompany INSTANCE = null;

	/**
	 * Point d'accès pour l'instance unique du singleton
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static DAOCompany getInstance() throws ClassNotFoundException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new DAOCompany();
		}
		return INSTANCE;
	}

	public Company find(int id) throws SQLException {
		// TODO Auto-generated method stub
		Company comp = null;
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE);) {
			preparedStatement.setInt(1, id);
			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.first())
					comp = new Company(id, result.getString("company.name"));
			}
		} catch (Exception e) {

		}
		return comp;
	}

	public Company find(String companyName) throws SQLException {
		Company comp = null;
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE_BY_NAME);) {
			preparedStatement.setString(1, companyName);
			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.first())
					comp = new Company(result.getInt("company.id"), companyName);
			}
		} catch (Exception e) {

		}
		return comp;
	}

	public ArrayList<Company> findAll() throws SQLException { // fonctionne
		Company tmp = null;
		ArrayList<Company> retAL = new ArrayList<Company>();
		try (Connection connect = DAOFactory.getInstance().getConnection();
				ResultSet result = connect.createStatement().executeQuery(GET);) {
			while (result.next()) {
				tmp = new Company(result.getInt("company.id"), result.getString("company.name"));
				retAL.add(tmp);
			}
		} catch (Exception e) {

		}

		return retAL;
	}

	public ArrayList<Company> findPagination(int limit, int offset) throws SQLException {

		ArrayList<Company> retAL = new ArrayList<Company>();
		Company tmp;
		if (limit < 0 || offset < 0) {
			return retAL;
		}
		try (Connection connect = DAOFactory.getInstance().getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(GET_PAGINATION);) {
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					tmp = new Company(result.getInt("company.id"), result.getString("company.name"));
					retAL.add(tmp);
				}
			}
		}

		return retAL;
	}

	public boolean delete(int id) {
		Company company = null;
		try {
			company = find(id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(company == null) {
			return false;
		}
		try(Connection connect = DAOFactory.getInstance().getConnection();){
			connect.setAutoCommit(false);
			try(PreparedStatement preparedStatementCompany = connect.prepareStatement(DELETE_COMPANY);
					PreparedStatement preparedStatementComputer = connect.prepareStatement(DELETE_COMPUTERS);){
				preparedStatementComputer.setInt(1, id);
				preparedStatementComputer.executeUpdate();
				preparedStatementCompany.setInt(1, id);
				
				if (preparedStatementCompany.executeUpdate() == 0) {
					connect.rollback();
				}
				else {
					connect.commit();
				}
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
