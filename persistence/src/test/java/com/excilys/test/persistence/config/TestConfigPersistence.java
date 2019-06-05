package com.excilys.test.persistence.config;

import java.util.Properties;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.excilys.cdb.configSpring.AppConfigPersistence;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = { "com.excilys.test.persistence.database" })
@Import(AppConfigPersistence.class)
public class TestConfigPersistence {
	
	@Bean
	@Primary
	public LocalSessionFactoryBean sessionFactory(HikariConfig config) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource(config));
		sessionFactory.setPackagesToScan(new String[] { "com.excilys.cdb.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}
	
	@Bean(destroyMethod = "close")
	@Primary
	public DataSource dataSource(HikariConfig config) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		return new HikariDataSource(config);
	}
	
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
	public Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		return hibernateProperties;
	}

}
