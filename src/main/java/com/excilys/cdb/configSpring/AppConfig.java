package com.excilys.cdb.configSpring;

import java.util.Properties;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.excilys.cdb.dao", "com.excilys.cdb.transfert", "com.excilys.cdb.service",
		"com.excilys.cdb.validator" })
public class AppConfig {

/*	@Bean
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
	public MessageSource messageSource() {
		final ResourceBundleMessageSource bundleMessage = new ResourceBundleMessageSource();
		bundleMessage.addBasenames("i18n/messages");
		return bundleMessage;
	}

	
	
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource ds) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(ds);
        System.out.println("ici");
        sessionFactory.setPackagesToScan(new String[] {"com.excilys.cdb.model" });
        System.out.println("ici2");
        sessionFactory.setHibernateProperties(hibernateProperties());
        System.out.println("ici3");
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
	        hibernateProperties.setProperty(
	          "hibernate.hbm2ddl.auto", "create-drop");
	        hibernateProperties.setProperty(
	          "hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	        return hibernateProperties;
	    }
	 
	 
	 */
	 
	 
	    @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan(
	          "com.excilys.cdb.model" );
	        sessionFactory.setHibernateProperties(hibernateProperties());
	 
	        return sessionFactory;
	    }
	 
	    @Bean
	    public DataSource dataSource() {
	    	TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			return new HikariDataSource(new HikariConfig("/hikary.properties"));
	    }
	 
	    @Bean
	    public PlatformTransactionManager hibernateTransactionManager() {
	        HibernateTransactionManager transactionManager
	          = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());
	        return transactionManager;
	    }
	 
	    private final Properties hibernateProperties() {
	        Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty(
	          "hibernate.hbm2ddl.auto", "validate");
	        hibernateProperties.setProperty(
	          "hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	 
	        return hibernateProperties;
	    }
	 
	 
}
