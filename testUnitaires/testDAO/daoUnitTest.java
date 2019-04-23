package com.excilys.cdb.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class daoUnitTest {

	private Computer computer;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		computer = new Computer(2,"bloblo", new Date("2017-11-11"), new Date("2017-11-11"), 4);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		computer = null;
				
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	/*@Test
	public void daoComputerInsert() {
		DAOComputer.insert(computer);
		
	}*/

}
