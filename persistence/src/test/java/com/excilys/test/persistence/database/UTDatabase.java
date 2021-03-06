package com.excilys.test.persistence.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@Component
public class UTDatabase {

	private static final String ENTRIES_SQL = "entriesUT.sql";
	private static final String SCHEMA_SQL = "schema.sql";
	private static  DataSource dataSource;

	private Map<Integer, Company> companies = new TreeMap<>();
	private Map<Integer, Computer> computers = new TreeMap<>();

	public UTDatabase(DataSource dataSource) {
		super();
		addCompanies();
		addComputers();
		UTDatabase.dataSource =dataSource;

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
		final Company company = new Company((long)id, name);
		companies.put(id, company);
	}

	private void addComputers() {
		addComputer(1, "MacBook Pro 15.4 inch", null, null, 1);
		addComputer(2, "CM-2a", null, null, 2);
		addComputer(3, "CM-200", null, null, 2);
		addComputer(4, "CM-5e", null, null, 2);
		addComputer(5, "CM-5", Date.valueOf("1991-1-1"), null, 2);
		addComputer(6, "MacBook Pro", Date.valueOf("2006-1-10"), null, 1);
		addComputer(7, "Apple IIe", null, null, -1);
		addComputer(8, "Apple IIc", null, null, -1);
		addComputer(9, "Apple IIGS", null, null, -1);
		addComputer(10, "Apple IIc Plus", null, null, -1);
		addComputer(11, "Apple II Plus", null, null, -1);
		addComputer(12, "Apple III", Date.valueOf("1980-5-1"), Date.valueOf("1984-4-1"), 1);
		addComputer(13, "Apple Lisa", null, null, 1);
		addComputer(14, "CM-2", null, null, 2);
		addComputer(15, "Connection Machine", Date.valueOf("1987-1-1"), null, 2);
		addComputer(16, "Apple II", Date.valueOf("1977-4-1"), Date.valueOf("1993-10-1"), 1);
		addComputer(17, "Apple III Plus", Date.valueOf("1983-12-1"), Date.valueOf("1984-4-1"), 1);
		addComputer(18, "COSMAC ELF", null, null, 3);
		addComputer(19, "COSMAC VIP", Date.valueOf("1977-1-1"), null, 3);
		addComputer(20, "ELF II", Date.valueOf("1977-1-1"), null, 4);
	}

	private void addComputer(int id, String name, Date introduced, Date discontinued, int companyId) {

		final Computer computer = new Computer((long)id, name, introduced, discontinued, (long)companyId, "");
		computers.put(id, computer);
	}

	/*
	 * Ne retire pas les lignes de commentaires risque de ne pas marcher avec
	 */
	private static void executeScript(String filename) throws SQLException, IOException {
		try (Connection connection = dataSource.getConnection();
				final Statement statement = connection.createStatement();
				final InputStream resourceAsStream = UTDatabase.class.getClassLoader().getResourceAsStream(filename);
				final Scanner scanner = new Scanner(resourceAsStream)) {

			StringBuilder sb = new StringBuilder();
			while (scanner.hasNextLine()) {
				final String nextLine = scanner.nextLine();
				sb.append(nextLine);
			}
			final StringTokenizer stringTokenizer = new StringTokenizer(sb.toString(), ";");

			while (stringTokenizer.hasMoreTokens()) {
				final String nextToken = stringTokenizer.nextToken();
				statement.execute(nextToken);
			}
		}
	}

	public Company findCompanyById(int id) {
		return Objects.nonNull(id) ? companies.get(id) : null;
	}

	public Computer findComputerById(int id) {
		return computers.get(id);
	}

	public List<Computer> findAllComputers() {
		return computers.values().stream().sorted(Comparator.comparingLong(Computer::getId))
				.collect(Collectors.toList());
	}

	public List<Computer> findAllComputers(int offset, int limit) {
		return findAllComputers().stream().skip(offset).limit(limit).collect(Collectors.toList());
	}

	public List<Company> findAllCompanies() {
		return companies.values().stream().sorted(Comparator.comparingLong(Company::getId))
				.collect(Collectors.toList());
	}

	public void reload() throws IOException, SQLException {
		executeScript(SCHEMA_SQL);
		executeScript(ENTRIES_SQL);
	}

	public List<Company> findAllCompanies(int offset, int limit) {
		return findAllCompanies().stream().skip(offset).limit(limit).collect(Collectors.toList());
	}

}
