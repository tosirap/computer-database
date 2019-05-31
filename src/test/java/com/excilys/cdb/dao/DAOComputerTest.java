package com.excilys.cdb.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.excilys.cdb.configSpring.AppConfig;
import com.excilys.cdb.database.UTDatabase;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.enums.OrderBy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
public class DAOComputerTest {

	@Autowired
	DAOComputer daoComp;
	@Autowired
	UTDatabase utdatabase;

	@Before
	public void setUp() throws Exception {
		utdatabase.reload();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void daoComputerFindOneCorrecte() throws DataAccessException {
		Computer actual = null;
		actual = daoComp.find((long)12);
		Computer expected = new Computer((long)12, "Apple III", "1980-05-01", "1984-04-01", (long)1, "Apple Inc.");
		assertEquals(actual, expected);
	}

	@Test(expected = DataAccessException.class)
	public void daoComputerFindOneInCorrecte1() {
		Computer computer = null;
		computer = daoComp.find((long)100999887);
	}

	@Test(expected = DataAccessException.class)
	public void daoComputerFindOneInCorrecte2() {
		Computer computer = daoComp.find( (long)-1);
	}

	@Test
	public void daoComputerCreateCorrecte() throws SQLException {
		Computer computer = new Computer((long)1, "name", (Date) null, (Date) null, (long)1, "Apple Inc.");
		boolean b = daoComp.create(computer);
		assertTrue(b);
	}

	@Test(expected = DataAccessException.class)
	public void daoComputerCreateInCorrecte() throws DataAccessException {
		Computer computer = new Computer( (long)0, "", (Date) null, (Date) null,  (long)0, "Apple Inc.");
		daoComp.create(computer);
	}

	@Test
	public void daoComputerUpdateCorrecte() throws DataAccessException {
		Computer computer = new Computer( (long)12, "Apple III", Date.valueOf("1980-5-1"), Date.valueOf("1984-4-1"), (long) 1,
				"Apple_Inc");
		daoComp.update(computer);
		Computer comp = null;
		comp = daoComp.find(computer.getId());
		assertEquals(computer, comp);
	}

	@Test(expected = DataAccessException.class)
	public void daoComputerUpdateInCorrecte1() throws DataAccessException {
		Computer testComputer = new Computer( (long)595, "bloblo", "2017-11-11", "2017-11-11",  (long)4, "Netronics"); // 595 : id
																											// inexistant
		boolean b = false;
		b = daoComp.update(testComputer);
		Computer comp = null;
		comp = daoComp.find(testComputer.getId());
	}

	@Test
	public void daoComputerFindAllCorrecte() throws DataAccessException {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.findAll();
		assertTrue(alComputer != null && !alComputer.isEmpty());
	}

	@Test
	public void daoComputerPaginationCorrecte() throws DataAccessException {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.findPagination(10, 5, OrderBy.COMPUTER_ID);
		assertTrue(alComputer.size() == 10 && alComputer.get(0).getId() >= 5);
	}

	@Test
	public void daoComputerPaginationInCorrecte1() throws DataAccessException {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.findPagination(-12, 50, OrderBy.COMPUTER_ID);
		assertTrue(alComputer == null);
	}

	@Test
	public void daoComputerPaginationInCorrecte2() throws DataAccessException {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.findPagination(10, 5000, OrderBy.COMPUTER_ID);
		assertTrue(alComputer.isEmpty());
	}

	@Test
	public void daoComputerSearchCountOk() {
		int res = daoComp.searchComputerCount("Ap");
		assertTrue(res > 0);
	}

	@Test
	public void daoComputerSearchCountIncorrect() {
		int res = daoComp.searchComputerCount("azertyuiopazertyuiop");
		assertTrue(res == 0);
	}

	@Test
	public void daoComputerSearchOK() {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.searchComputer("Ap", 5, 0, OrderBy.COMPUTER_ID);
		System.out.println(alComputer.size());
		assertTrue(alComputer.size() == 5);
	}

	@Test
	public void daoComputerSearchIncorrect() {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.searchComputer("azertyuiopazertyuiop", 5, 0, OrderBy.COMPUTER_ID);
		assertTrue(alComputer.isEmpty());
	}

	@Test
	public void daoComputerFindByName() {
		Computer computer = daoComp.findbyName("CM-2");
		assertTrue(computer != null);
	}

	@Test(expected = DataAccessException.class)
	public void daoComputerFindByNameinCorrect() {
		Computer computer = null;
		computer = daoComp.findbyName("CM-2azrzirhzrhzrè_z");
		assertTrue(computer == null);
	}

	@Test
	public void daoComputerCountOk() {
		int res = daoComp.count();
		assertTrue(res > 0);
	}

	@Test
	public void daoComputerDeleteOk() {
		boolean b = daoComp.delete(7);
		assertTrue(b);
	}

}
