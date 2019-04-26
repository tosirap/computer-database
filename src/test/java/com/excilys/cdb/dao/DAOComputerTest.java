package com.excilys.cdb.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Computer;

public class DAOComputerTest {

private Computer computer;
	DAOComputer daoComp;

	@Before
	public void setUp() throws Exception {
		computer = new Computer("3","bloblo", "2017-11-11", "2017-11-11", "4", "Netronics");
		daoComp = DAOComputer.getInstance();
		
	}

	@After
	public void tearDown() throws Exception {
		computer = null;
	}

	@Test
	public void daoComputerFindOneCorrecte() {
		Computer actual = daoComp.find(12);
		Computer expected = new Computer("3", "Apple III", "1980-05-01", "1984-04-01", "1",
				"Apple Inc.");
		assertEquals(actual, expected);
	}
	
	@Test
	public void daoComputerFindOneInCorrecte1() {
		Computer computer = daoComp.find(100999887);
		assertTrue(computer.getId() == -1);
	}
	
	@Test
	public void daoComputerFindOneInCorrecte2() {
		Computer computer = daoComp.find(-1);
		assertTrue(computer.getId() == -1);
	}
	
	@Test
	public void daoComputerCreateCorrecte() {
		int nbComputerBefore = daoComp.findAll().size();
		boolean b = daoComp.create(computer);
		int nbComputerAfter = daoComp.findAll().size();
		assertTrue((nbComputerAfter  - nbComputerBefore == 1)&&b);		
	}
	
	@Test
	public void daoComputerUpdateCorrecte() {
		daoComp.update(computer);
		Computer comp = daoComp.find(computer.getId());
		assertEquals(computer,comp);
	}
	
	@Test
	public void daoComputerUpdateInCorrecte1() {
		Computer testComputer = new Computer("595","bloblo", "2017-11-11", "2017-11-11", "4", "Netronics"); //595 : id inexistant
		boolean b = daoComp.update(testComputer);
		Computer comp = daoComp.find(testComputer.getId());
		assertTrue(comp.getId()==-1 && !b  );
	}
	
	@Test
	public void daoComputerFindAllCorrecte() {
		ArrayList<Computer> alComputer = daoComp.findAll();
		assertTrue(alComputer != null && !alComputer.isEmpty());
	}
	
	@Test
	public void daoComputerPaginationCorrecte() {
		ArrayList<Computer> alComputer = daoComp.findPagination(20, 50);
		assertTrue(alComputer.size() == 20 && alComputer.get(0).getId()>=50);
	}
	
	@Test
	public void daoComputerPaginationInCorrecte1() {
		ArrayList<Computer> alComputer = daoComp.findPagination(-12, 50);
		assertTrue(alComputer.isEmpty());
	}
	
	@Test
	public void daoComputerPaginationInCorrecte2() {
		ArrayList<Computer> alComputer = daoComp.findPagination(10, 5000);
		assertTrue(alComputer.isEmpty());
	}

}
