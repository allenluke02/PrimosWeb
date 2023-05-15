package com.bi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorisedException extends RuntimeException {
	
	    public NotAuthorisedException(String message) {
	        super(message);
	    }

	    public NotAuthorisedException(String message, Throwable cause) {
	        super(message, cause);
	    }
	}
