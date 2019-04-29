package com.excilys.cdb.vue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UITest {

	UI ui;
	@Before
	public void setUp() throws Exception {
		ui = new UI();;
	}

	@After
	public void tearDown() throws Exception {
		ui = null;
	}
	
	@Test
	public void miseEnFormeComputerCorrect() {
		String s = ui.miseEnFormeComputer("1;blop;null;null;3;Apple");
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
