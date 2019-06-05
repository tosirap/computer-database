package com.excilys.cdb.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.enums.OrderBy;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.QCompany;
import com.excilys.cdb.model.QComputer;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@Transactional(propagation = Propagation.NESTED)
public class DAOComputer {


	@PersistenceContext
	EntityManager entityManager;
	private QComputer qComputer = QComputer.computer;
	private QCompany qCompany = QCompany.company;
	private final JPAQueryFactory jpaQueryFactory;

	public DAOComputer( JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	public boolean create(Computer computer){ // fonctionne
		entityManager.persist(computer);
		return true;
	}

	public boolean delete(long id) {
		this.jpaQueryFactory.delete(qComputer).where(qComputer.id.eq(id)).execute();
		return true;
	}

	public boolean delete(Computer computer){ // fonctionne
		delete(computer.getId());
		return true;

	}

	public boolean update(Computer computer) { // fonctionne
		this.jpaQueryFactory.update(qComputer).where(qComputer.id.eq(computer.getId()))
				.set(qComputer.name, computer.getName()).set(qComputer.introduced, computer.getIntroduced())
				.set(qComputer.discontinued, computer.getDiscontinuted()).set(qComputer.company, computer.getCompany()).execute();
		return true;

	}

	public ArrayList<Computer> findAll() {// to do
		return new ArrayList<Computer>(this.jpaQueryFactory.selectFrom(qComputer).fetch());
	}

	public Computer find(Long id){ // fonctionne
		return this.jpaQueryFactory.selectFrom(qComputer).where(qComputer.id.eq(id)).fetchOne();
	}

	public Computer findbyName(String namePC){
		return this.jpaQueryFactory.selectFrom(qComputer).where(qComputer.name.eq(namePC)).fetchOne();
	}

	public ArrayList<Computer> findPagination(int limit, int offset, OrderBy orderby) {
		return new ArrayList<Computer>(this.jpaQueryFactory.selectFrom(qComputer).orderBy(orderby.getField()).limit(limit).offset(offset).fetch());

	}

	public int count() {
		return (int)this.jpaQueryFactory.selectFrom(qComputer).fetchCount();
	}

	public ArrayList<Computer> searchComputer(String string, int limit, int offset, OrderBy orderby) {
		return new ArrayList<Computer>(this.jpaQueryFactory.selectFrom(qComputer)
				.where(qComputer.name.like("%"+string+"%").or(qCompany.name.like("%"+string+"%"))).orderBy(orderby.getField()).limit(limit).offset(offset).fetch());

	}

	public int searchComputerCount(String string){
		return (int)( this.jpaQueryFactory.selectFrom(qComputer).where(qComputer.name.like("%"+string+"%").or(qCompany.name.like("%"+string+"%"))).fetchCount() );
	}

	public boolean deleteByCompanyId(Long id) {
		this.jpaQueryFactory.delete(qComputer).where(qComputer.id.eq(id)).execute();
		return true;
	}

}
