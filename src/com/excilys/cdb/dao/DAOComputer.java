package com.excilys.cdb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.cdb.model.Computer;

public class DAOComputer extends DAO<Computer> {
	
	public final String CREATE = "INSERT INTO computer(id ,name, introduced, discontinued, company_id) " + "VALUES (NULL , ?, ?,?,?)"; 
	public final String DELETE = "DELETE FROM computer WHERE id = ";
	public final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ";
	private final String GET = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
	
	public DAOComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Computer computer) {  //fonctionne
		// TODO Auto-generated method stub
		try {
            PreparedStatement preparedStatement = connect.prepareStatement(CREATE);
            preparedStatement.setObject(1,  computer.getName());
            preparedStatement.setObject(2,  computer.getIntroduced());
            preparedStatement.setObject(3,  computer.getDiscontinuted());
            if(computer.getCompanyId()== 0 || computer.getCompanyId() == -1 ) {
            	preparedStatement.setObject(4,  null);
            }
            else{
            	preparedStatement.setObject(4,  computer.getCompanyId());
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return false;
	}
	
	public boolean delete(int id) {
		try {
			PreparedStatement preparedStatement = connect.prepareStatement(DELETE + id + ";");
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
	public boolean delete(Computer computer) { //fonctionne
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connect.prepareStatement(DELETE + computer.getId() + ";");
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
			PreparedStatement preparedStatement = connect.prepareStatement( UPDATE + computer.getId() +";");
			preparedStatement.setObject(1, computer.getName());
			preparedStatement.setObject(2, computer.getIntroduced());
			preparedStatement.setObject(3, computer.getDiscontinuted());
            if(computer.getCompanyId()== 0 || computer.getCompanyId() == -1 ) {
            	preparedStatement.setObject(4,  null);
            }
            else{
            	preparedStatement.setObject(4,  computer.getCompanyId());
            }
            preparedStatement.executeUpdate();
            preparedStatement.close(); 
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
			ResultSet result = super.connect.createStatement().executeQuery(GET);
			while(result.next()) {
				tmp = new Computer(result.getInt("id"), result.getString("name"),result.getDate("introduced"),result.getDate("discontinued"),
						result.getInt("company_id"), result.getString("company.name"));
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
					.createStatement()
					.executeQuery(GET +" WHERE computer.id = " + id );
			if (result.first())
				comp = new Computer(result.getInt("id"), result.getString("name"),result.getDate("introduced"),result.getDate("discontinued"), 
						result.getInt("company_id"),result.getString("company.name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}




}
