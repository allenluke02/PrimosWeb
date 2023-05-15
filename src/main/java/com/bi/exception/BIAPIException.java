package com.bi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BIAPIException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus code;
	private String message;
		
	public BIAPIException(HttpStatus httpStatus, String message) {
		super();
		this.code = httpStatus;
		this.message = message;
	}
}
