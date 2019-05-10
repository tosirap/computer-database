package com.excilys.cdb;

import java.sql.SQLException;

import com.excilys.cdb.dao.DAOComputer;


public class MainCDB {

	public static void main(String[] args) {
		/*UI ui = new UI();
		ui.operations();*/
		DAOComputer dao = null;
		try {
			dao = DAOComputer.getInstance();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("eaea"+dao.count());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
