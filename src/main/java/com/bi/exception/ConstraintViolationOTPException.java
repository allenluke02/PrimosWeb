package com.bi.exception;

import java.sql.SQLException;

import org.hibernate.exception.ConstraintViolationException;

public class ConstraintViolationOTPException extends ConstraintViolationException {

	public ConstraintViolationOTPException(String message, SQLException root, String constraintName) {
		super(message, root, constraintName);
		// TODO Auto-generated constructor stub
	}


}
