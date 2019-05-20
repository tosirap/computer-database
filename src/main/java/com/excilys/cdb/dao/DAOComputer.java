package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.OrderBy;

@Component
public class DAOComputer {

	private final String CREATE = "INSERT INTO computer(id ,name, introduced, discontinued, company_id) "
			+ "VALUES (NULL , :name , :introduced, :discontinued,:company_id)";
	private final String DELETE = "DELETE FROM computer WHERE id = :id ";
	private final String UPDATE = "UPDATE computer SET name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :idCompany WHERE id = :idComputer ";
	private final String GET = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer"
			+ " LEFT JOIN company ON computer.company_id = company.id ";
	private final String GET_ONE = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer"
			+ " LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ? ";
	private final String GET_PAGINATION = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer"
			+ " LEFT JOIN company ON computer.company_id = company.id ORDER BY ";
	private final String GET_PAGINATION2 = " LIMIT ? OFFSET ?";

	private final String GET_ONE_BY_NAME = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer"
			+ " LEFT JOIN company ON computer.company_id = company.id WHERE computer.name = ? LIMIT 1";
	/*
	 * private final String GET_MULTI_BY_NAME =
	 * "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name"
	 * +
	 * " FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name = ? "
	 * ;
	 */
	private final String COUNT = "SELECT COUNT(*) AS total FROM computer";

	private final String SEARCH = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer LEFT JOIN "
			+ "company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ";
	private final String SEARCH2 = " LIMIT ? OFFSET ? ";

	private final String SEARCH_COUNT = "SELECT COUNT(*) AS total FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE "
			+ "computer.name LIKE ? OR company.name LIKE ?";

	private final DataSource dataSource;

	public DAOComputer(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public boolean create(Computer computer) throws SQLException { // fonctionne
		
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("name", computer.getName());
		vParams.addValue("introduced", computer.getIntroduced());
		vParams.addValue("discontinued", computer.getDiscontinuted());
		vParams.addValue("company_id", computer.getCompany().getId());

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);

		int vNbrLigneMaJ = vJdbcTemplate.update(CREATE, vParams);
		if(vNbrLigneMaJ == 1) {
			return true;
		}
		return false;

	}

	public boolean delete(int id) throws SQLException {

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", id);

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);

		int vNbrLigneMaJ = vJdbcTemplate.update(DELETE, vParams);
		if(vNbrLigneMaJ == 1) {
			return true;
		}
		return false;
	}

	public boolean delete(Computer computer) throws SQLException { // fonctionne

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", computer.getId());

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);

		int vNbrLigneMaJ = vJdbcTemplate.update(DELETE, vParams);
		if(vNbrLigneMaJ == 1) {
			return true;
		}
		return false;

	}

	public boolean update(Computer computer) throws SQLException { // fonctionne
		Computer cpt = find(computer.getId());
		if (cpt == null) {
			return false; // rien n'a update, il n'y a pas de pc
		}
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("name", computer.getName());
		vParams.addValue("introduced", computer.getIntroduced());
		vParams.addValue("discontinued", computer.getDiscontinuted());
		vParams.addValue("idCompany", computer.getCompany().getId());
		vParams.addValue("idComputer", computer.getId());
	

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);

		int vNbrLigneMaJ = vJdbcTemplate.update(UPDATE, vParams);
		if(vNbrLigneMaJ == 1) {
			return true;
		}
		return false;

	}

	public ArrayList<Computer> findAll() throws SQLException { // fonctionne
		ArrayList<Computer> retAL = new ArrayList<Computer>();
		Computer tmp;
		try (Connection connect = this.dataSource.getConnection();
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
		Computer comp = null;
		try (Connection connect = this.dataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE);) {
			preparedStatement.setInt(1, id);
			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.first()) {
					comp = new Computer(result.getInt("computer.id"), result.getString("name"),
							result.getDate("introduced"), result.getDate("discontinued"), result.getInt("company.id"),
							result.getString("company.name"));
				}
			}
		}
		return comp;
	}

	public ArrayList<Computer> findPagination(int limit, int offset, OrderBy orderby, boolean b) throws SQLException {
		ArrayList<Computer> retAL = new ArrayList<Computer>();
		Computer tmp;
		if (limit < 0 || offset < 0) {
			return retAL;
		}
		String asc = "ASC";
		if (b) {
			asc = "ASC";
		} else {
			asc = "DESC";
		}

		try (Connection connect = this.dataSource.getConnection();
				PreparedStatement preparedStatement = connect
						.prepareStatement(GET_PAGINATION + " " + orderby.toString() + " " + asc + GET_PAGINATION2);) {
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					tmp = new Computer(result.getInt("computer.id"), result.getString("name"),
							result.getDate("introduced"), result.getDate("discontinued"), result.getInt("company.id"),
							result.getString("company.name"));
					retAL.add(tmp);
				}
			}
		}
		return retAL;
	}

	public Computer findbyName(String namePC) throws SQLException {
		Computer comp = null;
		try (Connection connect = this.dataSource.getConnection();

				PreparedStatement preparedStatement = connect.prepareStatement(GET_ONE_BY_NAME);) {
			preparedStatement.setString(1, namePC);
			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.first()) {
					comp = new Computer(result.getInt("computer.id"), result.getString("name"),
							result.getDate("introduced"), result.getDate("discontinued"), result.getInt("company.id"),
							result.getString("company.name"));
				}
			}
		}
		return comp;
	}

	/*
	 * public ArrayList<Computer> findbyNameMulti(String namePC) throws SQLException
	 * { ArrayList<Computer> retAL = new ArrayList<>(); Computer tmp; try
	 * (Connection connect = DAOFactory.getInstance().getConnection(); ResultSet
	 * result = connect.createStatement().executeQuery(GET_MULTI_BY_NAME);) { while
	 * (result.next()) { tmp = new Computer(result.getInt("computer.id"),
	 * result.getString("name"), result.getDate("introduced"),
	 * result.getDate("discontinued"), result.getInt("company.id"),
	 * result.getString("company.name")); retAL.add(tmp); } } return retAL; }
	 */
	// https://openclassrooms.com/fr/courses/4504771-simplifiez-le-developpement-dapplications-java-avec-spring/4758445-simplifier-lexecution-de-requetes-sql-avec-spring-jdbc

	public int count() throws SQLException {
		int res = 0;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.dataSource);
		res = vJdbcTemplate.queryForObject(COUNT, Integer.class);
		return res;
	}

	public ArrayList<Computer> searchComputer(String string, int limit, int offset, OrderBy orderby, boolean b)
			throws SQLException {
		ArrayList<Computer> retAL = new ArrayList<>();
		Computer tmp;
		String asc;
		if (b) {
			asc = "ASC";
		} else {
			asc = "DESC";
		}
		try (Connection connect = this.dataSource.getConnection();
				PreparedStatement preparedStatement = connect
						.prepareStatement(SEARCH + " " + orderby.toString() + " " + asc + " " + SEARCH2);) {
			preparedStatement.setString(1, "%" + string + "%");
			preparedStatement.setString(2, "%" + string + "%");
			preparedStatement.setInt(3, limit);
			preparedStatement.setInt(4, offset);
			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					tmp = new Computer(result.getInt("computer.id"), result.getString("name"),
							result.getDate("introduced"), result.getDate("discontinued"), result.getInt("company.id"),
							result.getString("company.name"));
					retAL.add(tmp);
				}
			}
		}
		return retAL;
	}

	public int searchComputerCount(String string) throws SQLException {
		int res = 0;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.dataSource);
		res = vJdbcTemplate.queryForObject(SEARCH_COUNT, Integer.class, string, string);
		return res;

	}

}
