package com.bi.exception;

import lombok.Getter;

@Getter
public class BIValidationException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7982672894935944274L;

	public BIValidationException(String message) {
        super(message);
    }
}
