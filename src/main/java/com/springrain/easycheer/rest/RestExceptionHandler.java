package com.springrain.easycheer.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springrain.easycheer.exception.BaseException;
import com.springrain.easycheer.exception.ErrorCode;
import com.springrain.easycheer.exception.ObjectNotFoundException;

@ControllerAdvice("TenantManagementController")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ObjectNotFoundException.class})
	public ResponseEntity<ErrorResponse> handleException(HttpServletRequest httpRequest, BaseException exception) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ErrorCode.TENANT_NOT_FOUND, "Can not find tenant with id " + tenantId), HttpStatus.NOT_FOUND);
	}
}
