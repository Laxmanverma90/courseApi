package com.hcl.course.exception;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Sushil
 *
 */
@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {
	
	private LocalDate timeStamp;
	private String message;
	private String status;

}
