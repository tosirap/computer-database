package com.excilys.cdb.vue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.cdb.configSpring.AppConfig;
import com.excilys.cdb.database.UTDatabase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UITest {
	@Autowired
	UI ui;
	@Autowired
	UTDatabase utdatabase;
	
	@Before
	public void setUp() throws Exception {
		utdatabase.reload();
	}

	@After
	public void tearDown() throws Exception {
		ui = null;
	}
	
	@Test
	public void miseEnFormeComputerCorrect() {
		String s = ui.miseEnFormeComputer("blop;null;null;Apple");
		System.out.println(s);
		assertTrue(s != null && s != "Element introuvable");
	}

	@Test
	public void miseEnFormeComputerInCorrect1() {
		String s = ui.miseEnFormeComputer("");
		assertTrue(s == "Element introuvable");
	}
	
	@Test
	public void miseEnFormeComputerInCorrect2() {
		String s = ui.miseEnFormeComputer(null);
		assertTrue(s == "Element introuvable");
	}
	
	@Test
	public void miseEnFormeCompanyCorrect() {
		String s = ui.miseEnFormeCompany("1;Apple");
		assertTrue(s != null && s != "Element introuvable");
	}

	@Test
	public void miseEnFormeCompanyInCorrect1() {
		String s = ui.miseEnFormeCompany("");
		assertTrue(s == "Element introuvable");
	}
	
	@Test
	public void miseEnFormeCompanyInCorrect2() {
		String s = ui.miseEnFormeCompany(null);
		assertTrue(s == "Element introuvable");
	}
	
	
	
	
}
