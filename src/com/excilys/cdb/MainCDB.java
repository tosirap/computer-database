package com.excilys.cdb;

import java.sql.Date;
import java.util.ArrayList;

import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.vue.UI;

public class MainCDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//UI.operations();
		DAOComputer dao = new DAOComputer();
		Date d = Date.valueOf("2017-09-24");
		Date d2 = Date.valueOf("2018-09-24");
		Computer computerInsert = new Computer(-1,"ryzen", d,d2,4);
		computerInsert.setId(575);
		dao.delete(computerInsert);
		ArrayList<Computer> bla = dao.findAll();
		for(Computer c : bla) {
			System.out.println(c.toString());
		}
		
		Computer cpt = dao.find(17);
		System.out.println(cpt.toString());
	}

}
