package com.excilys.cdb;

import java.sql.Date;
import java.util.ArrayList;

import com.excilys.cdb.dao.DAOCompany;
import com.excilys.cdb.dao.DAOComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ServiceCompany;
import com.excilys.cdb.transfert.DTOCompany;
import com.excilys.cdb.vue.UI;

public class MainCDB {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//UI.operations();
		/*DAOComputer dao = new DAOComputer();
		Date d = Date.valueOf("2014-09-24");
		Date d2 = Date.valueOf("2015-09-24");
		Computer computerInsert = new Computer(568,"", null,null,3);
		//computerInsert.setId(575);
		//dao.create(computerInsert);
		dao.delete(computerInsert);
		ArrayList<Computer> bla = dao.findAll();
		for(Computer c : bla) {
			System.out.println(c.toString());
		}*/
		
		/*Computer cpt = dao.find(17);
		System.out.println(cpt.toString());*/
		
		/*DAOCompany dao2 = new DAOCompany();
		ArrayList<Company> companys = dao2.findAll();
		for(Company c : companys) {
			System.out.println(c.toString());
		}*/
		/*ServiceCompany scp = new ServiceCompany();
		ArrayList<DTOCompany> alDto = scp.listAllElements();
		System.out.println(alDto.size());
		for(DTOCompany c : alDto) {
			System.out.println(c.toString());
		}*/
		UI ui = new UI();
		ui.operations();
	}
	
}
