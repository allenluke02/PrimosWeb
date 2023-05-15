package com.bi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidOTPException extends RuntimeException{

	public InvalidOTPException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidOTPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidOTPException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidOTPException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidOTPException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
