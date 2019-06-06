package com.excilys.cdb.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.QRole;
import com.excilys.cdb.model.QUser;
import com.excilys.cdb.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
@Transactional(propagation = Propagation.NESTED)
public class DAOUser {
	@PersistenceContext
	EntityManager entityManager;
	private QUser qUser = QUser.user;
	private QRole qRole = QRole.role;
	private final JPAQueryFactory jpaQueryFactory;
	
	public DAOUser(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	public void createUser(User user) {
		entityManager.persist(user);
	}
	public void delete(User user) {
		this.jpaQueryFactory.delete(qUser).where(qUser.id.eq(user.getId())).execute();
	}
	public void update(User user) {
		this.jpaQueryFactory.update(qUser).where(qUser.id.eq(user.getId())).set(qUser.pseudo, user.getPseudo()).set(qUser.password, user.getPassword())
		.set(qUser.role, user.getRole()).execute(); 
	}
	
	public User find(Long id){ // fonctionne
		return this.jpaQueryFactory.selectFrom(qUser).where(qUser.id.eq(id)).fetchOne();
	}

	public User findbyName(String name){
		return this.jpaQueryFactory.selectFrom(qUser).where(qUser.pseudo.eq(name)).fetchOne();
	}
}
