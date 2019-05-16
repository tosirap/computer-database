package com.excilys.cdb.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.cdb.configSpring.AppConfig;
import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.database.UTDatabase;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.OrderBy;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
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
	public void daoComputerFindOneCorrecte() {
		Computer actual = null;
		try {
			actual = daoComp.find(12);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Computer expected = new Computer("12", "Apple III", "1980-05-01", "1984-04-01", "1", "Apple Inc.");
		assertEquals(actual, expected);
	}

	@Test
	public void daoComputerFindOneInCorrecte1() {
		Computer computer = null;
		try {
			computer = daoComp.find(100999887);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		assertTrue(computer == null);
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
		assertTrue(computer == null);
	}

	@Test
	public void daoComputerCreateCorrecte() {
		Computer computer = new Computer(-1, "name", null, null, 1, "Apple Inc.");
		int nbComputerBefore = 0;
		try {
			nbComputerBefore = daoComp.findAll().size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean b = false;
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
		assertTrue((nbComputerAfter - nbComputerBefore == 1) && b);
	}

	@Test
	public void daoComputerUpdateCorrecte() {
		Computer computer = new Computer(12, "Apple III", Date.valueOf("1980-5-1"), Date.valueOf("1984-4-1"), 1,
				"Apple_Inc");
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
		assertEquals(computer, comp);
	}

	@Test
	public void daoComputerUpdateInCorrecte1() {
		Computer testComputer = new Computer("595", "bloblo", "2017-11-11", "2017-11-11", "4", "Netronics"); // 595 : id
																												// inexistant
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
		assertTrue(comp == null && !b);
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
			alComputer = daoComp.findPagination(10, 5, OrderBy.COMPUTER_ID, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alComputer.size() == 10 && alComputer.get(0).getId() >= 5);
	}

	@Test
	public void daoComputerPaginationInCorrecte1() {
		ArrayList<Computer> alComputer = null;
		try {
			alComputer = daoComp.findPagination(-12, 50, OrderBy.COMPUTER_ID, true);
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
			alComputer = daoComp.findPagination(10, 5000, OrderBy.COMPUTER_ID, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alComputer.isEmpty());
	}

	@Test
	public void daoComputerSearchCountOk() {
		int res = 0;
		try {
			res = daoComp.searchComputerCount("Ap");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(res > 0);
	}

	@Test
	public void daoComputerSearchCountIncorrect() {
		int res = 0;
		try {
			res = daoComp.searchComputerCount("azertyuiopazertyuiop");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(res == 0);
	}

	@Test
	public void daoComputerSearchOK() {
		ArrayList<Computer> alComputer = null;
		try {
			alComputer = daoComp.searchComputer("Apple", 5, 0, OrderBy.COMPUTER_ID, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alComputer.size() == 5);
	}

	@Test
	public void daoComputerSearchIncorrect() {
		ArrayList<Computer> alComputer = null;
		try {
			alComputer = daoComp.searchComputer("azertyuiopazertyuiop", 5, 0, OrderBy.COMPUTER_ID, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			res = daoComp.count();
		} catch (Exception e) {

		}
		assertTrue(res > 0);
	}

	@Test
	public void daoComputerDeleteOk() {
		Computer computerAvant = null;
		Computer computerApres = null;
		try {
			computerAvant = daoComp.find(7);
			daoComp.delete(7);
			computerApres = daoComp.find(7);
		} catch (Exception e) {

		}
		assertTrue(computerAvant != null && computerApres == null);
	}

}
