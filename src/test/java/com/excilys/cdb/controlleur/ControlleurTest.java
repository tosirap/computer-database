package com.excilys.cdb.controlleur;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.excilys.cdb.configSpring.AppConfig;
import com.excilys.cdb.database.UTDatabase;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ControlleurTest {
	@Autowired
	Controlleur controlleur;
	@Autowired
	UTDatabase utdatabase;
	
	@Before
	public void setUp() throws IOException, SQLException {
		utdatabase.reload();
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
		ArrayList<String> alComp = controlleur.listComputerPagination("10", "10");
		assertTrue(alComp.size() == 10);
	}
	
	@Test
	public void listComputerPaginationInCorrectLimitNegative() {
		ArrayList<String> alComp = controlleur.listComputerPagination("-4", "40");
		assertTrue(alComp == null);
	}
	
	@Test
	public void listComputerPaginationInCorrectOffesetNegatif() {
		ArrayList<String> alComp = controlleur.listComputerPagination("4", "-40");
		assertTrue(alComp == null);
	}
	
	@Test
	public void listComputerPaginationInCorrectOffesetEnorme() {
		ArrayList<String> alComp = controlleur.listComputerPagination("5", "4000");
		assertTrue(alComp.size() == 0);
	}
	
	@Test
	public void listComputerPaginationInCorrectOffsetString() {
		ArrayList<String> alComp = controlleur.listComputerPagination("5", "mystere");
		assertTrue(alComp ==null || alComp.size() == 0);
	}
	
	@Test
	public void listCompanyPaginationCorrect() {
		ArrayList<String> alComp = controlleur.listCompanyPagination("5", "0");
		assertTrue(alComp.size() == 5);
	}
	
	@Test
	public void listCompanyPaginationInCorrectLimiNegative() {
		ArrayList<String> alComp = controlleur.listCompanyPagination("-4", "40");
		assertTrue(alComp == null);
	}
	
	@Test
	public void listCompanyPaginationInCorrectOffsetNegatif() {
		ArrayList<String> alComp = controlleur.listCompanyPagination("4", "-40");
		assertTrue(alComp == null);
	}
	
	@Test
	public void listCompanyPaginationInCorrectOffsetEnorme() {
		ArrayList<String> alComp = controlleur.listCompanyPagination("5", "4000");
		assertTrue(alComp.size() == 0);
	}
	
	@Test
	public void listCompanyPaginationInCorrectLimitString() {
		ArrayList<String> alComp = controlleur.listCompanyPagination("blocage", "4000");
		assertTrue(alComp == null || alComp.size() == 0);
	}
	
	@Test
	public void createComputerCorrect() {
		boolean b = controlleur.createComputer("name", "2016-11-11", "2017-11-11", "4", "companyName");
		assertTrue(b);
	}
	
	@Test
	public void createComputerInCorrectDateString() {
		boolean b = controlleur.createComputer("name", "2016-11-11", "blop", "4", "companyName");
		assertFalse(b);
	}
	
	@Test
	public void updateComputerCorrect() {
		boolean b = controlleur.updateComputer("12", "Apple III", "1980-05-01", "1984-04-01", "1");
		assertTrue(b);
	}
	
	@Test
	public void uptdateComputerInCorrectDateString() {
		boolean b = controlleur.createComputer("25","name", "2016-11-11", "blop", "4");
		assertFalse(b);
	}
	
	@Test
	public void supprComputerCorrect() {
		boolean b = controlleur.supprComputer("217291");
		assertTrue(b);
	}
	
	@Test
	public void supprComputerInCorrectIDString() {
		boolean b = controlleur.supprComputer("lolipop");
		assertFalse(b);
	}
	
	@Test
	public void supprComputerInCorrectIDNegatif() {
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
	public void checkDateIncorrect20emeMois() {
		boolean b = controlleur.checkDate("2017-20-21", "2019-4-13");
		assertTrue(!b);
	}
	
	@Test
	public void checkDateIncorrectDateIncomplete() {
		boolean b = controlleur.checkDate("2017", "2019-4-13");
		assertTrue(!b);
	}
	
	@Test
	public void checkDateIncorrectDateString() {
		boolean b = controlleur.checkDate("2017-02-11", "bonsoir");
		assertTrue(!b);
	}
	
	@Test
	public void checkDateInCorrectDateIncorrect() {
		boolean b = controlleur.checkDate("2019-10-21", "2017-10-1");
		assertTrue(!b);
	}
	
	@Test
	public void checkDateCorrect3() {
		boolean b = controlleur.checkDate("2019-10-21", "");
		assertTrue(b);
	}
	
	@Test
	public void computerDetailCorrect() {
		String s = controlleur.computerDetails("4");
		assertTrue(s != null && s != "");
	}
	
	/*@Test
	public void computerDetailInCorrect1() {
		String s = controlleur.computerDetails("babloz");
		//assertTrue(s != null && s != ""); todo
	}*/
	

}
