package com.simplilearn.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ConcreteExceptionHandler {
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public String runTimeException(Exception ex) {
		return "Error Occurred : " + ex.getMessage();
	}

}
