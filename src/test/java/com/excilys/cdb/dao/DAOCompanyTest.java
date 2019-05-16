package com.excilys.cdb.dao;

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
		Company companyExpected  = new Company (1,"Apple Inc.");
		Company companyResult = null;
		try {
			companyResult = daoCompany.find(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(companyExpected,companyResult);
	}
	
	@Test
	public void testFindInCorrect1() {
		Company companyResult = null;
		try {
			companyResult = daoCompany.find(-1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(companyResult,null);
	}
	
	@Test
	public void testFindInCorrect2() {
		Company companyResult = null;
		try {
			companyResult = daoCompany.find(555);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(companyResult,null);
	}
	
	@Test
	public void testFindAllCorrect() {
		ArrayList<Company> alCompany = null;
		try {
			alCompany = daoCompany.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alCompany.size() == 10);
	}
	
	@Test
	public void testFindbyNameCorrect() {
		Company companyExpected  = new Company (1,"Apple Inc.");
		Company companyResult = null;
		try {
			companyResult = daoCompany.find("Apple Inc.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(companyExpected,companyResult);
	}
	
	@Test
	public void testFindbyNameInCorrect() {
		Company companyResult = null;
		try {
			companyResult = daoCompany.find("Auhfuefheue.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(companyResult==null);
	}
	
	@Test
	public void testPaginationCorrect() {
		ArrayList<Company> alCompany = null;
		try {
			alCompany = daoCompany.findPagination(2, 4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alCompany.size() == 2 && alCompany.get(0).getId() >= 4);
	}
	
	@Test
	public void testPaginationInCorrect1() {
		ArrayList<Company> alCompany = null;
		try {
			alCompany = daoCompany.findPagination(5, 10000);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alCompany.isEmpty());
	}
	
	@Test
	public void testPaginationInCorrect2() {
		ArrayList<Company> alCompany = null;
		try {
			alCompany = daoCompany.findPagination(5, -10);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alCompany.isEmpty());
	}
	
	@Test
	public void testDeleteCompanyOk() {
		int nbOldPC = 0;
		int nbNewPC = 0;
		try {
			nbOldPC = daoComputer.count();
			daoCompany.delete(4);
			nbNewPC = daoComputer.count();
		}
		catch(Exception e) {
			
		}
		assertTrue(nbOldPC-nbNewPC>0);
	}
	
	
}
