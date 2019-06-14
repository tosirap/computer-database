package com.excilys.cdb.configSpring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.excilys.cdb.service" })
@Import(AppConfigPersistence.class)
public class AppConfigService {
	
}
