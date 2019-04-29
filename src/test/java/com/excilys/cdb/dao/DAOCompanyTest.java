package com.excilys.cdb.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.model.Company;


public class DAOCompanyTest {
	DAOCompany daoCompany;
	@Before
	public void setUp() throws Exception {
		daoCompany = DAOCompany.getInstance();
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
		assertTrue(alCompany.size() == 42);
	}
	
	@Test
	public void testPaginationCorrect() {
		ArrayList<Company> alCompany = null;
		try {
			alCompany = daoCompany.findPagination(5, 10);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(alCompany.size() == 5 && alCompany.get(0).getId() >= 10);
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
	
}
