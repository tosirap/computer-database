package com.excilys.cdb.enums;

import com.excilys.cdb.model.QCompany;
import com.excilys.cdb.model.QComputer;
import com.querydsl.core.types.OrderSpecifier;

public enum OrderBy {
	COMPUTER_ID("computer.id true", QComputer.computer.id.asc()),
	COMPUTER_NAME("computer.name true", QComputer.computer.name.asc()),
	COMPUTER_INTRODUCED("computer.introduced true", QComputer.computer.introduced.asc()),
	COMPUTER_DISCONTINUED("computer.discontinued true", QComputer.computer.discontinued.asc()),
	COMPANY_NAME("company.name true", QCompany.company.name.asc()),
	COMPUTER_ID_REVERSE("computer.id false", QComputer.computer.id.desc()),
	COMPUTER_NAME_REVERSE("computer.name false", QComputer.computer.name.desc()),
	COMPUTER_INTRODUCED_REVERSE("computer.introduced false", QComputer.computer.introduced.desc()),
	COMPUTER_DISCONTINUED_REVERSE("computer.discontinued false", QComputer.computer.discontinued.desc()),
	COMPANY_NAME_REVERSE("company.name false", QCompany.company.name.desc());

	private String name = "";
	private OrderSpecifier<?> field;
	
	
	OrderBy(String name, OrderSpecifier<?> field) {
		this.name = name;
		this.field = field;
	}

	public static OrderBy getOrderByField(String s) {
		for (OrderBy comd : OrderBy.values()) {
			if (comd.name.contentEquals(s)) {
				return comd;
			}
		}
		return COMPUTER_ID;
	}

	public OrderSpecifier<?> getField() {
		return field;
} 
	
	@Override
	public String toString() {
		return name;

	}
}
