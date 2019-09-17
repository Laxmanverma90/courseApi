package com.hcl.course.exception;

/**
 * 
 * @author Sushil
 *
 */
public class InvalidUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidUserException(String message)
	{
		super(message);
	}

}
