package com.excilys.cdb.database;

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

	private List<Company> companies = new ArrayList<Company>();
	private List<Computer> computers = new ArrayList<Computer>();

	public UTDatabase(DataSource dataSource) {
		super();
		addCompanies();
		addComputers();
		this.dataSource =dataSource;

	}

	private void addCompanies() {
		addCompany((long)1, "Apple Inc.");
		addCompany((long)2, "Thinking Machines");
		addCompany((long)3, "RCA");
		addCompany((long)4, "Netronics");
		addCompany((long)5, "Tandy Corporation");
		addCompany((long)6, "Commodore International");
		addCompany((long)7, "MOS Technology");
		addCompany((long)8, "Micro Instrumentation and Telemetry Systems");
		addCompany((long)9, "IMS Associates, Inc.");
		addCompany((long)10, "Digital Equipment Corporation");
	}

	private void addCompany(Long id, String name) {
		final Company company = new Company(id, name);
		companies.add(company);
	}

	private void addComputers() {
		addComputer((long)1, "MacBook Pro 15.4 inch", null, null, (long)1);
		addComputer((long)2, "CM-2a", null, null, (long)2);
		addComputer((long)3, "CM-200", null, null, (long)2);
		addComputer((long)4, "CM-5e", null, null, (long)2);
		addComputer((long)5, "CM-5", Date.valueOf("1991-1-1"), null, (long)2);
		addComputer((long)6, "MacBook Pro", Date.valueOf("2006-1-10"), null, (long)1);
		addComputer((long)7, "Apple IIe", null, null, (long)-1);
		addComputer((long)8, "Apple IIc", null, null, (long)-1);
		addComputer((long)9, "Apple IIGS", null, null,(long) -1);
		addComputer((long)10, "Apple IIc Plus", null, null, (long)-1);
		addComputer((long)11, "Apple II Plus", null, null, (long)-1);
		addComputer((long)12, "Apple III", Date.valueOf("1980-5-1"), Date.valueOf("1984-4-1"), (long)1);
		addComputer((long)13, "Apple Lisa", null, null, (long)1);
		addComputer((long)14, "CM-2", null, null, (long)2);
		addComputer((long)15, "Connection Machine", Date.valueOf("1987-1-1"), null, (long)2);
		addComputer((long)16, "Apple II", Date.valueOf("1977-4-1"), Date.valueOf("1993-10-1"), (long)1);
		addComputer((long)17, "Apple III Plus", Date.valueOf("1983-12-1"), Date.valueOf("1984-4-1"), (long)1);
		addComputer((long)18, "COSMAC ELF", null, null, (long)3);
		addComputer((long)19, "COSMAC VIP", Date.valueOf("1977-1-1"), null, (long)3);
		addComputer((long)20, "ELF II", Date.valueOf("1977-1-1"), null, (long)4);
	}

	private void addComputer(Long id, String name, Date introduced, Date discontinued, Long companyId) {

		final Computer computer = new Computer(id, name, introduced, discontinued, companyId, "");
		computers.add(computer);
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
			while (scanner.hasNextLine()){
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
		return computers;
	}

	public List<Computer> findAllComputers(int offset, int limit) {
		return findAllComputers().stream().skip(offset).limit(limit).collect(Collectors.toList());
	}

	public List<Company> findAllCompanies() {
		return companies;
	}

	public void reload() throws IOException, SQLException {
		executeScript(SCHEMA_SQL);
		executeScript(ENTRIES_SQL);
	}

	public List<Company> findAllCompanies(int offset, int limit) {
		return findAllCompanies().stream().skip(offset).limit(limit).collect(Collectors.toList());
	}

}
