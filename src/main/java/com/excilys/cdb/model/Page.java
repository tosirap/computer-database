package com.excilys.cdb.model;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Page {

	Logger logger = LoggerFactory.getLogger(Page.class);

	// private List<DTOComputer> listDTOComputer = new ArrayList<DTOComputer>();
	private int offset;
	private int PCparPageInt = 10;
	private int pageInt = 1;
	private int nbPageTotal;
	private int begin;
	private int end;
	private OrderBy orderBy = OrderBy.COMPUTER_ID;
	private boolean ascendant = false; // ASC /DESC de la bdd

	public Page(String pageNum, String pCparPage, String orderbyStr, String asc) {
		if (pageNum == null || pageNum.isEmpty()) {
			this.pageInt = 1;
		} else {
			try {
				this.pageInt = Integer.parseInt(pageNum);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		if (pCparPage == null || pCparPage.isEmpty()) {
			this.PCparPageInt = 10;
		} else {
			try {
				this.PCparPageInt = Integer.parseInt(pCparPage);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		if (this.PCparPageInt < 1) {
			this.PCparPageInt = 1;
		}
		this.offset = (PCparPageInt * (pageInt - 1));
		if (orderbyStr == null || orderbyStr.isEmpty()) {
			this.orderBy = OrderBy.COMPUTER_ID;
		} else {
			for (OrderBy ob : OrderBy.values()) {
				if (ob.toString().equals(orderbyStr)) {
					this.orderBy = ob;
				}
			}
		}
		if (asc == null || asc.isEmpty() || !asc.equals("true")) {
			this.ascendant = false;
		} else {
			this.ascendant = true;

		}
	}

	public void createPage(int nbComputer) {

		this.nbPageTotal = nbComputer / this.PCparPageInt;
		if (nbComputer % this.PCparPageInt != 0)

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

	/*public void instanciationPage(int nbComputer) {
		this.nbPageTotal = nbComputer / this.PCparPageInt;
		if (nbComputer % this.PCparPageInt != 0) {
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

	}*/

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPCparPageInt() {
		return PCparPageInt;
	}

	public void setPCparPageInt(int pCparPageInt) {
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

	public boolean isAscendant() {
		return ascendant;
	}

	public void setAscendant(boolean ascendant) {
		this.ascendant = ascendant;
	}

	// public HttpServletRequest createPage(HttpServletRequest request) {
	/*
	 * String pageNum; if (request.getParameter("page") == null) { pageNum = "1"; }
	 * else { pageNum = request.getParameter("page");
	 * 
	 * }
	 */
	/*
	 * String PCparPage; if (request.getParameter("PCparPage") == null) { PCparPage
	 * = "10"; } else { PCparPage = request.getParameter("PCparPage"); }
	 * 
	 * try { PCparPageInt = Integer.valueOf(PCparPage); pageInt =
	 * Integer.valueOf(pageNum); if (pageInt < 1) { pageInt = 1; pageNum = "1"; }
	 * 
	 * if (PCparPageInt < 1) { PCparPageInt = 1; PCparPage = "1"; } offset =
	 * (PCparPageInt * (pageInt - 1));
	 * 
	 * } catch (Exception e) { logger.info(e.getMessage()); }
	 */
	/*
	 * String orderByStr = request.getParameter("orderby"); if (orderByStr == null
	 * || orderByStr.isEmpty()) { orderByStr = "computer.id"; }
	 * 
	 * for (OrderBy ob : OrderBy.values()) { if (ob.toString().equals(orderByStr)) {
	 * orderBy = ob; } }
	 */

	/*
	 * if (request.getParameter("asc") != null &&
	 * request.getParameter("asc").equals("false")) { ascendant = false; } else {
	 * ascendant = true; }
	 */
	/*
	 * int nbComputer = 0; String search = request.getParameter("search"); if
	 * (search == null || search.isEmpty() || search.equals("dashboard")) {
	 * listDTOComputer = mappeurComputer
	 * .computerToDTO(serviceComputer.listPagination(PCparPageInt, offset, orderBy,
	 * ascendant)); nbComputer = serviceComputer.count(); } else { listDTOComputer =
	 * mappeurComputer .computerToDTO(serviceComputer.searchComputer(search,
	 * PCparPageInt, offset, orderBy, ascendant)); request.setAttribute("search",
	 * search); nbComputer = serviceComputer.searchComputerCount(search); }
	 * 
	 * nbPageTotal = nbComputer / PCparPageInt; if (nbComputer % PCparPageInt != 0)
	 * { nbPageTotal += 1; } if (nbPageTotal <= 5) { begin = 1; end = nbPageTotal; }
	 * else { if (pageInt <= 3) { begin = 1; end = 5; } else if (pageInt >=
	 * nbPageTotal - 3) { begin = nbPageTotal - 6; end = nbPageTotal; } else { begin
	 * = pageInt - 3; end = pageInt + 3; }
	 * 
	 * } if (pageInt > nbPageTotal) { pageInt = nbPageTotal - 1; pageNum =
	 * String.valueOf(pageInt); } request.setAttribute("orderby", orderByStr);
	 * request.setAttribute("asc", ascendant); request.setAttribute("PCparPage",
	 * PCparPage); request.setAttribute("page", pageNum);
	 * request.setAttribute("listComputer", listDTOComputer);
	 * request.setAttribute("sizeList", listDTOComputer.size());
	 * request.setAttribute("total", nbComputer);
	 * request.setAttribute("nbPageTotal", nbPageTotal);
	 * request.setAttribute("begin", begin); request.setAttribute("end", end);
	 * return request; }
	 */

}
