package com.excilys.cdb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.cdb.model.Computer;

public class DAOComputer extends DAO<Computer> {
	

	public DAOComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Computer computer) {  //fonctionne
		// TODO Auto-generated method stub
		try {
            PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO computer(id ,name, introduced, discontinued, company_id) "
            		+ "VALUES (NULL , ?, ?,?,?)");
            preparedStatement.setString(1,  computer.getName());
            preparedStatement.setString(2,  String.valueOf(computer.getIntroduced()));
            preparedStatement.setString(3,  String.valueOf(computer.getDiscontinuted()));
            preparedStatement.setString(4,  String.valueOf(computer.getCompanyId()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean delete(Computer computer) { //fonctionne
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM computer WHERE id = " + computer.getId() + ";");
			preparedStatement.executeUpdate(); 
			return true;
		}
		catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean update(Computer computer) { //fonctionne
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement ps = connect.prepareStatement("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = "
		+ computer.getId() +";");
            ps.setString(1, computer.getName());
            ps.setDate(2, computer.getIntroduced());
            ps.setDate(3, computer.getDiscontinuted());
            ps.setInt(4, computer.getCompanyId());
            ps.executeUpdate();
            ps.close(); 
            System.out.println("Update done");
			return true;
		
		}
		catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return false;
	}

	public ArrayList<Computer> findAll(){  //fonctionne
		ArrayList<Computer> retAL = new ArrayList<Computer>();
		Computer tmp;
		try{
			ResultSet result = super.connect.createStatement().executeQuery("SELECT * FROM computer");
			while(result.next()) {
				tmp = new Computer(result.getString("id"), result.getString("name"),result.getDate("introduced"),result.getDate("discontinued"), result.getString("company_id"));
				retAL.add(tmp);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
		return retAL;
	}
	
	@Override
	public Computer find(int id) {  //fonctionne
		// TODO Auto-generated method stub
		Computer comp = new Computer();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM computer WHERE id = " + id);
			if (result.first())
				comp = new Computer(String.valueOf(id), result.getString("name"),result.getDate("introduced"),result.getDate("discontinued"), result.getString("company_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}




}
