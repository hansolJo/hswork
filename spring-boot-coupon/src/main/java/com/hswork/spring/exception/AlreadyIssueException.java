package com.hswork.spring.exception;



public class AlreadyIssueException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AlreadyIssueException(String msg) {
		super(msg);
	}
	
	public AlreadyIssueException(Exception ex) {
		super(ex);
	}
}
