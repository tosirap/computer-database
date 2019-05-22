package com.excilys.cdb.transfert;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.excilys.cdb.model.Company;

public class RowMapperCompany implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company company = new Company(rs.getInt("company.id"), rs.getString("company.name"));
		return company;
	}

}
