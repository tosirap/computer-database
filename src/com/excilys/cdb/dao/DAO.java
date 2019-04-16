package com.excilys.cdb.dao;
//requete sql type JDBC

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO <T>{
	
	  protected Connection connect = null;

	  /*public DAO(Connection conn){
		  this.connect = conn;
	  }*/
	  public DAO() {
		  if(this.connect==null) {
			  try {
				  Class.forName("com.mysql.cj.jdbc.Driver");
				  this.connect= DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC",
						  "admincdb","qwerty1234");
				  System.out.println(connect);
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }
			  catch(ClassNotFoundException e) {
				  System.out.println("Erreur class");
				  e.printStackTrace();
			  }
		  }
	  }

	  
	  /**

	  * Méthode de création

	  * @param obj

	  * @return boolean 

	  */
	  public abstract boolean create(T obj);

	  /**

	  * Méthode pour effacer

	  * @param obj

	  * @return boolean 

	  */
	  public abstract boolean delete(T obj);

	  /**

	  * Méthode de mise à jour

	  * @param obj

	  * @return boolean

	  */
	  public abstract boolean update(T obj);
	  

	  /**

	  * Méthode de recherche des informations

	  * @param id

	  * @return T

	  */
	  public abstract T find(int id);
}
