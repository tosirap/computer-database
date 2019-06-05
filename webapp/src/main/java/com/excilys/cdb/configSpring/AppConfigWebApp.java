package com.excilys.cdb.configSpring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import({AppConfigBinding.class,AppConfigService.class})
public class AppConfigWebApp {
	

}
