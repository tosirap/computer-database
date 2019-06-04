package com.excilys.cdb.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.QCompany;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
@Transactional(propagation = Propagation.NESTED)
public class DAOCompany {

	static Logger logger = LoggerFactory.getLogger(DAOCompany.class);
	// protected Connection connect;

	@PersistenceContext EntityManager entityManager;
	private QCompany qCompany = QCompany.company;
	private final JPAQueryFactory jpaQueryFactory;
	
	public DAOCompany(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
		
	}

	public Company find(Long id) throws DataAccessException {
		return this.jpaQueryFactory.selectFrom(qCompany).where(qCompany.id.eq(id)).fetchOne();
	}

	public Company find(String companyName) throws DataAccessException {
		return this.jpaQueryFactory.selectFrom(qCompany).where(qCompany.name.eq(companyName)).fetchOne();
	}

	public ArrayList<Company> findAll() throws DataAccessException { // fonctionne
		return new ArrayList<Company>(this.jpaQueryFactory.selectFrom(qCompany).fetch());
	}

	public ArrayList<Company> findPagination(int limit, int offset) throws DataAccessException {
		return new ArrayList<Company>(this.jpaQueryFactory.selectFrom(qCompany).limit(limit).offset(offset).fetch());
	}

	public boolean delete(Long id) throws DataAccessException {
		this.jpaQueryFactory.delete(qCompany).where(qCompany.id.eq(id)).execute();
		return true;
	}

}
