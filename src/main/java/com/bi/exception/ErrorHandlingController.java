package com.bi.exception;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bi.services.MessageByLocaleService;

@ControllerAdvice
@PropertySource("classpath:message.properties")
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

	private @Autowired Environment environment;
	@Autowired
	HttpServletRequest httpRequest;
	@Autowired
	MessageByLocaleService messageLocaleService;
	
	@ExceptionHandler(BIAPIException.class)
	public ResponseEntity<ExceptionResponse> specialException(BIAPIException e) throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.addErrorMessage(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(exResponse, e.getCode());

	}
	
	
	@ExceptionHandler(BIServiceException.class)
	public ResponseEntity<ExceptionResponse> generalServiceException(BIServiceException e) throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.setCode(e.status);
		exResponse.addErrorMessage(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ExceptionResponse> generalServiceException(ForbiddenException e) throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.setCode(HttpStatus.FORBIDDEN.value());
		exResponse.addErrorMessage(e.getMessage());
		String currentUrl = httpRequest.getRequestURI();
		exResponse.setPath(currentUrl);
		return new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(InvalidOTPException.class)
	public ResponseEntity<ExceptionResponse> generalServiceException(InvalidOTPException e) throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse();
		exResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
		exResponse.addErrorMessage(e.getMessage());
		return new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionresponse = new ExceptionResponse();
		BindingResult bindingResult = ex.getBindingResult();
		List<ObjectError> list = bindingResult.getAllErrors();
		String currentUrl = httpRequest.getRequestURI();
		exceptionresponse.setPath(currentUrl);
		for (ObjectError error : list) {
			exceptionresponse.addErrorMessage(error.getDefaultMessage());
		}
		exceptionresponse.setCode(status.value());
		return new ResponseEntity(exceptionresponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {
		String errorMessage=null;
		ExceptionResponse exResponse = new ExceptionResponse();
		String currentUrl = httpRequest.getRequestURI();
		exResponse.setPath(currentUrl);
		exResponse.addErrorMessage(ex.getMessage().toString());
		return new ResponseEntity(exResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
			ExceptionResponse exResponse = new ExceptionResponse();
			String currentUrl = httpRequest.getRequestURI();
			exResponse.setPath(currentUrl);
			Pattern pattern = Pattern.compile(".*CONSTRAINT `(.*)` FOREIGN KEY.*");
			if(ex.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException cVE = (ConstraintViolationException) ex.getCause();
				if(cVE.getConstraintName() == null) {
					String errMessage = cVE.getSQLException().getMessage();
					if(errMessage.contains("foreign key constraint fails")) {
						Matcher mathcher = pattern.matcher(errMessage);
						if(mathcher.matches()) {
							String constraint = mathcher.group(1);
							exResponse.addErrorMessage(messageLocaleService.getMessage(constraint.replaceAll("_",".")));
							request.getDescription(true);
							exResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
							return new ResponseEntity(exResponse, HttpStatus.UNPROCESSABLE_ENTITY);
						}
					}
				} else {
					exResponse.addErrorMessage(messageLocaleService.getMessage(cVE.getConstraintName().replaceAll("_",".")));
					request.getDescription(true);
					exResponse.setCode(HttpStatus.CONFLICT.value());
					return new ResponseEntity(exResponse, HttpStatus.CONFLICT);
				}
				
			}
			
			return null;
	}
	
	@ExceptionHandler(BIValidationException.class)
	public ResponseEntity<ExceptionResponse> handleValidationException(BIValidationException ex) throws Exception {
		ExceptionResponse response = new ExceptionResponse();
		response.setCode(HttpStatus.BAD_REQUEST.value());
		response.addErrorMessage(ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
}

 
