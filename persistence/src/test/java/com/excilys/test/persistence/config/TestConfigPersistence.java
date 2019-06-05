package com.excilys.test.persistence.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import com.excilys.cdb.configSpring.AppConfigPersistence;
import com.excilys.test.persistence.database.UTDatabase;
import com.zaxxer.hikari.HikariConfig;

@Configuration
@ComponentScan(basePackages = {"com.excilys.test.persistence.database"})
@Import(AppConfigPersistence.class)
public class TestConfigPersistence {
	 @Bean
	    @Primary
	    public HikariConfig hikariConfig() {
	        final HikariConfig hikariConfig = new HikariConfig();
	        hikariConfig.setUsername("test");
	        hikariConfig.setPassword("test");
	        hikariConfig.setDriverClassName("org.h2.Driver");
	        hikariConfig.setJdbcUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
	        return hikariConfig;
	    }

	    @Bean
	    @Primary
	    public Properties jpaProperties() {
	        Properties properties = new Properties();
	        properties.setProperty("hibernate.hbm2ddl.auto", "create");
	        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	        return properties;
	}

}
