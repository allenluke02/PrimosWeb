package com.bi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class CorrectValueRequiredException extends RuntimeException  {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3963292047226151164L;

	public CorrectValueRequiredException(String key) {
		super("Correct Value of "+key + " is Required");
	}

	
}
