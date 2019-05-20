package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;

@Component
public class DAOCompany  {

	private final String GET = "SELECT company.id, company.name FROM company ";
	private final String GET_ONE = "SELECT company.id, company.name FROM company WHERE id = ?";
	private final String GET_ONE_BY_NAME = "SELECT company.id, company.name FROM company WHERE name = ?";
	private final String GET_PAGINATION = "SELECT company.id, company.name FROM company ORDER BY company.id LIMIT ?  OFFSET ? ";
	private final String DELETE_COMPANY = "DELETE FROM company WHERE id = ? ";
	private final String DELETE_COMPUTERS = "DELETE FROM computer WHERE company_id = ? ";

	static Logger logger = LoggerFactory.getLogger(DAOCompany.class);
	// protected Connection connect;
	private final DataSource dataSource;

	public DAOCompany(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public Company find(int id) throws SQLException {
		// TODO Auto-generated method stub
		Company comp = null;
		try (Connection connect = this.dataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE);) {
			preparedStatement.setInt(1, id);
			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.first())
					comp = new Company(id, result.getString("company.name"));
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return comp;
	}

	public Company find(String companyName) throws SQLException {
		Company comp = null;
		try (Connection connect = this.dataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE_BY_NAME);) {
			preparedStatement.setString(1, companyName);
			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.first())
					comp = new Company(result.getInt("company.id"), companyName);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return comp;
	}

	public ArrayList<Company> findAll() throws SQLException { // fonctionne
		Company tmp = null;
		ArrayList<Company> retAL = new ArrayList<Company>();
		try (Connection connect = this.dataSource.getConnection();
				ResultSet result = connect.createStatement().executeQuery(GET);) {
			while (result.next()) {
				tmp = new Company(result.getInt("company.id"), result.getString("company.name"));
				retAL.add(tmp);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return retAL;
	}

	public ArrayList<Company> findPagination(int limit, int offset) throws SQLException {

		ArrayList<Company> retAL = new ArrayList<Company>();
		Company tmp;
		if (limit < 0 || offset < 0) {
			return retAL;
		}
		try (Connection connect = this.dataSource.getConnection();
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
			logger.info(e1.getMessage());
		}
		if (company == null) {
			return false;
		}
		try (Connection connect = this.dataSource.getConnection();) {
			connect.setAutoCommit(false);
			try (PreparedStatement preparedStatementCompany = connect.prepareStatement(DELETE_COMPANY);
					PreparedStatement preparedStatementComputer = connect.prepareStatement(DELETE_COMPUTERS);) {
				preparedStatementComputer.setInt(1, id);
				preparedStatementComputer.executeUpdate();
				preparedStatementCompany.setInt(1, id);

				if (preparedStatementCompany.executeUpdate() == 0) {
					connect.rollback();
				} else {
					connect.commit();
				}
				return true;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return false;
	}

}
