package com.bi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException  {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3963292047226151164L;

	public EntityNotFoundException(String message) {
		super(message);
	}

	
}
