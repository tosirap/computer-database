package com.excilys.test.persistence.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Company;
import com.excilys.test.persistence.config.TestConfigPersistence;
import com.excilys.test.persistence.database.UTDatabase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfigPersistence.class)
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
		Company companyExpected = new Company((long) 1, "Apple Inc.");
		Company companyResult = null;
		companyResult = daoCompany.find((long) 1);

		assertEquals(companyExpected, companyResult);
	}

	@Test
	public void testFindInCorrect1() {
		Company companyResult = null;
		companyResult = daoCompany.find((long) -1);
		assertEquals(companyResult, null);
	}

	@Test
	public void testFindInCorrect2() {
		Company companyResult = null;
		companyResult = daoCompany.find((long) 555);
		assertEquals(companyResult, null);
	}

	@Test
	public void testFindAllCorrect() {
		ArrayList<Company> alCompany = null;
		alCompany = daoCompany.findAll();
		assertTrue(alCompany.size() == 10);
	}

	@Test
	public void testFindbyNameCorrect() {
		Company companyExpected = new Company((long) 1, "Apple Inc.");
		Company companyResult = null;
		companyResult = daoCompany.find("Apple Inc.");
		assertEquals(companyExpected, companyResult);
	}

	@Test
	public void testFindbyNameInCorrect() {
		Company companyResult = null;

		companyResult = daoCompany.find("Auhfuefheue.");

		assertTrue(companyResult == null);
	}

	@Test
	public void testPaginationCorrect() {
		ArrayList<Company> alCompany = null;
		alCompany = daoCompany.findPagination(2, 4);

		assertTrue(alCompany.size() == 2 && alCompany.get(0).getId() >= 4);
	}

	@Test
	public void testPaginationInCorrect1() {
		ArrayList<Company> alCompany = null;

		alCompany = daoCompany.findPagination(5, 10000);

		assertTrue(alCompany.isEmpty());
	}

	@Test
	public void testPaginationInCorrect2() {
		ArrayList<Company> alCompany = null;

		alCompany = daoCompany.findPagination(5, -10);

		assertTrue(alCompany.isEmpty());
	}

	@Test
	public void testDeleteCompanyOk() {
		int nbOldPC = 0;
		int nbNewPC = 0;
		try {
			nbOldPC = daoComputer.count();
			daoCompany.delete((long) 4);
			nbNewPC = daoComputer.count();
		} catch (Exception e) {

		}
		assertTrue(nbOldPC - nbNewPC > 0);
	}

}
