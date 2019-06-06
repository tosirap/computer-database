package com.excilys.cdb.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.QRole;
import com.excilys.cdb.model.Role;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
@Transactional(propagation = Propagation.NESTED)
public class DAORole {
	@PersistenceContext
	EntityManager entityManager;
	private QRole qRole = QRole.role;
	private final JPAQueryFactory jpaQueryFactory;
	
	public DAORole(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}
	public void createRole(Role role) {
		entityManager.persist(role);
	}
	public void delete(Role role) {
		this.jpaQueryFactory.delete(qRole).where(qRole.id.eq(role.getId())).execute();
	}
	public void update(Role role) {
		this.jpaQueryFactory.update(qRole).where(qRole.id.eq(role.getId())).set(qRole.name, role.getName()).execute(); 
	}
	
	public Role find(Long id){ // fonctionne
		return this.jpaQueryFactory.selectFrom(qRole).where(qRole.id.eq(id)).fetchOne();
	}

	public Role findbyName(String name){
		return this.jpaQueryFactory.selectFrom(qRole).where(qRole.name.eq(name)).fetchOne();
	}
}
