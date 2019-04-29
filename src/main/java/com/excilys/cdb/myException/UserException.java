package com.excilys.cdb.myException;

public class UserException extends Exception {
	
	private String msg;
	
	public UserException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

}
