package com.excilys.cdb.configSpring;

import java.util.Properties;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@Configuration
@ComponentScan({ "com.excilys.cdb.dao", "com.excilys.cdb.transfert", "com.excilys.cdb.service",
		"com.excilys.cdb.validator" })
public class AppConfig {

	@Bean
	public DataSource dataSource() {
		
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		return new HikariDataSource(new HikariConfig("/hikary.properties"));
	}
//
//	@Bean
//	public HikariConfig hikariConfig() {
//		return new HikariConfig("/hikary.properties");
//	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public MessageSource messageSource() {
		final ResourceBundleMessageSource bundleMessage = new ResourceBundleMessageSource();
		bundleMessage.addBasenames("i18n/messages");
		return bundleMessage;
	}

	@Bean
	@Autowired
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(ds);
		sessionFactory.setPackagesToScan(new String[] { "com.excilys.cdb.model" });
		sessionFactory.setHibernateProperties(this.hibernateProperties());
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
	
	private Properties hibernateProperties() {
		return new Properties() {
			private static final long serialVersionUID = 27052019L;
			{
				setProperty("hibernate.hbm2ddl.auto", "validate");
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
				setProperty("hibernate.globally_quoted_identifiers", "true");
			}
		};
}
}
