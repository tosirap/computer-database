package com.excilys.cdb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.configSpring.AppConfig;
import com.excilys.cdb.vue.UI;

public class MainCDB {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UI ui = context.getBean(UI.class);
		ui.operations();
		context.close();
	}

}
