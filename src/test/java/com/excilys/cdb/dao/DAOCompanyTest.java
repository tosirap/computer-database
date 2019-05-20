package com.excilys.cdb.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.cdb.configSpring.AppConfig;
import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.database.UTDatabase;
import com.excilys.cdb.model.Company;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class DAOCompanyTest {
	@Autowired
	DAOCompany daoCompany;
	@Autowired
	DAOComputer daoComputer;

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
	public void testFindCorrect() {
		Company companyExpected = new Company(1, "Apple Inc.");
		Company companyResult = null;
		companyResult = daoCompany.find(1);
		assertEquals(companyExpected, companyResult);
	}

	@Test(expected = DataAccessException.class)
	public void testFindInCorrect1() {
		Company companyResult = null;
		companyResult = daoCompany.find(-1);
	}

	@Test(expected = DataAccessException.class)
	public void testFindInCorrect2() {
		Company companyResult = null;
		companyResult = daoCompany.find(555);
	}

	@Test
	public void testFindAllCorrect() {
		ArrayList<Company> alCompany = null;
		alCompany = daoCompany.findAll();
		assertTrue(alCompany.size() == 10);
	}

	@Test
	public void testFindbyNameCorrect() {
		Company companyExpected = new Company(1, "Apple Inc.");
		Company companyResult = daoCompany.find("Apple Inc.");
		assertEquals(companyExpected, companyResult);
	}

	@Test(expected = DataAccessException.class)
	public void testFindbyNameInCorrect() {
		Company companyResult = null;
		companyResult = daoCompany.find("Auhfuef");
	}

	@Test
	public void testPaginationCorrect() {
		ArrayList<Company> alCompany = daoCompany.findPagination(2, 2);
		assertTrue(alCompany.size() == 2 && alCompany.get(0).getId() >= 2);
	}

	@Test
	public void testPaginationInCorrect1() {
		ArrayList<Company> alCompany  = daoCompany.findPagination(5, 10000);
		assertTrue(alCompany.isEmpty());
	}

	@Test
	public void testPaginationInCorrect2() {
		ArrayList<Company> alCompany = null;
		alCompany = daoCompany.findPagination(5, -10);
		assertTrue(alCompany == null);
	}

	@Test
	public void testDeleteCompanyOk() {
		int nbOldPC = 0;
		int nbNewPC = 0;
		try {
			nbOldPC = daoComputer.count();
			daoCompany.delete(4);
			nbNewPC = daoComputer.count();
		} catch (Exception e) {

		}
		assertTrue(nbOldPC - nbNewPC > 0);
	}

}
