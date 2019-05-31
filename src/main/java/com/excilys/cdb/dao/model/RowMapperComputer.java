package com.excilys.cdb.dao.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Computer;


@Component
public class RowMapperComputer implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Computer(rs.getLong("computer.id"), rs.getString("computer.name"), rs.getDate("computer.introduced"),
				rs.getDate("computer.discontinued"), rs.getLong("company.id"), rs.getString("company.name"));
	}

}
