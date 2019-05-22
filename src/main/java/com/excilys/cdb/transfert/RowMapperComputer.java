package com.excilys.cdb.transfert;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.Computer;

public class RowMapperComputer implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Computer computer = new Computer(rs.getInt("computer.id"), rs.getString("computer.name"), rs.getDate("computer.introduced"),
				rs.getDate("computer.discontinued"), rs.getInt("company.id"), rs.getString("company.name"));
		return computer;
	}

}