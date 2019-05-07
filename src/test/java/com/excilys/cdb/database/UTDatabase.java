package com.excilys.cdb.database;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class UTDatabase {

	private static final String ENTRIES_SQL = "entriesUT.sql";
	private static final String SCHEMA_SQL = "schema.sql";
	private static UTDatabase INSTANCE = null;
	
	private Map<Integer, Company> companies = new TreeMap<>();
	private Map<Integer, Computer> computers = new TreeMap<>();

	private UTDatabase() {
		addCompanies();
		addComputers();
	}


	public static UTDatabase getInstance() throws ClassNotFoundException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new UTDatabase();
		}
		return INSTANCE;
	}


    private void addCompanies() {
        addCompany(1, "Apple Inc.");
        addCompany(2, "Thinking Machines");
        addCompany(3, "RCA");
        addCompany(4, "Netronics");
        addCompany(5, "Tandy Corporation");
        addCompany(6, "Commodore International");
        addCompany(7, "MOS Technology");
        addCompany(8, "Micro Instrumentation and Telemetry Systems");
        addCompany(9, "IMS Associates, Inc.");
        addCompany(10, "Digital Equipment Corporation");
    }

    private void addCompany(int id, String name) {
        final Company company = new Company(id,name);
        companies.put(id, company);
	}
    
    private void addComputers() {
        addComputer(1, "MacBook Pro 15.4 inch", null, null, 1);
        addComputer(2, "CM-2a", null, null, 2);
        addComputer(3, "CM-200", null, null, 2);
        addComputer(4, "CM-5e", null, null, 2);
        addComputer(5, "CM-5", Date.valueOf("1991, 1, 1"), null, 2);
        addComputer(6, "MacBook Pro",  Date.valueOf("2006, 1, 10"), null, 1);
        addComputer(7, "Apple IIe", null, null, -1);
        addComputer(8, "Apple IIc", null, null, -1);
        addComputer(9, "Apple IIGS", null, null, -1);
        addComputer(10, "Apple IIc Plus", null, null, -1);
        addComputer(11, "Apple II Plus", null, null, -1);
        addComputer(12, "Apple III", Date.valueOf("1980, 5, 1"), Date.valueOf("1984, 4, 1"), 1);
        addComputer(13, "Apple Lisa", null, null, 1);
        addComputer(14, "CM-2", null, null, 2);
        addComputer(15, "Connection Machine", Date.valueOf("1987, 1, 1"), null, 2);
        addComputer(16, "Apple II", Date.valueOf("1977, 4, 1"), Date.valueOf("1993, 10, 1"), 1);
        addComputer(17, "Apple III Plus", Date.valueOf("1983, 12, 1"), Date.valueOf("1984, 4, 1"), 1);
        addComputer(18, "COSMAC ELF", null, null, 3);
        addComputer(19, "COSMAC VIP", Date.valueOf("1977, 1, 1"), null, 3);
        addComputer(20, "ELF II", Date.valueOf("1977, 1, 1"), null, 4);
    }

    private void addComputer(int id, String name, Date introduced, Date discontinued, int companyId) {
		final Computer computer = new Computer(id,name,introduced,discontinued,companyId, "");
        computers.put(id, computer);
}
}
