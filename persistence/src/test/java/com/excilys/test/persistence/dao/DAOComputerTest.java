package com.excilys.test.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.enums.OrderBy;
import com.excilys.cdb.model.Computer;
import com.excilys.test.persistence.config.TestConfigPersistence;
import com.excilys.test.persistence.database.UTDatabase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfigPersistence.class)
public class DAOComputerTest {

	@Autowired
	DAOComputer daoComp;
	@Autowired
	UTDatabase utdatabase;

	@Before
	public void setUp() throws Exception {
		utdatabase.reload();

	}

	@Test
	public void daoComputerFindOneCorrecte() {
		Computer actual = null;
		actual = daoComp.find((long) 12);
		Computer expected = new Computer((long) 12, "Apple III", "1980-05-01", "1984-04-01", (long) 1, "Apple Inc.");
		assertEquals(actual, expected);
	}

	@Test
	public void daoComputerFindOneInCorrecte1() {
		Computer computer = null;
		computer = daoComp.find((long) 100999887);
		assertTrue(computer == null);
	}

	@Test
	public void daoComputerFindOneInCorrecte2() {
		Computer computer = null;
		computer = daoComp.find((long) -1);
		assertTrue(computer == null);
	}

	@Test
	public void daoComputerCreateCorrecte() {
		Computer computer = new Computer((long) -1, "name", (Date) null, (Date) null, (long) 1, "Apple Inc.");
		int nbComputerBefore = 0;
		nbComputerBefore = daoComp.findAll().size();
		boolean b = false;
		b = daoComp.create(computer);
		int nbComputerAfter = 0;
		nbComputerAfter = daoComp.findAll().size();
		assertTrue((nbComputerAfter - nbComputerBefore == 1) && b);
	}

	@Test
	public void daoComputerUpdateCorrecte() {
		Computer computer = new Computer((long) 12, "Apple III", Date.valueOf("1980-5-1"), Date.valueOf("1984-4-1"),
				(long) 1, "Apple_Inc");
		daoComp.update(computer);
		Computer comp = null;
		comp = daoComp.find(computer.getId());
		assertEquals(computer, comp);
	}

	@Test(expected = AssertionError.class)
	public void daoComputerUpdateInCorrecte1() {
		Computer testComputer = new Computer((long) 53395, "bloblo", "2017-11-11", "2017-11-11", (long) 4, "Netronics"); 
		daoComp.update(testComputer);
		Computer comp = null;
		comp = daoComp.find(testComputer.getId());
		// assertTrue(comp == null && !b);
	}

	@Test
	public void daoComputerFindAllCorrecte() {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.findAll();
		assertTrue(alComputer != null && !alComputer.isEmpty());
	}

	@Test
	public void daoComputerPaginationCorrecte() {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.findPagination(10, 5, OrderBy.COMPUTER_ID);
		assertTrue(alComputer.size() == 10 && alComputer.get(0).getId() >= 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void daoComputerPaginationInCorrecte1() {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.findPagination(-12, 50, OrderBy.COMPUTER_ID);
	}

	@Test
	public void daoComputerPaginationInCorrecte2() {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.findPagination(10, 5000, OrderBy.COMPUTER_ID);
		assertTrue(alComputer.isEmpty());
	}

	@Test
	public void daoComputerSearchCountOk() {
		int res = 0;
		res = daoComp.searchComputerCount("Ap");
		assertTrue(res > 0);
	}

	@Test
	public void daoComputerSearchCountIncorrect() {
		int res = 0;
		res = daoComp.searchComputerCount("azertyuiopazertyuiop");
		assertTrue(res == 0);
	}

	@Test
	public void daoComputerSearchOK() {
		ArrayList<Computer> alComputer = null;
		alComputer = daoComp.searchComputer("Apple", 5, 0, OrderBy.COMPUTER_ID);
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
		Computer computer = null;
		try {
			computer = daoComp.findbyName("CM-2a");
		} catch (Exception e) {

		}
		assertTrue(computer != null);
	}

	@Test
	public void daoComputerFindByNameinCorrect() {
		Computer computer = null;
		try {
			computer = daoComp.findbyName("CM-2azrzirhzrhzrè_z");
		} catch (Exception e) {

		}
		assertTrue(computer == null);
	}

	@Test
	public void daoComputerCountOk() {
		int res = 0;
		res = daoComp.count();
		assertTrue(res > 0);
	}

	@Test
	public void daoComputerDeleteOk() {
		Computer computerAvant = null;
		Computer computerApres = null;
		try {
			computerAvant = daoComp.find((long) 7);
			daoComp.delete(7);
			computerApres = daoComp.find((long) 7);
		} catch (Exception e) {

		}
		assertTrue(computerAvant != null && computerApres == null);
	}

}
