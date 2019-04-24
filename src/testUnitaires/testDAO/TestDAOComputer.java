package testUnitaires.testDAO;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Computer;

public class TestDAOComputer {

//private Computer computer;
	DAOComputer daoComp;

	@Before
	public void setUp() throws Exception {
		// computer = new Computer(2,"bloblo", new Date("2017-11-11"), new
		// Date("2017-11-11"), 4);
		daoComp = DAOComputer.getInstance();
		daoComputerInsert();
	}

	@After
	public void tearDown() throws Exception {
		//computer = null;

	}

	@Test
	public void daoComputerInsert() {
		Computer actual = daoComp.find(12);
		System.out.println(actual.toString());
		Computer expected = new Computer("12", "Apple III", "1980-05-01", "1984-04-01", "1",
				"Apple Inc.");
		assertEquals(actual, expected);
	}

}
