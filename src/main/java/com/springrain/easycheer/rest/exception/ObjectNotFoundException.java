package com.springrain.easycheer.rest.exception;

public class ObjectNotFoundException extends RestBaseException {

	public ObjectNotFoundException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
