package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.transfert.RowMapperCompany;

@Component
public class DAOCompany {

	private final String GET = "SELECT company.id, company.name FROM company ";
	private final String GET_ONE = "SELECT company.id, company.name FROM company WHERE id = :company.id";
	private final String GET_ONE_BY_NAME = "SELECT company.id, company.name FROM company WHERE name = :name LIMIT 1";
	private final String GET_PAGINATION = "SELECT company.id, company.name FROM company ORDER BY company.id LIMIT :limit OFFSET :offset";
	private final String DELETE_COMPANY = "DELETE FROM company WHERE id = :company.id ";
	private final String DELETE_COMPUTERS = "DELETE FROM computer WHERE company_id = :company.id ";

	static Logger logger = LoggerFactory.getLogger(DAOCompany.class);
	// protected Connection connect;
	private final DataSource dataSource;

	public DAOCompany(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	public Company find(int id) throws DataAccessException {
		Company comp = null;
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("company.id", id);
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		RowMapperCompany rowMapperCompany = new RowMapperCompany();
		comp = (Company) vJdbcTemplate.queryForObject(GET_ONE, vParams, rowMapperCompany);
		return comp;
	}

	public Company find(String companyName) throws DataAccessException {
		Company comp = null;
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("name", companyName);
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		RowMapperCompany rowMapperCompany = new RowMapperCompany();
		comp = (Company) vJdbcTemplate.queryForObject(GET_ONE_BY_NAME, vParams, rowMapperCompany);
		return comp;
	}

	public ArrayList<Company> findAll() throws DataAccessException { // fonctionne
		RowMapperCompany rowMapperCompany = new RowMapperCompany();
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.dataSource);
		List<Company> listCompany = vJdbcTemplate.query(GET, rowMapperCompany);
		return new ArrayList<Company>(listCompany);
	}

	public ArrayList<Company> findPagination(int limit, int offset) throws DataAccessException {
		if (limit < 0 || offset < 0) {
			return null;
		}
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("limit", limit);
		vParams.addValue("offset", offset);
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource);
		RowMapperCompany rowMapperCompany = new RowMapperCompany();
		List<Company> retAL = vJdbcTemplate.query(GET_PAGINATION, vParams, rowMapperCompany);
		return new ArrayList<Company>(retAL);
	}

	
	public boolean delete(int id) throws DataAccessException {
		Company company = null;
		company = find(id);
		if (company == null) {
			return false;
		}
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.dataSource);
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("company.id", id);
		return (vJdbcTemplate.update(DELETE_COMPUTERS, vParams) != 0);		
	}

}
