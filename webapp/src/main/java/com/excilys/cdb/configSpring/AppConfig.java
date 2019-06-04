package com.excilys.cdb.configSpring;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.excilys.cdb.hikari.MyHikariConfig;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.excilys.cdb.dao", "com.excilys.cdb.hikari", "com.excilys.cdb.transfert", "com.excilys.cdb.service",
		"com.excilys.cdb.validator" })
public class AppConfig {
	@PersistenceContext EntityManager em;

	@Bean
	public LocalSessionFactoryBean sessionFactory(MyHikariConfig cfg) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(cfg.getDataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.excilys.cdb.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
}

	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "validate");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		return hibernateProperties;
	}
	
	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(em);
}
	
}
