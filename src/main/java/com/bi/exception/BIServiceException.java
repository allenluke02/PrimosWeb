package com.bi.exception;

public class BIServiceException extends Exception{

	public int status = 500;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6252008437485725655L;

	public BIServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BIServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BIServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BIServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public BIServiceException(String message, int status) {
		super(message);
		this.status = status;
	}

	public BIServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
