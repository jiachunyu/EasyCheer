package com.springrain.easycheer.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springrain.easycheer.exception.BaseException;
import com.springrain.easycheer.exception.ObjectNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ObjectNotFoundException.class })
	public ResponseEntity<ErrorResponse> handleException(HttpServletRequest httpRequest, BaseException exception) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(exception), RestUtil.REST_RESPONSE_HEADERS,
				HttpStatus.NOT_FOUND);
	}
}
