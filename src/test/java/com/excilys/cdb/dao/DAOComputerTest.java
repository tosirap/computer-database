package com.excilys.cdb.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.database.UTDatabase;
import com.excilys.cdb.model.Computer;

public class DAOComputerTest {

private Computer computer;
	DAOComputer daoComp;

	@Before
	public void setUp() throws Exception {
		UTDatabase.getInstance().reload();
		daoComp = DAOComputer.getInstance();
		
	}

	@After
	public void tearDown() throws Exception {
		computer = null;
	}

	@Test
	public void daoComputerFindOneCorrecte() {
		Computer actual = null;
		try {
			actual = daoComp.find(12);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Computer expected = new Computer("3", "Apple III", "1980-05-01", "1984-04-01", "1",
				"Apple Inc.");
		assertEquals(actual, expected);
	}
	
	@Test
	public void daoComputerFindOneInCorrecte1() {
		Computer computer = null;
		try {
			computer = daoComp.find(100999887);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(computer.getId() == -1);
	}
	
	@Test
	public void daoComputerFindOneInCorrecte2() {
		Computer computer = null;
		try {
			computer = daoComp.find(-1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(computer.getId() == -1);
	}
	
	/*@Test
	public void daoComputerCreateCorrecte() {
		int nbComputerBefore = 0;
		try {
			nbComputerBefore = daoComp.findAll().size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean b= false;
		try {
			b = daoComp.create(computer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int nbComputerAfter = 0;
		try {
			nbComputerAfter = daoComp.findAll().size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue((nbComputerAfter  - nbComputerBefore == 1)&&b);		
	}*/
	
	/*@Test
	public void daoComputerUpdateCorrecte() {
		try {
			daoComp.update(computer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Computer comp = null;
		try {
			comp = daoComp.find(computer.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(computer,comp);
	}*/
	
	@Test
	public void daoComputerUpdateInCorrecte1() {
		Computer testComputer = new Computer("595","bloblo", "2017-11-11", "2017-11-11", "4", "Netronics"); //595 : id inexistant
		boolean b = false;
		try {
			b = daoComp.update(testComputer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Computer comp = null;
		try {
			comp = daoComp.find(testComputer.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(comp.getId()==-1 && !b  );
	}
	
	@Test
	public void daoComputerFindAllCorrecte() {
		ArrayList<Computer> alComputer = null;
		try {
			alComputer = daoComp.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alComputer != null && !alComputer.isEmpty());
	}
	
	@Test
	public void daoComputerPaginationCorrecte() {
		ArrayList<Computer> alComputer = null;
		try {
			alComputer = daoComp.findPagination(20, 50);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alComputer.size() == 20 && alComputer.get(0).getId()>=50);
	}
	
	@Test
	public void daoComputerPaginationInCorrecte1() {
		ArrayList<Computer> alComputer = null;
		try {
			alComputer = daoComp.findPagination(-12, 50);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alComputer.isEmpty());
	}
	
	@Test
	public void daoComputerPaginationInCorrecte2() {
		ArrayList<Computer> alComputer = null;
		try {
			alComputer = daoComp.findPagination(10, 5000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alComputer.isEmpty());
	}

}
