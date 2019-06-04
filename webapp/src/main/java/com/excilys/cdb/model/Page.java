package com.excilys.cdb.model;

import org.slf4j.Logger;
import com.excilys.cdb.enums.OrderBy;
import org.slf4j.LoggerFactory;

public class Page {

	Logger logger = LoggerFactory.getLogger(Page.class);

	@Override
	public String toString() {
		return "Page [offset=" + offset + ", PCparPageInt=" + PCparPageInt + ", pageInt=" + pageInt + ", nbPcTotal="
				+ nbPcTotal + ", nbPageTotal=" + nbPageTotal + ", search=" + search + ", begin=" + begin + ", end="
				+ end + ", orderBy=" + orderBy +  ", lang=" + lang + "]";
	}

	private int offset;
	private int PCparPageInt = 10;
	private int pageInt = 1;
	private int nbPcTotal;

	private int nbPageTotal;
	private String search;

	private int begin;
	private int end;
	private OrderBy orderBy = OrderBy.COMPUTER_ID;
	private String lang;

	public Page() {
	}

	public void createPage() {
		this.nbPageTotal = nbPcTotal / this.PCparPageInt;
		if (nbPcTotal % this.PCparPageInt != 0)

		{
			this.nbPageTotal += 1;
		}

		if (this.nbPageTotal <= 5) {
			this.begin = 1;
			this.end = this.nbPageTotal;
		} else {
			if (this.pageInt <= 3) {
				this.begin = 1;
				this.end = 5;
			} else if (this.pageInt >= this.nbPageTotal - 3) {
				this.begin = this.nbPageTotal - 6;
				this.end = this.nbPageTotal;
			} else {
				this.begin = this.pageInt - 3;
				this.end = this.pageInt + 3;
			}

		}
		if (this.pageInt > this.nbPageTotal) {
			this.pageInt = this.nbPageTotal - 1;
		}

	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setOffset() {
		this.offset = (PCparPageInt * (pageInt - 1));
	}

	public int getPCparPage() {
		return PCparPageInt;
	}

	public void setPCparPage(int pCparPageInt) {
		PCparPageInt = pCparPageInt;
	}

	public int getPageInt() {
		return pageInt;
	}

	public void setPageInt(int pageInt) {
		this.pageInt = pageInt;
	}

	public int getNbPageTotal() {
		return nbPageTotal;
	}

	public void setNbPageTotal(int nbPageTotal) {
		this.nbPageTotal = nbPageTotal;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public OrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}

	public void setOrderBy(String orderbyStr) {
		if (orderbyStr == null || orderbyStr.isEmpty()) {
			return;
		} else {
			for (OrderBy ob : OrderBy.values()) {
				if (ob.toString().equals(orderbyStr)) {
					this.orderBy = ob;
				}
			}
		}
	}


	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getNbPcTotal() {
		return nbPcTotal;
	}

	public void setNbPcTotal(int nbPcTotal) {
		this.nbPcTotal = nbPcTotal;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
}
