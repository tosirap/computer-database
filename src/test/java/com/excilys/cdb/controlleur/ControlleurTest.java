package com.excilys.cdb.controlleur;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class ControlleurTest {
	Controlleur controlleur;
	
	@Before
	public void setUp() throws Exception {
		controlleur = Controlleur.getInstance();
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void listComputerCorrect() {
		ArrayList<String> alComp = controlleur.listComputer();
		assertTrue(alComp != null && !alComp.isEmpty());
	}
	
	@Test
	public void listCompanyCorrect() {
		ArrayList<String> alComp = controlleur.listCompany();
		assertTrue(alComp != null && !alComp.isEmpty());
	}
	
	@Test
	public void listComputerPaginationCorrect() {
		ArrayList<String> alComp = controlleur.listComputerPagination("10", "40");
		assertTrue(alComp.size() == 10);
	}
	
	@Test
	public void listComputerPaginationInCorrect1() {
		ArrayList<String> alComp = controlleur.listComputerPagination("-4", "40");
		assertTrue(alComp == null);
	}
	
	@Test
	public void listComputerPaginationInCorrect1Bis() {
		ArrayList<String> alComp = controlleur.listComputerPagination("4", "-40");
		assertTrue(alComp == null);
	}
	
	@Test
	public void listComputerPaginationInCorrect2() {
		ArrayList<String> alComp = controlleur.listComputerPagination("5", "4000");
		assertTrue(alComp.size() == 0);
	}
	
	@Test
	public void listCompanyPaginationCorrect() {
		ArrayList<String> alComp = controlleur.listCompanyPagination("5", "20");
		assertTrue(alComp.size() == 5);
	}
	
	@Test
	public void listCompanyPaginationInCorrect1() {
		ArrayList<String> alComp = controlleur.listCompanyPagination("-4", "40");
		assertTrue(alComp == null);
	}
	
	@Test
	public void listCompanyPaginationInCorrect1Bis() {
		ArrayList<String> alComp = controlleur.listCompanyPagination("4", "-40");
		assertTrue(alComp == null);
	}
	
	@Test
	public void listCompanyPaginationInCorrect2() {
		ArrayList<String> alComp = controlleur.listCompanyPagination("5", "4000");
		assertTrue(alComp.size() == 0);
	}
	
	@Test
	public void checkDateCorrect() {
		boolean b = controlleur.checkDate("2017-10-1", "2019-4-13");
		assertTrue(b);
	}
	
	@Test
	public void checkDateIncorrect1() {
		boolean b = controlleur.checkDate("2017-20-21", "2019-4-13");
		assertTrue(!b);
	}
	
	@Test
	public void checkDateIncorrect2() {
		boolean b = controlleur.checkDate("2017", "2019-4-13");
		assertTrue(!b);
	}
	
	@Test
	public void checkDateIncorrect3() {
		boolean b = controlleur.checkDate("2017-02-11", "bonsoir");
		assertTrue(!b);
	}
	
	@Test
	public void checkDateInCorrect4() {
		boolean b = controlleur.checkDate("2019-10-21", "2017-10-1");
		assertTrue(!b);
	}
	
	@Test
	public void computerDetailCorrect() {
		String s = controlleur.computerDetails("4");
		assertTrue(s != null && s != "");
	}
	
	@Test
	public void computerDetailInCorrect1() {
		String s = controlleur.computerDetails("babloz");
		//assertTrue(s != null && s != ""); todo
	}
	

}
