package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO<T> {

	protected Connection connect = null;

	/*
	 * public DAO(Connection conn){ this.connect = conn; }
	 */
	public DAO() {
		if (this.connect == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC", "admincdb",
						"qwerty1234");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Erreur class");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * Méthode de création
	 * 
	 * @param obj
	 * 
	 * @return boolean
	 * 
	 */
	public abstract boolean create(T obj);

	/**
	 * 
	 * Méthode pour effacer
	 * 
	 * @param obj
	 * 
	 * @return boolean
	 * 
	 */
	public abstract boolean delete(T obj);

	/**
	 * 
	 * Méthode de mise à jour
	 * 
	 * @param obj
	 * 
	 * @return boolean
	 * 
	 */
	public abstract boolean update(T obj);

	/**
	 * 
	 * Méthode de recherche des informations
	 * 
	 * @param id
	 * 
	 * @return T
	 * 
	 */
	public abstract T find(int id);

	/**
	 * 
	 * Affiche tout les elements
	 * 
	 * @param id
	 * 
	 * @return T
	 * 
	 */
	public abstract ArrayList<T> findAll();
}
