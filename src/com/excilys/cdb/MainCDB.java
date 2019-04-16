package com.excilys.cdb;

import java.util.ArrayList;

import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.vue.UI;

public class MainCDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//UI.operations();
		DAOComputer dao = new DAOComputer();
		ArrayList<Computer> bla = dao.findAll();
		for(Computer c : bla) {
			System.out.println(c.toString());
		}
		
		
	}

}
