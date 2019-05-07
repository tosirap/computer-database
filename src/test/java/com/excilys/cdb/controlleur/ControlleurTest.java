package com.excilys.cdb.controlleur;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.database.UTDatabase;




public class ControlleurTest {
	Controlleur controlleur;
	
	@Before
	public void setUp() throws Exception {
		controlleur = Controlleur.getInstance();
		UTDatabase.getInstance().reload();
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
	public void listComputerPaginationInCorrect3() {
		ArrayList<String> alComp = controlleur.listComputerPagination("5", "mystere");
		assertTrue(alComp ==null || alComp.size() == 0);
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
	public void listCompanyPaginationInCorrect3() {
		ArrayList<String> alComp = controlleur.listCompanyPagination("blocage", "4000");
		assertTrue(alComp == null || alComp.size() == 0);
	}
	
	@Test
	public void createComputerCorrect() {
		boolean b = controlleur.createComputer("name", "2016-11-11", "2017-11-11", "4", "companyName");
		assertTrue(b);
	}
	
	@Test
	public void createComputerInCorrect1() {
		boolean b = controlleur.createComputer("name", "2016-11-11", "blop", "4", "companyName");
		assertFalse(b);
	}
	
	@Test
	public void updateComputerCorrect() {
		boolean b = controlleur.updateComputer("12", "Apple III", "1980-05-01", "1984-04-01", "1");
		assertTrue(b);
	}
	
	@Test
	public void uptdateComputerInCorrect1() {
		boolean b = controlleur.createComputer("25","name", "2016-11-11", "blop", "4");
		assertFalse(b);
	}
	
	@Test
	public void supprComputerCorrect() {
		boolean b = controlleur.supprComputer("217291");
		assertTrue(b);
	}
	
	@Test
	public void supprComputerInCorrect1() {
		boolean b = controlleur.supprComputer("lolipop");
		assertFalse(b);
	}
	
	@Test
	public void supprComputerInCorrect2() {
		boolean b = controlleur.supprComputer("-1");
		assertFalse(b);
	}
	
	@Test
	public void checkDateCorrect() {
		boolean b = controlleur.checkDate("2017-10-1", "2019-4-13");
		assertTrue(b);
	}

	@Test
	public void checkDateCorrect2() {
		boolean b = controlleur.checkDate(null, "2017-10-1");
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
	public void checkDateInCorrect5() {
		boolean b = controlleur.checkDate("2019-10-21", "");
		assertTrue(b);
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
