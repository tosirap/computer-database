package com.excilys.cdb.hikari;

import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class MyHikariConfig {

	
	public DataSource getDataSource() {
		System.out.println("ballec");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		return new HikariDataSource(new HikariConfig("/hikary.properties"));
	}

}
