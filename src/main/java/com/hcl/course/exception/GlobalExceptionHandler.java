package com.hcl.course.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Laxman
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<ErrorResponse> invalidUserExceptionHandler(InvalidUserException exception)
	{
		ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(), exception.getMessage(),
				"Failed");

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}


	/**
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> recordNotFoundException(RecordNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(LocalDate.now(),ex.getMessage(), "Failed");
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
