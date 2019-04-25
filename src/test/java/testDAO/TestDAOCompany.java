package testDAO;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.model.Company;


public class TestDAOCompany {
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
		Company companyResult = daoCompany.find(1);
		assertEquals(companyExpected,companyResult);
	}
	
	@Test
	public void testFindInCorrect1() {
		Company companyResult = daoCompany.find(-1);
		assertEquals(companyResult,null);
	}
	
	@Test
	public void testFindInCorrect2() {
		Company companyResult = daoCompany.find(555);
		assertEquals(companyResult,null);
	}
	
	@Test
	public void testFindAllCorrect() {
		ArrayList<Company> alCompany = daoCompany.findAll();
		assertTrue(alCompany.size() == 42);
	}
	
	@Test
	public void testPaginationCorrect() {
		ArrayList<Company> alCompany = daoCompany.findPagination(5, 10);
		assertTrue(alCompany.size() == 5 && alCompany.get(0).getId() >= 10);
	}
	
	@Test
	public void testPaginationinCorrect1() {
		ArrayList<Company> alCompany = daoCompany.findPagination(5, 10000);
		assertTrue(alCompany.isEmpty());
	}
	
	@Test
	public void testPaginationinCorrect2() {
		ArrayList<Company> alCompany = daoCompany.findPagination(5, -10);
		assertTrue(alCompany.isEmpty());
	}
	
}
